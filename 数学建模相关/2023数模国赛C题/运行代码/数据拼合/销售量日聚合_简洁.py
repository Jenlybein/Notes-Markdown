import os
import pandas as pd

df = pd.read_csv('./sales_day_detail.csv')

# 每日销量简洁信息
day_simple = df[df['销售类型'] == 1]  # 只保留销售类型为销售的数据
day_simple = day_simple.groupby(['销售日期', '单品编码'], as_index=False).agg(
    销量总和=('销量(千克)', 'sum'),
    销量单价=('销售单价(元/千克)', 'mean'),
    打折数量=('打折/原价的计数总量', lambda x: x[df['是否打折销售'] == 1].sum()),
    原价数量=('打折/原价的计数总量', lambda x: x[df['是否打折销售'] == 0].sum())
)
day_simple.rename(columns={'销量总和': '销量(千克)','销量单价':'每日平均销售单价(元/千克)'}, inplace=True)  # 重命名列

# 保存处理后的数据
output_files = 'sales_day_simple.csv'
try:
    day_simple.to_csv(output_files, index=False)
    print(f'{output_files} 数据已保存')
except Exception as e:
    print(f"保存 {output_files} 时发生错误: {e}")
