import os
import pandas as pd
from scipy.stats import skew, kurtosis

df = pd.read_csv("./sales_day_multi_2.csv",encoding='gbk')

# 删除"销售日期"列，以便提取数值特征
df_numeric = df.drop(columns=['销售日期'])

# 计算统计特征
mean = df_numeric.mean()
std = df_numeric.std()
min_val = df_numeric.min()
max_val = df_numeric.max()
median = df_numeric.median()
skewness = df_numeric.apply(skew)
kurt = df_numeric.apply(kurtosis)

# 存储特征
def nums(name):
    ll = [max_val[name], min_val[name], mean[name], median[name], std[name], skewness[name], kurt[name]]
    return ll

features_df = pd.DataFrame({
    '特征': ['最大值', '最小值', '均值', '中位数', '标准差', '偏度', '峰度'],
    '水生根茎类': nums('水生根茎类'),
    '花叶类': nums('花叶类'),
    '花菜类': nums('花菜类'),
    '茄类': nums('茄类'),
    '辣椒类': nums('辣椒类'),
    '食用菌': nums('食用菌')
})

# 保存处理后的数据
output_files = 's数字特征.csv'
try:
    features_df.to_csv(output_files, index=False)
    print(f'数据已保存到 {output_files}')
except Exception as e:
    print(f"保存时发生错误: {e}")

