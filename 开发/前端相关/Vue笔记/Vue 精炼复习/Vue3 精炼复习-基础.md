# Vue3 精炼复习 - 基础篇

![Vue3 精炼复习](./assets/Vue3%20%E7%B2%BE%E7%82%BC%E5%A4%8D%E4%B9%A0.png)

---

## 总简介

**vue2官网**：[Vue.js (vuejs.org)](https://v2.cn.vuejs.org/)

**vue3官网**：[Vue.js - 渐进式 JavaScript 框架 | Vue.js (vuejs.org)](https://cn.vuejs.org/)

- **其他参考教程**（官网教程已很完善且对新手友好）
  - [开始使用 Vue - 学习 Web 开发 | MDN (mozilla.org)](https://developer.mozilla.org/zh-CN/docs/Learn/Tools_and_testing/Client-side_JavaScript_frameworks/Vue_getting_started)
  - [Vue3 教程 | 菜鸟教程 (runoob.com)](https://www.runoob.com/vue3/vue3-tutorial.html)

`Vue` (发音为 /vjuː/，类似 **view**) 是一款用于`构建用户界面`的 JavaScript `渐进式` `框架`。它基于标准 HTML、CSS 和 JavaScript 构建，并提供了一套声明式的、组件化的编程模型，帮助你高效地开发用户界面。无论是简单还是复杂的界面，Vue 都可以胜任。

Vue 免除原生JavaScript中的DOM操作，简化书写，提高编程效率。基于MVVM(Model-View-ViewModel)思想，实现数据的双向绑定，将编程的关注点放在数据上。

Vue.js 的核心特点包括`响应式数据绑定`、`组件化开发`、`虚拟DOM`、`双向数据绑定`、`插件扩展能力`以及`简洁易学`的特点。



## Vue3简介

  2020年9月18日，`Vue.js`发布版`3.0`版本，代号：`One Piece`

### 升级特点

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
- **API 风格**
  - *选项式 API (Options API)*
    - 使用选项式 API，我们可以用包含多个选项的对象来描述组件的逻辑，例如 `data`、`methods` 和 `mounted`。选项所定义的属性都会暴露在函数内部的 `this` 上，它会指向当前的组件实例。
  - *组合式 API (Composition API)*
    - 通过组合式 API，我们可以使用导入的 API 函数来描述组件逻辑。在单文件组件中，组合式 API 通常会与 `<script setup>` 搭配使用。这个 `setup` attribute 是一个标识，告诉 Vue 需要在编译时进行一些处理，让我们可以更简洁地使用组合式 API。比如，`<script setup>` 中的导入和顶层变量/函数都能够在模板中直接使用。



## Vue开发者工具

- 对应的Vue开发插件：
  - 谷歌 Chrome 浏览器 [Vue.js Devtools_6.6.3_Chrome插件下载_极简插件 (zzzmh.cn)](https://chrome.zzzmh.cn/info/nhdogjmejiglipccpnnnanhbledajbpd)
  - Edge 浏览器 [Vue.js devtools - Microsoft Edge Addons](https://microsoftedge.microsoft.com/addons/detail/vuejs-devtools/olofadcdnkkjdfgjcmjaadnlehnnihnl?hl=zh-CN)

安装成功后，点击进入Vue.js devtools插件的详细管理页面 [谷歌：详细信息]/[Edge：管理拓展] ，勾选**允许访问文件URL/网址**.

重启浏览器后，按F12，更多工具中找到Vue选项，即可开始使用Vue的开发者工具。

> 注意，需要Vue运行的页面才能使用该调试工具

  

## 引入方式

1. **通过 CDN 方式简单引入**（无法使用单文件组件 (SFC) 语法）

   ```html
   <!-- 全局构建版本示例 -->
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

   ```html
   <!-- ES 模块构建版本 -->
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

2. **基于 vue-cli 创建**

   1. 安装 ： `npm install -g @vue/cli`

   2. 创建并运行

      ```bash
      vue create vue_test
      cd vue_test
      npm run serve
      ```

3. **基于 vite 创建**（最新，推荐） ：

   ```bash
   npm create vue@latest

## 项目文件概述

1. `index.html` ：这是 Vue 项目的主入口文件，包含基本的 HTML 结构和项目根元素。启动 Vue 项目后，访问网站首页即加载此 HTML 文件。

2. `env.d.ts`文件：TypeScript 的类型声明文件。用于定义环境变量的类型。在使用 TypeScript 开发时，尤其是在 Vite 项目中，这个文件通常位于项目的根目录。它的主要作用是为 TypeScript 提供有关环境变量的类型信息，以便在编译时进行类型检查和在编码时提供自动完成。

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

     - `/// <reference types="vite/client" />`：三斜线指令，用于包含 Vite 客户端的类型声明，允许 TypeScript 识别 Vite 特有的模块。
     - `declare module "*.vue"`：声明 Vue 单文件组件的模块，使 TypeScript 能正确处理这些文件。
     - `interface ImportMetaEnv`：声明自定义的环境变量类型，例如 `VITE_TITLE`。
     - `interface ImportMeta`：扩展 `ImportMeta` 接口，以包含环境变量的类型信息。

3. `vite.config.ts`：Vite 配置文件。用于配置 Vite，定义项目的构建和开发服务器的设置。

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

4. `tsconfig.json`：TypeScript 配置文件。用于配置 TypeScript 编译器的选项。

   - 示例：

     ```json
     {
       compilerOptions: {
         target: "esnext",   // 设置编译目标为最新的 ECMAScript 版本。
         module: "esnext",   // 设置模块系统为 ESNext。
         strict: true,       // 启用所有严格类型检查选项。
         jsx: "preserve",    // 保留 JSX 语法，交由其他工具处理。
         sourceMap: true,    // 生成源地图文件，便于调试。
         resolveJsonModule: true,  // 允许导入 JSON 模块。
         esModuleInterop: true,    // 启用 ECMAScript 模块与 CommonJS 模块之间的互操作性。
         lib: ["esnext", "dom"],   // 包含 ECMAScript 和 DOM 库。
         types: ["vite/client"],   // 包含 Vite 客户端的类型声明。
       },
       include: ["src/**/*.ts", "src/**/*.d.ts", "src/**/*.tsx", "src/**/*.vue"], // 指定要包含在编译中的文件和目录。
     }
     ```

5. `tsconfig.app.json`：用于对应用程序的 TypeScript 编译进行细粒度设置。

6. `tsconfig.node.json`：用于对 Node.js 应用程序的 TypeScript 编译进行细粒度设置。

7. `package.json`：项目配置文件。包含项目的元数据、依赖和脚本。

   - 示例：

     ```json
     {
       name: "vue-app",  // 项目名称。
       version: "0.0.1", // 项目版本。
       scripts: {
         // 定义项目的 npm 脚本。
         dev: "vite",          // 启动开发服务器。
         build: "vite build",  // 构建项目。
         serve: "vite preview",// 预览构建后的项目。
       },
       dependencies: {
         // 定义项目的生产依赖。
         vue: "^3.2.0", // Vue 框架。
       },
       devDependencies: {
         "@vitejs/plugin-vue": "^1.2.0", // Vite 的 Vue 插件。
         typescript: "^4.3.2",           // TypeScript 语言。
         vite: "^2.3.0",                 // Vite 构建工具。
       },
     }
     ```

8. `package-lock.json`：用于锁定项目依赖版本的文件，确保项目构建一致性。

9. `src/main.ts`：项目主文件。用于创建 Vue 应用实例并挂载到 `index.html` 中定义的根元素。

   - 示例：

     ```js
     import { createApp } from 'vue';	// 从 Vue 库中导入 createApp 方法。
     import App from './App.vue';		// 导入根组件 App.vue。
     
     createApp(App).mount('#app');		// 创建 Vue 应用实例，并将其挂载到 index.html 中的 #app 元素上。
     ```

10. `src/App.vue`：主组件文件。Vue 项目的根组件。

    - 示例：

      ```vue
      <template>                <!-- 定义组件的模板部分，包含 HTML 结构。-->
        <div id="app">          <!-- 根元素。  -->
          <h1>{{ title }}</h1>  <!-- 显示标题。  -->
        </div>
      </template>
      
      <!--定义组件的脚本部分，使用 TypeScript 编写。  -->
      <script lang="ts">
      import { defineComponent } from 'vue'; //从 Vue 库中导入 defineComponent 方法。
      
      //定义并导出组件
      export default defineComponent({
        name: 'App',  //组件名称。
        data() {      //定义组件的数据属性
          return {
            title: import.meta.env.VITE_TITLE //title 值来自环境变量 VITE_TITLE。
          };
        }
      });
      </script>
      
      <style>
      #app {
        /*根元素的样式定义。*/
      }
      </style>
      ```

  

## 基础语法

### OptionsAPI 与 CompositionAPI

- `Vue2`的`API`设计是`Options`（配置）风格的。
- `Vue3`的`API`设计是`Composition`（组合）风格的。

`Options`类型的 `API`，数据、方法、计算属性等，是分散在：`data`、`methods`、`computed`中的，若想新增或者修改一个需求，就需要分别修改：`data`、`methods`、`computed`，不便于维护和复用。

`Composition` 类型的`API`可以用函数的方式，更加优雅的组织代码，让相关功能的代码更加有序的组织在一起。每一个功能对应一个function，而不用每写一个功能跨越四个部分。

#### setup

`setup` 是组合式 API（Composition API）的核心部分，它允许你在一个函数中组织组件的逻辑，而不是在选项对象中。

- `setup`函数返回的对象中的内容，可直接在模板中使用。
- `setup`中访问`this`是`undefined`。
- `setup`函数会在`beforeCreate`之前调用，它是“领先”所有钩子执行的，位于生命周期最开始的位置。
- 组件中所用到的均配置在`setup`中：数据（data）、方法（methods）、计算属性（computed）、监视（watch）、...。

`setup` **接收两个参数**：

1. `props`：一个对象，包含传递给组件的所有 props。
2. `context`：一个对象，包含 `attrs`、`slots` 和 `emit` 三个属性。

`setup` **函数的返回值**：

- 若返回一个**对象**：则对象中的：属性、方法等，在模板中均可以直接使用。类似于选项式 API 中的 `data` 和 `methods`。

#### setup 与 Options API 的关系

- `setup`函数会在`beforeCreate`之前调用，领先于`data`、`methos`。
  - `Vue2` 的配置（`data`、`methos`......）中\**可以访问到\** `setup`中的属性、方法。（使用this.属性)
  - 但在`setup`中\**不能访问到\**`Vue2`的配置（`data`、`methos`......）。
- 两种语法可共存，但如果产生冲突，则`setup`优先。

#### setup 语法糖

`<script setup>` 是 Vue 3.2 引入的一种语法糖，旨在简化和优化组件的定义方式。它使得组件的定义更加直观和简洁。

1. **无需 export default**：使用 `<script setup>` 后，不再需要显式地导出组件定义。Vue 会自动处理这一部分。
2. **直接使用响应式变量和方法**：在 `<script setup>` 中定义的变量和方法，可以直接在模板中使用，不需要显式地写 `return` 语句。

  

### 文本插值

最基本的数据绑定形式是文本插值，它使用的是“Mustache”语法 (即双大括号)：

```vue
<span>Message: {{ msg }}</span>
```

  

### 动态属性名

Vue 3 支持使用动态属性名：

```html
<button :[dynamicAttr]="value"></button>
```

  

### 使用 JavaScript 表达式

Vue 实际上在所有的数据绑定中都支持完整的 JavaScript 表达式。

```vue
{{ number + 1 }}

{{ ok ? 'YES' : 'NO' }}

{{ message.split('').reverse().join('') }}

<div :id="`list-${id}`"></div>
```

  

## 核心语法

### 响应式

当你在模板中使用了一个 `ref`，然后改变了这个 `ref` 的值时，Vue 会自动检测到这个变化，并且相应地更新 DOM。

#### 深层响应性

`Ref` 可以持有任何类型的值，包括深层嵌套的对象、数组等。这意味着即使改变嵌套对象或数组时，变化也会被检测到。

在模板中能直接访问到 `ref` 包裹的值，在脚本中需通过 `.value` 访问。

```vue
<template>
	<div>{{hw}}</div>
</template>

<script setup>
import { ref } from 'vue';
const hw = ref("hello,world!")
console.log(hw.value)
</script>
```

非原始值将通过 `reactive()` 转换为响应式代理。这意味着对象或数组中的每一个嵌套层级都会被 Vue 处理成响应式的，确保任何层级的变化都能被检测到。

有时候我们并不需要这种深层响应性，可以通过 `shallowRef` 来放弃深层响应性。对于浅层 ref，只有 `.value` 的访问会被追踪。

```vue
<script setup>
import { shallowRef } from 'vue';

// 创建一个 shallow ref，并赋值一个嵌套对象
const shallowState = shallowRef({
  user: {
    name: 'Bob',
    preferences: {
      theme: 'dark'
    }
  }
});

// 改变嵌套对象的值
shallowState.value.user.preferences.theme = 'light';

// Vue 不会检测到嵌套对象的变化，因为 shallowRef 只追踪 .value 的变化
</script>
```

#### reactive

若`ref`接收的是对象类型，内部其实也是调用了`reactive`函数。

`reactive`定义的响应式数据是“深层次”的。用 `reactive`定义的响应式数据不再需要用 `.value`调用对象之后再调用对象的值。

```vue
<template>
<div>汽车信息：{{ car.brand }}，价值{{ car.price }}万</div>
</template>

<script setup>
    import { reactive } from 'vue';
    let car = reactive({ brand: '奔驰', price: 100 })
    console.log(car)
</script>
```

与浅层 ref 类似，这里也有一个 `shallowReactive()` API 可以选择退出深层响应性。

#### 额外的 ref 解包细节

一个 ref 会在作为响应式对象的属性被访问或修改时自动解包。换句话说，它的行为就像一个普通的属性：

```js
const count = ref(0)
const state = reactive({
  count
})

console.log(state.count) // 0

state.count = 1
console.log(count.value) // 1
```

如果将一个新的 ref 赋值给一个关联了已有 ref 的属性，那么它会替换掉旧的 ref：

```js
const otherCount = ref(2)

state.count = otherCount
console.log(state.count) // 2
// 原始 ref 现在已经和 state.count 失去联系
console.log(count.value) // 1
```

与 reactive 对象不同的是，当 ref 作为响应式数组或原生集合类型 (如 `Map`) 中的元素被访问时，它不会被解包：

```js
const books = reactive([ref('Vue 3 Guide')])
console.log(books[0].value) // 这里需要 .value

const map = reactive(new Map([['count', ref(0)]]))
console.log(map.get('count').value) // 这里需要 .value
```

在 Vue 的模板渲染上下文中，只有顶级的 `ref` 属性会自动解包，这意味着在模板中使用这些 `ref` 属性时，不需要显式地使用 `.value`。

```vue
<template>
<!-- 顶级 ref 属性可以直接使用，并且会被自动解包 -->
<p>{{ count + 1 }}</p> <!-- 渲染结果为 1 -->

<!-- 非顶级 ref 属性不会自动解包 -->
<p>{{ object.id + 1 }}</p> <!-- 渲染结果为 [object Object]1 -->
</template>

<script setup>
    import { ref } from 'vue';
    const count = ref(0); // 定义顶级的 ref 属性
    const object = { id: ref(1) }; // 定义一个对象，其中包含一个 ref 属性
</script>
```

#### ref 与 reactive 对比

- 宏观角度看：

  1. `ref`用来定义：**基本类型数据**、**对象类型数据**；
  2. `reactive`用来定义：**对象类型数据**。

- 区别：

  - `ref`创建的变量必须使用`.value`（可以使用`volar`插件自动添加`.value`）。

  - `reactive`直接访问和修改属性。

  - `ref` 重新分配`.value`，不会失去响应式链接。`reactive`重新分配一个新对象，会失去响应式链接（可以使用`Object.assign`去整体替换）。

    ```js
    // ref
    let car = ref({brand:'奔驰',price:100})
    car.value = {brand:'奥拓',price:1}
    
    // reactive
    let car = reactive({brand:'奔驰',price:100})
    Object.assign(car,{brand:'奥拓',price:1})
    car = {brand:'奥拓',price:1} // 失去链接

#### 响应式对象属性转换

`toRefs` 和 `toRef` 是 Vue 3 中用于将响应式对象的属性转换为独立的 `ref` 的两个实用函数。这些函数在处理复杂对象和增强代码可读性方面非常有用。

**toRef**：用于将一个响应式对象的单个属性转换为 `ref`。这在需要单独处理某个属性而不需要解构整个对象时非常有用。

**toRefs**：用于将一个响应式对象的所有属性转换为 `ref`，以便每个属性都可以独立地解构并使用。


```js
import { ref, reactive, toRefs, toRef } from 'vue'

// 数据
let person = reactive({ name: '张三', age: 18, gender: 1 })

// 通过toRefs将person对象中的n个属性批量取出，且依然保持响应式的能力
let { name, gender } = toRefs(person)

// 通过toRef将person对象中的gender属性取出，且依然保持响应式的能力
let age = toRef(person, 'age')
```

  

### 计算属性

模板中的表达式虽然方便，但也只能用来做简单的操作。如果在模板中写太多逻辑，会让模板变得臃肿，难以维护。推荐使用**计算属性** `compute` 来描述依赖响应式状态的复杂逻辑。

```vue
<template>
    <div class="fullname">
        姓：<input type="text" v-model="firstName"><br>
        名：<input type="text" v-model="lastName"><br>
        全名：<span>{{ fullName }}</span>
    </div>
</template>

<script setup lang="ts">
import {ref, computed} from 'vue'

let firstName = ref("张")
let lastName = ref("三")

let fullName = computed(()=>{
    return firstName.value +" "+ lastName.value
})
</script>
```

只在某些特殊场景中可能才需要用到“可写”的属性，可以通过同时提供 `getter` 和 `setter` 来创建：

```vue
<!-- 通过存取器直接修改全面 -->
<template>
  <div class="fullname">
    <h3>请输入英文名</h3>
    姓：<input type="text" v-model="firstName"><br>
    名：<input type="text" v-model="lastName"><br>
    全名：<span>{{ fullName }}</span><br>
    <input ref="fullnameEle" type="text">
    <button @click="changFullName()">通过setter修改全名（空格隔开姓和名）</button>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'

const fullnameEle = ref<HTMLInputElement>();
const firstName = ref("zhang")
const lastName = ref("san")

const fullName = computed({
  get() {
    return firstName.value + " " + lastName.value
  },
  set(val) {
    const [str1, str2] = val.split(' ')
    if (str2 !== undefined) {
      firstName.value = str1
      lastName.value = str2
    }
  },
})

function changFullName() {
  if (!fullnameEle.value) return;
  fullName.value = fullnameEle.value.value;
}
</script>
```

在计算属性中使用 `reverse()` 和 `sort()` 的时候务必小心！这两个方法将变更原始数组，计算函数中不应该这么做。请在调用这些方法之前创建一个原数组的副本。

  

### 侦听器

在组合式 API 中，我们可以使用 `watch` 函数在每次响应式状态发生变化时触发回调函数。

1. `watch` 的第一个参数是“数据源”，`Vue3`中的`watch`只能监视以下**四种数据**：
   1. `ref`定义的数据。
   2. `reactive`定义的数据。
   3. 用函数返回的一个值（`getter`函数）。
   4. 一个包含上述内容的数组。
2. `watch`第二个参数是一个回调函数，该回调函数会接收几个参数，用于处理和响应数据的变化。包括：
   1. **newValue**: 变化后的新值。
   2. **oldValue**: 变化前的旧值。
   3. **onCleanup**: 用于注册清理函数，当依赖项再次变化或组件卸载时执行。
   4. **注意**：
      * 若修改的是`ref`定义的对象中的属性，`newValue` 和 `oldValue` 都是新值，因为它们是同一个对象。

      * 若修改整个`ref`定义的对象，`newValue` 是新值， `oldValue` 是旧值，因为不是同一个对象了。
3. `watch`第三个参数为可选项`options`：
   1. `immediate`（即时回调）：布尔值，默认 `false`。如果为 `true`，立即执行回调。
      - `watch` 默认是懒执行的：仅当数据源变化时，才会执行回调。但在某些场景中，我们希望在创建侦听器时，立即执行一遍回调。举例来说，我们想请求一些初始数据，然后在相关状态更改时重新请求数据。可以通过传入 `immediate: true` 选项来强制侦听器的回调立即执行。
   2. `deep`（深层侦听）：布尔值，默认 `false`。如果为 `true`，深度监听对象内部值的变化。
      - `watch`侦听对象类型数据时，默认监视的是对象的`地址值`，仅当更改对象时才会触发回调。
      - 若想监视对象内部的数据，可以通过传入 `deep: true` 手动开启深度监视。
      - 在 Vue 3.5+ 中，`deep` 选项还可以是一个数字，表示最大遍历深度——即 Vue 应该遍历对象嵌套属性的级数。
   3. `once`（一次侦听）：布尔值，默认 `false`。如果为 `true`，回调只在源变化时触发一次。
   4. `flush`：字符串，指定回调函数的执行时机。可选值：
      - `'pre'`：组件更新之前（默认值）。
      - `'post'`：组件更新之后。
      - `'sync'`：同步执行。
4. `watch` 函数会返回一个停止监听的函数。当调用这个返回的函数时，侦听器就会停止工作。

```vue
<!--示例-->
<template>
    <div class="watch">
        <h2>姓名：{{ person.name }}</h2>
        <h2>年龄：{{ person.age }}</h2>
        <button @click="changeName">修改名字</button>
        <button @click="changeAge">修改年龄</button>
        <button @click="changePerson">换人</button>
    </div>
</template>

<script lang="ts" setup name="Person">
import { ref, watch } from 'vue'
let person = ref({name: '张三', age: 18})
// 方法（监听ref，所以三个函数都会触发监听器，但是因为是对象数据，所以只有 changePerson 会有新旧值不同）
function changeName() {person.value.name += '~'}
function changeAge() {person.value.age += 1}
function changePerson() {person.value = { name: '李四', age: 90 }}

let stopWatcher = watch(person, (newValue, oldValue) => {
    console.log('person变化了', newValue, oldValue)
}, { deep: true })

setTimeout(stopWatcher, 60000) // 60秒后停止监听
</script>
```

#### watch 使用情况

- 对于基本类型数据，在 watch 函数第一个参数中直接写变量名即可，监视的是其`value`值的改变。

- 对于对象类型数据，直接写数据名，监视的是对象的`地址值`。若想监视对象内部的数据，要**手动开启深度监视**。示例如上例（如果是 `reactive` 的响应式数据则不用手动开启深度监视）

- 监视 reactive 定义的对象类型数据，不能更换整个对象，要使用`Object.assign`来克隆对象内容。侦听器reactive默认是开启深度监视的。

- 监视 ref 或 reactive 定义的对象类型数据中的某个属性：
  监视的要是对象里的`属性`，那么最好写函数式。若是对象监视的是`地址值`，需要关注对象内部，需要手动开启深度监视。（因为函数式传入是每次都重新调用一次函数，所以即使地址值改变也能触发侦听）

  1. 若该属性值**不是**【对象类型】，需要在watch的第一个参数中，用函数形式将数据源以 return 的形式传入。

  2. 若该属性值**是**【对象类型】，可直接传入，也可用函数形式，建议写成函数。

     1. 若直接传入，监听的是地址值内的内容，类似于情况三
     2. 若用函数形式，则指向的传入地址值改变，也能观察到。

     ```js
     <template>
         <div class="watch">
             <h2>姓名：{{ person.name }}</h2>
             <h2>年龄：{{ person.age }}</h2>
             <h2>汽车：{{ person.car.c1 }}、{{ person.car.c2 }}</h2>
             <button @click="changeName">改名</button>
             <button @click="changeAge">改年龄</button>
             <button @click="changeC1">改第一台车</button>
             <button @click="changeC2">改第二台车</button>
             <button @click="changeCar">改整个车对象</button>
         </div>
     </template>
     
     <script setup lang="ts">
     import { reactive, watch } from 'vue'
     
     let person = reactive({
         name: "张三",
         age: 18,
         car: {
             c1: "奔驰",
             c2: "宝马",
         }
     })
     
     function changeName() {
         person.name = prompt("名字", "张三") as string
     }
     function changeAge() {
         const age = prompt("年龄", "18")
         person.age = age !== null ? Number(age) : 0
     }
     function changeC1() {
         person.car.c1 = "奥迪"
     }
     function changeC2() {
         person.car.c2 = "宾利"
     }
     function changeCar() {
         person.car = { c1: '特斯拉', c2: '比亚迪' }
     }
     //监视响应式对象中的某个属性，且该属性是基本类型的，要写成函数式
     watch(() => person.name, (newValue, oldValue) => {
         console.log('person.name变化了', newValue, oldValue)
     })
     //该属性是对象类型的，可以直接写，也能写函数，更推荐写函数
     watch(() => person.car, (newValue, oldValue) => {
         console.log('person.car变化了', newValue, oldValue)
     }, { deep: true })
     </script>
     
     <style>
     .watch {
         background-color: lightblue;
         box-shadow: 0 0 10px;
         border-radius: 10px;
         padding: 20px;
     }
     </style>

- 监视多个数据，可以使用数组形式。

  ```js
  watch([() => person.car , () => person.name] , (newValue, oldValue) => {
      console.log('person变化了', newValue, oldValue)
  }, { deep: true })
  ```

#### watchEffect

`watchEffect()` 会自动追踪回调函数中使用的所有响应式属性，而不需要显示地传入指定变量。

`watchEffect` 会在创建时立即执行一次回调函数，而`watch` 默认不会立即执行。

`watchEffect` 不会访问新旧值，只会检测更改后的当前值。

`watchEffect` 返回一个停止函数，可以手动停止监听。

```js
import { ref, watchEffect } from 'vue'

const count = ref(0)
const double = ref(0)

watchEffect(() => {
    // 出现了 double 和 count，这两个响应式数据会被监听
    double.value = count.value * 2
    console.log(`count is ${count.value}, double is ${double.value}`)
})
```

#### 副作用清理

有时我们可能会在侦听器中执行副作用，例如异步请求。

但如果在请求完成之前，数据源发生了变化，那么上一个请求一般来说已经是没有用的了。理想情况下，我们希望能够在数据源变为新值时取消过时的请求。

可以使用 `onWatcherCleanup()` API 来注册一个清理函数，当侦听器失效并准备重新运行时会被调用。`onWatcherCleanup` 仅在 Vue 3.5+ 中支持，并且必须在 `watchEffect` 效果函数或 `watch` 回调函数的同步执行期间调用：你不能在异步函数的 `await` 语句之后调用它。

```js
import { watch, onWatcherCleanup } from 'vue'

watch(id, (newId) => {
  const controller = new AbortController()

  fetch(`/api/${newId}`, { signal: controller.signal }).then(() => {
    // 回调逻辑
  })

  onWatcherCleanup(() => {
    controller.abort() // 终止过期请求
  })
})
```

作为替代，`onCleanup` 函数还作为第三个参数传递给侦听器回调，以及 `watchEffect` 作用函数的第一个参数：

```js
watch(id, (newId, oldId, onCleanup) => {
  // ...
  onCleanup(() => {
    // 清理逻辑
  })
})

watchEffect((onCleanup) => {
  // ...
  onCleanup(() => {
    // 清理逻辑
  })
})
```

#### 回调的触发时机

**为避免重复调用，Vue 会对更新进行批量处理**：例如，如果我们向一个数组同步推入一千个项目，我们可能不希望 `watch` 侦听器触发一千次。这种批量处理会在一个事件循环内合并多次变更，只触发一次回调。

默认情况下，`watch` 侦听器的回调会在父组件更新之后、所属组件的 DOM 更新之前被调用。也就是说，如果你在 `watch` 回调中访问组件的 DOM，它会处于更新前的状态。

```bash
父组件更新 -> watch 回调 -> 所属组件 DOM 更新
```

可以修改 `watch` 的 `flush` 选项以进行修改回调时机，`flush`含有以下状态（默认为 default）：

1. **post**：回调中能访问被 Vue 更新之后的所属组件的 DOM

2. **default**：默认的回调时机，即父组件更新后，所属组件更新前

3. **sync**：同步的回调时机，即 Vue 发生任何 DOM 更新前

   **输出顺序： sync->default->post**

  

### 模板引用

`ref` 是一个特殊的 attribute，它允许我们在一个特定的 DOM 元素或子组件实例被挂载后，获得对它的直接引用。

用在普通`DOM`标签上，获取的是`DOM`节点。用在组件标签上，获取的是组件实例对象。

**3.5 之前**，直接声明一个 `ref()` 即可获取对应使用了 `ref=""` DOM 元素。**3.5 之后**，提供对应辅助函数`useTemplateRef()`，更语义化。

```vue

<template>
  <div class="person">
    <h3 ref="title">Vue</h3>
    <input ref="myInput" type="text" placeholder="输入内容" /><br><br>
    <button @click="showLog">修改内容</button>
  </div>
</template>

<script lang="ts" setup name="Person">
import { ref } from 'vue'

// 3.5 之前的方法
let title = ref<HTMLHeadingElement>()
let myInput = ref<HTMLInputElement>()
// 3.5 之后的方法
let title = useTemplateRef("title")
let myInput = useTemplateRef("myInput")

function showLog() {
  if (!title.value || !myInput.value) return
  title.value.innerText = "函数触发修改"
  myInput.value.value = "函数触发修改"
}
</script>
```

#### 组件标签

- **子组件未使用 \<script setup>**：
  - 如果一个子组件使用的是选项式 API 或没有使用 `<script setup>`，被引用的组件实例和该子组件的 `this` 完全一致，这意味着父组件对子组件的每一个属性和方法都有完全的访问权。
  - 这容易让父子组件之间紧密耦合，因此应该只在绝对需要时才使用组件引用。
- **子组件使用 \<script setup>**：使用了 `<script setup>` 的组件是默认私有的，父组件无法访问子组件中的任何东西，除非子组件在其中通过 `defineExpose` 宏显式暴露。

```vue
<!-- 父组件获取子组件 -->
<template>
<Person ref="ren"/>
<button @click="test">测试</button>
</template>

<script setup>
    import Person from './components/Person.vue'
    import {useTemplateRef} from 'vue'

    let ren = useTemplateRef("ren")

    function test(){
        console.log(ren.value.name)
        console.log(ren.value.age)
    }
</script>


<!-- 子组件Person.vue中要使用defineExpose暴露内容 -->
<script lang="ts" setup name="Person">
    import {ref,defineExpose} from 'vue'

    let name = ref('张三')
    let age = ref(18)

    // 使用defineExpose将组件中的数据交给外部
    defineExpose({name,age})
</script>
```

#### 函数模板引用

`ref` attribute 还可以绑定为一个函数，会在每次组件更新时都被调用。

使用函数作为 `ref` 允许你在元素被渲染和卸载时执行特定的逻辑。

该函数会收到元素引用作为其第一个参数 el：

- 当组件渲染时，`el` 参数会收到指向当前 DOM 元素的引用。
- 当这个 DOM 元素被卸载（即从页面中移除）时，函数也会被调用，此时 `el` 将会是 `null`**。**

```html
<input :ref="(el) => { /* 将 el 赋值给一个数据属性或 ref 变量 */ }">
```

需要使用动态的 `:ref` 绑定才能够传入一个函数。

  

### 组件基础

每当使用一个组件，就创建了一个新的**实例**。每一个组件都维护着自己的状态。

#### 传递 props

`defineProps` 是一个仅 `<script setup>` 中可用的编译宏命令，并不需要显式地导入。声明的 props 会自动暴露给模板。`defineProps` 会返回一个对象，其中包含了可以传递给组件的所有 props。

可以使用`ts`接口进行 props 传入进行限制。使用 `withDefault` 进行传入默认设置（避免没传入值而导致网页为空）。

```ts
// types / index.ts
export interface TitleInter{
  id: number,
  title: string,
}

export type Titles = Array<TitleInter>
```

```vue
<!--BlogPost.vue-->
<template>
    <div class="blogpost">
        <h4> {{ pre }} ：{{ title }}</h4>
    </div>
</template>

<script setup lang="ts">
import { type Titles } from './types';
const props = withDefaults(defineProps<Titles>(), {
    title: () => "test",
    pre: () => "Blog"
})
</script>
```

如果没有使用 `<script setup>`，props 必须以 `props` 选项的方式声明，props 对象会作为 `setup()` 函数的第一个参数被传入：

```js
export default {
  props: ['title'],
  setup(props) {
    console.log(props.title)
  }
}
```

#### 监听事件

父组件可以通过 `v-on` 或 `@` 来选择性地监听子组件上抛的事件，就像监听原生 DOM 事件那样。

子组件可以通过调用内置的 `$emit`传入事件名称来抛出一个事件。

子组件也可以通过 `defineEmits` 宏来声明需要抛出的事件，声明的事件将以函数形式返回。

```vue
<!--父组件-->
<template>
  <div>
    <BlogPost 
      v-for="post in posts"
      :key="post.id"
      :title="post.title"
      :fs="post.fs"
      @enlarge-text="post.fs += 0.1"
    />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import BlogPost from './BlogPost.vue';
const posts = ref([
  {id:"1",title:"title 1", fs: 1},
  {id:"2",title:"title 2", fs: 1},
])
</script>
```

```vue
<!--BlogPost.vue-->
<!-- 通过 $emit 直接抛出事件 -->
<template>
    <div class="blogpost" :style="{ fontSize: fs + 'em' }">
        <h4>{{ title }}</h4>
        <button @click="$emit('enlarge-text')">字体加大</button>
    </div>
</template>

<script setup>
const props = defineProps(['title', 'fs'])
</script>
```

```vue
<!--BlogPost.vue-->
<!-- 通过 defineEmit 用函数抛出事件 -->
<template>
    <div class="blogpost" :style="{ fontSize: fs + 'em' }">
        <h4>{{ title }}</h4>
        <button @click="enlargeTextEvent">字体加大</button>
    </div>
</template>

<script setup lang="ts">
const props = defineProps(['title', 'fs'])
const emit = defineEmits(['enlarge-text'])
function enlargeTextEvent(){
    emit("enlarge-text");
}
</script>
```

#### 插槽

使用 `<slot>` 作为一个占位符，父组件传递进来的内容就会渲染在这里。

详细讲解请看 Vue3 进阶部分。

#### 动态组件

有些场景会需要在多个组件间来回切换，比如 Tab 界面。可以通过 Vue 的 `<component>` 元素和特殊的 `is` attribute 实现。

```vue
<!--父组件App.vue-->
<template>
  <div class="demo">
    <button
       v-for="(_, tab) in tabs"
       :key="tab"
       :class="['tab-button', { active: currentTab === tab }]"
       @click="currentTab = tab"
     >
      {{ tab }}
    </button>
	  <component :is="tabs[currentTab]" class="tab"></component>
  </div>
</template>

<script setup lang="ts">
import Home from './Home.vue'
import Posts from './Posts.vue'
import Archive from './Archive.vue'
import { ref } from 'vue'
 
const currentTab = ref('Home')
const tabs = {Home,Posts,Archive}
</script>

<style>
.tab-button {
  padding: 6px 10px;
  border-top-left-radius: 3px;
  border-top-right-radius: 3px;
  border: 1px solid #ccc;
  cursor: pointer;
  background: #f0f0f0;
  margin-bottom: -1px;
  margin-right: -1px;
}
.tab-button:hover {
  background: #e0e0e0;
}
.tab-button.active {
  background: #e0e0e0;
}
.tab {
  border: 1px solid #ccc;
  padding: 10px;
}
</style>
```

```vue
<!--Home.vue-->
<template>
  <div class="tab">
    Home component
  </div>
</template>

<!--Posts.vue-->
<template>
  <div class="tab">
    Posts component
  </div>
</template>

<!--Archive.vue-->
<template>
  <div class="tab">
    Archive component
  </div>
</template>
```

  

### 生命周期

**生命周期钩子**：`Vue`组件实例在创建时要经历一系列的初始化步骤，在此过程中`Vue`会在合适的时机，调用特定的函数，从而让开发者有机会在特定阶段运行自己的代码，这些特定的函数统称为：生命周期钩子。

**Vue3的生命周期**：

- **创建**阶段：`setup`
- **挂载**阶段：`onBeforeMount`、`onMounted`
- **更新**阶段：`onBeforeUpdate`、`onUpdated`
- **卸载**阶段：`onBeforeUnmount`、`onUnmounted`

<img src="https://cn.vuejs.org/assets/lifecycle_zh-CN.W0MNXI0C.png" alt="组件生命周期图示" style="zoom: 67%;" />

  

### 自定义 HOOKS

`hook`—— 本质是一个函数，把`setup`函数中使用的`Composition API`进行了封装，类似于`vue2.x`中的`mixin`。

在 `src` 中创建一个名为 `hooks`的目录，然后在里面创建 `.ts` 文件，进行 hooks 的定义。

> 例：下面定义了一个获取小狗图片的hooks。其中用到了axios插件（可用fetch替代）。
>
> ```vue
> <template>
>   <img v-for="(url,index) in dogList.urlList" :key="index" :src="(url as string)"> 
>   <span v-show="dogList.isLoading">加载中......</span><br>
>   <button @click="getDog">再来一只狗</button>
> </template>
> 
> <script setup lang="ts">
>   import useDog from '@/hooks/useDog'
> 	
>   let {dogList,getDog} = useDog()
> </script>
> 
> <style>
>   img{
>     height: 100px;
>     margin: 5px;
>   }
> </style>
> ```
>
> ```js
> // src / hooks / useDog.ts
> import { ref, reactive, onMounted } from "vue";
> import axios from "axios";
> 
> export default function () {
>   let dogList = reactive({
>     urlList: [] as string[],
>     isLoading: false,
>   });
> 
>   async function getDog() {
>     dogList.isLoading = true; // 加载开始时设置为 true
>     try {
>       let result = await axios.get(
>         "https://dog.ceo/api/breed/pembroke/images/random"
>       );
>       dogList.urlList.push(result.data.message);
>     } catch (error) {
>       alert(error);
>     } finally {
>       dogList.isLoading = false; // 加载结束时设置为 false
>     }
>   }
> 
>   onMounted(() => {
>     getDog();
>   });
> 
>   return { dogList, getDog };
> }

  

### 内置指令

![指令语法图](https://cn.vuejs.org/assets/directive.DtZKvoAo.png)

指令由 `v-` 作为前缀，表明它们是一些由 Vue 提供的特殊 attribute，它们将为渲染的 DOM 应用特殊的响应式行为。

#### v-bind 动态绑定

`v-bind` 用于将数据绑定到 HTML 元素的属性或组件的 prop 上。

在Vue 3中，`v-bind` 可以简写为 `:`。

```html
<!-- 绑定属性 -->
<img v-bind:src="imageSrc">

<!-- 简写形式 -->
<img :src="imageSrc">
```

##### v-bind 绑定 class 和 style

Vue 3 对 class 和 style 绑定进行了特别优化。

`:class` 指令也可以和一般的 `class` 属性共存。`style` 同理

###### 绑定 class

```html
<!-- 对象语法 + 简写语法 + 与普通属性共存 -->
<template>
    <div :class="{ active: isActive, 'text-danger': hasError }" class="text">
    </div>
</template>

<script setup lang="js">
    const isActive = ref(true)
    const hasError = ref(false)
</script>
```

```vue
<!-- 数组语法 + 一般写法 -->
<template>
    <div v-bind:class="[activeClass, errorClass]"></div>
</template>

<script setup lang="js">
    const activeClass = ref('active')
    const errorClass = ref('text-danger')
</script>
```

```vue
<!--如果你也想在数组中有条件地渲染某个 class，你可以使用三元表达式-->
<div :class="[isActive ? activeClass : '', errorClass]"></div>

<!--然而，这可能在有多个依赖条件的 class 时会有些冗长。因此也可以在数组中嵌套对象-->
<div :class="[{ [activeClass]: isActive }, errorClass]"></div>
```

###### 绑定 style

```html
<!-- 对象语法 -->
<div :style="{ color: activeColor, fontSize: fontSize + 'px' }"></div>

<!-- 数组语法 -->
<div :style="[baseStyles, overridingStyles]"></div>
```

##### v-bind 动态绑定多个属性

Vue 3 允许使用不带参数的 `v-bind` 一次性绑定多个属性。

```javascript
<template>
    <div v-bind="attrs"></div>
</template>

<script setup lang="js">
    const attrs = {
        id: 'container',
        class: 'wrapper',
        'data-status': 'active'
    }
</script>
```

  

#### v-model 双向数据绑定

`v-model` 是 Vue 中用于实现表单输入元素和组件双向数据绑定的指令。

将数据绑定到元素的 `value` 属性，监听 `input` 事件。

或将数据绑定到组件的 `modelValue` prop ，监听组件的 `update:modelValue` 事件。（进阶部分讲解）

##### 本质

`v-model`的本质是绑定 value 值和监听 input 事件。

```html
<!-- 使用v-model指令 -->
<input type="text" v-model="userName">

<!-- v-model的本质是下面这行代码 -->
<input 
  type="text" 
  :value="userName" 
  @input="userName =(<HTMLInputElement>$event.target).value"
>
```

##### html 元素双向绑定

- 文本类型绑定value值。

  ```html
  <!-- 文本输入 -->
  <input v-model="message" placeholder="edit me">
  <!-- 多行文本 -->
  <textarea v-model="message"></textarea>
  ```

- 单选按钮，绑定value值。

  ```html
  <!-- 单选按钮 -->
  <template>
      <input type="radio" id="jack" v-model="picked" value="one">
      <label for="jack">Jack</label>
  
      <input type="radio" id="abc" v-model="picked" value="two">
      <label for="abc">Jack</label>
  
      <input type="radio" id="eed" v-model="picked" value="three">
      <label for="eed">Jack</label>
      {{ picked }}
  </template>
  
  <script setup>
      import { ref } from 'vue'
      const picked = ref('')
  </script>
  ```

- 单一个的复选框，绑定布尔类型值。存在多个复选框则绑定value到数组。

  ```html
  <!-- 复选框 -->
  <script setup>
  import { ref } from 'vue'
  const checkedNames = ref([])
  </script>
  
  <template>
    <div>Checked names: {{ checkedNames }}</div>
  
    <input type="checkbox" id="jack" value="Jack" v-model="checkedNames" />
    <label for="jack">Jack</label>
   
    <input type="checkbox" id="john" value="John" v-model="checkedNames" />
    <label for="john">John</label>
   
    <input type="checkbox" id="mike" value="Mike" v-model="checkedNames" />
    <label for="mike">Mike</label>
  </template>
  ```

- 单选的选择列表绑定的是对应选项的value，多选则绑定到数组。

  ```html
  <template>
    <div>
      <h3>单选和多选组合</h3>
      
      <div class="select-group">
        <label>单选选择：</label>
        <select v-model="singleChoice">
          <option disabled value="">请选择</option>
          <option value="red">红色</option>
          <option value="green">绿色</option>
          <option value="blue">蓝色</option>
        </select>
        <p>选择结果: {{ singleChoice || '未选择' }}</p>
      </div>
      
      <div class="select-group">
        <label>多选选择：</label>
        <select v-model="multiChoice" multiple size="3">
          <option value="apple">苹果</option>
          <option value="banana">香蕉</option>
          <option value="orange">橙子</option>
          <option value="grape">葡萄</option>
        </select>
        <p>选择结果: {{ multiChoice.length ? multiChoice : '未选择' }}</p>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  const singleChoice = ref('') // 单选数据
  const multiChoice = ref([]) // 多选数据
  </script>
  ```


##### 修饰符

- `.lazy`：默认情况下，`v-model` 会在每次 `input` 事件后更新数据。可用 `lazy` 修饰符来改为在每次 `change` 事件（用户离开输入框或按下回车键）后更新数据。
- `.number`：如果想让输入自动转换为数字，可用 `number` 修饰符来管理输入。如果该值无法被 `parseFloat()` 处理，那么将返回原始值。`number` 修饰符会在输入框有 `type="number"` 时自动启用。
- `.trim`：如果想自动去除输入内容中两端的空格，可用 `trim` 修饰符。

  

#### v-on 事件监听

我们可以使用 `v-on` 指令 (简写为 `@`) 来监听 DOM 事件，并在事件触发时执行对应的 JavaScript。

```html
<!-- 简单计数器 -->
<script setup>
import { ref } from 'vue'
const counter = ref(0)
</script>

<template>
	<button @click="counter++">Add 1</button>
	<p>The button above has been clicked {{ counter }} times.</p>
</template>
```

##### 在内联事件处理器中访问事件参数

```html
<!-- 使用特殊的$event 变量，函数:function warn(message, event) {} -->
<button @click="warn('Form cannot be submitted yet.', $event)">
  Submit
</button>

<!-- 使用内联箭头函数 -->
<button @click="(event) => warn('Form cannot be submitted yet.', event)">
  Submit
</button>
```

##### 事件修饰符

在处理事件时调用 `event.preventDefault()` 或 `event.stopPropagation()` 是很常见的。尽管我们可以直接在方法内调用，但如果方法能更专注于数据逻辑而不用去处理 DOM 事件的细节会更好。

为解决这一问题，Vue 为 `v-on` 提供了**事件修饰符**。修饰符是用 `.` 表示的指令后缀，包含以下这些：

- `.stop` ：阻止事件冒泡传播。通常用于防止点击事件传递到父元素。
- `.prevent`：阻止默认行为。通常用于防止链接跳转或表单提交刷新页面。
- `.self`：仅当 event.target 是元素本身时才会触发事件处理器。忽略子元素触发的事件。
- `.capture`：使用事件捕获模式触发事件处理器。默认是事件冒泡模式。
- `.once`：事件处理器只触发一次。
- `.passive`：告诉浏览器你不想阻止默认行为，用于提高滚动性能。

> 使用修饰符时需要注意调用顺序，因为相关代码是以相同的顺序生成的。
> `@click.prevent.self` 会阻止元素及其子元素的所有点击事件的默认行为。
> `@click.self.prevent` 则只会阻止对元素本身的点击事件的默认行为。

##### 按键修饰符

可用 [`KeyboardEvent.key`](https://developer.mozilla.org/zh-CN/docs/Web/API/KeyboardEvent/key/Key_Values) 暴露的按键名称作为修饰符，但需要转为 kebab-case 形式。

```html
<input @keyup.page-down="onPageDown" />
```

在上面的例子中，仅会在 `$event.key` 为 `'PageDown'` 时调用事件处理。

Vue 为一些常用的按键提供了别名：

- `.enter`、`.tab`、`.delete` 、`.esc`、`.space`、`.up`、`.down`、`.left`、`.right`

可用以下系统按键修饰符来触发鼠标或键盘事件监听器，只有当按键被按下时才会触发：

- `.ctrl`、`.alt`、`.shift`、`.meta`

鼠标按键修饰符：

- `.left`、`.right`、`.middle`

> 举例来说：
>
> ```html
> <!-- Alt + Enter -->
> <input @keyup.alt.enter="clear" />
> 
> <!-- Ctrl + 点击 -->
> <div @click.ctrl="doSomething">Do something</div>
> ```

##### .exact 修饰符

`.exact` 修饰符允许精确控制触发事件所需的系统修饰符的组合。

```html
<!-- 当按下 Ctrl 时，即使同时按下 Alt 或 Shift 也会触发 -->
<button @click.ctrl="onClick">A</button>

<!-- 仅当按下 Ctrl 且未按任何其他键时才会触发 -->
<button @click.ctrl.exact="onCtrlClick">A</button>

<!-- 仅当没有按下任何系统按键时触发 -->
<button @click.exact="onClick">A</button>
```

  

#### v-show 与 v-if 条件渲染

`v-if` 和 `v-show`指令用于条件性地渲染一块内容。这块内容只会在指令的表达式返回真值时才被渲染。

也可以使用 `v-else` 为 `v-if` 添加一个“else 区块”。一个 `v-else` 元素必须跟在一个 `v-if` 或者 `v-else-if` 元素后面，否则它将不会被识别。

`v-else-if` 提供的是相应于 `v-if` 的“else if 区块”。它可以连续多次重复使用。

```html
<!--v-if示例-->
<template>
	<button @click="num = (num + 1) % 3">toggle</button>
	<div v-if="num === 1">A</div>
	<div v-else-if="num === 2">B</div>
	<div v-else>Not A/B</div>
</template>
```

```html
<!--v-show示例-->
<template>
	<button @click="num = (num + 1) % 3">toggle</button>
	<div v-show="num === 1">A</div>
	<div v-show="num === 2">B</div>
	<div v-show="num === 0">Not A/B</div>
</template>
```

`v-if` 的条件区块只有当条件首次变为 true 时才被渲染，条件值为 false则不会渲染。条件切换时，条件区块内的事件监听器和子组件都会被销毁或重建。

`v-show` 元素无论初始条件如何，始终会被渲染，只有 CSS `display` 属性会被切换。

总的来说，`v-if` 有更高的切换开销， `v-show` 有更高的初始渲染开销。因此，如果需要频繁切换，则使用 `v-show` 较好；如果在运行时绑定条件很少改变，则 `v-if` 会更合适。

##### \<template> 上的条件渲染

因为条件渲染必须依附于某个元素。但如果想切换的不止一个元素，可在 `<template>` 元素上使用条件渲染指令，这只是一个不可见的包装器元素，最后渲染的结果并不会包含这个 `<template>` 元素。

```html
<template v-if="ok">
    <h1>Title</h1>
    <p>Paragraph 1</p>
    <p>Paragraph 2</p>
</template>
```

  

#### v-for 循环列表渲染

`v-for` 指令的值需要使用 `item in items` 形式的特殊语法，其中 `items` 是源数据的数组，而 `item` 是迭代项的别名。

在 `v-for` 块中可以完整地访问父作用域内的属性和变量，即使是多层嵌套最内层也能访问到最顶层的内容。

`v-for` 也支持使用可选的第二个参数表示当前项的位置索引。

```html
<template>
  <ul>
    <li v-for="(item, index) in items">{{ index }} - {{ item.name }}</li>
  </ul>
</template>

<script setup>
  import { reactive } from 'vue'
  let items = reactive([{ name: 'Item 1' }, { name: 'Item 2' }])
</script>
```

也可以使用 `of` 作为分隔符来替代 `in`，这更接近 JavaScript 的迭代器语法：

```html
<div v-for="item of items"></div>
```

可以在 `v-for` 中使用解构语法，更简洁的提取内容：

```html
<div v-for="({ message }, index) in items" :key="index">
  {{ message }} {{ index }}
</div>
```

可以使用 `v-for` 来遍历一个对象的所有属性。遍历的顺序会基于对该对象调用 `Object.values()` 的返回值来决定。

可以通过提供第二个参数表示属性名 (例如 key)。第三个参数表示位置索引。

```html
<script setup>
import { reactive } from 'vue'
const myObject = reactive({
  title: 'How to do lists in Vue',
  author: 'Jane Doe',
  publishedAt: '2016-04-10'
})
</script>

<template>
    <div v-for="(value, key, index) in myObject">
      {{ index }}. {{ key }}: {{ value }}
    </div>
</template>
```

`v-for` 可以直接接受一个整数值。在这种用例中，会将该模板基于 `1...n` 的取值范围重复多次（初始值为1）：

```html
<span v-for="n in 10">{{ n }}</span>
```

与模板上的 v-if 类似，你也可以在 <template> 标签上使用 v-for 来渲染一个包含多个元素的块。

##### v-for 与 v-if

当它们同时存在于一个节点上时，`v-if` 比 `v-for` 的优先级更高。这意味着 `v-if` 的条件将无法访问到 `v-for` 作用域内定义的变量别名。

在外先包装一层 `<template>` 再在其上使用 `v-for` 可以解决这个问题 (这也更加明显易读)：

```html
<template v-for="todo in todos">
    <li v-if="!todo.isComplete">
        {{ todo.name }}
    </li>
</template>
```

##### 通过 key 管理状态

Vue 默认按照“**就地更新**”的策略来更新通过 `v-for` 渲染的元素列表。当数据项的顺序改变时，Vue 不会随之移动 DOM 元素的顺序，而是就地更新每个元素，确保它们在原本指定的索引位置上渲染。

为了给 Vue 一个提示，以便它可以跟踪每个节点的标识，从而重用和重新排序现有的元素，你需要为每个元素对应的块提供一个唯一的 `key` attribute：

```html
<div v-for="item in items" :key="item.id">
  <!-- 内容 -->
</div>
```

#### 数组变化侦测

Vue 能够侦听响应式数组的**变更方法**(调用它们的原数组进行变更)，并在它们被调用时触发相关的更新。包括：`push()`、`pop()`、`shift()`、`unshift()`、`splice()`、`sort()`、`reverse()`

相对地，也有一些**不可变方法**，例如 `filter()`，`concat()` 和 `slice()`，这些都不会更改原数组，而总是**返回一个新数组**。当遇到的是非变更方法时，我们需要将旧的数组替换为新的：

```js
let words = ref(['spray', 'elite', 'exuberant', 'destruction', 'present'])

words.value = words.value.filter(words.filter((word) => word.length > 6)
```

##### 展示过滤或排序后的结果

有时，我们希望显示数组经过过滤或排序后的内容，而不实际变更或重置原始数据。在这种情况下，你可以创建返回**已过滤或已排序数组的计算属性**。

在计算属性不可行的情况下 (例如在多层嵌套的 `v-for` 循环中)，你可以使用**创建函数**的方法：

```js
// 模板部分
<ul v-for="numbers in sets">
  <li v-for="n in even(numbers)">{{ n }}</li>
</ul>

// 代码部分
const sets = ref([
  [1, 2, 3, 4, 5],
  [6, 7, 8, 9, 10]
])

function even(numbers) {
  return numbers.filter((number) => number % 2 === 0)
}
```



#### v-text 与 v-html 插入文本或元素

`v-text` 指令用于更新元素的 textContent。`v-html` 指令用于更新元素的 innerHTML。
