import pandas as pd
from scipy.optimize import minimize


# 最优值求解
def optimize_for_date(date_data):
    # 初始化全局销量字典
    F_dict = {}
    # 定义目标函数
    def objective(x):
        H_total = 0
        for category in categories_unique:
            idx = category_to_index[category]

            # 从优化变量中提取定价
            C = x[idx]

            # 提取数据行
            row = date_data[date_data['分类名称'] == category]
            if row.empty:
                continue  # 如果行为空，跳过该品类

            row = row.iloc[0]

            C_predict = row['预测值_平均销售单价(元/千克)']
            F_predict = row['预测值_平均销量(千克)']
            I_predict = row['预测值_平均批发价格(元/千克)']
            beta = row['需求弹性系数 (β)(%)'] / 100
            D = row['平均损耗率(%)'] / 100

            # 计算销量调整
            adjustment_factor = 1 + beta * ((C - C_predict) ** 3)
            # 计算 F_i
            F_i = F_predict * adjustment_factor
            # 存储计算得来的 F_i
            F_dict[category] = F_i

            related_sales = 0

            F_i_opt = F_i + related_sales

            # 计算收益和成本
            J_i = C * (F_i_opt / (1 - D))
            K_i = I_predict * F_i_opt
            H_i = J_i - K_i

            # 累加总收益
            H_total += H_i

        return -H_total  # 最大化目标函数

    # 初始猜测值
    initial_x = [0] * 7
    for category in categories_unique:
        idx = category_to_index[category]
        row = date_data[date_data['分类名称'] == category]
        if not row.empty:
            initial_x[idx] = row.iloc[0]['预测值_平均销售单价(元/千克)']
        else:
            initial_x[idx] = 1  # 给一个默认初始值

    # 约束条件：所有定价都应非负
    constraints = [{'type': 'ineq', 'fun': lambda x: x[i]} for i in range(len(initial_x))]

    # 优化
    result = minimize(objective, initial_x, constraints=constraints, method='SLSQP')

    # 提取优化后的定价
    optimized_values = result.x
    date_data['最优定价(元/千克)'] = [optimized_values[category_to_index[cat]] for cat in date_data['分类名称']]

    return date_data

# 加载数据
data_df = pd.read_csv('model_needed_data.csv')

# 获取所有品类名称和日期
categories_unique = data_df['分类名称'].unique()
date_unique = data_df['日期'].unique()

# 初始化全局数据结构
category_to_index = {category: idx for idx, category in enumerate(categories_unique)}

# 按不同日期进行表格分类，然后优化
optimized_dfs = []
for date in date_unique:
    df = data_df[data_df['日期'] == date]
    optimized_dfs.append(optimize_for_date(df))

# 合并所有优化后的数据
optimized_data_df = pd.concat(optimized_dfs)

# 计算最优补货量
optimized_data_df['最优补货量(千克)'] = optimized_data_df.apply(lambda row: (row['预测值_平均销量(千克)'] * (1 + (row['需求弹性系数 (β)(%)'] / 100) * ((row['最优定价(元/千克)'] - row['预测值_平均销售单价(元/千克)']) ** 3))) / (1 - row['平均损耗率(%)'] / 100), axis=1)

# 保存结果到CSV
optimized_data_df[['日期','分类名称','最优定价(元/千克)','最优补货量(千克)']].to_csv('optimized_results2.csv', index=False)

print("优化完成，结果已保存到 optimized_results2.csv")
