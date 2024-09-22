import os
import pandas as pd

data = pd.read_csv("./sales_day_single.csv")
results = []
catecount = [0,0,0]

# 转换销售日期为datetime格式
data['销售日期'] = pd.to_datetime(data['销售日期'], format='%Y-%m-%d')
# 计算每个季度和每个季节的销售天数
data['年份'] = data['销售日期'].dt.year
data['季节'] = (data['销售日期'].dt.month % 12) // 3 + 1

# 逐列处理
item_codes = data.columns[1:-2]
for item in item_codes:
    # 计算每个单品在三年内销售量非0的总天数
    total_nonzero_days = data[data[item] > 0].shape[0]

    # 计算每年销售量非0的天数
    years = data[data[item] > 0].groupby('年份').size()
    years = years.reindex(data['年份'].unique(), fill_value=0)
    yearly_avg_nonzero_days = years.mean()

    # 计算季节销售量非0的天数
    seasonal_nonzero_days = data[data[item] > 0].groupby(['年份', '季节']).size()
    all_periods = pd.MultiIndex.from_product(
        [data['年份'].unique(), [1,2,3,4]],
        names=['年份', '季节']
    )
    seasonal_nonzero_days = seasonal_nonzero_days.reindex(all_periods, fill_value=0)

    # 计算各季节销售量非0的天数
    spring_days = seasonal_nonzero_days.loc[(slice(None), 2)].mean()
    summer_days = seasonal_nonzero_days.loc[(slice(None), 3)].mean()
    autumn_days = seasonal_nonzero_days.loc[(slice(None), 4)].mean()
    winter_days = seasonal_nonzero_days.loc[(slice(None), 1)].mean()
    seasonal_mean = seasonal_nonzero_days.mean()

    # 分类规则
    if total_nonzero_days>750 or yearly_avg_nonzero_days > 280 or (spring_days > 20 and summer_days > 20 and autumn_days > 20 and winter_days > 20):
        category = '常年型蔬菜'
        catecount[0]+=1

    elif total_nonzero_days < 60 or yearly_avg_nonzero_days < 15 or (spring_days < 10 and summer_days < 10 and autumn_days < 10 and winter_days < 10):
        category = '时令型蔬菜'
        catecount[1] += 1
    else:
        category = '季节型蔬菜'
        catecount[2] += 2

    results.append({
        '单品编码': item,
        '销售量非0的总天数': total_nonzero_days,
        '每年平均销售量非0天数': yearly_avg_nonzero_days,
        '每个季节平均销售量': seasonal_mean,
        '春季销售量非0的天数': spring_days,
        '夏季销售量非0的天数': summer_days,
        '秋季销售量非0的天数': autumn_days,
        '冬季销售量非0的天数': winter_days,
        '蔬菜类型': category
    })



results_df = pd.DataFrame(results).fillna(0)
print(catecount)

# 保存处理后的数据
output_files = 's蔬菜类型.csv'

try:
    results_df.to_csv(output_files, index=True)
    print(f'数据已保存到 {output_files}')
except Exception as e:
    print(f"保存时发生错误: {e}")