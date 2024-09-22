import os
import pandas as pd

df = pd.read_csv("..\数据拼合\sales_day_simple.csv")
df_1 = pd.read_csv("..\预处理\cleaned_df1.csv")

df = df.drop(columns=['打折数量','原价数量'])

# 合并两个数据集
df_merged = pd.merge(df, df_1, on='单品编码',how='left')

df_merged = df_merged.groupby(['销售日期', '分类编码', '分类名称'], as_index=False).agg(
    销量=('销量(千克)', 'sum'),
    平均销量=('销量(千克)', 'mean'),
)
df_merged.rename(columns={'销量': '销量(千克)','平均销量': '平均销量(千克)'}, inplace=True)  # 重命名列

# 透视表
pivot_table = df_merged.pivot(index='销售日期', columns='分类名称', values=['销量(千克)','平均销量(千克)'])

# 填充空值为 0
pivot_table = pivot_table.fillna(0)

# 保存处理后的数据
output_files = 'sales_day_multi.csv'
try:
    pivot_table.to_csv(output_files, index=True)
    print(f'数据已保存到 {output_files}')
except Exception as e:
    print(f"保存时发生错误: {e}")


