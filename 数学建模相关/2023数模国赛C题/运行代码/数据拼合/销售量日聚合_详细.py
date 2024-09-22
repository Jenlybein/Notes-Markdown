import os
import pandas as pd

df = pd.read_csv('../预处理/cleaned_df2.csv')

df = df.drop(columns=['扫码销售时间'])

result = df.groupby(['销售日期', '单品编码', '销售单价(元/千克)', '销售类型', '是否打折销售'], as_index=False).agg(
    打折原价计数总量=('是否打折销售', 'count'),  # 计数每个分组的记录数
    销量总和=('销量(千克)', 'sum')
)

result.rename(columns={'打折原价计数总量': '打折/原价的计数总量', '销量总和': '销量(千克)'}, inplace=True)

# 保存处理后的数据
output_file = 'sales_day_detail.csv'

try:
    result.to_csv(output_file, index=False)  # 不保存索引列
    print(f'数据已保存到 {output_file}')
except Exception as e:
    print(f"保存数据时发生错误: {e}")
