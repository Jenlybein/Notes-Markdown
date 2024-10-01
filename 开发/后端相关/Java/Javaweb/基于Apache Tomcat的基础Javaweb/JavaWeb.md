------

<center><font size=7><b> JavaWeb开发——个人笔记 </center></font></center>

---

[TOC]

---

本学习笔记参考黑马、尚硅谷等教程，先做总述写清笔记所记内容，给出部分已有笔记链接，而后详细介绍部分内容。



## JavaWeb简介

Web开发（Web Development）是创建和维护网站和Web应用程序的过程。它涉及前端开发、后端开发、数据库管理、安全性等方面。Java Web开发是使用Java语言进行Web应用程序开发的过程，因其稳定性、可扩展性和安全性而广泛应用于企业级应用开发。



### 后端开发

常用的后端语言：Java、Python、Ruby、Node.js、Golang

本笔记使用 `JAVA` 作为后端语言。

**学习笔记**：[JAVA学习笔记-CSDN博客](https://blog.csdn.net/Jenlibein/article/details/140695567)

- **本笔记JAVA后端相关技术**
  - **Apache Tomcat**
  - **Maven**
  - **Spring 框架**



### 前端开发

前端一般使用 `HTML`、`CSS`、`JavaScript`作为开发语言。

其中，国内通常在 JavaScript 中使用 `vue.js` 框架。

- **学习笔记**
  - [HTML学习笔记[Web开发]_静态页面html笔记-CSDN博客](https://blog.csdn.net/Jenlibein/article/details/140547521)
  - [CSS学习笔记[Web开发]-CSDN博客](https://blog.csdn.net/Jenlibein/article/details/140549428)
  - [JavaScript基础知识学习笔记[Web开发]-CSDN博客](https://blog.csdn.net/Jenlibein/article/details/140548442)
- **学习教程**
  - [现代 JavaScript 教程](https://zh.javascript.info/) 可以在此处查看其余的JavaScript教程
  - [HTML 教程 (w3school.com.cn)](https://www.w3school.com.cn/html/index.asp) 可以在此处查看其余的Html教程
  - [CSS 教程 (w3school.com.cn)](https://www.w3school.com.cn/css/index.asp) 可以在此处查看其余的Html教程



### 前后端联合

`HTTP`（HyperText Transfer Protocol，超文本传输协议）是Web通信的基础协议，用于在客户端（通常是Web浏览器）和服务器之间传输超文本。它是一个无状态的协议，意味着每个请求和响应都是独立的，不会保留以前的交互信息。

`Servlet` 是 Java Web 应用的核心组件，用于处理客户端请求并生成动态响应。Tomcat 作为 Servlet 容器，提供了一个运行环境，使得开发者能够部署和运行 Servlet 应用。

`JSP` 是一种用于创建动态 Web 页面技术，允许在 HTML 中嵌入 Java 代码。Tomcat 的 Jasper 引擎将 JSP 页面编译成 Java Servlet，交由 Catalina 处理。(不过当前使用已经非常少，处于被淘汰状态)



## HTTP协议

[HTTP协议——个人笔记-CSDN博客](https://blog.csdn.net/Jenlibein/article/details/140860710)



## Apache Tomcat

[Tomcat部署——个人笔记-CSDN博客](https://blog.csdn.net/Jenlibein/article/details/140853746)



## Tomcat Servlet

[Servlet——个人笔记-CSDN博客](https://blog.csdn.net/Jenlibein/article/details/141141455)



## JDBC 技术

外链：...



## MVC架构模式

MVC（Model View Controller）是软件工程中的一种**`软件架构模式`**，它把软件系统分为**`模型`**、**`视图`**和**`控制器`**三个基本部分。用一种业务逻辑、数据、界面显示分离的方法组织代码，将业务逻辑聚集到一个部件里面，在改进和个性化定制界面及用户交互的同时，不需要重新编写业务逻辑。



+ **MVC模式下，具体功能解释**
  + **M**：Model 模型层
    + 存放和数据库对象的实体类以及一些用于存储非数据库表完整相关的VO对象
    + 存放一些对数据进行逻辑运算操作的的一些业务处理代码

  + **V**：View 视图层
    + 存放一些视图文件相关的代码 html css js等
    + 在前后端分离的项目中,后端已经没有视图文件,该层次已经衍化成独立的前端项目

  + **C**：Controller 控制层
    + 接收客户端请求,获得请求数据
    + 调用服务层处理业务逻辑，接收结果
    + 将准备好的数据响应给客户端




+ **MVC模式下,项目中的常见包**
  + M:
    1. 实体类包(pojo /entity /bean) 专门存放和数据库对应的实体类和一些VO对象
    2. 数据库访问包(dao/mapper)  专门存放对数据库不同表格CURD方法封装的一些类
    3. 服务包(service)                       专门存放对数据进行业务逻辑运算的一些类
  + C:
    1. 控制层包(controller)

  + V:
    1. web目录下的视图资源 html css js img 等
    2. 前端工程化后,在后端项目中已经不存在了



**非前后端分离的MVC**

![1690349913931](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408151843515.png)



**前后端分离的MVC**

![1683363039636](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408151843524.png)



### 常见包的理解

#### 工具类层

##### Util

工具。顾明思义，util层就是存放工具类的地方，对于一些独立性很高的小功能，或重复性很高的代码片段，可以提取出来放到Util层中。

例如，字符串处理、日期处理等。



#### 数据层

##### POJO (Plain Ordinary Java Object)

简单的 Java 对象，强调它是一个普通的 Java 对象。POJO 通常只有 private 属性以及相应的 public 的 getter 和 setter 方法。

POJO 不实现任何接口或继承任何特定类，不承担任何特殊的身份，只能装载数据。

##### PO (Persistent Object)

持久化对象。与数据库中的表相对应的 Java 对象，PO 对象通常需要实现序列化接口，用于保存和恢复数据。

##### VO (Value Object)

值对象。用于业务层之间的数据传递，或者用于页面显示的数据。VO 可以包含一些业务逻辑验证。

##### **DTO (Data Transfer Object)**

数据传输对象。用于在不同层之间传输数据，与 VO 类似。DTO 通常用于减少方法调用的次数，将多个参数封装成一个对象进行传递。有时候我们仅仅需要获得某一个表的几个字段，所以此时可以用DTO存储这几个字段。与view的用法相同.



**下面要讲的这几个包，本质上都是POJO类**

##### View

视图对象。当包名为 view 时，通常存放的是对实体表的映射类，用于给前端展示。视图对象可以包含一个或多个表的部分字段，用于特定展示需求。

比如：user表中有name、id、age，出于某些原因，我们只需要向用户展示name和id值，但进行相关操作时，我们往往需要对user表进行很复杂读处理，每次定义都很麻烦。因此可以在view层中定义user_view类，将user表中的name、id存入user_view视图，这样在进行操作时只需调用视图表，就可以完成相对应的操作

##### Domain

域对象。代表业务领域中的概念，通常包含多个相关表的综合信息。例如，一个简历域可能包括工作经验表、项目经验表、简历基本信息表。在 Domain 包中，可以定义一个大的简历对象，将多个表的内容整合在一个对象中，作为整体操作。

##### Entity

实体类。严格对应数据库中的表，数据库中有什么字段，Entity 类中就有什么字段。操作数据库表时，通常直接操作对应的实体类。

比如：user表中有name、id、age，则entity包中对应的同名类也只有这三个同名属性。

##### Model

模型类。当包名为 model 时，通常存放的是实体类的模型，用于后端操作。模型类可以包含多个表的综合信息，用于后端逻辑处理。

比如：user表中有name、id、age，出于安全原因，我们需要把用户的密码定义在另一表中，即user_passwd表，但进行相关操作时，我们往往需要将两个表关联使用，每次定义都很麻烦。因此可以在model层中定义user_model类，将user表中的信息与user_passwd表中的信息整合成一张综合表，这样在进行操作时只需调用综合表，就可以完成对两个表的关联操作

> 总结
>
> - 如果想对几个表综合操作，就用domain
> - 如果是严格对数据库表操作，就用entity
> - 如果想显示某个几个表的综合信息，就用model。注意model包一般放在service层。
> - 如果只想展示某个表中的几段信息，就用view。注意view包一般放在controller层。



#### 数据访问层

##### DAO (Data Access Object)

数据访问对象。用于访问数据库，通常与 PO 结合使用。DAO 包含了各种数据库操作方法，如增删改查（CRUD）。每个数据表通常有一个对应的 DAO。

##### Mapper

在 MyBatis 框架中，Mapper 实际上是 DAO 的一种实现形式。MyBatis 使用 XML 或注解来定义 SQL 映射，Mapper 层与 DAO 层功能相同。



#### 业务层

##### Service

服务层。用于处理包含多个 POJO 对象（即对多个表的数据操作）时的事务管理。Service 层的接口实现类通常被注入一个或多个 DAO 对象，以完成有意义的数据操作。Service 层整合了 DAO 和 Entity 类，封装成业务方法，供 Controller 层调用。

##### BO (Business Object)

业务对象。BO 在 DAO 的基础上添加业务方法，形成业务对象。BO 中的业务方法通常针对单个实体对象，如果跨越多个实体对象，则方法应放在 Service 层。



#### 控制层

##### Controller

负责请求转发，接受页面传递过来的参数，根据参数的不同，是调用不同的Service层方法进行操作，操作完成后将返回结果传递给页面。



```powershell
# 常见的 MVC 架构的目录结构示例
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           ├── controller/      // 控制层
│   │           │   ├── UserController.java
│   │           │   └── ...
│   │           ├── service/         // 业务层
│   │           │   ├── UserService.java
│   │           │   ├── UserServiceImpl.java
│   │           │   └── ...
│   │           ├── repository/      // 数据访问层
│   │           │   ├── UserRepository.java
│   │           │   └── ...
│   │           ├── model/           // 数据层
│   │           │   ├── User.java
│   │           │   ├── UserDTO.java
│   │           │   ├── UserVO.java
│   │           │   └── ...
│   │           ├── util/            // 工具类层
│   │           │   ├── DateUtil.java
│   │           │   └── ...
│   │           └── config/          // 配置层
│   │               ├── WebConfig.java
│   │               └── ...
│   ├── resources/
│   │   ├── static/                  // 静态资源
│   │   │   ├── css/
│   │   │   ├── js/
│   │   │   └── ...
│   │   ├── templates/               // 视图模板
│   │   │   ├── index.html
│   │   │   └── ...
│   │   └── application.properties   // 配置文件
│   └── webapp/
│       ├── WEB-INF/
│       │   ├── views/
│       │   │   ├── home.jsp
│       │   │   └── ...
│       └── ...
└── test/
    └── java/
        └── com/
            └── example/
                ├── controller/
                ├── service/
                ├── repository/
                ├── model/
                ├── util/
                └── ...
```



## 好用的开发依赖包

### Junit

JUnit是一个Java语言的单元测试框架。它由Kent Beck和Erich Gamma建立，逐渐成为源于Kent Beck的sUnit的xUnit家族中最为成功的一个。 JUnit有它自己的JUnit扩展生态圈。多数Java的开发环境都已经集成了JUnit作为单元测试的工具。

JUnit是由 Erich Gamma 和 Kent Beck 编写的一个回归测试框架（regression testing framework）。Junit测试是程序员测试，即所谓白盒测试，因为程序员知道被测试的软件如何（How）完成功能和完成什么样（What）的功能。Junit是一套框架，继承TestCase类，就可以用Junit进行自动测试了。

IDEA中可以自动安装。

### Jackson

Jackson库就是是一个基于Java语言的开源JSON格式解析工具。它的主要功能是提供JSON解析和生成，简单来说，Jackson就是用于将Java对象与JSON数据进行互相转换。

官网：[GitHub - FasterXML/jackson: Main Portal page for the Jackson project](https://github.com/FasterXML/jackson)

- 下载：
  - jackson-core : [Maven Central: com.fasterxml.jackson.core:jackson-core (sonatype.com)](https://central.sonatype.com/artifact/com.fasterxml.jackson.core/jackson-core/versions)
  - jackson-databind : [Maven Central: com.fasterxml.jackson.core:jackson-databind (sonatype.com)](https://central.sonatype.com/artifact/com.fasterxml.jackson.core/jackson-databind/versions)
  - jackson-annotations : [Maven Central: com.fasterxml.jackson.core:jackson-annotations (sonatype.com)](https://central.sonatype.com/artifact/com.fasterxml.jackson.core/jackson-annotations?smo=true)

### Lombok

Lombok是一个Java库，能自动插入编辑器并构建工具，简化Java开发。通过添加注解的方式，不需要为类编写getter或equals方法，同时可以自动化日志变量。[官网链接 Project Lombok](https://projectlombok.org/) 、 [下载链接 Download (projectlombok.org)](https://projectlombok.org/download)

**IDEA 中安装**

settings -> plugins -> 搜索Lombok -> 安装

settings  -> Build,Execution,Deployment -> Compiler -> Annotation Processors -> 右侧加减栏下Default -> Enable annotation processing

```java
// 代码编译时会自动生成`getter`、`setter`、`equals`、`hashCode`、`toString` 方法
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -8054600833969507380L;
    private Integer id;
    private String username;
    private Integer age;
}
```

```java
//自动化日志变量
@Slf4j
@RestController
@RequestMapping(("/user"))
public class UserController {

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable Integer id) {
        User user = new User();
        user.setUsername("风清扬");
        user.setAge(21);
        user.setId(id);

        if (log.isInfoEnabled()) {
            log.info("用户 {}", user);
        }
        return user;
    }
}
// 通过反编译可以看到@Slf4j注解生成了log日志变量（严格意义来说是常量），无需去声明一个log就可以在类中使用log记录日志。
```

> 常用注解
>
> @Setter 注解在类或字段，注解在类时为所有字段生成setter方法，注解在字段上时只为该字段生成setter方法。
> @Getter 使用方法同上，区别在于生成的是getter方法。
> @ToString 注解在类，添加toString方法。
> @EqualsAndHashCode 注解在类，生成hashCode和equals方法。
> @AllArgsConstructor 注解在类，生成包含类中所有字段的构造方法。（全参构造）
> @NoArgsConstructor 注解在类，生成无参的构造方法。（无参构造）
> @RequiredArgsConstructor 注解在类，为类中需要特殊处理的字段生成构造方法，比如final和被@NonNull注解的字段。
> @Data 注解在类，生成setter/getter、equals、canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法。
> @Slf4j 注解在类，生成log变量，严格意义来说是常量。private static final Logger log = LoggerFactory.getLogger(UserController.class);





## 实操项目开发一（日程管理系统实践）

### 项目搭建

#### 数据库创建

1. 创建架构(模式)

   若在IDEA中操作需安装专业版

2. 创建表

   ```mysql
   SET NAMES utf8mb4;
   SET FOREIGN_KEY_CHECKS = 0;
   
   -- 创建日程表
   DROP TABLE IF EXISTS `sys_schedule`;
   CREATE TABLE `sys_schedule`  (
     `sid` int NOT NULL AUTO_INCREMENT,
     `uid` int NULL DEFAULT NULL,
     `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
     `completed` int(1) NULL DEFAULT NULL,
     PRIMARY KEY (`sid`) USING BTREE
   ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;
   
   -- 插入日程数据
   -- ----------------------------
   
   -- 创建用户表
   DROP TABLE IF EXISTS `sys_user`;
   CREATE TABLE `sys_user`  (
     `uid` int NOT NULL AUTO_INCREMENT,
     `username` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
     `user_pwd` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
     PRIMARY KEY (`uid`) USING BTREE,
     UNIQUE INDEX `username`(`username`) USING BTREE
   ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;
   
   -- 插入用户数据
   INSERT INTO `sys_user` VALUES (1, 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e');
   INSERT INTO `sys_user` VALUES (2, 'lisi', 'e10adc3949ba59abbe56e057f20f883e');
   
   SET FOREIGN_KEY_CHECKS = 1;
   ```

   得到表 sys_user 如下

   | uid  | username | user_pwd                         |
   | ---- | -------- | -------------------------------- |
   | 1    | zhangsan | e10adc3949ba59abbe56e057f20f883e |
   | 2    | lisi     | e10adc3949ba59abbe56e057f20f883e |

   得到空表 sys_schedule 如下

   | sid  | uid  | title | completed |
   | ---- | ---- | ----- | --------- |

   

#### 导入依赖

Lombok

Alibaba Druid

mysql-connector



#### POJO包处理

- 基本要求
  - 实体类的类名和表格名称应该对应(对应不是一致)
  - 实体类的属性名和表格的列名应该对应
  - 每个属性都必须是私有的
  - 每个属性都应该具备 getter setter
  - 必须具备无参构造器
  - 应该实现序列化接口( 缓存   分布式项目数据传递  可能会将对象序列化)
  - 应该重写类的 hashcode 和 equals 方法
  - toString 可选是否重写

src 中创建类 test.schdule.pojo -> SysSchedule 、 SysUser （对应数据库中的表，属性对应列名)

```java
// SysUser 代码(此处使用Lombok)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysUser implements Serializable {
    private Integer uid;
    private Integer username;
    private Integer userPwd;
}
```

```java
// SysSchedule 代码(此处使用Lombok)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SysSchedule {
    private Integer sid;
    private Integer uid;
    private String title;
    private Integer completed;
}
```



#### DAO包处理

- 基本要求
  - 该包中定义针对表格的CURD方法
  - 一般需要定义接口和实现类，控制合作者代码统一和方便调用者
  - 写好注释和文档



##### 通用类与工具类的导入

导入JDBCUtil连接池工具类并准备.properties配置文件

```java
package test.schdule.UTIL;

public class JDBCUtil{
    //创建连接池引用，因为要提供给当前项目全局使用，所以创建为静态的。
    private static final DataSource ds;
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    //在项目启动时，即创建连接池对象，赋值给dataSource
    static{
        try {
            Properties ppt = new Properties();
            InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
            ppt.load(in);

            ds = DruidDataSourceFactory.createDataSource(ppt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 向外提供连接池的方法
    public static DataSource getDataSource(){
        return ds;
    }

    //对外提供获取连接的静态方法
    public static Connection getConnection() throws SQLException {
        // ThreadLocal中获取Connection
        Connection conn = threadLocal.get();
        // ThreadLocal中无conn，即第一次获取
        if(conn == null){
            conn = ds.getConnection();
            threadLocal.set(conn);
        }
        return conn;
    }
    //对外提供回收连接的静态方法
    public static void release() throws SQLException {
        Connection conn = threadLocal.get();
        if(conn != null){
            threadLocal.remove();
            // 如果开启了事务的手动提交，操作完毕后，归还给连接池之前，要将事务自动提交改为true
            conn.setAutoCommit(true);
            conn.close();
        }
    }
}
```

```properties
driverClassName=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/schdule_system
username=root
password=86503358299
initialSize=5
maxActive=10
maxWait=1000
```

导入BaseDAO类

```java
package test.schdule.DAO;

import test.schdule.Util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用的数据库操作代码封装
 */
public class BaseDAO {
    /**
     * 通用的增删改方法
     * @param sql 调用者要执行的sql语句
     * @param params sql语句中占位符要赋值的参数
     * @return 受影响的行数
     * @throws Exception 错误
     */
    public int executeUpdate(String sql, Object... params) throws Exception {
        // 获取数据库连接，预编译 sql 语句
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        // 为占位符复制，执行sql，接收返回结果
        if(params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]); //?的编号从1开始，不是从0开始，数组的下标是从0开始
            }
        }
        int row  = ps.executeUpdate();
        // 释放资源
        ps.close();
        //这里检查下是否开启事务，开启不关闭连接，业务方法关闭!
        //connection.getAutoCommit()为false，不要在这里回收connection，由开启事务的地方回收
        //connection.getAutoCommit()为true，正常回收连接
        //没有开启事务的话，直接回收关闭即可!
        if (conn.getAutoCommit()) {
            JDBCUtil.release();
        }
        return row;
    }

    /**
     * 通用查询查询多个Javabean对象的方法
     * @param clazz 接收T类型的Class对象（例如Employee.class)
     */
    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws Exception {
        // 获取数据库连接，预编译 sql 语句
        Connection conn = JDBCUtil.getConnection();
        // 准备语句对象
        PreparedStatement ps = conn.prepareStatement(sql);
        // 为占位符复制，执行sql，接收返回结果
        if(params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
        // 接收结果并处理
        ResultSet rs = ps.executeQuery();
        List<T> list = new ArrayList<>();

        // 获取结果集中的元数据对象（包含：列的数量、每列的名称）
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        //遍历结果集ResultSet，把查询结果中的一条一条记录，变成一个一个T 对象，放到list中。
        while (rs.next()) {
            // 循环一次，代表有一行数据，通过反射创建一个对象
            T t = clazz.newInstance(); //要求这个类型必须有公共的无参构造
            // 循环遍历当前行的列
            for (int i = 1; i <= columnCount; i++) {
                //通过下标获取列的值
                Object value = rs.getObject(i);

                // 获取到列的value值，这个值就是t这个对象的某一属性
                // 获取当前列的名字 = 对象的属性名
                String fieldName = rsmd.getColumnLabel(i);
                // 通过类对象喝fieldName获取要封装的对象的属性
                Field field = clazz.getDeclaredField(fieldName);
                // 突破封装的private
                field.setAccessible(true);//这么做可以操作private的属性
                // 通过fieldName和对象的属性名来将值设入
                field.set(t,value);
            }
            list.add(t);
        }
        rs.close();
        ps.close();
        //这里检查下是否开启事务，开启不关闭连接，业务方法关闭!
        //没有开启事务的话，直接回收关闭即可!
        if (conn.getAutoCommit()) {
            JDBCUtil.release();
        }
        return list;
    }

    /**
     * 查询单条，复用查询多条的方法
     */
    public <T> T executeQueryBean(Class<T> clazz, String sql, Object...params) throws Exception{
        List<T> list = this.executeQuery(clazz, sql, params);
        if(list!=null && !list.isEmpty()) {
            return list.get(0);
        }else{
            return null;
        }
    }

    public <T> T executeQueryObject(Class<T> clazz, String sql, Object...params) throws Exception{
        T t = null;
        // 获取数据库连接，预编译 sql 语句
        Connection conn = JDBCUtil.getConnection();
        // 准备语句对象
        PreparedStatement ps = conn.prepareStatement(sql);
        // 为占位符复制，执行sql，接收返回结果
        if(params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
        // 接收结果并处理
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            t = (T) rs.getObject(1);
        }
        rs.close();
        ps.close();
        //这里检查下是否开启事务，开启不关闭连接，业务方法关闭!
        //没有开启事务的话，直接回收关闭即可!
        if (conn.getAutoCommit()) {
            JDBCUtil.release();
        }
        return t;
    }
}
```



##### 接口类与实现类

定义SysScheduleDao接口类

```java
//SysSchedule 接口定义

/**
 * 介绍一下当前类...
 * 作者:......
 * 时间:......
 */
public interface SysScheduleDAO {
    /**
     * 用于向数据中增加一条日程记录
     * @param schedule 日程数据以SysSchedule实体类对象形式入参
     * @return 返回影响数据库记录的行数，行数为0说明增加失败，行数大于0说明增加成功
     */
    int addSchedule(SysSchedule schedule) throws Exception;

    /**
     * 查询所有用户的所有日程
     * @return 结果装入List<SysSchedule>返回
     */
    List<SysSchedule> findAll() throws Exception;
    
    // 后续需要什么方法就在接口添加什么方法
}
```

定义 SysScheduleDaoimpl类 实现 SysScheduleDao 接口类

```java
package test.schdule.dao.impl;

import test.schdule.dao.BaseDAO;
import test.schdule.dao.SysScheduleDAO;
import test.schdule.pojo.SysSchedule;

import java.util.List;

public class SysScheduleDAOimpl extends BaseDAO implements SysScheduleDAO {
    @Override
    public int addSchedule(SysSchedule schedule) throws Exception{
        String sql = "INSERT INTO sys_schedule values(DEFAULT,?,?,?)";
        return executeUpdate(sql,schedule.getUid(),schedule.getTitle(),schedule.getCompleted());
    }

    @Override
    public List<SysSchedule> findAll() throws Exception {
        String sql = "SELECT * from sys_schedule";
        return executeQuery(SysSchedule.class, sql);
    }
}
```

对该实现类进行测试

```java
package test.schdule.test;

public class testScheduleDao {
    private static SysScheduleDAO scdao;
    @BeforeClass
    public static void initScheduleDao(){
        scdao = new SysScheduleDAOimpl();
    }

    @Test
    public void testFindAll() throws Exception {
        List<SysSchedule> list = scdao.findAll();
        for (SysSchedule sysSchedule : list) {
            System.out.println(sysSchedule);
        }
    }

    @Test
    public void testAddSchedule() throws Exception {
        SysSchedule ss = new SysSchedule(null,2,"学习",0);
        int row = scdao.addSchedule(ss);
        System.out.println(row);
    }
}
```



#### SERVICE包处理

接口类

```java
package test.schdule.service.impl;

public class SysScheduleServiceImpl {
}
```

```java
package test.schdule.service;

public interface SysUserService {
}
```

实现类

```java
package test.schdule.service.impl;

public class SysScheduleServiceImpl {
}
```

```java
package test.schdule.service.impl;

public class SysUserServiceImpl {
}
```



#### CONTROLLER包处理

- 该层的包一般用Servlet实现。

BaseController处理请求路径问题

```java
package test.schdule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 判断此次请求：增or删or改or查
        String uri = req.getRequestURI(); // 例 → /schedule/add
        String[] split = uri.split("/");
        String methodName = split[split.length - 1];

        // 使用反射，通过方法名获取下面的方法
        Class<? extends BaseController> aClass = this.getClass();
        // 获取方法
        try {
            Method declaredMethod = aClass.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            declaredMethod.setAccessible(true); // 破解方法的访问修饰符
            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

- 多个处理器继承BaseController

SysScheduleController 

```java
package test.schdule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 判断此次请求：增or删or改or查
        String uri = req.getRequestURI(); // 例 → /schedule/add
        String[] split = uri.split("/");
        String methodName = split[split.length - 1];

        // 使用反射，通过方法名获取下面的方法
        Class<? extends BaseController> aClass = this.getClass();
        // 获取方法
        try {
            Method declaredMethod = aClass.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            declaredMethod.setAccessible(true); // 破解方法的访问修饰符
            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

SysUserController 

```java
package test.schdule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/*")
public class SysUserController extends BaseController {
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("user add");
    }
    protected void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("user remove");
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("user update");
    }
    protected void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("user find");
    }
}
```



#### 加密工具类的使用

导入 MD5Util.java 工具类
```java
package test.schdule.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public final class MD5Util {
    public static String encrypt(String strSrc) {
        try {
            char hexChars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', 'a', 'b', 'c', 'd', 'e', 'f' };
            byte[] bytes = strSrc.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            bytes = md.digest();
            int j = bytes.length;
            char[] chars = new char[j * 2];
            int k = 0;
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                chars[k++] = hexChars[b >>> 4 & 0xf];
                chars[k++] = hexChars[b & 0xf];
            }
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("MD5加密出错!!!");
        }
    }
}
```



#### 页面资源导入

创建以下页面

login.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="static/css/login&register.css" />
</head>
<body>
    <div class="container">
        <h1 class="ht">欢迎使用日程管理系统</h1>
        <h3 class="ht">请登录</h3>
        <form method="post" action="/user/login" onsubmit="return checkForm()">
            <input id="usernameInput" placeholder="请输入账号(5到10位数字)" type="text" name="username" onblur="checkUsername()" required>
            <span id="usernameMsg">&nbsp&nbsp&nbsp</span>
            
            <input id="userPwdInput" placeholder="请输入密码(6位数字)" type="password" name="password" onblur="checkUserPwd()" required>
            <span id="userPwdMsg">&nbsp&nbsp&nbsp</span>

            <span id="wrongPwdOrName">&nbsp&nbsp&nbsp</span>

            <div class="signup-link">
                <input class="btn1" type="submit" value="登录">
                <input class="btn1" type="reset" value="重置">
                <button class="btn1"><a href="/register.html">去注册</a></button>
            </div>
        </form>
    </div>
</body>
<script src="static/js/login.js"></script>
</html>
```
login.js
```js
// 检验用户名格式是否合法的函数
function checkUsername(){
    // 定义正则表示字符串的规则:5到10位数字
    var  usernameReg= /^[a-zA-Z0-9]{5,10}$/
    // 获得用户在页面上输入的信息
    var usernameInput =document.getElementById("usernameInput")
    var username = usernameInput.value
    // 获得格式提示的框
    var usernameMsg =document.getElementById("usernameMsg")
    // 格式有误时,返回false,在页面上提示
    if(!usernameReg.test(username)){ 
        usernameMsg.innerText="用户名格式有误"
        return false
    }
    // 格式OK,返回true 在页面上提示OK
    usernameMsg.innerText="OK"
    return true
}

// 检验密码格式是否合法的函数
function checkUserPwd(){
    // 定义正则表示字符串的规则
    var  userPwdReg= /^[0-9]{6}$/
    // 获得用户在页面上输入的信息
    var userPwdInput =document.getElementById("userPwdInput")
    var userPwd = userPwdInput.value
    // 获得格式提示的框
    var userPwdMsg =document.getElementById("userPwdMsg")
    // 格式有误时,返回false,在页面上提示
    if(!userPwdReg.test(userPwd)){ 
        userPwdMsg.innerText="密码必须是6位数字"
        return false
    }
    // 格式OK,返回true 在页面上提示OK
    userPwdMsg.innerText="OK"
    return true

}

// 表单在提交时,校验用户名和密码格式,格式OK才会提交
function checkForm(){
    var flag1 =checkUsername()
    var flag2 =checkUserPwd()

    return flag1&&flag2
}

// 获取URL参数
const urlParams = new URLSearchParams(window.location.search);
const status_login = urlParams.get('status_login');
// 显示消息
const wrongPwdOrName = document.getElementById('wrongPwdOrName');
if (status_login === 'wrongname') {
    wrongPwdOrName.innerText = '用户名不存在.';
} else if (status_login === 'wrongpswd') {
    wrongPwdOrName.innerText = '密码错误.';
}
```

register.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="static/css/login&register.css" />
</head>
<body>
    <div class="container">
        <h1 class="ht">欢迎使用日程管理系统</h1>
        <h3 class="ht">请进行注册</h3>
        <form method="post" action="/user/register" onsubmit="return checkForm()">
            <input id="usernameInput" placeholder="请输入账号(5到10位数字)" type="text" name="username" onblur="checkUsername()" required>
            <span id="usernameMsg">&nbsp&nbsp&nbsp</span>
            
            <input id="userPwdInput" placeholder="请输入密码(6位数字)" type="password" name="password" onblur="checkUserPwd()" required>
            <span id="userPwdMsg">&nbsp&nbsp&nbsp</span>

            <input id="userConfirmPwdInput" placeholder="请重复确认密码" type="password" onblur="checkConfirmPwd()" required>
            <span id="userConfirmPwdMsg">&nbsp&nbsp&nbsp</span>

            <span id="wrongPwdOrName">&nbsp&nbsp&nbsp</span>

            <div class="signup-link">
                <input class="btn1" type="submit" value="注册">
                <input class="btn1" type="reset" value="重置">
                <button class="btn1"><a href="/login.html">去登录</a></button>
            </div>
        </form>
    </div>
</body>
<script src="static/js/register.js"></script>
</html>
```

register.js

```js
// 检验用户名格式是否合法的函数
function checkUsername(){
    // 定义正则表示字符串的规则:5到10位数字
    var  usernameReg= /^[a-zA-Z0-9]{5,10}$/
    // 获得用户在页面上输入的信息
    var usernameInput =document.getElementById("usernameInput")
    var username = usernameInput.value
    // 获得格式提示的框
    var usernameMsg =document.getElementById("usernameMsg")
    // 格式有误时,返回false,在页面上提示
    if(!usernameReg.test(username)){ 
        usernameMsg.innerText="用户名格式有误"
        return false
    }
    // 格式OK,返回true 在页面上提示OK
    usernameMsg.innerText="OK"
    return true
}

// 检验密码格式是否合法的函数
function checkUserPwd(){
    // 定义正则表示字符串的规则
    var  userPwdReg= /^[0-9]{6}$/
    // 获得用户在页面上输入的信息
    var userPwdInput =document.getElementById("userPwdInput")
    var userPwd = userPwdInput.value
    // 获得格式提示的框
    var userPwdMsg =document.getElementById("userPwdMsg")
    // 格式有误时,返回false,在页面上提示
    if(!userPwdReg.test(userPwd)){ 
        userPwdMsg.innerText="密码必须是6位数字"
        return false
    }
    // 格式OK,返回true 在页面上提示OK
    userPwdMsg.innerText="OK"
    return true
}

// 检验两次输入密码是否一致的函数
function checkConfirmPwd(){
    // 获得用户在页面上输入的信息
    var userPwdInput =document.getElementById("userPwdInput")
    var userPwd = userPwdInput.value
    var userConfirmPwdInput =document.getElementById("userConfirmPwdInput")
    var userConfirmPwd = userConfirmPwdInput.value
    // 获得格式提示的框
    var userPwdMsg = document.getElementById("userConfirmPwdMsg")
    // 格式有误时,返回false,在页面上提示
    if(userConfirmPwd!=userPwd){ 
        userPwdMsg.innerText="两次输入的密码不一致"
        return false
    }
    // 格式OK,返回true 在页面上提示OK
    userPwdMsg.innerText="OK"
    return true
}

// 表单在提交时,校验用户名和密码格式,格式OK才会提交
function checkForm(){
    var flag1 =checkUsername()
    var flag2 =checkUserPwd()
    var flag3 = checkConfirmPwd()

    return flag1&&flag2&&flag3
}

// 获取URL参数
const urlParams = new URLSearchParams(window.location.search);
const status_register = urlParams.get('status_register');
// 显示消息
const wrongPwdOrName = document.getElementById('wrongPwdOrName');
if (status_register === 'success') {
    wrongPwdOrName.innerText = '注册成功';
} else if (status_register === 'failed') {
    wrongPwdOrName.innerText = '注册失败,用户名可能已经被使用.';
}
```

registerSuccess.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="static/css/login&register.css" />
</head>
<body>
<div class="container">
    <h1 class="ht">欢迎使用日程管理系统</h1>
    <h3 class="ht">注册成功！</h3>
    <div class="signup-link">
        <button class="btn1" style="width:100px;"><a href="/login.html">请前往登录</a></button>
    </div>
</div>
</body>
</html>
```

login&register.css

```css
html,body {
    caret-color: transparent;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background: linear-gradient(#6a11cb, #6a11cb, #2575fc);
    font-family: Arial, sans-serif;
    margin: 0;
}

.container {
    background: #fff;
    padding: 2rem;
    border-radius: 15px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.5);
    max-width: 400px;
    width: 100%;
    text-align: center;
}
.container form{
    display: flex;
    flex-direction: column;
}
.container .ht{
    text-align: center;
    color: rgb(28, 162, 167);
    font-family: 幼圆;
}

.container input[type="text"],
.container input[type="password"]
{
    caret-color: auto;
    width: 90%;
    height: 20%;
    padding: 0.5rem;
    margin: auto;
    border: 2px solid #6d6d6d;
    border-radius: 10px;
    font-size: 1rem;
    white-space:nowarp;
    overflow:hidden;
    text-overflow:ellipsis;
}

.btn1{
    border: 2px solid rgb(119, 119, 119);
    border-radius: 5px;
    width:60px;
    background-color: antiquewhite;
    font-size: 0.8rem;
    font-family: 微软雅黑;
}

#usernameMsg , #userPwdMsg , #wrongPwdOrName, #userConfirmPwdMsg{
    color: rgb(230, 87, 51);
    padding: 0rem auto;
    margin-left: 0rem;
    font-size: 0.8rem;
}

.buttonContainer{
    text-align: center;
}
```





### 业务代码

以下代码实现注册、登录功能（修改相应文件）

#### controller

```java
package test.schdule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.schdule.pojo.SysUser;
import test.schdule.service.SysUserService;
import test.schdule.service.impl.SysUserServiceImpl;
import test.schdule.util.MD5Util;

import java.io.IOException;

@WebServlet("/user/*")
public class SysUserController extends BaseController {
    private final SysUserService userService = new SysUserServiceImpl();

    /**
     * 接收用户注册请求的业务处理方法（业务接口）
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 接收客户端提交的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 调用服务层的方法，完成注册功能
        SysUser sysUser = new SysUser(null,username,password);
        int rows = userService.register(sysUser);

        // 返回注册结果
        if (rows > 0) {
            // resp.sendRedirect("/registerSuccess.html"); // 此处为重定向另外一页表示成功
            // 此处我用重定向到本页，刷新网页，修改params，配合js显示隐藏信息
            resp.sendRedirect("/registerSuccess.html");
        } else {
            resp.sendRedirect("/register.html?status_register=failed");
        }
    }

    /**
     * 接收用户登录请求的业务处理方法（业务接口）
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 接收客户端提交的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 调用服务层的方法，完成登录功能
        SysUser sysUser = userService.findByUsername(username);

        // 检测用户名是否存在
        if (sysUser==null) {
            resp.sendRedirect("/login.html?status_login=wrongname");
        }
        // 检测密码是否符合
        else if( ! MD5Util.encrypt(password).equals(sysUser.getUserPwd()) ){
            resp.sendRedirect("/login.html?status_login=wrongpswd");
        }
        // 登录成功
        else {
            resp.sendRedirect("/showSchedule.html");
        }
    }
}
```

#### dao

```java
package test.schdule.dao;

import test.schdule.pojo.SysUser;

public interface SysUserDao {

    /**
     * 向数据库中增加一条用户记录的方法
     * @param sysUser 要增加的username和pwd以SysUser实体类对象的形式接收
     * @return 成功1，失败0
     */
    int addSysUser(SysUser sysUser) throws Exception;

    /**
     * Dao层：根据用户名获取完整用户信息的方法
     * @param username 要查询的用户名
     * @return 找到了则返回SysUser
     */
    SysUser findByUsername(String username) throws Exception;
}
```

```java
package test.schdule.dao.impl;

import org.junit.Test;
import test.schdule.dao.BaseDao;
import test.schdule.dao.SysUserDao;
import test.schdule.pojo.SysUser;

public class SysUserDaoImpl extends BaseDao implements SysUserDao {
    @Override
    public int addSysUser(SysUser sysUser) throws Exception {
        String sql = "INSERT INTO sys_user VALUES(DEFAULT,?,?)";
        return executeUpdate(sql,sysUser.getUsername(),sysUser.getUserPwd());
    }

    @Override
    public SysUser findByUsername(String username) throws Exception {
        String sql = "select uid,username,user_pwd userPwd from sys_user where username = ?";
        return executeQueryBean(SysUser.class,sql,username);
    }
}
```

#### service

```java
package test.schdule.service;

import test.schdule.pojo.SysUser;

public interface SysUserService {

    /**
     * 注册用户的方法
     * @param sysUser 注册的用户名和密码以SysUser对象的形式接收
     * @return 成功1，失败0
     */
    int register(SysUser sysUser) throws Exception;

    /**
     * 服务层：根据用户名获取完整用户信息的方法
     * @param username 要查询的用户名
     * @return 找到了则返回SysUser
     */
    SysUser findByUsername(String username) throws Exception;
}
```

```java
package test.schdule.service.impl;

import test.schdule.dao.SysUserDao;
import test.schdule.dao.impl.SysUserDaoImpl;
import test.schdule.pojo.SysUser;
import test.schdule.service.SysUserService;
import test.schdule.util.MD5Util;

public class SysUserServiceImpl implements SysUserService {
    private SysUserDao userDao = new SysUserDaoImpl();

    @Override
    public int register(SysUser sysUser) throws Exception {
        // 将明文密码转为密文密码
        sysUser.setUserPwd(MD5Util.encrypt(sysUser.getUserPwd()));
        // 调用DAO层的方法，将信息存入数据库
        return userDao.addSysUser(sysUser);
    }

    @Override
    public SysUser findByUsername(String username) throws Exception {
        return userDao.findByUsername(username);
    }
}
```



## 会话

### 会话管理存在的意义

#### HTTP是无状态协议

+ 无状态就是不保存状态,即无状态协议(stateless),HTTP协议自身不对请求和响应之间的通信状态进行保存,也就是说,在HTTP协议这个级别,协议对于发送过的请求或者响应都不做持久化处理
+ 简单理解:浏览器发送请求,服务器接收并响应,但是服务器不记录请求是否来自哪个浏览器,服务器没记录浏览器的特征,就是客户端的状态

> 举例: 张三去一家饭馆点了几道菜,觉得味道不错,第二天又去了,对老板说,还点上次的那几道菜
>
> + 无状态: 老板没有记录张三是否来过,更没有记录上次他点了那些菜,张三只能重新再点一遍
> + 有状态: 老板把每次来吃饭的用户都做好记录,查阅一下之前的记录,查到了张三之前的菜单,直接下单



### 会话管理实现的手段

Cookie 和 Session配合解决

+ cookie是在客户端保留少量数据的技术,主要通过响应头向客户端响应一些客户端要保留的信息
+ session是在服务端保留更多数据的技术,主要通过HttpSession对象保存一些和客户端相关的信息
+ cookie和session配合记录请求状态



### Cookie

#### 概述

cookie是一种**客户端**会话技术,cookie由服务端产生,它是服务器存放在浏览器的一小份数据,浏览器以后每次访问该服务器的时候都会将这小份数据携带到服务器去。

+ 服务端创建cookie,将cookie放入响应对象中,Tomcat容器将cookie转化为set-cookie响应头,响应给客户端
+ 客户端在收到cookie的响应头时,在下次请求该服务的资源时,会以cookie请求头的形式携带之前收到的Cookie
+ cookie是一种键值对格式的数据,从tomcat8.5开始可以保存中文,但是不推荐
+ 由于cookie是存储于客户端的数据,比较容易暴露,一般不存储一些敏感或者影响安全的数据

![image-20240914221429009](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409142218455.png)



> 应用场景举例
>
> 1. 记录用户名
>
>    当我们在用户名的输入框中输入完用户名后,浏览器记录用户名,下一次再访问登录页面时,用户名自动填充到用户名的输入框.
>
> 2. 保存电影播放进度
>
>    在网页上播放电影的时候,如果中途退出浏览器了,下载再打开浏览器播放同一部电影的时候,会自动跳转到上次退出时候的进度,因为在播放的时候会将播放进度保存到cookie中



#### Cookie的使用

创建servletA.java，给客户端设置cookie

```java
package com.test.servlet;

@WebServlet("/servletA")
public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建cookie
        Cookie cookie1 = new Cookie("keya","valuea");
        Cookie cookie2 = new Cookie("keyb","valueb");

        // 将cookie放入response对象
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
    }
}
```

进入网站查看，获取cookie

![image-20240914225619420](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409142304785.png)

之后在该网页请求任何资源都会发送cookie

![image-20240914230220672](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409142304804.png)

创建servletB.java，给客户端设置cookie

```java
@WebServlet("/servletB")
public class ServletB extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中携带的cookie
        Cookie[] cookies = req.getCookies();

        //
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + "=" + cookie.getValue());
            }
        }
    }
}
```

先进servletA再进servletB即可看到程序后台输出



#### Cookie的时效性

默认情况下Cookie的有效期是一次会话范围内，我们可以通过`cookie的setMaxAge()`方法让Cookie持久化保存到浏览器上

`cookie.setMaxAge(int expiry)`参数单位是秒，表示cookie的持久化时间，如果设置参数为0，表示将浏览器中保存的该cookie删除

-   会话级Cookie
    -   服务器端并没有明确指定Cookie的存在时间
    -   在浏览器端，Cookie数据存在于内存中
    -   只要浏览器还开着，Cookie数据就一直都在
    -   浏览器关闭，内存中的Cookie数据就会被释放
-   持久化Cookie
    -   服务器端明确设置了Cookie的存在时间
    -   在浏览器端，Cookie数据会被保存到硬盘上
    -   Cookie在硬盘上存在的时间根据服务器端限定的时间来管控，不受浏览器关闭的影响
    -   持久化Cookie到达了预设的时间会被释放

```java
package com.test.servlet;

@WebServlet("/servletA")
public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建cookie
        Cookie cookie1 = new Cookie("c1","c1_message");
        cookie1.setMaxAge(60);
        Cookie cookie2 = new Cookie("c2","c2_message");

        // 将cookie放入response对象
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
    }
}
```

![image-20240915004154922](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409150041987.png)

重启浏览器后，c2会消失，c1不会消失。c1在60秒后消失。



#### Cookie的提交路径

访问互联网资源时不能每次都需要把所有Cookie带上。访问不同的资源时,可以携带不同的cookie,我们可以通过cookie的`setPath(String path)` 对cookie的路径进行设置

```java
package com.test.servlet;

@WebServlet("/servletA")
public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建cookie
        Cookie cookie1 = new Cookie("c1","c1_message");
        cookie1.setMaxAge(60);
        Cookie cookie2 = new Cookie("c2","c2_message");
	cookie2.setPath("/servletB");
        
        // 将cookie放入response对象
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
    }
}
```

这时只有在网页向servletB发起请求时才会携带c2，其他请求不携带c2。



### Session

#### 概述

HttpSession 是一种保留更多信息在**服务端**的一种技术,服务器会为每一个客户端开辟一块内存空间,即session对象. 客户端在发送请求时,都可以使用自己的session. 这样服务端就可以通过session来记录某个客户端的状态了

+ 服务端在为客户端创建session时,会同时将session对象的id,即JSESSIONID以cookie的形式放入响应对象
+ 后端创建完session后,客户端会收到一个特殊的cookie,叫做JSESSIONID
+ 客户端下一次请求时携带JSESSIONID,后端收到后,根据JSESSIONID找到对应的session对象
+ 通过该机制,服务端通过session就可以存储一些专门针对某个客户端的信息了
+ session也是域对象(后续详细讲解)

![image-20240915004515135](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409150045211.png)

> 应用场景
>
> 1. 记录用户的登录状态
>
>    用户登录后,将用户的账号等敏感信息存入session
>
> 2. 记录用户操作的历史
>
>    例如记录用户的访问痕迹,用户的购物车信息等临时性的信息



#### HttpSession的使用

用户提交form表单到ServletA,携带用户名,ServletA获取session 将用户名存到Session,用户再请求其他任意Servlet,获取之间存储的用户

**定义表单页,提交用户名**

```html
<form action="/servlet1" method="post">
    用户名:
    <input type="text" name="username">
    <input type="submit" value="提交">
</form>
```

**定义Servlet1,将用户名存入session**

```java
@WebServlet("/servlet1")
public class Servlet1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求中的username参数
        String username = req.getParameter("username");

        // 获得session对象
        HttpSession session = req.getSession();

        System.out.println(session.getId());
        System.out.println(session.isNew());

        // 将username存入session
        session.setAttribute("username", username);

        // 客户端相应信息
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("成功");
    }
}
```

**定义Servlet2,读取用户名**

```java
@WebServlet("/servlet2")
public class Servlet2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获得session对象
        HttpSession session = req.getSession();
        System.out.println(session.getId());
        System.out.println(session.isNew());

        // 读取session中存储的用户名
        String username = (String) session.getAttribute("username");
        System.out.println("username: " + username);
    }
}
```

![image-20240915011812986](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409150118044.png)

![image-20240915011842536](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409150118585.png)

![image-20240915011918608](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409150119661.png)

![image-20240915012611618](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409150127857.png)

> getSession() 方法的处理逻辑
>
> // 判断请求中有没有一个特殊的cookie JESSIONID值
> 	// 有：根据JESSIONID找对应的SESSION对象
> 		// 找到相应对象：返回该session
> 		// 没找到：创建一个新的session返回，并且向response对象中存入一个JSESSIONID的cookie
> // 没有：创建一个新的session返回，并且向response对象中存入一个JSESSIONID的cookie
>
> ![image-20240915011057639](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409150122850.png)



#### HttpSession时效性

##### 设置session的时效的原因

+ 用户量很大之后，Session对象相应的也要创建很多。如果一味创建不释放，那么服务器端的内存迟早要被耗尽。
+ 客户端关闭行为无法被服务端直接侦测,或者客户端较长时间不操作也经常出现,类似这些的情况,就需要对session的时限进行设置了



##### 时效性设置

1. Tomcat 中存有默认时间

默认的session最大闲置时间(两次使用同一个session中的间隔时间) 在tomcat/conf/web.xml配置为30分钟

![image-20240915012119777](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409150122458.png)



2. 可在自己当前项目的web.xml对最大闲置时间进行重新设定

![image-20240915012706335](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409150127330.png)



3. 可通过HttpSession的API 对最大闲置时间进行设定

```java
// 设置最大闲置时间
session.setMaxInactiveInterval(60);
```

```java
// 直接让session失效
session.invalidate();
```



### 三大域对象

#### 域对象概述（Domain Object）

域对象: 一些用于存储数据和传递数据的对象,传递数据不同的范围,我们称之为不同的域,不同的域对象代表不同的域,共享数据的范围也不同

+ web项目中,我们一定要熟练使用的域对象分别是 **请求域**,**会话域**,**应用域**
+ **请求域**对象是`HttpServletRequest` ,传递数据的范围是一次请求之内及请求转发
+ **会话域**对象是`HttpSession`,传递数据的范围是一次会话之内,可以跨多个请求
+ **应用域**对象是`ServletContext`,传递数据的范围是本应用之内,可以跨多个会话

> 举例
>
> 1. 摆在张三工位下,就只有张三一个人能用
> 2. 摆在办公室的公共区,办公室内的所有人都可以用
> 3. 摆在楼层的走廊区,该楼层的所有人都可以用

> 请求域
>
> ![image-20240915134327851](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409151343011.png)
>
> 会话域
>
> ![image-20240915134347325](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409151343406.png)
>
> 应用域
>
> ![image-20240915134401402](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409151344046.png)
>
> 所有域在一起
>
> ![image-20240915134414237](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409151344327.png)



#### 域对象的使用

| API                                         | 功能                    |
| ------------------------------------------- | ----------------------- |
| void setAttribute(String name,String value) | 向域对象中添加/修改数据 |
| Object getAttribute(String name);           | 从域对象中获取数据      |
| removeAttribute(String name);               | 移除域对象中的数据      |



ServletA向三大域中放入数据

```java
@WebServlet("/servletA")
public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //向请求域存放数据
        req.setAttribute("request","requestMessage");

        //向会话域存放数据
        HttpSession session = req.getSession();
        session.setAttribute("session","sessionMessage");

        //向应用域存放数据
        ServletContext context = getServletContext();
        context.setAttribute("context","contextMessage");

        // 请求转发（不使用转发则无法在servletB读取到数据）
        req.getRequestDispatcher("/servletB").forward(req, resp); //选择是否注释该行，观察请求域的作用范围
    }
}
```

ServletB从三大于中取出数据

```java
@WebServlet("/servletB")
public class ServletB extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求域中的数据
        String reqMessage = (String)req.getAttribute("request");
        System.out.println("请求域： "+reqMessage);

        // 获取会话域中的数据
        HttpSession session = req.getSession();
        String sessionMessage = (String)session.getAttribute("session");
        System.out.println("会话域： "+sessionMessage);

        // 获取应用域中的数据
        ServletContext context = getServletContext();
        String contextMessage = (String)context.getAttribute("context");
        System.out.println("应用域： "+contextMessage);
    }
}
```

输出

```
// 不注释请求转发
请求域： requestMessage
会话域： sessionMessage
应用域： contextMessage

// 注释请求转发，访问ServletA后访问ServletB
请求域： null
会话域： sessionMessage
应用域： contextMessage

// 换一个浏览器后，访问ServletB
请求域： null
会话域： null
应用域： contextMessage

// 重启服务器后，访问ServletB
请求域： null
会话域： null
应用域： null
```



+ 请求转发时,请求域可以传递数据`请求域内一般放本次请求业务有关的数据,如:查询到的所有的部门信息`
+ 同一个会话内,不用请求转发,会话域可以传递数据`会话域内一般放本次会话的客户端有关的数据,如:当前客户端登录的用户` 
+ 同一个APP内,不同的客户端,应用域可以传递数据`应用域内一般放本程序应用有关的数据 如:Spring框架的IOC容器`



## 过滤器（Filter）

### 概述

过滤器是JAVAEE技术规范之一,作用目标资源的请求进行过滤的一套技术规范,是Java Web项目中`最为实用的技术之一`

+ Filter接口定义了过滤器的开发规范,所有的过滤器都要实现该接口
+ Filter的工作位置是项目中所有目标资源之前,容器在创建HttpServletRequest和HttpServletResponse对象后,会先调用Filter的doFilter方法
+ Filter的doFilter方法可以控制请求是否继续,如果放行,则请求继续,如果拒绝,则请求到此为止,由过滤器本身做出响应
+ Filter不仅可以对请求做出过滤,也可以在目标资源做出响应前,对响应再次进行处理
+ Filter是GOF中责任链模式的典型案例
+ Filter的常用应用包括但不限于: 登录权限检查,解决网站乱码,过滤敏感字符,日志记录,性能分析... ...

> 生活举例: 公司前台,停车场安保,地铁验票闸机
>
> + 公司前台对来访人员进行审核,如果是游客则拒绝进入公司,如果是客户则放行 . 客户离开时提醒客户不要遗忘物品
> + 停车场保安对来访车辆进行控制,如果没有车位拒绝进入,如果有车位,发放停车卡并放行,车辆离开时收取请车费
> + 地铁验票闸机在人员进入之前检查票,没票拒绝进入,有票验票后放行,人员离开时同样验票

> 过滤器开发中应用的场景
>
> + 日志的记录
> + 性能的分析
> + 乱码的处理
> + 事务的控制
> + 登录的控制
> + 跨域的处理
> + ... ...

> 过滤器工作位置图解
>
> ![image-20240916124445568](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409161244723.png)



### Filter接口API

```java
package jakarta.servlet;
import java.io.IOException;

public interface Filter {
    default public void init(FilterConfig filterConfig) throws ServletException {
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException;
    default public void destroy() {
    }
}
```



| API                                                          | 目标                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| default public void init(FilterConfig filterConfig)          | 初始化方法,由容器调用并传入初始配置信息filterConfig对象      |
| public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) | 过滤方法,核心方法,过滤请求,决定是否放行,响应之前的其他处理等都在该方法中 |
| default public void destroy()                                | 销毁方法,容器在回收过滤器对象之前调用的方法                  |



#### 过滤器使用

目标:开发一个日志记录过滤器

+ 用户请求到达目标资源之前,记录用户的请求资源路径
+ 响应之前记录本次请求目标资源运算的耗时
+ 可以选择将日志记录进入文件,为了方便测试,这里将日志直接在控制台打印



定义一个过滤器类,编写功能代码

```java


/**
 * 日志过滤器 记录请求的历史 将日志打印到控制台
 *
 * 1 实现Filter接口
 * 2 重写过滤方法
 * 3 配置过滤器
 *  web.xml
 *  注释
 */
public class LoggingFilter implements Filter {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 过滤请求的响应方法
     *  1 请求到达目标资源之前，先经过该方法
     *  2 该方法有能力控制请求是否继续向后到达目标资源 可以在该方法内直接向客户端做响应处理
     *  3 请求到达目标资源后，响应之后，还会经过该方法
     *
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1 请求到达目标资源之前的功能代码
        System.out.println("logginFilter before filterChain.doFilter invoked");

        // (选填)打印日志
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        String dateTime = dateFormat.format(new Date());

        long t1 = System.currentTimeMillis();

        // 2 放行代码
        filterChain.doFilter(servletRequest, servletResponse);
        // 3 响应之前 HttpServletResponse 转换为响应报文之前的功能代码
        System.out.println("logginFilter after filterChain.doFilter invoked");

        // (选填)打印日志
        long t2 = System.currentTimeMillis();

        System.out.println(requestURI + " 于 " + dateTime + " 被访问. " + " 请求耗时：" + (t2 - t1) + " ms");
    }
}
```

说明

+ doFilter方法中的请求和响应对象是以父接口的形式声明的,实际传入的实参就是HttpServletRequest和HttpServletResponse子接口级别的,可以安全强转
+ filterChain.doFilter(request,response); 这行代码的功能是放行请求,如果没有这一行代码,则请求到此为止
+ filterChain.doFilter(request,response);在放行时需要传入request和response,意味着请求和响应对象要继续传递给后续的资源,这里没有产生新的request和response对象



需要在 `web.xml` 里定义过滤器作用范围

```xml
<web-app >
    <!-- 配置过滤器-->
    <filter>
        <filter-name>loggingFilter</filter-name>
        <filter-class>com.test.filters.LoggingFilter</filter-class>
    </filter>

    <!-- 配置过滤器的过滤资源规则 路径  servlet-name -->
    <filter-mapping>
        <filter-name>loggingFilter</filter-name>
        <!--
        url-pattern 根据请求的资源路径，对制定的请求进行过滤
            /* 对全部资源
            /a/* 过滤以a开头的资源
            *.html 过滤以html为后缀的资源
            /servlet1 对servlet1的资源进行过滤
        servlet-name 根据请求的servlet的别名，对指定的servlet资源进行过滤
        -->
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```

说明

+ filter-mapping标签中定义了过滤器对那些资源进行过滤
+ 子标签url-pattern通过映射路径确定过滤范围
  + /servletA  精确匹配,表示对servletA资源的请求进行过滤
  + *.html 表示对以.action结尾的路径进行过滤
  + /* 表示对所有资源进行过滤
  + 一个filter-mapping下可以配置多个url-pattern
+ 子标签servlet-name通过servlet别名确定对那些servlet进行过滤
  + 使用该标签确定目标资源的前提是servlet已经起了别名
  + 一个filter-mapping下可以定义多个servlet-name
  + 一个filter-mapping下,servlet-name和url-pattern子标签可以同时存在



定义Servlet作为目标资源

```java
@WebServlet("/servletA")
public class servletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet1 service invoked");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        resp.getWriter().write("servletA message");
    }
}
```



结果

```java
logginFilter before filterChain.doFilter invoked
servlet1 service invoked
logginFilter after filterChain.doFilter invoked
/servletA 于 2024-09-17 21:58:26 被访问.  请求耗时：11 ms
```



> 过滤过程图解
>
> ![image-20240917220102281](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409172201362.png)



#### 过滤器生命周期

| 阶段       | 对应方法                                                     | 执行时机      | 执行次数 |
| ---------- | ------------------------------------------------------------ | ------------- | -------- |
| 创建对象   | 构造器                                                       | web应用启动时 | 1        |
| 初始化方法 | void init(FilterConfig filterConfig)                         | 构造完毕      | 1        |
| 过滤请求   | void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) | 每次请求      | 多次     |
| 销毁       | default void destroy()                                       | web应用关闭时 | 1次      |



测试代码：

```java
package com.test.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/*")
public class LifeCycleFilter implements Filter {
    // 创建对象
    public LifeCycleFilter(){
        System.out.println("LifeCycleFilter constructor method invoked");
    }

    // 初始化，FilterConfig是从web.xml中读取信息
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LifeCycleFilter init method invoked");
        System.out.println(filterConfig.getInitParameter("dateTimePattern"));
    }

    //过滤请求
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("LifeCycleFilter doFilter method invoked");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    //销毁
    @Override
    public void destroy() {
        System.out.println("LifeCycleFilter destory method invoked");
    }
}
```

web.xml 写入param信息以在 init() 读取。

```xml
    <filter>
        <filter-name>LifeCycleFilter</filter-name>
        <filter-class>com.test.filters.LifeCycleFilter</filter-class>
        <init-param>
            <param-name>dateTimePattern</param-name>
            <param-value>yyyy-MM-dd HH:mm:ss</param-value>
        </init-param>
    </filter>

    <filter-mapping>
        <filter-name>LifeCycleFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```



#### 过滤器链的使用

一个web项目中,可以同时定义多个过滤器,多个过滤器对同一个资源进行过滤时,工作位置有先后,整体形成一个工作链,称之为过滤器链。

+ 过滤器链中的过滤器的顺序由filter-mapping顺序决定
+ 每个过滤器过滤的范围不同,针对同一个资源来说,过滤器链中的过滤器个数可能是不同的
+ 如果某个Filter是使用ServletName进行匹配规则的配置，那么这个Filter执行的优先级要更低

![image-20240917235148597](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409172351676.png)



将之前项目的web.xml注释掉，然后创建新的3个Filter。

```xml
<web-app>
    <filter>
        <filter-name>Filter1</filter-name>
        <filter-class>com.test.filters.Filter1</filter-class>
    </filter>

    <filter-mapping>
        <filter-name>Filter1</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <filter>
        <filter-name>Filter2</filter-name>
        <filter-class>com.test.filters.Filter2</filter-class>
    </filter>

    <filter-mapping>
        <filter-name>Filter2</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <filter>
        <filter-name>Filter3</filter-name>
        <filter-class>com.test.filters.Filter3</filter-class>
    </filter>

    <filter-mapping>
        <filter-name>Filter3</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>


</web-app>
```

Filter1.java （Filter2.java，Filter3.java 同理）

```java
package com.test.filters;

import jakarta.servlet.*;
import java.io.IOException;

public class Filter1 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1 请求到达目标资源之前的功能代码
        System.out.println("Filter1 before doFilter invoked");
        // 2 放行代码
        filterChain.doFilter(servletRequest, servletResponse);
        // 3 响应之前 HttpServletResponse 转换为响应报文之前的功能代码
        System.out.println("Filter1 after doFilter invoked");
    }
}
```



启动项目，输入  `http://localhost:8080/servletA` ，结果如下：

```java
Filter1 before doFilter invoked
Filter2 before doFilter invoked
Filter3 before doFilter invoked
servlet1 service invoked
Filter3 after doFilter invoked
Filter2 after doFilter invoked
Filter1 after doFilter invoked
```

> 工作流程图解
>
> ![image-20240918004721640](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409180047719.png)



#### 注解方式配置过滤器

@WebFilter注解的使用

> 源码
> ```java
> package jakarta.servlet.annotation;
> 
> import jakarta.servlet.DispatcherType;
> import java.lang.annotation.Documented;
> import java.lang.annotation.ElementType;
> import java.lang.annotation.Retention;
> import java.lang.annotation.RetentionPolicy;
> import java.lang.annotation.Target;
> 
> @Target({ElementType.TYPE})
> @Retention(RetentionPolicy.RUNTIME)
> @Documented
> public @interface WebFilter {
>     String description() default "";
> 
>     String displayName() default "";
> 
>     WebInitParam[] initParams() default {};
> 
>     String filterName() default "";
> 
>     String smallIcon() default "";
> 
>     String largeIcon() default "";
> 
>     String[] servletNames() default {};
> 
>     String[] value() default {};
> 
>     String[] urlPatterns() default {};
> 
>     DispatcherType[] dispatcherTypes() default {DispatcherType.REQUEST};
> 
>     boolean asyncSupported() default false;
> }
> ```

一个比较完整的Filter的XML配置

```xml
<!--配置filter,并为filter起别名-->
<filter>
    <filter-name>loggingFilter</filter-name>
    <filter-class>com.atguigu.filters.LoggingFilter</filter-class>
    <!--配置filter的初始参数-->
    <init-param>
        <param-name>dateTimePattern</param-name>
        <param-value>yyyy-MM-dd HH:mm:ss</param-value>
    </init-param>
</filter>
<!--为别名对应的filter配置要过滤的目标资源-->
<filter-mapping>
    <filter-name>loggingFilter</filter-name>
    <!--通过映射路径确定过滤资源-->
    <url-pattern>/servletA</url-pattern>
    <!--通过后缀名确定过滤资源-->
    <url-pattern>*.html</url-pattern>
    <!--通过servlet别名确定过滤资源-->
    <servlet-name>servletBName</servlet-name>
</filter-mapping>
```

将xml配置转换成注解方式实现

```java
package com.test.filters;

@WebFilter(
        filterName = "loggingFilter",
        initParams = {@WebInitParam(name="dateTimePattern",value="yyyy-MM-dd HH:mm:ss")},
        urlPatterns = {"/servletA","*.html"},
        servletNames = {"servletBName"}
)
public class LoggingFilter  implements Filter {
    private SimpleDateFormat dateFormat ;

    /*init初始化方法,通过filterConfig获取初始化参数
    * init方法中,可以用于定义一些其他初始化功能代码
    * */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取初始参数
        String dateTimePattern = filterConfig.getInitParameter("dateTimePattern");
        // 初始化成员变量
        dateFormat=new SimpleDateFormat(dateTimePattern);
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 参数父转子
        HttpServletRequest request =(HttpServletRequest)  servletRequest;
        HttpServletResponse  response =(HttpServletResponse)  servletResponse;
        // 拼接日志文本
        String requestURI = request.getRequestURI();
        String time = dateFormat.format(new Date());
        String beforeLogging =requestURI+"在"+time+"被请求了";
        // 打印日志
        System.out.println(beforeLogging);
        // 获取系统时间
        long t1 = System.currentTimeMillis();
        // 放行请求
        filterChain.doFilter(request,response);
        // 获取系统时间
        long t2 = System.currentTimeMillis();
        String afterLogging =requestURI+"在"+time+"的请求耗时:"+(t2-t1)+"毫秒";
        // 打印日志
        System.out.println(afterLogging);

    }
}
```



## 监听器

### 概述

监听器专门用于对域对象对象身上发生的事件或状态改变进行监听和相应处理的对象。

+ 监听器是GOF设计模式中,观察者模式的典型案例
+ 观察者模式: 当被观察的对象发生某些改变时, 观察者自动采取对应的行动的一种设计模式

+ 监听器使用的感受类似JS中的事件,被观察的对象发生某些情况时,自动触发代码的执行
+ 监听器并不监听web项目中的所有组件,仅仅是对三大域对象做相关的事件监听



### 监听器的分类

+ web中定义八个监听器接口作为监听器的规范,这八个接口按照不同的标准可以形成不同的分类

+ **按监听的对象划分**
  + application域监听器 `ServletContextListener`  `ServletContextAttributeListener` 
  + session域监听器 `HttpSessionListener`  `HttpSessionAttributeListener`  `HttpSessionBindingListener`  `HttpSessionActivationListener`  
  + request域监听器 `ServletRequestListener`  `ServletRequestAttributeListener` 
+ **按监听的事件划分**
  + 域对象的创建和销毁监听器 `ServletContextListener`   `HttpSessionListener`   `ServletRequestListener`  
  + 域对象数据增删改事件监听器 `ServletContextAttributeListener`  `HttpSessionAttributeListener`   `ServletRequestAttributeListener` 
  + 其他监听器  `HttpSessionBindingListener`  `HttpSessionActivationListener`  



### 主要的接口

#### application域监听器 (context)

1. ServletContextListener  监听 ServletContext 对象的创建与销毁

   | 方法名                                      | 作用                     |
   | ------------------------------------------- | ------------------------ |
   | contextInitialized(ServletContextEvent sce) | ServletContext创建时调用 |
   | contextDestroyed(ServletContextEvent sce)   | ServletContext销毁时调用 |

   `ServletContextEvent` 对象代表从 ServletContext 对象身上捕获到的事件，通过这个事件对象我们可以获取到ServletContext 对象。

   

2. ServletContextAttributeListener 监听 ServletContext 中属性的添加、移除和修改.。

   | 方法名                                               | 作用                                 |
   | ---------------------------------------------------- | ------------------------------------ |
   | attributeAdded(ServletContextAttributeEvent scab)    | 向ServletContext中添加属性时调用     |
   | attributeRemoved(ServletContextAttributeEvent scab)  | 从ServletContext中移除属性时调用     |
   | attributeReplaced(ServletContextAttributeEvent scab) | 当ServletContext中的属性被修改时调用 |

   ServletContextAttributeEvent  对象代表属性变化事件，它包含的方法如下：

   | 方法名              | 作用                     |
   | ------------------- | ------------------------ |
   | getName()           | 获取修改或添加的属性名   |
   | getValue()          | 获取被修改或添加的属性值 |
   | getServletContext() | 获取ServletContext对象   |




> 测试代码
>
> - 定义监听器
>
>   - 此处可以配置 web.xml
>
>     ```xml
>     <web-app>
>         <listener>
>             <listener-class>com.test.listener.ApplicationListener</listener-class>
>         </listener>
>     </web-app>
>     ```
>
>   - 或直接使用注解
>
>     ```java
>     @WebListener
>     ```
>
>   ```java
>   package com.test.listener;
>   
>   import jakarta.servlet.http.*;
>   import jakarta.servlet.*;
>   import jakarta.servlet.annotation.WebListener;
>   
>   @WebListener
>   public class ApplicationListener implements ServletContextListener, ServletContextAttributeListener{
>       @Override
>       public void contextInitialized(ServletContextEvent sce){
>           ServletContext application = sce.getServletContext();
>           System.out.println(application.hashCode()+"应用域 初始化");
>       }
>   
>       @Override
>       public void contextDestroyed(ServletContextEvent sce){
>           ServletContext application = sce.getServletContext();
>           System.out.println(application.hashCode()+"应用域 销毁");
>   
>       }
>   
>       @Override
>       public void attributeAdded(ServletContextAttributeEvent scae) {
>           ServletContext application = scae.getServletContext();
>           String key = scae.getName();
>           Object value = scae.getValue();
>           System.out.println(application.hashCode()+"应用域 增加： "+key+"："+value);
>       }
>   
>       @Override
>       public void attributeRemoved(ServletContextAttributeEvent scae) {
>           ServletContext application = scae.getServletContext();
>           String key = scae.getName();
>           Object value = scae.getValue();
>           System.out.println(application.hashCode()+"应用域 移除： "+key+"："+value);
>       }
>   
>       @Override
>       public void attributeReplaced(ServletContextAttributeEvent scae) {
>           ServletContext application = scae.getServletContext();
>           // 获取旧的值
>           String key = scae.getName();
>           Object value = scae.getValue();
>           // 获取新的值
>           Object keyNew = application.getAttribute(key);
>   
>           System.out.println(application.hashCode()+"应用域 修改： "+key+"："+value+" 变为 "+keyNew);
>       }
>   }
>   
>   ```
>
> - 定义触发监听器的代码
>
>   - Servlet1.java
>
>     ```java
>     package com.test.servlet;
>     
>     import jakarta.servlet.*;
>     import jakarta.servlet.annotation.WebServlet;
>     import jakarta.servlet.http.*;
>     import java.io.IOException;
>     
>     @WebServlet("/servlet1")
>     public class Servlet1 extends HttpServlet{
>         @Override
>         protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
>             // 向应用域中放入数据
>             ServletContext application = this.getServletContext();
>             application.setAttribute("key-a","value-a");
>         }
>     }
>     ```
>
>   - Servlet2.java
>
>     ```java
>     @WebServlet("/servlet2")
>     public class Servlet2 extends HttpServlet{
>         @Override
>         protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
>             // 修改应用域中放入数据
>             ServletContext application = this.getServletContext();
>             application.setAttribute("key-a","value-aaa");
>         }
>     }
>     ```
>
>   - Servlet3.java
>
>     ```java
>     @WebServlet("/servlet3")
>     public class Servlet3 extends HttpServlet{
>         @Override
>         protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
>             // 删除应用域中放入数据
>             ServletContext application = this.getServletContext();
>             application.removeAttribute("key-a");
>         }
>     }
>     ```
>
> - 测试结果
>
>   ```java
>   648419305应用域 初始化
>   648419305应用域 增加： key-a：value-a
>   648419305应用域 修改： key-a：value-a 变为 value-aaa
>   648419305应用域 移除： key-a：value-aaa
>   648419305应用域 销毁  // 关闭服务器后销毁
>   ```



#### session域监听器

1. HttpSessionListener  监听HttpSession对象的创建与销毁

   | 方法名                                 | 作用                      |
   | -------------------------------------- | ------------------------- |
   | sessionCreated(HttpSessionEvent hse)   | HttpSession对象创建时调用 |
   | sessionDestroyed(HttpSessionEvent hse) | HttpSession对象销毁时调用 |

   `HttpSessionEvent`对象代表从HttpSession对象身上捕获到的事件，通过这个事件对象我们可以获取到触发事件的HttpSession对象。

   

2. HttpSessionAttributeListener 监听 HttpSession中属性的添加、移除和修改

   | 方法名                                        | 作用                              |
   | --------------------------------------------- | --------------------------------- |
   | attributeAdded(HttpSessionBindingEvent se)    | 向HttpSession中添加属性时调用     |
   | attributeRemoved(HttpSessionBindingEvent se)  | 从HttpSession中移除属性时调用     |
   | attributeReplaced(HttpSessionBindingEvent se) | 当HttpSession中的属性被修改时调用 |

   HttpSessionBindingEvent 对象代表属性变化事件，它包含的方法如下：

   | 方法名       | 作用                          |
   | ------------ | ----------------------------- |
   | getName()    | 获取修改或添加的属性名        |
   | getValue()   | 获取被修改或添加的属性值      |
   | getSession() | 获取触发事件的HttpSession对象 |




> 测试代码
>
> - 定义监听器
>
>   - 此处可以配置 web.xml或直接使用注解
>
>
>   ```java
>   package com.test.listener;
>   
>   import jakarta.servlet.http.*;
>   import jakarta.servlet.*;
>   import jakarta.servlet.annotation.WebListener;
>   
>   @WebListener
>   public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener{
>       @Override
>       public void sessionCreated(HttpSessionEvent se) {
>           // 任何一个session域对象的创建都会触发该方法的执行
>           HttpSession session = se.getSession();
>           System.out.println(session.hashCode()+"会话域 初始化");
>       }
>   
>       @Override
>       public void sessionDestroyed(HttpSessionEvent se) {
>           HttpSessionListener.super.sessionDestroyed(se);
>           // 任何一个session域对象的销毁都会触发该方法的执行
>           HttpSession session = se.getSession();
>           System.out.println(session.hashCode()+"会话域 销毁");
>       }
>   
>       @Override
>       public void attributeAdded(HttpSessionBindingEvent se) {
>           // 任何一个session域对象中增加数据都会触发该方法的执行
>           String key = se.getName();
>           Object value = se.getValue();
>           HttpSession session = se.getSession();
>           System.out.println(session.hashCode()+"会话域 增加： "+key+"："+value);
>       }
>   
>       @Override
>       public void attributeRemoved(HttpSessionBindingEvent se) {
>           // 任何一个session域对象中移除数据都会触发该方法的执行
>           String key = se.getName();
>           Object value = se.getValue();
>           HttpSession session = se.getSession();
>           System.out.println(session.hashCode()+"会话域 删除： "+key+"："+value);
>       }
>   
>       @Override
>       public void attributeReplaced(HttpSessionBindingEvent se) {
>           // 任何一个session域对象中修改数据都会触发该方法的执行
>           String key = se.getName();
>           Object value = se.getValue();
>           HttpSession session = se.getSession();
>           Object keyNew = session.getAttribute(key);
>           System.out.println(session.hashCode()+"会话域 修改： "+key+"："+value+" 变为 "+keyNew);
>       }
>   }
>   
>   ```
>
> - 定义触发监听器的代码
>
>   - Servlet1.java
>
>     ```java
>     package com.test.servlet;
>     
>     import jakarta.servlet.*;
>     import jakarta.servlet.annotation.WebServlet;
>     import jakarta.servlet.http.*;
>     import java.io.IOException;
>     
>     @WebServlet("/servlet1")
>     public class Servlet1 extends HttpServlet{
>         @Override
>         protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
>             // 向域中放入数据
>             HttpSession session = req.getSession();
>             session.setAttribute("key-a","value-a");
>         }
>     }
>     
>     ```
>
>   - Servlet2.java
>
>     ```java
>     @WebServlet("/servlet2")
>     public class Servlet2 extends HttpServlet{
>         @Override
>         protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
>             // 修改域中放入数据
>             HttpSession session = req.getSession();
>             session.setAttribute("key-a","value-aaa");
>         }
>     }
>     ```
>
>   - Servlet3.java
>
>     ```java
>     @WebServlet("/servlet3")
>     public class Servlet3 extends HttpServlet{
>         @Override
>         protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
>             // 删除域中放入数据
>             HttpSession session = req.getSession();
>             session.removeAttribute("key-a");
>         }
>     }
>     ```
>
> - 测试结果
>
>   ```java
>   2122849544会话域 初始化
>   2122849544会话域 增加： key-a：value-a
>   2122849544会话域 修改： key-a：value-a 变为 value-aaa
>   2122849544会话域 删除： key-a：value-aaa
>   2122849544会话域 销毁  // 设置会话自动销毁规则，或者关闭服务器
>   ```





#### request域监听器

1. ServletRequestListener 监听ServletRequest对象的创建与销毁

   | 方法名                                      | 作用                         |
   | ------------------------------------------- | ---------------------------- |
   | requestInitialized(ServletRequestEvent sre) | ServletRequest对象创建时调用 |
   | requestDestroyed(ServletRequestEvent sre)   | ServletRequest对象销毁时调用 |

   `ServletRequestEvent`对象代表从HttpServletRequest对象身上捕获到的事件，通过这个事件对象我们可以获取到触发事件的HttpServletRequest对象。另外还有一个方法可以获取到当前Web应用的ServletContext对象。

   

2. ServletRequestAttributeListener 监听ServletRequest中属性的添加、移除和修改

   | 方法名                                               | 作用                                 |
   | ---------------------------------------------------- | ------------------------------------ |
   | attributeAdded(ServletRequestAttributeEvent srae)    | 向ServletRequest中添加属性时调用     |
   | attributeRemoved(ServletRequestAttributeEvent srae)  | 从ServletRequest中移除属性时调用     |
   | attributeReplaced(ServletRequestAttributeEvent srae) | 当ServletRequest中的属性被修改时调用 |

   ServletRequestAttributeEvent对象代表属性变化事件，它包含的方法如下：

   | 方法名               | 作用                             |
   | -------------------- | -------------------------------- |
   | getName()            | 获取修改或添加的属性名           |
   | getValue()           | 获取被修改或添加的属性值         |
   | getServletRequest () | 获取触发事件的ServletRequest对象 |




> 测试代码
>
> - 定义监听器
>
>   - 此处可以配置 web.xml 或直接使用注解
>
>
>   ```java
>   package com.test.listener;
>   
>   import jakarta.servlet.*;
>   import jakarta.servlet.http.HttpSession;
>   
>   public class RequestListener implements ServletRequestListener, ServletRequestAttributeListener {
>       @Override
>       public void requestInitialized(ServletRequestEvent sre) {
>           ServletRequest request = sre.getServletRequest();
>           System.out.println(request.hashCode()+"请求域 初始化");
>       }
>   
>       @Override
>       public void requestDestroyed(ServletRequestEvent sre) {
>           ServletRequest request = sre.getServletRequest();
>           System.out.println(request.hashCode()+"请求域 销毁");
>       }
>   
>       @Override
>       public void attributeAdded(ServletRequestAttributeEvent srae) {
>           String key = srae.getName();
>           Object value = srae.getValue();
>           ServletRequest request = srae.getServletRequest();
>           System.out.println(request.hashCode()+"请求域 增加： "+key+"："+value);
>       }
>   
>       @Override
>       public void attributeRemoved(ServletRequestAttributeEvent srae) {
>           String key = srae.getName();
>           Object value = srae.getValue();
>           ServletRequest request = srae.getServletRequest();
>           System.out.println(request.hashCode()+"请求域 删除： "+key+"："+value);
>       }
>   
>       @Override
>       public void attributeReplaced(ServletRequestAttributeEvent srae) {
>           String key = srae.getName();
>           Object value = srae.getValue();
>           ServletRequest request = srae.getServletRequest();
>           Object keyNew = request.getAttribute(key);
>           System.out.println(request.hashCode()+"请求域 修改： "+key+"："+value+" 变为 "+keyNew);
>       }
>   }
>   
>   ```
>
> - 定义触发监听器的代码
>
>   - Servlet1.java
>
>     ```java
>     package com.test.servlet;
>     
>     import jakarta.servlet.*;
>     import jakarta.servlet.annotation.WebServlet;
>     import jakarta.servlet.http.*;
>     import java.io.IOException;
>     
>     @WebServlet("/servlet1")
>     public class Servlet1 extends HttpServlet{
>         @Override
>         protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
>             // 向域中放入数据
>             req.setAttribute("key-a","value-a");
>     
>             // 请求转发
>             req.getRequestDispatcher("/servlet2").forward(req,resp);
>         }
>     }
>     
>     ```
>
>   - Servlet2.java
>
>     ```java
>     @WebServlet("/servlet2")
>     public class Servlet2 extends HttpServlet{
>         @Override
>         protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
>             // 修改域中放入数据
>             req.setAttribute("key-a","value-aaa");
>     
>             // 请求转发
>             req.getRequestDispatcher("/servlet3").forward(req,resp);
>         }
>     }
>     ```
>
>   - Servlet3.java
>
>     ```java
>     @WebServlet("/servlet3")
>     public class Servlet3 extends HttpServlet{
>         @Override
>         protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
>             // 删除域中放入数据
>             req.removeAttribute("key-a");
>         }
>     }
>     ```
>
> - 测试结果
>
>   ```java
>   1182227595请求域 初始化
>   1182227595请求域 修改： org.apache.catalina.ASYNC_SUPPORTED：true 变为 false
>   1182227595请求域 增加： key-a：value-a
>   1182227595请求域 修改： key-a：value-a 变为 value-aaa
>   1182227595请求域 删除： key-a：value-aaa
>   1182227595请求域 销毁 //请求结束后立刻销毁
>   ```



### session域的两个特殊监听器

#### session绑定监听器

1. HttpSessionBindingListener 监听当前监听器对象在Session域中的增加与移除

   | 方法名                                      | 作用                              |
   | ------------------------------------------- | --------------------------------- |
   | valueBound(HttpSessionBindingEvent event)   | 该类的实例被放到Session域中时调用 |
   | valueUnbound(HttpSessionBindingEvent event) | 该类的实例从Session中移除时调用   |

   `HttpSessionBindingEvent`对象代表属性变化事件，它包含的方法如下：

   | 方法名       | 作用                          |
   | ------------ | ----------------------------- |
   | getName()    | 获取当前事件涉及的属性名      |
   | getValue()   | 获取当前事件涉及的属性值      |
   | getSession() | 获取触发事件的HttpSession对象 |




> 测试代码
>
> - 定义监听器
>
>   - 此处可以配置 web.xml 或直接使用注解
>
>   ```java
>   package com.test.listener;
>   
>   import jakarta.servlet.http.HttpSession;
>   import jakarta.servlet.http.HttpSessionBindingEvent;
>   import jakarta.servlet.http.HttpSessionBindingListener;
>   
>   public class SessionBindingListener implements HttpSessionBindingListener {
>       @Override
>       public void valueBound(HttpSessionBindingEvent event) {
>           // 当前监听器实例放入某个session中作为数据  绑定
>           HttpSession session = event.getSession();
>           String key = event.getName();
>           System.out.println("SessionBindingListener"+this.hashCode()+" 以名称 "+ key + " 绑入 "+session.hashCode());
>       }
>   
>       @Override
>       public void valueUnbound(HttpSessionBindingEvent event) {
>           // 当前监听器实例从某个session中移除  解绑定
>           HttpSession session = event.getSession();
>           String key = event.getName();
>           System.out.println("SessionBindingListener"+this.hashCode()+" 以名称 "+ key + " 解绑 "+session.hashCode());
>       }
>   }
>   ```
>
> - 定义触发监听器的代码
>
>   - ServletBind.java
>
>     ```java
>     package com.test.servlet;
>     
>     import com.test.listener.SessionBindingListener;
>     import jakarta.servlet.ServletException;
>     import jakarta.servlet.annotation.WebServlet;
>     import jakarta.servlet.http.HttpServlet;
>     import jakarta.servlet.http.HttpServletRequest;
>     import jakarta.servlet.http.HttpServletResponse;
>     import jakarta.servlet.http.HttpSession;
>     
>     import java.io.IOException;
>     
>     @WebServlet("/servletBind")
>     public class ServletBind extends HttpServlet {
>         @Override
>         protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
>             HttpSession session = req.getSession();
>             SessionBindingListener sb1 = new SessionBindingListener();
>             session.setAttribute("sb1", sb1);
>         }
>     }
>     ```
>
>   - ServletUnbind.java
>
>     ```java
>     package com.test.servlet;
>             
>     import com.test.listener.SessionBindingListener;
>     import jakarta.servlet.ServletException;
>     import jakarta.servlet.annotation.WebServlet;
>     import jakarta.servlet.http.HttpServlet;
>     import jakarta.servlet.http.HttpServletRequest;
>     import jakarta.servlet.http.HttpServletResponse;
>     import jakarta.servlet.http.HttpSession;
>             
>     import java.io.IOException;
>             
>     @WebServlet("/servletUnbind")
>     public class ServletUnbind extends HttpServlet {
>         @Override
>         protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
>             HttpSession session = req.getSession();
>             session.removeAttribute("sb1");
>         }
>     }
>             
>     ```
>
> - 测试结果
>
>   ```java
>   237401186请求域 初始化
>   237401186请求域 修改： org.apache.catalina.ASYNC_SUPPORTED：true 变为 false
>   SessionBindingListener14785494 以名称 sb1 绑入 1757272836
>   SessionBindingListener415493262 以名称 sb1 解绑 1757272836
>   1757272836会话域 修改： sb1：com.test.listener.SessionBindingListener@e19bd6 变为 com.test.listener.SessionBindingListener@e19bd6
>   237401186请求域 销毁
>               
>   1625435996请求域 初始化
>   1625435996请求域 修改： org.apache.catalina.ASYNC_SUPPORTED：true 变为 false
>   SessionBindingListener14785494 以名称 sb1 解绑 1757272836
>   1757272836会话域 删除： sb1：com.test.listener.SessionBindingListener@e19bd6
>   1625435996请求域 销毁
>   ```



#### 钝化活化监听器

**钝化活化概述**

+ session对象在服务端是以对象的形式存储于内存的,session过多,服务器的内存也是吃不消的
+ 而且一旦服务器发生重启,所有的session对象都将被清除,也就意味着session中存储的不同客户端的登录状态丢失
+ 为了分摊内存 压力并且为了保证session重启不丢失,我们可以设置将session进行钝化处理
+ 在关闭服务器前或者到达了设定时间时,对session进行序列化到磁盘,这种情况叫做session的钝化
+ 在服务器启动后或者再次获取某个session时,将磁盘上的session进行反序列化到内存,这种情况叫做session的活化



**配置钝化活化**

在web目录下，添加 META-INF，并在其中创建Context.xml

```powershell
|— web
|	|— META-INF
|		|— Context.xml
```

文件中配置钝化

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context>
    <Manager className="org.apache.catalina.session.PersistentManager" maxIdleSwap="1">
        <!-- 设置钝化文件的磁盘路径 -->
        <Store className="org.apache.catalina.session.FileStore" directory="d:\mysession"></Store>
    </Manager>
</Context>
```



**测试钝化活化**

请求servletA，获得session，并存入数据，然后重启服务器

``` java
@WebServlet("/servletA")
public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        // 添加数据
        session.setAttribute("k1","v1");
    }
}
```

请求servletB，获取session，获取重启前存入的数据

``` java
@WebServlet("/servletB")
public class ServletB extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object v1 = session.getAttribute("k1");
        System.out.println(v1);
    }
}
```



**监听钝化活化**

HttpSessionActivationListener  监听某个对象在Session中的序列化与反序列化。

| 方法名                                    | 作用                                  |
| ----------------------------------------- | ------------------------------------- |
| sessionWillPassivate(HttpSessionEvent se) | 该类实例和Session一起钝化到硬盘时调用 |
| sessionDidActivate(HttpSessionEvent se)   | 该类实例和Session一起活化到内存时调用 |

+ `HttpSessionEvent`对象代表事件对象，通过getSession()方法获取事件涉及的HttpSession对象。



> 定义监听器
>
> ```java
> package com.test.listener;
> 
> import jakarta.servlet.annotation.WebListener;
> import jakarta.servlet.http.HttpSession;
> import jakarta.servlet.http.HttpSessionActivationListener;
> import jakarta.servlet.http.HttpSessionEvent;
> 
> import java.io.Serializable;
> 
> @WebListener
> public class ActivationListener implements HttpSessionActivationListener, Serializable {
>     //  监听钝化
>     @Override
>     public void sessionWillPassivate(HttpSessionEvent se) {
>         // session 对象即将进行钝化之前执行
>         HttpSession session = se.getSession();
>         System.out.println("session with JSESSIONID "+ session.getId()+" will passivate");
>     }
> 
>     //  监听活化
>     @Override
>     public void sessionDidActivate(HttpSessionEvent se) {
>         // session 对象活化完毕之后执行
>         HttpSession session = se.getSession();
>         System.out.println("session with JSESSIONID "+ session.getId()+" did activate");
>     }
> }
> 
> ```
>
> 
>
> 定义触发监听器的代码
>
> ```java
> package com.test.servlet;
> 
> import com.test.listener.ActivationListener;
> import jakarta.servlet.ServletException;
> import jakarta.servlet.annotation.WebServlet;
> import jakarta.servlet.http.HttpServlet;
> import jakarta.servlet.http.HttpServletRequest;
> import jakarta.servlet.http.HttpServletResponse;
> import jakarta.servlet.http.HttpSession;
> 
> import java.io.IOException;
> 
> @WebServlet("/servletAct")
> public class servletAct extends HttpServlet {
>     @Override
>     protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
>         HttpSession session = req.getSession();
>         // 添加数据
>         session.setAttribute("k1","v1");
>         // 添加钝化活化监听器
>         session.setAttribute("activationListener",new ActivationListener());
>     }
> }
> ```



## 实操项目开发二（日程管理系统实践）

### 过滤器控制登录校验

需求说明:未登录状态下不允许访问showShedule.html和SysScheduleController相关增删改处理,重定向到login.html,登录成功后可以自由访问

![无标题-2024-06-22-2219](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409190905377.png)



修改 SysUserController.java

```java
	// 检测用户名是否存在
        if (sysUser==null) {
            resp.sendRedirect("/login.html?status_login=wrongname");
        }
        // 检测密码是否符合
        else if( ! MD5Util.encrypt(password).equals(sysUser.getUserPwd()) ){
            resp.sendRedirect("/login.html?status_login=wrongpswd");
        }
        // 登录成功
        else {
            // 登录成功后，将登录的用户信息放入session
            HttpSession session = req.getSession();
            session.setAttribute("sysUser", sysUser);
            
            // 跳转
            resp.sendRedirect("/showSchedule.html");
        }
```

登录过滤器 LoginFilter.java

```java
package test.schdule.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import test.schdule.pojo.SysUser;

import java.io.IOException;

@WebFilter( urlPatterns = {"/schedule/*","/showSchedule.html"} )
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取session域对象
        HttpSession session = request.getSession();

        // 从session域中获得登录的用户对象
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");

        // 判断用户对象是否为空，没登陆则跳回到login.html，登录则放行
        if (sysUser == null) {
            response.sendRedirect("login.html");
        }else{
            filterChain.doFilter(request, response);
        }
    }
}
```



## Ajax 技术

### 发送请求的方式、标签

1. 方式
   1. 浏览器输入地址
2. 标签
   1. 自动的请求
      - html 的 head 标签中，scipt/link
      - img 标签
   2. 手动的请求，往往需要在新的页面上获得响应信息
      - a 标签
      - form 表单标签
   3. 运行 js 代码产生请求
      - 通过js触发响应后，根据js代码动态处理是否要跳转页面还是要将响应的信息通过DOM编程显示在dom树中。



### ajax简述

+ AJAX = Asynchronous JavaScript and XML（异步的 JavaScript 和 XML）。

+ AJAX 不是新的编程语言，而是一种使用现有标准的新方法。

+ AJAX 最大的优点是在不重新加载整个页面的情况下，可以与服务器交换数据并更新部分网页内容。

+ AJAX 不需要任何浏览器插件，但需要用户允许 JavaScript 在浏览器上执行。

+ XMLHttpRequest 只是实现 Ajax 的一种方式。



![无标题-2024-06-22-2219](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202409190940254.png)

**Ajax 工作原理**

![](./../../Javaweb%25E5%25B0%259A%25E7%25A1%2585%25E8%25B0%25B7%25E7%25AC%2594%25E8%25AE%25B0/images/image_bjXPJoLb6a-1690508517199.png)

+ 简单来说,我们之前发的请求通过类似  form表单标签,a标签 这种方式,现在通过 运行js代码动态决定什么时候发送什么样的请求
+ 通过运行JS代码发送的请求浏览器可以不用跳转页面 ,我们可以在JS代码中决定是否要跳转页面
+ 通过运行JS代码发送的请求,接收到返回结果后,我们可以将结果通过dom编程渲染到页面的某些元素上,实现局部更新



**Ajax 实现方式**

1. 原生 Js 的实现方式：代码繁琐，涉及到回调函数问题
2. 第三方封装好的工具：Jquery
3. 框架：VUE，axios



### 原生javascript方式进行ajax(了解)

index.html

```html
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script>
        function getMessage(){
            // 实例化一个xmlHttpRequest对象的回调函数
            let request = new XMLHttpRequest();

            // 设置xmlHttpRequest对象的回调函数
            // request.readState 四个状态 1 2 3 4
            // request.status 响应状态，响应状态码
            request.onreadystatechange=function (){
                // 接收响应结果，处理结果
                if(request.readyState===4 && request.status === 200){
                    let myDiv = document.getElementById("sayHello");
                    myDiv.innerHTML=request.responseText;

                }
            }

            // 获取input框的值
            let inputValue = document.getElementById("myInput").value;

            // 设置请求方式和路径
            request.open("GET","/hello?username="+inputValue);

            // 发送请求
            request.send();
        }
    </script>
</head>
<body>
    <input type="text" id="myInput" placeholder="请输入你的用户名">
    <button onclick="getMessage()">Ajax测试按钮(跟你说Hello）</button>
    <div id="sayHello"></div>
</body>
</html>
```

响应处理 HelloServlet.java

```java
package com.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收参数
        String username = req.getParameter("username");
        System.out.println(username);
        //做出响应
        resp.getWriter().write("Hello，"+username);
    }
}
```



## 实操项目开发三（日程管理系统实践）

### 注册提交前校验用户名是否占用功能

- **注意**

  - 响应乱码

  - 响应信息格式与处理方式规范

    - 后端响应回来的信息应有一个统一的格式，前后端共同遵守响应一个JSON串

    - JSON 串示例

      {

      ​	"code" : "100/200/300/400/401"		业务状态码，表示本次请求是否成功。 若失败，则写明失败原因。注意非响应报文中的响应码。

      ​	"message" : ""									业务状态码的补充说明/描述。

      ​	"data" : {}											本次响应的数据。

      }

  - 校验不通过阻止表单提交问题 （在回调函数中return无法影响外部函数的返回值，可使用全局变量处理，未来在VUE的axios结合promise处理）

  - 导入 Jackson 处理 json



修改对应 regist.html 页面代码的 checkUsername 函数。

```js
// 检验用户名格式是否合法的函数
function checkUsername(){
    // 定义正则表示字符串的规则:5到10位数字
    var usernameReg= /^[a-zA-Z0-9]{5,10}$/
    // 获得用户在页面上输入的信息
    var usernameInput =document.getElementById("usernameInput")
    var username = usernameInput.value
    // 获得格式提示的框
    var usernameMsg =document.getElementById("usernameMsg")
    // 格式有误时,返回false,在页面上提示
    if(!usernameReg.test(username)){ 
        usernameMsg.innerText="用户名格式有误"
        return false
    }
    usernameMsg.innerText="格式正确"

    // 格式校验后继续检测用户名是否被占用
    let request = new XMLHttpRequest()
    // 设置回调函数，设置响应回来的信息如何处理
    request.onreadystatechange=function(){
        if(request.readyState===4 && request.status===200){
            // 后端的响应的JSON字符串转换为前端的对象
            let response =JSON.parse(request.responseText)

            if(response.code !== 200){
                usernameMsg.innerText="用户名已存在"
            }else{
                usernameMsg.innerText="用户名可用"
            }
        }
    }
    // 设置请求方式和请求的资源路径
    request.open("GET","/user/checkUsernameUsed?username="+username);
    // 发送请求
    request.send();

    return true;
}
```



创建 ResultCodeEnum.java 和 Result.java  作为公共的JSON数据响应格式类。

Result.java

```java
package test.schedule.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result<T> {
    // 返回码
    private Integer code;
    // 返回消息
    private String message;
    // 返回数据
    private T data;
    // 返回数据
    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<T>();
        if (data != null)
            result.setData(data);
        return result;
    }
    public static <T> Result<T> build(T body, Integer code, String message) {
        Result<T> result = build(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }
    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }
}
```

ResultCodeEnum.java

```java
package test.schedule.common;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(200,"success"),
    USERNAME_ERROR(501,"usernameError"),
    PASSWORD_ERROR(503,"passwordError"),
    NOTLOGIN(504,"notLogin"),
    USERNAME_EXIST(505,"usernameExist");

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
```



在 SysUserController.java 中添加

```java
    /**
     * 注册时，接收要注册的用户名，校验用户名是否被占用的业务接口
     */
    protected void checkUsernameUsed(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String username = req.getParameter("username");

        // 调用服务层的方法，查看是否存在对应用户
        SysUser sysUser = userService.findByUsername(username);

        // 检测用户名是否存在
        Result<Object> result = Result.build(null, ResultCodeEnum.USERNAME_EXIST);
        if (sysUser==null) {
            result = Result.build(null, ResultCodeEnum.SUCCESS);
        }

        WebUtil.writeJson(resp,result);
    }
```

创建通用 WebUtil.java 降低代码复用

```java
package test.schedule.util;


import test.schedule.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class WebUtil {
    private static ObjectMapper objectMapper;
    // 初始化objectMapper
    static{
        objectMapper=new ObjectMapper();
        // 设置JSON和Object转换时的时间日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
    // 从请求中获取JSON串并转换为Object
    public static <T> T readJson(HttpServletRequest request,Class<T> clazz){
        T t =null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            StringBuffer buffer =new StringBuffer();
            String line =null;
            while((line = reader.readLine())!= null){
                buffer.append(line);
            }

            t= objectMapper.readValue(buffer.toString(),clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return t;
    }
    // 将Result对象转换成JSON串并放入响应对象
    public static void writeJson(HttpServletResponse response, Result result){
        response.setContentType("application/json;charset=UTF-8");
        try {
            String json = objectMapper.writeValueAsString(result);
            response.getWriter().write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```



## JSP 技术

**JSP（JavaServer Pages）** 是一种用于创建动态网页的技术，由 **Sun Microsystems**（后来并入 Oracle）开发，基于 Java 编程语言。JSP 是一种服务器端技术，允许嵌入 Java 代码到 HTML 页面中，以便生成动态内容。JSP 通常用于开发基于 Java 的 web 应用程序，特别是在需要生成动态内容的情况下，例如用户提交表单、查询数据库、显示动态数据等。

### JSP 的基本概念

1. **JSP 文件的后缀是 `.jsp`**，其内容主要由 HTML（或 XML）标记组成，并且可以包含 Java 代码。与传统的静态 HTML 页面不同，JSP 页面能够根据用户请求生成动态内容。
2. **JSP 是 Servlet 技术的扩展**：JSP 最终会被转换为 Servlet（Java 编写的服务端程序），并由服务器执行。因此，JSP 本质上是 Servlet 的简化形式。它通过结合 HTML 和 Java 代码来减少编写复杂的 Java 代码，同时又能生成动态内容。
3. **MVC 模式中的 JSP**：在 Web 开发中，JSP 通常用于 **视图层（View Layer）**，即负责显示数据给用户的部分。在经典的 MVC（Model-View-Controller）架构中，控制器（Controller）负责处理业务逻辑，模型（Model）管理数据，而 JSP 作为视图层用于显示数据。

### JSP 工作原理

JSP 的工作流程如下：

1. **客户端请求**：客户端通过浏览器请求一个 JSP 页面。
2. **JSP 转换成 Servlet**：Web 容器（如 Apache Tomcat）将 JSP 文件转换成一个 Java Servlet。这个 Servlet 包含了嵌入在 JSP 中的 Java 代码以及生成 HTML 页面所需的内容。
3. **编译 Servlet**：JSP 文件被转换成 Servlet 后，Web 容器将这个 Servlet 编译为字节码并加载到内存中。
4. **执行 Servlet**：编译后的 Servlet 运行，处理客户端请求，执行 JSP 中的 Java 代码，生成动态内容（如从数据库获取数据、处理用户输入等），并生成一个 HTML 页面作为响应。
5. **返回响应**：生成的 HTML 页面发送回客户端，用户通过浏览器查看。



### JSP 语法和标签

JSP 中的内容主要包括 HTML 标签和嵌入的 Java 代码。JSP 使用特定的语法和标签来区分 Java 代码和 HTML。

1. **脚本标签**：允许在 JSP 中嵌入 Java 代码。

   > 第一种：<% %>： java 脚本段，可以定义局部变量、编写语句 
   >
   > 第二种：<%! %>：声明，可以定义全局（成员）变量、方法、类 
   >
   > 第三种：<%= %>：表达式，数据一个变量或具体内容

   - <% ... %>：在其中可以写入 Java 代码。

     ```jsp
     <%
       String name = "Alice";
     %>
     ```

   - <%= ... %>：输出表达式的结果到客户端（HTML 页面）。

     ```jsp
     <%= name %>  <!-- 直接在 HTML 中输出变量 name 的值 -->
     ```

2. **指令标签**：用于设置 JSP 页面的属性或配置信息。

   指令标签是JSP页面上的一种特殊标签，JSP指令可以用来设置整个JSP页面相关的属性，如网页的编码方式、网页使用的脚本语言、导包等。

   常见的指令包括：

   1. page：用于定义页面级别的属性，如导入类、缓冲区大小等。

      ```jsp
      <%@ 
          page language="java" // 设置jsp中的脚本语言
              contentType="text/html; charset=UTF-8"  // 浏览器解析所需信息
              import="java.util.*" // 导包
              errorPage="error.jsp" // 出错时跳转的页面
      %>
      ```

   2. include：在 JSP 页面中包含其他文件。

      ```jsp
      <body>
          <%@ include file="header.jsp" %> <!-- 网页头部 -->
          <div style="height: 500px">
              网页内容
          </div>
          <%@ include file="foot.jsp" %> <!-- 网页底部 -->
      </body>
      ```

3. **JSP 标准标签库（JSTL）**：用于简化 JSP 中的逻辑处理，提供了一组标准标签，避免在 JSP 中直接编写 Java 代码。常见的 JSTL 标签包括：

   - 条件语句标签：

     ```jsp
     <c:if test="${user != null}">
       Welcome, ${user.name}!
     </c:if>
     ```

   - 循环标签：

     ```jsp
     <c:forEach var="item" items="${itemsList}">
       <p>${item.name}</p>
     </c:forEach>
     ```

4. **注释**：

   - JSP 注释：不会被发送到客户端的注释。

     ```jsp
     <%-- 这是一条 JSP 注释 --%>
     ```



### 九大内置对象

内置对象： 因为JSP的本质是Servlet，在JSP文件经过转译之后，生成JAVA代码，在运行时，JSP给我们准备好了九个可以直接使用而不用我们自己去new的对象,这九个对象我们称之为内置对象. 

内置对象完全由JSP自行去维护,我们直接使用即可。

| 八个内置对象 | 类型                                   |
| ------------ | -------------------------------------- |
| config       | javax.servlet.ServletConfig            |
| page         | javax.servlet.jsp.JspPage              |
| exception    | javax.servlet.jsp.JspException         |
| request      | javax.servlet.http.HttpServletRequest  |
| response     | javax.servlet.http.HttpServletResponse |
| session      | javax.servlet.http.HttpSession         |
| application  | javax.servlet.ServletContext           |
| out          | javax.servlet.jsp.JspWriter            |
| pageContext  | javax.servlet.jsp.PageContext          |

因为这里名字已经起好了，所以不能用其它名字



在 jsp 页面中加入 `isErrorPage="true"` 以后，生成的转译文件中才会有exception对象，才可以使用 exception对象。

error.jsp如下：

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        现在服务器繁忙，请联系管理员。
        <%
        	String message = exception.getMessage();
        %>
        当前页面的错误是：<%=message%>
    </body>
</html>
```

需要开启 exception 的页面（发生错误后跳转）

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
```



**另一种划分方式**

| 四个域对象  | 域            | 使用范围       |
| ----------- | ------------- | -------------- |
| pageContext | page域        | 当前页面内可用 |
| request     | reqeust域     | 一次请求       |
| session     | session域     | 一次会话       |
| application | application域 | 整个项目运行   |

| 响应对象 | 说明     |
| -------- | -------- |
| response | 响应对象 |

| 输出流对象 | 说明       |
| ---------- | ---------- |
| out        | 打印流对象 |

| 其他三个对象  | 说明                                                         |
| ------------- | ------------------------------------------------------------ |
| servletConfig | 由于JSP本身也是一个Servlet,所以容器也会给我们准备一个ServletConfig |
| page          | 就是JSP转换的Servlet的对象，也就是当前JSP对象本身            |
| exception     | 异常对象,在错误提示页上使用,当isErrorpage=true 才具有该对象  |



### EL表达式的使用

在jsp页面中使用EL表达式来获取域对象中的数据，简化了在jsp页面中使用<%%>java脚本获取 域对象数据的方式

语法结构非常简单：

```jsp
${expression}
```



域对象的概念在 JSP 中一共有四个：pageContext, request, session, application；
范围依次是， 本页面，一次请求， 一次会话，整个应用程序。

当需要指定从某个特定的域对象中查找数据时可以使用四个域对象对应的空间对象，分别是： pageScope, requestScope, sessionScope, applicationScope。

使用EL表达式获取域数据的时候，如果没有指明作用域，那会按照作用域由小到大的顺序去找，直到 找到为止： pageContext ---> request ---> session ---> application

当域对象全找完了还未找到则返回空字符串 `""`。

> 示例
>
> ```jsp
> <%@ page contentType="text/html;charset=UTF-8" language="java" %>
> <html>
>     <head>
>         <title>Title</title>
>     </head>
>     <body>
>         <%
>         pageContext.setAttribute("uname","lulu");
>         request.setAttribute("uname","lili");
>         session.setAttribute("uname","feifei");
>         application.setAttribute("uname","mingming");
>         %>
>         
>         <%--之前的写法：如果数据没有取出，显示的效果为：null--%>
>         <%=pageContext.getAttribute("uname3")%>
>         
>         <%--el表达式的写法：如果数据没有取出，那么展示的就是空白的效果，实际取出的就是空字符串--%>
>         ${uname}
>         ${uname3}
>         ${sessionScope.uname}
>     </body>
> </html>
> ```



### EL中的运算符

算术运算、等值判断、关系运算符、逻辑运算符 与 Java 无异

empty运算符

```jsp
<%--
empty
判断域对象是否为空。为空，返回true；不为空返回false；注意：字符串对象、集合对象、普通
对象的empty测试
${empty 限域变量名 }
判断对象是否不为空。
${!empty 限域变量名 }
--%>
${empty uname}
${empty list}
${empty map}
${empty user}
```



### JSTL

JSTL（JavaServer Pages Standard Tag Library，JSP标准标签库)是一个JSP标签集合，它封装了JSP应用的通用核心功能。

JSTL支持通用的、结构化的任务，比如迭代，条件判断，XML文档操作，国际化标签，SQL标签。 除了这些，它还提供了一个框架来使用集成JSTL的自定义标签。



之前的学习中，我们的操作过程：

1. `Servlet` 进行请求处理
2. 使用 `JSP` 将请求处理的结果展示
3. 在 `jsp 页面`使用 `EL表达式` 获取并输出域对象中数据

但是如果要循环输出一个 list 集合的数据，还得在 jsp页面 使用 <%%>java小脚本的形式写java代码来完成，比较麻烦！



#### JSTL的作用

简而言之，JSTL 可以替换在 Jsp 中声明的 Java逻辑代码。

EL表达式只是取出域对象中数据，而 JSTL 是对 数据进行各种处理。所以，也可以解释为 JSTL是辅助EL的使用。



#### JSTL 标签库的内容

1. 核心标签库 
2. SQL标签库 
3. 函数标签库 
4. 格式化标签库 
5. XML标签库 

现在只讨论 JSTL 中最重要的标签：迭代集合、格式化数字和日期等标签。 

- 核心标签库 ： http://java.sun.com/jsp/jstl/core 
  	包含 Web 应用的常见工作
  	比如：循环、表达式赋值、基本输入输出等。
- 格式化标签库 ： http://java.sun.com/jsp/jstl/fmt
  	用来格式化显示数据的工作
  	比如：对不同区域的日期格式化等。



#### JSTL的使用

- 使用步骤 
  1. 在项目中导入 JSTL 的 .jar 包 
     - jstl.jar
     - standard.jar
     - 下载：[Index of /dist/jakarta/taglibs/standard/binaries (apache.org)](https://archive.apache.org/dist/jakarta/taglibs/standard/binaries/)
     - 将 jakarta-taglibs-standard-1.1.2/lib/ 下的 两个 jar 文件：standard.jar 和 jstl.jar 文件拷贝到项目的指定目录下。
  2. 在 jsp 页面中通过 taglib 指令引入 jstl标签库
  3. 使用 jstl 标签完成开发

为了在 JSP 页面使用 JSTL 类库，必须以下列格式使用 taglib 指令标签：

```jsp
<%@taglib uri="" prefix="" %>
```

uri：你需要使用的jstl的库的地址 ，你想用哪个库你就引入哪个库。 
prefix：用来自定义标签的前缀，有点像之前学习的表的别名，通过别名可以操作表中字段，这里通 过前缀去操作库中的标签。

> 示例：引入核心库
>
> ```jsp
> <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
> ```
>
> 前缀可以是任意内容，遵循规范可以使团队中由不同人员编写的代码更加相似；所以，建议使用事先 设计好的前缀。比如使用核心库，前缀一般使用c，如果使用格式化库，前缀一般是fmt。



##### 条件动作标签

条件动作指令用于处理页面的输出结果依赖于某些输入值的情况。

在 Java 中是利用 if、 if…else 和 switch 语句来进行处理的。

在 JSTL 中也有 4 个标签可以执行条件式动作指令：if、 choose、when 和 otherwise。



###### if 标签

if 标签先对某个条件进行测试，如果该条件运算结果为 true, 则处理它的主体内容。

测试结果保存在 一个 Boolean 对象中，并创建一个限域变量来引用 Boolean 对象。

可以利用 var 属性设置限域变量 名，利用 scope 属性来指定其作用范围。

```jsp
<c:if test="<boolean>" var="<string>" scope="<string>">
...
</c:if>
```

| if 标签的属性 | 描述                                                         | 是否必要 | 默认值 |
| ------------- | ------------------------------------------------------------ | -------- | ------ |
| test          | 条件                                                         | 是       | 无     |
| var           | 用于存储条件结果的变量(限域变量名)                           | 否       | 无     |
| scope         | var属性的作用域，可取值：page、request、session、application | 否       | page   |

> 测试示例
>
> ```jsp
> <%@ page contentType="text/html;charset=UTF-8" language="java" %>
> <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
> <html>
>     <head>
>         <title>Title</title>
>     </head>
>     <body>
>         <%
>         // 在request作用域中存储数据：
>         // 存储int类型数据，学生的成绩：
>         request.setAttribute("score",95);
>         %>
>         
>         <%--如果成绩在60分以上，就是及格：--%>
>         <c:if test="${score >= 60}">
>             成绩及格！！
>         </c:if>
>         
>         <c:if test="${score < 60}">
>             成绩不及格！！
>         </c:if>
>         
>         <c:if test="${score >= 60}" var="flag" scope="request">
>             <%--服务器的跳转：类似之前学习的请求转发--%>
>             <jsp:forward page="index.jsp"></jsp:forward>
>             成绩及格！！${flag}
>         </c:if>
>     </body>
> </html>
> ```



##### choose、when 和 otherwise 标签

choose 和 when 标签的作用与 Java 中的 switch 和 case 关键字相似，用于在众多选项中做出选择。 

也就是说：他们为相互排斥的条件式执行提供相关内容。 

switch语句中有case，而choose标签中对应有when。
switch语句中有default，而choose标签中有 otherwise。

```jsp
<c:choose>
    <c:when test="<boolean>">
        ...
    </c:when>
    <c:when test="<boolean>">
        ...
    </c:when>
    ...
    ...
    <c:otherwise>
        ...
    </c:otherwise>
</c:choose>
```



#### 迭代标签

forEach 是将一个主体内容迭代多次，或者迭代一个对象集合。

可以迭代的对象包括所有的 java.util.Collection 和 java.util.Map 接口的实现，以及对象或者基本类型的数组。

他还可 以迭代 java.util.Iterator 和 java.util.Enumeration，但不能在多个动作指令中使用 Iterator 或者 Enumeration, 因为 Iterator 或者 Enumeration 都不能重置（reset）。

```jsp
<c:forEach
           items="<object>"
           begin="<int>"
           end="<int>"
           step="<int>"
           var="<string>">
</c:forEach>
```

| 属性  | 描述                                   | 是否必要 | 默认值     |
| ----- | -------------------------------------- | -------- | ---------- |
| items | 要被循环的数据                         | 否       | 无         |
| begin | 开始的元素(0=第一个元素，1=第二个元素) | 否       | 0          |
| end   | 最后的元素                             | 否       | 最后的元素 |
| step  | 每一步迭代的步长                       | 否       | 1          |
| var   | 代表当前条目的变量名                   | 否       | 无         |



##### 遍历主体内容多次

相当于java的for循环： for(int i = 0; i < 10; i++) { }

```jsp
<c:forEach var="限域变量名" begin="开始数" end="结束数" step="迭代数" >
</c:forEach>
```

```js
<body>
    <%--输出0到10--%>
    <c:forEach var="i" begin="0" end="10">
        ${i}
    </c:forEach>
    <hr>
    <%--输出0,2,4,6,8,10--%>
    <c:forEach var="i" begin="0" end="10" step="2">
        ${i}
    </c:forEach>
</body>
```



##### 循环

相当于java的foreach循环： for(String str : list) {}

```jsp
<c:forEach items="被循环的集合" var="限域变量名">
</c:forEach>
```



#### 格式化动作标签

......
