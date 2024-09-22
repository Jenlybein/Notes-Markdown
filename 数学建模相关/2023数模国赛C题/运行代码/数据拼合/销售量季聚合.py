import pandas as pd

# 假设你的数据存储在一个名为data.xlsx的文件中
df = pd.read_csv('./sales_day_simple.csv')

# 将销售日期转换为日期格式
df['销售日期'] = pd.to_datetime(df['销售日期'], format='%Y-%m-%d')

# 提取年月
df['销售年月'] = df['销售日期'].dt.to_period('Q')

# 按销售年月和单品编码分组并求和
result = df.groupby(['销售年月', '单品编码']).agg({
    '销量(千克)': 'sum',
    '打折数量': 'sum',
    '原价数量': 'sum'
}).reset_index()

# 将结果保存到新的Excel文件
result.to_csv('./sales_quarter.csv', index=False)
