[TOC]

------

<center><font size=7><b>CSS学习</center></font></center>

---



## CSS 简介

CSS 是一种描述 HTML 文档样式的语言。

CSS 描述应该如何显示 HTML 元素。

CSS 用于定义网页的样式，包括针对不同设备和屏幕尺寸的设计和布局。

- *CSS* 指的是层叠样式表* (*C*ascading *S*tyle *S*heets)
- CSS 描述了*如何在屏幕、纸张或其他媒体上显示 HTML 元素*
- CSS *节省了大量工作*。它可以同时控制多张网页的布局
- 外部样式表存储在 *CSS 文件*中

***：**也称级联样式表。



一张 HTML 页面 可以有 多个样式

下面是一张提供了四个不同样式表的 HTML 页面。请单击下面的样式表链接，来查看不同的样式：

<iframe style="width:100%;height:700px;background:#ffffff;border:none" src="https://www.w3school.com.cn/demo/css/intro.html"></iframe>



样式定义通常保存在外部 .css 文件中。

通过使用外部样式表文件，只需更改一个文件即可更改整个网站的外观！



## CSS 插入

有三种插入样式表的方法：

- 外部 CSS
- 内部 CSS
- 行内 CSS

### 外部 CSS

通过使用外部样式表，您只需修改一个文件即可改变整个网站的外观！

每张 HTML 页面必须在 head 部分的 <link> 元素内包含对外部样式表文件的引用。

> 外部样式在 HTML 页面 <head> 部分内的 <link> 元素中进行定义：
>
> ```html
> <!DOCTYPE html>
> <html>
> <head>
> <link rel="stylesheet" type="text/css" href="mystyle.css">
> </head>
> <body>
> 
> <h1>This is a heading</h1>
> <p>This is a paragraph.</p>
> 
> </body>
> </html>
> ```
>
> 外部样式表可以在任何文本编辑器中编写，并且必须以 .css 扩展名保存。
>
> 外部 .css 文件不应包含任何 HTML 标签。
>
> "mystyle.css" 是这样的：
>
> ```css
> body {
>   background-color: lightblue;
> }
> 
> h1 {
>   color: navy;
>   margin-left: 20px;
> }
> ```
>
> **注意：**请勿在属性值和单位之间添加空格（例如 `margin-left: 20 px;`）。正确的写法是：`margin-left: 20px;`



### 内部 CSS

如果一张 HTML 页面拥有唯一的样式，那么可以使用内部样式表。

内部样式是在 head 部分的 <style> 元素中进行定义。

内部样式在 HTML 页面的 <head> 部分内的 <style> 元素中进行定义



### 行内 CSS

行内样式（也称内联样式）可用于为单个元素应用唯一的样式。

如需使用行内样式，请将 style 属性添加到相关元素。style 属性可包含任何 CSS 属性。

行内样式在相关元素的 "style" 属性中定义

**提示：**行内样式失去了样式表的许多优点（通过将内容与呈现混合在一起）。请谨慎使用此方法。



### 多个样式表

如果在不同样式表中为**同一选择器**（元素）定义了一些属性，则将**使用最后读取的样式表**中的值。



### 层叠顺序

当为某个 HTML 元素指定了多个样式时，会使用哪种样式呢？

页面中的所有样式将按照以下规则“层叠”为新的“虚拟”样式表，其中第一优先级最高：

1. 行内样式（在 HTML 元素中）
2. 外部和内部样式表（在 head 部分）
3. 浏览器默认样式

因此，行内样式具有最高优先级，并且将覆盖外部和内部样式以及浏览器默认样式。



## CSS 语法

<img src="https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409211404245.gif" alt="CSS 选择器" style="zoom:150%;background:#ffffff;" />

**选择器**指向您需要设置样式的 HTML 元素。

**声明块**包含一条或多条用分号分隔的声明。

每条**声明**都包含一个 CSS **属性**名称和一个**值**，以<u>*冒号分隔*</u>。

多条 CSS 声明用分号分隔，声明块用<u>*花括号括起*</u>来。

> <span id="jump">实例</span>
>
> 在此例中，所有 <p> 元素都将居中对齐，并带有红色文本颜色：
>
> ```css
> p {
>   color: red;
>   text-align: center;
> }
> ```
>
> ### 例子解释
>
> - `p` 是 CSS 中的*选择器*（它指向要设置样式的 HTML 元素：<p>）。
> - `color` 是属性，`red` 是属性值
> - `text-align` 是属性，`center` 是属性值



## CSS 选择器

CSS 选择器用于“查找”（或选取）要设置样式的 HTML 元素。

我们可以将 CSS 选择器分为五类：

- **简单选择器**（根据<u>名称</u>、<u>id</u>、<u>类</u>来选取元素）
- **组合器选择器**（根据它们之间的<u>特定关系</u>来选取元素）
- **伪类选择器**（根据<u>特定状态</u>选取元素）
- **伪元素选择器**（选取<u>元素的一部分并</u>设置其样式）
- **属性选择器**（根据<u>属性</u>或<u>属性值</u>来选取元素）





| 所有简单的 CSS 选择器     | 例子       | 例子描述                             |
| :------------------------ | :--------- | :----------------------------------- |
| .*class*                  | ro         | 选取所有 class="intro" 的元素。      |
| #*id*                     | #firstname | 选取 id="firstname" 的那个元素。     |
| *                         | *          | 选取所有元素。                       |
| *element*                 | p          | 选取所有 <p> 元素。                  |
| *element*，*element*，... | div, p     | 选取所有 <div> 元素和所有 <p> 元素。 |

### CSS 元素选择器

元素选择器根据元素名称来选择 HTML 元素。

> 实例：[点击跳转](#jump)

### CSS id 选择器

id 选择器使用 HTML 元素的 id 属性来选择特定元素。

元素的 id 在页面中是唯一的，因此 id 选择器用于选择一个唯一的元素！

要选择具有特定 id 的元素，请写一个井号（＃），后跟该元素的 id。

> ### 实例
>
> 这条 CSS 规则将应用于 id="para1" 的 HTML 元素：
>
> ```css
> <head>
> <style>
> #para1 {
>   text-align: center;
>   color: red;
> }
> </style>
> </head>
> <body>
> 
> <p id="para1">Hello World!</p>
> <p>本段不受样式的影响。</p>
> 
> </body>
> ```

### CSS 类选择器

类选择器选择有特定 class 属性的 HTML 元素。

如需选择拥有特定 class 的元素，请写一个句点（.）字符，后面跟类名。

**注意：**类名不能以数字开头！

> ### 实例
>
> 在此例中，所有带有 class="center" 的 HTML 元素将为红色且居中对齐：
>
> ```html
> <head>
> <style>
> .center {
>   text-align: center;
>   color: red;
> }
> </style>
> </head>
> <body>
> 
> <h1 class="center">居中的红色标题</h1>
> <p class="center">居中的红色段落。</p> 
> 
> </body>
> ```

还可以指定只有特定的 HTML 元素会受类的影响。

> 在这个例子中，只有具有 class="center" 的 <p> 元素会居中对齐：
>
> ```html
> <style>
> p.center {
>   text-align: center;
>   color: red;
> }
> </style>
> ```

HTML 元素也可以引用多个类。

> 在这个例子中，<p> 元素将根据 class="center" 和 class="large" 进行样式设置：
>
> ```html
> <p class="center large">这个段落引用两个类。</p>
> ```

### CSS 通用选择器

通用选择器（*）选择页面上的所有的 HTML 元素。

> ### 实例
>
> 下面的 CSS 规则会影响页面上的每个 HTML 元素：
>
> ```css
> * {
>   text-align: center;
>   color: blue;
> }
> ```

### CSS 分组选择器

分组选择器选取所有具有相同样式定义的 HTML 元素。

多个元素具有相同的样式定义最好对选择器进行分组，最大程度地缩减代码。

如需对选择器进行分组，请用逗号来分隔每个选择器。

> 请看下面的 CSS 代码（h1、h2 和 p 元素具有相同的样式定义）：
>
> ```css
> h1 {
>   text-align: center;
>   color: red;
> }
> 
> h2 {
>   text-align: center;
>   color: red;
> }
> 
> p {
>   text-align: center;
>   color: red;
> }
> ```
>
> ```html
> h1, h2, p {
>   text-align: center;
>   color: red;
> }
> ```



**补充：**

### CSS 后代选择器

我们可以定义后代选择器来创建一些规则，使这些规则在某些文档结构中起作用，而在另外一些结构中不起作用。

> 举例来说，如果您希望只对 h1 元素中的 em 元素应用样式，可以这样写：
>
> ```html
> h1 em {color:red;}
> ```
>
> ```html
> <head>
> <style type="text/css">
> h1 em {color:red;}
> </style>
> </head>
> 
> <body>
> <h1>This is a <em>important</em> heading</h1>
> <p>This is a <em>important</em> paragraph.</p>
> </body>
> ```
>
> 当然，您也可以在 h1 中找到的每个 em 元素上放一个 class 属性，但是显然，后代选择器的效率更高。

### CSS 子元素选择器

与后代选择器相比，子元素选择器（Child selectors）只能选择作为某元素子元素的元素。

如果您不希望选择任意的后代元素，而是希望缩小范围，只选择某个元素的子元素，请使用子元素选择器（Child selector）。

> 例如，如果您希望选择只作为 h1 元素子元素的 strong 元素，可以这样写：
>
> ```html
> h1 > strong {color:red;}
> ```
>
> ```html
> <head>
> <style type="text/css">
> h1 > strong {color:red;}
> </style>
> </head>
> 
> <body>
> <h1>This is <strong>very</strong> <strong>very</strong> important.</h1>
> <h1>This is <em>really <strong>very</strong></em> important.</h1>
> </body>
> ```

### CSS 相邻兄弟选择器

相邻兄弟选择器（Adjacent sibling selector）可选择紧接在另一元素后的元素，且二者有相同父元素。

如果需要选择紧接在另一个元素后的元素，而且二者有相同的父元素，可以使用相邻兄弟选择器（Adjacent sibling selector）。

相邻兄弟选择器使用了加号（+），即相邻兄弟结合符（Adjacent sibling combinator）。

请记住，用一个结合符只能选择两个相邻兄弟中的第二个元素。

> 例如，如果要增加紧接在 h1 元素后出现的段落的上边距，可以这样写：
>
> ```html
> h1 + p {margin-top:50px;}
> ```
>
> ```html
> <!DOCTYPE HTML>
> <html>
> <head>
> <style type="text/css">
> h1 + p {margin-top:50px;color:red;}
> </style>
> </head>
> 
> <body>
> <h1>This is a heading.</h1>
> <p>This is paragraph.</p>
> <p>This is paragraph.</p>
> <p>This is paragraph.</p>
> <p>This is paragraph.</p>
> <p>This is paragraph.</p>
> </body>
> </html>
> ```
>
> 相邻兄弟结合符还可以结合其他结合符：
>
> ```css
> html > body table + ul {margin-top:20px;}
> ```
>
> 这个选择器解释为：选择紧接在 table 元素后出现的所有兄弟 ul 元素，该 table 元素包含在一个 body 元素中，body 元素本身是 html 元素的子元素。



## CSS 注释

注释用于解释代码，以后在您编辑源代码时可能会有所帮助。

浏览器会忽略注释。

注释能横跨多行。

位于 `<style>` 元素内的 CSS 注释，以 `/*` 开始，以 `*/` 结束：



## CSS 颜色

指定颜色是通过使用预定义的颜色名称，或 RGB、HEX、HSL、RGBA、HSLA 值。

### 颜色名称

<div style="width: 20%; line-height: 80px; float: left; border: 0px; margin: 20px; text-align: center; color: white; background-color:tomato;">Tomato</div>
<div style="width: 20%; line-height: 80px; float: left; border: 0px; margin: 20px; text-align: center; color: white; background-color:Orange;">Orange</div>
<div style="width: 20%; line-height: 80px; float: left; border: 0px; margin: 20px; text-align: center; color: white; background-color:DodgerBlue;">DodgerBlue</div>
<div style="width: 20%; line-height: 80px; float: left; border: 0px; margin: 20px; text-align: center; color: white; background-color:MediumSeaGreen;">MediumSeaGreen</div>
<div style="width: 20%; line-height: 80px; float: left; border: 0px; margin: 20px; text-align: center; color: white; background-color:Gray;">Gray</div>
<div style="width: 20%; line-height: 80px; float: left; border: 0px; margin: 20px; text-align: center; color: white; background-color:SlateBlue;">SlateBlue</div>
<div style="width: 20%; line-height: 80px; float: left; border: 0px; margin: 20px; text-align: center; color: white; background-color:Violet;">Violet</div>
<div style="width: 20%; line-height: 80px; float: left; border: 0px; margin: 20px; text-align: center; color: white; background-color:LightGray;">LightGray</div>











CSS/HTML 支持140 种标准颜色名。



### RGB 颜色

`rgb(*red*, *green*, *blue*)`

每个参数 (*red*、*green* 以及 *blue*) 定义了 0 到 255 之间的颜色强度。

**RGBA 值**

RGBA 颜色值是具有 alpha 通道的 RGB 颜色值的扩展 - 它指定了颜色的不透明度。

`rgba(*red*, *green*, *blue*, *alpha*)`

*alpha* 参数是介于 0.0（完全透明）和 1.0（完全不透明）之间的数字



### HEX 颜色

在 CSS 中，可以使用以下格式的十六进制值指定颜色：

`#rrggbb`

其中 rr（红色）、gg（绿色）和 bb（蓝色）是介于 00 和 ff 之间的十六进制值（与十进制 0-255 相同）。



### HSL 颜色

在 CSS 中，可以使用色相、饱和度和明度（HSL）来指定颜色，格式如下：

`hsla(*hue*, *saturation*, *lightness*)`

色相（*hue*）是色轮上从 0 到 360 的度数。0 是红色，120 是绿色，240 是蓝色。

饱和度（*saturation*）是一个百分比值，0％ 表示灰色阴影，而 100％ 是全色。

亮度（*lightness*）也是百分比，0％ 是黑色，50％ 是既不明也不暗，100％是白色。

**HSLA 值**

HSLA 颜色值是带有 Alpha 通道的 HSL 颜色值的扩展 - 它指定了颜色的不透明度。

HSLA 颜色值指定为：

`hsla(*hue*, *saturation*, *lightness*, *alpha*)`

*alpha* 参数是介于 0.0（完全透明）和 1.0（完全不透明）之间的数字



### 背景色

```html
<h1 style="background-color:DodgerBlue;">China</h1>
<p style="background-color:Tomato;">China is a great country!</p>
```

<h1 style="background-color:DodgerBlue;">China</h1>

<p style="background-color:Tomato;">China is a great country!</p>



### 文本颜色

```html
<h1 style="color:Tomato;">China</h1>
<p style="color:DodgerBlue;">China is a great country!</p>
<p style="color:MediumSeaGreen;">China, officially the People's Republic of China...</p>
```

<h1 style="color:Tomato;">China</h1>

<p style="color:DodgerBlue;">China is a great country!</p>

<p style="color:MediumSeaGreen;">China, officially the People's Republic of China...</p>



### 边框颜色

```html
<h1 style="border:2px solid Tomato;">Hello World</h1>
<h1 style="border:2px solid DodgerBlue;">Hello World</h1>
<h1 style="border:2px solid Violet;">Hello World</h1>
```

<h1 style="border:2px solid Tomato;">Hello World</h1>

<h1 style="border:2px solid DodgerBlue;">Hello World</h1>

<h1 style="border:2px solid Violet;">Hello World</h1>





## CSS 背景

CSS 背景属性用于定义元素的背景效果。

### 背景属性

| 属性                  | 说明                                                         |
| --------------------- | ------------------------------------------------------------ |
| background-color      | 指定元素的背景色                                             |
| background-image      | 属性指定用作元素背景的图像。默认情况下，属性在水平和垂直方向上都重复图像。<br />**注意：**使用背景图像时，请使用不会干扰文本的图像。 |
| background-repeat     | 属性指定用作元素背景的图像的重复效果。                       |
| background-attachment | 属性指定背景图像是应该滚动还是固定的（不会随页面的其余部分一起滚动） |
| background-position   | 属性用于指定背景图像的位置。                                 |
| background-size       | 规定背景图像的尺寸。                                         |
| background-origin     | 规定在何处放置背景图像。                                     |
| background-clip       | 规定背景的绘制区域。                                         |
| opacity               | 指定元素的透明度。取值范围为 0.0 - 1.0。值越低，越透明       |

> `background-color` 页面的背景色设置如下：
>
> ```css
> body {
>   background-color: lightblue;
> }
> ```
>
> `background-image ` 页面的背景图像可以像这样设置：
>
> ```css
> body {
>   background-image: url("paper.gif");
> }
> ```
>
> `background-repeat: repeat-x`，否则它们看起来会很奇怪，这种图像仅在水平方向重复，则背景看起来会更好：
>
> ```html
> body {
>   background-image: url("gradient_bg.png");
>   background-repeat: repeat-x;
> }
> ```
>
> `background-repeat: no-repeat;` 背景图像仅显示一次：
>
> ```css
> body {
>   background-image: url("tree.png");
>   background-repeat: no-repeat;
> }
> ```
>
> `background-position;` 把背景图片放在右上角：
>
> ```css
> body {
>   background-image: url("tree.png");
>   background-repeat: no-repeat;
>   background-position: right top;
> }
> ```
>
> 固定背景图像：`fixed`  随页面的其余部分一起滚动：`scroll`
>
> ```css
> body {
>   background-image: url("tree.png");
>   background-repeat: no-repeat;
>   background-position: right top;
>   background-attachment: fixed;
> }
> ```



### 背景简写

如需缩短代码，也可以在一个属性中指定所有背景属性。它被称为简写属性。

使用简写属性 `background`

在使用简写属性时，属性值的顺序为：

- background-color
- background-image
- background-repeat
- background-attachment
- background-position

>```css
>body {
>  background-color: #ffffff;
>  background-image: url("tree.png");
>  background-repeat: no-repeat;
>  background-position: right top;
>}
>```
>
>```css
>body {
>  background: #ffffff url("tree.png") no-repeat right top;
>}
>```





## CSS 框模型

所有 HTML 元素都可以视为方框。在 CSS 中，在谈论设计和布局时，会使用术语“盒模型”或“框模型”。

CSS 框模型实质上是一个包围每个 HTML 元素的框。它包括：外边距、边框、内边距以及实际的内容。下图展示了框模型：

<img src="https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409211405043.gif" alt="CSS 框模型" style="zoom:120%;background-color:gray;border-radius: 5px;" />

- *内容* - 框的内容，其中显示文本和图像。
- *内边距* - 清除内容周围的区域。内边距是透明的。
- *边框* - 围绕内边距和内容的边框。
- *外边距* - 清除边界外的区域。外边距是透明的。

元素框的最内部分是实际的内容，直接包围内容的是内边距。内边距呈现了元素的背景。内边距的边缘是边框。边框以外是外边距，外边距默认是透明的，因此不会遮挡其后的任何元素。

**提示：**背景应用于由内容和内边距、边框组成的区域。

内边距、边框和外边距都是可选的，默认值是零。但是，许多元素将由用户代理样式表设置外边距和内边距。可以通过将元素的 margin 和 padding 设置为零来覆盖这些浏览器样式。这可以分别进行，也可以使用通用选择器对所有元素进行设置：

```css
* {
  margin: 0;
  padding: 0;
}
```

元素的总宽度应该这样计算：

元素总宽度 = 宽度 + 左内边距 + 右内边距 + 左边框 + 右边框 + 左外边距 + 右外边距

元素的总高度应该这样计算：

元素总高度 = 高度 + 上内边距 + 下内边距 + 上边框 + 下边框 + 上外边距 + 下外边距

> 假设框的每个边上有 10 个像素的外边距和 5 个像素的内边距。如果希望这个元素框达到 100 个像素，就需要将内容的宽度设置为 70 像素，请看下图：
>
> <img src="https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409211405110.gif" alt="CSS 框模型实例" style="zoom:120%;background-color:gray;border-radius: 5px;" />



### CSS 边框

CSS `border` 属性允许您指定元素边框的样式、宽度和颜色。

`border-style` 属性指定要显示的边框类型。属性可以设置**一到四个值**（用于上边框、右边框、下边框和左边框）。

允许以下值：

- `dotted` - 定义点线边框
- `dashed` - 定义虚线边框
- `solid` - 定义实线边框
- `double` - 定义双边框
- `groove` - 定义 3D 坡口边框。效果取决于 border-color 值
- `ridge` - 定义 3D 脊线边框。效果取决于 border-color 值
- `inset` - 定义 3D inset 边框。效果取决于 border-color 值
- `outset` - 定义 3D outset 边框。效果取决于 border-color 值
- `none` - 定义无边框
- `hidden` - 定义隐藏边框

> ```css
> p.dotted {border-style: dotted;}
> p.dashed {border-style: dashed;}
> p.solid {border-style: solid;}
> p.double {border-style: double;}
> p.groove {border-style: groove;}
> p.ridge {border-style: ridge;}
> p.inset {border-style: inset;}
> p.outset {border-style: outset;}
> p.none {border-style: none;}
> p.hidden {border-style: hidden;}
> p.mix {border-style: dotted dashed solid double;}
> ```
>
> <p style="border-style: dotted;">点状边框</p>
> <p style="border-style: dashed;">虚线边框。</p>
> <p style="border-style: solid;">实线边框。</p>
> <p style="border-style: double;">双线边框。</p>
> <p style="border-style: groove;">凹槽边框。</p>
> <p style="border-style: ridge;">垄状边框。</p>
> <p style="border-style: inset;">3D inset 边框。</p>
> <p style="border-style: outset;">3D outset 边框。</p>
> <p style="border-style: none;">无边框。</p>
> <p style="border-style: hidden;">隐藏边框。</p>
> <p style="border-style: dotted dashed solid double;">混合边框。</p>

`border-width` 属性指定四个边框的宽度。属性可以设置一到四个值（用于上边框、右边框、下边框和左边框）

可以将宽度设置为特定大小（以 px、pt、cm、em 计），也可以使用以下三个预定义值之一：thin、medium 或 thick

> ```css
> p.one {
>   border-style: solid;
>   border-width: 5px;
> }
> 
> p.two {
>   border-style: solid;
>   border-width: medium;
> }
> 
> p.three {
>   border-style: solid;
>   border-width: 25px 10px 4px 35px; /* 上边框 25px，右边框 10px，下边框 4px，左边框 35px */
> }
> ```

`border-color` 属性用于设置四个边框的颜色。属性可以设置一到四个值（用于上边框、右边框、下边框和左边框）。

可以通过以下方式设置颜色：

- name - 指定颜色名，比如 "red"
- HEX - 指定十六进制值，比如 "#ff0000"
- RGB - 指定 RGB 值，比如 "rgb(255,0,0)"
- HSL - 指定 HSL 值，比如 "hsl(0, 100%, 50%)"
- transparent

**注释：**如果未设置 `border-color`，则它将继承元素的颜色。

`单独的边`可以为每一侧指定不同的边框。

> ```css
> p {
>   border-top-style: dotted;
>   border-right-style: solid;
>   border-bottom-style: dotted;
>   border-left-style: solid;
> }
> ```

如果 `border-style` 属性设置四个值：

`border-style: dotted solid double dashed;`

- 上边框是虚线
- 右边框是实线
- 下边框是双线
- 左边框是虚线

如果 `border-style` 属性设置三个值：

`border-style: dotted solid double;`

- 上边框是虚线
- 右和左边框是实线
- 下边框是双线

如果 `border-style` 属性设置两个值：

`border-style: dotted solid;`

- 上和下边框是虚线
- 右和左边框是实线

如果 `border-style` 属性设置一个值：

`border-style: dotted;`

- 四条边均为虚线



**边框属性简写**

为了缩减代码，也可以在一个属性中指定所有单独的边框属性。

`border` 属性是以下各个边框属性的简写属性：

- `border-width`
- `border-style`（必需）
- `border-color`

> ```css
> p {
>   border: 5px solid red;
> }
> ```
>
> 左边框
>
> ```css
> p {
>   border-left: 6px solid red;
>   background-color: lightgrey;
> }
> ```
>
> 下边框
>
> ```css
> p {
>   border-bottom: 6px solid red;
>   background-color: lightgrey;
> }
> ```

**圆角边框**

`border-radius` 属性用于向元素添加圆角边框

```html
p {
  border: 2px solid red;
  border-radius: 5px;
}
```



### CSS 外边距

CSS `margin` 属性用于在任何定义的边框之外，为元素周围创建空间。

通过 CSS，您可以完全控制外边距。有一些属性可用于设置元素每侧（上、右、下和左）的外边距。

CSS 拥有用于为元素的每一侧指定外边距的属性：

- `margin-top`
- `margin-right`
- `margin-bottom`
- `margin-left`

所有外边距属性都可以设置以下值：

- auto - 浏览器来计算外边距
- *length* - 以 px、pt、cm 等单位指定外边距
- % - 指定以包含元素宽度的百分比计的外边距
- inherit - 指定应从父元素继承外边距

**提示：**允许负值。

**Margin - 简写属性**

为了缩减代码，可以在一个属性中指定所有外边距属性。

`margin` 属性是以下各外边距属性的简写属性：

- `margin-top`
- `margin-right`
- `margin-bottom`
- `margin-left`

如果 `margin` 属性有四个值：

- margin: 25px 50px 75px 100px;

- - 上外边距是 25px
  - 右外边距是 50px
  - 下外边距是 75px
  - 左外边距是 100px

如果 `margin` 属性设置三个值：

- margin: 25px 50px 75px;

- - 上外边距是 25px
  - 右和左外边距是 50px
  - 下外边距是 75px

- 

如果 `margin` 属性设置两个值：

- margin: 25px 50px;

- - 上和下外边距是 25px
  - 右和左外边距是 50px

如果 `margin` 属性设置了一个值：

- margin: 25px;

- - 所有四个外边距都是 25px



**auto 值**

您可以将 margin 属性设置为 `auto`，以使元素在其容器中水平居中。

然后，该元素将占据指定的宽度，并且剩余空间将在左右边界之间平均分配。

> 使用 `margin: auto`：
>
> ```css
> div {
>   width: 300px;
>   margin: auto;
>   border: 1px solid red;
> }
> ```

**inherit 值**

本例使 <p class="ex1"> 元素的左外边距继承自父元素（<div>）：

使用 inherit 值：

```css
div {
  border: 1px solid red;
  margin-left: 100px;
}

p.ex1 {
  margin-left: inherit;
}
```



### CSS 内边距

CSS `padding` 属性用于在任何定义的边界内的元素内容周围生成空间。

通过 CSS，您可以完全控制内边距（填充）。有一些属性可以为元素的每一侧（上、右、下和左侧）设置内边距。

CSS 拥有用于为元素的每一侧指定内边距的属性：

- `padding-top`
- `padding-right`
- `padding-bottom`
- `padding-left`

所有内边距属性都可以设置以下值：

- *length* - 以 px、pt、cm 等单位指定内边距
- % - 指定以包含元素宽度的百分比计的内边距
- inherit - 指定应从父元素继承内边距

**提示：**不允许负值。

**Padding - 简写属性**

为了缩减代码，可以在一个属性中指定所有内边距属性。

`padding` 属性是以下各内边距属性的简写属性：

- `padding-top`
- `padding-right`
- `padding-bottom`
- `padding-left`

**内边距和元素宽度**

CSS `width` 属性指定元素内容区域的宽度。内容区域是元素（盒模型）的内边距、边框和外边距内的部分。

因此，如果元素拥有指定的宽度，则添加到该元素的内边距会添加到元素的总宽度中。这通常是不希望的结果。

> ### 实例
>
> 在这里，<div> 元素的宽度为 300px。但是，<div> 元素的实际宽度将是 350px（300px + 左内边距 25px + 右内边距 25px）：
>
> ```css
> div {
>   width: 300px;
>   padding: 25px;
> }
> ```



### CSS 尺寸属性

| 属性       | 描述                 |
| :--------- | :------------------- |
| height     | 设置元素的高度。     |
| width      | 设置元素的宽度。     |
| max-height | 设置元素的最大高度。 |
| max-width  | 设置元素的最大宽度。 |
| min-height | 设置元素的最小高度。 |
| min-width  | 设置元素的最小宽度。 |

height 和 width 属性不包括内边距、边框或外边距。它设置的是元素内边距、边框以及外边距内的区域的高度或宽度。

`height` 和 `width` 属性可设置如下值：

- `auto` - 默认。浏览器计算高度和宽度。
- `*length*` - 以 px、cm 等定义高度/宽度。
- `%` - 以包含块的百分比定义高度/宽度。
- `initial` - 将高度/宽度设置为默认值。
- `inherit` - 从其父值继承高度/宽度。



`max-width` 属性用于设置元素的最大宽度。

可以用长度值（例如 px、cm 等）或包含块的百分比（％）来指定 max-width（最大宽度），也可以将其设置为 none（默认值。意味着没有最大宽度）。

若设置 `width:500px;`  当浏览器窗口小于元素的宽度（500px）时，浏览器会将水平滚动条添加到页面。

若设置 `max-width:500px;`  当浏览器窗口小于元素的宽度（500px）时，元素宽度将随浏览器大小改变。

**提示：**将浏览器窗口拖动到小于500px的宽度，以查看两个 div 之间的区别！



## CSS轮廓

轮廓是在元素周围绘制的一条线，在边框之外，以凸显元素。

CSS 拥有如下轮廓属性：

- `outline-style`
- `outline-color`
- `outline-width`
- `outline-offset`
- `outline`

**注意：**轮廓与[边框](https://www.w3school.com.cn/css/css_border.asp)不同！不同之处在于：轮廓是在元素边框之外绘制的，并且可能与其他内容重叠。同样，轮廓也不是元素尺寸的一部分；元素的总宽度和高度不受轮廓线宽度的影响。

### CSS 轮廓样式

outline-style 属性指定轮廓的样式，并可设置如下值：

- `dotted` - 定义点状的轮廓。
- `dashed` - 定义虚线的轮廓。
- `solid` - 定义实线的轮廓。
- `double` - 定义双线的轮廓。
- `groove` - 定义 3D 凹槽轮廓。
- `ridge` - 定义 3D 凸槽轮廓。
- `inset` - 定义 3D 凹边轮廓。
- `outset` - 定义 3D 凸边轮廓。
- `none` - 定义无轮廓。
- `hidden` - 定义隐藏的轮廓。

### CSS 轮廓宽度

`outline-width` 属性指定轮廓的宽度，并可设置如下值之一：

- thin（通常为 1px）
- medium（通常为 3px）
- thick （通常为 5px）
- 特定尺寸（以 px、pt、cm、em 计）

### CSS 轮廓颜色

`outline-color` 属性用于设置轮廓的颜色。

可以通过以下方式设置颜色：

- *name* - 指定颜色名，比如 "red"
- HEX - 指定十六进制值，比如 "#ff0000"
- RGB - 指定 RGB 值，比如 "rgb(255,0,0)"
- HSL - 指定 HSL 值，比如 "hsl(0, 100%, 50%)"
- invert - 执行颜色反转（确保轮廓可见，无论是什么颜色背景）

**反转颜色**

使用 `outline-color: invert`执行颜色反转。这样可以确保无论颜色背景如何，轮廓都是可见的

### CSS 轮廓简写

`outline` 属性是用于设置以下各个轮廓属性的简写属性：

- `outline-width`
- `outline-style`（必需）
- `outline-color`

从上面的列表中，`outline` 属性可指定一个、两个或三个值。值的顺序无关紧要。

### CSS 轮廓偏移

`outline-offset` 属性在元素的轮廓与边框之间添加空间。元素及其轮廓之间的空间是透明的。



## CSS 文本

### 文本颜色

`color` 属性用于设置文本的颜色。

`background-color`背景色

### 文本对齐

**水平对齐**

`text-align` 属性用于设置文本的水平对齐方式。

文本可以左对齐或右对齐，或居中对齐 ：left，right，center

当 `text-align` 属性设置为 "justify" 后，将拉伸每一行，以使每一行具有相等的宽度，并且左右边距是直的

**垂直对齐**

`vertical-align` 属性设置元素的垂直对齐方式：top，middle，bottom

### 文本方向

`direction` 和 `unicode-bidi` 属性可用于更改元素的文本方向：

### 文字装饰

`text-decoration` 属性用于设置或删除文本装饰。

`text-decoration: none;` 通常用于从<u>链接</u>上删除下划线：

```css
h1 {
  text-decoration: overline;
}

h2 {
  text-decoration: line-through;
}

h3 {
  text-decoration: underline;
}
```

### 文本转换

`text-transform` 属性用于指定文本中的大写和小写字母。

它可用于将所有内容转换为大写或小写字母，或将每个单词的首字母大写：uppercase，lowercase，capitalize

### 文字间距

**文字缩进**

`text-indent` 属性用于指定文本第一行的缩进：

```css
p {
  text-indent: 50px;
}
```

**字母间距**

`letter-spacing` 属性用于指定文本中字符之间的间距。

```css
h1 {
  letter-spacing: 3px;
}

h2 {
  letter-spacing: -3px;
}
```

**行高**

`line-height` 属性用于指定行之间的间距：

**字间距**

`word-spacing` 属性用于指定文本中单词之间的间距。

**空白**

`white-space` 属性指定元素内部空白的处理方式。

此例演示如何禁用元素内的文本换行：

```css
p {
  white-space: nowrap;
}
```

### 文本阴影

`text-shadow` 属性为文本添加阴影。

指定水平阴影（2px）垂直阴影（2px）模糊效果（5px）阴影颜色（红色）：

```css
h1 {
  text-shadow: 2px 2px 5px red;
}
```

<h1 style="text-shadow: 2px 2px 5px red;"">text-shadow: 2px 2px 5px red;



## CSS 字体

### 通用字体族

在 CSS 中，有五个通用字体族：

- 衬线字体（Serif）- 在每个字母的边缘都有一个小的笔触。它们营造出一种形式感和优雅感。
- 无衬线字体（Sans-serif）- 字体线条简洁（没有小笔画）。它们营造出现代而简约的外观。
- 等宽字体（Monospace）- 这里所有字母都有相同的固定宽度。它们创造出机械式的外观。
- 草书字体（Cursive）- 模仿了人类的笔迹。
- 幻想字体（Fantasy）- 是装饰性/俏皮的字体。

> **提示：**在计算机屏幕上，无衬线字体被认为比衬线字体更易于阅读。

<table class="dataintable">
<tbody><tr>
<th style="width:40%">通用字体族</th>
<th style="width:60%">字体名称实例</th>
</tr>
<tr>
<td>Serif</td>
<td><span style="font-size:150%;font-family:'Times New Roman',serif">Times New Roman</span><br>
<span style="font-size:150%;font-family:Georgia,serif">Georgia</span><br>
<span style="font-size:150%;font-family:Garamond,serif">Garamond</span><br>
</td>
</tr>
<tr>
<td>Sans-serif</td>
<td><span style="font-size:150%;font-family:Arial,sans-serif">Arial</span><br>
<span style="font-size:150%;font-family:Verdana,sans-serif">Verdana</span><br>
<span style="font-size:150%;font-family:Helvetica,sans-serif">Helvetica</span><br>
</td>
</tr>
<tr>
<td>Monospace</td>
<td><span style="font-size:150%;font-family:'Courier New',monospace">Courier New</span><br>
<span style="font-size:150%;font-family:'Lucida Console',monospace">Lucida Console</span><br>
<span style="font-size:150%;font-family:Monaco,monospace">Monaco</span>
</td>
</tr>
<tr>
<td>Cursive</td>
<td><span style="font-size:150%;font-family:'Brush Script MT',cursive">Brush Script MT</span><br>
<span style="font-size:150%;font-family:'Lucida Handwriting',cursive">Lucida Handwriting</span><br>
</td>
</tr>
<tr>
<td>Fantasy</td>
<td><span style="font-size:150%;font-family:Copperplate,fantasy">Copperplate</span><br>
<span style="font-size:150%;font-family:Papyrus,fantasy">Papyrus</span>
</td>
</tr>
</tbody></table>

### font-family 属性

在 CSS 中，我们使用 font-family 属性规定文本的字体。

font-family 属性应包含多个字体名称作为“后备”系统，以确保浏览器/操作系统之间的最大兼容性。请以您需要的字体开始，并以通用系列结束（如果没有其他可用字体，则让浏览器选择通用系列中的相似字体）。字体名称应以逗号分隔。

```css
.p1 {
  font-family: "Times New Roman", Times, serif;
}
```

### 字体样式

`font-style` 属性主要用于指定斜体文本。

此属性可设置三个值：

- normal - 文字正常显示
- italic - 文本以斜体显示
- oblique - 文本为“倾斜”（倾斜与斜体非常相似，但支持较少）

### 字体粗细

`font-weight` 属性指定字体的粗细：

normal，lighter，bold和数字

### 字体变体

`font-variant` 属性指定是否以 small-caps 字体（小型大写字母）显示文本。

在 small-caps 字体中，所有小写字母都将转换为大写字母。但是，转换后的大写字母的字体大小小于文本中原始大写字母的字体大小。

<p style="font-variant: small-caps;">My name is Bill Gates.</p>

### 字体大小

`font-size` 属性设置文本的大小。

在网页设计中，能够管理文本大小很重要。但是，不应使用调整字体大小来使段落看起来像标题，或是使标题看起来像段落。

请始终使用正确的 HTML 标签，例如 <h1> - <h6> 用于标题，而 <p> 仅用于段落。

font-size 值可以是绝对或相对大小。

绝对尺寸：

- 将文本设置为指定大小
- 不允许用户在所有浏览器中更改文本大小（可访问性不佳）
- 当输出的物理尺寸已知时，绝对尺寸很有用

相对尺寸：

- 设置相对于周围元素的大小
- 允许用户在浏览器中更改文本大小

> **注释：**如果您没有指定字体大小，则普通文本（如段落）的默认大小为 16px（16px = 1em）。

**以像素设置字体大小**

使用像素设置文本大小可以完全控制文本大小：

```css
h1 {
  font-size: 40px;
}
```

> **提示：**如果您使用了像素，则仍然可以使用缩放工具来调整整个页面的大小。

**用 em 设置字体大小**

为了允许用户调整文本大小（在浏览器菜单中），许多开发人员使用 em 而不是像素。

建议使用 em 尺寸单位。

1em 等于当前字体大小。浏览器中的默认文本大小为 16px。因此，默认大小 1em 为 16px。

可以使用这个公式从像素到 em 来计算大小：pixels/16=em。

```css
h1 {
  font-size: 2.5em; /* 40px/16=2.5em */
}
```

**使用百分比和 Em 的组合**

```css
body {
  font-size: 100%;
}

h1 {
  font-size: 2.5em;
}
```

**响应式字体大小**

可以使用 `vw` 单位设置文本大小，它的意思是“视口宽度”（"viewport width"）。

这样，文本大小将遵循浏览器窗口的大小，随浏览器窗口的大小改变而改变文本大小

```html
<h1 style="font-size:10vw;">响应式文本</h1>
```

### 谷歌字体

如果您不想使用 HTML 中的任何标准字体，则可以使用 Google Fonts API 向页面添加数百种其他字体。

只需添加一个样式表链接并引用您选择的字体系列：

```html
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Sofia">
<style>
body {
  font-family: "Sofia";
  font-size: 22px;
}
</style>
</head>
<body>

<h1>Sofia Font</h1>
<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.</p>

</body>
</html>
```

### 简写字体属性

为了缩短代码，也可以在一个属性中指定所有单个字体属性。

`font` 属性是以下属性的简写属性：

- `font-style`
- `font-variant`
- `font-weight`
- `font-size/line-height`
- `font-family`

> **注意：**`font-size` 和 `font-family` 的值是必需的。如果缺少其他值之一，则会使用其默认值。

## CSS 图标

向 HTML 页面添加图标的最简单方法是使用图标库，比如 Font Awesome。

将指定的图标类的名称添加到任何行内 HTML 元素（如 <i> 或 <span>）。

下面的图标库中的所有图标都是可缩放矢量，可以使用 CSS进行自定义（大小、颜色、阴影等）。

**Bootstrap 图标**

如需使用 Bootstrap glyphicons，请在 HTML 页面的 <head> 部分内添加这行：

```html
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
```

**Google 图标**

如需使用 Google 图标，请在HTML页面的 <head> 部分中添加以下行：

```html
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
```

**提示：**无需下载或安装！



## CSS 链接

**设置链接样式**

链接可以使用任何 CSS 属性（例如 `color`、`font-family`、`background` 等）来设置样式。

**根据链接状态设置链接的不同样式。**

四种链接状态分别是：

- `a:link` - 正常的，未访问的链接
- `a:visited` - 用户访问过的链接
- `a:hover` - 用户将鼠标悬停在链接上时
- `a:active` - 链接被点击时

```css
/* 未被访问的链接 */
a:link {
  color: red;
}

/* 已被访问的链接 */
a:visited {
  color: green;
}

/* 将鼠标悬停在链接上 */
a:hover {
  color: hotpink;
}

/* 被选择的链接 */
a:active {
  color: blue;
}
```

> 如果为多个链接状态设置样式，请遵循如下顺序规则：
>
> - a:hover 必须 a:link 和 a:visited 之后
> - a:active 必须在 a:hover 之后



## CSS 列表

在 HTML 中，列表主要有两种类型：

- 无序列表（<ul>）- 列表项用的是项目符号标记
- 有序列表（<ol>）- 列表项用的是数字或字母标记

CSS 列表属性使您可以：

- 为有序列表设置不同的列表项标记
- 为无序列表设置不同的列表项标记
- 将图像设置为列表项标记
- 为列表和列表项添加背景色

```css
ul.a {
  list-style-type: circle;
}

ul.b {
  list-style-type: square;
}

ol.c {
  list-style-type: upper-roman;
}

ol.d {
  list-style-type: lower-alpha;
}
```

### **图像作为列表项标记**

`list-style-image` 属性将图像指定为列表项标记：

```css
ul {
  list-style-image: url('sqpurple.gif');
}
```

### **定位列表项标记**

`list-style-position` 属性指定列表项标记（项目符号）的位置。

"list-style-position: outside;" 表示项目符号点将在列表项之外。列表项每行的开头将垂直对齐。这是默认的：

- Coffee - 用烘焙过的咖啡豆制成的冲泡饮料
- Tea
- Coca-cola

`"list-style-position: inside;"` 表示项目符号将在列表项内。由于它是列表项的一部分，因此它将成为文本的一部分，并在开头推开文本：

- Coffee - 用烘焙过的咖啡豆制成的冲泡饮料
- Tea
- Coca-cola

```css
ul.a {
  list-style-position: outside;
}

ul.b {
  list-style-position: inside;
}
```

### 删除默认设置

`list-style-type:none` 属性也可以用于删除标记/项目符号。请注意，列表还拥有默认的外边距和内边距。要删除此内容，请在 <ul> 或 <ol> 中添加 `margin:0` 和 `padding:0` ：

```css
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
}
```

### 简写属性

`list-style` 属性是一种简写属性。它用于在一条声明中设置所有列表属性：

```css
ul {
  list-style: square inside url("sqpurple.gif");
}
```

在使用简写属性时，属性值的顺序为：

- `list-style-type`（如果指定了 list-style-image，那么在由于某种原因而无法显示图像时，会显示这个属性的值）
- `list-style-position`（指定列表项标记应显示在内容流的内部还是外部）
- `list-style-image`（将图像指定为列表项标记）

如果缺少上述属性值之一，则将插入缺失属性的默认值（如果有）。

### 设置列表的颜色样式

我们还可以使用颜色设置列表样式，使它们看起来更有趣。

添加到 <ol> 或 <ul> 标记的任何样式都会影响整个列表，而添加到 <li> 标记的属性将影响各个列表项



## CSS 表格

### 表格边框

如需在 CSS 中设置表格边框，请使用 `border` 属性。

### 全宽表格

在某些情况下，上表似乎很小。如果您需要一个可以覆盖整个屏幕（全宽）的表格，请为 <table> 元素添加 width: 100%

```css
table {
  width: 100%;
}
```

### 双边框

默认情况下的表格有双边框。这是因为表格和 th、td 元素都有单独的边框。

### 合并表格边框

`border-collapse` 属性设置是否将表格边框折叠为单一边框

```css
table {
  border-collapse: collapse;
}

table, th, td {
  border: 1px solid black;
}
```

如果只希望表格周围有边框，则仅需为 <table> 指定 `border` 属性

```css
table {
  border: 1px solid black;
}
```

### 表格宽度和高度

表格的宽度和高度由 `width` 和 `height` 属性定义。

下例将表的宽度设置为 100％，将 <th> 元素的高度设置为 50px：

```css
table {
  width: 100%;
}

th {
  height: 50px;
}
```

### 水平对齐

`text-align` 属性设置 <th> 或 <td> 中内容的水平对齐方式（左、右或居中）。

默认情况下，<th> 元素的内容居中对齐，而 <td> 元素的内容左对齐。

 <td> 元素居中center，左对齐left，右对齐right

### 垂直对齐

`vertical-align` 属性设置 <th> 或 <td> 中内容的垂直对齐方式（上、下或居中）。

默认情况下，表中内容的垂直对齐是居中（<th> 和 <td> 元素都是）。

### 表格内边距

如需控制边框和表格内容之间的间距，请在 <td> 和 <th> 元素上使用 `padding` 属性

### 水平分隔线

向 <th> 和 <td> 添加 `border-bottom` 属性，以实现水平分隔线

```css
th, td {
  border-bottom: 1px solid #ddd;
}
```

### 可悬停表格

在 <tr> 元素上使用 `:hover` 选择器，以突出显示鼠标悬停时的表格行

```css
tr:hover {background-color: #f5f5f5;}
```

### 条状表格

为了实现斑马纹表格效果，请使用 `nth-child()` 选择器，并为所有偶数（或奇数）表行添加 `background-color`

```css
tr:nth-child(even) {background-color: #f2f2f2;}
```

## 表格颜色

下例指定了 <th> 元素的背景颜色和文本颜色

```css
th {
  background-color: #4CAF50;
  color: white;
}
```

### 响应式表格

如果屏幕太小而无法显示全部内容，则响应式表格会显示水平滚动条

在 <table> 元素周围添加带有 `overflow-x:auto` 的容器元素（例如 <div>），以实现响应式效果：

```html
<div style="overflow-x:auto;">

<table>
... table content ...
</table>

</div>
```
