import pandas as pd

df_p = pd.read_csv('./SARIMA_reshaped_data.csv')
df_b = pd.read_csv('./linear_regression_results.csv')
df_5 = pd.read_csv('../预处理/cleaned_df5.csv')

# 合并数据
merged_df = pd.merge(df_p, df_5, left_on='分类名称', right_on='小分类名称', how='left')
merged_df = pd.merge(merged_df, df_b[['分类名称','需求弹性系数 (β)']], left_on='分类名称', right_on='分类名称', how='left')

# 每日销量简洁信息
merged_df['补货量基准(千克)'] = merged_df['预测值_平均销量(千克)'] / (1-(merged_df['平均损耗率(%)_小分类编码_不同值']/100))

merged_df.rename(columns={'需求弹性系数 (β)': '需求弹性系数 (β)(%)', '平均损耗率(%)_小分类编码_不同值': '平均损耗率(%)'}, inplace=True)


# 保存处理后的数据
path = "model_needed_data.csv"
df = merged_df
try:
    df.to_csv(path, index=False)
    print(f'数据已保存到 {path}')
except Exception as e:
    print(f"保存时发生错误: {e}")
