[TOC]

------

<center><font size=7><b>HTML学习</center></font></center>

---



## HTML 简介

中文名称：***超文本标记语言***
英语名称：***HyperText Markup Language***

就其核心而言，HTML 是一种相当简单的、由不同元素组成的**<u>`标记语言`</u>**，

**HTML 是用来描述网页的一种语言。**

- HTML 不是一种编程语言，而是一种`标记语言`
- 标记语言是一套**标记标签** (markup tag)
- HTML 使用标记标签来**描述**网页
- HTML 文档包含了HTML **标签**及**文本**内容
- HTML文档也叫做 **web 页面**
- HTML可以被应用于文本片段，使文本在文档中具有不同的含义（它是段落吗？它是项目列表吗？它是表格吗？），将文档结构化为逻辑块（文档是否有头部？有三列内容？有一个导航菜单？），并且可以将图片，影像等内容嵌入到页面中。它可以被应用于文本片段，使文本在文档中具有不同的含义（它是段落吗？它是项目列表吗？它是表格吗？），将文档结构化为逻辑块（文档是否有头部？有三列内容？有一个导航菜单？），并且可以将图片，影像等内容嵌入到页面中。

> **备注：** HTML 标签不区分大小写。也就是说，输入标签时既可以使用大写字母也可以使用小写字母。不过，从一致性、可读性来说，最好仅使用小写字母。
>
> **注意:**HTML中不支持 空格、回车、制表符，它们都会被解析成一个空白字符。
>
> **\<!DOCTYPE html>**是 HTML5 标准网页声明，全称为 Document Type HyperText Mark-up Language，是一条标示语言。支持 HTML5 标准的主流浏览器都认识这个声明。表示网页采用 HTML5，**\<!DOCTYPE html>** 



### HTML文档的后缀名

- **.html**
- **.htm**

**htm 与 html 的区别**

前者是超文本标记(Hypertext Markup)，后者是超文本标记语言(Hypertext Markup Language)

可以说 htm = html。这两种都是静态网页文件的扩展名，扩展名可以互相更换而不会引起错误

**两种后缀出现的原因**

htm 是来源于老的 8.3 文件格式，DOS 操作系统只能支持长度为三位的后缀名，所以是 htm,但在 windows 下无所谓 HTM 与 HTML，html 是为长文件名的格式命名的。所以 htm 是为了兼容过去的DOS命名格式存在的，在效果上没有区别的。以前 htm 和 html 作为不同的服务器上的超文本文件，但现在通用。



### HTML版本

从初期的网络诞生后，已经出现了许多HTML版本:

| 版本      | 发布时间 |
| :-------- | :------- |
| HTML      | 1991     |
| HTML+     | 1993     |
| HTML 2.0  | 1995     |
| HTML 3.2  | 1997     |
| HTML 4.01 | 1999     |
| XHTML 1.0 | 2000     |
| HTML5     | 2012     |
| XHTML5    | 2013     |



## HTML 编辑器

- **推荐使用**
  - **Visual Studio Code**（简称 VS Code）
- **相关插件**
  - **Live Server** / **Live Preview** : 实时预览
  - **Fitten Code**  : HTML AI 编程助手



##  HTML 标签 (HTML Tag)

- HTML标签 是由 *尖括号* 包围的关键词，比如 **\<html>**
- HTML标签 通常是 *成对出现*的，比如 **\<b>** 和 **\</b>**

### HTML 提示：使用小写标签

HTML 标签对大小写不敏感：<P> 等同于 <p>。许多网站都使用大写的 HTML 标签。

万维网联盟（W3C）在 HTML 4 中**推荐**使用小写，而在未来 (X)HTML 版本中**强制**使用小写。

### HTML 标签简写及全称

下表列出了 HTML 标签简写及全称：

| 标签        | 英文全称                  | 中文说明                             |
| :---------- | :------------------------ | :----------------------------------- |
| a           | Anchor                    | 锚：使被标签包裹的内容成为一个超链接 |
| abbr        | Abbreviation              | 缩写词                               |
| acronym     | Acronym                   | 取首字母的缩写词                     |
| address     | Address                   | 地址                                 |
| alt         | alter                     | 替用(一般是图片显示不出的提示)       |
| b           | Bold                      | 粗体（文本）                         |
| bdo         | Bi-Directional Override   | 文本显示方向                         |
| big         | Big                       | 变大（文本）                         |
| blockquote  | Block Quotation           | 区块引用语                           |
| br          | Break                     | 换行                                 |
| cell        | cell                      | 巢                                   |
| cellpadding | cellpadding               | 巢补白                               |
| cellspacing | cellspacing               | 巢空间                               |
| center      | Centered                  | 居中（文本）                         |
| cite        | Citation                  | 引用                                 |
| code        | Code                      | 源代码（文本）                       |
| dd          | Definition Description    | 定义描述                             |
| del         | Deleted                   | 删除（的文本）                       |
| dfn         | Defines a Definition Term | 定义定义条目                         |
| div         | Division                  | 分隔                                 |
| dl          | Definition List           | 定义列表                             |
| dt          | Definition Term           | 定义术语                             |
| em          | Emphasized                | 加重（文本）                         |
| font        | Font                      | 字体                                 |
| h1~h6       | Header 1 to Header 6      | 标题1到标题6                         |
| hr          | Horizontal Rule           | 水平尺                               |
| href        | hypertext reference       | 超文本引用                           |
| i           | Italic                    | 斜体（文本）                         |
| iframe      | Inline frame              | 定义内联框架                         |
| ins         | Inserted                  | 插入（的文本）                       |
| kbd         | Keyboard                  | 键盘（文本）                         |
| li          | List Item                 | 列表项目                             |
| nl          | navigation lists          | 导航列表                             |
| ol          | Ordered List              | 排序列表                             |
| optgroup    | Option group              | 定义选项组                           |
| p           | Paragraph                 | 段落                                 |
| pre         | Preformatted              | 预定义格式（文本 ）                  |
| q           | Quotation                 | 引用语                               |
| rel         | Reload                    | 加载                                 |
| s/ strike   | Strikethrough             | 删除线                               |
| samp        | Sample                    | 示例（文本                           |
| small       | Small                     | 变小（文本）                         |
| span        | Span                      | 范围                                 |
| src         | Source                    | 源文件链接                           |
| strong      | Strong                    | 加重（文本）                         |
| sub         | Subscripted               | 下标（文本）                         |
| sup         | Superscripted             | 上标（文本）                         |
| td          | table data cell           | 表格中的一个单元格                   |
| th          | table header cell         | 表格中的表头                         |
| tr          | table row                 | 表格中的一行                         |
| tt          | Teletype                  | 打印机（文本）                       |
| u           | Underlined                | 下划线（文本）                       |
| ul          | Unordered List            | 不排序列表                           |
| var         | Variable                  | 变量（文本）                         |



## HTML 元素

###  HTML元素组成

HTML 元素以**开始标签**起始，以**结束标签**终止

<img src="https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409211339224.png" style="zoom: 80%;" />

整个元素即指 **开始标签**、**内容**、**结束标签** 三部分组成的整体。

- **开始标签**（Opening tag）：包含元素的名称（本例为 *p*），被左、右角括号所包围。开头标签标志着元素开始或开始生效的地方。在这个示例中，它在段落文本的开始之前。
- **内容**（Content）：**元素的内容**是开始标签与结束标签之间的内容，本例中就是段落的文本。
- **结束标签**（Closing tag）：与开始标签相似，只是其在元素名之前包含了一个斜杠。这标志着该元素的结束。没有包含关闭标签是一个常见的初学者错误，它可能会产生奇特的结果。
- 开始和结束标签也被称为 ***开放标签*** 和 ***闭合标签***



### 嵌套元素

你也可以把元素放到其他元素之中——这被称作*嵌套*。

正确例：

```html
<p>My cat is <strong>very</strong> grumpy.</p>
```

错误例：

```html
<p>My cat is <strong>very grumpy.</p></strong>
```

你需要确保元素被正确的嵌套：在上面的例子中我们先打开 `p` 元素，然后才打开 `strong` 元素，因此必须先将 `strong` 元素关闭，然后再去关闭 `p` 元素。

**所有的元素都需要正确的打开和关闭，这样才能按你所想的方式展现**。由于上述示例中的那种重叠，浏览器不得不猜测你的意图。这种猜测可能会导致意想不到的结果。



### 块级元素和内联元素

在 HTML 中有两种你需要知道的重要元素类别，**块级元素** 和 **内联元素**。

- `块级元素`在页面中以块的形式展现。一个块级元素出现在它前面的内容之后的新行上。任何跟在块级元素后面的内容也会出现在新的行上。块级元素通常是页面上的结构元素。例如，一个块级元素可能代表标题、段落、列表、导航菜单或页脚。一个块级元素不会嵌套在一个内联元素里面，但它可能嵌套在另一个块级元素里面。
- `内联元素`通常出现在块级元素中并环绕文档内容的一小部分，而不是一整个段落或者一组内容。内联元素不会导致文本换行。它通常与文本一起使用。
  例如，<a> 元素创建一个超链接，<em> 和 <strong> 等元素创建强调。

考虑如下示例：

```html
<em>第一</em><em>第二</em><em>第三</em>

<p>第四</p>
<p>第五</p>
<p>第六</p>
```

![image-20240518015215471](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409211339803.png)

`<em>` 是一个**块级元素**，所以第二行代码中的每个 *p* 元素分别都另起了新的一行展现，并且每个段落间都有一些间隔（这是因为默认的浏览器有着展示 `<p>` 元素的默认 **<u>CSS 样式</u>** ）。

> **备注：** HTML5 重新定义了元素的类别：见[元素内容分类](https://html.spec.whatwg.org/multipage/indices.html#element-content-categories)。尽管这些新的定义更精确，但却比上述的“块级元素”和“内联元素”更难理解，因此在之后的讨论中仍使用旧的定义。
>
> **备注：** 在这篇文章中提到的“块”和“内联”，不应该与 [CSS 盒子的类型](https://developer.mozilla.org/zh-CN/docs/Learn/CSS/Building_blocks/The_box_model#块级盒子（block_box）_和_内联盒子（inline_box）)中的同名术语相混淆。尽管它们默认是相关的，但改变 CSS 显示类型并不会改变元素的分类，也不会影响它可以包含和被包含于哪些元素。防止这种混淆也是 HTML5 摒弃这些术语的原因之一。
>
> **备注：** 你可以查阅包含了块级元素和内联元素列表的参考页面。参见[块级元素](https://developer.mozilla.org/zh-CN/docs/Glossary/Block-level_content)和[内联元素](https://developer.mozilla.org/zh-CN/docs/Glossary/Inline-level_content)页面。



### 空元素

不是所有元素都拥有开始标签、内容和结束标签。一些元素只有一个标签，通常用来在此元素所在位置插入/嵌入一些东西。这些元素被称为 空元素。

空元素**在开始标签中进行关闭**（以开始标签的结束而结束）

​	例如：元素 `<img>` 是用来在页面插入一张指定的图片。元素 `<br>` 是换行

```html
<img
  src="https://roy-tian.github.io/learning-area/extras/getting-started-web/beginner-html-site/images/firefox-icon.png"
  alt="Firefox 图标" />
```

效果如下：

<img
  src="https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409211339619.png"
  alt="Firefox 图标" />

> **备注：** HTML 中，无需在一个空元素的标签末尾添加 `/`，例如 `<img src="images/cat.jpg" alt="cat" />`。然而，这也是一种有效的语法，当你希望你的 HTML 是有效的 XML 时，这么做也没问题。



## HTML 属性

- HTML 元素可以设置**属性**
- 属性可以在元素中添加**附加信息**
- 属性一般描述于**开始标签**

![含有‘class="editor-note"’属性的段落标签](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409211339926.png)

属性包含元素的额外信息，这些信息不会出现在实际的内容中。在上述例子中，这个 **`class`** 属性是一个识别名称，以后为元素设置样式信息时更加方便。



### 属性组成

- 一个空格，它在属性和元素名称之间。如果一个元素具有多个属性，则每个属性之间必须由空格分隔。
- 属性名称，后面跟着一个等于号。
- 一个属性值，由一对引号（""）引起来。（双引号是最常用的，不过使用单引号也没有问题。）

属性总是以名称/值对的形式出现，**比如：name="value"**。



### 布尔属性

有时你会看到没有值的属性，这也是完全可以接受的。这些属性被称为布尔属性。布尔属性只能有一个值，这个值一般与属性名称相同。例如，考虑 [`disabled`](https://developer.mozilla.org/zh-CN/docs/Web/HTML/Element/input#disabled) 属性，你可以将其分配给表单输入元素。用它来禁用表单输入元素，这样用户就不能输入了。被禁用的元素通常有一个灰色的外观。示例如下：

```html
<!-- 使用 disabled 属性来防止终端用户输入文本到输入框中 -->
<input type="text" disabled />

<!-- 下面这个输入框不包含 disabled 属性，所以用户可以向其中输入 -->
<input type="text" />
```



## HTML 网页结构

<table border="1">
	<td>
		<b>&lt;html&gt;</b>
		<table border="1">
			<td  bgcolor="gray">
				<b>&lt;head&gt;</b>
				<table border="1">
				<td bgcolor="#737373">
					<b>&lt;title&gt;页面标题&lt;title&gt;</b>
				</td>
				</table>
				<b>&lt;/head&gt;</b>
			</td>
		</table>
		<table border="1">
			<td bgcolor="gray">
				<b>&lt;body&gt;</b>
				<table border="1">
				<td bgcolor="#737373">
					<b>&lt;h1&gt;这是一个标题&lt;/h1&gt;</b>
				</td>
				</table>
				<table border="1">
				<td bgcolor="#737373">
					<b>&lt;p&gt;这是一个段落&lt;/p&gt;</b>
				</td>
				</table>
				<b>&lt;/body&gt;</b>
			</td>
		</table>
		<b>&lt;/html&gt;</b>
	</td>
</table>


> 只有 `<body> 区域部分` 才会在浏览器中显示。

举例：

```html
<!--举例-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>
页面标题</title>
</head>
<body>
<h1>我的第一个标题</h1>
<p>我的第一个段落。</p>
</body>
</html>
```

<img src="https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409211340118.jpg" alt="img" style="zoom:50%;" />

> **注：**在浏览器的页面上使用键盘上的 **F12** 按键开启调试模式，就可以看到组成标签。

1. `<!DOCTYPE html>`: 声明文档类型。早期的 HTML（大约 1991-1992 年）文档类型声明类似于链接，规定了 HTML 页面必须遵从的良好规则，能自动检测错误和其他有用的东西。文档类型是一个历史遗留问题，需要包含它才能使其他东西正常工作。现在，只需要知道 `<!DOCTYPE html>` 是最短的有效文档声明！
2. `<html></html>`: html 元素。这个元素包裹了页面中所有的内容，有时被称为根元素。
3. `<head></head>`: head 元素。这个元素是一个容器，它包含了所有你想包含在 HTML 页面中但**不在 HTML 页面中显示**的内容。这些内容包括你想在搜索结果中出现的关键字和页面描述、CSS 样式、字符集声明等等。以后的章节中会学到更多相关的内容。
4. `<meta charset="utf-8">`: meta 元素。这个元素代表了不能由其他 HTML 元相关元素表示的元数据，比如 <base>、<link>、<script>、<style> 或 <title>。`charset`属性将你的文档的字符集设置为 UTF-8，其中包括绝大多数人类书面语言的大多数字符。有了这个设置，页面现在可以处理它可能包含的任何文本内容。没有理由不对它进行设置，它可以帮助避免以后的一些问题。
5. `<title></title>`: title 元素。这设置了页面的标题，也就是出现在该页面加载的浏览器标签中的内容。当页面被加入书签时，页面标题也被用来描述该页面。
6. `<body></body>`: body 元素。包含了你访问页面时*所有*显示在页面上的内容，包含文本、图片、视频、游戏、可播放音频轨道等等。





## HTML 基础格式

### HTML的通用声明

声明位于文档中的最前面的位置，处于 \<html> 标签之前。此标签可告知浏览器文档使用哪种 HTML 或 XHTML 规范。

| **HTML5**  <br/>\<!DOCTYPE html>                             |
| ------------------------------------------------------------ |
| **HTML 4.01**<br/>\<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"<br/>"http://www.w3.org/TR/html4/loose.dtd"> |
| **XHTML 1.0**<br/>\<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"<br/>"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> |
| 查看完整网页声明类型 [DOCTYPE 参考手册](https://www.runoob.com/tags/tag-doctype.html)。 |



### HTML 头部

**HTML \<head> 元素**

\<head> 元素包含了所有的头部标签元素。在 <head>元素中你可以插入脚本（scripts）, 样式文件（CSS），及各种meta信息。

可以添加在头部区域的元素标签为: <title>, <style>, <meta>, <link>, <script>, <noscript> 和 <base>。



**HTML \<meta> 元素**

\<meta> 标签提供了元数据.元数据也不显示在页面上，但会被浏览器解析。

META 元素通常用于指定网页的描述，关键词，文件的最后修改时间，作者，和其他元数据。

元数据可以使用于浏览器（如何显示内容或重新加载页面），搜索引擎（关键词），或其他Web服务。

\<meta> 一般放置于 \<head> 区域

>为搜索引擎定义关键词:
>
>```html
><meta name="keywords" content="HTML, CSS, XML, XHTML, JavaScript">
>```
>
>为网页定义描述内容:
>
>```html
><meta name="description" content="免费 Web & 编程 教程">
>```
>
>定义网页作者:
>
>```html
><meta name="author" content="Runoob">
>```
>
>每30秒钟刷新当前页面:
>
>```html
><meta http-equiv="refresh" content="30">
>```
>
>HTML中文编码：目前在大部分浏览器中，直接输出中文会出现中文乱码的情况，这时候我们就需要在头部将字符声明为 UTF-8 或 GBK。
>
>```html
><meta charset="UTF-8"> 
>```



**HTML \<title> 元素**

\<title> 标签定义了不同文档的标题。在 HTML/XHTML 文档中是必需的。

- 定义了浏览器工具栏的标题
- 当网页添加到收藏夹时，显示在收藏夹中的标题
- 显示在搜索引擎结果页面的标题

```html
<head> 
<meta charset="utf-8"> 
<title>文档标题</title>
</head>
```



**HTML \<base> 元素**

\<base> 标签描述了基本的链接地址/链接目标，该标签作为HTML文档中所有的链接标签的默认链接

```html
<head>
<base href="http://www.runoob.com/images/" target="_blank">
</head>
```



**HTML \<link> 元素**

<link> 标签定义了文档与外部资源之间的关系。

<link> 标签通常用于链接到样式表:

```html
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
</head>
```



**HTML \<style> 元素**

\<style> 标签定义了HTML文档的样式文件引用地址.

在 <style> 元素中你也可以直接添加样式来渲染 HTML 文档:

```html
<head>
<style type="text/css">
body {
    background-color:yellow;
}
p {
    color:blue
}
</style>
</head>
```



**HTML \<script> 元素**

\<script>标签用于加载脚本文件，如： JavaScript。





### HTML 标题

HTML 标题（Heading）是通过<h1> - <h6> 标签来定义的。

```html
<h1>这是一个标题</h1>
<h2>这是一个标题</h2>
<h3>这是一个标题</h3>
```



### HTML 水平线

\<hr> 标签在 HTML 页面中创建水平线。

```html
<p>这是一个段落。</p>
<hr>
<p>这是一个段落。</p>
<hr>
<p>这是一个段落。</p>
```

hr 元素可用于分隔内容。



### HTML 注释

可以将注释插入 HTML 代码中，这样可以提高其可读性，使代码更易被人理解。浏览器会忽略注释，也不会显示它们。

```html
<!-- 这是一个注释 -->
```

**注释:** 开始括号之后（左边的括号）需要紧跟一个叹号 **!** (英文标点符号)，结束括号之前（右边的括号）不需要，合理地使用注释可以对未来的代码编辑工作产生帮助。



### HTML 段落

HTML 段落是通过标签 <p> 来定义的。

```html
<p>这是一个段落。</p>
<p>这是另外一个段落。</p>
```

**注意：**浏览器会自动地在段落的前后添加空行。（</p> 是块级元素）



### HTML 折行

如果您希望在不产生一个新段落的情况下进行换行（新行），请使用  \<br> 标签

```html
<p>这个<br>段落<br>演示了分行的效果</p>
```



### HTML 文本格式化

HTML 使用标签 <b>("bold") 与 <i>("italic") 对输出的文本进行格式, 如：**粗体** or *斜体*

通常标签 <strong> 替换加粗标签 <b> 来使用, <em> 替换 <i>标签使用。
然而，这些标签的含义是不同的：<b> 与<i> 定义粗体或斜体文本。<strong> 或者 <em>意味着你要呈现的文本是重要的，所以要突出显示。现今所有主要浏览器都能渲染各种效果的字体。不过，未来浏览器可能会支持更好的渲染效果。

```html
<b>这个文本是加粗的</b>
<br />
<strong>这个文本是加粗的</strong>
<br />
<big>这个文本字体放大</big>
<br />
<em>这个文本是斜体的</em>
<br />
<i>这个文本是斜体的</i>
<br />
<small>这个文本是缩小的</small>
<br />
<sub>下标</sub>
<br />
<sup>上标</sup>
<br />
<bdo dir="rtl">该段落文字从右到左显示。</bdo>
<br />
<q>该段文字输出时加上双引号</q>
```

使用 `pre` 标签对空行和空格进行控制。输入换行和空格，在输出时不会变。

```html
<pre>
此例演示如何使用 pre 标签
对空行和    空格
进行控制
</pre>
```

"计算机输出" 标签。这些标签常用于显示计算机/编程代码。

```html
<code>计算机输出</code>
<br />
<kbd>键盘输入</kbd>
<br />
<tt>打字机文本</tt>
<br />
<samp>计算机代码样本</samp>
<br />
<var>计算机变量</var>
```

在 HTML 文件中写地址

```html
<address>
Written by <a href="mailto:webmaster@example.com">Jon Doe</a>.<br> 
Visit us at:<br>
Example.com<br>
Box 564, Disneyland<br>
USA
</address>
```

\<blockquote> 元素 （或者 HTML 块级引用元素），代表其中的文字是引用内容。

```html
<blockquote>
        <p>Life is what happens when you're busy making other plans.</p>
        <footer>— John Lennon</footer>
</blockquote>
```

| HTML 文本格式化标签 | 描述         |
| :------------------ | :----------- |
| 定义粗体文本        |              |
| em                  | 定义着重文字 |
| i                   | 定义斜体字   |
| small               | 定义小号字   |
| strong              | 定义加重语气 |
| sub                 | 定义下标字   |
| sup                 | 定义上标字   |
| ins                 | 定义插入字   |
| del                 | 定义删除字   |

| HTML "计算机输出" 标签 | 描述               |
| :--------------------- | :----------------- |
| code                   | 定义计算机代码     |
| kbd                    | 定义键盘码         |
| samp                   | 定义计算机代码样本 |
| var                    | 定义变量           |
| pre                    | 定义预格式文本     |

| HTML 引文, 引用, 及标签定义 | 描述               |
| :-------------------------- | :----------------- |
| abbr                        | 定义缩写           |
| address                     | 定义地址           |
| bdo                         | 定义文字方向       |
| blockquote                  | 定义长的引用       |
| q                           | 定义短的引用语     |
| cite                        | 定义引用、引证     |
| dfn                         | 定义一个定义项目。 |



### HTML 链接

HTML 链接是通过标签 <a> 来定义的。

href 是 Hypertext Reference 的缩写，表示超文本引用。



默认情况下，链接将以以下形式出现在浏览器中：

- 一个未访问过的链接显示为蓝色字体并带有下划线。
- 访问过的链接显示为紫色并带有下划线。
- 点击链接时，链接显示为红色并带有下划线。

> 注意：如果为这些超链接设置了 CSS 样式，展示样式会根据 CSS 的设定而显示。



以下是 HTML 中创建链接的基本语法和属性：`<a>` 元素：创建链接的主要HTML元素是`<a>`（锚）元素，它使被标签包裹的内容成为一个超链接。

| `<a>`属性      | 值                                                           | 描述                                                         |
| :------------- | :----------------------------------------------------------- | :----------------------------------------------------------- |
| download       | *文件名*                                                     | 规定当用户单击超链接时将下载目标。                           |
| href           | *URL*                                                        | 规定链接指向的页面的 URL。                                   |
| hreflang       | *语言代码*                                                   | 规定被链接文档的语言。                                       |
| media          | *媒体查询*                                                   | 规定被链接文档是为何种媒介/设备优化的。                      |
| ping           | *URL 列表*                                                   | 规定以空格分隔的 URL 列表，当链接被访问时，浏览器将发送带有 ping 正文的 POST 请求（在后台发送）。通常用于跟踪。 |
| referrerpolicy | no-referrer<br />no-referrer-when-downgrade<br />origin<br />origin-when-cross-origin<br />same-origin<br />strict-origin-when-cross-origin<br />unsafe-url | <br />规定要与链接一起发送的引用信息。                       |
| rel            | alternate<br />author<br />bookmark<br />external<br />help<br />license<br />next<br />nofollow<br />noreferrer<br />noopener<br />prev<br />search<br />tag | 规定当前文档和被链接文档之间的关系。                         |
| target         | \_blank<br />\_parent<br />\_self<br />\_top<br />*framename* | 在新窗口或选项卡中打开链接文档。<br />在父框架中打开链接文档。<br />在与点击相同的框架中打开链接的文档（默认）。<br />在窗口的整个主体中打开链接的文档。<br />在指定的 iframe 中打开链接文档。<br />(**规定在何处打开被链接文档。**) |
| type           | *媒体类型*                                                   | 规定被链接文档的媒体类型。                                   |

链接的 HTML 代码很简单，它类似这样：

>**举例**
>
>编辑下面的文本框中的内容，使之变成指向任一个你喜欢的 web 地址的链接。
>
>1. 添加 `<a>` 元素。
>2. 添加 `href` 属性和 `title` 属性。
>3. 指定 `target` 属性，使得点击链接时在新标签页打开。
>
>你可以在*实时输出*区域看到你修改的内容。你应该可以看到一个链接，当鼠标移上此链接时会显示 `title` 属性值，当点击此链接时会跳转到 `href` 属性指定的 web 地址。记住：在元素名和属性名之间以及两个属性之间要有一个空格。
>
>```html
><a href="https://www.mozilla.org/" title="The Mozilla homepage" target="_blank">链接文本</a>
>```
>
><a href="https://www.mozilla.org/" title="The Mozilla homepage" target="_blank">链接文本</a>

*"链接文本"* 不必一定是文本。图片或其他 HTML 元素都可以成为链接。

**图像链接：**您还可以使用图像作为链接。在这种情况下，<a> 元素包围着 <img> 元素。例如：

```html
<a href="https://www.example.com">
  <img src="example.jpg" alt="示例图片">
</a>
```

**锚点链接：**除了链接到其他网页外，您还可以在同一页面内创建内部链接，这称为锚点链接。要创建锚点链接，需要在目标位置使用 <a> 元素定义一个标记，并使用#符号引用该标记。例如：

```html
<a href="#section2">跳转到第二部分</a>
<!-- 在页面中的某个位置 -->
<a name="section2"></a>
```

**下载链接：**如果您希望链接用于下载文件而不是导航到另一个网页，可以使用 download 属性。例如：

```html
<a href="document.pdf" download>下载文档</a>
```



### HTML 图像

| 标签 | 描述                       |
| :--- | :------------------------- |
| img  | 定义图像                   |
| map  | 定义图像地图               |
| area | 定义图像地图中的可点击区域 |

<img> 是空标签，意思是说，它只包含属性，并且没有闭合标签。

要在页面上显示图像，你需要使用源属性（src）。src 指 "source"。源属性的值是图像的 URL 地址。

>1、**图片来源于网络，写绝对路径**：
>
>```html
><img src="http://static.runoob.com/images/runoob-logo.png" width="300" height="120"/>
>```
>
>2、**图片来源于本地，写相对路径或绝对路径**：
>
>-  **./**：代表文件所在的目录（可以省略不写）如果写成image/background就相当于是在html文件下找image文件夹，当然是找不到的
>-  **../**：代表文件所在的父级目录
>-  **../../**：代表文件所在的父级目录的父级目录
>-  **/**：代表文件所在的根目录
>
>1) ***.html** 文件跟 ***.jpg** 文件(f盘)在不同目录下：
>
>```html
><img src="file:///f:/*.jpg" width="300" height="120"/>
>```
>
>2. 图片 ***.jpg** 在 **image** 文件夹中，***.html** 在 **connage** 文件夹中，**image** 跟 **connage** 在同一目录下：
>
>```html
><img src="../image/*.jpg/"width="300" height="120"/>
>```

**HTML 图像- Alt属性**

alt 属性用来为图像定义一串预备的可替换的文本。

替换文本属性的值是用户定义的。

在浏览器无法载入图像时，替换文本属性告诉读者她们失去的信息。此时，浏览器将显示这个替代性的文本而不是图像。为页面上的图像都加上替换文本属性是个好习惯，这样有助于更好的显示信息，并且对于那些使用纯文本浏览器的人来说是非常有用的。

>示例"[创建图像映射](https://www.runoob.com/try/try.php?filename=tryhtml_areamap)"中的代码：
>
>矩形：(左上角顶点坐标为(x1,y1)，右下角顶点坐标为(x2,y2))
>圆形：(圆心坐标为(X1,y1)，半径为r)
>多边形：(各顶点坐标依次为(x1,y1)、(x2,y2)、(x3,y3) ......)
>
>```
><map name="planetmap">
><area shape="rect" coords="x1,y1,x2,y2" href=url>
><area shape="circle" coords="x1,y1,r" href=url>
><area shape="poly" coords="x1,y1,x2,y2 ......" href=url>
></map>
>```



### HTML 表格

HTML 表格由 **\<table>** 标签来定义。

HTML 表格是一种用于展示结构化数据的标记语言元素。

每个表格均有若干行（由 **<tr>** 标签定义），每行被分割为若干单元格（由 **<td>** 标签定义），表格可以包含标题行（**<th>**）用于定义列的标题。

| 标签       | 描述                                                         |
| :--------- | :----------------------------------------------------------- |
| <table>    | 定义表格                                                     |
| <th>       | 定义表格的表头。th 是 table header的缩写。大多数浏览器会把表头显示为粗体居中的文本。 |
| <tr>       | 定义表格的行。tr 是 table row 的缩写                         |
| <td>       | 定义表格单元。td 是 table data 的缩写                        |
| <caption>  | 定义表格标题。可用于为整个表格定义标题。                     |
| <colgroup> | 定义表格列的组                                               |
| <col>      | 定义用于表格列的属性                                         |
| <thead>    | 定义表格的页眉。在 <thead > 中，使用 <th > 元素定义列的标题  |
| <tbody>    | 定义表格的主体。在 <tbody > 中，使用 <tr > 元素定义行，并在每行中使用 <td > 元素定义单元格数据 |
| <tfoot>    | 定义表格的页脚。可用于在表格的底部定义摘要、统计信息等内容。 |

> \<table> 标签常用属性：
>
> ```html
> border="1"   表格边框的宽度
> bordercolor="#fff"   表格边框的颜色
> cellspacing="5"   单元格之间的间距
> width="500"   表格的总宽度
> height="100"   表格的总高度
> align="right"   表格整体对齐方式    (参数有  left、center、right)
> bgcolor="#fff"   表格整体的背景色
> ```
>
> <tr> 标签的常用属性:
>
> ```html
> bgcolor="#fff"    行的颜色
> align="right"    行内文字的水平对齐方式    (参数有left、center、right)
> valign="top"     行内文字的垂直对齐方式    (参数有top、middle、bottom)
> ```
>
> <td>、<th> 标签的常用属性:
>
> ```html
> width="500"    单元格的宽度，设置后对当前一列的单元格都有影响
> height="100"   单元格的高度，设置后对当前一行的单元格都有影响
> bgcolor="fff"  单元格的背景色
> align="right"  单元格文字的水平对齐方式    (参数left、center、right)
> rowspan="3"    合并垂直水平方向的单元格
> colspan="3"    合并水平方向单元格
> valign="top"   单元格文字的垂直对齐方式    (参数middle、bottom、top) 
> ```

数据单元格可以包含文本、图片、列表、段落、表单、水平线、表格等等。

> HTML 表格生成器：https://www.jyshare.com/front-end/7688/。

>以下是一个简单的 HTML 表格实例:
>
>```html
><table  border="1" cellpadding="10" width="100%" >
><caption>My Table</caption>
><thead>
><colgroup>
><col span="2" style="background-color:red">
><col style="background-color:yellow">
></colgroup>
><tr>
> <th>列标题1</th>
> <th>列标题2</th>
> <th>列标题3</th>
> <th>列标题4</th>
></tr>
></thead>
><tbody>
><tr>
> <td>行1，列1</td>
> <td>行1，列2</td>
> <td>行1，列3</td>
> <td>行1，列4</td>
></tr>
><tr>
> <td>行2，列1</td>
> <td>行2，列2</td>
> <td>行2，列3</td>
> <td>行2，列4</td>
></tr>
></tbody>
></table>
>```
>
><table  border="1">
><caption>My Table</caption>
><thead>
><colgroup>
><col span="2" style="background-color:red">
><col style="background-color:yellow">
></colgroup>
><tr>
> <th>列标题1</th>
> <th>列标题2</th>
> <th>列标题3</th>
> <th>列标题4</th>
></tr>
></thead>
><tbody>
><tr>
> <td>行1，列1</td>
> <td>行1，列2</td>
> <td>行1，列3</td>
> <td>行1，列4</td>
></tr>
><tr>
> <td>行2，列1</td>
> <td>行2，列2</td>
> <td>行2，列3</td>
> <td>行2，列4</td>
></tr>
></tbody>
></table>

> 课程表实例
>
> ```html
> <h4 style="text-align:center">课程表</h4>
> <table border="1" cellpadding="10" width="100%">
>  <tr>
> 	    <th colspan="2">时间\日期</th>
> 	    <th>一</th>
> 	    <th>二</th>
> 	    <th>三</th>
> 	    <th>四</th>
> 	    <th>五</th>
>  </tr>
>  <tr>
> 	    <th rowspan="2">上午</th>
> 	    <th>9:30-10:15</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
>  </tr>
>  <tr>
> 	    <th>10:25-11:10</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
>  </tr>
>  <tr>
>  <th colspan="7"></th>
>  </tr>
>  <tr>
> 	    <th rowspan="2">下午</th>
> 	    <th>14:30-15:15</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
>  </tr>
>  <tr>
> 	    <th>15:25-16:10</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
>  </tr>
> </table>
> ```
>
> <h4 style="text-align:center">课程表</h4>
> <table border="1" cellpadding="10" width="100%">
>  <tr>
> 	    <th colspan="2">时间\日期</th>
> 	    <th>一</th>
> 	    <th>二</th>
> 	    <th>三</th>
> 	    <th>四</th>
> 	    <th>五</th>
>  </tr>
>  <tr>
> 	    <th rowspan="2">上午</th>
> 	    <th>9:30-10:15</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
>  </tr>
>  <tr>
> 	    <th>10:25-11:10</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
>  </tr>
>  <tr>
>  <th colspan="7"></th>
>  </tr>
>  <tr>
> 	    <th rowspan="2">下午</th>
> 	    <th>14:30-15:15</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
>  </tr>
>  <tr>
> 	    <th>15:25-16:10</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
> 	    <th>语文</th>
>  </tr>
> </table>



### HTML 列表

| 标签 | 描述                 |
| :--- | :------------------- |
| <ol> | 定义有序列表         |
| <ul> | 定义无序列           |
| <li> | 定义列表项           |
| <dl> | 定义列表             |
| <dt> | 自定义列表项目       |
| <dd> | 定义自定列表项的描述 |

有序列表是一列项目，列表项目使用数字进行标记。 有序列表始于 <ol> 标签。每个列表项始于 <li> 标签。

列表项使用数字来标记。

```html
<h4>编号列表：</h4>
<ol>
 <li>Apples</li>
 <li>Bananas</li>
 <li>Lemons</li>
 <li>Oranges</li>
</ol>  

<h4>大写字母列表：</h4>
<ol type="A">
 <li>Apples</li>
 <li>Bananas</li>
 <li>Lemons</li>
 <li>Oranges</li>
</ol>  

<h4>小写字母列表：</h4>
<ol type="a">
 <li>Apples</li>
 <li>Bananas</li>
 <li>Lemons</li>
 <li>Oranges</li>
</ol>  

<h4>罗马数字列表：</h4>
<ol type="I">
 <li>Apples</li>
 <li>Bananas</li>
 <li>Lemons</li>
 <li>Oranges</li>
</ol>  

<h4>小写罗马数字列表：</h4>
<ol type="i">
 <li>Apples</li>
 <li>Bananas</li>
 <li>Lemons</li>
 <li>Oranges</li>
</ol>  
```

无序列表是一个项目的列表，此列项目使用粗体圆点（典型的小黑圆圈）进行标记。

无序列表使用 <ul> 标签

```html
<p><b>注意：</b> 在 HTML 4中 ul 属性已废弃，HTML5 已不支持该属性，因此我们使用 CSS 代替来定义不同类型的无序列表如下：</p>

<h4>圆点列表：</h4>
<ul style="list-style-type:disc">
 <li>Apples</li>
 <li>Bananas</li>
 <li>Lemons</li>
 <li>Oranges</li>
</ul>  

<h4>圆圈列表：</h4>
<ul style="list-style-type:circle">
 <li>Apples</li>
 <li>Bananas</li>
 <li>Lemons</li>
 <li>Oranges</li>
</ul>  

<h4>正方形列表：</h4>
<ul style="list-style-type:square">
 <li>Apples</li>
 <li>Bananas</li>
 <li>Lemons</li>
 <li>Oranges</li>
</ul>
```

嵌套列表

```html
<h4>嵌套列表1：</h4>
<ul>
  <li>Coffee</li>
  <li>Tea
    <ul>
      <li>Black tea</li>
      <li>Green tea</li>
    </ul>
  </li>
  <li>Milk</li>
</ul>
```

```html
<h4>嵌套列表2：</h4>
<ul>
  <li>Coffee</li>
  <li>Tea
    <ul>
      <li>Black tea</li>
      <li>Green tea
        <ul>
          <li>China</li>
          <li>Africa</li>
        </ul>
      </li>
    </ul>
  </li>
  <li>Milk</li>
</ul>
```

自定义列表不仅仅是一列项目，而是项目及其注释的组合。

自定义列表以 <dl> 标签开始。每个自定义列表项以 <dt> 开始。每个自定义列表项的定义以 <dd> 开始。

```html
<dl>
<dt>Coffee</dt>
<dd>- black hot drink</dd>
<dt>Milk</dt>
<dd>- white cold drink</dd>
</dl>
```





### HTML 区块

HTML 可以通过 <div> 和 <span>将元素组合起来。

| HTML 分组标签 | 描述                                        |
| :------------ | :------------------------------------------ |
| <div>         | 定义了文档的区域，块级 (block-level)        |
| <span>        | 用来组合文档中的行内元素， 内联元素(inline) |



**区块元素**

大多数 HTML 元素被定义为**块级元素**或**内联元素**。

块级元素在浏览器显示时，通常会以新行来开始（和结束）。

实例: <h1>, <p>, <ul>, <table>

**内联元素**

内联元素在显示时通常不会以新行开始。

实例: <b>, <td>, <a>, <img>

**\<div> 元素**

HTML <div> 元素是块级元素，它可用于组合其他 HTML 元素的容器。

\<div> 元素没有特定的含义。除此之外，由于它属于块级元素，浏览器会在其前后显示折行。

如果与 CSS 一同使用，<div> 元素可用于对大的内容块设置样式属性。

\<div> 元素的另一个常见的用途是文档布局。它取代了使用表格定义布局的老式方法。使用 <table> 元素进行文档布局不是表格的正确用法。<table> 元素的作用是显示表格化的数据。

**<span> 元素**

HTML <span> 元素是内联元素，可用作文本的容器

<span> 元素也没有特定的含义。

当与 CSS 一同使用时，<span> 元素可用于为部分文本设置样式属性。



### HTML 样式 - CSS

CSS (Cascading Style Sheets) 用于渲染HTML元素标签的样式。

CSS 是在 HTML 4 开始使用的,是为了更好的渲染HTML元素而引入的.

CSS 可以通过以下方式添加到HTML中:

- 内联样式- 在HTML元素中使用"style" **属性**
- 内部样式表 -在HTML文档头部 <head> 区域使用<style> **元素** 来包含CSS
- 外部引用 - 使用外部 CSS **文件**

最好的方式是通过外部引用CSS文件.

​	使用 CSS 最大的好处是，如果把 CSS 代码存放到外部样式表中，那么站点会更易于维护。通过编辑单一的文件，就可以改变所有页面的布局。

| HTML 样式标签                                  | 描述                   |
| :--------------------------------------------- | :--------------------- |
| <style>                                        | 定义文本样式           |
| <link>                                         | 定义资源引用地址       |
| <font>, <center>, <strike> \| color 和 bgcolor | 不建议使用的标签和属性 |

**内联样式**

当特殊的样式需要应用到个别元素时，就可以使用内联样式。 

使用内联样式的方法是在相关的标签中使用样式属性。

样式属性可以包含任何 CSS 属性。

以下实例显示出如何改变段落的颜色和左外边距。

```html
<p style="color:blue;margin-left:20px;">这是一个段落。</p>
```

<p style="color:blue;margin-left:20px;">这是一个段落。</p>

> **背景颜色**
>
> 背景色属性（background-color）定义一个元素的背景颜色
>
> ```html
> <body style="background-color:yellow;">
> <h2 style="background-color:red;">这是一个标题</h2>
> <p style="background-color:green;">这是一个段落。</p>
> </body>
> ```
>
> **字体, 字体颜色 ，字体大小**
>
> 可以使用font-family（字体），color（颜色），和font-size（字体大小）属性来定义字体的样式
>
> ```html
> <h1 style="font-family:verdana;">一个标题</h1>
> <p style="font-family:arial;color:red;font-size:20px;">一个段落。</p>
> <a href="http://www.runoob.com/" style="text-decoration:none;">无下划线链接</a>
> ```
>
> **文本对齐方式**
>
> 使用 text-align（文字对齐）属性指定文本的水平与垂直对齐方式
>
> 文本对齐属性 text-align取代了旧标签 <center> 
>
> ```html
> <h1 style="text-align:center;">居中对齐的标题</h1>
> <p>这是一个段落。</p>
> ```



**内部样式表**

当单个文件需要特别样式时，就可以使用内部样式表。你可以在<head> 部分通过 <style>标签定义内部样式表

```html
<head>
<style type="text/css">
body {background-color:yellow;}
p {color:blue;}
</style>
</head>
```

**外部样式表**

当样式需要被应用到很多页面的时候，外部样式表将是理想的选择。使用外部样式表，你就可以通过更改一个文件来改变整个站点的外观。

```html
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
</head>
```





### HTML 布局

大多数网站会把内容安排到多个列中（就像杂志或报纸那样）。

大多数网站可以使用 <div> 或者 <table> 元素来创建多列。CSS 用于对元素进行定位，或者为页面创建背景以及色彩丰富的外观。



**使用<div> 元素**

div 元素是用于分组 HTML 元素的块级元素。

下面的例子使用多个 div 元素来创建多列布局

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Demo</title>
</head>
<body>
	<div id="maxname" style="width:500px;">
      <div id="hname" style="background-color:#666FFF;">
        <h1 style="margin-bottom:0;text-align:center;">Div布局实例</h1>
      </div>
      <div id="menu" style="background-color:#FFFFFF;height:200px;width:100px;float:left;">    
          菜单<br/>
          num1<br/>
          num2<br/>
          num3
      </div>
      <div id="content" style="background-color:#FFFF00;height:200px;width:300px;float:left;">
        中间内容
      </div>
      <div id="right" style="background-color:#000000;color:#FFFFFF;height:200px;width:100px;float:right;">
          信息内容
      </div>
      <div id="bool" style="background-color:#F0F8FF;clear:both;text-align:center;">
        作者：Cty
      </div>
  </div>
</body>
</html>
```



**使用表格**

使用 HTML <table> 标签是创建布局的一种简单的方式。

大多数站点可以使用 <div> 或者 <table> 元素来创建多列。CSS 用于对元素进行定位，或者为页面创建背景以及色彩丰富的外观。

> 即使可以使用 HTML 表格来创建漂亮的布局，但设计表格的目的是呈现表格化数据 - 表格不是布局工具！

下面的例子使用三行两列的表格 - 第一和最后一行使用 colspan 属性来横跨两列：

```html
<!DOCTYPE html>
<html>
<head> 
<meta charset="utf-8"> 
<title>aaa</title> 
</head>
<body>
 
<table width="500" border="0">
<tr>
<td colspan="2" style="background-color:#FFA500;">
<h1>主要的网页标题</h1>
</td>
</tr>
 
<tr>
<td style="background-color:#FFD700;width:100px;">
<b>菜单</b><br>
HTML<br>
CSS<br>
JavaScript
</td>
<td style="background-color:#eeeeee;height:200px;width:400px;">
内容在这里</td>
</tr>
 
<tr>
<td colspan="2" style="background-color:#FFA500;text-align:center;">
版权 © runoob.com</td>
</tr>
</table>
 
</body>
</html>
```





### HTML 表单

HTML 表单用于收集用户的输入信息。

HTML 表单表示文档中的一个区域，此区域包含交互控件，将用户收集到的信息发送到 Web 服务器。

HTML 表单通常包含各种输入字段、复选框、单选按钮、下拉列表等元素。

| 标签       | 描述                                         |
| :--------- | :------------------------------------------- |
| <form>     | 定义供用户输入的表单                         |
| <input>    | 定义输入域                                   |
| <textarea> | 定义文本域 (一个多行的输入控件)              |
| <label>    | 定义了 <input> 元素的标签，一般为输入标题    |
| <fieldset> | 定义了一组相关的表单元素，并使用外框包含起来 |
| <legend>   | 定义了 <fieldset> 元素的标题                 |
| <select>   | 定义了下拉选项列表                           |
| <optgroup> | 定义选项组                                   |
| <option>   | 定义下拉列表中的选项                         |
| <button>   | 定义一个点击按钮                             |
| <datalist> | 指定一个预先定义的输入控件选项列表           |
| <keygen>   | 定义了表单的密钥对生成器字段                 |
| <output>   | 定义一个计算结果                             |

表单是一个包含表单元素的区域。

表单元素是允许用户在表单中输入内容，比如：文本域（textarea）、下拉列表（select）、单选框（radio-buttons）、复选框（checkbox） 等等。

我们可以使用 **<form>** 标签来创建表单:

```html
<form>
input 元素
</form>
```

**文本域（Text Fields）**

文本域通过 **<input type="text">** 标签来设定，当用户要在表单中键入字母、数字等内容时，就会用到文本域。

**注意:**表单本身并不可见。同时，在大多数浏览器中，文本域的默认宽度是 20 个字符。

```html
<form>
First name: <input type="text" name="firstname"><br>
Last name: <input type="text" name="lastname">
</form>
```

**密码字段**

密码字段通过标签 **<input type="password">** 来定义:

```html
<form>
Password: <input type="password" name="pwd">
</form>
```

**单选按钮（Radio Buttons）**

**<input type="radio">** 标签定义了表单的单选框选项:

```html
<form action="">
<input type="radio" name="sex" value="male">男<br>
<input type="radio" name="sex" value="female">女
</form>
```

**复选框（Checkboxes）**

**<input type="checkbox">** 定义了复选框。

复选框可以选取一个或多个选项：

```html
<form>
<input type="checkbox" name="vehicle" value="Bike">我喜欢自行车<br>
<input type="checkbox" name="vehicle" value="Car">我喜欢小汽车
</form>
```

**提交按钮(Submit)**

**<input type="submit">** 定义了提交按钮。

当用户单击确认按钮时，表单的内容会被传送到服务器。表单的动作属性 **action** 定义了服务端的文件名。

**action** 属性会对接收到的用户输入数据进行相关的处理:

```html
<form name="input" action="html_form_action.php" method="get">
Username: <input type="text" name="user">
<input type="submit" value="Submit">
</form>
```

method 属性，它用于定义表单数据的提交方式，可以是以下值：

- **post**：指的是 HTTP POST 方法，表单数据会包含在表单体内然后发送给服务器，用于提交敏感数据，如用户名与密码等。
- **get**：默认值，指的是 HTTP GET 方法，表单数据会附加在 **action** 属性的 URL 中，并以 **?**作为分隔符，一般用于不敏感信息，如分页等。例如：https://www.runoob.com/?page=1，这里的 page=1 就是 get 方法提交的数据。

```html
<!-- 以下表单使用 GET 请求发送数据到当前的 URL，method 默认位 GET。 -->
<form>
  <label>Name:
    <input name="submitted-name" autocomplete="name">
  </label>
  <button>Save</button>
</form>

<!-- 以下表单使用 POST 请求发送数据到当前的 URL。 -->
<form method="post">
  <label>Name:
    <input name="submitted-name" autocomplete="name">
  </label>
  <button>Save</button>
</form>

<!-- 表单使用 fieldset, legend, 和 label 标签 -->
<form method="post">
  <fieldset>
    <legend>Title</legend>
    <label><input type="radio" name="radio"> Select me</label>
  </fieldset>
</form>
```

以下是简单的HTML表单的例子：

```html
<form action="/" method="post">
    <!-- 文本输入框 -->
    <label for="name">用户名:</label>
    <input type="text" id="name" name="name" required>

    <br>

    <!-- 密码输入框 -->
    <label for="password">密码:</label>
    <input type="password" id="password" name="password" required>

    <br>

    <!-- 单选按钮 -->
    <label>性别:</label>
    <input type="radio" id="male" name="gender" value="male" checked>
    <label for="male">男</label>
    <input type="radio" id="female" name="gender" value="female">
    <label for="female">女</label>

    <br>

    <!-- 复选框 -->
    <input type="checkbox" id="subscribe" name="subscribe" checked>
    <label for="subscribe">订阅推送信息</label>

    <br>

    <!-- 下拉列表 -->
    <label for="country">国家:</label>
    <select id="country" name="country">
        <option value="cn">CN</option>
        <option value="usa">USA</option>
        <option value="uk">UK</option>
    </select>

    <br>

    <!-- 提交按钮 -->
    <input type="submit" value="提交">
</form>
```

```html
<!--带边框的表单-->
<form action="">
<fieldset>
<legend>Personal information:</legend>
Name: <input type="text" size="30"><br>
E-mail: <input type="text" size="30"><br>
Date of birth: <input type="text" size="10">
</fieldset>
</form>
```

```html
<!--下拉列表-->
<!--标有selected为预选-->
<form action="">
<select name="cars">
<option value="volvo">Volvo</option>
<option value="saab">Saab</option>
<option value="fiat" selected>Fiat</option>
<option value="audi">Audi</option>
</select>
</form>
```

```html
<!--触发按钮-->
<form action="">
<input type="button" value="Hello world!">
</form>
```





### HTML 框架

**设置高度与宽度**

height 和 width 属性用来定义iframe标签的高度与宽度。

属性默认以像素为单位, 但是你可以指定其按比例显示 (如："80%")。

**移除边框**

frameborder 属性用于定义iframe表示是否显示边框。

```html
<iframe src="https://www.baidu.com" name="iframe_a" width="200" height="500" frameborder="0"></iframe>
```



### HTML 颜色

HTML 颜色由一个十六进制符号来定义，这个符号由红色、绿色和蓝色的值组成（RGB）。

每种颜色的最小值是0（十六进制：#00）。最大值是255（十六进制：#FF）。

三种颜色 红，绿，蓝的组合从0到255，一共有1600万种不同颜色(256 x 256 x 256)。

这个表格给出了由三种颜色混合而成的具体效果：

<div class="example">
<table class="reference notranslate">
  <tbody><tr>
    <th xstyle="background-color:white" width="50%">颜色(Color)</th>
    <th xstyle="background-color:white" width="25%">颜色十六进制(Color HEX)</th>
    <th xstyle="background-color:white" width="25%">颜色RGB(Color RGB)</th>
  </tr>
  <tr>
    <td bgcolor="#000000">&nbsp;</td>
    <td>#000000</td>
    <td>rgb(0,0,0)</td>
  </tr>
  <tr>
    <td bgcolor="#FF0000">&nbsp;</td>
    <td>#FF0000</td>
    <td>rgb(255,0,0)</td>
  </tr>
  <tr>
    <td bgcolor="#00FF00">&nbsp;</td>
    <td>#00FF00</td>
    <td>rgb(0,255,0)</td>
  </tr>
  <tr>
    <td bgcolor="#0000FF">&nbsp;</td>
    <td>#0000FF</td>
    <td>rgb(0,0,255)</td>
  </tr>
  <tr>
    <td bgcolor="#FFFF00">&nbsp;</td>
    <td>#FFFF00</td>
    <td>rgb(255,255,0)</td>
  </tr>
  <tr>
    <td bgcolor="#00FFFF">&nbsp;</td>
    <td>#00FFFF</td>
    <td>rgb(0,255,255)</td>
  </tr>
  <tr>
    <td bgcolor="#FF00FF">&nbsp;</td>
    <td>#FF00FF</td>
    <td>rgb(255,0,255)</td>
  </tr>
  <tr>
    <td bgcolor="#C0C0C0">&nbsp;</td>
    <td>#C0C0C0</td>
    <td>rgb(192,192,192)</td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF">&nbsp;</td>
    <td>#FFFFFF</td>
    <td>rgb(255,255,255)</td>
  </tr>
</tbody></table>
</div>


RGBA 的意思是（Red-Green-Blue-Alpha）
它是在 RGB 上扩展包括了 **“alpha”** 通道，运行对颜色值设置透明度。
alpha 范围 0~1，0 表示全透明。

141个颜色名称是在HTML和CSS颜色规范定义的（17标准颜色，再加124）。下表列出了所有颜色的值，包括十六进制值。

> [HTML 颜色名 | 菜鸟教程 (runoob.com)](https://www.runoob.com/html/html-colornames.html)



### HTML 脚本标签

| 标签       | 描述                             |
| :--------- | :------------------------------- |
| <script>   | 定义了客户端脚本                 |
| <noscript> | 定义了不支持脚本浏览器输出的文本 |



### HTML 字符实体

HTML 中的预留字符必须被替换为字符实体。

一些在键盘上找不到的字符也可以使用字符实体来替换。

如果希望正确地显示预留字符，我们必须在 HTML 源代码中使用字符实体（character entities）。

| 显示结果 | 描述        | 实体名称           | 实体编号 |
| :------- | :---------- | :----------------- | :------- |
|          | 空格        | \&nbsp;            | &#160;   |
| <        | 小于号      | \&lt;              | &#60;    |
| >        | 大于号      | \&gt;              | &#62;    |
| &        | 和号        | \&amp;             | &#38;    |
| "        | 引号        | \&quot;            | &#34;    |
| '        | 撇号        | \&apos; (IE不支持) | &#39;    |
| ￠       | 分          | \&cent;            | &#162;   |
| £        | 镑          | \&pound;           | &#163;   |
| ¥        | 人民币/日元 | \&yen;             | &#165;   |
| €        | 欧元        | \&euro;            | &#8364;  |
| §        | 小节        | \&sect;            | &#167;   |
| ©        | 版权        | \&copy;            | &#169;   |
| ®        | 注册商标    | \&reg;             | &#174;   |
| ™        | 商标        | \&trade;           | &#8482;  |
| ×        | 乘号        | \&times;           | &#215;   |
| ÷        | 除号        | \&divide;          | &#247;   |

虽然 html 不区分大小写，但实体字符对大小写敏感。

**结合音标符**

发音符号是加到字母上的一个"glyph(字形)"。

一些变音符号, 如 尖音符 ( ̀) 和 抑音符 ( ́) 。

变音符号可以出现字母的上面和下面，或者字母里面，或者两个字母间。

变音符号可以与字母、数字字符的组合来使用。

| 音标符 | 字符 | Construct | 输出结果 |
| :----- | :--- | :-------- | :------- |
| ̀       | a    | a\&#768;  | à        |
| ́       | a    | a\&#769;  | á        |
| ̂       | a    | a\&#770;  | â        |
| ̃       | a    | a\&#771;  | ã        |
| ̀       | O    | O\&#768;  | Ò        |
| ́       | O    | O\&#769;  | Ó        |
| ̂       | O    | O\&#770;  | Ô        |
| ̃       | O    | O\&#771;  | Õ        |

**不间断空格(Non-breaking Space)**

HTML 中的常用字符实体是不间断空格(&nbsp;)。

浏览器总是会截短 HTML 页面中的空格。如果您在文本中写 10 个空格，在显示该页面之前，浏览器会删除它们中的 9 个。如需在页面中增加空格的数量，您需要使用 &nbsp; 字符实体。

> 使用实体名而不是数字的好处是，名称易于记忆。不过坏处是，浏览器也许并不支持所有实体名称（对实体数字的支持却很好）。



### HTML 统一资源定位器(Uniform Resource Locators — URL )

URL 是一个网页地址。

URL可以由字母组成，如"runoob.com"，或互联网协议（IP）地址： 192.68.20.50。大多数人进入网站使用网站域名来访问，因为 名字比数字更容易记住。

Web浏览器通过URL从Web服务器请求页面。

当您点击 HTML 页面中的某个链接时，对应的 <a> 标签指向万维网上的一个地址。

一个统一资源定位器(URL) 用于定位万维网上的文档。

> **scheme`://`host.domain`:`port`/`path`/`filename**
>
> 说明:
>
> - - scheme - 定义因特网服务的类型。最常见的类型是 http
>   - host - 定义域主机（http 的默认主机是 www）
>   - domain - 定义因特网域名，比如 runoob.com
>   - :port - 定义主机上的端口号（http 的默认端口号是 80）
>   - path - 定义服务器上的路径（如果省略，则文档必须位于网站的根目录中）。
>   - filename - 定义文档/资源的名称

| 常见的 URL Scheme | 访问               | 用于...                             |
| :---------------- | :----------------- | :---------------------------------- |
| http              | 超文本传输协议     | 以 http:// 开头的普通网页。不加密。 |
| https             | 安全超文本传输协议 | 安全网页，加密所有信息交换。        |
| ftp               | 文件传输协议       | 用于将文件下载或上传至网站。        |
| file              |                    | 您计算机上的文件。                  |



### URL 字符编码

URL 只能使用 [ASCII 字符集](https://www.runoob.com/tags/html-ascii.html).

来通过因特网进行发送。由于 URL 常常会包含 ASCII 集合之外的字符，URL 必须转换为有效的 ASCII 格式。

URL 编码使用 "%" 其后跟随两位的十六进制数来替换非 ASCII 字符。

URL 不能包含空格。URL 编码通常使用 + 来替换空格。
