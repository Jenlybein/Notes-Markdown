class Perceptron(object):
    def __init__(self, input_num, activator):
        """
        初始化感知机，设置输入参数的个数和激活函数
        """
        self.activator = activator
        self.weights = [0.0 for _ in range(input_num)] # 权重向量初始化为0
        self.bias = 0.0 # 偏置项初始化为0

    def __str__(self):
        """
        打印学习到的权重、偏置项
        """
        return 'weights\t:%s\nbias\t:%f\n' % (self.weights, self.bias)

    def predict(self, input_vec):
        """
        输入向量，输出感知器的计算结果
        """
        # 使用zip将input_vec和weights打包在一起，计算加权和
        weighted_sum = sum(x * w for x, w in zip(input_vec, self.weights)) + self.bias
        return self.activator(weighted_sum)

    def train(self, input_vecs, labels, iteration, rate):
        """
        输入训练数据：一组向量、与每个向量对应的label；以及训练轮数、学习率
        """
        for i in range(iteration):
            self._one_iteration(input_vecs, labels, rate)

    def _one_iteration(self, input_vecs, labels, rate):
        """
        一次迭代，把所有的训练数据过一遍
        """
        # 对每个样本，按照感知器规则更新权重
        for input_vec, label in zip(input_vecs, labels):
            output = self.predict(input_vec) # 计算感知器在当前权重下的输出
            self._update_weights(input_vec, output, label, rate) # 更新权重

    def _update_weights(self, input_vec, output, label, rate):
        """
        按照感知器规则更新权重
        """
        delta = label - output # 计算误差
        self.weights = [w + rate * delta * x for x, w in zip(input_vec, self.weights)] # 更新权重
        self.bias += rate * delta # 更新偏置项

def f(x):
    """
    定义激活函数f
    """
    return 1 if x > 0 else 0

def get_training_dataset():
    """
    基于and真值表构建训练数据
    """
    # 构建训练数据
    # 输入向量列表
    input_vecs = [[1, 1], [0, 0], [1, 0], [0, 1]]
    # 期望的输出列表，注意要与输入一一对应
    # [1,1] -> 1, [0,0] -> 0, [1,0] -> 0, [0,1] -> 0
    labels = [1, 0, 0, 0]
    return input_vecs, labels

def train_and_perceptron():
    """
    使用and真值表训练感知器
    """
    # 创建感知器，输入参数个数为2（因为and是二元函数），激活函数为f
    p = Perceptron(2, f)
    # 训练，迭代10轮, 学习速率为0.1
    input_vecs, labels = get_training_dataset()
    p.train(input_vecs, labels, 10, 0.1)
    # 返回训练好的感知器
    return p

if __name__ == '__main__':
    # 训练and感知器
    and_perception = train_and_perceptron()
    # 打印训练获得的权重
    print(and_perception)
    # 测试
    print('1 and 1 =', and_perception.predict([1, 1]))
    print('0 and 0 =', and_perception.predict([0, 0]))
    print('1 and 0 =', and_perception.predict([1, 0]))
    print('0 and 1 =', and_perception.predict([0, 1]))