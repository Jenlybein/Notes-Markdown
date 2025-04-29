# React 快速上手



## 简介

- **React 官网**：[React](https://react.dev/)
- **React 中文官网**：[React 官方中文文档](https://zh-hans.react.dev/)
- **React 教程主页**：[快速入门 – React 中文文档](https://zh-hans.react.dev/learn)

**React** 是一个由 Facebook 开发并开源的 **JavaScript 库**，用于构建用户界面（UI），尤其是单页面应用（SPA）。它的核心思想是通过组件化开发，将 UI 拆分为独立、可复用的部分，并通过声明式编程简化交互逻辑。

1. 声明式：React使用的（JSX语法），而 Vue 中将 HTML,CSS,JS 都分离了。所以很多初级前端开发者可以很好的学习 Vue，而学习 React 需要对 JavaScript 相对有一定要求。这也是更多新手前端开发者和初级前端开发者更热爱 Vue 的一个原因。
2. 组件化：组件是react中最重要的内容，构建管理自身状态的封装组件，然后对其组合以构成复杂的 UI。
3. 跨平台：react 既可以开发web应用也可以使用同样的语法开发原生应用（react-native），比如安卓和ios应用，react更像是一个 `元框架` 为各种领域赋能。



## 安装

1. 安装 `Node.js`

2. 使用 `vite` 作为构建工具，并使用 `react` 模板：

   ```bash
   npm create vite@latest my-app -- --template react
   ```

3. 进入目录并安装依赖

   ```bash
   cd my-app && npm install

4. 这样即可创建好基本的项目，打开 `src/App.jsx`，基本使用格式如下：

   ```jsx
   function App() {
     return (
       // JSX 语法，类似于 HTML
       <div>
         <p>hello React</p>
       </div>
     )
   }
   
   export default App
   ```

### 路由

可使用 [React Router](https://reactrouter.com/start/data/custom)  或 [Tanstack Router](https://tanstack.com/router/latest) 。

**React Router 第三方中文文档**：[React Router - 第三方中文文档](https://reactrouter.remix.org.cn/start/framework/installation)

如果项目重度依赖 TypeScript 或需要 Remix 风格的数据加载，TanStack Router 是更好的选择；否则 React Router 的成熟度仍是首选。

新手可先使用 `React Router`。



