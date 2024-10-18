

[TOC]
------

<center><font size=7><b> Vue基础——个人笔记 </center></font></center>

---



# Vue

## 简介

**vue2官网**：[Vue.js (vuejs.org)](https://v2.cn.vuejs.org/)

**vue3官网**：[Vue.js - 渐进式 JavaScript 框架 | Vue.js (vuejs.org)](https://cn.vuejs.org/)

- **教程**
  - [开始使用 Vue - 学习 Web 开发 | MDN (mozilla.org)](https://developer.mozilla.org/zh-CN/docs/Learn/Tools_and_testing/Client-side_JavaScript_frameworks/Vue_getting_started)
  - [Vue3 教程 | 菜鸟教程 (runoob.com)](https://www.runoob.com/vue3/vue3-tutorial.html)

`Vue` (发音为 /vjuː/，类似 **view**) 是一款用于`构建用户界面`的 JavaScript `渐进式` `框架`。它基于标准 HTML、CSS 和 JavaScript 构建，并提供了一套声明式的、组件化的编程模型，帮助你高效地开发用户界面。无论是简单还是复杂的界面，Vue 都可以胜任。

Vue 免除原生JavaScript中的DOM操作，简化书写，提高编程效率。基于MVVM(Model-View-ViewModel)思想，实现数据的双向绑定，将编程的关注点放在数据上。

Vue.js 的核心特点包括`响应式数据绑定`、`组件化开发`、`虚拟DOM`、`双向数据绑定`、`插件扩展能力`以及`简洁易学`的特点。

![1722245988373 (1)](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202407291832720.jpg)

> 构建用户界面：基于数据动态渲染页面
>
> 渐进式：循序渐进的学习。可以一部分一部分学习，由内到外，由小到大，不用全部学习之后才能使用
>
> 框架：是一个完整的项目解决方案，又是一个半成品软件，是一套可重用的、通用的、软件基础代码模型。基于框架进行开发，更加快捷、更加高效。

**Vue的使用方式与对应场景**

1. Vue核心包开发：局部模块改造
2. Vue核心包 & Vue 插件 工程化：整站开发

**Vue的特点**

- 对开发者：
  - 优点：大大提升开发效率
  - 缺点：需要理解记忆规则

## Vue开发者工具

- 对应的Vue开发插件：
  - 谷歌 Chrome 浏览器 [Vue.js Devtools_6.6.3_Chrome插件下载_极简插件 (zzzmh.cn)](https://chrome.zzzmh.cn/info/nhdogjmejiglipccpnnnanhbledajbpd)
  - Edge 浏览器 [Vue.js devtools - Microsoft Edge Addons](https://microsoftedge.microsoft.com/addons/detail/vuejs-devtools/olofadcdnkkjdfgjcmjaadnlehnnihnl?hl=zh-CN)

安装成功后，点击进入Vue.js devtools插件的详细管理页面 [谷歌：详细信息]/[Edge：管理拓展] ，勾选**允许访问文件URL/网址**.

重启浏览器后，按F12，更多工具中找到Vue选项，即可开始使用Vue的开发者工具。

> 注意，需要Vue运行的页面才能使用该调试工具



---

# Vue2 速通

Vue2当前已经停止更新，学习Vue2有利于老项目的兼容与更方便的学习Vue3

## 创建Vue实例

### 步骤

1.  准备容器
2.  引包（开发版本包 / 生产版本包）
3.  创建实例
4.  添加配置项  —》 完成渲染

#### 1.准备容器

在html中，创立Vue所管理的区域

```html
<div id="app">
    <!-- 将来编写一些用来渲染的代码 -->
</div>
```

#### 2.引包

查看官网，了解安装流程，同时获取安装文件：[安装 — Vue.js (vuejs.org)](https://v2.cn.vuejs.org/v2/guide/installation.html)

**用命令行工具(CLI)**

见官方文档，新手不建议使用

**用 \<script> 引入**

![image-20240730002808435](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202407300028500.png)

下载你需要的版本，然后在 .html 文件相同目录下创建名为vue的文件夹，放入vue.js。

在html文件中输入

```html
<script src="vue/vue.js" type="text/javascript" charset="utf-8"></script>
```

如果只是用来学习，则

```html	
<script src="https://cdn.jsdelivr.net/npm/vue@2.7.14/dist/vue.js"></script>
```

#### 3.创建实例

在JS代码区域，创建Vue核心对象，定义数据模型

```html
<!-- 引入VueJS核心包后，在全局环境存在Vue构造函数 -->
<script>
    const app = new Vue({
        <!-- .... -->
    })
</script>
```

#### 4.添加配置项

```vue
<div id="app">
    <!-- 编写一些用来渲染的代码 -->
    {{ msg }}
    <!-- 还可以在此处 使用函数、访问元素、拼接字符串 等表达式  -->
    {{ msg.toUpperCase() }}
    {{ msg + " ,World" }}
    <!-- 但不支持 if for 这类的语句：{{ if }} 出现则会出错 -->
</div>

<script src="vue/vue.js" type="text/javascript" charset="utf-8"></script>
<script>
    const app = new Vue({
        // 通过 el 配置选择器，指定Vue管理的是哪个盒子
        el: '#app',
        // 通过 data 提供数据
        data:{
            msg: 'Hello'
            friend
        }
    })
</script>
```



## Vue指令

| **常用指令**            | **作用**                                                    |
| ----------------------- | ----------------------------------------------------------- |
| v-html                  | 将数据以html标签的形式插入网页                              |
| v-bind                  | 为HTML标签绑定属性值，如设置 href , css样式等，可省略为 `:` |
| v-model                 | 在表单元素上创建双向数据绑定                                |
| v-on                    | 为HTML标签绑定事件，可省略为 `@`                            |
| v-if、v-else-if、v-else | 条件性的渲染某元素，判定为true时渲染,否则不渲染             |
| v-show                  | 根据条件展示某元素，区别在于切换的是display属性的值         |
| v-for                   | 列表渲染，遍历容器的元素或者对象的属性                      |



**v-show**

1.**作用**：控制元素显示隐藏
2.**表达式值**： true 显示，false 隐藏
3.**原理**：切换 `display : none` 控制显示隐藏
4.**场景**：**频繁**切换显示隐藏的场景

```html
<div id="app">
	年龄{{age}},经判定为:
	<span v-show="age <= 35">年轻人</span>
	<span v-show="age > 35 & age<=60">中年人</span>
</div>
```



**v-if**
1.**作用**：控制元素显示隐藏（条件渲染)
2.**表达式值**：true 显示，false 隐藏
3.**原理**：基于条件判断，是否 创建 或 移除 元素节点
4.**场景**：要么显示，要么隐藏，不频繁切换的场景

```html
<div id="app">
    年龄{{age}},经判定为:
    <span v-if="age <= 35">年轻人</span>
    <span v-else-if="age > 35 && age < 60">中年人</span>
    <span v-else>老年人</span>
</div>
```



**v-on**
1.**作用**：注册事件 = 添加监听提供处理逻辑
2.**语法**：
	v-on:事件名="内联语句 / methods中的函数名"
	（**简写** @事件名 = "内联语句 / methods中的函数名")

```vue
<div id="app">
	<input type="button" value="按钮" v-on:click="handle()">
	<input type="button" value="按钮" @click="handle()">
</div>
<script>
    new Vue({
        el: "#app",
        data: {
    	//...
        },
        methods: {
            handle:function(){
                alert('我被点击了');
            }
        },
    })
</script>
```



**v-bind**
1.**作用**：动态的设置html的标签属性，如src、url、title 等
2.**简写**：`:`

```html
<div id="app">
	<a v-bind:href="url">百度</a>
	<a :href="url">百度</a>
</div>
<script>
  new Vue({
     el: "#app",
     data: {
        url: "https://www.baidu.com"
     }
  })
</script>
```



**v-for**
**作用**：基于数据循环，多次渲染整个元素→ 数组、对象、数字..

**v-for 中key 的作用**：给元素添加的唯一标识。若不加key，v-for 的默认行为会尝试原地修改元素，属性值可能使用错误 (就地复用)

```html
<div id="app">
	<div v-for="addr in addrs" :key="addr">{{addr}}</div>
	<div v-for="(addr,index) in addrs">{{index + 1}} : {{addr}}</div>

</div>
<script>
  new Vue({
     el: "#app",
     data: {
        addrs: ['北京','上海','广州','深圳','成都','杭州']
     }
  })
</script>
```



**v-model**

**作用**：给 表单元素 使用，双向数据绑定→ 可以快速 获取 或 设置 表单元素内容

```html
<div id="app">
    <!--v-model 可以让数据和视图，形成双向数据绑定
    (1)数据变化，视图自动更新
    (2)视图变化，数据自动更新
    可以快速获取或设置表单元素的内容
    -->
    账户:<input type="text" v-model="username"> <br><br>
    密码:<input type="password" v-model="password"> <br><br>
    <button>登录</button>
    <button>重置</button>
</div>
<script>
    const app = new Vue({
        el:'#app',
        data:{
        	username : '',
        	password: ''
        }
    })
</script>
```



## 生命周期

**生命周期**：指一个对象从创建到销毁的整个过程。
**生命周期的八个阶段**：每触发一个生命周期事件，会自动执行一个生命周期方法(钩子)。

| **状态**      | **阶段周期** |
| ------------- | ------------ |
| beforeCreate  | 创建前       |
| created       | 创建后       |
| beforeMount   | 挂载前       |
| mounted       | 挂载完成     |
| beforeUpdate  | 更新前       |
| updated       | 更新后       |
| beforeDestroy | 销毁前       |
| destroyed     | 销毁后       |

![image-20240801033337190](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408010333827.png)

**mounted**：挂载完成，Vue初始化成功，HTML页面渲染成功。（发送请求到服务端，加载数据）

```html
<script>
    new Vue({
        el: "#app",
        data: {
        },
        mounted() {
            console.log("Vue挂载完毕,发送请求获取数据");
        },
        methods: {
        },
    })
</script>
```



---

# Vue3

## 简介

2020年9月18日，`Vue.js`发布版`3.0`版本，代号：`One Piece`

### 特点

- 源码升级
  - 使用Proxy代替`defineProperty`实现响应式。
  - 重写虚拟DOM的实现和`Tree-Shaking`。
- 可以更好的支持TypeScript。
- 新的内置组件
  - Teleport、Fragments、Suspense
- Composition API
- 新的全局 API
- 响应性系统
- 模板语法改进
- 渲染机制改进
- 自定义渲染器 API

### 组件风格

- **单文件组件**
  - 在大多数启用了构建工具的 Vue 项目中，我们可以使用一种类似 HTML 格式的文件来书写 Vue 组件，它被称为**单文件组件** (也被称为 `*.vue` 文件，英文 Single-File Components，缩写为 **SFC**)。顾名思义，Vue 的单文件组件会将一个组件的逻辑 (JavaScript)，模板 (HTML) 和样式 (CSS) 封装在同一个文件里。
- API 风格
  - **选项式 API (Options API)**
    - 使用选项式 API，我们可以用包含多个选项的对象来描述组件的逻辑，例如 `data`、`methods` 和 `mounted`。选项所定义的属性都会暴露在函数内部的 `this` 上，它会指向当前的组件实例。
  - **组合式 API (Composition API)**
    - 通过组合式 API，我们可以使用导入的 API 函数来描述组件逻辑。在单文件组件中，组合式 API 通常会与 `<script setup>` 搭配使用。这个 `setup` attribute 是一个标识，告诉 Vue 需要在编译时进行一些处理，让我们可以更简洁地使用组合式 API。比如，`<script setup>` 中的导入和顶层变量/函数都能够在模板中直接使用。



## 安装Vue3

1. 安装Node.js

2. 安装vue

   ```bash
   npm install -g -d vue @vue/cli 
   ```

3. 安装官方推荐的vscode插件：

   - Vue Official （旧名叫 Volar）
     - 在功能上 `volar` 和 `vetur` 是一致的，都是针对 `vue` 的插件，但是 `volar` 的功能却要强大得多。



## 创建Vue3工程

### 1.通过 CDN 使用 vue

可以借助 script 标签直接通过 CDN 来使用 Vue：

```html
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
```

当然，也可以下载此文件并自行提供服务。

```html
<!-- 将vue.js文件下载到根目录 -->
<script src="vue.global.js"></script>
```

这里使用 [unpkg](https://unpkg.com/)，但也可以使用任何提供 npm 包服务的 CDN，例如 [jsdelivr](https://www.jsdelivr.com/package/npm/vue) 或 [cdnjs](https://cdnjs.com/libraries/vue)。

通过 CDN 使用 Vue 时，不涉及“构建步骤”。这使得设置更加简单，并且可以用于增强静态的 HTML 或与后端框架集成。

但是将无法使用单文件组件 (SFC) 语法。

**全局构建版本示例**：

```vue
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>

<div id="app">{{ message }}</div>

<script>
    const { createApp, ref } = Vue

    createApp({
        setup() {
            const message = ref('Hello vue!')
            return {
                message
            }
        }
    }).mount('#app')
</script>
```

**ES 模块构建版本**：

```vue
<div id="app">{{ message }}</div>

<script type="module">
    import { createApp, ref } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'

    createApp({
        setup() {
            const message = ref('Hello Vue!')
            return {
                message
            }
        }
    }).mount('#app')
</script>
```



### 2.基于 vue-cli 创建

官方文档：[创建一个项目 | Vue CLI (vuejs.org)](https://cli.vuejs.org/zh/guide/creating-a-project.html)

vue-cli 是基于 webpack 的。

> Vue CLI 现已处于维护模式!
>
> 现在官方推荐使用 [`create-vue`](https://github.com/vuejs/create-vue) 来创建基于 [Vite](https://cn.vitejs.dev/) 的新项目。 另外请参考 [Vue 3 工具链指南](https://cn.vuejs.org/guide/scaling-up/tooling.html) 以了解最新的工具推荐。

```bash
## 查看@vue/cli版本，确保@vue/cli版本在4.5.0以上
vue --version

## 安装或者升级你的@vue/cli 
npm install -g @vue/cli

## 执行创建命令
vue create vue_test

##  随后选择3.x
##  Choose a version of Vue.js that you want to start the project with (Use arrow keys)
##  > 3.x
##    2.x

## 启动
cd vue_test
npm run serve
```



### 3.基于 vite 创建

`vite` 是新一代前端构建工具

官方文档：[Home | Vite中文网 (vitejs.cn)](https://vitejs.cn/)

优势：

- 轻量快速的热重载（`HMR`），能实现极速的服务启动。

- 对 `TypeScript`、`JSX`、`CSS` 等支持开箱即用。

- 真正的按需编译，不再等待整个应用编译完成。

  - webpack的构建：

    ![image-20241014092735969](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202410140927292.png)

  - vite的构建：

    ![image-20241014092851927](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202410140928185.png)

具体操作（官方教程：[快速上手 | Vue.js (vuejs.org)](https://cn.vuejs.org/guide/quick-start#creating-a-vue-application)）

```bash
## 1.创建命令
npm create vue@latest

## 2.具体配置 （此处为了手动学习和理解，一些配置在构建时候应该选no，在正常使用时候选yes）
## 配置项目名称
√ Project name: vue3_test
## 是否添加TypeScript支持
√ Add TypeScript?  Yes
## 是否添加JSX支持
√ Add JSX Support?  No
## 是否添加路由环境
√ Add Vue Router for Single Page Application development?  No
## 是否添加pinia环境
√ Add Pinia for state management?  No
## 是否添加单元测试
√ Add Vitest for Unit Testing?  No
## 是否添加端到端测试方案
√ Add an End-to-End Testing Solution? » No
## 是否添加ESLint语法检查
√ Add ESLint for code quality?  No
## 是否添加Prettiert代码格式化
√ Add Prettier for code formatting?  No
```



### 初步启动项目

在初始化完毕后，通过终端输入 `npm i` 安装项目所需的资源，安装完成后项目内会生成 `node_modules` 文件夹。

```bash
npm run dev
// ctrl + c 关闭运行
```

初次运行将看到vue自带的主页面。

将src目录内的内容删除，我们实现一个简单的Vue网页（选项式API）。

文件目录结构：

```bash
src:.
│  App.vue
│  main.ts
│
└─components
        Person.vue
```

```ts
// main.ts

// 引入createApp用于创建应用
import {createApp} from 'vue'
// 引入App根组件
import App from './App.vue'

createApp(App).mount('#app')
```

```vue
<!-- App.vue -->

<template>
    <!--html-->
    <div class="app">
        <h1>Vue Test：你好！</h1>
        <Person /> <!--使用组件-->
    </div>
</template>

<script>
// JS 或 TS
import Person from './components/Person.vue'

export default {
    name: 'App', //组件名
    components: { Person } //注册组件
}
</script>

<style>
/* 样式 */
.app {
    background-color: antiquewhite;
    box-shadow: 0 0 10px;
    border-radius: 10px;
    padding: 20px;
}
</style>
```

```vue
<!-- Person.vue -->

<template>
    <div class="person">
        <h2>姓名：{{ name }}</h2>
        <h2>年龄：{{ age }}</h2>
        <button @click="changeName">修改名字</button>
        <button @click="changeAge">年龄+1</button>
        <button @click="showTel">点我查看联系方式</button>
    </div>
</template>

<script lang="ts">
export default {
    name: 'App',
    data() {
        return {
            name: '张三',
            age: 18,
            tel: '13888888888'
        }
    },
    methods: {
        changeName() {
            this.name = prompt("请输入名字", "张三") as string;
        },
        changeAge() {
            this.age += 1
        },
        showTel() {
            alert(this.tel)
        }
    },
}
</script>

<style>
/* 样式 */
.person {
    background-color: lightblue;
    box-shadow: 0 0 10px;
    border-radius: 10px;
    padding: 20px;
}
</style>
```

随后再次启动`npm run dev`，即可看到一个由vue构建的简单页面。



## 项目文件概述

**Vue 初始项目文件**：

1. `index.html` ：入口文件

   - 这是 Vue 项目的主入口文件，包含基本的 HTML 结构和项目根元素。

     启动 Vue 项目后，访问网站首页即加载此 HTML 文件。

2. `env.d.ts`文件：

     - TypeScript 的类型声明文件。用于定义环境变量的类型。在使用 TypeScript 开发时，尤其是在 Vite 项目中，这个文件通常位于项目的根目录。它的主要作用是为 TypeScript 提供有关环境变量的类型信息，以便在编译时进行类型检查和在编码时提供自动完成。
       
     - 一个典型的 **env.d.ts** 文件可能包含以下内容：
       
        ```ts
        /// <reference types="vite/client" />
        
        declare module "*.vue" {
            import { DefineComponent } from "vue";
            const component: DefineComponent<{}, {}, any>;
            export default component;
        }
        
        interface ImportMetaEnv {
            readonly VITE_TITLE: string;
            // 可以在此处继续声明其他环境变量
        }
        
        interface ImportMeta {
            readonly env: ImportMetaEnv;
        }
        ```
        
        - 解释：
          - `/// <reference types="vite/client" />`：三斜线指令，用于包含 Vite 客户端的类型声明，允许 TypeScript 识别 Vite 特有的模块。
          - `declare module "*.vue"`：声明 Vue 单文件组件的模块，使 TypeScript 能正确处理这些文件。
          - `interface ImportMetaEnv`：声明自定义的环境变量类型，例如 `VITE_TITLE`。
          - `interface ImportMeta`：扩展 `ImportMeta` 接口，以包含环境变量的类型信息。


3. `vite.config.ts`：Vite 配置文件

   - 用于配置 Vite，定义项目的构建和开发服务器的设置。

   - 示例：

     ```ts
     import { defineConfig } from 'vite';		// 从 Vite 导入 `defineConfig` 方法，用于定义配置。
     import vue from '@vitejs/plugin-vue';		// 导入 Vite 的 Vue 插件。
     
     export default defineConfig({		// 导出配置对象。
         plugins: [vue()],				// 使用 Vue 插件。
         server: {port: 3000},			// 设置开发服务器的端口为 3000。
         build: {outDir: 'dist'}			// 设置构建输出目录为 `dist`。
     });
     ```

4. `tsconfig.json`：TypeScript 配置文件

   - 用于配置 TypeScript 编译器的选项。

   - 示例：

     ```json
     {
       "compilerOptions": {
         "target": "esnext",				// 设置编译目标为最新的 ECMAScript 版本。
         "module": "esnext",				// 设置模块系统为 ESNext。
         "strict": true,						// 启用所有严格类型检查选项。
         "jsx": "preserve",					// 保留 JSX 语法，交由其他工具处理。
         "sourceMap": true,					// 生成源地图文件，便于调试。
         "resolveJsonModule": true,				// 允许导入 JSON 模块。
         "esModuleInterop": true,				// 启用 ECMAScript 模块与 CommonJS 模块之间的互操作性。
         "lib": ["esnext", "dom"],					// 包含 ECMAScript 和 DOM 库。
         "types": ["vite/client"]					// 包含 Vite 客户端的类型声明。
       },
       "include": ["src/**/*.ts", "src/**/*.d.ts", "src/**/*.tsx", "src/**/*.vue"]	// 指定要包含在编译中的文件和目录。
     }
     ```

5. `tsconfig.app.json`

   - 用于对应用程序的 TypeScript 编译进行细粒度设置。
6. `tsconfig.node.json`

   - 用于对 Node.js 应用程序的 TypeScript 编译进行细粒度设置。

7. `package.json`：项目配置文件

   - 包含项目的元数据、依赖和脚本。

   - 示例：

     ```json
     {
       "name": "vue-app",				// 项目名称。
       "version": "0.0.1",					// 项目版本。
       "scripts": {							// 定义项目的 npm 脚本。
         "dev": "vite",						// 启动开发服务器。
         "build": "vite build",					// 构建项目。
         "serve": "vite preview"					// 预览构建后的项目。
       },
       "dependencies": {						// 定义项目的生产依赖。
         "vue": "^3.2.0"						// Vue 框架。
       },
       "devDependencies": {					
         "@vitejs/plugin-vue": "^1.2.0",			// Vite 的 Vue 插件。
         "typescript": "^4.3.2",					// TypeScript 语言。
         "vite": "^2.3.0"						// Vite 构建工具。
       }
     }
     ```

7. `package-lock.json`

   - 用于锁定项目依赖版本的文件，确保项目构建一致性。

9. `src/main.ts`：项目主文件

   - 用于创建 Vue 应用实例并挂载到 `index.html` 中定义的根元素。

   - 示例：

     ```ts
     import { createApp } from 'vue';	// 从 Vue 库中导入 createApp 方法。
     import App from './App.vue';		// 导入根组件 App.vue。
     
     createApp(App).mount('#app');		// 创建 Vue 应用实例，并将其挂载到 index.html 中的 #app 元素上。
     ```

9. `src/App.vue`：主组件文件

   - Vue 项目的根组件。
   
   - 示例：
   
     ```vue
     <template>							<!-- 定义组件的模板部分，包含 HTML 结构。-->
     <div id="app">						<!-- 根元素。  -->
         <h1>{{ title }}</h1>				<!-- 显示标题。  -->
         </div>
     </template>
     
     <script lang="ts"> <!-- 定义组件的脚本部分，使用 TypeScript 编写。  -->
         import { defineComponent } from 'vue'; <!--从 Vue 库中导入 defineComponent 方法。-->
     
         export default defineComponent({ 		<!--定义并导出组件。-->
             name: 'App',						<!--组件名称。-->
             data() {							<!-- 定义组件的数据属性 -->
                 return {
                     title: import.meta.env.VITE_TITLE	<!--title 值来自环境变量 VITE_TITLE。-->
                 };
             }
         });
     </script>
     
     <style>
         
         #app {		/*根元素的样式定义。*/
             font-family: Avenir, Helvetica, Arial, sans-serif;
             -webkit-font-smoothing: antialiased;
             -moz-osx-font-smoothing: grayscale;
             text-align: center;
             color: #2c3e50;
             margin-top: 60px;
         }
     </style>
     ```



## 基础语法

### 模板语法

Vue 使用一种基于 HTML 的模板语法，使我们能够声明式地将其组件实例的数据绑定到呈现的 DOM 上。所有的 Vue 模板都是语法层面合法的 HTML，可以被符合规范的浏览器和 HTML 解析器解析。

在底层机制中，Vue 会将模板编译成高度优化的 JavaScript 代码。结合响应式系统，当应用状态变更时，Vue 能够智能地推导出需要重新渲染的组件的最少数量，并应用最少的 DOM 操作。



### 文本插值

最基本的数据绑定形式是文本插值，它使用的是“Mustache”语法 (即双大括号)：

```vue
<span>Message: {{ msg }}</span>
```

双大括号标签会被替换为相应组件实例中 `msg` 属性的值。同时每次 `msg` 属性更改时它也会同步更新。



### 原始 HTML

双大括号会将数据解释为纯文本，而不是 HTML。若想插入 HTML，需要使用 `v-html`

```vue
<p>Using text interpolation: {{ rawHtml }}</p>
<p>Using v-html directive: <span v-html="rawHtml"></span></p>
```

在当前组件实例上，将此元素的 innerHTML 与 `rawHtml` 属性保持同步。

`span` 的内容将会被替换为 `rawHtml` 属性的值，插值为纯 HTML——数据绑定将会被忽略。注意，你不能使用 `v-html` 来拼接组合模板，因为 Vue 不是一个基于字符串的模板引擎。在使用 Vue 时，应当使用组件作为 UI 重用和组合的基本单元。

> 安全警告
>
> 在网站上动态渲染任意 HTML 是非常危险的，因为这非常容易造成 [XSS 漏洞](https://zh.wikipedia.org/wiki/跨網站指令碼)。请仅在内容安全可信时再使用 `v-html`，并且**永远不要**使用用户提供的 HTML 内容。



### Attribute 绑定

双大括号不能在 HTML attributes 中使用。想要响应式地绑定一个 attribute，应该使用 `v-bind` 指令

```vue
<div v-bind:id="dynamicId"></div>
```

`v-bind` 指令指示 Vue 将元素的 `id` attribute 与组件的 `dynamicId` 属性保持一致。如果绑定的值是 `null` 或者 `undefined`，那么该 attribute 将会从渲染的元素上移除。

#### 简写

因为 `v-bind` 非常常用，我们提供了特定的简写语法：

```vue
<div :id="dynamicId"></div>
```

#### 同名简写

- 仅支持 3.4 版本及以上

如果 attribute 的名称与绑定的 JavaScript 值的名称相同，那么可以进一步简化语法，省略 attribute 值：

```vue
<div v-bind:id="id"></div>
<div :id="id"></div>

<!-- 与 :id="id" 相同 -->
<div :id></div>

<!-- 这也同样有效 -->
<div v-bind:id></div>
```

#### 布尔型 Attribute

依据 true / false 值来决定 attribute 是否应该存在于该元素上。`disabled` 就是最常见的例子之一。

`v-bind` 在这种场景下的行为略有不同：

```vue
<button :disabled="isButtonDisabled">Button</button>
```

当 `isButtonDisabled` 为[真值](https://developer.mozilla.org/en-US/docs/Glossary/Truthy)或一个空字符串 (即 `<button disabled="">`) 时，元素会包含这个 `disabled` attribute。而当其为其他[假值](https://developer.mozilla.org/en-US/docs/Glossary/Falsy)时 attribute 将被忽略。

- `isButtonDisabled` 为 `true`，渲染结果为：

  - ```vue
    <button :disabled="false">Button</button>
    ```

- `isButtonDisabled` 为 `false`，渲染结果为：

  - ```vue
    <button>Button</button>
    ```

#### 动态绑定多个值

如果你有像这样的一个包含多个 attribute 的 JavaScript 对象：

```js
const objectOfAttrs = {
  id: 'container',
  class: 'wrapper',
  style: 'background-color:green'
}
```

通过不带参数的 `v-bind`，你可以将它们绑定到单个元素上：

```vue
<div v-bind="objectOfAttrs"></div>
```



### 使用 JavaScript 表达式

 Vue 实际上在所有的数据绑定中都支持完整的 JavaScript 表达式。

这些表达式都会被作为 JavaScript ，以当前组件实例为作用域解析执行。

在 Vue 模板内，JavaScript 表达式可以被使用在如下场景上：

- 在文本插值中 (双大括号)
- 在任何 Vue 指令 (以 `v-` 开头的特殊 attribute) attribute 的值中

```vue
{{ number + 1 }}

{{ ok ? 'YES' : 'NO' }}

{{ message.split('').reverse().join('') }}

<div :id="`list-${id}`"></div>
```

每个绑定仅支持**单一表达式**，也就是一段能够被求值的 JavaScript 代码。

一个简单的判断方法是是否可以合法地写在 `return` 后面。

因此，下面的例子都是**无效**的：

```vue
<!-- 这是一个语句，而非表达式 -->
{{ var a = 1 }}

<!-- 条件控制也不支持，请使用三元表达式 -->
{{ if (ok) { return message } }}
```

#### 受限的全局访问

模板中的表达式将被沙盒化，仅能够访问到有限的全局对象列表。

在模板中的表达式可以访问一些常用的内置全局对象，比如：

- `Math`: 提供数学常数和函数，例如 `Math.PI` 和 `Math.random()`.
- `Date`: 提供处理日期和时间的方法，例如 `Date.now()` 和 `new Date()`.

没有显式包含在列表中的全局对象将不能在模板内表达式中访问，例如用户附加在 `window` 上的属性。然而，你也可以自行在 `app.config.globalProperties`上显式地添加它们，供所有的 Vue 表达式使用。



### 指令

**完整的指令语法**：

![指令语法图](https://cn.vuejs.org/assets/directive.DtZKvoAo.png)

指令由 `v-` 作为前缀，表明它们是一些由 Vue 提供的特殊 attribute，你可能已经猜到了，它们将为渲染的 DOM 应用特殊的响应式行为。

指令 attribute 的期望值为一个 JavaScript 表达式 (除了少数几个例外: `v-for`、`v-on` 和 `v-slot`)。一个指令的任务是在其表达式的值变化时响应式地更新 DOM。

> 以 `v-if`为例：
>
> ```vue
> <p v-if="seen">Now you see me</p>
> ```
>
> 这里，`v-if` 指令会基于表达式 `seen` 的值的真假来移除/插入该 `<p>` 元素。

#### 参数 Arguments

某些指令会需要一个“参数”，在指令名后通过一个冒号隔开做标识。

> 例如用 `v-bind` 指令来响应式地更新一个 HTML attribute：
>
> ```vue
> <a v-bind:href="url"> ... </a>
> 
> <!-- 简写 -->
> <a :href="url"> ... </a>
> ```
>
> 这里 `href` 就是一个参数，它告诉 `v-bind` 指令将表达式 `url` 的值绑定到元素的 `href` attribute 上。在简写中，参数前的一切 (例如 `v-bind:`) 都会被缩略为一个 `:` 字符。
>
> 另一个例子是 `v-on` 指令，它将监听 DOM 事件：
>
> ```vue
> <a v-on:click="doSomething"> ... </a>
> 
> <!-- 简写 -->
> <a @click="doSomething"> ... </a>
> ```
>
> 这里的参数是要监听的事件名称：`click`。`v-on` 有一个相应的缩写，即 `@` 字符。我们之后也会讨论关于事件处理的更多细节。

#### 动态参数

同样在指令参数上也可以使用一个 JavaScript 表达式，需要包含在一对方括号内。

> ```vue
> <a v-bind:[attributeName]="url"> ... </a>
> 
> <!-- 简写 -->
> <a :[attributeName]="url"> ... </a>
> ```
>
> 这里的 `attributeName` 会作为一个 JavaScript 表达式被动态执行，计算得到的值会被用作最终的参数。
>
> 举例来说，如果你的组件实例有一个数据属性 `attributeName`，其值为 `"href"`，那么这个绑定就等价于 `v-bind:href`。

> 相似地，你还可以将一个函数绑定到动态的事件名称上：
>
> ```vue
> <a v-on:[eventName]="doSomething"> ... </a>
> 
> <!-- 简写 -->
> <a @[eventName]="doSomething"> ... </a>
> ```
>
> 在此示例中，当 `eventName` 的值是 `"focus"` 时，`v-on:[eventName]` 就等价于 `v-on:focus`。

##### 动态参数值的限制

动态参数中表达式的值应当是一个字符串，或者是 `null`。特殊值 `null` 意为显式移除该绑定。其他非字符串的值会触发警告。

##### 动态参数语法的限制

动态参数表达式因为某些字符的缘故有一些语法限制，比如空格和引号，在 HTML attribute 名称中都是不合法的。

如果需要传入一个复杂的动态参数，推荐使用计算属性替换复杂的表达式。

> 例如下面的示例：
>
> ```vue
> <!-- 这会触发一个编译器警告 -->
> <a :['foo' + bar]="value"> ... </a>
> ```

当使用 DOM 内嵌模板 (直接写在 HTML 文件里的模板) 时，我们需要避免在名称中使用大写字母，因为浏览器会强制将其转换为小写。

> ```vue
> <a :[someAttr]="value"> ... </a>
> ```
>
> 上面的例子将会在 DOM 内嵌模板中被转换为 `:[someattr]`。如果你的组件拥有 “someAttr” 属性而非 “someattr”，这段代码将不会工作。
>
> 单文件组件内的模板**不**受此限制。

#### 修饰符 Modifiers

修饰符是以点开头的特殊后缀，表明指令需要以一些特殊的方式被绑定。

> 例如 `.prevent` 修饰符会告知 `v-on` 指令对触发的事件调用 `event.preventDefault()`：
>
> ```vue
> <form @submit.prevent="onSubmit">...</form>
> ```





## 核心语法

### OptionsAPI 与 CompositionAPI

- `Vue2`的`API`设计是`Options`（配置）风格的。
- `Vue3`的`API`设计是`Composition`（组合）风格的。

#### Options API 的弊端

`Options`类型的 `API`，数据、方法、计算属性等，是分散在：`data`、`methods`、`computed`中的，若想新增或者修改一个需求，就需要分别修改：`data`、`methods`、`computed`，不便于维护和复用。

```js
export default{
    data(){
        return{
            //功能A...
            //功能B...
            //...
        }
    },
    methods:{
            //功能A...
            //功能B...
            //...
    },
    computed:{
            //功能A...
            //功能B...
            //...
    },
    watch:{
            //功能A...
            //功能B...
            //...
    }
}
```

内容分散，每次添加功能都要在各部分写上对应内容，不利于维护。

#### CompositionAPI 优势

可以用函数的方式，更加优雅的组织代码，让相关功能的代码更加有序的组织在一起。

每一个功能对应一个function，而不用每写一个功能跨越四个部分。



### setup

#### setup 概述

在 Vue 3 中，`setup` 是组合式 API（Composition API）的核心部分，它允许你在一个函数中组织组件的逻辑，而不是在选项对象中。

特点如下：

- `setup`函数返回的对象中的内容，可直接在模板中使用。
- `setup`中访问`this`是`undefined`。
- `setup`函数会在`beforeCreate`之前调用，它是“领先”所有钩子执行的，位于生命周期最开始的位置。
- 组件中所用到的均配置在`setup`中：数据（data）、方法（methods）、计算属性（computed）、监视（watch）、...。

使用：

- setup接收两个参数：
  1. `props`：一个对象，包含传递给组件的所有 props。
  2. `context`：一个对象，包含 `attrs`、`slots` 和 `emit` 三个属性。

- setup 函数的返回值：

  - 若返回一个**对象**：则对象中的：属性、方法等，在模板中均可以直接使用。类似于选项式 API 中的 `data` 和 `methods`。

    - 

  - 若返回一个**函数**：则可以自定义渲染内容。

    - 示例代码如下：

      ```jsx
      setup(){
        return ()=> '你好啊！'
      }
      ```

- 属性方法定义：

  - 直接在`setup()`的代码块`{}`内定义的属性，不是响应式的。
  - 若要使用响应式特性，需声明`ref()`。

##### setup 与 Options API 的关系

- `setup`函数会在`beforeCreate`之前调用，领先于`data`、`methos`。
  - `Vue2` 的配置（`data`、`methos`......）中**可以访问到** `setup`中的属性、方法。（使用this.属性)
  - 但在`setup`中**不能访问到**`Vue2`的配置（`data`、`methos`......）。
- 两种语法可共存，但如果产生冲突，则`setup`优先。

#### setup 语法糖

`<script setup>` 是 Vue 3.2 引入的一种语法糖，旨在简化和优化组件的定义方式。它使得组件的定义更加直观和简洁。

1. **无需 `export default`**：
   - 使用 `<script setup>` 后，不再需要显式地导出组件定义。Vue 会自动处理这一部分。
2. **直接使用响应式变量和方法**：
   - 在 `<script setup>` 中定义的变量和方法，可以直接在模板中使用，无需返回它们。
3. **自动解析模板引用**：
   - 模板中引用的变量和方法，Vue 会自动解析并绑定，无需显式地进行绑定操作。
   - 这使得代码更加简洁和直观。
4. **组合式 API 更加简洁**：
   - `setup` 函数中的组合式 API (Composition API) 使用 `<script setup>` 语法糖后，不需要显式地写 `return` 语句。
   - 这减少了样板代码，使逻辑结构更加清晰。



#### 指定组件名字

组件的名字是文件名，若要在代码中另外指定组件名字，一般需要定义name属性。

```vue
<script lang="ts">
    export default{
        name:'Person234',
        setup(){/*...*/}
    }
</script>
```

若使用 `<script setup>`，则无法用 `export`，所以需另外开一个不带 `setup`的 `<script>`来定义组件名。

我们可以借助`vite`中的插件简化，使 `<script setup>`也能改组件名：

1. 安装插件：终端输入`npm i vite-plugin-vue-setup-extend -D`

2. 修改配置文件：`vite.config.ts`，文件内对应位置加入以下内容：

   ```ts
   import VueSetupExtend from 'vite-plugin-vue-setup-extend'
   
   export default defineConfig({
     plugins: [ VueSetupExtend() ]
   })
   ```

3. 修改标签，添加组件名：`<script setup lang="ts" name="Person">`



## ref 创建

### 基本类型的响应式数据
