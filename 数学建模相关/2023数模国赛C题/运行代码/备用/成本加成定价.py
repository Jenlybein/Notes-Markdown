import os
import pandas as pd

df = pd.read_csv("..\dataset\step3\sales_day_profit_2.csv",encoding='gbk')
df_1 = pd.read_csv("..\dataset\step1\cleaned_df1.csv")

df = df.drop(columns=['利润'])

# 合并两个数据集
df_merged = pd.merge(df, df_1, on='单品编码',how='left')

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
output_dir = '../dataset/step3/'
if not os.path.exists(output_dir):
    os.makedirs(output_dir)

output_files = {
    'df_merged': os.path.join(output_dir, 'cost_plus_pricing.csv'),
}

for df_name, path in output_files.items():
    df = globals()[df_name]  # 使用 globals() 获取 DataFrame
    try:
        df.to_csv(path, index=False)
        print(f'{df_name} 数据已保存到 {path}')
    except Exception as e:
        print(f"保存 {df_name} 时发生错误: {e}")
