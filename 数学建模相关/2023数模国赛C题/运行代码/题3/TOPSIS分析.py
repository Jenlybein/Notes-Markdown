import pandas as pd
import numpy as np

# 读取数据和权重
data = pd.read_csv('needed_data_熵权法-TOPSIS.csv')
weights = pd.read_csv('entropy_and_weights.csv')

# 将权重列转为字典
weights_dict = weights.set_index('指标')['权重(%)'].to_dict()

# 选择用于TOPSIS分析的列
columns = [
    '总销量(千克)', '销售次数', '平均利润(元/千克)', '总利润(元)', '打折率(%)', '损耗率(%)'
]

# 创建标准化后的数据
def normalize_positive(column):
    return (column - column.min()) / (column.max() - column.min())

def normalize_negative(column):
    inverted = 1 / (column + 1e-7)  # 防止除以零
    normalized_inverted = normalize_positive(inverted)
    return 1 / (normalized_inverted + 1e-7)

data_normalized = pd.DataFrame()
data_normalized['总销量(千克)'] = normalize_positive(data['总销量(千克)'])
data_normalized['销售次数'] = normalize_positive(data['销售次数'])
data_normalized['平均利润(元/千克)'] = normalize_positive(data['平均利润(元/千克)'])
data_normalized['总利润(元)'] = normalize_positive(data['总利润(元)'])
data_normalized['打折率(%)'] = normalize_negative(data['打折率(%)'])
data_normalized['损耗率(%)'] = normalize_negative(data['损耗率(%)'])

# 计算加权矩阵
data_weighted = data_normalized.copy()
for col in columns:
    data_weighted[col] *= weights_dict[col]

# 计算理想解和负理想解
ideal_solution = data_weighted.max()
negative_ideal_solution = data_weighted.min()

# 计算每个方案到理想解和负理想解的距离
def euclidean_distance(row, ideal_solution):
    return np.sqrt(((row - ideal_solution) ** 2).sum())

distances_to_ideal = data_weighted.apply(lambda row: euclidean_distance(row, ideal_solution), axis=1)
distances_to_negative_ideal = data_weighted.apply(lambda row: euclidean_distance(row, negative_ideal_solution), axis=1)

# 计算TOPSIS分数
topsis_scores = distances_to_negative_ideal / (distances_to_ideal + distances_to_negative_ideal)

# 将结果加入原数据
data['TOPSIS得分'] = topsis_scores

# 计算排名
data['排名'] = data['TOPSIS得分'].rank(ascending=False, method='min').astype(int)

# 按排名排序
data_sorted = data.sort_values(by='排名')

# 提取所需数据
data_sorted = data_sorted[['排名','单品编码','单品名称','TOPSIS得分']]
data_sorted = data_sorted[data_sorted['排名']<=33]

# 保存结果
data_sorted.to_csv('topsis_results.csv', index=False)

print("TOPSIS分析结果和排名已保存")
