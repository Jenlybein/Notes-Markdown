import pandas as pd
import numpy as np

# 读取数据
data = pd.read_csv('needed_data_熵权法-TOPSIS.csv')

# 处理正向指标
def normalize_positive(column):
    return (column - column.min()) / (column.max() - column.min())

# 处理反向指标
def normalize_negative(column):
    inverted = 1 / (column + 1e-7)  # 防止除以零
    normalized_inverted = normalize_positive(inverted)
    return 1 / (normalized_inverted + 1e-7)

# 标准化数据
data_normalized = pd.DataFrame()
data_normalized['总销量(千克)'] = normalize_positive(data['总销量(千克)'])
data_normalized['销售次数'] = normalize_positive(data['销售次数'])
data_normalized['平均利润(元/千克)'] = normalize_positive(data['平均利润(元/千克)'])
data_normalized['总利润(元)'] = normalize_positive(data['总利润(元)'])
data_normalized['打折率(%)'] = normalize_negative(data['打折率(%)'])
data_normalized['损耗率(%)'] = normalize_negative(data['损耗率(%)'])

# 计算信息熵
def calculate_entropy(column):
    p = column / column.sum()
    return -np.sum(p * np.log(p + 1e-7)) / np.log(len(column))

entropies = data_normalized.apply(calculate_entropy)

# 计算权重
entropy_weights = (1 - entropies) / (1 - entropies).sum() * 100

results_df = pd.DataFrame({
    '指标': entropy_weights.index,
    '信息熵值E': entropies.values,
    '权重(%)': entropy_weights.values
})

results_df.to_csv('entropy_and_weights.csv', index=False)

print("信息熵值、信息效用值和权重已保存到 'entropy_and_weights.csv'")