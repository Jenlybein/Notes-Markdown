------

<center><font size=7><b> Tomcat部署——个人笔记 </center></font></center>

---

[TOC]

---

本学习笔记参考尚硅谷等教程。



### 简介

[Apache Tomcat 官网](https://tomcat.apache.org/)

Tomcat是Apache 软件基金会（Apache Software Foundation）的Jakarta 项目中的一个核心项目，由Apache、Sun 和其他一些公司及个人共同开发而成。由于有了Sun 的参与和支持，最新的Servlet 和JSP 规范总是能在Tomcat 中得到体现。Tomcat 服务器是一个免费的开放源代码的**Web 应用服务器**，属于轻量级应用服务器，在中小型系统和并发访问用户不是很多的场合下被普遍使用，是开发和调试JSP 程序的首选。



### 安装

**下载**

进入官网，查看左边侧边栏（若没出现可能是浏览器窗口宽度不够，拉长之后自动出现），在Download一栏中选择要下载的Tomcat版本。

注意：Tomcat 9 之前开发的项目在 Tomcat 10之后的版本不兼容。

根据表中提示选择要下载的Tomcat 版本 ：[Apache Tomcat® - Tomcat的版本选择](https://tomcat.apache.org/whichversion.html)

此处我选择用Java 17来进行开发，所以我选择的是Tomcat 10，详细版本为10.1.26

下载Tomcat 10对应的压缩文件：<u>64-bit Windows zip</u>，之后在适合的位置解压缩。



**配置环境变量**

右键”我的电脑“ → 系统设置 → 高级系统设置 → 高级 → 环境变量 → 系统变量 → 新建

变量名中填写： `JAVA_HOME`

变量值中填写：`你的JAVA的根目录`（使用 IDEA 会自动下载JAVA，所以可以去IDEA查找对应 JAVA 的下载目录）



**测试**

在 Tomcat 的解压根目录中，打开 `bin/startup.bat` ，若启动成功，则安装完成。若闪退，则可能是配置JAVA_HOME的时候出错。

启动成功之后，观察倒数第二行出现的“`http-nio-数字`”，此数字为端口(一般默认为8080)。在浏览器中输入 `localhost:数字` ，则可进入Tomcat服务器的首页。

Tomcat 的关闭方式：
1.关闭Tomcat的运行窗口
2.运行bin/shutdown.bat



### 配置文件

启动成功 Tomcat 时，窗口内字体为乱码（cmd默认编码方式为GBK，而Tomcat编码默认为UTF-8)
在conf/logging.properties中修改 java.util.logging.ConsoleHandler.encoding项，使其=GBK。

其他情况出现乱码也如上，在对应的配置项操作。

conf/server.xml 中可以修改服务器相关配置（如端口号修改）

conf/tomcat-users.xml 配置管理类型项目的账号密码



### WEB项目的标准结构

> 一个标准的可以用于发布的WEB项目标准结构如下
>
> Tomcat 根目录 / webapps
> │
> └─app/
>      │ 
>      ├─ static/
>      │    ├── css
>      │    ├── img
>      │    └── js
>      │
>      ├─ WEB-INF/
>      │    ├── classes
>      │    ├── lib
>      │    └── web.xml
>      │
>      └─ index.html

+ app  本应用根目录
  + static 非必要目录,约定俗成的名字,一般在此处放静态资源 ( css  js  img)
  + WEB-INF  必要目录,必须叫WEB-INF,受保护的资源目录,浏览器通过url不可以直接访问的目录
    + classes     必要目录,src下源代码,配置文件,编译后会在该目录下,web项目中如果没有源码,则该目录不会出现
    + lib             必要目录,项目依赖的jar编译后会出现在该目录下,web项目要是没有依赖任何jar,则该目录不会出现
    + web.xml   必要文件,web项目的基本配置文件. 较新的版本中可以没有该文件,但是学习过程中还是需要该文件 
  + index.html  非必要文件,index.html/index.htm/index.jsp为默认的欢迎页



### WEB项目部署

> 方式1   直接将编译好的项目放在webapps目录下  (已经演示)

> 方式2   将编译好的项目打成war包放在webapps目录下,tomcat启动后会自动解压war包(其实和第一种一样)

> 方式3   可以将项目放在非webapps的其他目录下,在tomcat的conf下创建Catalina/localhost目录,并在该目录下准备一个app.xml文件，使Tomcat获取指向该项目的路径。
>
> ```xml
> <!-- 
> 	path: 项目的访问路径,也是项目的上下文路径,就是在浏览器中,输入的项目名称
>     docBase: 项目在磁盘中的实际路径
>  -->
> <Context path="/app" docBase="D:\mywebapps\app" />
> ```



### IDEA中开发并部署运行WEB项目

打开 IDEA 设置

左侧侧边栏，选中“**构建，执行，部署**”项（Build，Execution，Deployment)，再选中**应用程序服务器**(Application Servers)

点击右侧窗口的左上方出现的 **＋**，在弹出菜单选择Tomcat服务器（Tomcat Server)

主目录（Tomcat Home）选择Tomcat的安装目录

点击ok即可关联。



> 使用Tomcat开发项目时，推荐先创建一个空项目,这样可以在一个空项目下同时存在多个modules,不用后续来回切换之前的项目,当然也可以忽略此步直接创建web项目

使用IDEA创建项目。

创建完成后，选中项目结构（Project Structure)

![image-20240801170014725](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011738498.png)

检查项目的SDK,语法版本,以及项目编译后的输出目录

![image-20240801170150158](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011738324.png)

创建一个普通的JAVA项目

![image-20240801170400392](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011738249.png)

![image-20240801170449637](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011738166.png)

创建完毕后，打开项目结构，为项目添加Tomcat依赖

![image-20240801170916440](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011738732.png)

![image-20240801171008345](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011738226.png)

![image-20240801171031330](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011738595.png)

添加框架支持（选中相应module后，双击shift，搜索"添加框架支持")

![image-20240801171452171](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011738893.png)

![image-20240801171800981](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011738872.png)

删除module目录下出现的index.jsp ,替换为 index.html



处理配置文件

+ 在工程下创建resources目录,专门用于存放配置文件(都放在src下也行,单独存放可以尽量避免文件集中存放造成的混乱)
+ 标记目录为资源目录,不标记的话则该目录不参与编译（右键resources目录，选择"将目录标记为..."，在弹出菜单中选择"资源根目录")



处理依赖jar包问题

+ 在WEB-INF下创建lib目录
+ 必须在WEB-INF下，且目录名必须叫lib
+ 复制jar文件进入lib目录
+ 将lib目录添加为当前项目的依赖（右键lib目录，选择"添加为库..." → 环境级别推荐选择module 级别,降低对其他项目的影响,name可以空着不写) 



在web文件夹下，生成一个 WEB 项目的标准结构，将要开发的项目放入。

![image-20240801172634825](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011738657.png)



配置项目，使其在Tomcat服务器上运行

![image-20240801173300244](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011738839.png)

![image-20240801173412178](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011739269.png)

![image-20240801173448419](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011739344.png)

![image-20240801173530764](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011739686.png)

点击apply 应用后,回到Server部分. After Launch是配置启动成功后,是否默认自动打开浏览器并输入URL中的地址,HTTP port是Http连接器目前占用的端口号

![image-20240801173722109](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011739830.png)

运行项目

![image-20240801174042138](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011740178.png)

访问对应文件，成功

![image-20240801174109312](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011741347.png)

> 工程结构和可以发布的项目结构之间的目录对应关系
>
> ![image-20240801174129857](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011741894.png)

> IDEA部署并运行项目的原理
>
> + idea并没有直接进将编译好的项目放入tomcat的webapps中
> + idea根据关联的tomcat,创建了一个tomcat副本,将项目部署到了这个副本中
> + idea的tomcat副本在C:\用户\当前用户\AppData\Local\JetBrains\IntelliJIdea2022.2\tomcat\中
> + idea的tomcat副本并不是一个完整的tomcat,副本里只是准备了和当前项目相关的配置文件而已
> + idea启动tomcat时,是让本地tomcat程序按照tomcat副本里的配置文件运行
> + idea的tomcat副本部署项目的模式是通过conf/Catalina/localhost/*.xml配置文件的形式实现项目部署的
>
> ![image-20240801174204152](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408011742203.png)

