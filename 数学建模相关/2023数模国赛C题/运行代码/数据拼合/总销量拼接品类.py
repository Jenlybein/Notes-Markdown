import os
import pandas as pd

df = pd.read_csv(".\sales_total.csv")
df_1 = pd.read_csv("..\预处理\cleaned_df1.csv")

df = df.drop(columns=['打折数量','原价数量'])

# 合并两个数据集
df_merged = pd.merge(df, df_1[['单品编码', '单品名称', '分类编码', '分类名称']], on='单品编码',how='left')


# 保存处理后的数据
output_files = 'sales_total_multi.csv'
try:
    df_merged.to_csv(output_files, index=False)
    print(f'数据已保存到')
except Exception as e:
    print(f"保存时发生错误: {e}")


