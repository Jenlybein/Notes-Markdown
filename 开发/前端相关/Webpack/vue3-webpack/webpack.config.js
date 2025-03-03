const path = require("path");
const { VueLoaderPlugin } = require("vue-loader");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const { DefinePlugin } = require("webpack");

module.exports = (env, argv) => ({
  // 入口文件配置
  entry: "./src/main.js", // 项目主入口文件

  // 输出配置
  output: {
    filename: "[name].[contenthash:8].js", // 输出文件名格式（带哈希缓存）
    path: path.resolve(__dirname, "dist"), // 输出目录路径
    clean: true, // 每次构建前清空输出目录
  },

  // 模块处理规则
  module: {
    rules: [
      // Vue 单文件组件处理规则
      {
        test: /\.vue$/, // 匹配 .vue 文件
        loader: "vue-loader", // 使用 vue-loader 处理
      },
      // CSS 处理规则
      {
        test: /\.css$/, // 匹配 .css 文件
        use: [
          "vue-style-loader", // 将 CSS 注入 DOM (专为 Vue 优化)
          "css-loader", // 解析 CSS 模块依赖
        ],
      },
      // 图片资源处理规则
      {
        test: /\.(png|jpe?g|gif|svg)$/i, // 匹配图片格式
        type: "asset/resource", // 直接复制文件到输出目录
      },
    ],
  },

  // 插件配置
  plugins: [
    new VueLoaderPlugin(), // 必须! 用于处理 .vue 文件模板
    new HtmlWebpackPlugin({
      template: "./src/index.html", // 基于此 HTML 模板生成最终页面
      title: "Vue 3 Webpack Scaffold", // 设置页面标题
    }),
    new DefinePlugin({
      // 定义 Vue 3 全局常量
      __VUE_OPTIONS_API__: true, // 启用 Options API
      __VUE_PROD_DEVTOOLS__: false, // 生产环境禁用 devtools
    }),
  ],

  // 模块解析配置
  resolve: {
    extensions: [".js", ".vue", ".json", ".ts"], // 自动补全这些扩展名
    alias: {
      "@": path.resolve(__dirname, "src"),
    },
  },

  // 开发服务器配置
  devServer: {
    static: "./dist", // 静态文件目录
    hot: true, // 启用热更新
    open: true, // 自动打开浏览器
    port: 8080, // 指定端口号
  },

  // 优化配置
  optimization: {
    splitChunks: {
      chunks: "all", // 自动代码分割所有类型的 chunks
    },
  },

  // Source Map 配置
  devtool:
    argv.mode === "development"
      ? "eval-source-map" // 开发环境: 高质量 Source Map
      : "source-map", // 生产环境: 独立 .map 文件
});
