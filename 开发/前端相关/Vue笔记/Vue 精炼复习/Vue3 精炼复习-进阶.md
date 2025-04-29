# Vue3 精炼复习 - 进阶篇

---

![Vue3 精炼复习 - 进阶篇](./assets/Vue3%20%E7%B2%BE%E7%82%BC%E5%A4%8D%E4%B9%A0%20-%20%E8%BF%9B%E9%98%B6%E7%AF%87.png)

**官方 API 文档**：https://cn.vuejs.org/api/options-state.html

## 深入组件

### 组件注册

一个 Vue 组件在使用前需要先被“注册”，这样 Vue 才能在渲染模板时找到其对应的实现。组件注册有两种方式：全局注册和局部注册。

- **全局注册**：使用 Vue 应用实例的 `.component()` 方法，让组件在当前 Vue 应用中全局可用。

  - `.component()` 方法可以被链式调用。
  - 全局注册的组件可以在此应用的任意组件的模板中使用。例如下例注册的组件，在其他地方直接输入 `<MyComponent1 />` 即可使用。

  ```js
  import MyComponent1 from './App1.vue'
  import MyComponent2 from './App1.vue'
  app.component('MyComponent1', MyComponent1)
  	.component('MyComponent2', MyComponent2)
  ```

- **局部注册**：局部注册的组件需要在使用它的父组件中显式导入，并且只能在该父组件中使用。

  - 局部注册的组件在后代组件中*不*可用。在这个例子中，`ComponentA` 注册后仅在当前组件可用。

  ```js
  <script setup>
  import ComponentA from './ComponentA.vue'
  </script>
  
  <template>
    <ComponentA />
  </template>
  ```

   

### Props

一个组件需要显式声明它所接受的 props，这样 Vue 才能知道外部传入的哪些是 props，哪些是透传 attribute。

#### defineProps

在 `<script setup>` 语法中，使用 `defineProps` 进行定义获取的 props。

- 数组形式：

  ```html
  <script setup>
      const props = defineProps(['foo'])
      console.log(props.foo)
  </script>

- 对象形式（运行时声明方式）：

  ```html
  <script setup lang="ts">
  const props = defineProps({
    // 基础类型检查
    title: String,
    
    // 多个可能的类型
    likes: [String, Number],
    
    // 必填项
    isPublished: {
      type: Boolean,
      required: true
    },
    
    // 带默认值
    commentIds: {
      type: Array,
      default: () => []
    },
    
    // 自定义验证函数
    author: {
      type: Object,
      default: () => ({}),
      validator: (value : {name:string,age:number}) => {
        return value.name.startsWith("user-") && value.age > 18
      }
    }
  })
  </script>

- 类型声明方式：

  ```html
  <script setup lang="ts">
  const props = defineProps<{
    title: string
    likes?: number
  }>()
  </script>
  ```

  ```ts
  <script setup lang="ts">
  import type { User } from './types'
  const props = defineProps<{
      user: User
  }>()
  </script>

- 使用 `withDefaults` ，与默认值结合

  ```html
  <script setup>
  interface Props {
    title: string
    likes?: number
    isPublished?: boolean
  }
  
  const props = withDefaults(defineProps<Props>(), {
    likes: 0,
    isPublished: false
  })
  </script>

#### 单向数据流

所有的 props 都遵循着**单向绑定**原则，props 因父组件的更新而变化，自然地将新的状态向下流往子组件，而不会逆向传递。

这避免了子组件意外修改父组件的状态的情况，不然应用的数据流将很容易变得混乱而难以理解。

```js
const props = defineProps(['foo'])
props.foo = 'bar' // ❌ 警告！prop 是只读的！
```

prop 被用于传入初始值；而子组件想在之后将其作为一个局部数据属性。在这种情况下，最好是新定义一个局部数据属性，从 props 上获取初始值即可：

```js
const props = defineProps(['initData'])
// 方法1，用 ref 包裹
const localData1 = ref(props.initData)
// 方法2，用计算属性进一步自动处理
const localData2 = computed(() => props.initData.trim().toLowerCase())
```

当`对象`或`数组`作为 props 被传入时，虽然子组件无法更改 props 绑定，但**仍然可以更改对象或数组内部的值**。这是因为 JavaScript 的对象和数组是按引用传递，对 Vue 来说，阻止这种更改需要付出的代价异常昂贵。

在最佳实践中，应该尽可能避免这样的更改，除非父子组件在设计上本来就需要紧密耦合。**在大多数场景下，子组件应该抛出一个事件来通知父组件做出改变**。

#### Boolean 类型转换

为了更贴近原生 boolean attributes 的行为，声明为 `Boolean` 类型的 props 有特别的类型转换规则。

> ```js
> // 子组件中接收参数
> defineProps({
>       disabled: Boolean
> })
> ```
>
> 该组件可以被这样使用：
>
> ```html
> <!-- 等同于传入 :disabled="true" -->
> <MyComponent disabled />
> 
> <!-- 等同于传入 :disabled="false" -->
> <MyComponent />
> ```

当一个 prop 被声明为允许多种类型时，`Boolean` 的转换规则也将被应用。然而，当同时允许 `String` 和 `Boolean` 时，有一种边缘情况——只有当 `Boolean` 出现在 `String` 之前时，`Boolean` 转换规则才适用：

```js
// disabled 将被转换为 true
defineProps({
  disabled: [Boolean, String]
})

// disabled 将被转换为 true
defineProps({
  disabled: [Number, Boolean]
})

// disabled 将被解析为空字符串 (disabled="")
defineProps({
  disabled: [String, Boolean]
})
```

   

### 组件事件

在基础篇有介绍过，用`$emit('someEvent')`或 `defineEmits` 获得的函数 抛出事件。父组件用 `v-on` 即可监听事件。

#### 事件参数

有时候我们会需要在触发事件时附带一个特定的值，相当于给事件监听器的处理方法传入参数。

> 举例来说，我们想要 `<BlogPost>` 组件来管理文本会缩放得多大。在这个场景下，我们可以给 `$emit` 提供一个额外的参数：
>
> ```html
> <!--MyButton.vue-->
> <button @click="$emit('increaseBy', 1)">
>   Increase by 1
> </button>
> ```
>
> 然后我们在父组件中监听事件，我们可以先简单写一个内联的箭头函数作为监听器，此函数会接收到事件附带的参数：
>
> ```html
> <!--App.vue-->
> <MyButton @increase-by="(n) => count += n" />
> ```

   

### 组件双向数据绑定

#### defineModel

子组件使用 `defineModel()`宏（3.4 版本后出现），父组件使用`v-model` 可以在组件上使用以实现双向绑定。

`defineModel()` 返回的值是一个 ref。它可以像其他 ref 一样被访问以及修改，不过它能起到在父组件和当前变量之间的双向绑定的作用：

```html
<!-- Parent.vue -->
<template>
<Child v-model="countModel" />
<template>
    
<script setup>
const countModel = ref(0)
</script>


<!-- Child.vue -->
<script setup>
const model = defineModel()
function update() {model.value++}
</script>

<template>
  <div>Parent bound v-model is: {{ model }}</div>
  <button @click="update">Increment</button>
</template>
```

组件上的 `v-model` 也可以接受一个参数。有了参数之后，就可以实现**多个** v-model 绑定。

```html
<!-- Parent.vue -->
<Child v-model:abc="userName" v-model:xyz="password"/>

<!-- Child.vue -->
<script setup>
    const username = defineModel('abc', { default: "user" })
    const password = defineModel('xyz')
</script>

<template>
    <input type="text" v-model="username" />
    <input type="password" v-model="password" />
</template>
```

#### 旧版本的双向绑定

在 3.4 版本之前，一般会按照如下的方式来实现子组件，正是 `defineModel` 展开的形式：

- 名为 `modelValue` 的 prop，本地 ref 的值与其同步；

- 名为 `update:modelValue` 的事件，当本地 ref 的值发生变更时触发。

  ```html
  <!-- Child.vue -->
  <script setup>
  const props = defineProps(['modelValue'])
  const emit = defineEmits(['update:modelValue'])
  </script>
  
  <template>
    <input
      :value="modelValue"
      @input="emit('update:modelValue',$event.target.value)"
    />
  </template>
  ```

- 然后，父组件中的 `v-model="foo"` 将被编译为：

  ```html
  <!-- Parent.vue -->
  <Child v-model="foo" />
  <!-- ↑编译为↓ -->
  <Child
    :modelValue="foo"
    @update:modelValue="$event => (foo = $event)"
  />
  ```

旧版本的接收参数写法如下：

```html
<!-- Parent.vue -->
<Child v-model:abc="userName" v-model:xyz="password"/>

<!-- Child.vue -->
<template>
  <div class="box">
    <input 
       type="text" 
       :value="abc" 
       @input="emit('update:abc',$event.target.value)"
    >
    <input 
       type="password" 
       :value="xyz" 
       @input="emit('update:xyz',$event.target.value)"
    >
  </div>
</template>

<script setup lang="ts">
  defineProps(['abc','xyz'])
  const emit = defineEmits(['update:abc','update:xyz'])
</script>
```

#### 自定义修饰符

`v-model` 有一些内置的修饰符，例如 `.trim`，`.number` 和 `.lazy`。在某些场景下可能要用到自定义组件的 `v-model` 支持自定义的修饰符。

> 示例：创建一个自定义的修饰符 `capitalize`，它会自动将 `v-model` 绑定输入的字符串值第一个字母转为大写：
>
> ```html
> <MyComponent v-model.capitalize="myText" />
> ```
>
> 解构 `defineModel()` 的返回值，可以在子组件中访问添加到组件 `v-model` 的修饰符。
>
> 为了能够基于修饰符选择性地调节值的读取和写入方式，我们可以给 `defineModel()` 传入 `get` 和 `set` 这两个选项。
>
> ```html
> <script setup>
> const [model, modifiers] = defineModel({
>   set(value) {
>     if (modifiers.capitalize) {
>       return value.charAt(0).toUpperCase() + value.slice(1)
>     }
>     return value
>   }
> })
> 
> console.log(modifiers) // { capitalize: true }
> </script>
> 
> <template>
>   <input type="text" v-model="model" />
> </template>
> ```

### 透传 Attributes

“透传 attribute”指的是传递给一个组件，却没有被该组件声明为 props 或 emits 的 attribute 或者 `v-on` 事件监听器。最常见的例子就是 `class`、`style` 和 `id`。

当一个组件以单个元素为根作渲染时，透传的 attribute 会自动被添加到根元素上。

如果一个子组件的根元素已经有了 `class` 或 `style` attribute，它会和从父组件上继承的值合并。

> 举例来说，假如我们有一个 `<MyButton>` 组件，它的模板长这样：
>
> ```html
> <!-- <MyButton> 的模板 -->
> <button class="btn">Click Me</button>
> ```
>
> 一个父组件使用了这个组件，并且传入了 `class`：
>
> ```html
> <MyButton class="large" />
> ```
>
> 最后渲染出的 DOM 结果是：
>
> ```html
> <button class="btn large">Click Me</button>
> ```

#### Attributes 继承禁用 与 访问

如果不想要一个组件自动地继承 attribute，可以在组件选项中设置 `inheritAttrs: false`。

从 3.3 开始也可以直接在 `<script setup>` 中使用 [`defineOptions`](https://cn.vuejs.org/api/sfc-script-setup.html#defineoptions)：

```html
<script setup>
    defineOptions({
        inheritAttrs: false
    })
</script>
```

最常见的需要禁用 attribute 继承的场景就是 attribute 需要应用在根节点以外的其他元素上。

这些透传进来的 attribute 可以在模板的表达式中直接用 `$attrs` 访问到。

这个 `$attrs` 对象包含了除组件所声明的 `props` 和 `emits` 之外的所有其他 attribute，例如 `class`**，**`style`**，**`v-on` 监听器等等。

```html
<span>Fallthrough attribute: {{$attrs }}</span>
```

之后，可以通过 `v-bind` 将透传的 attribute 应用在内部的元素上：

```html
<div class="btn-wrapper">
  <button class="btn" v-bind="$attrs">Click Me</button>
</div>
```

#### 多根节点的 Attributes 继承

和单根节点组件有所不同，有着**多个根节点的组件没有自动 attribute 透传行为**。

如果 `$attrs` 没有被显式绑定，将会抛出一个运行时警告。

#### 在 JavaScript 中访问透传 Attributes

如果需要，可以在 `<script setup>` 中使用 `useAttrs()` API 来访问一个组件的所有透传 attribute：

```html
<script setup>
import { useAttrs } from 'vue'

const attrs = useAttrs()
</script>
```

   

### 插槽 slot

![插槽图示](https://cn.vuejs.org/assets/slots.CKcE8XYd.png)

插槽内容可以访问到父组件的数据作用域，因为插槽内容本身是在父组件模板中定义的。

插槽内容可以是任意合法的模板内容，不局限于文本。通过使用插槽，组件更加灵活和具有可复用性。现在组件可以用在不同的地方渲染各异的内容，但同时还保证都具有相同的样式。

```html
<!-- 父组件 App.vue -->
<FancyButton>{{ message }}</FancyButton>

<!-- 子组件 FancyButton.vue -->
<template>
    <button>
		<slot></slot>
    </button>
</template>
```

#### 默认内容

在外部没有提供任何内容的情况下，可以在<slot>内为插槽指定默认内容。

``` html
<button type="submit">
  <slot>
    Submit <!-- 默认内容 -->
  </slot>
</button>
```

#### 具名插槽

带 `name` 的插槽被称为具名插槽 (named slots)。没有提供 `name` 的 `<slot>` 出口会隐式地命名为“default”。

要为具名插槽传入内容，我们需要使用一个含 `v-slot` 指令的 `<template>` 元素，并将目标插槽的名字传给该指令。

`v-slot` 有对应的简写 `#`。`<template v-slot:header>` 可以简写为 `<template #header>`。

```html
<!-- 父组件模板部分 App.vue -->
<BaseLayout>
    <template #header>
        <h1>Here might be a page title</h1>
    </template>

    <template v-slot:default>
        <p>A paragraph for the main content.</p>
    </template>

    <template #footer>
        <p>Here's some contact info</p>
    </template>
</BaseLayout>

<!-- 子组件 FancyButton.vue -->
<div class="container">
    <header>
        <slot name="header"></slot>
    </header>
    <main>
        <slot></slot>
    </main>
    <footer>
        <slot name="footer"></slot>
    </footer>
</div>
```

#### 条件插槽

有时需要根据插槽是否存在来渲染某些内容。

可以结合使用 [$slots](https://cn.vuejs.org/api/component-instance.html#slots) 属性与 `v-if` 来实现。

```HTML
<template>
    <div class="card">
        <div v-if="$slots.header" class="card-header">
            <slot name="header" />
        </div>

        <div v-if="$slots.default" class="card-content">
            <slot />
        </div>

        <div v-if="$slots.footer" class="card-footer">
            <slot name="footer" />
        </div>
    </div>
</template>
```

#### 动态插槽名

动态指令参数在 `v-slot` 上也是有效的。

```HTML
<base-layout>
  <template v-slot:[dynamicSlotName]>
    ...
  </template>
</base-layout>
```

#### 作用域插槽

在某些场景下插槽的内容可能想要同时使用父组件域内和子组件域内的数据，Vue3的插槽提供了这样一个作用域，让父子之间实现部分数据通信。

当然，此处也可以使用具名插槽。

- 在子组件中，`<slot>`内使用 `v-bind` 可将选择的数据传入作用域。
- 在父组件中，通过子组件标签上的 `v-slot` 指令，从子组件接收插槽 props 对象：

```html
<!-- 子组件 UserList.vue -->
<template>
  <ul>
    <li v-for="(user, index) in users" :key="user.id">
      <!-- 将用户数据通过slot传递给父组件 -->
      <slot :user="user" :index="index" name="default">
        默认显示: {{ user.name }}
      </slot>
    </li>
  </ul>
</template>

<script setup>
const users = [
  { id: 1, name: '张三', age: 25 },
  { id: 2, name: '李四', age: 30 },
  { id: 3, name: '王五', age: 28 }
]
</script>


<!-- 父组件模板部分 App.vue -->
<template>
    <UserList>
      <!-- 接收子组件传递的数据 -->
      <template v-slot:default="slotProps">
        <!-- 自定义显示方式 -->
        {{ slotProps.index + 10 }}. {{ slotProps.user.name }} ({{ slotProps.user.age }}岁)
      </template>
    </UserList>
</template>

<script setup>
import UserList from './UserList.vue'
</script>
```

#### 无渲染组件

**无渲染组件(Renderless Components)**是 Vue 中一种特殊的设计模式，它专注于逻辑处理而不直接渲染任何 DOM 元素，将渲染控制权完全交给使用它的父组件。

无渲染组件不包含模板，或者只包含一个简单的 `<slot>` 标签。

```html
<!-- 父组件：导入一个追踪当前鼠标位置逻辑的组件 -->
<script setup>
  import MouseTracker from './MouseTracker.vue'
</script>

<template>
  <MouseTracker v-slot="{ x, y }">
    Mouse is at: {{ x }}, {{ y }}
  </MouseTracker>
</template>


<!-- 子组件 MouseTracker.vue -->
<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
const x = ref(0), y = ref(0)

const update = e => {
  [x.value, y.value] = [e.pageX, e.pageY]
}

onMounted(() => window.addEventListener('mousemove', update))
onUnmounted(() => window.removeEventListener('mousemove', update))
</script>

<template>
  <slot :x="x" :y="y"/>
</template>
```



### 依赖注入

#### Prop 逐级透传问题

从父组件向子组件传递数据时，通常使用 props。若存在多层级嵌套的组件，而某个深层的子组件需要一个较远的祖先组件中的部分数据，仅用 props 则必须将其沿着组件链逐级传递下去，非常麻烦。

如下图， `<Footer>` 组件为了使 `<DeepChild>` 能访问到所需数据，需对数据进行定义并向下传递，即使`<Footer>`本身根本用不到。如果组件链路非常长，可能会影响到更多这条路上的组件。这一问题被称为**prop 逐级透传**，显然是需要尽量避免的情况。

![Prop 逐级透传的过程图示](https://cn.vuejs.org/assets/prop-drilling.XJXa8UE-.png)

#### Provide 与 Inject

`provide` 和 `inject` 通信（即依赖注入）是`逐级透传问题`的解决方法之一。数据通过 `provide()`函数发出，在另一处通过 `inject()` 函数接收。

注意，`inject` 会查找组件树上游的 `provide`，即使用`provide()`的组件必须是使用 `inject()`的组件的祖先组件。`provide`/`inject` 的设计是**单向的父传子**（自上而下）的数据流，无法反向传递或跨分支传递。

- `provide()` 函数接收两个参数：
  1. 第一个参数：定义注入名，可以是一个字符串或是一个 `Symbol`值，接收数据的组件会用注入名来查找期望注入的值。
  2. 第二个参数：提供的数据，数据可以是任意类型，包括响应式的状态。提供的响应式状态使后代组件可以由此和提供者建立响应式的联系。
- `inject()`函数接收三个参数：
  1. 第一个参数：传入的注入名，该注入名的确没有任何组件提供，则会抛出一个运行时警告。
  2. 第二个参数：默认值，若没有获取到提供的数据，则使用该值。

```js
<!-- 祖先组件的script内 App.vue -->
provide('message', '传入的数据')

<!-- 子组件的script内 Child.vue -->
const message = inject('message', '默认值')
```

默认值可能需要通过调用一个函数或初始化一个类来取得。为了避免在用不到默认值的情况下进行不必要的计算或产生副作用，我们可以使用工厂函数来创建默认值（*第三个参数表示默认值应该被当作一个工厂函数*）：

```js
const value = inject('key', () => new ExpensiveClass(), true)
```

#### 响应式最佳实践

**建议尽可能将任何对响应式状态的变更都保持在供给方组件中**，这样可以确保所提供状态的声明和变更操作都内聚在同一个组件内，使其更容易维护。

有时可能需在注入方组件中更改数据，推荐在供给方组件内声明并提供一个`更改数据的方法函数`，并用 `readonly()` 来包装值，确保提供的数据不能被注入方的组件更改：

```html
<!-- 在供给方组件内 -->
<script setup>
  import { provide, ref, readonly } from 'vue'
  const location = ref('North Pole')
  function updateLocation() {
    location.value = 'South Pole'
  }

  provide('location', {readonly(location), updateLocation})
</script>


<!-- 在注入方组件 -->
<script setup>
import { inject } from 'vue'
const { location, updateLocation } = inject('location')
</script>

<template>
  <button @click="updateLocation">{{ location }}</button>
</template>
```

#### 使用 Symbol 作注入名

但如果正在构建大型的应用，包含非常多的依赖提供，或者正在编写提供给其他开发者使用的组件库，建议最好使用 Symbol 来作为注入名以避免潜在的冲突。

推荐在一个单独的文件中导出这些注入名 Symbol：

```js
// keys.js
export const myInjectionKey = Symbol()


// 在供给方组件中
import { myInjectionKey } from './keys.js'
provide(myInjectionKey, {/*要提供的数据*/});


// 注入方组件
import { myInjectionKey } from './keys.js'
const injected = inject(myInjectionKey)
```



### 异步组件

在大型项目中，我们可能需要拆分应用为更小的块，并仅在需要时再从服务器加载相关组件。Vue 提供了 `defineAsyncComponent` 方法来实现此功能。

`defineAsyncComponent` 方法接收一个返回 Promise 的加载函数。这个 Promise 的 `resolve` 回调方法应该在从服务器获得组件定义时调用。也可以调用 `reject(reason)` 表明加载失败。

```js
import { defineAsyncComponent } from 'vue'

const AsyncComp = defineAsyncComponent(() => {
  return new Promise((resolve, reject) => {
    // ...从服务器获取组件
    resolve(/* 获取到的组件 */)
  })
})

// ... 像使用其他一般组件一样使用 `<AsyncComp />`
```

ES 模块动态导入 也会返回一个 Promise，所以多数情况下我们会将它和 `defineAsyncComponent` 搭配使用。

```js
import { defineAsyncComponent } from 'vue'

const AsyncComp = defineAsyncComponent(() =>
  import('./components/MyComponent.vue')
)
```

最后得到的 `AsyncComp` 是一个外层包装过的组件，仅在页面需要它渲染时才会调用加载内部实际组件的函数。

#### 加载与错误状态

异步操作不可避免地会涉及到加载和错误状态，因此 `defineAsyncComponent()` 也支持在高级选项中处理这些状态：

```js
const AsyncComp = defineAsyncComponent({
  loader: () => import('./Foo.vue'), // 加载函数
  loadingComponent: LoadingComponent, // 加载异步组件时使用的组件
  delay: 200,						// 展示加载组件前的延迟时间，默认为 200ms
  errorComponent: ErrorComponent,	// 加载失败后展示的组件
  // 如果提供了一个 timeout 时间限制，并超时了
  // 也会显示这里配置的报错组件，默认值是：Infinity
  timeout: 3000
})
```

如果提供了一个加载组件，它将在内部组件加载时先行显示。在加载组件显示之前有一个默认的 200ms 延迟——这是因为在网络状况较好时，加载完成得很快，加载组件和最终组件之间的替换太快可能产生闪烁，反而影响用户感受。

如果提供了一个报错组件，则它会在加载器函数返回的 Promise 抛错时被渲染。你还可以指定一个超时时间，在请求耗时超过指定时间时也会渲染报错组件。

### 动态组件

`<component>` 是 Vue 提供的一个特殊组件，用于动态渲染不同的组件，是实现组件动态切换的核心功能。

```html
<template>
  <button @click="currentComponent = 'Home'">Home</button>
  <button @click="currentComponent = 'About'">About</button>
  
  <component :is="currentComponent" />
</template>

<script setup>
import { ref } from 'vue'
import Home from './Home.vue'
import About from './About.vue'

const currentComponent = ref('Home')
</script>
```

   

## 组件通信

<img src="https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202410271405437.png" alt="image-20241027140511350" style="zoom:67%;" />

### prop

`props`是使用频率最高的一种通信方式，常用与 ：**父 ↔ 子**。

- 若 **父传子**：属性值是**非函数**。
- 若 **子传父**：属性值是**函数**。

   

### 自定义事件

自定义事件常用于：**子 => 父**。

   

### v-model

实现 **父↔子** 之间相互通信。

   

### $attrs

`$attrs`用于实现当前组件的父组件，向当前组件的子孙组件通信（**祖→孙**）。

用`v-bind="$attrs"`的方式，一层一层实现 `属性透传`。

   

### provide、inject

实现祖孙组件直接通信

   

### pinia

pinia也可以作为通信方式，之后的路由与Pinia篇会讲解。

   

### slot 插槽

通过父子间创建的 slot 作用域进行通信。

   

### \$parent 和 \$refs

- `$refs`用于 ：**父→子。**
- `$parent`用于：**子→父。**
- 数据都需要主动暴露才能被访问：`defineExpose()`

```html
<!--父组件 -->
<template>
	<div>
		<h1>父组件数据：{{ parentData }}</h1>
		<button @click="changeChild1">修改Child1</button>
		<button @click="changeChild2">修改Child2</button>
		<button @click="changeAllChild($refs)">修改全部Child</button>
		<Child ref="c1"/>
		<Child ref="c2"/>
	</div>
</template>

<script setup>
	import Child from './Child.vue'
	const c1 = useTemplateRef("c1"), c2 = useTemplateRef("c2")
	const parentData = ref(1)
	function changeChild1(){c1.value.childData += 1}
	function changeChild2(){c2.value.childData += 1}
	function changeAllChild(refs:{[key:string]:any}){
		for (let key in refs){
			refs[key].childData += 3
		}
	}
	defineExpose({parentData}) // 向外部提供数据
</script>


<!--子组件 Child.vue-->
<template>
  <div>
    <h2>子组件数据：{{ parentData }}</h2>
    <button @click="minus($parent)">修改Parent</button>
  </div>
</template>

<script setup>
	const childData = ref(3)
	function minus(parent:any){parent.parentData -= 1}
	defineExpose({childData}) // 把数据交给外部
</script>
```

   

### mitt

与依赖注入（`inject，provide`）功能类似，但这个没有限制，可以实现任意组件间通信。

这是一个第三方库，使用前，需进行安装：`npm i mitt`

1. 新建文件：`src\utils\emitter.ts`

   ```js
   import mitt from "mitt"; // 引入mitt 
   
   const emitter = mitt() // 创建emitter
   
   export default emitter // 暴露mitt

2. 接收数据的组件中：绑定事件、同时在销毁前解绑事件：

   ```JS
   import emitter from "@/utils/emitter";
   import { onUnmounted } from "vue";
   
   // 绑定事件
   emitter.on('send-toy',(value)=>{
     console.log('send-toy事件被触发',value)
   })
   
   onUnmounted(()=>{
     emitter.off('send-toy') // 解绑事件
   })

3. 提供数据的组件，在合适的时候触发事件：

   ```JS
   import emitter from "@/utils/emitter";
   
   function sendToy(){
     emitter.emit('send-toy', toy.value) // 触发事件
   }
   
   

## 内置组件

### Transition

`<Transition>` 是一个内置组件，无需注册即可使用。可以通过默认插槽传递给`<Transition>`传递组件或元素，该元素或组件进入和离开 DOM 时`<Transition>`会给其应用动画。

进入或离开可以由以下的条件之一触发：

- 由 `v-if`或`v-show`触发的切换。
- 由特殊元素 `<component>` 切换的动态组件。
- 改变特殊的 `key` 属性。

当一个 `<Transition>` 组件中的元素被插入或移除时，会发生下面这些事情：

1. Vue 会自动检测目标元素是否应用了 CSS 过渡或动画。如果是，则一些 `CSS 过渡 class` 会在适当的时机被添加和移除。
2. 如果有作为监听器的 `JavaScript hook`，这些钩子函数会在适当时机被调用。
3. 如果没有探测到 CSS 过渡或动画、也没有提供 JavaScript 钩子，那么 DOM 的插入、删除操作将在浏览器的下一个动画帧后执行。

#### 基于 CSS 的过渡效果

一共有 6 个应用于进入与离开过渡效果的 CSS class。

1. `v-enter-from`：进入动画的起始状态。在元素插入之前添加，在元素插入完成后的下一帧移除。
2. `v-enter-active`：进入动画的生效状态。应用于整个进入动画阶段。在元素被插入之前添加，在过渡或动画完成之后移除。这个 class 可以被用来定义进入动画的持续时间、延迟与速度曲线类型。
3. `v-enter-to`：进入动画的结束状态。在元素插入完成后的下一帧被添加 (也就是 `v-enter-from` 被移除的同时)，在过渡或动画完成之后移除。
4. `v-leave-from`：离开动画的起始状态。在离开过渡效果被触发时立即添加，在一帧后被移除。
5. `v-leave-active`：离开动画的生效状态。应用于整个离开动画阶段。在离开过渡效果被触发时立即添加，在过渡或动画完成之后移除。这个 class 可以被用来定义离开动画的持续时间、延迟与速度曲线类型。
6. `v-leave-to`：离开动画的结束状态。在一个离开动画被触发后的下一帧被添加 (也就是 `v-leave-from` 被移除的同时)，在过渡或动画完成之后移除。

![过渡图示](https://cn.vuejs.org/assets/transition-classes.DYG5-69l.png)

##### 过渡效果名

我们可以给 `<Transition>` 组件传一个 `name` prop 来声明一个**过渡效果名**。

对于一个有名字的过渡效果，起作用的过渡 class 会不再以 `v` 作为前缀，而是用其名字作为前缀。如下例，即用 `name="fade"` 中定义的 `fade` 作为前缀。

```html
<template>
  <Transition name="fade">
    <!--...-->
  </Transition>
</template>


<style scoped>
.fade-enter-active{
  transition: opacity 0.5s ease;
}
.fade-leave-active {
  animation: bounce-in 0.5s reverse;
}
.fade-enter-from,
.fade-leave-to {
  transform: translateX(20px);
  opacity: 0;
}

@keyframes bounce-in {
  0% {transform: scale(0);}
  50% {transform: scale(1.25);}
  100% {transform: scale(1);}
}
</style>
```

如上例，`<Transition>` 一般都会搭配原生 CSS 过渡（`transition` 属性）和 CSS 动画（`animation` 属性）一起使用。

##### 同时使用 transition 和 animation

然而在某些场景中，你或许想要在同一个元素上同时使用它们两个。

举例来说，Vue 触发了一个 CSS 动画，同时鼠标悬停触发另一个 CSS 过渡。此时需显式传入 `type` 来告诉 `<Transition>` 需要关心哪种类型，传入的值是 `animation` 或 `transition`：

```html
<Transition type="animation">...</Transition>
```

##### 完全自定义每个阶段的类名

也可以向 `<Transition>` 传递以下的 props 来指定自定义的过渡 class：

- `enter-from-class`
- `enter-active-class`
- `enter-to-class`
- `leave-from-class`
- `leave-active-class`
- `leave-to-class`

传入的这些 class 会覆盖相应阶段的默认 class 名。这个功能在你想要在 Vue 的动画机制下集成其他的第三方 CSS 动画库时非常有用。

```html
<Transition
  enter-from-class="custom-enter-from"
  enter-active-class="custom-enter-active"
  enter-to-class="custom-enter-to"
  leave-from-class="custom-leave-from"
  leave-active-class="custom-leave-active"
  leave-to-class="custom-leave-to"
>
  <!-- 你的内容 -->
</Transition>
```

##### 深层级过渡与显式过渡时长

尽管过渡 class 仅能应用在 `<Transition>` 的直接子元素上，我们还是可以使用深层级的 CSS 选择器，在深层级的元素上触发过渡效果。

然而，这会带来一个小问题。默认情况下，`<Transition>` 组件会通过监听过渡根元素上的第一个 `transitionend` 或者 `animationend` 事件来尝试自动判断过渡何时结束。而在嵌套的过渡中，期望的行为应该是等待所有内部元素的过渡完成。

在这种情况下，你可以通过向 `<Transition>` 组件传入 `duration` prop 来显式指定过渡的持续时间 (以毫秒为单位)。

```html
<Transition :duration="550">...</Transition>
```

```html
<template>
  <!-- 通过子模板内的时间计算 0.25 + 0.3 = 0.55s -->
  <Transition name="nested" :duration="550">
    <div v-if="show" class="outer">
      <div class="inner">
        Hello
      </div>
    </div>
  </Transition>
</template>

<style>
/* ... 省略了其他必要的 CSS */

/* 应用于嵌套元素的规则 */
.nested-enter-active .inner {
  transition-delay: 0.25s; /* 延迟嵌套元素的进入以获得交错效果 */
  transition: all 0.3s ease-in-out;
}
.nested-leave-active .inner {
  transition: all 0.3s ease-in-out;
}
.nested-enter-from .inner,
.nested-leave-to .inner {
  transform: translateX(30px);
  opacity: 0;
}
</style>
```

##### 性能考量

`<Transition>`用到的 CSS 属性大多是 `transform` 和 `opacity` 之类，这些属性制作动画非常高效，在动画过程中不会影响到 DOM 结构，因此不会每一帧都触发昂贵的 CSS 布局重新计算，且大多数的现代浏览器都可以在执行 `transform` 动画时利用 GPU 进行硬件加速。

相比之下，像 `height` 或者 `margin` 这样的属性会触发 CSS 布局变动，因此执行它们的动画效果更昂贵，需要谨慎使用。

   

#### JavaScript 钩子的过渡效果

可以通过监听 `<Transition>` 组件事件的方式在过渡过程中挂上钩子函数，这些钩子可以与 CSS 过渡或动画结合使用，也可以单独使用。

```html
<Transition
  @before-enter="onBeforeEnter"
  @enter="onEnter"
  @after-enter="onAfterEnter"
  @enter-cancelled="onEnterCancelled"
  @before-leave="onBeforeLeave"
  @leave="onLeave"
  @after-leave="onAfterLeave"
  @leave-cancelled="onLeaveCancelled"
>
  <!-- ... -->
</Transition>


<script setup>
// 在元素被插入到 DOM 之前被调用
// 用这个来设置元素的 "enter-from" 状态
function onBeforeEnter(el) {}

// 在元素被插入到 DOM 之后的下一帧被调用
// 用这个来开始进入动画
function onEnter(el, done) {
  // 调用回调函数 done 表示过渡结束
  // 如果与 CSS 结合使用，则这个回调是可选参数
  done()
}

// 当进入过渡完成时调用。
function onAfterEnter(el) {}

// 当进入过渡在完成之前被取消时调用
function onEnterCancelled(el) {}

// 在 leave 钩子之前调用
// 大多数时候，你应该只会用到 leave 钩子
function onBeforeLeave(el) {}

// 在离开过渡开始时调用
// 用这个来开始离开动画
function onLeave(el, done) {
  // 调用回调函数 done 表示过渡结束
  // 如果与 CSS 结合使用，则这个回调是可选参数
  done()
}

// 在离开过渡完成、
// 且元素已从 DOM 中移除时调用
function onAfterLeave(el) {}

// 仅在 v-show 过渡中可用
function onLeaveCancelled(el) {}
</script>
```

在使用仅由 JavaScript 执行的动画时，最好是添加一个 `:css="false"` prop。这显式地向 Vue 表明可以跳过对 CSS 过渡的自动探测。除了性能稍好一些之外，还可以防止 CSS 规则意外地干扰过渡效果。

```html
<Transition :css="false">
  ...
</Transition>
```

Javascript 钩子可以配合动画库执行动画，比如 [GSAP 库](https://gsap.com/)、 [Anime.js](https://animejs.com/) 或者 [Motion One](https://motion.dev/) 。

#### 封装为可复用的过渡组件

要创建一个可被复用的过渡，我们需要为 `<Transition>` 组件创建一个包装组件，并向内传入插槽内容：

```html
<!-- MyTransition.vue -->
<template>
  <!-- 包装内置的 Transition 组件 -->
  <Transition name="my-transition">
    <slot></slot> <!-- 向内传递插槽内容 -->
  </Transition>
</template>

<style>
/* 注意：避免在这里使用 <style scoped>，因为那不会应用到插槽内容上 */
</style>
```

#### 初次渲染时过渡

如果你想在某个节点初次渲染时应用一个过渡效果，你可以添加 `appear` prop：

```html
<Transition appear>
  ...
</Transition>
```

#### 过渡模式

如果进入和离开的元素都是在同时开始动画的，容易出现布局问题。因此不得不将它们设为 `position: absolute`来避免布局问题发生，但是有时候我们不想设置这个属性。

我们可能想先执行离开动画，然后在其完成**之后**再执行元素的进入动画。手动编排这样的动画是非常复杂的，好在我们可以通过向 `<Transition>` 传入一个 `mode` prop 来实现这个行为：

```html
<Transition mode="out-in">
  ...
</Transition>
```

`<Transition>` 也支持 `mode="in-out"`，虽然这并不常用。

#### 使用 Key Attribute 过渡

有时为了触发过渡，你需要强制重新渲染 DOM 元素。

以计数器组件为例：

```html
<script setup>
import { ref } from 'vue';
const count = ref(0);
setInterval(() => count.value++, 1000);
</script>

<template>
  <Transition>
    <span :key="count">{{ count }}</span>
  </Transition>
</template>
```

如果不使用 `key` attribute，则只有文本节点会被更新，因此不会发生过渡。但是，有了 `key` 属性，Vue 就知道在 `count` 改变时创建一个新的 `span` 元素，因此 `Transition` 组件有两个不同的元素在它们之间进行过渡。

   

### TransitionGroup

`<TransitionGroup>` 会在一个 `v-for` 列表中的元素或组件被插入，移动，或移除时应用动画。

`<TransitionGroup>` 支持和 `<Transition>` 基本相同的 props、CSS 过渡 class 和 JavaScript 钩子监听器，但有以下几点区别：

- 默认情况下，它不会渲染一个容器元素。但你可以通过传入 `tag` prop 来指定一个元素作为容器元素来渲染。
- 过渡模式在这里不可用，因为我们不再是在互斥的元素之间进行切换。
- 列表中的每个元素都**必须**有一个独一无二的 `key` attribute。
- CSS 过渡 class 会被应用在列表内的元素上，**而不是**容器元素上。

这里是 `<TransitionGroup>` 对一个 `v-for` 列表添加进入 / 离开动画的示例：

```html
<TransitionGroup name="list" tag="ul">
  <li v-for="item in items" :key="item">
    {{ item }}
  </li>
</TransitionGroup>


<style>
.list-enter-active,
.list-leave-active {
  transition: all 0.5s ease;
}
.list-enter-from,
.list-leave-to {
  opacity: 0;
  transform: translateX(30px);
}
</style>
```

上面的示例有一些明显的缺陷：当某一项被插入或移除时，它周围的元素会立即发生“**跳跃**”而不是平稳地移动。我们可以通过添加一些额外的 CSS 规则来解决这个问题：

```CSS
.list-move, /* 对移动中的元素应用的过渡 */
.list-enter-active,
.list-leave-active {
  transition: all 0.5s ease;
}

.list-enter-from,
.list-leave-to {
  opacity: 0;
  transform: translateX(30px);
}

/* 确保将离开的元素从布局流中删除
  以便能够正确地计算移动的动画。 */
.list-leave-active {
  position: absolute;
}
```

   

### KeepAlive

默认情况下，一个组件实例在被替换掉后会被销毁。这会导致它丢失其中所有已变化的状态——当这个组件再一次被显示时，会创建一个只带有初始状态的新实例。

要想组件能在被“切走”的时候保留它们的状态，可以用 `<KeepAlive>` 内置组件将这些动态组件包装起来：

```HTML
<!-- 非活跃的组件将会被缓存！ -->
<KeepAlive>
  <component :is="activeComponent" />
</KeepAlive>
```

#### 包含/排除

`<KeepAlive>` 默认会缓存内部的所有组件实例，但我们可以通过 `include` 和 `exclude` prop 来定制该行为。这两个 prop 的值都可以是一个以英文逗号分隔的字符串、一个正则表达式，或是包含这两种类型的一个数组：

```html
<template>
	<!-- 以英文逗号分隔的字符串 -->
    <KeepAlive include="Home,User">
      <component :is="view" />
    </KeepAlive>

    <!-- 正则表达式 (需使用 `v-bind`) -->
    <KeepAlive :include="/Home|User/">
      <component :is="view" />
    </KeepAlive>

    <!-- 数组 (需使用 `v-bind`) -->
    <KeepAlive :include="['Home', 'User']">
      <component :is="view" />
    </KeepAlive>
</template>

<script setup>
import SFC1 from './Home.vue'
import SFC2 from './User.vue'
</script>
```

它会根据组件的 `name` 选项进行匹配，所以组件如果想要条件性地被 `KeepAlive` 缓存，就必须显式声明一个 `name` 选项。

在 3.2.34 或以上的版本中，使用 `<script setup>` 的单文件组件会自动根据文件名生成对应的 `name` 选项，无需再手动声明。

#### 最大缓存实例数

我们可以通过传入 `max` prop 来限制可被缓存的最大组件实例数。`<KeepAlive>` 的行为在指定了 `max` 后类似一个 **LRU 缓存**：如果缓存的实例数量即将超过指定的那个最大数量，则最久没有被访问的缓存实例将被销毁，以便为新的实例腾出空间。

```html
<KeepAlive :max="10">
  <component :is="activeComponent" />
</KeepAlive>
```

#### 缓存实例的生命周期

当一个组件实例从 DOM 上移除但因为被 `<KeepAlive>` 缓存而仍作为组件树的一部分时，它将变为不活跃状态而不是被卸载。当一个组件实例作为缓存树的一部分插入到 DOM 中时，它将重新被激活。

一个持续存在的组件可以通过 `onActivated()` 和 `onDeactivated()` 注册相应的两个状态的生命周期钩子：

```html
<script setup>
import { onActivated, onDeactivated } from 'vue'

onActivated(() => {
  // 调用时机为首次挂载
  // 以及每次从缓存中被重新插入时
})

onDeactivated(() => {
  // 在从 DOM 上移除、进入缓存
  // 以及组件卸载时调用
})
</script>
```

- `onActivated` 在组件挂载时也会调用，并且 `onDeactivated` 在组件卸载时也会调用。
- 这两个钩子不仅适用于 `<KeepAlive>` 缓存的根组件，也适用于缓存树中的后代组件。

   

### Teleport

有时我们可能会遇到这样的场景：一个组件模板的一部分在逻辑上从属于该组件，但从整个应用视图的角度来看，它在 DOM 中应该被渲染在整个 Vue 应用外部的其他地方。

理想情况下，我们希望触发模态框的按钮和模态框本身是在同一个组件中，因为它们都与组件的开关状态有关。但这意味着该模态框将与按钮一起渲染在应用 DOM 结构里很深的地方。这会导致该模态框的 CSS 布局代码很难写。

`<Teleport>` 是一个内置组件，它可以将一个组件内部的一部分模板“传送”到该组件的 DOM 结构外层的位置去。

`<Teleport>` 接收一个 `to` prop 来指定传送的目标。`to` 的值可以是一个 CSS 选择器字符串，也可以是一个 DOM 元素对象。

> 示例，实现一个模态框：
>
> ```html
> <div class="outer">
>     <MyModal />
> </div>
> ```
>
> `<MyModal>` 的实现：
>
> ```html
> <script setup>
> import { ref } from 'vue'
> const open = ref(false)
> </script>
> 
> <template>
>   <button @click="open = true">Open Modal</button>
> 
>   <Teleport to="body">
>     <div v-if="open" class="modal">
>       <p>Hello from the modal!</p>
>       <button @click="open = false">Close</button>
>     </div>
>   </Teleport>
> </template>
> 
> <style scoped>
> .modal {
>   position: fixed;
>   z-index: 999;
>   top: 20%;
>   left: 50%;
>   width: 300px;
>   margin-left: -150px;
> }
> </style>
> ```
>
> 未使用`<Teleport>`时，会有一些潜在的问题：
>
> - `position: fixed` 能够相对于浏览器窗口放置有一个条件，那就是不能有任何祖先元素设置了 `transform`、`perspective` 或者 `filter` 样式属性。也就是说如果我们想要用 CSS `transform` 为祖先节点 `<div class="outer">` 设置动画，就会不小心破坏模态框的布局！
> - 这个模态框的 `z-index` 受限于它的容器元素。如果有其他元素与 `<div class="outer">` 重叠并有更高的 `z-index`，则它会覆盖住我们的模态框。

#### 禁用 Teleport

在某些场景下可能需要视情况禁用 `<Teleport>`。举例来说，我们想要在桌面端将一个组件当做浮层来渲染，但在移动端则当作行内组件。我们可以通过对 `<Teleport>` 动态地传入一个 `disabled` prop 来处理这两种不同情况。

```html
<Teleport :disabled="isMobile">
  ...
</Teleport>
```

这里的 `isMobile` 状态可以根据 CSS media query 的不同结果动态地更新。

#### 延迟解析的 Teleport

在 Vue 3.5 及更高版本中，我们可以使用 `defer` prop 推迟 Teleport 的目标解析，直到应用的其他部分挂载。这允许 Teleport 将由 Vue 渲染且位于组件树之后部分的容器元素作为目标：

```html
<Teleport defer to="#late-div">...</Teleport>

<!-- 稍后出现于模板中的某处 -->
<div id="late-div"></div>
```

请注意，目标元素必须与 Teleport 在同一个挂载/更新周期内渲染，即如果 `<div>` 在一秒后才挂载，Teleport 仍然会报错。延迟 Teleport 的原理与 `mounted` 生命周期钩子类似。

  

### Suspense

`<Suspense>` 是一个内置组件，用来在组件树中协调对异步依赖的处理。它让我们可以在组件树上层等待下层的多个嵌套异步依赖项解析完成，并可以在等待时渲染一个加载状态。

在这个组件树中可能有多个嵌套组件，要渲染出它们，首先得解析一些异步资源。如果没有 `<Suspense>`，则它们每个都需要处理自己的加载、报错和完成状态。在最坏的情况下，我们可能会在页面上看到三个旋转的加载态，在不同的时间显示出内容。

有了 `<Suspense>` 组件后，我们就可以在等待整个多层级组件树中的各个异步依赖获取结果时，在顶层展示出加载中或加载失败的状态。

#### 可被等待的异步依赖

`<Suspense>` 可以等待的异步依赖有：

1. 带有异步 `setup()` 的组件。

   ```JS
   export default {
     async setup() {
       //...
     }
   }
   ```

2. 使用 `<script setup>` 时有顶层 `await` 表达式的组件

   ```HTML
   <script setup>
   const res = await fetch(...)
   const posts = await res.json()
   </script>
   ```

3. 组件关系链上存在 `<Suspense>`即异步组件。异步组件也可以通过在选项中指定 `suspensible: false` 表明不用 `Suspense` 控制，并让组件始终自己控制其加载状态。

#### 加载中状态

`<Suspense>` 组件有两个插槽：`#default` 和 `#fallback`。两个插槽都只允许**一个**直接子节点。在可能的时候都将显示默认插槽中的节点。否则将显示后备插槽中的节点。

- ```html
  <!--单个异步依赖组件-->
  <template>
    <Suspense>
      <!-- 只能存在一个，将被放入默认插槽 -->
      <AsyncComponent />
      
      <template #fallback>
        <div>加载中，请稍候...</div>
      </template>
    </Suspense>
  </template>
  ```

- ```html
  <!--多个个异步依赖组件-->
  <template>
    <Suspense>
      <template #default>
        <!-- 具有深层异步依赖的组件 -->
        <AsyncComponent1 />
        <AsyncComponent2 />
      </template>
      
      <template #fallback>
        <div>加载中，请稍候...</div>
      </template>
    </Suspense>
  </template>

1. 初始渲染时，

   - 没有遇到异步依赖，`<Suspense>` 会直接进入完成状态。
   - `<Suspense>` 遇到任何异步依赖，则会进入**挂起**状态，展示的是`#fallback`插槽的后备内容。

2. 当所有遇到的异步依赖都完成后，`<Suspense>` 会进入**完成**状态，并将展示出默认插槽的内容。

3. 进入完成状态后，只有当`#default`插槽的根节点被替换时，`<Suspense>` 才会回到**挂起**状态。组件树中新的更深层次的异步依赖**不会**造成 `<Suspense>` 回退到挂起状态。

4. 发生回退时，`#fallback`插槽的内容不会立即展示出来。相反，`<Suspense>` 在等待新内容和异步依赖完成时，仍会展示之前 `#default` 插槽已经渲染完成的内容。

   - 这个行为可传入 `timeout` prop 进行配置：在等待渲染新内容耗时超过 `timeout` 之后，`<Suspense>` 将会切换为展示`#fallback`插槽的内容。

   - 若 `timeout` 值为 `0` 将导致在替换默认内容时立即显示后备内容。

     ```html
     <Suspense timeout=0> ... </Suspense>
     ```

#### 事件

`<Suspense>` 组件会触发三个事件：

1. `pending`：进入挂起状态时触发
2. `resolve`：在 `default` 插槽完成获取新内容时触发。
3. `fallback`：在 `fallback` 插槽的内容显示时触发。

例如，可以使用这些事件在加载新组件时在之前的 DOM 最上层显示一个加载指示器。

#### 错误处理

`<Suspense>` 组件自身目前还不提供错误处理，不过你可以使用 [`errorCaptured`](https://cn.vuejs.org/api/options-lifecycle.html#errorcaptured) 选项或者 [`onErrorCaptured()`](https://cn.vuejs.org/api/composition-api-lifecycle.html#onerrorcaptured) 钩子，在使用到 `<Suspense>` 的父组件中捕获和处理异步错误。

#### 嵌套使用

当我们有多个类似于下方的异步组件 (常见于嵌套或基于布局的路由) 时：

```html
<Suspense>
  <component :is="DynamicAsyncOuter">
    <component :is="DynamicAsyncInner" />
  </component>
</Suspense>
```

当我们更改 `DynamicAsyncOuter` 时，`<Suspense>` 会正确地等待它，但当我们更改 `DynamicAsyncInner` 时，嵌套的 `DynamicAsyncInner` 会呈现为一个空节点，直到它被解析为止 (而不是之前的节点或回退插槽)。

为了解决这个问题，我们可以使用嵌套的方法来处理嵌套组件的补丁，就像这样：

```html
<Suspense>
  <component :is="DynamicAsyncOuter">
    <Suspense suspensible> <!-- 像这样 -->
      <component :is="DynamicAsyncInner" />
    </Suspense>
  </component>
</Suspense>
```

如果你不设置 `suspensible` 属性，内部的 `<Suspense>` 将被父级 `<Suspense>` 视为同步组件。这意味着它将会有自己的回退插槽，如果两个 `Dynamic` 组件同时被修改，则当子 `<Suspense>` 加载其自己的依赖关系树时，可能会出现空节点和多个修补周期，这可能不是理想情况。设置后，所有异步依赖项处理都会交给父级 `<Suspense>` (包括发出的事件)，而内部 `<Suspense>` 仅充当依赖项解析和修补的另一个边界。

   

### 内置组件的嵌套顺序

将 `<Suspense>` 和 `<Transition>`、`<KeepAlive>` 等组件结合。要保证这些组件都能正常工作，嵌套的顺序非常重要。

另外，这些组件都通常与 Vue Router 中的 `<RouterView>` 组件结合使用。

```html
<RouterView v-slot="{ Component }">
  <template v-if="Component">
    <Transition mode="out-in">
      <KeepAlive>
        <Suspense>
          <!-- 主要内容 -->
          <component :is="Component"></component>

          <!-- 加载中状态 -->
          <template #fallback>
            正在加载...
          </template>
        </Suspense>
      </KeepAlive>
    </Transition>
  </template>
</RouterView>
```

Vue Router 使用动态导入对**懒加载组件**进行了内置支持。这些与异步组件不同，目前他们不会触发 `<Suspense>`。但是，它们仍然可以有异步组件作为后代，这些组件可以照常触发 `<Suspense>`。



## 其他重要 API

### customRef 自定义响应式

`customRef` 是 Vue 3 中提供的一个高级 API，用于创建自定义的 `ref`，允许开发者对依赖项的跟踪和更新触发进行精细控制。它特别适用于需要对数据变化进行细粒度控制的场景，例如防抖和节流效果。

#### 基本语法

`customRef` 的基本语法如下：

```js
const myRef = customRef((track, trigger) => {
  return {
    get() {
      // ...
    },
    set(newValue) {
      // ...
    }
  };
});
```

`customRef` 接受一个回调函数作为参数，该回调函数接收两个参数：

- `track`: 用于告诉 Vue 需要跟踪该 `ref` 的依赖。
- `trigger`: 用于通知 Vue 该 `ref` 的值发生了变化。

#### `get` 和 `set` 方法

- `get` 方法：在访问 `ref` 的值时调用，用于告诉 Vue 需要跟踪该值。
- `set` 方法：在设置 `ref` 的值时调用，用于通知 Vue 该值发生了变化，并执行自定义的更新逻辑。

> 示例：实现防抖效果
>
> 自定义 `ref` 实现防抖hook（`useDebouncedRef.ts`）：
>
> ```js
> import { customRef } from "vue";
> 
> export default function useDebouncedRef(initValue: string, delay: number) {
>   return customRef((track, trigger) => {
>     let timer: number;
>     let value = initValue;
> 
>     return {
>       get() {
>         track(); // 告诉 Vue 跟踪这个 ref 的依赖
>         return value;
>       },
>       set(newValue) {
>         clearTimeout(timer); // 清除之前的定时器
>         timer = setTimeout(() => {
>           value = newValue; // 更新值
>           trigger(); // 通知 Vue 这个 ref 的值发生了变化
>         }, delay); // 设置新的定时器
>       }
>     };
>   });
> }
> ```
>
> 在 Vue 组件中使用这个自定义的防抖 `ref`：
>
> ```html
> <template>
>   <div>
>     <input v-model="msg" placeholder="Type something...">
>     <p>Debounced Message: {{ msg }}</p>
>   </div>
> </template>
> 
> <script setup>
> import { ref } from 'vue';
> import useDebouncedRef from './useDebouncedRef';
> 
> const { msg } = useDebouncedRef('Hello', 1000); // 初始化值为 'Hello'，防抖延迟为 1000 毫秒
> </script>
> ```
>
> 1. `useDebouncedRef` 函数：
>    - 接收两个参数：`initValue`（初始值）和 `delay`（延迟时间，以毫秒为单位）。
>    - 返回一个使用 `customRef` 创建的自定义 `ref`。
> 2. `customRef` 回调函数：
>    - 接收两个参数：`track` 和 `trigger`。
>    - 定义 `get` 和 `set` 方法。
> 3. `get` 方法：
>    - 调用 `track()`，告诉 Vue 这个 `ref` 的值需要被依赖跟踪。
>    - 返回当前的值。
> 4. `set` 方法：
>    - 清除之前的定时器，以确保只有最后一次输入在延迟时间后被处理。
>    - 设置一个新的定时器，在延迟时间到期后更新 `value` 并调用 `trigger()` 通知 Vue 值发生了变化。
> 5. 组件中的使用：
>    - 在组件中，使用 `v-model` 双向绑定到 `msg`。
>    - 输入框中的值会通过防抖机制进行处理，只有在用户停止输入超过指定的延迟时间后，才会更新 `msg` 的值并重新渲染视图。