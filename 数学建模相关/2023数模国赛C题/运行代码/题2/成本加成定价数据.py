import pandas as pd

df = pd.read_csv('../数据拼合/sales_day_detail.csv')
df_pifa = pd.read_csv('../预处理/cleaned_df3.csv')
df_1 = pd.read_csv("..\预处理\cleaned_df1.csv")

# 每日销量简洁信息
df = df[df['销售类型'] == 1]  # 只保留销售类型为销售的数据

df['销售额'] = df['销售单价(元/千克)'] * df['销量(千克)']

df_group = df.rename(columns={'销量': '销量(千克)', '单品编码': '编码', '销售日期': '日期'})

# 合并两个数据集，按“销售日期”和“单品编码”匹配
df_merged = pd.merge(df_group, df_pifa, on=['日期', '编码'], how='left')

# 计算“销售额-批发价格”
df_merged['成本额'] = df_merged['批发价格(元/千克)'] * df_merged['销量(千克)']

# 合并两个数据集，准备拼合分类
df_merged = df_merged.rename(columns={'编码': '单品编码', '日期': '销售日期'})
df_merged = pd.merge(df_merged, df_1, on='单品编码',how='left')

df_merged = df_merged.groupby(['销售日期', '分类编码', '分类名称'], as_index=False).agg(
    销量=('销量(千克)', 'sum'),
    平均销量=('销量(千克)','mean'),
    销售额总和=('销售额', 'sum'),
    批发额总和=('成本额', 'mean'),
)
df_merged.rename(columns={'销量': '销量(千克)','平均销量': '平均销量(千克)'}, inplace=True)  # 重命名列

df_merged['平均销售单价(元/千克)'] = df_merged['销售额总和'] / df_merged['销量(千克)']
df_merged['平均批发价格(元/千克)'] = df_merged['批发额总和'] / df_merged['销量(千克)']

df_merged['成本加成比例'] = (df_merged['平均销售单价(元/千克)'] - df_merged['平均批发价格(元/千克)']) / df_merged['平均批发价格(元/千克)']

# 保存处理后的数据

output_files = 'cost_plus_pricing.csv'
try:
    df_merged.to_csv(output_files, index=False)
    print(f'数据已保存到 {output_files}')
except Exception as e:
    print(f"保存时发生错误: {e}")
