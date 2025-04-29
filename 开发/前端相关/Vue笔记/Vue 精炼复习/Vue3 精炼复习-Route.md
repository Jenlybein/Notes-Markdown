# Vue3 精炼复习 - Router 篇

---

![Vue3 精炼复习 - Router 篇](./assets/Vue3%20%E7%B2%BE%E7%82%BC%E5%A4%8D%E4%B9%A0%20-%20Router%20%E7%AF%87.png)

  前端的路由（Frontend Routing）是指在单页应用程序（Single Page Application，SPA）中，通过管理URL路径来控制不同页面或视图的展示，而不需要刷新整个页面。前端路由的核心在于利用JavaScript和浏览器的历史API来实现页面间的导航和内容切换，从而提供更快、更流畅的用户体验。

## 简单路由

如果只需要一个简单的页面路由，而不想为此引入一整个路由库，可以通过`动态组件`的方式，监听浏览器 `hashchange` 事件 或使用 `History API` 来更新当前组件。

```html
<!-- 监听 hashchange -->
<script setup>
import { ref, computed } from 'vue'
import Home from './components/Home.vue'
import Posts from './components/Posts.vue'
import NotFound from './components/NotFound.vue'

const routes = {
  '/': Home,
  '/posts': Posts
}

const currentPath = ref(window.location.hash)

window.addEventListener('hashchange', () => {
  currentPath.value = window.location.hash
})

const currentView = computed(() => {
  return routes[currentPath.value.slice(1) || '/'] || NotFound
})
</script>

<template>
  <a href="#/">Home</a> |
  <a href="#/posts">Posts</a> |
  <a href="#/non-existent-path">Broken Link</a>
  <component :is="currentView" />
</template>
```

## 安装与配置

> 路由组件通常存放在`pages` 或 `views`文件夹，一般组件通常存放在`components`文件夹。

1. 启动终端，输入：`npm install vue-router`，若启用 Typescript 则使用 `npm install @types/vue-router --save-dev`。

2. 在 src 中创建文件夹 `router` ，在里面创建文件 `index.ts` 

3. 在 `index.ts` 内，调用 `createRouter()` 函数创建路由器实例，并 export 。

   ```js
   import {createRouter, createWebHistory} from 'vue-router'
   
   // 引入要呈现的组件
   import Home from '@/pages/Home.vue'
   import Posts from '@/pages/Posts.vue'
   import NotFound from '@/pages/NotFound.vue'
   
   // 创建路由器
   const router = createRouter({
       history:createWebHistory(), //路由器的工作模式
       routes:[    // 路由规则
           { path:'/home', component:Home },
           { path:'/posts', component:Posts },
           { path:'*', component:NotFound },
       ]
   })
   export default router

4. 在 `main.ts` 引入路由器（ 一定要在 mount app 之前）

   ```js
   import { createApp } from 'vue'
   import App from './App.vue'
   import router from './router'
   
   const app = createApp(App)
   
   app.use(router)
   app.mount('#app')

5. 在 `App.vue` 中使用路由

   1. `<RouterLink>` 设置跳转链接：不同于常规的 `<a>` 标签， 该标签能够在不重新加载页面的情况下改变 URL，处理 URL 的生成、编码和其他功能。
   2. `<RouterView>` 设置组件展示位置
   3. `active-class` 设置路由启动时候的样式
   4. `$route`：可以在组件模板中使用 `$route` 来访问当前的路由对象。

   ```html
   <template>
     <div class="app">
       <h2 class="title">Vue路由测试</h2>
       <!-- 导航区 -->
       <div class="navigate">
         <RouterLink to="/home" active-class="active">首页</RouterLink> |
         <RouterLink to="/posts" active-class="active">请求</RouterLink> |
         <RouterLink to="/dsadsacdsa" active-class="active">404</RouterLink>
       </div>
       <!-- 展示区 -->
         <strong>当前路由路径:</strong> {{ $route.fullPath }}
       <div class="main-content">
         <RouterView></RouterView>
       </div>
     </div>
   </template>
   
   <script lang="ts" setup name="App">
     import {RouterLink,RouterView} from 'vue-router'  
   </script>

## 路由器工作模式

### 历史模式（History Mode）也称 HTML5 模式

使用浏览器的**History API**（如`history.pushState`和`history.replaceState`）来管理URL。URL看起来像常规的路径（如`/about`）。

**优点**：`URL`更加美观，不带有`#`，更接近传统的网站`URL`。

**缺点**：后期项目上线，需要服务端配合处理路径问题，否则刷新会有`404`错误。要解决这个问题，你需要做的就是在你的服务器上添加一个简单的回退路由。如果 URL 不匹配任何静态资源，它应提供与你的应用程序中的 `index.html` 相同的页面。

```js
const router = createRouter({
	history:createWebHistory(),
})
```

### 哈希模式（Hash Mode）

使用URL中的哈希（`#`）来模拟不同的路径。浏览器在哈希变化时不会发送请求，而是通过JavaScript处理（如`/index.html#/about`）。

优点：兼容性更好，因为不需要服务器端处理路径。

缺点：`URL`带有`#`不太美观，且在`SEO`（Search Engine Optimization，搜索引擎优化）优化方面相对较差。

```js
const router = createRouter({
	history:createWebHashHistory(),
})
```

### Memory 模式

Memory 模式不会假定自己处于浏览器环境，因此不会与 URL 交互也不会自动触发初始导航。这使得它非常适合 Node 环境和 SSR。

它是用 `createMemoryHistory()` 创建的，并且需要你在调用 `app.use(router)` 之后手动 push 到初始导航。

```js
import { createRouter, createMemoryHistory } from 'vue-router'
const router = createRouter({
  history: createMemoryHistory(),
})
```

虽然不推荐，你仍可以在浏览器应用程序中使用此模式，但请注意它**不会有历史记录**，这意味着你无法*后退*或*前进*。



## 命名路由

在 **routes** 中的某项路由配置中，可以通过 `name` 属性给路由规则命名：

```js
routes:[
  {
    name:'zhuye',
    path:'/home',
    component:Home
  },
]
```

之后可以使用该 `name` 进行跳转：

```html
<!--直接通过名字跳转（to的对象写法配合name属性） -->
<router-link :to="{name:'zhuye'}">跳转</router-link>
```

### 重定向

在配置中使用 `redirect`，将路由重定向。

1. 具体编码：

   ```js
   {
       path:'/',
       redirect:'/about'
   }
   ```

2. 重定向的目标也可以是一个命名的路由：

   ```js
   const routes = [{ path: '/home', redirect: { name: 'homepage' } }]
   ```

3. 重定向的目标也可以是一个方法，动态返回重定向目标：

   ```js
   {
       path: '/search/:searchText',
       redirect: to => {
         // 方法接收目标路由作为参数
         // return 重定向的字符串路径/路径对象
         return { path: '/search', query: { q: to.params.searchText } }
       },
   },
   ```

4. 相对重定向（不以 `/` 开头）

   ```js
   const routes = [
     {
       path: '/users/:id/posts',
       redirect: 'profile' // 会重定向到 /users/:id/profile
     },
   ]
   ```

在写 `redirect` 的时候，可以省略 `component` 配置，因为它从来没有被直接访问过，所以没有组件要渲染。唯一的例外是`嵌套路由`：如果一个路由记录有 `children` 和 `redirect` 属性，它也应该有 `component` 属性。

#### 检测重定向

(先读[编程式导航](###编程式导航)再回来读此部分更好理解)

当发生重定向时（从 `/a` 重定向 `/b`），Vue Router 会在目标路由（`/b`）的 `route` 对象上添加一个 `redirectedFrom` 属性，表示从哪（`/a`）重定向过来。

`redirectedFrom`存储原来导航目标的路由对象。

因此，通过读取路由地址中的 `redirectedFrom` 属性，对其进行不同的检查：

```js
await router.push('/a') // 路由里配置了会重定向到 /b

console.log(router.currentRoute.value.redirectedFrom)
// 输出：{ path: '/a', ... }（即原来的导航目标）

if (router.currentRoute.value.redirectedFrom) {
  // 如果重定向属性存在，那就 ...
}
```

### 别名

使用配置项`alias`为路由配上别名。

与重定向不同，使用别名时，URL 不会变更。

例如：`/` 的别名设置为 `/home` ，当用户访问 `/home` 时，URL 仍然是 `/home`，但会被匹配为用户正在访问 `/`。

```js
{
  path: '/',
  alias: '/home', // 通过/home或/都能访问
  component: Home
}
```

通过别名，你可以自由地将 UI 结构映射到一个任意的 URL，而不受配置的嵌套结构的限制。

使别名以 `/` 开头，以使嵌套路径中的路径成为绝对路径。

甚至可以将两者结合起来，用一个数组提供多个别名：

```js
const routes = [
  {
    path: '/users',
    component: UsersLayout,
    children: [
      // 为这 3 个 URL 呈现 UserList（进入到UsersLayout component后，UserList component将自动呈现）
      // - /users
      // - /users/list
      // - /people
      { path: '', component: UserList, alias: ['/people', 'list'] },
    ],
  },
]
```

如果你的路由有参数，请确保在任何绝对别名中包含它们，否则会变得很奇怪。



## 路由参数传入与访问

### 查询参数 query

查询参数是 URL 中 `?` 后面的部分，采用 `key=value` 的形式，多个参数用 `&` 连接：`http://example.com/path?name=John&age=30`

#### query 访问

与 `params` 不同 `query` 参数不需要在路由配置中预先声明，通过 `route.query` 访问。

```html
<template>
    <!-- 可以通过 $route 在模板中访问 -->
    {{ $route.query.name }}
</template>

<script setup>
// 使用 useRoute 在 setup 中访问
const route = useRoute()
console.log(route.query)
// 例如，访问 http://example.com/path?name=John&age=30 ，则就是 {name:"John",age:30}。
</script>
```

#### query 传入

```html
<!-- 跳转并携带query参数（to的字符串写法） -->
<RouterLink :to="/news?id=123&titile=123&content=123">
    {{news.title}}
</RouterLink>

<!-- 跳转并携带query参数（to的对象写法） -->
<RouterLink :to="{
  path: '/news',
  query: {
     id: news.id,
     title: news.title,
     content: news.content,
    }
  }">
    {{ news.title }}
</RouterLink>
```

### 动态路径参数 params

在 Web 前端中，动态路由匹配用于让页面根据 URL 的不同参数变化加载相应内容。

#### params 路由配置

**路径参数 params** 用在配置时冒号 `:` 表示，需在路由配置规则中提前设置。

使用 `path` 时不能使用 `params`，若之后要使用路由跳转的`to`的对象写法，必须使用`name`配置项。

```js
const routes = [
  // 动态字段以冒号开始
  { name:"userid", path: '/user/:id', component: User }
]
```

#### params 访问

设置后，匹配到的 params 值将在每个组件中以 `route.params` 的形式暴露出来。（选项式 API 用`this.$route.params`）

```html
<template>
    <!-- 可以通过 $route 在模板中访问 -->
    User {{ $route.params.id }}
</template>

<script setup>
// 使用 useRoute 在 setup 中访问
const route = useRoute()
console.log(route.params.id)
// 例如，访问 /users/johnny ，则 id 就是 johnny。
</script>
```

#### params 传入

使用 `path` 时不能使用 `params`。传递`params`参数时，若使用`to`的对象写法，则必须使用`name`配置项。

```html
<!-- 跳转并携带params参数（to的字符串写法） -->
<RouterLink :to="`/news/detail/001/新闻001/内容001`">
    {{news.title}}
</RouterLink>
				
<!-- 跳转并携带params参数（to的对象写法） -->
<RouterLink 
  :to="{
    name:'xiang', //用name跳转
    params:{
      id:news.id,
      title:news.title,
      content:news.title
    }
  }"
>
  {{news.title}}
</RouterLink>
```

#### 同路由多参数

可以在同一个路由中设置有多个 *路径参数*，它们会映射到 `$route.params` 上的相应字段。

```js
// router/index.ts
const routes = [
  { path: '/users/:username/posts/:postId', component: User }
]
/users/eduardo/posts/123

// script setup 内 （假设访问 /users/eduardo/posts/123 ）s
console.log(route.params) // 得到 { username: 'eduardo', postId: '123' }
```

### props 配置

作用：让路由组件更方便的收到参数（可以将路由参数作为`props`传给组件）

1. **布尔值写法**：将路由收到的所有**params参数**作为props传给路由组件（只能传params参数）

   ```js
   { name:'detail',
     path:'/detail/:id/:title/:content',
     component:Detail,
     props:true
   }
   ```

2. **函数写法**：把返回的对象中每一组key-value作为props传给Detail组件。

   - 接收一个参数，设定为当前配置的路由对象。
   - 返回对象可以设定为 route.query 从而使 query参数 也能以 props形式 便捷地传给组件。

   ```js
   { path:'/detail',
     component:Detail,
     props(route){
       return route.query
     }
   }
   ```

3. **对象写法**：自己设定对象，把对象中的每一组key-value作为props传给Detail组件。

   ```js
   { path:'/detail',
     component:Detail,
     props:{a:1,b:2,c:3}, 
   }

### 响应参数变化监控

1. **监听 `$route` 对象的变化**： Vue 提供了 `watch` 方法，可以监听 `route.params` 的变化。这样我们可以在参数变化时更新页面内容：

   ```js
   watch(() => route.params.id, (newId, oldId) => {
     // 对路由变化做出响应...
   })
   ```

2. **使用 `beforeRouteUpdate` 导航守卫**： 这个守卫在路由切换时触发，可以让我们提前响应参数变化，比如在新页面加载前获取新数据：

   ```js
   // router/index.ts
   import { onBeforeRouteUpdate } from 'vue-router'
   onBeforeRouteUpdate(async (to, from) => {
     // 对路由变化做出响应...
     userData.value = await fetchUser(to.params.id)
   })
   ```

### 嵌套路由

一些应用程序的 UI 由多层嵌套的组件组成。在这种情况下，URL 的片段通常对应于特定的嵌套组件结构。

要将组件渲染到这个嵌套的 `<router-view>` 中，我们需要在路由中配置 `children`。

```js
// router/index.ts
const routes = [
  {
    path: '/user/:id',
    component: User,
    children: [
      {
        // 当 /user/:id/profile 匹配成功，UserProfile 将被渲染到 User 的 <router-view> 内部
        path: 'profile',
        component: UserProfile,
      },
      {
        // 当 /user/:id/posts 匹配成功，UserPosts 将被渲染到 User 的 <router-view> 内部
        path: 'posts',
        component: UserPosts,
      },
    ],
  },
]
```

```html
<!-- App.vue -->
<template>
  <router-view />
</template>


<!-- User.vue -->
<template>
  <div>
    User {{ $route.params.id }}
    <router-view />
  </div>
</template>
```



## 路由跳转与展示

### RouterLink

`<RouterLink>` 或`<router-link>`是 Vue Router 提供的导航组件，用于在应用内部进行路由跳转。不同于常规的 `<a>` 标签， 该标签能够在不重新加载页面的情况下改变 URL，处理 URL 的生成、编码和其他功能。

1. **字符串路径用法**（最简单形式）

   ```html
   <RouterLink to="/home">首页</RouterLink>

2. **对象形式用法**（更灵活）

   ```html
   <RouterLink :to="{ path: '/user/123' }">用户</RouterLink>
   ```

   对象状态下能接收的参数：

   1. `path`：目标路由的路径

   2. `name`：命名路由的名称

   3. `params`：动态路径参数对象

   4. `query`：查询参数对象

   5. `replace`：是否替换当前历史记录（不添加新记录），传入值类型为 Boolean

      ```html
      <RouterLink :to="{ path: '/login', replace: true }">登录</RouterLink>
      ```

   6. `append`：是否在当前路径后追加路径，传入值类型为 Boolean

      ```html
      <!-- 当前路径为 /user，则生成路径 /user/profile -->
      <RouterLink :to="{ path: 'profile', append: true }">资料</RouterLink>
      ```

   7. `force`：是否强制刷新组件（Vue Router 4.1+），传入值类型为 Boolean

      ```html
      <RouterLink :to="{ path: '/login', force: true }">刷新</RouterLink>
      ```

   8. `state`：通过 History API 传递的状态对象（Vue Router 4+）

      ```html
      <RouterLink :to="{ path: '/checkout', state: { from: 'home', discount: true }}">结账</RouterLink>
      ```

   注意：使用 `path` 时不能使用 `params`

`<RouterLink>` 的可用属性。

|         属性         |     类型      |           默认值           |                 说明                 |
| :------------------: | :-----------: | :------------------------: | :----------------------------------: |
|         `to`         | String/Object |            必填            |      目标路由路径或路由描述对象      |
|      `replace`       |    Boolean    |           false            | 是否替换当前历史记录（不添加新记录） |
|       `append`       |    Boolean    |           false            |       是否在当前路径后追加路径       |
|    `active-class`    |    String     |    'router-link-active'    |           匹配时的激活类名           |
|       `exact`        |    Boolean    |           false            |             开关精确匹配             |
| `exact-active-class` |    String     | 'router-link-exact-active' |         精确匹配时的激活类名         |
|       `event`        | String/Array  |          'click'           |            触发导航的事件            |



### RouterView

`<RouterView>` 设置组件展示位置，是路由出口组件，用于渲染匹配到的路由组件。

#### 命名视图

有时候想同时 (同级) 展示多个视图，而不是嵌套展示，例如创建一个布局，有 `sidebar` (侧导航) 和 `main` (主内容) 两个视图，这个时候命名视图就派上用场了。

在 `<router-view>`中传入 `name` 属性，为视图命名。未设置名字则默认为 `default`。

在路由配置 `router/index.ts` 中，确保正确使用 `components` 配置 (单个为不带s，传入组件。多个则带上 s，传入对象)。

```html
<!-- 使用路由的组件 -->
<router-view class="view left-sidebar" name="LeftSidebar" />
<router-view class="view main-content" />
<router-view class="view right-sidebar" name="RightSidebar" />
```

```js
// router/index.ts
const router = createRouter({
  // ...
  routes: [
    {
      path: '/',
      components: {
        default: Home,
        LeftSidebar, // LeftSidebar: LeftSidebar 的缩写.
        RightSidebar, // 它们与 `<router-view>` 上的 `name` 属性匹配.
      },
    },
  ],
})
```



### 编程式导航

除了使用 `<router-link>` 创建 a 标签来定义导航链接，我们还可以借助 router 的实例方法，通过编写代码来实现。

在组件内部，可用 `$router` 属性访问路由。使用组合式 API，则可用 `useRouter()` 来访问路由器。

浏览器的历史记录有两种写入方式：分别为`push`和`replace`。

#### push

使用 `push` 方法，相当于使用`<RouterLink>`的 `to` 属性，可进行路由跳转。

```js
import {useRoute,useRouter} from 'vue-router'

const router = useRouter()

// 字符串路径
router.push('/users/eduardo')

// 带有路径的对象
router.push({ path: '/users/eduardo' })

// 命名的路由，并加上参数，让路由建立 url
router.push({ name: 'user', params: { username: 'eduardo' } })

// 带查询参数，结果是 /register?plan=private
router.push({ path: '/register', query: { plan: 'private' } })

// 带 hash，结果是 /about#team
router.push({ path: '/about', hash: '#team' })
```

**注意**：如果提供了 `path`，`params` 会被忽略，上述例子中的 `query` 并不属于这种情况。

`router.push` 和所有其他导航方法都会返回一个 *Promise*，让我们可以等到导航完成后才知道是成功还是失败。

```js
await router.push('/my-profile');
```

##### 检测导航是否成功跳转

如果导航被阻止，导致用户停留在同一个页面上，由 `router.push` 返回的 `Promise` 的解析值将是 *Navigation Failure*。否则，它将是一个 *falsy* 值(通常是 `undefined`)。这样我们就可以区分我们导航是否离开了当前位置：

```js
const navigationResult = await router.push('/my-profile')
if (navigationResult) {/*导航被阻止*/}
else {/*导航成功 (包括重新导航的情况)*/}
```

#### replace

`replace`是替换当前记录，即覆盖当前页面的历史记录（不产生新的历史记录，覆盖后无法回退到之前被覆盖的页面）。

在开启`<RouterLink>`中加入 `replace` 属性，可开启`replace`模式（传入值是 Boolean）。

```html
<RouterLink replace="true">News</RouterLink>
```

编程式导航则使用 `router.replace`，使用方法与 push 一致。

```js
const router = useRouter()
router.replace("...") // 使用方法
```

#### 横跨历史

该方法采用一个整数作为参数，表示在历史堆栈中前进或后退多少步，类似于 `window.history.go(n)`。

```js
// 向前移动一条记录，与 router.forward() 相同
router.go(1)

// 返回一条记录，与 router.back() 相同
router.go(-1)

// 前进 3 条记录
router.go(3)

// 如果没有那么多记录，静默失败
```

#### 篡改历史

`router.push`、`router.replace` 和 `router.go` 是 [`window.history.pushState`、`window.history.replaceState` 和 `window.history.go`](https://developer.mozilla.org/en-US/docs/Web/API/History) 的翻版，它们确实模仿了 `window.history` 的 API。

#### 生成 URL

`router.resolve` 是 Vue Router 提供的一个方法，用于**生成 URL** 或 **解析路由**。它可以根据路由名称或路径以及参数生成相应的 URL，通常用于导航、重定向和动态链接生成。

假设我们有一个带有动态参数 `:id` 的路由：

```js
const router = createRouter({
  history: createWebHistory(),
  routes:{ path: '/user/:id', name: 'user' }
})
```

使用 `router.resolve` 可以根据这个路由生成一个 URL。例如：

```js
// 生成一个 /user/123 的 URL
const routeInfo = router.resolve({ name: 'user', params: { id: '123' } })
console.log(routeInfo.href) // 输出 /user/123
```



## 路由元信息

通过接收属性对象的`meta`属性可以实现将任意信息附加到路由上，并且它可以在路由地址和导航守卫上都被访问到。

```js
{ path: '/posts',
  component: PostsLayout,
  children: [
    { path: 'new',
      component: PostsNew,
      meta: { requiresAuth: true }, // 只有经过身份验证的用户才能创建帖子
    },
    { path: ':id',
      component: PostsDetail
      meta: { requiresAuth: false }, // 任何人都可以阅读文章
    }
  ]
}
```

一个路由匹配到的所有路由记录会暴露为 `route` 对象(还有在导航守卫中的路由对象)的`route.matched` 数组。我们需要遍历这个数组来检查路由记录中的 `meta` 字段，但是 Vue Router 还为你提供了一个 `route.meta` 方法，它是一个非递归合并**所有 `meta`** 字段（从父字段到子字段）的方法。

```js
router.beforeEach((to, from) => {
  // to.matched.some(record => record.meta.requiresAuth)
  if (to.meta.requiresAuth && !auth.isLoggedIn()) {
    // 此路由需要授权，请检查是否已登录。如果没有，则重定向到登录页面
    return {
      path: '/login',
      // 保存我们所在的位置，以便以后再来
      query: { redirect: to.fullPath },
    }
  }
})
```



## 导航守卫

**vue-router **提供的导航守卫主要用来通过跳转或取消的方式守卫导航。

### 全局前置守卫

可以使用 `router.beforeEach` 注册一个全局前置守卫：

- 接收参数：
  - **`to`**: 即将要进入的目标
  - **`from`**: 当前导航正要离开的路由
- 可以返回的值如下:
  - `false`: 取消当前的导航。URL 地址会重置到 `from` 路由对应的地址。
  - `一个路由地址`: 通过一个路由地址重定向到一个不同的地址，如同调用 `router.push()`，且可以传入同样的配置项。

当一个导航触发时，全局前置守卫按照创建顺序调用。

守卫是异步解析执行，此时导航在所有守卫 resolve 完之前一直处于**等待中**。

```js
router.beforeEach(async (to, from) => {
    if (
        // 检查用户是否已登录（检测 to 是否Login，避免无限重定向）
        !(await isAuthenticated()) && to.name !== 'Login'
    ) {
        return { name: 'Login' } // 将用户重定向到登录页面
    }
})
```

如果遇到了意料之外的情况，可能会抛出一个 `Error`。这会取消导航并且调用 `router.onError()` 注册过的回调。

如果什么都没有，`undefined` 或返回 `true`，则导航是有效的，并调用下一个导航守卫

### 可选的第三个参数 next

在之前的 Vue Router 版本中，还可以使用 *第三个参数* `next` 。

这是一个常见的错误来源，开发者们经过讨论将其移除。然而，它仍然是被支持的。

若要使用 `next`，**确保**其在任何给定的导航守卫中都被**严格调用一次**。它可以出现多于一次，但是只能在所有的逻辑路径都不重叠的情况下，否则钩子永远都不会被解析或报错。

这里有一个在用户未能验证身份时重定向到`/login`的错误用例：

```js
// BAD
router.beforeEach((to, from, next) => {
  if (to.name !== 'Login' && !isAuthenticated) next({ name: 'Login' })
  // 如果用户未能验证身份，则 `next` 会被调用两次
  next()
})
```

### 全局解析守卫

可以用 `router.beforeResolve` 注册一个全局守卫。

和 `router.beforeEach` 类似，在**每次导航**时都会触发。不同的是，解析守卫刚好会在导航被确认之前、**所有组件内守卫和异步路由组件被解析之后**调用。

举例，根据路由在`元信息`中的 `requiresCamera` 属性确保用户访问摄像头的权限：

```js
router.beforeResolve(async to => {
  if (to.meta.requiresCamera) { // 前往的路由需要访问摄像头
    try {
      await askForCameraPermission() // 查看有无摄像头的权限
    } catch (error) {
      if (error instanceof NotAllowedError) {
        return false // ... 处理错误，然后取消导航
      } else {
        throw error // 意料之外的错误，取消导航并把错误传给全局处理器
      }
    }
  }
})
```

`router.beforeResolve` 是获取数据或执行任何其他操作（如果用户无法进入页面时你希望避免执行的操作）的理想位置。

### 全局后置钩子

可用`afterEach`注册全局后置钩子，但这些钩子不会接受 `next` 函数也不会改变导航本身。它们对于分析、更改页面标题、声明页面等辅助功能以及许多其他事情都很有用。

```js
router.afterEach((to, from, failure) => {
  if (!failure) sendToAnalytics(to.fullPath)
  sendToAnalytics(to.fullPath)
})
```

### 路由独享的守卫

在当个路由配置项内，可以直接定义 `beforeEnter` 守卫，让其变为独享的路由守卫。

`beforeEnter` 守卫 只在进入路由时触发，不会在 `params`、`query` 或 `hash` 改变时触发。当配合嵌套路由使用时，父路由和子路由都可以使用 `beforeEnter`。如果放在父级路由上，路由在具有相同父级的子路由之间移动时不会被触发。

```js
{ path: '/users/:id',
  component: UserDetails,
  beforeEnter: (to, from) => {
    return false
  },
},
```

也可以将一个函数数组传递给 `beforeEnter`，这在为不同的路由重用守卫时很有用。

```js
function removeQueryParams(to) {
  if (Object.keys(to.query).length)
    return { path: to.path, query: {}, hash: to.hash }
}

function removeHash(to) {
  if (to.hash) return { path: to.path, query: to.query, hash: '' }
}

const routes = [
  {
    path: '/users/:id',
    component: UserDetails,
    beforeEnter: [removeQueryParams, removeHash],
  },
  {
    path: '/about',
    component: UserDetails,
    beforeEnter: [removeQueryParams],
  },
]
```

### 组件内的守卫

可以在路由组件内直接定义路由导航守卫(传递给路由配置的)。

**选项式API**：

```html
<script>
export default {
  beforeRouteEnter(to, from, next) {
    // 在渲染该组件的对应路由被验证前调用
    // 在此时进行获取数据是不错的选择，在加载组件的同时异步获取数据，减少等待时间。
    // 守卫执行时，组件实例未被创建，所以在此提供通过 next 的回调函数访问组件实例的机制
    next(vm => {/*通过 `vm` 访问组件实例，调用实例的代码或属性*/})
  }
  beforeRouteUpdate(to, from) {},
  beforeRouteLeave(to, from) {},
  setup(){},
}
</script>
```

**组合式API**：

```html
<script setup>
import { onBeforeRouteLeave, onBeforeRouteUpdate, onBeforeRouteEnter } from 'vue-router'
    
onBeforeRouteUpdate(to, from) {
    // 在当前路由改变，但是该组件被复用时调用
    // 举例来说，对于一个带有动态参数的路径 `/users/:id`，在 `/users/1` 和 `/users/2` 之间跳转的时候，由于会渲染同样的组件，因此组件实例会被复用。而这个钩子就会在这个情况下被调用。
    // 此时组件实例已经挂载好了
}

onBeforeRouteLeave(to, from) {
    // 在导航离开渲染该组件的对应路由时调用
}
</script>
```

`<script setup>` 没有`beforeRouteEnter`配置项，若想使用可以混用选项式 API ：

```html
<script>
export default {
  beforeRouteEnter(to, from, next) {}
}
</script>

<script setup>
  // ...
</script>
```

### 完整的导航解析流程

1. 导航被触发。
2. 在失活的组件里调用 `beforeRouteLeave` 守卫。
3. 调用全局的 `beforeEach` 守卫。
4. 在重用的组件里调用 `beforeRouteUpdate` 守卫(2.2+)。
5. 在路由配置里调用 `beforeEnter`。
6. 解析异步路由组件。
7. 在被激活的组件里调用 `beforeRouteEnter`。
8. 调用全局的 `beforeResolve` 守卫(2.5+)。
9. 导航被确认。
10. 调用全局的 `afterEach` 钩子。
11. 触发 DOM 更新。
12. 调用 `beforeRouteEnter` 守卫中传给 `next` 的回调函数，创建好的组件实例会作为回调函数的参数传入。



## 导航故障

### 检测导航故障

之前在[编程式导航](####检测导航是否成功跳转)中有初步介绍`router.push`的返回值 *Navigation Failure*。 *Navigation Failure* 是带有一些额外属性的 `Error` 实例，这些属性提供哪些导航被阻止了以及为什么被阻止的信息。

要检查导航结果的性质，请使用 `isNavigationFailure` 函数：

```js
import { NavigationFailureType, isNavigationFailure } from 'vue-router'

// 试图离开未保存的编辑文本界面
const failure = await router.push('/articles/2')

if (isNavigationFailure(failure, NavigationFailureType.aborted)) {
  // 给用户显示一个小通知
  showToast('You have unsaved changes, discard and leave anyway?')
}
```

### 全局导航故障

可用 `router.afterEach()` 导航守卫检测全局导航故障：

```ts
router.afterEach((to, from, failure) => {
  if (failure) {
    sendToAnalytics(to, from, failure)
  }
})
```

### 导航故障的属性

所有的导航失败都会暴露 `to` 和 `from` 属性，以反映失败导航的当前位置和目标位置：

```js
// 正在从 / 尝试访问 admin 页面
router.push('/admin').then(failure => {
  if (isNavigationFailure(failure, NavigationFailureType.aborted)) {
    failure.to.path // '/admin'
    failure.from.path // '/'
  }
})
```



## RouterView 插槽

`RouterView` 组件暴露了一个插槽，可以用来渲染路由组件。其插槽提供两个属性：

1. `Component`：当前路由匹配的组件实例
2. `route`：当前路由对象

以下代码等价于不带插槽的 `<router-view />`：

```html
<router-view v-slot="{ Component, route }">
  <component :is="Component" />
</router-view>
```

当想获得其他功能时，插槽提供了额外的扩展性：

1. 比如插入`KeepAlive` & `Transition`组件，获得动效和保持路由组件活跃。

   ```html
   <router-view v-slot="{ Component }">
     <transition name="fade">
       <keep-alive>
         <component :is="Component" />
       </keep-alive>
     </transition>
   </router-view>
   ```

2. 比如使用模板引用直接获得路由组件示例

   ```html
   <router-view v-slot="{ Component }">
     <component :is="Component" ref="mainContent" />
   </router-view>
   
   

## 路由组件过渡动效

### 单个路由的过渡

如果希望每个路由组件拥有其独特的过渡动效，可以使用 `meta` 元信息和动态的 `name` 结合在一起，放在`<transition>` 上：

```js
{ path: '/custom-transition',
  component: PanelLeft,
  meta: { transition: 'slide-left' },
},
{ path: '/other-transition',
  component: PanelRight,
  meta: { transition: 'slide-right' },
},
```

```html
<router-view v-slot="{ Component, route }">
  <!-- 使用自定义过渡，无自定义使用 `fade` -->
  <transition :name="route.meta.transition || 'fade'">
    <component :is="Component" />
  </transition>
</router-view>
```

### 强制在复用的视图之间进行过渡

Vue 可能会自动复用看起来相似的组件，从而忽略了任何过渡。可以添加``key` 属性来强制过渡。

```html
<router-view v-slot="{ Component, route }">
  <transition name="fade">
    <component :is="Component" :key="route.path" />
  </transition>
</router-view>
```

### 基于路由的动态过渡

我们可以添加一个`afterEach`全局后置钩子，根据路径的深度动态添加信息到 `meta` 字段。

```html
<!-- 使用动态过渡名称 -->
<router-view v-slot="{ Component, route }">
  <transition :name="route.meta.transition">
    <component :is="Component" />
  </transition>
</router-view>
```

```js
router.afterEach((to, from) => {
  const toDepth = to.path.split('/').length
  const fromDepth = from.path.split('/').length
  to.meta.transition = toDepth < fromDepth ? 'slide-right' : 'slide-left'
})
```



## 路由懒加载

当打包构建应用时，JavaScript 包会变得非常大，影响页面加载。如果我们能把不同路由对应的组件分割成不同的代码块，然后当路由被访问的时候才加载对应组件，这样就会更加高效。

Vue Router 支持`动态导入`：

```js
// 将 import UserDetails from './views/UserDetails.vue' 替换成以下
const UserDetails = () => import('./views/UserDetails.vue')

const router = createRouter({
  routes: [
    { path: '/users/:id', component: UserDetails }
    // 或在路由定义里直接使用它
    { path: '/users/:id', component: () => import('./views/UserDetails.vue') },
  ],
})
```

`component`和 `components` 的配置也可以接收一个返回 Promise 组件的函数：

```js
const UserDetails = () =>
  Promise.resolve({
    /* 组件定义 */
  })
```



## 动态路由

对路由的添加通常是通过 `routes` 选项来完成的，但是在某些情况下，开发者可能想在应用程序已经运行的时候添加或删除路由。

- `router.hasRoute(name)`：检查以该名称命名的路由是否存在，返回一个 Boolean。

- `router.getRoutes()`：获取一个包含所有路由记录的数组。

- `router.addRoute()`：添加一个路由，其会返回一个回调以方便删除操作。

  ```js
  const removeRoute = router.addRoute({ path: '/about', component: About })
  removeRoute() // 删除路由如果存在的话
  ```

  - 若添加一个名称冲突的路由，会先删除原有路由，再添加新路由。

  - 若第一个参数放入的是已存在路由的名称，则可添加为嵌套路由。

    ```js
    router.addRoute({ name: 'admin', path: '/admin', component: Admin })
    router.addRoute('admin', { path: 'settings', component: AdminSettings })
    // 等效于
    router.addRoute({
      name: 'admin',
      path: '/admin',
      component: Admin,
      children: [{ path: 'settings', component: AdminSettings }],
    })

- `router.removeRoute()` 按名称删除路由

  ```js
  router.removeRoute('about')