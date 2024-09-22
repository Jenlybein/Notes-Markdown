import pandas as pd

# 读取CSV文件
df = pd.read_csv('../数据拼合/sales_day_simple.csv')
df_1 = pd.read_csv('../预处理/cleaned_df1.csv')

# 将日期列转换为datetime格式
df['销售日期'] = pd.to_datetime(df['销售日期'])

# 筛选出2023年6月24日至2023年6月30日的数据
start_date = '2023-06-24'
end_date = '2023-06-30'
mask = (df['销售日期'] >= start_date) & (df['销售日期'] <= end_date)
filtered_df = df[mask]

# 获取单品编码的唯一值
unique_codes = filtered_df["单品编码"].unique()

# 将唯一值转换为DataFrame
unique_codes_df = pd.DataFrame(unique_codes, columns=['单品编码'])

# 获取单品名称
df = pd.merge(unique_codes_df,df_1[['单品编码','单品名称']],on='单品编码',how='left')

# 保存唯一值到新的CSV文件
df.to_csv('s可售单品.csv', index=False)