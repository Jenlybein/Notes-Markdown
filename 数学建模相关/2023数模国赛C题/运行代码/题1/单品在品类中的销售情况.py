import pandas as pd
import matplotlib.pyplot as plt
from matplotlib.font_manager import FontProperties

# 设置字体
font = FontProperties(fname='C:/Windows/Fonts/SimHei.ttf')  # Windows系统路径
plt.rcParams['font.sans-serif'] = ['SimHei']  # 用来正常显示中文标签
plt.rcParams['axes.unicode_minus'] = False  # 用来正常显示负号

# 读取CSV数据
data = pd.read_csv('../数据拼合/sales_total_multi.csv')

# 获取所有品类
categories = data['分类名称'].unique()

# 绘制每个品类的图
for category in categories:
    # 筛选出当前品类且销量不为0的数据
    category_data = data[(data['分类名称'] == category) & (data['销量(千克)'] > 0)]

    # 如果过滤后的数据为空，则跳过
    if category_data.empty:
        continue

    # 按销量降序排列，并取前30条数据
    category_data = category_data.sort_values(by='销量(千克)', ascending=False).head(20)

    # 根据单品数量调整图像宽度和高度
    num_items = len(category_data)
    fig_width = max(10, num_items * 0.5)  # 设置图像宽度，最小宽度为10
    fig_height = max(6, num_items * 0.4)  # 设置图像高度，最小高度为6

    # 创建柱状图
    plt.figure(figsize=(fig_width, fig_height))
    plt.bar(category_data['单品名称'], category_data['销量(千克)'])
    plt.xlabel('单品名称', fontproperties=font)
    plt.ylabel('销量(千克)', fontproperties=font)
    plt.title(f'{category} 品类中的单品销量', fontproperties=font)
    plt.xticks(rotation=90)  # 使x轴标签更易读
    plt.tight_layout()  # 自动调整布局以防标签被遮挡

    # 显示图像
    plt.show()  # 显示图像
