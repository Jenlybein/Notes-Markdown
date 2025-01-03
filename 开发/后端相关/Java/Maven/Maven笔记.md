# Maven笔记

**Apache Maven Project**

---
---

## Maven快速入门

### Maven简介

[Introduction – Maven (apache.org)](https://maven.apache.org/what-is-maven.html)

**Apache Maven** 是一个项目管理和构建工具，它基于**项目对象模型**(Project Object Model. POM)的概念，通过一小段描述信息来管理项目的构建。

- 依赖管理
  - Maven 可以管理项目的依赖，包括自动下载所需依赖库、自动下载依赖需要的依赖并且保证版本没有冲突、依赖版本管理等。通过 Maven，我们可以方便地维护项目所依赖的外部库，而我们仅仅需要编写配置即可。
- 统一项目结构
  - 提供标准、统一的项目结构。方便再各种开发工具中打开项目。
- 项目构建
  - 标准跨平台(Linux、Windows、MacOs)的自动化项目构建方式。
  - 项目开发完成后，想要将项目打成.war文件，并部署到服务器中运行，使用Maven软件，我们可以通过一行构建命令（mvn package）快速项目构建和打包。

### Maven安装

安装地址：[Maven Releases History – Maven (apache.org)](https://maven.apache.org/docs/history.html)

点开网址后查看所有版本对应的Java版本，选好版本后点击右上角Download按钮跳转到下载页，选择Binary格式的zip包下载。

（安装前注意要安装对应的Java版本，并配置有`java_home`环境变量）

下载完成后解压到想要的安装位置，新建系统变量`MAVA_HOME`指到对应安装文件夹。

再配置系统变量Path，新建`%MAVEN_HOME%\bin`

点击应用后完成环境变量配置。

```bash
命令行输入：mvn -v 
# 输出版本信息即可，如果错误，请仔细检查环境变量即可！
# 注：如果未输出maven的版本，绝大部分原因都是java_home变量的配置不正确，请仔细检查！
```

### Maven配置

需要改**maven/conf/settings.xml**配置文件，来修改maven的一些默认配置。我们主要休要修改的有三个配置：

1. 依赖本地缓存位置（本地仓库位置）

   ```xml
   <localRepository>D:\maven_repository</localRepository>
   ```

2. maven下载镜像：国内阿里镜像 (加快下载速度)

   修改 `<mirrors>`标签，为其添加如下子标签：

   ```xml
   <mirror>
         <id>alimaven</id>
         <name>aliyun maven</name>
         <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
         <mirrorOf>central</mirrorOf>
       </mirror>
   ```

3. 配置jdk版本项目构建（以jdk17为例）

   修改 `<profiles>`标签，为其添加如下子标签：

   ```xml
   <profile>
       <id>jdk-17</id>
       <activation>
         <activeByDefault>true</activeByDefault>
         <jdk>17</jdk>
       </activation>
       <properties>
         <maven.compiler.source>17</maven.compiler.source>
         <maven.compiler.target>17</maven.compiler.target>
         <maven.compiler.compilerVersion>17</maven.compiler.compilerVersion>
       </properties>
   </profile>
   ```

## 基于IDEA的Maven工程创建

### IDEA配置本地Maven（当前工程）

需要将配置好的maven软件，配置到idea开发工具中即可！ 注意：idea工具默认自带maven配置软件，但是因为没有修改配置，建议替换成本地配置好的maven.

打开idea的settings，选中“Build、Execution、Deployment”栏。

再依次点击 build tool / maven

选中本地maven软件根目录。

User settings file 选中 maven/conf/settings.xml 即可。

### 配置全局Maven环境

在关闭项目后，新建项目时，选择Customize，再选择All Settings。

然后步骤跟以上一样。

### 梳理Maven工程GAVP属性

Maven工程相对之前的工程，多出一组**GAVP属性**，`G`、`A`、`V`需要我们在创建项目的时指定，`P`有默认值，后期通过配置文件修改。

Maven 中的 **GAVP** 是指 `GroupId`（组名）、`ArtifactId`（工件名）、`Version`（版本）、`Packaging`（打包） 四个属性的缩写。其中前三个是必要的，而 Packaging 属性为可选项。这四个属性主要为每个项目在maven仓库总做一个标识。有了具体标识，方便maven软件对项目进行管理和互相引用。

#### GAVP遵循规则

1) GroupID 格式：com.{公司/BU }.业务线.[子业务线]，最多 4 级。

   1) 说明：{公司/BU} 例如：alibaba/taobao/tmall/aliexpress 等 BU 一级；子业务线可选。
   2) 正例：com.taobao.tddl 或 com.alibaba.sourcing.multilang com.atguigu.java

2) ArtifactID 格式：产品线名-模块名。语义不重复不遗漏，先到仓库中心去查证一下。

   1) 正例：tc-client / uic-api / tair-tool / bookstore

3) Version版本号格式推荐：主版本号.次版本号.修订号 1.0.0

   1) 主版本号：当做了不兼容的 API 修改，或者增加了能改变产品方向的新功能。
   2) 次版本号：当做了向下兼容的功能性新增（新增类、接口等）。
   3) 修订号：修复 bug，没有修改方法签名的功能加强，保持 API 兼容性。
   4) 例如： 初始→1.0.0 修改bug → 1.0.1 功能调整 → 1.1.1等

4) Packaging定义规则：

   指示将项目打包为什么类型的文件，idea根据packaging值，识别maven项目类型！

   packaging 属性为 jar（默认值），代表普通的Java工程，打包以后是.jar结尾的文件。

   packaging 属性为 war，代表Java的web工程，打包以后.war结尾的文件。

   packaging 属性为 pom，代表不会打包，用来做继承的父工程。



**参考以下文章进行Web项目创建**：

[IDEA2022版本创建maven web项目（两种方式）最全图文教学_idea创建web+maven项目-CSDN博客](https://blog.csdn.net/weixin_45384457/article/details/128532296)

若只是普通项目，可以参考方法1，不选用webapp，选择quickstart则可。



## Maven Web 程序的文件结构及每个文件的作用

```bash
|-- pom.xml                               # Maven 项目管理文件 
|-- src
    |-- main                              # 项目主要代码
    |   |-- java                          # Java 源代码目录
    |   |   `-- com/example/myapp         # 开发者代码主目录
    |   |       |-- controller            # 存放 Controller 层代码的目录
    |   |       |-- service               # 存放 Service 层代码的目录
    |   |       |-- dao                   # 存放 DAO 层代码的目录
    |   |       `-- model                 # 存放数据模型的目录
    |   |-- resources                     # 资源目录，存放配置文件、静态资源等
    |   |   |-- log4j.properties          # 日志配置文件
    |   |   |-- spring-mybatis.xml        # Spring Mybatis 配置文件
    |   |   `-- static                    # 存放静态资源的目录
    |   |       |-- css                   # 存放 CSS 文件的目录
    |   |       |-- js                    # 存放 JavaScript 文件的目录
    |   |       `-- images                # 存放图片资源的目录
    |   `-- webapp                        # 存放 WEB 相关配置和资源
    |       |-- WEB-INF                   # 存放 WEB 应用配置文件
    |       |   |-- web.xml               # Web 应用的部署描述文件
    |       |   `-- classes               # 存放编译后的 class 文件
    |       `-- index.html                # Web 应用入口页面
    `-- test                              # 项目测试代码
        |-- java                          # 单元测试目录
        `-- resources                     # 测试资源目录
```

- pom.xml：Maven 项目管理文件，用于描述项目的依赖和构建配置等信息。
- src/main/java：存放项目的 Java 源代码。
- src/main/resources：存放项目的资源文件，如配置文件、静态资源等。
- src/main/webapp/WEB-INF：存放 Web 应用的配置文件。
- src/main/webapp/index.html：Web 应用的入口页面。
- src/test/java：存放项目的测试代码。
- src/test/resources：存放测试相关的资源文件，如测试配置文件等。

## Maven依赖管理

Maven 的依赖管理能够帮助开发人员自动解决软件包依赖问题，使得开发人员能够轻松地将其他开发人员开发的模块或第三方框架集成到自己的应用程序或模块中，避免出现版本冲突和依赖缺失等问题。

通过定义 POM 文件，Maven 能够自动解析项目的依赖关系，并通过 Maven 仓库自动下载和管理依赖，从而避免了手动下载和管理依赖的繁琐工作和可能引发的版本冲突问题。


**maven项目信息属性配置和读取：**

```xml
<!-- 模型版本 -->
<modelVersion>4.0.0</modelVersion>
<!-- 公司或者组织的唯一标志，并且配置时生成的路径也是由此生成， 如com.companyname.project-group，maven会将该项目打成的jar包放本地路径：/com/companyname/project-group -->
<groupId>com.companyname.project-group</groupId>
<!-- 项目的唯一ID，一个groupId下面可能多个项目，就是靠artifactId来区分的 -->
<artifactId>project</artifactId>
<!-- 版本号 -->
<version>1.0.0</version>

<!--打包方式
    默认：jar
    jar指的是普通的java项目打包方式！ 项目打成jar包！
    war指的是web项目打包方式！项目打成war包！
    pom不会讲项目打包！这个项目作为父工程，被其他工程聚合或者继承！后面会讲解两个概念
-->
<packaging>jar/pom/war</packaging>
```

**依赖管理和添加：**

```xml
<!-- 
   通过编写依赖jar包的gav必要属性，引入第三方依赖！
   scope属性是可选的，可以指定依赖生效范围！
   依赖信息查询方式：
      1. maven仓库信息官网 https://mvnrepository.com/
      2. mavensearch插件搜索
 -->
<dependencies>
    <!-- 引入具体的依赖包 -->
    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.17</version>
        <!--
            生效范围
            - compile ：main目录 test目录  打包打包 [默认]
            - provided：main目录 test目录  Servlet
            - runtime： 打包运行           MySQL
            - test:    test目录           junit
         -->
        <scope>runtime</scope>
    </dependency>

</dependencies>
```

**依赖版本提取和维护:**

```XML
<!--声明版本-->
<properties>
  <!--命名随便,内部制定版本号即可！-->
  <junit.version>4.11</junit.version>
  <!-- 也可以通过 maven规定的固定的key，配置maven的参数！如下配置编码格式！-->
  <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
</properties>

<dependencies>
  <dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <!--引用properties声明版本 -->
    <version>${junit.version}</version>
  </dependency>
</dependencies>
```

#### 依赖传递和冲突

**依赖传递**指的是当一个模块或库 A 依赖于另一个模块或库 B，而 B 又依赖于模块或库 C，那么 A 会间接依赖于 C。这种依赖传递结构可以形成一个依赖树。当我们引入一个库或框架时，构建工具（如 Maven、Gradle）会自动解析和加载其所有的直接和间接依赖，确保这些依赖都可用。

**依赖传递的作用**

1. 减少重复依赖：当多个项目依赖同一个库时，Maven 可以自动下载并且只下载一次该库。这样可以减少项目的构建时间和磁盘空间。
2. 自动管理依赖: Maven 可以自动管理依赖项，使用依赖传递，简化了依赖项的管理，使项目构建更加可靠和一致。
3. 确保依赖版本正确性：通过依赖传递的依赖，之间都不会存在版本兼容性问题，确实依赖的版本正确性！

**依赖冲突**

当直接引用或者间接引用出现了相同的jar包，一个项目就会出现相同的重复jar包，这就产生了冲突。

maven自动解决依赖冲突问题能力，会按照自己的原则，进行重复依赖选择。同时也提供了手动解决的冲突的方式。

**自动选择原则**

- 短路优先原则（第一原则）
  - A—>B—>C—>D—>E—>X(version 0.0.1)
  - A—>F—>X(version 0.0.2)
  - 则A依赖于X(version 0.0.2)。
- 依赖路径长度相同情况下，则“先声明优先”（第二原则）
  - A—>E—>X(version 0.0.1)
  - A—>F—>X(version 0.0.2)
- 在`<depencies></depencies>`中，先声明的，路径相同，会优先选择！

**手动排除**

用`<exclusions>`排除不需要或者冲突的依赖。

#### 依赖范围

依赖的jar包，默认情况下，可以在任何地方使用。可以通过`<dependency>`中的`<scope>`设置作用范围。

- 主程序范围有效。(main文件夹范围内)
- 测试程序范围有效。(test文件夹范围内)
- 是否参与打包运行。(package指令范围内)

例如：

| scope值         | 主程序 | 测试程序 | 打包（运行） | 范例        |
| --------------- | ------ | -------- | ------------ | ----------- |
| compile（默认） | Y      | Y        | Y            | log4j       |
| test            | -      | Y        | -            | junit       |
| provided        | Y      | Y        | -            | servlet-api |
| runtime         | -      | Y        | Y            | jdbc驱动    |

## 扩展构建管理和插件配置

项目构建是指将源代码、依赖库和资源文件等转换成可执行或可部署的应用程序的过程，在这个过程中包括编译源代码、链接依赖库、打包和部署等多个步骤。

**清理->编译->测试->报告->打包->部署**

| 命令        | 描述                                        |
| ----------- | ------------------------------------------- |
| mvn clean   | 清理编译或打包后的项目结构,删除target文件夹 |
| mvn compile | 编译项目，生成target文件                    |
| mvn test    | 执行测试源码 (测试)                         |
| mvn site    | 生成一个项目依赖信息的展示页面              |
| mvn package | 打包项目，生成war / jar 文件                |
| mvn install | 打包后上传到maven本地仓库(本地部署)         |
| mvn deploy  | 只打包，上传到maven私服仓库(私服部署)       |

![在这里插入图片描述](https://i-blog.csdnimg.cn/blog_migrate/4b9e55342e128d8e9f68ba621bdb0bbe.png#pic_center)

## 构建命令周期

构建生命周期可以理解成是一组固定构建命令的有序集合。

触发周期后的命令，会自动触发周期前的命令。



- 清理周期：主要是对项目编译生成文件进行清理
  - 包含命令：clean
- 默认周期：定义了真正构件时所需要执行的所有步骤，它是生命周期中最核心的部分
  - 包含命令：compile - test - package - install / deploy
- 报告周期
  - 包含命令：site
  - 打包: mvn clean package 本地仓库: mvn clean install

```bash
打包: mvn clean package
重新编译: mvn clean compile
本地部署: mvn clean install 
```

