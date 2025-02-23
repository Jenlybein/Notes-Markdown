from sklearn.linear_model import Perceptron
import numpy as np

# 构建训练数据：输入特征和对应的标签
X = np.array([[1, 1], [0, 0], [1, 0], [0, 1]])  # 输入特征
y = np.array([1, 0, 0, 0])  # 标签

# 创建感知机模型，设置最大迭代次数和学习率
model = Perceptron(max_iter=10, eta0=0.1)

# 训练模型
model.fit(X, y)

# 打印训练后的权重和偏置
print(f'权重: {model.coef_}')
print(f'偏置: {model.intercept_}')

# 测试模型
test_samples = np.array([[1, 1], [0, 0], [1, 0], [0, 1]])
predictions = model.predict(test_samples)
print(f'预测结果: {predictions}')
