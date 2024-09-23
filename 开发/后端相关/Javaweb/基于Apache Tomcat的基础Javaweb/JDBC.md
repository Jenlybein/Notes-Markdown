------

<center><font size=7><b> JDBC学习——个人笔记 </center></font></center>

---

[TOC]

---

## 前言

本学习笔记参考尚硅谷、w3c等教程。

学习 JDBC 之前，需先学习：核心 JAVA 编程、SQL 或 MySQL 数据库



## JDBC 简介

### 什么是JDBC

- JDBC：Java Database Connectivity，意为Java数据库连接。
- JDBC是Java提供的一组独立于任何数据库管理系统的API。
- Java提供接口规范，由各个数据库厂商提供接口的实现，厂商提供的实现类封装成jar文件，也就是我们俗称的数据库驱动jar包。
- 学习JDBC，充分体现了面向接口编程的好处，程序员只关心标准和规范，而无需关注实现过程。

![image-20240815215737248](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408152158860.png)



### JDBC API 库包含以下任务

都是与数据库相关的常用用法

- 制作到数据库的连接。
- 创建 SQL 或 MySQL 语句,
- 执行 SQL或 MySQL 查询数据库,
- 查看和修改所产生的记录。



### JDBC的核心组成

- 接口规范：
  - 为了项目代码的可移植性，可维护性，SUN公司从最初就制定了Java程序连接各种数据库的统一接口规范。这样的话，不管是连接哪一种DBMS软件，Java代码可以保持一致性。
  - 接口存储在java.sql和javax.sql包下。
- 实现规范：
  - 因为各个数据库厂商的DBMS软件各有不同，那么各自的内部如何通过SQL实现增、删、改、查等操作管理数据，只有这个数据库厂商自己更清楚，因此把接口规范的实现交给各个数据库厂商自己实现。
  - 厂商将实现内容和过程封装成jar文件，我们程序员只需要将jar文件引入到项目中集成即可，就可以开发调用实现过程操作数据库了。



### JDBC 架构

JDBC 的 API 支持两层和三层处理模式进行数据库访问，但一般的 JDBC 架构由两层处理模式组成：

- **JDBC API**: 提供了应用程序对 JDBC 管理器的连接。
- **JDBC Driver API**: 提供了 JDBC 管理器对驱动程序连接。

JDBC API 使用驱动程序管理器和数据库特定的驱动程序来提供异构（heterogeneous）数据库的透明连接。

JDBC 驱动程序管理器可确保正确的驱动程序来访问每个数据源。该驱动程序管理器能够支持连接到多个异构数据库的多个并发的驱动程序。

以下是结构图，其中显示了驱动程序管理器相对于在 JDBC 驱动程序和 Java 应用程序所处的位置。

 ![img](https://atts.w3cschool.cn/attachments/image/wk/jdbc/r8GqQJ3.jpg) 



### 常见的 JDBC 组件

JDBC 的 API 提供了以下接口和类：

**DriverManager ：**这个类管理一系列数据库驱动程序。匹配连接使用通信子协议从 JAVA 应用程序中请求合适的数据库驱动程序。识别 JDBC 下某个子协议的第一驱动程序将被用于建立数据库连接。

**Driver :** 这个接口处理与数据库服务器的通信。你将很少直接与驱动程序互动。相反，你使用 DriverManager 中的对象，它管理此类型的对象。它也抽象与驱动程序对象工作相关的详细信息。

**Connection :** 此接口具有接触数据库的所有方法。该连接对象表示通信上下文，即，所有与数据库的通信仅通过这个连接对象进行。

**Statement :** 使用创建于这个接口的对象将 SQL 语句提交到数据库。除了执行存储过程以外，一些派生的接口也接受参数。

**ResultSet :** 在你使用语句对象执行 SQL 查询后，这些对象保存从数据获得的数据。它作为一个迭代器，让您可以通过它的数据来移动。

**SQLException :** 这个类处理发生在数据库应用程序的任何错误。



## JDBC 环境

### 步骤

1. **安装 Java**

   使用 IDEA 下载的 Java ，并找到其安装路径。
   或前往 Java 下载官网，挑选中意的版本：[Java Downloads | Oracle](https://www.oracle.com/java/technologies/downloads/) 。

   之后，请设置环境变量 `JAVA_HOME` ：该环境变量应该指向你安装的JDK目录

2. **安装 MySQL 数据库**

   ​	`MySQL 数据库`： MySQL 是一个开源数据库。可以从 [MySQL 官方网站](http://dev.mysql.com/downloads/mysql)上下载。

3. **创建数据库**

4. **下载 JDBC-MySQL 的 jar 包**

   ​	下载官网：[MySQL :: Download MySQL Connector/J (Archived Versions)](https://downloads.mysql.com/archives/c-j/)
   ​	注意下载的 jar 包版本 最好与 MySQL 的大版本一致。
   ​	对应系统选，Windows系统选 Platform Independent，下载zip。

5. **在 IDEA 中 创建Java项目**

   ​	在项目下创建lib文件夹，将下载的驱动jar包复制到文件夹里。

6. **在项目中集成库**

   ​	选中lib文件夹右键->Add as Library，与项目集成。

7. **编写代码**

   1. 注册驱动【依赖的驱动类，进行安装】
   2. 获取连接【Connection建立连接】
   3. 创建发送SQL语句对象【Connection创建发送SQL语句的Statement】
   4. 发送SQL语句，并获取返回结果【Statement 发送sql语句到数据库并且取得返回结果】
   5. 结果集解析【结果集解析，将查询结果解析出来】
   6. 资源关闭【释放ResultSet、Statement 、Connection】



> ### 示例
>
> 创建数据库
>
> ```mysql
> create database jdbc_test;
> 
> use jdbc_test;
> 
> create table jdbc_temp1
> (
>     t_id     int auto_increment comment '员工编号' primary key,
>     t_name   varchar(100)  not null comment '员工姓名',
>     t_salary double(10, 5) not null comment '员工薪资',
>     t_age    int           not null comment '员工年龄'
> );
> 
> insert into jdbc_temp1 (t_name,t_salary,t_age)
> values  ('员工1', 777.77, 32),
>         ('员工2', 666.66, 41),
>         ('员工3',111, 23),
>         ('员工4',123, 26),
>         ('员工5', 123, 28);
> ```
>
> 在JAVA中使用jdbc访问数据库
>
> ```java
> package com.test.base;
> 
> import java.sql.*;
> 
> public class JDBCQuick {
>     public static void main(String[] args) throws ClassNotFoundException, SQLException {
>         // 1.注册驱动（将数据库厂商提供的驱动类进行加载）（jdk6之后不再需要写）
>         // Class.forName("com.mysql.cj.jdbc.Driver");
> 
>         // 2.获取连接对象
>         String url = "jdbc:mysql://localhost:3306/jdbc_test";
>         String user = "root";
>         String password = "86503358299";
>         Connection conn = DriverManager.getConnection(url, user, password);
> 
>         // 3.获取执行SQL语句的对象
>         Statement stmt = conn.createStatement();
> 
>         // 4.编写SQL语句并执行,接收返回的结果集
>         String sql = "select t_id, t_name, t_salary, t_age from jdbc_test.jdbc_temp1";
>         ResultSet rs = stmt.executeQuery(sql);
> 
>         // 5.处理结果：遍历结果集
>         while (rs.next()) {
>             int id = rs.getInt("t_id");
>             String name = rs.getString("t_name");
>             double salary = rs.getDouble("t_salary");
>             int age = rs.getInt("t_age");
>             System.out.println(id + "\t" + name + "\t" + salary + "\t" + age);
>         }
> 
>         // 6.资源释放（原则：先开后关，后开先关)
>         rs.close();
>         stmt.close();
>         conn.close();
>     }
> }
> ```



## 核心API

###  注册驱动

- ```java
  Class.forName("com.mysql.cj.jdbc.Driver");
  ```

- 在 Java 中，当使用 JDBC（Java Database Connectivity）连接数据库时，需要加载数据库特定的驱动程序，以便与数据库进行通信。加载驱动程序的目的是为了注册驱动程序，使得 JDBC API 能够识别并与特定的数据库进行交互。

- 从JDK6开始，不再需要显式地调用 `Class.forName()` 来加载 JDBC 驱动程序，只要在类路径中集成了对应的jar文件，会自动在初始化时注册驱动程序。



###  Connection

- Connection接口是JDBC API的重要接口，用于建立与数据库的通信通道。换而言之，Connection对象不为空，则代表一次数据库连接。
- 在建立连接时，需要指定数据库URL、用户名、密码参数。
  - URL：jdbc:mysql://localhost:3306/atguigu
    - jdbc:mysql://IP地址:端口号/数据库名称?参数键值对1&参数键值对2
- `Connection` 接口还负责管理事务，`Connection` 接口提供了 `commit` 和 `rollback` 方法，用于提交事务和回滚事务。
- 可以创建 `Statement` 对象，用于执行 SQL 语句并与数据库进行交互。
- 在使用JDBC技术时，必须要先获取Connection对象，在使用完毕后，要释放资源，避免资源占用浪费及泄漏。



###  Statement

- `Statement` 接口用于执行 SQL 语句并与数据库进行交互。它是 JDBC API 中的一个重要接口。通过 `Statement` 对象，可以向数据库发送 SQL 语句并获取执行结果。
- 结果可以是一个或多个结果。
  - 增删改：受影响行数单个结果。
  - 查询：单行单列、多行多列、单行多列等结果。
- 但是`Statement` 接口在执行SQL语句时，会产生`SQL注入攻击问题`:
  - 当使用 `Statement` 执行动态构建的 SQL 查询时，往往需要将查询条件与 SQL 语句拼接在一起，直接将参数和SQL语句一并生成，让SQL的查询条件始终为true得到结果。



###  PreparedStatement

- `PreparedStatement`是 `Statement` 接口的子接口，用于执行`预编译`的 SQL 查询，作用如下：
  - 预编译SQL语句：在创建PreparedStatement时，就会预编译SQL语句，也就是SQL语句已经固定。
  - 防止SQL注入：`PreparedStatement` 支持参数化查询，将数据作为参数传递到SQL语句中，采用?占位符的方式，将传入的参数用一对单引号包裹起来''，无论传递什么都作为值。有效防止传入关键字或值导致SQL注入问题。
  - 性能提升：PreparedStatement是预编译SQL语句，同一SQL语句多次执行的情况下，可以复用，不必每次重新编译和解析。
- 后续的学习我们都是基于PreparedStatement进行实现，更安全、效率更高！

```java
//输入需要的查询名称
     Scanner sc = new scanner(System.in);
     String name = sc.nextLine();

//原来的：
     // 3.获取执行SQL语句的对象
     Statement stmt = conn.createStatement();
     // 4.编写SQL语句并执行,接收返回的结果集
     String sql = "select t_id, t_name, t_salary, t_age from jdbc_test.jdbc_temp1 where t_name='"+name+"'";
     ResultSet rs = stmt.executeQuery(sql);

//使用PreparedStatement
     // 3.获取执行SQL语句的对象 (此处用问号)
     PreparedStatement ps = conn.prepareStatement("select t_id, t_name, t_salary, t_age from jdbc_test.jdbc_temp1 where t_name= ?");
     // 4.为问好占位符赋值，并执行Sql语句，接收返回的结果集
     ps.setString(1, name) //下标是指第几个问号
     ResultSet rs = ps.executeQuery();
```



###  ResultSet

- `ResultSet`是 JDBC API 中的一个接口，用于表示从数据库中`执行查询语句所返回的结果集`。它提供了一种用于遍历和访问查询结果的方式。
- 遍历结果：ResultSet可以使用 `next()` 方法将游标移动到结果集的下一行，逐行遍历数据库查询的结果，返回值为boolean类型，true代表有下一行结果，false则代表没有。
- 获取单列结果：可以通过`getXxx`的方法获取单列的数据，该方法为重载方法，支持索引和列名进行获取。



## 基本CRUD

CRUD是指在做计算处理时的增加(Create)、读取查询(Retrieve)、更新(Update)和删除(Delete)几个单词的首字母简写。主要被用在描述软件系统中DataBase或者持久层的基本操作功能。

### 查询单行单列

```java
@Test
    public void querySingleRowAndCol() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url,user,pasw);
        //预编译SQL语句
        PreparedStatement ps = conn.prepareStatement(
                "select count(*) as count from jdbc_temp1"
        );
        //执行语句，获取结果
        ResultSet rs = ps.executeQuery();
        //处理结果
        while(rs.next()){
            int count = rs.getInt("count");
            System.out.println("count = "+ count);
        }
        //释放资源（掀开后关)
        rs.close();
        ps.close();
        conn.close();
    }
```

### 查询单行多列

```java
public void querySingleRow() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url,user,pasw);

        //预编译SQL语句
        PreparedStatement ps = conn.prepareStatement(
                "select * from jdbc_temp1 where t_id = ?"
        );

        //执行语句，获取结果
        ps.setInt(1,2);
        ResultSet rs = ps.executeQuery();

        //处理结果
        while(rs.next()){
            int t_id = rs.getInt("t_id");
            String t_name = rs.getString("t_name");
            String t_salary = rs.getString("t_salary");
            int t_age = rs.getInt("t_age");
            System.out.println("id = " + t_id + ", name = " + t_name+ ", salary = " + t_salary + ", age = " + t_age);
        }

        //释放资源（掀开后关)
        rs.close();
        ps.close();
        conn.close();
    }
```

### 查询多行多列

```java
   public void queryMultiRow() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url,user,pasw);

        //预编译SQL语句
        PreparedStatement ps = conn.prepareStatement(
                "select * from jdbc_temp1"
        );

        //执行语句，获取结果
        ResultSet rs = ps.executeQuery();

        //处理结果
        System.out.println("id\tname\tsalary\tage");
        while(rs.next()){
            int t_id = rs.getInt("t_id");
            String t_name = rs.getString("t_name");
            String t_salary = rs.getString("t_salary");
            int t_age = rs.getInt("t_age");
            System.out.println(t_id + "\t" + t_name+ "\t" + t_salary + "\t" + t_age);
        }

        //释放资源（掀开后关)
        rs.close();
        ps.close();
        conn.close();
    }
```

### 新增数据

```java
    public void insertRow() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url,user,pasw);

        //预编译SQL语句
        PreparedStatement ps = conn.prepareStatement(
                "Insert into jdbc_temp1(t_name,t_salary,t_age) values(?,?,?)"
        );

        //执行语句，获取结果
        ps.setString(1,"员工6");
        ps.setDouble(2,321.0);
        ps.setInt(3,22);
        int rs = ps.executeUpdate();

        //处理结果
        if(rs > 0){
            System.out.println("新增数据成功");
        }
        else{
            System.out.println("新增数据失败");
        }

        //释放资源（掀开后关)
        ps.close();
        conn.close();
    }
```

### 修改数据

```java
    public void setRow() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url,user,pasw);

        //预编译SQL语句
        PreparedStatement ps = conn.prepareStatement(
                "UPDATE jdbc_temp1 set t_salary = ? where t_name = ?"
        );

        //执行语句，获取结果
        ps.setDouble(1,3000);
        ps.setString(2,"员工6");
        int rs = ps.executeUpdate();

        //处理结果
        if(rs > 0)
            System.out.println("修改成功");
        else
            System.out.println("修改失败");

        //释放资源（掀开后关)
        ps.close();
        conn.close();
    }
```

### 删除数据

```java
    public void deleteRow() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url,user,pasw);

        //预编译SQL语句
        PreparedStatement ps = conn.prepareStatement(
                "DELETE from jdbc_temp1 where t_name = ?"
        );

        //执行语句，获取结果
        ps.setString(1,"员工6");
        int rs = ps.executeUpdate();

        //处理结果
        if(rs > 0)
            System.out.println("删除成功");
        else
            System.out.println("删除失败");

        //释放资源（掀开后关)
        ps.close();
        conn.close();
    }
```



## JDBC拓展

### 实体类和ORM

> 在使用JDBC操作数据库时，直接提取到 Java 会变成零散的变量，不利于维护和管理。
>
> 而我们Java是面向对象的，一个表对应的是一个类，一行数据就对应的是Java中的一个对象，一个列对应的是对象的属性。
>
> 所以我们要把数据存储在一个载体里，这个载体就是**实体类**。

#### ORM（Object Relational Mapping）思想

中文名为**对象到关系数据库的映射**。
**作用**：在编程中，把面向对象的概念跟数据库中表的概念对应起来，以面向对象的角度操作数据库中的数据，即一张表对应一个类，一行数据对应一个对象，一个列对应一个属性。

当下 JDBC 这种过程我们称其为<u>手动ORM</u>。

ORM框架：比如MyBatis、JPA等。



##### ORM 对象示例

```java
public class Employee {
    private Integer id;
    private String name;
    private Double salary;
    private Integer age;

    public Employee(Integer id, String name, Double salary, Integer age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }
    
   // 以下还有getter，setter，toString
```



##### ORM 映射示例

```java
    public void querySingleRow() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url,user,pasw);
        
        //创建PreparedStatement对象，并预编译SQL语句，使用?占位符
        PreparedStatement ps = conn.prepareStatement("select * from jdbc_test.jdbc_temp1 where t_id = ?");

        //为占位符赋值，获取结果
        ps.setInt(1, 1);
        ResultSet rs = ps.executeQuery();
        //预先创建实体类变量
        Employee employee = null;
        //处理结果
        while (rs.next()) {
            int empId = rs.getInt("t_id");
            String empName = rs.getString("t_name");
            Double empSalary = Double.valueOf(rs.getString("t_salary"));
            int empAge = rs.getInt("t_age");
            employee = new Employee(empId,empName,empSalary,empAge);
        }

        System.out.println("employee = " + employee);

        //释放资源(先开后关原则)
        rs.close();
        ps.close();
        conn.close();
    }
```

```java
    public void queryRowList() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url, user, pasw);

        //创建PreparedStatement对象，并预编译SQL语句，使用?占位符
        PreparedStatement ps = conn.prepareStatement("select * from jdbc_test.jdbc_temp1");

        //为占位符赋值，获取结果
        ResultSet rs = ps.executeQuery();

        //预先创建实体类变量
        Employee employee = null;
        List<Employee> employeeList = new ArrayList<>();

        //处理结果
        while (rs.next()) {
            int empId = rs.getInt("t_id");
            String empName = rs.getString("t_name");
            Double empSalary = Double.valueOf(rs.getString("t_salary"));
            int empAge = rs.getInt("t_age");
            employee = new Employee(empId, empName, empSalary, empAge);
            employeeList.add(employee);
        }

        for (Employee e : employeeList) {
            System.out.println(e);
        }

        //释放资源(先开后关原则)
        rs.close();
        ps.close();
        conn.close();
    }
```



### 主键回显

在数据中，执行新增操作时，主键列为自动增长，可以在表中直观的看到。

但是在Java程序中，我们执行完新增后，只能得到受影响行数，无法得知当前新增数据的主键值。

在Java程序中获取数据库中插入新数据后的主键值，并赋值给Java对象，此操作为主键回显。

> 具体方法：
>
> 1.在创建Statement对象时候，要求执行后返回主键（加上Statement.RETURN_GENERATED_KEYS）
> 	PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
>
> 2.执行后，通过 getGeneratedKeys 获得主键
> 	ResultSet rsSet = ps.getGeneratedKeys();
> 	employee.setId(rsSet.getInt(1));

```java
public void ReturnPK() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url, user, pasw);

        //创建PreparedStatement对象，并预编译SQL语句，使用?占位符
        String sql = "INSERT INTO jdbc_test.jdbc_temp1(t_name,t_salary,t_age) VALUES(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        //创建对象，将对象的属性值，填充在占位符
        Employee employee = new Employee(null,"Jack",231.0,23);
        ps.setString(1, employee.getName());
        ps.setDouble(2,employee.getSalary());
        ps.setInt(3,employee.getAge());

        // 获取结果
        int rs = ps.executeUpdate();

        // 处理结果
        if (rs > 0) {
            System.out.println("成功");
            // 获取新增的主键列，回显到employee对象的属性上
            ResultSet rsSet = ps.getGeneratedKeys();
            if(rsSet.next())
                employee.setId(rsSet.getInt(1));
            rsSet.close();
            System.out.println(employee);
        }else {
            System.out.println("失败");
        }

        //释放资源(先开后关原则)
        ps.close();
        conn.close();
    }
```



### 批量操作

- 插入多条数据时，一条一条发送给数据库执行，效率低下

- 通过批量操作，可以提升多次操作效率

> 注意：
> 	1、必须在连接数据库的URL后面追加?rewriteBatchedStatements=true，允许批量操作
> 	2、新增SQL必须用values。且语句最后不要追加分号( ; )结束
> 	3、调用addBatch()方法，将SQL语句进行批量添加操作
> 	4、统一执行批量操作，调用executeBatch()

```java
    public void testBatch() throws SQLException {
        //获取链接
        String url = "jdbc:mysql://localhost:3306/jdbc_test?rewriteBatchedStatements=true"; //修改1
        String user = "root";
        String pasw = "86503358299";
        Connection conn = DriverManager.getConnection(url, user, pasw);

        //创建PreparedStatement对象，并预编译SQL语句，使用?占位符
        String sql = "INSERT INTO jdbc_test.jdbc_temp1(t_name,t_salary,t_age) VALUES(?,?,?)"; //修改2
        PreparedStatement ps = conn.prepareStatement(sql);

        //获取当前代码的执行时间(ms)
        long start = System.currentTimeMillis();

        //创建对象，将对象的属性值，填充在占位符
        for(int i = 0; i < 10000; i++){
            ps.setString(1, "abc"+i);
            ps.setInt(3,100+i);
            ps.setDouble(2,20+i);

            ps.addBatch(); //修改3
        }

        // 获取结果
        ps.executeBatch(); //修改4

        long end = System.currentTimeMillis();

        System.out.println("消耗时间: "+(end-start));

        //释放资源(先开后关原则)
        ps.close();
        conn.close();
    }
```



## 连接池

**问题**

- 每次操作数据库都要获取新连接，使用完毕后就close释放，频繁的创建和销毁造成资源浪费。
  	频繁创建和销毁 Connection。
- 连接的数量无法把控，对服务器来说压力巨大。



**连接池**

连接池 就是数据库连接对象的缓冲区，通过配置，由连接池负责创建连接、管理连接、释放连接等操作。

预先创建数据库连接放入连接池，用户在请求时，通过池直接获取连接，使用完毕后，将连接放回池中，避免了频繁的创建和销毁，同时解决了创建的效率。

当池中无连接可用，且未达到上限时，连接池会新建连接。

池中连接达到上限，用户请求会等待，可以设置超时时间。



**常见连接池**

JDBC 的数据库连接池使用 javax.sql.DataSource接口进行规范，所有的第三方连接池都实现此接口，自行添加具体实现！也就是说，所有连接池获取连接的和回收连接方法都一样，不同的只有性能和扩展功能!

| 名称       | 介绍                                                         |
| ---------- | ------------------------------------------------------------ |
| DBCP       | 是Apache提供的数据库连接池，速度相对C3P0较快，但自身存在一些BUG。 |
| C3P0       | 是一个开源组织提供的一个数据库连接池，速度相对较慢，稳定性还可以。 |
| Proxool    | 是sourceforge下的一个开源项目数据库连接池，有监控连接池状态的功能， 稳定性较c3p0差一点 |
| **Druid**  | 是阿里提供的数据库连接池，是集DBCP 、C3P0 、Proxool 优点于一身的数据库连接池，性能、扩展性、易用性都更好，功能丰富 |
| **Hikari** | （ひかり[shi ga li]）取自日语，是光的意思，是SpringBoot2.x之后内置的一款连接池，基于 BoneCP （已经放弃维护，推荐该连接池）做了不少的改进和优化，口号是快速、简单、可靠。 |



### 软编码和硬编码

#### 硬编码

将连接池的配置信息和Java代码耦合在一起。
        1、创建连接池对象。
        2、设置连接池的配置信息【必须 | 非必须】
        3、通过连接池获取连接对象
        4、回收连接【不是释放连接，而是将连接归还给连接池，给其他线程进行复用

#### 软编码

将连接池配置信息从JAVA代码中提取出来，以配置文件的形式存储。
		1、在项目目录下创建resources文件夹，标识该文件夹为资源目录
		2、在resources文件夹创建db.properties配置文件，将连接信息定义在该文件中。
		3、Java代码中读取db.properties配置文件。



### Druid 连接池

Alibaba Druid Github首页 ： [alibaba/druid Wiki · GitHub](https://github.com/alibaba/druid/wiki/首页)

Jar 包下载地址：[Central Repository: com/alibaba/druid (maven.org)](https://repo1.maven.org/maven2/com/alibaba/druid/)

先将jar包引入lib文件夹

#### 硬编码方式（了解）

```java
    public void testHardCodeDruid() throws SQLException {
        // 创建DruidDataSource 连接池对象
        DruidDataSource ds = new DruidDataSource();

        //设置连接池的配置信息
            // 必须设置的配置
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver"); //驱动类
        ds.setUrl("jdbc:mysql://localhost:3306/jdbc_test");
        ds.setUsername("root");
        ds.setPassword("86503358299");
            // 非必须设置的配置
        ds.setInitialSize(10); // 初始创建的链接对象
        ds.setMaxActive(20); // 最大链接对象个数

        // 通过连接池获取连接对象
        Connection conn = ds.getConnection();
        System.out.println(conn);
        // CRUD... ...

        // 回收连接
        conn.close(); //回收到池
    }
```



#### 软编码方式（推荐）

- 在项目目录下创建resources文件夹，标识该文件夹为资源目录

- 在resources文件夹创建db.properties配置文件，将连接信息定义在该文件中。

  ```properties
  # druid连接池需要的配置参数，key固定命名
  driverClassName = com.mysql.cj.jdbc.Driver
  url = jdbc:mysql://localhost:3306/jdbc_test
  username = root
  password = 86503358299
  
  initialSize = 10
  maxActive = 20
  ```

- Java代碼

  ```java
  public void testSoftCodeDruid() throws Exception {
          // 创建Properties集合，用于存储外部配置文件的key和value值
          Properties ppt = new Properties();
  
          // 读取外部配置文件，获取输入流，加载到集合
          InputStream is = DruidTest.class.getClassLoader().getResourceAsStream("db.properties");
          ppt.load(is);
  
          // 基于Properties集合构建DruidDataSource连接池
          DataSource ds = DruidDataSourceFactory.createDataSource(ppt);
  
          // 通过连接池获取连接对象
          Connection conn = ds.getConnection();
          System.out.println(conn);
          // CRUD... ...
  
          // 回收连接
          conn.close();
      }
  ```

#### Druid其他配置

| 配置                          | **缺省** | **说明**                                                     |
| ----------------------------- | -------- | ------------------------------------------------------------ |
| name                          |          | 配置这个属性的意义在于，如果存在多个数据源，监控的时候可以通过名字来区分开来。 如果没有配置，将会生成一个名字，格式是：”DataSource-” + System.identityHashCode(this) |
| jdbcUrl                       |          | 连接数据库的url，不同数据库不一样。例如：mysql : jdbc:mysql://10.20.153.104:3306/druid2 oracle : jdbc:oracle:thin:@10.20.149.85:1521:ocnauto |
| username                      |          | 连接数据库的用户名                                           |
| password                      |          | 连接数据库的密码。如果你不希望密码直接写在配置文件中，可以使用ConfigFilter。详细看这里：<https://github.com/alibaba/druid/wiki/%E4%BD%BF%E7%94%A8ConfigFilter> |
| driverClassName               |          | 根据url自动识别 这一项可配可不配，如果不配置druid会根据url自动识别dbType，然后选择相应的driverClassName(建议配置下) |
| initialSize                   | 0        | 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时 |
| maxActive                     | 8        | 最大连接池数量                                               |
| maxIdle                       | 8        | 已经不再使用，配置了也没效果                                 |
| minIdle                       |          | 最小连接池数量                                               |
| maxWait                       |          | 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。 |
| poolPreparedStatements        | false    | 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。 |
| maxOpenPreparedStatements     | -1       | 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100 |
| validationQuery               |          | 用来检测连接是否有效的sql，要求是一个查询语句。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。 |
| testOnBorrow                  | true     | 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 |
| testOnReturn                  | false    | 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能 |
| testWhileIdle                 | false    | 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。 |
| timeBetweenEvictionRunsMillis |          | 有两个含义： 1)Destroy线程会检测连接的间隔时间2)testWhileIdle的判断依据，详细看testWhileIdle属性的说明 |
| numTestsPerEvictionRun        |          | 不再使用，一个DruidDataSource只支持一个EvictionRun           |
| minEvictableIdleTimeMillis    |          |                                                              |
| connectionInitSqls            |          | 物理连接初始化的时候执行的sql                                |
| exceptionSorter               |          | 根据dbType自动识别 当数据库抛出一些不可恢复的异常时，抛弃连接 |
| filters                       |          | 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有： 监控统计用的filter:stat日志用的filter:log4j防御sql注入的filter:wall |
| proxyFilters                  |          | 类型是List，如果同时配置了filters和proxyFilters，是组合关系，并非替换关系 |



### HikariCP连接池

HikariCP Github官网：[GitHub - brettwooldridge/HikariCP: 光 HikariCP・A solid, high-performance, JDBC connection pool at last.](https://github.com/brettwooldridge/HikariCP)

HikariCP Maven中央仓库下载：[Maven Central: com.zaxxer:HikariCP:5.1.0 (sonatype.com)](https://central.sonatype.com/artifact/com.zaxxer/HikariCP/5.1.0/versions)

slf4j Maven中央仓库下载：[Maven Central: org.slf4j:slf4j-api (sonatype.com)](https://central.sonatype.com/artifact/org.slf4j/slf4j-api/versions)

slf4j 和 HikariCP 必须一起放入lib目录才可使用 Hikari连接池

#### 硬编码方式

```java
    public void testHardCodeHikari() throws SQLException {
        // 创建连接池对象
        HikariDataSource hds = new HikariDataSource();

        // 设置连接池的配置信息
            // 必须设置的配置
        hds.setDriverClassName("com.mysql.cj.jdbc.Driver"); //驱动类
        hds.setJdbcUrl("jdbc:mysql://localhost:3306/jdbc_test");
        hds.setUsername("root");
        hds.setPassword("86503358299");
            // 非必须设置的配置
        hds.setMinimumIdle(10); // 初始创建的链接对象
        hds.setMaximumPoolSize(20); // 最大链接对象个数

        // 获取连接对象
        Connection conn = hds.getConnection();
        System.out.println(conn);

        // 回收
        conn.close();
    }
```



#### 软编码方式

- 配置文件

  ```properties
  driverClassName=com.mysql.cj.jdbc.Driver
  jdbcUrl=jdbc:mysql://localhost:3306/jdbc_test
  username=root
  password=86503358299
  
  minimumIdle=10
  maximumPoolSize=20
  ```

- 代码

  ```java
      public void testSoftCodeHikari() throws SQLException, IOException {
          //创建Properties集合，用于存储外部配置文件key-value
          Properties ppt = new Properties();
  
          //读取外部配置文件，获取输入流，输到ppt集合
          InputStream is = HikariTest.class.getClassLoader().getResourceAsStream("hikari.properties");
          ppt.load(is);
  
          //创建Hikari连接池配置对象，将集合传入
          HikariConfig config = new HikariConfig(ppt);
  
          // 创建连接池对象
          HikariDataSource hds = new HikariDataSource(config);
  
          // 获取连接对象
          Connection conn = hds.getConnection();
          System.out.println(conn);
  
          // 回收
          conn.close();
      }
  ```

  

#### HikariCP其他配置

| 属性                | 默认值       | 说明                                                         |
| ------------------- | ------------ | ------------------------------------------------------------ |
| isAutoCommit        | true         | 自动提交从池中返回的连接                                     |
| connectionTimeout   | 30000        | 等待来自池的连接的最大毫秒数                                 |
| minimumIdle         | 10           | 池中维护的最小空闲连接数 minIdle<0或者minIdle>maxPoolSize，则被重置为maxPoolSize |
| maxLifetime         | 1800000      | 池中连接最长生命周期如果不等于0且小于30秒则会被重置回30分钟  |
| maximumPoolSize     | 10           | 池中最大连接数，包括闲置和使用中的连接                       |
| metricRegistry      | null         | 连接池的用户定义名称，主要出现在日志记录和JMX管理控制台中以识别池和池配置 |
| healthCheckRegistry | null         | 报告当前健康信息                                             |
| poolName            | HikariPool-1 | 连接池的用户定义名称，主要出现在日志记录和JMX管理控制台中以识别池和池配置 |
| idleTimeout         |              | 是允许连接在连接池中空闲的最长时间                           |



## JDBC优化及工具类封装

### 问题

我们在使用JDBC的过程中，发现部分代码存在冗余的问题（以下步骤会被多次重复使用）：

- 创建连接池。
- 获取连接。
- 连接的回收。

解决思路：将相同的步骤封装



### JDBC工具类（基础）

*	1、维护一个连接池对象。
*	2、对外提供在连接池中获取连接的方法
*	3、对外提供回收连接的方法
*	注意：工具类仅对外提供共性的功能代码，所以方法均为静态方法！

```java
public class JDBCUtil {
    //创建连接池引用，因为要提供给当前项目全局使用，所以创建为静态的。
    private static DataSource ds;

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
    //对外提供获取连接的静态方法
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    //对外提供回收连接的静态方法
    public static void release(Connection conn) throws SQLException {
        conn.close();
    }
}
```
```java
//测试
public class UtilTests {
    @Test
    public void test() throws SQLException {
        Connection conn = JDBCUtil.getConnection();
        System.out.println(conn);
        //CRUD...
        JDBCUtil.release(conn);
    }
}
```

> 注意：此种封装方式，无法保证单个请求连接的线程，多次操作数据库时，连接是同一个，无法保证事务！
>
> 同一用户线程多次操作获取了多个链接，造成连接资源的浪费。



### ThreadLocal

JDK 1.2 版本中提供 `java.lang.ThreadLocal`，为解决多线程程序的并发问题提供了一种新的思路。
	使用这个工具类可以很简洁地编写出优美的多线程程序。
	通常用来在在多线程中管理共享数据库连接、Session等。

`ThreadLocal` 用于保存某个线程共享变量。
	在Java中，每一个线程对象中都有一个`ThreadLocalMap<ThreadLocal， Object>`
	key 就是一个ThreadLocal，Object 即为该线程的共享变量。
	而这个map是通过 ThreadLocal 的 set 和 get 方法操作的。

对于同一个static ThreadLocal，不同线程只能从中get，set，remove自己的变量，而不会影响其他线程的变量。

- 在进行对象跨层传递的时候，使用ThreadLocal可以避免多次传递，打破层次间的约束。
- 线程间数据隔离。
- 进行事务操作，用于存储线程事务信息。
- 数据库连接，`Session`会话管理。

1. ThreadLocal对象.get: 获取ThreadLocal中当前线程共享变量的值。
2. ThreadLocal对象.set: 设置ThreadLocal中当前线程共享变量的值。
3. ThreadLocal对象.remove: 移除ThreadLocal中当前线程共享变量的值。



### JDBC工具类（进阶）

在之前版本基础上，将连接对象放在每个线程的ThreadLocal中，保证从头到尾当前线程操作的是同一连接对象。

 *  JDBC工具类（V2.0）：
 *      1、维护一个连接池对象、维护了一个线程绑定变量的ThreadLocal对象
 *      2、对外提供在ThreadLocal中获取连接的方法
 *      3、对外提供回收连接的方法，回收过程中，将要回收的连接从ThreadLocal中移除！
 *  注意：工具类仅对外提供共性的功能代码，所以方法均为静态方法！
 *  注意：使用ThreadLocal就是为了一个线程在多次数据库操作过程中，使用的是同一个连接！

```java
public class JDBCUtil_v2 {
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
            conn.close();
        }
    }
}
```

```java
// 测试
    @Test
    public void test2() throws SQLException {
        Connection conn1 = JDBCUtil_v2.getConnection();
        Connection conn2 = JDBCUtil_v2.getConnection();
        Connection conn3 = JDBCUtil_v2.getConnection();
        System.out.println(conn1);
        System.out.println(conn2);
        System.out.println(conn3);
        //CRUD...
        JDBCUtil_v2.release();
    }
```



## DAO (Data Access Object，数据访问对象)

### DAO 概念

Java是面向对象语言，数据在Java中通常以对象的形式存在。一张表对应一个实体类，一张表的操作对应一个DAO对象！

在Java操作数据库时，我们会将对同一张表的增删改查操作统一维护起来，维护的这个类就是DAO层。

DAO层只关注对数据库的操作，供业务层Service调用，将职责划分清楚



### BaseDAO 概念

基本上每一个数据表都应该有一个对应的DAO接口及其实现类，发现对所有表的操作（增、删、改、查）代码重复度很高，所以可以抽取公共代码，给这些DAO的实现类可以抽取一个公共的父类，复用增删改查的基本操作，我们称为BaseDAO。



### BaseDAO 代码

新建dao文件夹，在dao文件夹内创建 BaseDAO.java

1. 增删改
2. 多行查询 (此处需运用到反射的相关知识 ,参考：[看懂Class＜T＞ clazz；所需的Java基础知识_class clazz-CSDN博客](https://blog.csdn.net/weixin_45598285/article/details/122609937))
3. 单行查询 (复用多行查询)

```java
package com.test.senior.dao;

import com.test.senior.Util.JDBCUtil_v2;

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
        Connection conn = JDBCUtil_v2.getConnection();
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
            JDBCUtil_v2.release();
        }
        return row;
    }

    /**
     * 通用查询查询多个Javabean对象的方法
     * @param clazz 接收T类型的Class对象（例如Employee.class)
     */
    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws Exception {
        // 获取数据库连接，预编译 sql 语句
        Connection conn = JDBCUtil_v2.getConnection();
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
            JDBCUtil_v2.release();
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
}
```



### 测试 BaseDAO

在dao文件夹内创建 EmployeeDAO.java 。定义接口类。

```java
public interface EmployeeDAO {
    public List<Employee> selectAll() throws Exception;
    public Employee selectById(int id) throws Exception;
    public int insert(Employee employee);
    public int update(Employee employee);
    public int delete(int id);
}
```

在 dao 内创建 impl 文件夹，在 impl 内创建 EmployeeDAOimpl.java 。 定义实现类。

```java
public class EmployeeDAOimpl extends BaseDAO implements EmployeeDAO {
    @Override
    public List<Employee> selectAll() throws Exception {
        String sql = "SELECT t_id as id, t_name as name, t_salary as salary, t_age as age FROM jdbc_test.jdbc_temp1";
        return executeQuery(Employee.class, sql, null);
    }

    @Override
    public Employee selectById(int id) throws Exception {
        String sql = "SELECT t_id as id, t_name as name, t_salary as salary, t_age as age FROM jdbc_test.jdbc_temp1 WHERE t_id = ?";
        return executeQueryBean(Employee.class, sql, id);
    }

    @Override
    public int insert(Employee employee) throws Exception {
        String sql = "INSERT INTO jdbc_test.jdbc_temp1(t_name, t_salary, t_age) VALUES(?,?,?)";
        return executeUpdate(sql,employee.getName(),employee.getSalary(),employee.getAge());
    }

    @Override
    public int update(Employee employee) throws Exception {
        String sql = "UPDATE jdbc_test.jdbc_temp1 SET t_salary = ? WHERE t_id = ?";
        return executeUpdate(sql,employee.getSalary(),employee.getId());
    }

    @Override
    public int delete(int id) throws Exception{
        String sql = "DELETE FROM jdbc_test.jdbc_temp1 WHERE t_id = ?";
        return executeUpdate(sql, id);
    }
}
```

创建测试类

```java
public class BaseDAOTest {
    @Test
    public void testEmployeeDaoSelectAll() throws Exception {
        EmployeeDAO employee = new EmployeeDAOimpl();
        List<Employee> list = employee.selectAll();
        for (Employee emp : list) {
            System.out.println(emp);
        }
    }

    @Test
    public void testEmployeeDaoSelectID() throws Exception {
        EmployeeDAO employee = new EmployeeDAOimpl();
        Employee emp = employee.selectById(10008);
        System.out.println(emp);
    }

    @Test
    public void testEmployeeDaoInsert() throws Exception {
        EmployeeDAO employee = new EmployeeDAOimpl();
        Employee emp = new Employee(null,"Tom",1900.0,22);
        int result = employee.insert(emp);
        System.out.println(result);
    }

    @Test
    public void testEmployeeDaoUpdate() throws Exception {
        EmployeeDAO employee = new EmployeeDAOimpl();
        Employee emp = new Employee(10008,"Tom",2200.0,22);
        int result = employee.update(emp);
        System.out.println(result);
    }

    @Test
    public void testEmployeeDaoDelete() throws Exception {
        EmployeeDAO employee = new EmployeeDAOimpl();
        int result = employee.delete(10008);
        System.out.println(result);
    }
}
```

测试发现功能运行成功。



## 事务

### 事务回顾

- 数据库事务就是一种SQL语句执行的缓存机制，不会单条执行完毕就更新数据库数据，最终根据缓存内的多条语句执行结果统一判定!   一个事务内所有语句都成功及事务成功，我们可以触发commit提交事务来结束事务，更新数据!   一个事务内任意一条语句失败，即为事务失败，我们可以触发rollback回滚结束事务，数据回到事务之前状态!
- 一个业务涉及多条修改数据库语句!   例如:
  -  经典的转账案例，转账业务(A账户减钱和B账户加钱，要一起成功)   
  -  批量删除(涉及多个删除)       
  -  批量添加(涉及多个插入)  
- 事务的特性：
  1. 原子性（Atomicity）原子性是指事务是一个不可分割的工作单位，事务中的操作要么都发生，  要么都不发生。 
  2. 一致性（Consistency）事务必须使数据库从一个一致性状态变换到另外一个一致性状态。
  3. 隔离性（Isolation）事务的隔离性是指一个事务的执行不能被其他事务干扰，  即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。
  4. 持久性（Durability）持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，  接下来的其他操作和数据库故障不应该对其有任何影响
- 事务的提交方式：
  - 自动提交：每条语句自动存储一个事务中，执行成功自动提交，执行失败自动回滚! 
  - 手动提交:  手动开启事务，添加语句，手动提交或者手动回滚即可!



### 数据库事务操作

```mysql
-- 查看当前连接，事务的提交方式（ON 自动提交、OFF 关闭自动提交。若关闭自动提交需手动提交）
SHOW VARIABLES LIKE 'autocommit';

-- 设置手动提交
SET autocommit = FALSE;

-- 提交事务
COMMIT;
-- 若提交，则之后无法回滚，对数据库产生持久性影响

-- 回滚事务
ROLLBACK;
-- 若数据库执行命令出错，则可在未提交时回滚数据库到执行之前的状态
```



### JDBC 事务关键代码

```java
try{
    connection.setAutoCommit(false); //关闭自动提交了
    //connection.setAutoCommit(false)也就类型于SET autocommit = off
    
    //注意，只要当前connection对象，进行数据库操作，都不会自动提交事务
    //数据库动作!
    //prepareStatement - 单一的数据库动作 c r u d 
    //connection - 操作事务 
    
    //所有操作执行正确，提交事务！
    connection.commit();
  }catch(Execption e){
    //出现异常，则回滚事务！
    connection.rollback();
  }
```



### JDBC 实现

#### 数据库准备

```mysql
-- 继续在 jdbc_test 架构 中创建银行表
CREATE TABLE t_bank(
   id INT PRIMARY KEY AUTO_INCREMENT COMMENT '账号主键',
   account VARCHAR(20) NOT NULL UNIQUE COMMENT '账号',
   money  INT UNSIGNED COMMENT '金额，不能为负值') ;
   
INSERT INTO t_bank(account,money) VALUES
  ('zhangsan',1000),('lisi',1000);
```



#### JDBC 代码修改

修改 JDBCUtil_v2.java

```java
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
```

修改BaseDAO.java 中的 executeUpdate 和 executeQuery 方法

```java
	// 释放资源
        ps.close();
        //这里检查下是否开启事务，开启不关闭连接，业务方法关闭!
        //connection.getAutoCommit()为false，不要在这里回收connection，由开启事务的地方回收
        //connection.getAutoCommit()为true，正常回收连接
        //没有开启事务的话，直接回收关闭即可!
        if (conn.getAutoCommit()) {
            JDBCUtil_v2.release();
        }
```



#### JDBC 代码增加

DAO接口代码

```java
public interface BankDAOI{
    int addMoney(Integer id,Integer money);
    int subMoney(Integer id,Integer money);
}
```

DAO实现类代码

```java
public class BankDAOimpl extends BaseDAO implements BankDao {
    @Override
    public int addMoney(Integer id, Integer money) throws Exception {
        String sql = "update t_bank set money = money + ? where id = ?";
        return executeUpdate(sql,money,id);
    }

    @Override
    public int subMoney(Integer id, Integer money) throws Exception {
        String sql = "update t_bank set money = money - ? where id = ?";
        return executeUpdate(sql,money,id);
    }
}
```

测试代码（手动添加异常，查看出现错误和无错时，数据库的不同）

```java
public class BankDAOTest {
    @Test
    public void testTransaction() throws SQLException {
        BankDao bd = new BankDAOimpl();
        Connection conn = null;

        try{
            //获取连接，将连接的事务改为手动提交
            conn = JDBCUtil_v2.getConnection();
            conn.setAutoCommit(false);

            //操作打款
            bd.subMoney(1,100);
            // int i = 10/0; //手动添加异常，查看事务是否添加成功
            bd.addMoney(2,100);

            //前置多次的dao操作没有异常，提交事务
            conn.commit();
        }catch (Exception e){
            // 出现异常，回滚事务
            try{
                conn.rollback();
                throw new RuntimeException(e);
            }catch (Exception ex){
                throw new RuntimeException(ex);
            }
        }finally {
            JDBCUtil_v2.release();
        }
    }
}
```

