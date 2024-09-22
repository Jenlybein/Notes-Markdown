import os
import pandas as pd

df = pd.read_csv("..\数据拼合\sales_day_simple.csv")
df.drop(columns=['打折数量','原价数量'])

# 汇总相同日期和单品编码的销量
df_summary = df.groupby(['销售日期', '单品编码']).sum().reset_index()

# 透视表
pivot_table = df_summary.pivot(index='销售日期', columns='单品编码', values='销量(千克)')

# 填充空值为 0
pivot_table = pivot_table.fillna(0)

# 保存处理后的数据

output_files = 'sales_day_single.csv'

try:
    pivot_table.to_csv(output_files, index=True)
    print(f'数据已保存到 {output_files}')
except Exception as e:
    print(f"保存时发生错误: {e}")
