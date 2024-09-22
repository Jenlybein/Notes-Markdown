import pandas as pd
from scipy.stats import pearsonr
import seaborn as sns
import matplotlib.pyplot as plt
from matplotlib.font_manager import FontProperties


# 读取数据
df = pd.read_csv("./cost_plus_pricing.csv")

# 数据分组
grouped = df.groupby('分类名称')

# 存储结果
results = []

# 计算皮尔逊相关系数
for name, group in grouped:
    # 计算销量与成本加成之间的皮尔逊相关系数
    corr, p_value = pearsonr(group['销量(千克)'], group['成本加成比例'])

    # 将结果添加到结果列表中
    results.append({'分类名称': name, '相关系数': corr, 'p值': p_value})

# 将结果列表转换为DataFrame
results_df = pd.DataFrame(results)

# 显示结果
results_df.to_csv("Pearson_corr_profit.csv", index=False)

# 可视化相关性
sns.set(style="whitegrid")

# 生成各分类相关系数的柱状图
# 设置字体
font = FontProperties(fname='C:/Windows/Fonts/SimHei.ttf')  # Windows系统路径
plt.rcParams['font.sans-serif'] = ['SimHei']  # 用来正常显示中文标签
plt.rcParams['axes.unicode_minus'] = False  # 用来正常显示负号 # 有中文出现的情况，需要u'内容'

plt.figure(figsize=(10, 6))
sns.barplot(x='分类名称', y='相关系数', data=results_df)
plt.title('各分类销量与成本加成的皮尔逊相关系数')
plt.xlabel('分类名称')
plt.ylabel('相关系数')
plt.xticks(rotation=0)
plt.show()
