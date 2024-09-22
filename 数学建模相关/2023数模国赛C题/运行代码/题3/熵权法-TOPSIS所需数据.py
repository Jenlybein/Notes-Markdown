import pandas as pd

# 读取CSV文件
df = pd.read_csv('../数据拼合/sales_day_detail.csv')
df_1 = pd.read_csv('../预处理/cleaned_df1.csv')
df_3 = pd.read_csv('../预处理/cleaned_df3.csv')
df_4 = pd.read_csv('../预处理/cleaned_df4.csv')
df_x = pd.read_csv("./s可售单品.csv")

# 将日期列转换为datetime格式
df['销售日期'] = pd.to_datetime(df['销售日期'])

# 每日销量简洁信息
day_simple = df[df['销售类型'] == 1]  # 只保留销售类型为销售的数据
day_simple = day_simple.groupby(['销售日期', '单品编码'], as_index=False).agg(
    销量总和=('销量(千克)', 'sum'),
    打折数量=('打折/原价的计数总量', lambda x: x[df['是否打折销售'] == 1].sum()),
    原价数量=('打折/原价的计数总量', lambda x: x[df['是否打折销售'] == 0].sum()),
    销售次数=('打折/原价的计数总量','sum'),
    销售单价=('销售单价(元/千克)', 'mean')
)
day_simple.rename(columns={'销量总和': '销量(千克)','销售单价':'每日平均销售单价(元/千克)'}, inplace=True)  # 重命名列

# 将日期列转换为datetime格式
df_3['日期'] = pd.to_datetime(df_3['日期'])
# 获取批发成本
df = pd.merge(day_simple,df_3,left_on=['单品编码','销售日期'], right_on=['编码','日期'],how='left')

# 获取每千克利润
df['平均利润(元/千克)'] = df['每日平均销售单价(元/千克)'] - df['批发价格(元/千克)']

df = df.groupby(['单品编码'], as_index=False).agg(
    销量总和=('销量(千克)', 'sum'),
    打折数量=('打折数量', 'sum'),
    原价数量=('原价数量', 'sum'),
    销售次数=('销售次数','sum'),
    销售单价=('每日平均销售单价(元/千克)', 'mean'),
    批发价格=('批发价格(元/千克)', 'mean'),
    平均利润=('平均利润(元/千克)','mean')
)
df.rename(columns={'销量总和': '总销量(千克)','销售单价':'平均销售单价(元/千克)','批发价格':'平均批发价格(元/千克)','平均打折率':'平均打折率(%)','平均利润':'平均利润(元/千克)'}, inplace=True)  # 重命名列

# 获取打折率
df['打折率(%)'] = (df['打折数量'] / df['原价数量'] * 100).fillna(0).apply(lambda x: min(x, 200))

# 获取损失率
df = pd.merge(df,df_4[['单品编码', '损耗率(%)']],on=['单品编码'],how='left')

# 获取总利润
df['总利润(元)'] = df['总销量(千克)']*df['平均利润(元/千克)']

# 获取单品名称
df = pd.merge(df,df_1[['单品编码','单品名称']],on='单品编码',how='left')

# 只保留可售单品
unique_codes = df_x['单品名称'].unique()
df = df[df['单品名称'].isin(unique_codes)]

# 保存唯一值到新的CSV文件
df.to_csv('needed_data_熵权法-TOPSIS.csv', index=False)