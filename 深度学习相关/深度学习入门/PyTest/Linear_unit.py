from Perceptron import Perceptron
import matplotlib.pyplot as plt


class LinearUnit(Perceptron):
    def __init__(self, input_num):
        """初始化线性单元，设置输入参数的个数，并修改激活函数为 f(x) = x"""
        Perceptron.__init__(self, input_num, lambda x: x)

def get_training_dataset():
    """捏造5个人的收入数据"""
    # 构建训练数据
    # 输入向量列表，每一项是工作年限
    input_vecs = [[5], [3], [8], [1.4], [10.1]]
    # 期望的输出列表，月薪，注意要与输入一一对应
    labels = [5500, 2300, 7600, 1800, 11400]
    return input_vecs, labels

def train_linear_unit():
    """使用数据训练线性单元"""
    # 创建感知器，输入参数的特征数为1（工作年限）
    lu = LinearUnit(1)
    # 训练，迭代10轮, 学习速率为0.01
    input_vecs, labels = get_training_dataset()
    lu.train(input_vecs, labels, 10, 0.01)
    # 返回训练好的线性单元
    return lu

def plot(linear_unit):
    """最终结果可视化"""
    input_vecs, labels = get_training_dataset()
    fig = plt.figure()
    ax = fig.add_subplot(111)
    ax.scatter(list(map(lambda x: x[0], input_vecs)), labels)
    weights = linear_unit.weights
    bias = linear_unit.bias
    x = range(0, 12, 1)
    y = list(map(lambda x: weights[0] * x + bias, x))
    ax.plot(x, y)
    plt.show()

if __name__ == '__main__':
    '''训练线性单元'''
    linear_unit = train_linear_unit()
    # 打印训练获得的权重
    print(linear_unit)
    # 测试
    print('Work 3.4 years, monthly salary = %.2f' % linear_unit.predict([3.4]))
    print('Work 15 years, monthly salary = %.2f' % linear_unit.predict([15]))
    print('Work 1.5 years, monthly salary = %.2f' % linear_unit.predict([1.5]))
    print('Work 6.3 years, monthly salary = %.2f' % linear_unit.predict([6.3]))
    plot(linear_unit)
