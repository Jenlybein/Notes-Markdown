# Vue-flow

**基于 Vue 3 的图形编辑库**

**VueFlow** 是一个基于 Vue 3 的图形编辑库，专门用于创建和操作流程图、图表和其他可视化数据结构。它以其丰富的功能和灵活的配置选项而受到开发者的青睐，适用于需要构建复杂图形界面应用的各种场景。

---

## 安装

```bash
npm add @vue-flow/core				# 核心

npm install @vue-flow/background	 # 背景
npm install @vue-flow/minimap		 # 小地图
npm install @vue-flow/controls		 # 自带的缩放、居中、加锁功能
npm install @vue-flow/node-toolbar    # 工具栏
npm install @vue-flow/node-resizer    # 缩放
```

打开 `main.ts` 导入全局样式

```ts
// 导入 vue-flow 全局样式
import "@vue-flow/core/dist/style.css";
import "@vue-flow/core/dist/theme-default.css";
import '@vue-flow/controls/dist/style.css';
import '@vue-flow/minimap/dist/style.css';
```



## 基本使用

### 加载工具

- 加载基本组件： `Background 、Controls、MiniMap `，分别是背景、左下缩放工具栏、右下地图，这些位置都是默认的。
- 要在画布上显示的内容，都要放在` <VueFlow>`标签内

```vue
<template>
    <div>
        <VueFlow>
            <Background />
            <Controls />
            <MiniMap />
        </VueFlow>
    </div>
</template>

<script setup lang="ts">
import { VueFlow, Position, Panel, useVueFlow, MarkerType } from '@vue-flow/core'
import { Background } from '@vue-flow/background'
import { Controls } from '@vue-flow/controls'
import { MiniMap } from '@vue-flow/minimap'
</script>
```

### 加载节点和边，绘图

- `nodes` 绑定节点；`edges`绑定边
- `nodes` 中节点属性必须有 id 唯一标识，`position`记录位置
- `edges` 中边的属性必须有id唯一标识，source和target分别记录边的源节点和目的节点
- `fit-view-on-init` 默认图居中
- `default-marker-color` 修改边的节点颜色

```vue
<template>
    <div class="container">
        <VueFlow :nodes="chatNodes" :edges="chatEdges" fit-view-on-init default-marker-color="#409EFF"
            class="flowchat-container">
            <!-- 加载的工具 -->
        </VueFlow>
    </div>
</template>

<script setup lang="ts">
import { VueFlow, Position, Panel, useVueFlow, MarkerType } from '@vue-flow/core'
import { Background } from '@vue-flow/background'
import { Controls } from '@vue-flow/controls'
import { MiniMap } from '@vue-flow/minimap'
import { ref } from 'vue';

//节点
const chatNodes = ref([
    {
        id: '1',
        position: { x: 50, y: 50 },
        data: { label: 'Node 1', },
    },
    {
        id: '2',
        position: { x: 150, y: 50 },
        data: { label: 'Node 2', },
    }
])
//边
const chatEdges = ref([
    {
        id: 'e1-2',
        source: '1',
        target: '2',
        type: 'default',
        markerEnd: MarkerType.Arrow,
    }
])
</script>

<style scoped>
.container {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 100%;
}

.flowchat-container {
    width: 100%;
    height: calc(100% - 50px);
    position: absolute;
    top: 50px;
    padding: 20px 0;
}
</style>
```

### 节点的增删

对于节点的增加和删除，我们可以通过直接改变 nodes 参数来实现，也可以使用 [useVueFlow](https://link.juejin.cn/?target=https%3A%2F%2Fvueflow.dev%2Ftypedocs%2Ffunctions%2FuseVueFlow.html) 提供的方法[`addNodes`](https://link.juejin.cn/?target=https%3A%2F%2Fvueflow.dev%2Ftypedocs%2Finterfaces%2FActions.html%23addnodes) 和[`removeNodes`](https://link.juejin.cn/?target=https%3A%2F%2Fvueflow.dev%2Ftypedocs%2Finterfaces%2FActions.html%23removeNodes)直接改变组件内部的状态实现。

[vue-flow绘制流程图-CSDN博客](https://blog.csdn.net/m0_50666077/article/details/140437266)
