------

<center><font size=7><b> Servlet——个人笔记 </center></font></center>

---

[TOC]

---

本学习笔记参考尚硅谷、菜鸟教程、w3cschool等教程。



## Servlet简介

Servlet 官网: [Java Servlet Technology (oracle.com)](https://www.oracle.com/java/technologies/java-servlet-tec.html)
Servlet API文档: [Java Servlet Technology - The Java EE 6 Tutorial (oracle.com)](https://docs.oracle.com/javaee/6/tutorial/doc/bnafd.html)

Java Servlet 是运行在 Web 服务器或应用服务器上的程序，它是作为来自 Web 浏览器或其他 HTTP 客户端的请求和 HTTP 服务器上的数据库或应用程序之间的中间层。

狭义的Servlet是指Java语言实现的一个接口，广义的Servlet是指任何实现了这个Servlet接口的类，一般情况下，人们将Servlet理解为后者。一个 Servlet 就是 Java 编程语言中的一个类，它被用来扩展服务器的性能，服务器上驻留着可以通过“请求-响应”编程模型来访问的应用程序。虽然 Servlet 可以对任何类型的请求产生响应，但通常只用来扩展 Web 服务器的应用程序。

使用 Servlet，可以交互式地浏览和修改数据，生成动态Web内容，收集来自网页表单的用户输入，呈现来自数据库或者其他源的记录。

Servlet运行于支持Java的应用服务器中。从原理上讲，Servlet可以响应任何类型的请求，但绝大多数情况下Servlet只用来扩展基于HTTP协议的Web服务器。



### Servlet命名

Servlet 的命名可以看出 sun 命名的特点，如 Applet 表示小应用程序；Scriptlet = Script + Applet，表示小脚本程序；同样 Servlet = Service + Applet，表示小服务程序。



### Servlet由来

Servlet 是在服务器上运行的小程序。这个词是在 Java applet的环境中创造的，Java applet 是一种当作单独文件跟网页一起发送的小程序，它通常用于在客户端运行，结果得到为用户进行运算或者根据用户互作用定位图形等服务。

服务器上需要一些程序，常常是根据用户输入访问数据库的程序。这些通常是使用公共网关接口（Common Gateway Interface，CGI）应用程序完成的。然而，在服务器上运行 Java，这种程序可使用 Java 编程语言实现。在通信量大的服务器上，JavaServlet 的优点在于它们的执行速度更快于 CGI 程序。各个用户请求被激活成单个程序中的一个线程，而无需创建单独的进程，这意味着服务器端处理请求的系统开销将明显降低。

#### 实现过程

最早支持 Servlet 技术的是 JavaSoft 的 Java Web Server。此后，一些其它的基于 Java 的 Web Server 开始支持标准的 Servlet API。Servlet 的主要功能在于交互式地浏览和修改数据，生成动态 Web 内容。

1) 客户端发送请求至服务器端；
2) 服务器将请求信息发送至 Servlet；
3) Servlet 生成响应内容并将其传给服务器。响应内容动态生成，通常取决于客户端的请求；
4) 服务器将响应返回给客户端。



### Servlet 相对 CGI 的优势

Java Servlet 通常情况下与使用 CGI（Common Gateway Interface，公共网关接口）实现的程序可以达到异曲同工的效果。但是相比于 CGI，Servlet 有以下几点优势：

- 性能明显更好。
- Servlet 在 Web 服务器的地址空间内执行。这样它就没有必要再创建一个单独的进程来处理每个客户端请求。
- Servlet 是独立于平台的，因为它们是用 Java 编写的。
- 服务器上的 Java 安全管理器执行了一系列限制，以保护服务器计算机上的资源。因此，Servlet 是可信的。
- Java 类库的全部功能对 Servlet 来说都是可用的。它可以通过 sockets 和 RMI 机制与 applets、数据库或其他软件进行交互。



### 简要说说什么是CGI

按我的初步理解，CGI形式的程序，是通过前端修改网页URL来实现与后端程序通信的。

详细解释借用他人博客：[WEB之CGI----CGI详解（原理，配置及访问）_cgi web-CSDN博客](https://blog.csdn.net/weixin_39609623/article/details/86312439)



## Servlet 在IDEA中开发流程

1. 创建JavaWeb项目，添加web框架支持，将Tomcat添加入项目依赖。

   ```html
   <!-- 以下示例为验证username是否与admin冲突，是则输出No，否则输出Yes -->
   <!doctype html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <title>Document</title>
   </head>
   <body>
   
   <form method="get" action="userServlet">
       用户名: <input type="text" name="username"><br>
       <input type="submit" value="校验">
   </form>
   
   </body>
   </html>

2. 重写service方法，service(HttpServletRequest req, HttpServletResponse resp)

3. 在service方法中，定义业务处理代码。

   ```java
   public class UserServlet extends HttpServlet {
       @Override
       protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           //super.service(req, resp); //注释此处，不然会出现405
   
           // 1. 从request对象中获取请求中任何信息
           String username = req.getParameter("username"); //根据参数名获取参数值，无论参数是在url?后，还是在请求体中
           // 2. 处理业务代码
           String info = "YES";
           if("admin".equals(username)){
               info = "NO";
           }
           // 3.设置Content-Type响应头，明确返回的数据类型
           resp.setContentType("text/html;charset=utf-8");
           // 4.将要相应的数据放入response
           PrintWriter out = resp.getWriter(); // 该方法返回的是一个向响应体中打印字符串的打印流
           out.println(info);
       }
   }
   ```

4. 在web.xml中配置Servlet对应的请求映射路径（web-app标签内）

   ```xml
       <!--
           配置Servlet类，并起一个别名
           servlet-class 告诉tomcat对应的要实例化的servlet类
           servlet-name 用于关联请求的映射路径
       -->
       <servlet>
           <!--给UserServlet起一个别名-->
           <servlet-name>userServlet</servlet-name>
           <servlet-class>com.test.servlet.UserServlet</servlet-class>
       </servlet>
       
       <servlet-mapping>
           <!--关联别名和映射路径-->
           <servlet-name>userServlet</servlet-name>
           <url-pattern>/userServlet</url-pattern>
       </servlet-mapping>
   ```

> 注意：
> 一个servlet-name可以对应多个url-pattern，但一个url-pattern只能对应一个servlet-name
>  /        表示通配所有资源,不包括jsp文件
>  /*       表示通配所有资源,包括jsp文件
>  /a/*     匹配所有以a前缀的映射路径
> *.action 匹配所有以action为后缀的映射路径

> 映射关系图
>
> ![image-20240808175344658](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408081753723.png)



## Servlet注解方式配置

利用注解时，可以省去在web.xml中写入相关标签，加开开发效率。



### @WebServlet注解源码

官方JAVAEEAPI文档下载地址 ：[Java EE - Technologies (oracle.com)](https://www.oracle.com/java/technologies/javaee/javaeetechnologies.html#javaee8) 

```java
public @interface WebServlet {
    /**
     * The name of the servlet
     * 相当于 servlet-name
     * @return the name of the servlet
     */
    String name() default "";

    /**
     * The URL patterns of the servlet
     * 如果只配置一个url-pattern ,则通过该属性即可,和urlPatterns属性互斥
     * @return the URL patterns of the servlet
     */
    String[] value() default {};

    /**
     * The URL patterns of the servlet
     * 如果要配置多个url-pattern ,需要通过该属性,和value属性互斥
     * @return the URL patterns of the servlet
     */
    String[] urlPatterns() default {};

    /**
     * The load-on-startup order of the servlet
     * 配置Servlet是否在项目加载时实例化
     * 默认值-1时，tomcat启动不会实例化该servlet
     * 其他正整数，tomcat启动时实例化该servlet的顺序
     * @return the load-on-startup order of the servlet
     */
    int loadOnStartup() default -1;

    /**
     * The init parameters of the servlet
     * 配置初始化参数
     * @return the init parameters of the servlet
     */
    WebInitParam[] initParams() default {};

    /**
     * Declares whether the servlet supports asynchronous operation mode.
     *
     * @return {@code true} if the servlet supports asynchronous operation mode
     * @see jakarta.servlet.ServletRequest#startAsync
     * @see jakarta.servlet.ServletRequest#startAsync( jakarta.servlet.ServletRequest,jakarta.servlet.ServletResponse)
     */
    boolean asyncSupported() default false;

    /**
     * The small-icon of the servlet
     *
     * @return the small-icon of the servlet
     */
    String smallIcon() default "";

    /**
     * The large-icon of the servlet
     *
     * @return the large-icon of the servlet
     */
    String largeIcon() default "";

    /**
     * The description of the servlet
     *
     * @return the description of the servlet
     */
    String description() default "";

    /**
     * The display name of the servlet
     *
     * @return the display name of the servlet
     */
    String displayName() default "";
}
```



### @WebServlet注解使用

```java
@WebServlet(
        name = "userServlet",
        //value = "/user",
        urlPatterns = {"/userServlet1","/userServlet2","/userServlet"},
        initParams = {@WebInitParam(name = "encoding",value = "UTF-8")},
        loadOnStartup = 6
)
public class UserServlet  extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //......
    }
}
```





## Servlet常见容器

Tomcat, Jetty, resin, Oracle Application server, WebLogic Server, Glassfish, Websphere, JBoss 等等。（提供了 Servlet 功能的服务器，叫做 Servlet 容器。对 web 程序来说，Servlet 容器的作用就相当于桌面程序里操作系统的作用，都是提供一些编程基础设施）



## Servlet 生命周期

### 简介

生命周期简述：对象在容器中从开始创建到销毁的过程。
应用程序中的对象不仅在空间上有层次结构的关系，在时间上也会因为处于程序运行过程中的不同阶段而表现出不同状态和不同行为——这就是对象的生命周期。

Servlet对象是Servlet容器创建的，生命周期方法都是由容器(目前我们使用的是Tomcat)调用的。这一点和我们之前所编写的代码有很大不同。在今后的学习中我们会看到，越来越多的对象交给容器或框架来创建，越来越多的方法由容器或框架来调用，开发人员要尽可能多的将精力放在业务逻辑的实现上。

| 生命周期 | 对应方法                                                 | 执行时机               | 执行次数 |
| -------- | -------------------------------------------------------- | ---------------------- | -------- |
| 构造对象 | 构造器                                                   | 第一次请求或者容器启动 | 1        |
| 初始化   | init()                                                   | 构造完毕后             | 1        |
| 处理服务 | service(HttpServletRequest req,HttpServletResponse resp) | 每次请求               | 多次     |
| 销毁     | destory()                                                | 容器关闭               | 1        |



### 测试

运行服务器，在对应网址url后加上 /servletLifeCycle 进行访问测试。
发现只会在第一次请求时使用构造器、初始化方法和service方法，之后的刷新只会调用service方法。
服务器结束时候才会调用销毁方法。

```java
package com.test.servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletLifeCycle")
public class ServletLifeCycle  extends HttpServlet {
    public ServletLifeCycle(){
        System.out.println("构造器");
    }
    @Override
    public void init() throws ServletException {
        System.out.println("初始化方法");
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service方法");
    }
    @Override
    public void destroy() {
        System.out.println("销毁方法");
    }
}
```

> 注意：
>
> 1. 通过生命周期测试我们发现Servlet对象在容器中是单例的
> 2. 容器是可以处理并发的用户请求的,每个请求在容器中都会开启一个线程
> 3. 多个线程可能会使用相同的Servlet对象,所以在Servlet中,我们不要轻易定义一些容易经常发生修改的成员变量
> 4. load-on-startup中定义的正整数表示实例化顺序,如果数字重复了,容器会自行解决实例化顺序问题,但是应该避免重复
> 5. Tomcat容器中,已经定义了一些随系统启动实例化的servlet,我们自定义的servlet的load-on-startup尽量不要占用数字1-5











## Servlet 方法

### init() 方法

init 方法被设计成只调用一次。它在第一次创建 Servlet 时被调用，在后续每次用户请求时不再调用。因此，它是用于一次性初始化，就像 Applet 的 init 方法一样。

Servlet 创建于用户第一次调用对应于该 Servlet 的 URL 时，但是您也可以指定 Servlet 在服务器第一次启动时被加载。

当用户调用一个 Servlet 时，就会创建一个 Servlet 实例，每一个用户请求都会产生一个新的线程，适当的时候移交给 doGet 或 doPost 方法。init() 方法简单地创建或加载一些数据，这些数据将被用于 Servlet 的整个生命周期。

```java
public void init() throws ServletException {
  // 初始化代码...
}
```

### service() 方法

service() 方法是执行实际任务的主要方法。Servlet 容器（即 Web 服务器）调用 service() 方法来处理来自客户端（浏览器）的请求，并把格式化的响应写回给客户端。

每次服务器接收到一个 Servlet 请求时，服务器会产生一个新的线程并调用服务。service() 方法检查 HTTP 请求类型（GET、POST、PUT、DELETE 等），并在适当的时候调用 doGet、doPost、doPut，doDelete 等方法。

```java
public void service(ServletRequest request, 
                    ServletResponse response) 
      throws ServletException, IOException{
}
```

service() 方法由容器调用，service 方法在适当的时候调用 doGet、doPost、doPut、doDelete 等方法。所以，您不用对 service() 方法做任何动作，您只需要根据来自客户端的请求类型来重写 doGet() 或 doPost() 即可。

### doGet() 方法

GET 请求来自于一个 URL 的正常请求，或者来自于一个未指定 METHOD 的 HTML 表单，它由 doGet() 方法处理。

```java
public void doGet(HttpServletRequest request,
                  HttpServletResponse response)
    throws ServletException, IOException {
    // Servlet 代码
}
```

### doPost() 方法

POST 请求来自于一个特别指定了 METHOD 为 POST 的 HTML 表单，它由 doPost() 方法处理。

```java
public void doPost(HttpServletRequest request,
                   HttpServletResponse response)
    throws ServletException, IOException {
    // Servlet 代码
}
```

### destroy() 方法

destroy() 方法只会被调用一次，在 Servlet 生命周期结束时被调用。destroy() 方法可以让您的 Servlet 关闭数据库连接、停止后台线程、把 Cookie 列表或点击计数器写入到磁盘，并执行其他类似的清理活动。

在调用 destroy() 方法之后，servlet 对象被标记为垃圾回收。

```java
  public void destroy() {
    // 终止化代码...
  }
```

### 架构图

下图显示了一个典型的 Servlet 生命周期方案。

- 第一个到达服务器的 HTTP 请求被委派到 Servlet 容器。
- Servlet 容器在调用 service() 方法之前加载 Servlet。
- 然后 Servlet 容器处理由多个线程产生的多个请求，每个线程执行一个单一的 Servlet 实例的 service() 方法。

![Servlet 生命周期](https://www.runoob.com/wp-content/uploads/2014/07/Servlet-LifeCycle.jpg)



## ServletConfig和ServletContext

### ServletConfig

+ ServletConfig是为Servlet提供初始配置参数的一种对象,每个Servlet都有自己独立唯一的ServletConfig对象
+ 容器会为每个Servlet实例化一个ServletConfig对象,并通过Servlet生命周期的init方法传入给Servlet作为属性

```java
package jakarta.servlet;
import java.util.Enumeration;
public interface ServletConfig {
    String getServletName();
    ServletContext getServletContext();
    String getInitParameter(String var1);
    Enumeration<String> getInitParameterNames();
}
```

| 方法名                  | 作用                                                         |
| ----------------------- | ------------------------------------------------------------ |
| getServletName()        | 获取\<servlet-name>HelloServlet\</servlet-name>定义的Servlet名称 |
| getServletContext()     | 获取ServletContext对象                                       |
| getInitParameter()      | 获取配置Servlet时设置的『初始化参数』，根据名字获取值        |
| getInitParameterNames() | 获取所有初始化参数名组成的Enumeration对象                    |

![image-20240810220232770](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408102202840.png)



### ServletContext

+ ServletContext对象有称呼为上下文对象,或者叫应用域对象(后讲)
+ 容器会为每个app创建一个独立的唯一的ServletContext对象
+ ServletContext对象为**所有**的Servlet所共享
+ ServletContext可以为**所有**的Servlet提供初始配置参数

![image-20240810220348120](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408102203169.png)

| 其他重要API                                                  | 功能                 | 功能解释                                                     |
| ------------------------------------------------------------ | -------------------- | ------------------------------------------------------------ |
| String realPath = servletContext.getRealPath("资源在web目录中的路径"); | 获取项目的上下文路径 | 使用了servletContext动态获取资源的真实路径，**那么无论项目的部署路径发生什么变化，都会动态获取项目运行时候的实际路径**，所以就不会发生由于写死真实路径而导致项目部署位置改变引发的路径错误问题 |
| String contextPath = servletContext.getContextPath();        | 获取项目的上下文路径 | 项目的部署名称,也叫项目的上下文路径,在部署进入tomcat时所使用的路径,该路径是可能发生变化的,通过该API动态获取项目真实的上下文路径,可以**帮助我们解决一些后端页面渲染技术或者请求转发和响应重定向中的路径问题** |



| 域对象的相关API                             | 功能解释            |
| ------------------------------------------- | ------------------- |
| void setAttribute(String key,Object value); | 向域中存储/修改数据 |
| Object getAttribute(String key);            | 获得域中的数据      |
| void removeAttribute(String key);           | 移除域中的数据      |

+ 域对象: 一些用于存储数据和传递数据的对象,传递数据不同的范围,我们称之为不同的域,不同的域对象代表不同的域,共享数据的范围也不同
+ ServletContext代表应用,所以ServletContext域也叫作应用域,是webapp中最大的域,可以在本应用内实现数据的共享和传递
+ webapp中的三大域对象,分别是应用域,会话域,请求域





### 使用举例


> - 定义Servlet
>
> ```java
> public class ServletA extends HttpServlet {
>     @Override
>     protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
>         ServletConfig servletConfig = this.getServletConfig();
>         // 根据参数名获取单个参数
>         String value = servletConfig.getInitParameter("param1");
>         System.out.println("param1:"+value);
>         // 获取所有参数名
>         Enumeration<String> parameterNames = servletConfig.getInitParameterNames();
>         // 迭代并获取参数名
>         while (parameterNames.hasMoreElements()) {
>             String paramaterName = parameterNames.nextElement();
>             System.out.println(paramaterName+":"+servletConfig.getInitParameter(paramaterName));
>         }
>     }
> }
> 
> public class ServletB extends HttpServlet {
>     @Override
>     protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
>         ServletConfig servletConfig = this.getServletConfig();
>         // 根据参数名获取单个参数
>         String value = servletConfig.getInitParameter("param1");
>         System.out.println("param1:"+value);
>         // 获取所有参数名
>         Enumeration<String> parameterNames = servletConfig.getInitParameterNames();
>         // 迭代并获取参数名
>         while (parameterNames.hasMoreElements()) {
>             String paramaterName = parameterNames.nextElement();
>             System.out.println(paramaterName+":"+servletConfig.getInitParameter(paramaterName));
>         }
>     }
> }
> 
> public class ServletContext extends HttpServlet {
>     @Override
>     protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
>         ServletConfig servletConfig = this.getServletConfig();
>         
>         // 获取context
>         ServletContext = servletContext = servletConfig.getServletContext();
>         
>         //根据名称获取参数
>         String encoding = servletContext.getInitParameter("encodeing");
>         System.out.println("encoding:"+encoding);
>         
>        //迭代器获取参数
>         Enumeration<String> parameterNames = servletContext.getInitParameterNames();
>         while(parameterNames.hasMoreElements()){
>             String pname = parameterNames.nextElement();
>             System.out.println(pname+"="+String encoding = servletContext.getInitParameter(pname));
>         }
>     }
> }
> ```
>
> - 配置Servlet
>
> ```xml
> <!--config--> 
> <servlet>
>        <servlet-name>ServletA</servlet-name>
>        <servlet-class>com.atguigu.servlet.ServletA</servlet-class>
>        <!--配置ServletA的初始参数-->
>        <init-param>
>            <param-name>param1</param-name>
>            <param-value>value1</param-value>
>        </init-param>
>        <init-param>
>            <param-name>param2</param-name>
>            <param-value>value2</param-value>
>        </init-param>
>    </servlet>
> 
>     <servlet>
>         <servlet-name>ServletB</servlet-name>
>         <servlet-class>com.atguigu.servlet.ServletB</servlet-class>
>         <!--配置ServletB的初始参数-->
>         <init-param>
>             <param-name>param3</param-name>
>             <param-value>value3</param-value>
>         </init-param>
>         <init-param>
>             <param-name>param4</param-name>
>             <param-value>value4</param-value>
>         </init-param>
>     </servlet>
> 
>     <servlet-mapping>
>         <servlet-name>ServletA</servlet-name>
>         <url-pattern>/servletA</url-pattern>
>     </servlet-mapping>
> 
>     <servlet-mapping>
>         <servlet-name>ServletB</servlet-name>
>         <url-pattern>/servletB</url-pattern>
>     </servlet-mapping>
> 
> <!--context--> 
>     <context-param>
>         <param-name>encoding</param-name>
>         <param-value>UTF-8</param-value>
>     </context-param>
> 
>     <context-param>
>         <param-name>username</param-name>
>         <param-value>zhangsan</param-value>
>     </context-param>
> ```
>



## Servlet 表单数据

### GET 方法

GET 方法向页面请求发送已编码的用户信息。页面和已编码的信息中间用 `?` 字符分隔。

```url
http://www.test.com/hello?key1=value1&key2=value2
```

GET 方法是默认的从浏览器向 Web 服务器传递信息的方法，它会产生一个很长的字符串，出现在浏览器的地址栏中。如果要向服务器传递的是密码或其他的敏感信息，请不要使用 GET 方法。

GET 方法有大小限制：请求字符串中最多只能有 1024 个字符。

这些信息使用 QUERY_STRING 头传递，并可以通过 QUERY_STRING 环境变量访问，Servlet 使用 **doGet()** 方法处理这种类型的请求。

### POST 方法

另一个向后台程序传递信息的比较可靠的方法是 POST 方法。POST 方法打包信息的方式与 GET 方法基本相同，但是 POST 方法不是把信息作为 URL 中 ? 字符后的文本字符串进行发送，而是把这些信息作为一个单独的消息。消息以标准输出的形式传到后台程序，您可以解析和使用这些标准输出。Servlet 使用 doPost() 方法处理这种类型的请求。

### 使用 Servlet 读取表单数据

Servlet 处理表单数据，这些数据会根据不同的情况使用不同的方法自动解析：

- **getParameter()：**您可以调用 request.getParameter() 方法来获取表单参数的值。
- **getParameterValues()：**如果参数出现一次以上，则调用该方法，并返回多个值，例如复选框。
- **getParameterNames()：**如果您想要得到当前请求中的所有参数的完整列表，则调用该方法。



## Servlet 请求和相应

### HttpServletRequest 客户端 HTTP 请求

+ HttpServletRequest是一个接口,其父接口是ServletRequest

+ HttpServletRequest是Tomcat将请求报文转换封装而来的对象,在Tomcat调用service方法时传入

+ HttpServletRequest代表客户端发来的请求,所有请求中的信息都可以通过该对象获得

  ![image-20240811210614180](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408112106749.png)



| 获得请求参数相关 API                                    | 功能解释                             |
| ------------------------------------------------------- | ------------------------------------ |
| String getParameter(String parameterName);              | 根据请求参数名获取请求单个参数值     |
| String[] getParameterValues(String parameterName);      | 根据请求参数名获取请求多个参数值数组 |
| Enumeration<String> getParameterNames();                | 获取所有请求参数名                   |
| Map<String, String[]> getParameterMap();                | 获取所有请求参数的键值对集合         |
| BufferedReader getReader() throws IOException;          | 获取读取请求体的字符输入流           |
| ServletInputStream getInputStream() throws IOException; | 获取读取请求体的字节输入流           |
| int getContentLength();                                 | 获得请求体长度的字节数               |

  

| 获取请求行信息相关(方式,请求的url,协议及版本) API | 功能解释                       |
| ------------------------------------------------- | ------------------------------ |
| StringBuffer getRequestURL();                     | 获取客户端请求的url            |
| String getRequestURI();                           | 获取客户端请求项目中的具体资源 |
| int getServerPort();                              | 获取客户端发送请求时的端口     |
| int getLocalPort();                               | 获取本应用在所在容器的端口     |
| int getRemotePort();                              | 获取客户端程序的端口           |
| String getScheme();                               | 获取请求协议                   |
| String getProtocol();                             | 获取请求协议及版本号           |
| String getMethod();                               | 获取请求方式                   |

```java
// 示例
@WebServlet("/servlet02")
public class Servlet02 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 行相关  GET/POST  URI  URL  HTTP/1.1
        System.out.println(req.getMethod());        // 获取请求方式
        System.out.println(req.getScheme());        // 获取请求协议
        System.out.println(req.getProtocol());      // 获取请求协议及版本号
        System.out.println(req.getRequestURI());    // 获取客户端请求的uri (项目内资源的路径)
        System.out.println(req.getRequestURL());    // 获取客户端请求的url (项目内资源的完整路径）

        System.out.println(req.getLocalPort());     // 获取本应用在所在容器的端口
        System.out.println(req.getServerPort());    // 获取客户端发送请求时的端口（可能因为代理服务器原因与服务器本地不一样）
        System.out.println(req.getRemotePort() + "\n");    // 获取客户端程序的端口

        // 头相关 key:value...
        String Accept = req.getHeader("Accept");
        System.out.println("Accept : " + Accept);

        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = req.getHeader(name);
            System.out.println(value);
        }
    }
}
```



| 请求头包含信息      | 描述                                                         |
| :------------------ | :----------------------------------------------------------- |
| Accept              | 这个头信息指定浏览器或其他客户端可以处理的 MIME 类型。值 **image/png** 或 **image/jpeg** 是最常见的两种可能值。 |
| Accept-Charset      | 这个头信息指定浏览器可以用来显示信息的字符集。例如 ISO-8859-1。 |
| Accept-Encoding     | 这个头信息指定浏览器知道如何处理的编码类型。值 **gzip** 或 **compress** 是最常见的两种可能值。 |
| Accept-Language     | 这个头信息指定客户端的首选语言，在这种情况下，Servlet 会产生多种语言的结果。例如，en、en-us、ru 等。 |
| Authorization       | 这个头信息用于客户端在访问受密码保护的网页时识别自己的身份。 |
| Connection          | 这个头信息指示客户端是否可以处理持久 HTTP 连接。持久连接允许客户端或其他浏览器通过单个请求来检索多个文件。值 **Keep-Alive** 意味着使用了持续连接。 |
| Content-Length      | 这个头信息只适用于 POST 请求，并给出 POST 数据的大小（以字节为单位）。 |
| Cookie              | 这个头信息把之前发送到浏览器的 cookies 返回到服务器。        |
| Host                | 这个头信息指定原始的 URL 中的主机和端口。                    |
| If-Modified-Since   | 这个头信息表示只有当页面在指定的日期后已更改时，客户端想要的页面。如果没有新的结果可以使用，服务器会发送一个 304 代码，表示 **Not Modified** 头信息。 |
| If-Unmodified-Since | 这个头信息是 If-Modified-Since 的对立面，它指定只有当文档早于指定日期时，操作才会成功。 |
| Referer             | 这个头信息指示所指向的 Web 页的 URL。例如，如果您在网页 1，点击一个链接到网页 2，当浏览器请求网页 2 时，网页 1 的 URL 就会包含在 Referer 头信息中。 |
| User-Agent          | 这个头信息识别发出请求的浏览器或其他客户端，并可以向不同类型的浏览器返回不同的内容。 |

| 获得请求头信息相关 API                | 功能解释               |
| ------------------------------------- | ---------------------- |
| String getHeader(String headerName);  | 根据头名称获取请求头   |
| Enumeration<String> getHeaderNames(); | 获取所有的请求头名字   |
| String getContentType();              | 获取content-type请求头 |

```java
//示例 后端
@WebServlet("/servlet04")
public class Servlet04 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 直接根据键获取
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String[] hobbies = req.getParameterValues("hobby");
        System.out.println(username+"\n"+password+"\n"+ Arrays.toString(hobbies)+"\n");

        // 获取所有参数名后逐个输出
        Enumeration<String> pnames = req.getParameterNames();
        while(pnames.hasMoreElements()){
            String pname = pnames.nextElement();
            String[] values = req.getParameterValues(pname);
            if(values.length>1){
                System.out.println(pname+"="+Arrays.toString(values));
            }else{
                System.out.println(pname+"="+values[0]);
            }
        }

        System.out.println("\n");

        // 返回所有参数的map集合，用键值对回去值。
        Map<String,String[]> map = req.getParameterMap();
        Set<Map.Entry<String,String[]>> entries = map.entrySet(); // 遍历Map的方式
        for(Map.Entry<String,String[]> entry : entries){
            String pname = entry.getKey();
            String[] values = entry.getValue();
            if(values.length>1){
                System.out.println(pname+"="+Arrays.toString(values));
            }else{
                System.out.println(pname+"="+values[0]);
            }
        }

    }
}
```

```html
//示例 前端
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
<!-- 此处用的是get，用post效果相同 -->
<form method="get" action="servlet04">
    <input type="text" name="username"> <br>
    <input type="password" name="password"> <br>
    爱好:
    <input type="checkbox" name="hobby" value="1"> 篮球
    <input type="checkbox" name="hobby" value="2"> 足球
    <input type="checkbox" name="hobby" value="3"> 排球
    <input type="checkbox" name="hobby" value="4"> 铅球<br>

    <input type="submit">
</form>
</body>
</html>
```



| 其他 API                                     | 功能解释                    |
| -------------------------------------------- | --------------------------- |
| String getServletPath();                     | 获取请求的Servlet的映射路径 |
| ServletContext getServletContext();          | 获取ServletContext对象      |
| Cookie[] getCookies();                       | 获取请求中的所有cookie      |
| HttpSession getSession();                    | 获取Session对象             |
| void setCharacterEncoding(String encoding) ; | 设置请求体字符集            |



### HttpServletResponse 服务端 HTTP 响应

+ HttpServletResponse是一个接口,其父接口是ServletResponse
+ HttpServletResponse是Tomcat预先创建的,在Tomcat调用service方法时传入
+ HttpServletResponse代表对客户端的响应,该对象会被转换成响应的报文发送给客户端,通过该对象我们可以设置响应信息

| 头信息              | 描述                                                         |
| :------------------ | :----------------------------------------------------------- |
| Allow               | 这个头信息指定服务器支持的请求方法（GET、POST 等）。         |
| Cache-Control       | 这个头信息指定响应文档在何种情况下可以安全地缓存。可能的值有：**public、private** 或 **no-cache** 等。Public 意味着文档是可缓存，Private 意味着文档是单个用户私用文档，且只能存储在私有（非共享）缓存中，no-cache 意味着文档不应被缓存。 |
| Connection          | 这个头信息指示浏览器是否使用持久 HTTP 连接。值 **close** 指示浏览器不使用持久 HTTP 连接，值 **keep-alive** 意味着使用持久连接。 |
| Content-Disposition | 这个头信息可以让您请求浏览器要求用户以给定名称的文件把响应保存到磁盘。 |
| Content-Encoding    | 在传输过程中，这个头信息指定页面的编码方式。                 |
| Content-Language    | 这个头信息表示文档编写所使用的语言。例如，en、en-us、ru 等。 |
| Content-Length      | 这个头信息指示响应中的字节数。只有当浏览器使用持久（keep-alive）HTTP 连接时才需要这些信息。 |
| Content-Type        | 这个头信息提供了响应文档的 MIME（Multipurpose Internet Mail Extension）类型。 |
| Expires             | 这个头信息指定内容过期的时间，在这之后内容不再被缓存。       |
| Last-Modified       | 这个头信息指示文档的最后修改时间。然后，客户端可以缓存文件，并在以后的请求中通过 **If-Modified-Since** 请求头信息提供一个日期。 |
| Location            | 这个头信息应被包含在所有的带有状态码的响应中。在 300s 内，这会通知浏览器文档的地址。浏览器会自动重新连接到这个位置，并获取新的文档。 |
| Refresh             | 这个头信息指定浏览器应该如何尽快请求更新的页面。您可以指定页面刷新的秒数。 |
| Retry-After         | 这个头信息可以与 503（Service Unavailable 服务不可用）响应配合使用，这会告诉客户端多久就可以重复它的请求。 |
| Set-Cookie          | 这个头信息指定一个与页面关联的 cookie。                      |



| 设置响应行相关 API         | 功能解释       |
| -------------------------- | -------------- |
| void setStatus(int  code); | 设置响应状态码 |



| 设置响应头相关 API                                     | 功能解释                                         |
| ------------------------------------------------------ | ------------------------------------------------ |
| void setHeader(String headerName, String headerValue); | 设置/修改响应头键值对                            |
| void setContentType(String contentType);               | 设置content-type响应头及响应字符集(设置MIME类型) |



| 设置响应体相关 API                                        | 功能解释                                                |
| --------------------------------------------------------- | ------------------------------------------------------- |
| PrintWriter getWriter() throws IOException;               | 获得向响应体放入信息的字符输出流                        |
| ServletOutputStream getOutputStream() throws IOException; | 获得向响应体放入信息的字节输出流                        |
| void setContentLength(int length);                        | 设置响应体的字节长度,其实就是在设置content-length响应头 |



| 其他 API                                                     | 功能解释                                            |
| ------------------------------------------------------------ | --------------------------------------------------- |
| void sendError(int code, String message) throws IOException; | 向客户端响应错误信息的方法,需要指定响应码和响应信息 |
| void addCookie(Cookie cookie);                               | 向响应体中增加cookie                                |
| void setCharacterEncoding(String encoding);                  | 设置响应体字符集                                    |

> MIME类型
>
> + MIME类型,可以理解为文档类型,用户表示传递的数据是属于什么类型的文档
>
> + 浏览器可以根据MIME类型决定该用什么样的方式解析接收到的响应体数据
>
> + 可以这样理解: 前后端交互数据时,告诉对方发给对方的是 html/css/js/图片/声音/视频/... ...
>
> + tomcat/conf/web.xml中配置了常见文件的拓展名和MIMIE类型的对应关系
>
> + 常见的MIME类型举例如下
>
>   | 文件拓展名                  | MIME类型               |
>   | --------------------------- | ---------------------- |
>   | .html                       | text/html              |
>   | .css                        | text/css               |
>   | .js                         | application/javascript |
>   | .png /.jpeg/.jpg/... ...    | image/jpeg             |
>   | .mp3/.mpe/.mpeg/ ... ...    | audio/mpeg             |
>   | .mp4                        | video/mp4              |
>   | .m1v/.m1v/.m2v/.mpe/... ... | video/mpeg             |
>



```java
//样例

@WebServlet("/servlet06")
public class Servlet06 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String info = "<h1>Hello World</h1>";

        // 设置相应行相关的API  ->  HTTP/1.1 200/404/405/500/...
        resp.setStatus(200);    //设置200(成功)，即使没有找到资源也会返回该状态。设置404同理。

        // 设置响应头相关的API
        resp.setHeader("Content-Type", "text/html;charset=utf-8");   // 表明相应的内容
        resp.setHeader("Content-Length", String.valueOf(info.getBytes().length));                      // 标明返回内容的长度
            // 另一种写法
            //resp.setContentType("text/html;charset=utf-8");
            //resp.setContentLength(info.getBytes().length);

        // 设置响应体内容API
        // 获得一个向相应体中输出文本字符输出流
        PrintWriter out = resp.getWriter();
        out.write(info);

        // 获得一个向响应体中输入二进制信息的字节输出流
        // ServletOutputStream out = resp.getOutputStream();
    }

}
```



## Servlet 请求转发和响应重定向

### 简介

+ 请求转发和响应重定向是web应用中间接访问项目资源的两种手段,也是Servlet控制页面跳转的两种手段
+ 请求转发通过HttpServletRequest实现,响应重定向通过HttpServletResponse实现
+ 举例：
  + 请求转发：张三找李四借钱,李四没有,李四找王五,让王五借给张三
  + 响应重定向：张三找李四借钱,李四没有,李四让张三去找王五,张三自己再去找王五借钱





### 请求转发

![image-20240812151137709](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408121511784.png)

**特点（需记忆）**

+ 请求转发通过HttpServletRequest对象获取请求转发器实现
+ 请求转发是服务器内部的行为,对客户端是屏蔽的
+ 客户端只发送了一次请求,客户端地址栏不变
+ 服务端只产生了一对请求和响应对象,这一对请求和响应对象会继续传递给下一个资源
+ 因为全程只有一个HttpServletRequset对象,所以请求参数可以传递,请求域中的数据也可以传递
+ 请求转发可以转发给其他Servlet动态资源,也可以转发给一些静态资源以实现页面跳转
+ 请求转发**可以转发给WEB-INF**下受保护的资源
+ 请求转发不能转发到本项目以外的外部资源

| API                                              | 功能           |
| ------------------------------------------------ | -------------- |
| RequestDispatcher getRequestDispatcher(String)   | 获取请求转发器 |
| forward(HttpServletRequest, HttpServletResponse) | 发出转发动作   |

> 举例（整个过程中网址没变）
>
> ![image-20240812154321184](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408121543234.png)
>
> ```java
> @WebServlet("/servletA")
> public class ServletA extends HttpServlet {
>     @Override
>     protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
> 
>         String money = req.getParameter("money");
>         System.out.println("A被触发，获取参数money："+money);
> 
>         RequestDispatcher rd = req.getRequestDispatcher("/servletB");
>         rd.forward(req, resp);
>     }
> }
> ```
>
> ```java
> @WebServlet("/servletB")
> public class ServletB extends HttpServlet {
>     @Override
>     protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
>         String money = req.getParameter("money");
>         System.out.println("B被触发，获取参数money："+money); // B也能获取到parameter中的money
> 
>         //重定向到视图资源
>         System.out.println("B重定向到aaa.html"); //aaa随便创建一个html即可
>         RequestDispatcher rd = req.getRequestDispatcher("aaa.html");
>         rd.forward(req, resp);
>     }
> }
> ```
>
> 浏览器输入url
>
> ```http
> localhost:8080/demo04/servletA?money=1000
> ```
>
> ![image-20240812154445733](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408121544771.png)



### 响应重定向

![image-20240812162412316](https://raw.githubusercontent.com/Jenlybein/My-Typora-Images/imgs/202408121624392.png)

**特点（需记忆）**

+ 响应重定向通过HttpServletResponse对象的sendRedirect方法实现
+ 响应重定向是服务端通过302响应码和路径,告诉客户端自己去找其他资源,是在服务端提示下的,客户端的行为
+ 客户端至少发送了两次请求,客户端地址栏是要变化的
+ 服务端产生了多对请求和响应对象,且请求和响应对象不会传递给下一个资源
+ 因为全程产生了多个HttpServletRequset对象,所以请求参数不可以传递,请求域中的数据也不可以传递
+ 重定向可以是其他Servlet动态资源,也可以是一些静态资源以实现页面跳转
+ 重定向不可以到给WEB-INF下受保护的资源
+ 重定向可以到本项目以外的外部资源

> 举例（整个过程中网址没变）
>
> ```java
> @WebServlet("/servlet1")
> public class ServletA_redirect extends HttpServlet {
>     @Override
>     protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
>         //  获取请求参数
>         String username = req.getParameter("username");
>         System.out.println(username);
>         //  向请求域中添加数据
>         req.setAttribute("reqKey","requestMessage");
>         //  响应重定向
>         // 重定向到servlet动态资源 OK
>         resp.sendRedirect("servlet2");
>         // 重定向到视图静态资源 OK
>         //resp.sendRedirect("aaa.html");
>         // 重定向到WEB-INF下的资源 NO
>         //resp.sendRedirect("WEB-INF/views/view1");
>         // 重定向到外部资源 OK
>         //resp.sendRedirect("http://www.baidu.com");
>     }
> }
> ```
>
> ```java
> @WebServlet("/servlet2")
> public class ServletB extends HttpServlet {
>     @Override
>     protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
>         // 获取请求参数 (不可自动传递)
>         String username = req.getParameter("username");
>         System.out.println(username);
>         // 获取请求域中的数据
>         String reqMessage = (String)req.getAttribute("reqKey");
>         System.out.println(reqMessage);
>         // 做出响应
>         resp.getWriter().write("servlet2 response");        
>     }
> }
> ```
>
> 浏览器输入url
>
> ```http
> localhost:8080/demo04/servlet1?username=123
> ```
