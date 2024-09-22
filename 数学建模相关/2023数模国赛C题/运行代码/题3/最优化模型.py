import pandas as pd
from scipy.optimize import minimize


# 最优值求解
def optimize_for_date(date_data):
    # 定义目标函数
    def objective(x):
        H_total = 0
        for category in categories_unique:
            idx = category_to_index[category]

            # 从优化变量中提取定价
            C = x[idx]

            # 提取数据行
            row = date_data[date_data['单品名称'] == category]
            if row.empty:
                continue  # 如果行为空，跳过该品类

            row = row.iloc[0]

            C_avg = row['平均定价(元/千克)']
            F_avg = row['平均销量(千克)']
            I_avg = row['平均批发价(元/千克)']
            beta = row['需求弹性系数 (β)'] / 100
            D = row['损耗率(%)'] / 100

            # 计算销量调整
            adjustment_factor = 1 + beta * ((C - C_avg) ** 3)
            # 计算 F_i
            F_i = F_avg * adjustment_factor

            # 计算收益和成本
            B_i = F_i / (1 - D)
            J_i = C * F_i
            K_i = I_avg * B_i
            H_i = J_i - K_i

            # 累加总收益
            H_total += H_i

        return -H_total  # 最大化目标函数

    # 初始猜测值
    initial_x = [0] * len(categories_unique)
    for category in categories_unique:
        idx = category_to_index[category]
        row = date_data[date_data['单品名称'] == category]
        if not row.empty:
            initial_x[idx] = row.iloc[0]['平均定价(元/千克)']
        else:
            initial_x[idx] = 1  # 给一个默认初始值

    # 约束条件：所有定价都应非负，并且保证 B_j >= 2.5
    constraints = []
    B_min = 2.5
    for category in categories_unique:
        idx = category_to_index[category]
        row = date_data[date_data['单品名称'] == category]
        if not row.empty:
            F_avg = row.iloc[0]['平均销量(千克)']
            C_avg = row.iloc[0]['平均定价(元/千克)']
            beta = row.iloc[0]['需求弹性系数 (β)'] / 100
            D = row.iloc[0]['损耗率(%)'] / 100
            constraints.append({'type': 'ineq', 'fun': lambda x, idx=idx, F_avg=F_avg, C_avg=C_avg, beta=beta, D=D, B_min=B_min: (F_avg * (1 + beta * ((x[idx] - C_avg) ** 3)) / (1 - D)) - B_min})
        constraints.append({'type': 'ineq', 'fun': lambda x, idx=idx: x[idx]})

    # 优化
    result = minimize(objective, initial_x, constraints=constraints, method='SLSQP')

    # 提取优化后的定价
    optimized_values = result.x
    date_data['最优定价(元/千克)'] = [optimized_values[category_to_index[cat]] for cat in date_data['单品名称']]

    return date_data

# 加载数据
data_df = pd.read_csv('needed_data_model.csv')

# 获取所有单品名称和日期
categories_unique = data_df['单品名称'].unique()

# 初始化全局数据结构
category_to_index = {category: idx for idx, category in enumerate(categories_unique)}

# 优化
df = optimize_for_date(data_df)

# 计算最优补货量
df['最优补货量(千克)'] = df.apply(lambda row: (row['平均销量(千克)'] * (1 + (row['需求弹性系数 (β)'] / 100) * ((row['最优定价(元/千克)'] - row['平均定价(元/千克)']) ** 3))) / (1 - row['损耗率(%)'] / 100), axis=1)

# 保存结果到CSV
df[['单品编码','单品名称','最优定价(元/千克)','最优补货量(千克)']].to_csv('optimized_results.csv', index=False)

print("优化完成，结果已保存到 optimized_results.csv")
