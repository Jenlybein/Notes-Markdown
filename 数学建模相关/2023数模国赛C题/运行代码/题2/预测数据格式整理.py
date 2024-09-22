import pandas as pd

# 读取CSV文件
df = pd.read_csv('SARIMA_all_forecasts.csv')

# 重塑数据，使每个日期、分类名称下的所有预测值都在同一行
# 创建一个空的DataFrame来存储重塑后的数据
reshaped_df = pd.DataFrame(columns=['日期', '分类名称', '预测值_平均销量(千克)', '预测值_平均批发价格(元/千克)', '预测值_平均销售单价(元/千克)'])

# 获取唯一的日期和分类名称
dates = df['日期'].unique()
categories = df['分类名称'].unique()

# 遍历日期和分类名称，将数据整理到一个新行中
rows_list = []
for date in dates:
    for category in categories:
        # 筛选当前日期和分类的数据
        temp_df = df[(df['日期'] == date) & (df['分类名称'] == category)]
        if not temp_df.empty:
            # 提取预测值
            avg_sales = temp_df['预测值_平均销量(千克)'].dropna().values
            avg_wholesale_price = temp_df['预测值_平均批发价格(元/千克)'].dropna().values
            avg_retail_price = temp_df['预测值_平均销售单价(元/千克)'].dropna().values

            # 创建一行新的数据
            new_row = {
                '日期': date,
                '分类名称': category,
                '预测值_平均销量(千克)': avg_sales[0] if len(avg_sales) > 0 else None,
                '预测值_平均批发价格(元/千克)': avg_wholesale_price[0] if len(avg_wholesale_price) > 0 else None,
                '预测值_平均销售单价(元/千克)': avg_retail_price[0] if len(avg_retail_price) > 0 else None
            }

            # 将新行添加到列表中
            rows_list.append(new_row)

# 将列表转换为DataFrame
reshaped_df = pd.DataFrame(rows_list)

# 保存重塑后的数据到新的CSV文件
reshaped_df.to_csv('SARIMA_reshaped_data.csv', index=False)