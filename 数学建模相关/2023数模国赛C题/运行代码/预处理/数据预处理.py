import os
import pandas as pd

pd.set_option('future.no_silent_downcasting', True)  # 避免数据类型转换的警告

# 读取数据
path_in = "../基础数据/"
df_1 = pd.read_excel(path_in+'附件1.xlsx')
df_2 = pd.read_excel(path_in+'附件2.xlsx')
df_3 = pd.read_excel(path_in+'附件3.xlsx')
df_4 = pd.read_excel(path_in+'附件4_新.xlsx')

print(df_3.columns[1])
print(df_4.columns[2])



# 缺失值和重复值处理函数
def check(df, name):
    rows_with_missing_values = df[df.isna().any(axis=1)]
    duplicates = df[df.duplicated(keep=False)]

    if not rows_with_missing_values.empty:
        print(name, " 中包含缺失值的行：")
        print(rows_with_missing_values, "\n")
    else:
        print(name, " 该数据无缺失值", "\n")

    if not duplicates.empty:
        print(name, " 中包含重复的行：")
        print(duplicates, "\n")
    else:
        print(name, "该数据无重复", "\n")


# 清除缺失值和重复值
def drop(df):
    df = df.drop_duplicates()
    df = df.dropna()
    return df


# 日期检测函数
def datecheck(df, col, name):
    df.set_index(col, inplace=True)  # 设定日期列为索引
    full_date_range = pd.date_range(start=df.index.min(), end=df.index.max(), freq='D')  # 生成完整的日期范围
    missing_dates = full_date_range.difference(df.index)  # 找出缺失的日期

    if not missing_dates.empty:
        print(name, " 缺失的日期:")
        print(missing_dates)
    else:
        print(name, " 日期连续", "\n")


# 处理数据
# 附件 1
check(df_1, "附件1")
df_1['分类编码'] = df_1['分类编码'].astype(str)  # 确保分类编码是字符串类型
df_1['单品编码'] = df_1['单品编码'].astype(str)

# 附件 2
check(df_2, "附件2")
# df_2['销售日期'] = pd.to_datetime(df_2['销售日期'], errors='coerce')  # 转换为日期类型
# df_2['扫码销售时间'] = pd.to_datetime(df_2['扫码销售时间'].astype(str), format='%H:%M:%S', errors='coerce')  # 转换为时间类型
df_2['销量(千克)'] = df_2['销量(千克)'].astype(float)  # 确保类型准确
df_2['销售单价(元/千克)'] = df_2['销售单价(元/千克)'].astype(float)  # 确保类型准确
df_2['单品编码'] = df_2['单品编码'].astype(str)  # 确保类型准确
df_2['是否打折销售'] = df_2['是否打折销售'].replace({'是': 1, '否': 0})  # 打折类型转换为数字
df_2['销售类型'] = df_2['销售类型'].replace({'销售': 1, '退货': 0})  # 销售类型转换为数字

# 附件 3
check(df_3, "附件3")
# df_3[df_3.columns[0]] = pd.to_datetime(df_3[df_3.columns[0]], errors='coerce')  # 转换为日期类型
df_3[df_3.columns[1]] = df_3[df_3.columns[1]].astype(str)  # 确保编号类型准确
df_3[df_3.columns[2]] = df_3[df_3.columns[2]].astype(float)  # 确保批发价格类型准确

# 附件 4
check(df_4, "附件4_新")
df_4[df_4.columns[0]] = df_4[df_4.columns[0]].astype(str)  # 确保编号类型准确
df_4[df_4.columns[2]] = df_4[df_4.columns[2]].astype(float)  # 确保损耗率类型准确

# 日期检查
datecheck(df_2, "销售日期", "附件 2")
datecheck(df_3, "日期", "附件 3")

# 保存处理后的数据
output_files = {
    'df_1': 'cleaned_df1.csv',
    'df_2': 'cleaned_df2.csv',
    'df_3': 'cleaned_df3.csv',
    'df_4': 'cleaned_df4.csv'
}

for df_name, path in output_files.items():
    df = globals()[df_name]  # 使用 globals() 获取 DataFrame
    try:
        df.to_csv(path, index=True)
        print(f'{df_name} 数据已保存到 {path}')
    except Exception as e:
        print(f"保存 {df_name} 时发生错误: {e}")
