import pandas as pd
import matplotlib.pyplot as plt
from matplotlib.font_manager import FontProperties

# 设置字体
font = FontProperties(fname='C:/Windows/Fonts/SimHei.ttf')  # Windows系统路径
plt.rcParams['font.sans-serif'] = ['SimHei']  # 用来正常显示中文标签
plt.rcParams['axes.unicode_minus'] = False  # 用来正常显示负号 # 有中文出现的情况，需要u'内容'

# 读取CSV数据
data = pd.read_csv('./sales_day_multi_2.csv', parse_dates=['销售日期'], dayfirst=True,encoding='gbk')

# 将销售日期设置为索引
data.set_index('销售日期', inplace=True)

# 创建一个图形，包含两个子图（左右排列）
fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(16, 6), sharex=True)

# 绘制折线图
for column in data.columns:
    ax1.plot(data.index, data[column], label=column)

ax1.set_title('各类蔬菜销售量折线图', fontproperties=font)
ax1.set_xlabel('销售日期', fontproperties=font)
ax1.set_ylabel('销售量', fontproperties=font)
ax1.legend()
ax1.grid(True)

# 绘制趋势图（使用移动平均来显示趋势）
window_size = 20  # 增大窗口大小以平滑趋势
data_rolling = data.rolling(window=window_size).mean()

for column in data.columns:
    ax2.plot(data.index, data_rolling[column], label=f'{column} 移动平均', linestyle='-')  # 使用实线

ax2.set_title('各类蔬菜销售量趋势图（移动平均）', fontproperties=font)
ax2.set_xlabel('销售日期', fontproperties=font)
ax2.set_ylabel('销售量', fontproperties=font)
ax2.legend()
ax2.grid(True)

# 调整布局以防止重叠
plt.tight_layout()
plt.show()
