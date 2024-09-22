import pandas as pd
from matplotlib import pyplot as plt
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import StandardScaler, MinMaxScaler
from sklearn.metrics import mean_squared_error, r2_score

# 读取数据集
df_x = pd.read_csv("./topsis_results.csv")
data = pd.read_csv('./needed_data_SARIMA.csv')

# 数据分组
unique_codes = df_x['单品名称'].unique()
data = data[data['单品名称'].isin(unique_codes)]

# 存储每个品类的回归结果
results = []

for category in data['单品名称'].unique():
    subset = data[data['单品名称'] == category]
    X = subset[['销量(千克)']].values.reshape(-1, 1)
    y = subset['每日平均销售单价(元/千克)'].values

    # 拟合线性回归模型
    model = LinearRegression()
    model.fit(X, y)

    # 计算回归系数、截距
    elasticity = model.coef_[0]
    intercept = model.intercept_

    # 计算决定系数 R^2 和均方误差 MSE
    y_pred = model.predict(X)
    r2 = r2_score(y, y_pred)
    mse = mean_squared_error(y, y_pred)

    # 存储结果
    results.append({
        '单品名称': category,
        '需求弹性系数 (β)': elasticity,
        '截距': intercept,
        '决定系数 R^2': r2,
        '均方误差 MSE': mse
    })

    # 绘制真实值与预测值的对比图
    plt.scatter(X, y, color='blue', label='真实值')
    plt.plot(X, y_pred, color='red', linewidth=2, label='拟合线')
    plt.xlabel('平均销售单价(元/千克)')
    plt.ylabel('销量(千克)')
    plt.title('定价与销量的线性回归分析')
    plt.legend()
    plt.grid(True)
    plt.show()

# 将结果存储到数据集中
results_df = pd.DataFrame(results)
results_df.to_csv('./linear_regression_results.csv', index=False)

print("线性回归结果已保存。")
