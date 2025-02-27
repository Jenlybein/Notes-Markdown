import random
import numpy as np

def sigmoid(x):
    """Sigmoid 激活函数"""
    # x = np.clip(x, -100, 100) # 限制 x 的范围，避免溢出
    return 1 / (1 + np.exp(-x))

class Node:
    def __init__(self, layer_index: int, node_index: int):
        """ 构造节点对象
        layer_index: 节点所属的层的编号
        node_index: 节点的编号
        """
        self.layer_index = layer_index
        self.node_index = node_index
        self.downstream = []
        self.upstream = []
        self.output = 0.0
        self.delta = 0.0

    def set_output(self, output: float):
        """设置节点的输出值。如果节点属于输入层会用到这个函数。"""
        self.output = output

    def append_downstream_connection(self, conn):
        """添加一个到下游节点的连接"""
        self.downstream.append(conn)

    def append_upstream_connection(self, conn):
        """添加一个到上游节点的连接"""
        self.upstream.append(conn)

    def calc_output(self):
        """根据公式(3.1)，用sigmoid计算节点的输出"""
        total_input = sum([conn.upstream_node.output * conn.weight for conn in self.upstream])
        self.output = sigmoid(total_input)

    def calc_hidden_layer_delta(self):
        """根据公式(3.7)计算隐藏层节点的误差"""
        downstream_delta = sum([conn.downstream_node.delta * conn.weight for conn in self.downstream])
        self.delta = self.output * (1 - self.output) * downstream_delta

    def calc_output_layer_delta(self, label: float):
        """根据公式(3.6)计算输出层层节点的误差"""
        self.delta = self.output * (1 - self.output) * (label - self.output)

    def __str__(self):
        """打印节点的信息"""
        node_str = f"{self.layer_index}-{self.node_index}: output: {self.output:.6f} delta: {self.delta:.6f}"
        downstream_str = "\n\t".join(str(conn) for conn in self.downstream)
        upstream_str = "\n\t".join(str(conn) for conn in self.upstream)
        return f"{node_str}\n\tdownstream:\n\t{downstream_str}\n\tupstream:\n\t{upstream_str}"


class Layer:
    def __init__(self, layer_index: int, node_count: int):
        """ 初始化一层
        layer_index: 层编号
        node_count: 层所包含的节点个数
        """
        self.layer_index = layer_index
        # 创建普通节点
        self.nodes = [Node(layer_index, i) for i in range(node_count)]
        # 额外添加一个常数节点（偏置节点）
        self.nodes.append(Node(layer_index, node_count))

    def set_output(self, data):
        """设置层的输出。当层是输入层时会用到。"""
        for i, value in enumerate(data):
            self.nodes[i].set_output(value)

    def calc_output(self):
        """计算层的输出向量"""
        for node in self.nodes[:-1]:  # 除去常数节点
            node.calc_output()
        self.nodes[-1].output = 1.0  # 偏置节点输出值为1

    def dump(self):
        """打印层的信息"""
        for node in self.nodes:
            print(node)


class Connection:
    def __init__(self, upstream_node, downstream_node):
        """
        初始化连接，权重初始化为一个很小的随机数
        upstream_node: 连接的上游节点
        downstream_node: 连接的下游节点
        """
        self.upstream_node = upstream_node
        self.downstream_node = downstream_node
        self.weight = random.uniform(-0.1, 0.1)  # 生成 -0.1 到 0.1 之间的随机浮点数
        self.gradient = 0.0  # 初始化梯度为 0.0

    def calc_gradient(self):
        """计算梯度"""
        self.gradient = self.downstream_node.delta * self.upstream_node.output

    def get_gradient(self):
        """获取当前的梯度"""
        return self.gradient

    def update_weight(self, rate: float):
        """ 根据梯度下降算法更新权重
        rate: 学习率
        """
        self.calc_gradient()
        self.weight += rate * self.gradient  # 使用梯度下降更新权重

    def __str__(self):
        """打印连接信息"""
        return (f"({self.upstream_node.layer_index}-{self.upstream_node.node_index}) -> "
                f"({self.downstream_node.layer_index}-{self.downstream_node.node_index}) = {self.weight:.6f}")


class Connections(object):
    def __init__(self):
        self.connections = []

    def add_connection(self, connection):
        self.connections.append(connection)

    def dump(self):
        for conn in self.connections:
            print(conn)


class Network:
    def __init__(self, layers):
        """ 初始化一个全连接神经网络
        layers: 一维数组，描述神经网络每层节点数
        """
        self.connections = Connections()  # 连接集合
        self.layers = []

        # 创建各层神经网络
        for i, num_nodes in enumerate(layers):
            self.layers.append(Layer(i, num_nodes))

        # 连接每层节点，构建全连接网络（排除最后一层，输出层不用连接下一层）
        for layer in range(len(layers) - 1):
            connections = [
                Connection(upstream_node, downstream_node)
                for upstream_node in self.layers[layer].nodes
                for downstream_node in self.layers[layer + 1].nodes[:-1]  # 跳过偏置节点
            ]
            for conn in connections:
                self.connections.add_connection(conn)
                conn.downstream_node.append_upstream_connection(conn)
                conn.upstream_node.append_downstream_connection(conn)

    def train(self, labels, data_set, rate, iteration):
        """ 训练神经网络
        labels: 一维数组，训练样本标签，每个元素是一个样本的标签
        data_set: 二维数组，训练样本特征，每个元素是一个样本的特征
        rate: 学习率
        iteration: 迭代次数
        """
        for _ in range(iteration):
            for label, sample in zip(labels, data_set):
                self.train_one_sample(label, sample, rate)

    def train_one_sample(self, label, sample, rate):
        """ 用一个样本训练网络
        label: 样本标签
        sample: 样本输入
        rate: 学习率
        """
        self.predict(sample)  # 前向传播
        self.calc_delta(label)  # 计算误差
        self.update_weight(rate)  # 更新权重

    def calc_delta(self, label):
        """ 计算每个节点的误差项 delta
        label: 样本标签
        """
        output_nodes = self.layers[-1].nodes  # 输出层节点
        for i, lbl in enumerate(label):
            output_nodes[i].calc_output_layer_delta(lbl)

        # 从倒数第二层向前计算隐藏层的 delta
        for layer in reversed(self.layers[:-1]):
            for node in layer.nodes:
                node.calc_hidden_layer_delta()

    def update_weight(self, rate):
        """ 更新每个连接的权重
        rate: 学习率
        """
        for layer in self.layers[:-1]:  # 不更新输出层
            for node in layer.nodes:
                for conn in node.downstream:
                    conn.update_weight(rate)

    def calc_gradient(self):
        """计算每个连接的梯度"""
        for layer in self.layers[:-1]:  # 不计算输出层
            for node in layer.nodes:
                for conn in node.downstream:
                    conn.calc_gradient()

    def get_gradient(self, label, sample):
        """ 获得网络在一个样本下，每个连接上的梯度
        label: 样本标签
        sample: 样本输入
        """
        self.predict(sample)
        self.calc_delta(label)
        self.calc_gradient()

    def predict(self, sample):
        """ 根据输入的样本预测输出值
        sample: 一维数组，样本的特征，即网络的输入向量
        """
        self.layers[0].set_output(sample)  # 设置输入层的值
        for layer in self.layers[1:]:  # 计算各层输出
            layer.calc_output()
        return [node.output for node in self.layers[-1].nodes[:-1]]  # 返回最终输出，去除偏置节点

    def dump(self):
        """打印网络信息"""
        for layer in self.layers:
            layer.dump()


def gradient_check(network, sample_feature, sample_label):
    """ 梯度检查
    network: 神经网络对象
    sample_feature: 样本的特征
    sample_label: 样本的标签
    """

    # 计算网络误差
    def network_error(vec1, vec2):
        """计算网络误差: 误差 = 0.5 * Σ (预测值 - 真实值)^2"""
        return 0.5 * sum([(v1 - v2) ** 2 for v1, v2 in zip(vec1, vec2)])

    # 获取网络在当前样本下每个连接的梯度
    network.get_gradient(sample_feature, sample_label)

    # 对每个权重做梯度检查
    for conn in network.connections.connections:
        # 获取指定连接的梯度
        actual_gradient = conn.get_gradient()

        # 增加一个很小的值，计算网络的误差
        epsilon = 0.0001
        conn.weight += epsilon
        error1 = network_error(network.predict(sample_feature), sample_label)

        # 减去一个很小的值，计算网络的误差
        conn.weight -= 2 * epsilon  # 刚才加过了一次，因此这里需要减去2倍
        error2 = network_error(network.predict(sample_feature), sample_label)

        # 根据公式计算期望的梯度值
        expected_gradient = (error2 - error1) / (2 * epsilon)

        # 打印
        print(f"expected gradient: \t{expected_gradient:.6f}\n"
              f"actual gradient: \t{actual_gradient:.6f}")
