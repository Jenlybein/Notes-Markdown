import pandas as pd
from mlxtend.preprocessing import TransactionEncoder
from mlxtend.frequent_patterns import fpgrowth, association_rules

# 读取CSV文件
df = pd.read_csv('./sales_day_single.csv')

# 删除销售日期列，因为我们只关心单品的出现情况
df = df.drop(columns=['销售日期'])

# 转换数据为事务列表
transactions = df.apply(lambda row: [item for item in row.index if row[item] > 0], axis=1).tolist()


# 将事务数据转换为适合FP-growth的格式
te = TransactionEncoder()
te_ary = te.fit(transactions).transform(transactions)
df_encoded = pd.DataFrame(te_ary, columns=te.columns_)

# 执行FP-growth算法，设置最小支持度阈值为0.5
frequent_itemsets = fpgrowth(df_encoded, min_support=0.5, use_colnames=True)

# 将频繁项集输出到CSV
frequent_itemsets.to_csv('FP-Growth_frequent_itemsets.csv', index=False)

# 生成关联规则，设置最小置信度阈值为0.7
rules = association_rules(frequent_itemsets, metric="confidence", min_threshold=0.7)
# 将关联规则输出到CSV
rules.to_csv('FP-Growth_association_rules.csv', index=False)