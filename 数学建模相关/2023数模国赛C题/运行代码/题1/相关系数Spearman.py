import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from matplotlib.font_manager import FontProperties
from scipy import stats
import numpy as np

# 设置字体
font = FontProperties(fname='C:/Windows/Fonts/SimHei.ttf')  # Windows系统路径
plt.rcParams['font.sans-serif'] = ['SimHei']  # 用来正常显示中文标签
plt.rcParams['axes.unicode_minus'] = False  # 用来正常显示负号 # 有中文出现的情况，需要u'内容'

# 步骤 1: 导入数据并预处理
df = pd.read_csv('./sales_day_multi_2.csv', encoding='gbk')
df = df.drop(columns=['销售日期'])

# 步骤 2: 计算Spearman相关系数矩阵
spearman_corr = df.corr(method='spearman')

# 计算p-值矩阵
def spearman_corr_p_value(x, y):
    _, p_val = stats.spearmanr(x, y)
    return p_val

# 创建p-值矩阵
p_values = pd.DataFrame(index=df.columns, columns=df.columns)

for i in df.columns:
    for j in df.columns:
        if i != j:
            p_values.loc[i, j] = spearman_corr_p_value(df[i], df[j])
        else:
            p_values.loc[i, j] = np.nan  # 自身的p值设为NaN

# 输出相关系数矩阵和p-值矩阵
spearman_corr.to_csv("./spearman_corr.csv")
p_values.to_csv("./spearman_p_values.csv")

# 步骤 3: 可视化Spearman相关系数矩阵
plt.figure(figsize=(10, 8))
sns.heatmap(spearman_corr, annot=True, cmap='coolwarm', fmt=".2f")
plt.title("Spearman Correlation Matrix")
plt.savefig("./图片/spearman_corr_heatmap.png")
plt.show()

# 可视化p-值矩阵
plt.figure(figsize=(10, 8))
sns.heatmap(p_values.astype(float), annot=True, cmap='coolwarm', fmt=".2f", mask=p_values.isna())
plt.title("Spearman Correlation p-Values Matrix")
plt.savefig("./图片/spearman_p_values_heatmap.png")
plt.show()
