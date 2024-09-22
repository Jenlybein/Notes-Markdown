import pandas as pd

df = pd.read_csv('./sales_day_simple.csv', encoding='UTF8')

# 按销售年月和单品编码分组并求和
result = df.groupby(['单品编码']).agg({
    '销量(千克)': 'sum',
    '打折数量': 'sum',
    '原价数量': 'sum'
}).reset_index()

# 将结果保存到新的Excel文件
result.to_csv('./sales_total.csv', index=False)
