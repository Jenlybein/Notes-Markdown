import pandas as pd

# 读取CSV文件
df = pd.read_csv('../数据拼合/sales_day_detail.csv')
df_1 = pd.read_csv('../预处理/cleaned_df1.csv')
df_3 = pd.read_csv('../预处理/cleaned_df3.csv')
df_4 = pd.read_csv('../预处理/cleaned_df4.csv')
df_beta = pd.read_csv('../题2/linear_regression_results.csv')
df_x = pd.read_csv("./topsis_results.csv")

# 将日期列转换为datetime格式
df['销售日期'] = pd.to_datetime(df['销售日期'])

# 每日销量简洁信息
day_simple = df[df['销售类型'] == 1]  # 只保留销售类型为销售的数据
day_simple = day_simple.groupby(['销售日期', '单品编码'], as_index=False).agg(
    销量总和=('销量(千克)', 'sum'),
    销售单价=('销售单价(元/千克)', 'mean')
)
day_simple.rename(columns={'销量总和': '销量(千克)','销售单价':'每日平均销售单价(元/千克)'}, inplace=True)  # 重命名列

# 将日期列转换为 datetime 格式
df_3['日期'] = pd.to_datetime(df_3['日期'])

# 获取批发成本
df = pd.merge(day_simple,df_3,left_on=['单品编码','销售日期'], right_on=['编码','日期'],how='left')

# 获取单品名称
df = pd.merge(df,df_1[['单品编码','单品名称','分类名称']],on='单品编码',how='left')

# 去除重复列
df = df.drop(columns=['编码','日期'])

# 筛选出2023年6月24日至2023年6月30日的数据
start_date = '2023-06-24'
end_date = '2023-06-30'
mask = (df['销售日期'] >= start_date) & (df['销售日期'] <= end_date)
filtered_df = df[mask]

# 数据分组
unique_codes = df_x['单品名称'].unique()
df = filtered_df[filtered_df['单品名称'].isin(unique_codes)]

# 获取平均值
df = df.groupby(['单品编码','单品名称','分类名称'], as_index=False).agg(
    平均销量=('销量(千克)', 'mean'),
    平均定价=('每日平均销售单价(元/千克)', 'mean'),
    平均批发价=('批发价格(元/千克)','mean')
)
df.rename(columns={'平均销量': '平均销量(千克)','平均定价':'平均定价(元/千克)','平均批发价':'平均批发价(元/千克)'}, inplace=True)  # 重命名列

# 损耗率
df = pd.merge(df,df_4[['单品名称','损耗率(%)']],on='单品名称',how='left')

# 价格弹性系数
df = pd.merge(df,df_beta[['分类名称','需求弹性系数 (β)']],on='分类名称',how='left')

# 补货量基准
df['补货量基准(千克)'] = df['平均销量(千克)'] / (1-(df['损耗率(%)']/100))

# 保存唯一值到新的CSV文件
df.to_csv('needed_data_model.csv', index=False)
