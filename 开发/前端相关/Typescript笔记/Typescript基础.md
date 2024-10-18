[TOC]
------

<center><font size=7><b> Typescript基础——个人笔记 </center></font></center>

---



## 简介

TypeScript 是由微软开发并在2012年首次发布的一种开源编程语言。它是JavaScript的一个超集，主要提供了**静态类型**和面向对象的特性，从而增强了JavaScript的开发体验和代码质量。TypeScript代码可以编译成纯JavaScript，以便在任何浏览器、主机和操作系统上运行。

### 由来和发展历史

1. **背景**：JavaScript作为一种动态类型的脚本语言，虽然灵活且广泛应用于前端开发，但在大型项目中容易出现类型错误和维护困难的问题。随着Web应用的复杂性增加，开发者对更强类型检查和面向对象编程特性的需求也越来越强烈。
2. **微软的参与**：为了解决这些问题，微软在2012年发布了TypeScript。Anders Hejlsberg，C#语言的首席架构师，主导了TypeScript的设计。TypeScript的设计目标是补充JavaScript的不足，同时保持与现有JavaScript代码的兼容性。
3. **社区与生态系统**：自发布以来，TypeScript逐渐获得了广泛的认可和采用。许多流行的框架（如Angular）选择TypeScript作为主要开发语言，这进一步推动了TypeScript的普及和发展。

### 发展历程

1. **初期版本（2012-2014）**：TypeScript的早期版本主要引入了类型注解、类和模块等基础特性。这些特性让开发者可以开始在项目中使用TypeScript，享受类型检查带来的好处。
2. **广泛采用（2015-2017）**：随着TypeScript 1.5的发布，TypeScript引入了ES6的许多特性，如模块加载和解构赋值。这使得TypeScript更具吸引力，许多大型项目和框架开始采用TypeScript。
3. **成熟阶段（2018-至今）**：TypeScript 2.x和3.x版本引入了更多高级特性，如条件类型、映射类型、可选链操作符等。TypeScript的功能越来越强大，开发工具和社区支持也不断增强，使其成为现代前端开发的重要工具。



## 编译 TypeScript

浏览器不能直接运⾏ TypeScript 代码，需要编译为 JavaScript 再交由浏览器解析器执⾏。

### 命令⾏编译

要把 `.ts` 文件编译为 `.js` 文件，需要配置 TypeScript 的编译环境，步骤如下：

1. 安装 npm 包管理器：npm 是 Node.js 的默认包管理器，因此安装 Node.js 时会自动安装 npm。

2. 全局安装 TypeScript：

   ```bash
   npm install -g typescript
   ```

3. 创建一个 demo.ts 文件：在项目目录中创建一个以 `.ts` 结尾的文件，例如 `demo.ts`。

4. 使用命令编译 .ts 文件：

   ```bash
   tsc demo.ts
   ```

### ⾃动化编译

1. 创建 TypeScript 编译控制⽂件

   ```bash
   tsc --init
   ```

   1. ⼯程中会⽣成⼀个 tsconfig.json 配置⽂件，其中包含着很多编译时的配置。 
   2. 观察发现，默认编译的 JS 版本是 es2016，可以⼿动调整为其他版本。

2. 监视⽬录中的 .ts ⽂件变化

   ```bash
   tsc --watch 
   #或
   tsc -w
   ```

3. 设置：编译出错时不⽣成 .js ⽂件

   ```bash
   tsc --noEmitOnError --watch
   ```

   备注：当然也可以修改 tsconfig.json 中的 noEmitOnError 配置



## 类型总览

### JavaScript 中的数据类型

八大基础数据类型：

1. `number`：用于数字，包括整数和浮点数。
2. `string`：用于文本字符串。
3. `boolean`：用于布尔值，表示真（`true`）或假（`false`）。
4. `null` 和 `undefined`：分别表示空值和未定义的值。
5. `bigint`：用于表示任意精度的整数。
6. `symbol`：用于创建唯一且不可变的值。
7. `object`：用于表示复杂的数据结构。`object` 包含以下类型：`Array`、`Function`、`Date`、`Error` 等。

### TypeScript 中的数据类型

1. 支持所有上述 JavaScript 类型。
2. 新增六种类型：
   1. `any`：表示任意类型，关闭类型检查功能。
   2. `void`：表示没有任何类型，通常用于函数没有返回值的情况。
   3. `unknown`：表示未知类型，类似 `any` 但更安全。
   4. `never`：表示永不存在的值类型，通常用于抛出错误的函数或无限循环。
   5. `tuple`：表示固定长度和已知类型的数组。
   6. `enum`：用于定义一组命名常量。
3. 两种用于自定义类型的方法：
   1. `type`：用于创建类型别名。
   2. `interface`：用于定义对象的结构。



## 类型声明

TypeScript 的类型声明是其核心特性之一，允许开发者显式地定义变量、函数参数和返回值的类型，从而增强代码的可读性和可维护性。

使⽤ `:` 来对变量或函数形参，进⾏类型声明。



### 类型推断

TS 会根据我们的代码，进⾏类型推导。

```ts
let d = -99 //TypeScript会推断出变量d的类型是数字
d = false //警告：不能将类型“boolean”分配给类型“number"
```

注意，类型推断不是万能的，⾯对复杂类型时推断容易出问题，所以尽量还是明确的编写类型声明！



### 基本类型声明

```ts
let a: string //变量a只能存储字符串
let b: number //变量b只能存储数值
let c: boolean //变量c只能存储布尔值
let data: any = { name: "李四" }; // 允许任何类型
```

在 `:` 后也可以写字⾯量类型，不过实际开发中⽤的不多。

```ts
let a: '你好' //a的值只能为字符串“你好”
let b: 100 //b的值只能为数字100
a = '欢迎'//警告：不能将类型“"欢迎"”分配给类型“"你好"”
b = 200 //警告：不能将类型“200”分配给类型"100"
```



## 常用类型

### any

**解释**：`any` 类型表示任意类型，关闭类型检查的功能。使用 `any` 类型后，该变量可以赋值为任何类型的值。

**使用场景**：通常用于处理现有的 JavaScript 代码或者需要动态类型的场景，但应尽量避免使用 `any`，以保持 TypeScript 的类型安全性

```ts
// 明确的表示a的类型是 any —— 显式的any
let a: any
// 以下对a的赋值，均⽆警告
a = 100
a = '你好'
a = false

// 没有明确的表示b的类型是any，但TS主动推断出来b是any —— 隐式的any
let b
//以下对b的赋值，均⽆警告
b = 100
b = '你好'
b = fals
```

注意： any 类型的变量，可以赋值给任意类型的变量。这意味着会破坏其他变量的类型检查。

```ts
let c:any
c = 9

let x: string
x = c // ⽆警告
```



### unknow

**解释**：`unknown` 类型也是表示任意类型，但比 `any` 更安全。使用 `unknown` 类型的值必须经过类型检查或类型断言才能进行具体的操作。

**使用场景**：适用于需要保存任意类型的值，但在使用这些值之前需要进行类型检查的场景。

```ts
// 设置a的类型为unknown
let a: unknown

//以下对a的赋值，均符合规范
a = 100
a = false
a = '你好'

// 设置x的数据类型为string
let x: string
x = a //警告：不能将类型“unknown”分配给类型“string”


//第⼀种⽅式：加类型判断
if(typeof a === 'string'){
 x = a
 console.log(x)
}

//第⼆种⽅式：加断⾔
x = a as string

//第三种⽅式：加断⾔
x = <string>a
```



### never

**解释**：`never` 类型表示永远不会有值的类型（ `undefined` 、 `null` 、 `''` 、 `0` 都不行），通常用于那些永远不会返回的函数（比如抛出异常或死循环的函数）。

**使用场景**：用于标识函数永远不会有返回值，或代码不可能执行到的地方。

never ⼀般是 TypeScript 主动推断出来的，例如：

```ts
// 指定a的类型为string
let a: string
// 给a设置⼀个值
a = 'hello'
if (typeof a === 'string') {
    console.log(a.toUpperCase())
} else {
    console.log(a) // TypeScript会推断出此处的a是never，因为没有任何⼀个值符合此处的逻辑
}
```

never 也可⽤于限制函数的返回值：

```ts
// 限制throwError函数不需要有任何返回值，任何值都不⾏，像undeifned、null都不⾏
function throwError(str: string): never {
    throw new Error('程序异常退出:' + str)
}
```



### void

**解释**：`void` 类型表示没有任何类型，通常用于函数没有返回值的情况。

**使用场景**：void 通常⽤于函数返回值声明，用于函数没有返回任何值的场景。

注意：若编码者没有编写 return 指定函数返回值，会有⼀个隐式返回值 ，是 undefined 。虽然函数返回类型为 void ，但也是可以接受 返回值为 undefined。

以下写法均符合规范：

```ts
// ⽆警告
function logMessage(msg:string):void{
 console.log(msg)
}
// ⽆警告
function logMessage(msg:string):void{
 console.log(msg)
 return;
}
// ⽆警告
function logMessage(msg:string):void{
 console.log(msg)
 return undefined
}

logMessage("你好")
```

限制函数返回值时，`undefined` 和 `void` 存在区别。

虽然它们都表示一种“空”的状态，但语义和使用场景有所不同。具体来说：

- `void` 表示一种广泛的“空”的概念，常用于表示函数没有返回值。**返回值类型为 void 的函数，调⽤者不应依赖其返回值进⾏任何操作。**
- `undefined` 是 `void` 能接受的一种“空”的具体实现，但 `void` 的语义超越了 `undefined`，是意图上的约定，而不仅仅是特定值的限制。

如果⼀个函数返回类型为 void ，那么： 

1. 从语法上讲：函数可以显式/隐式返回 undefined 。
2. 从语义上讲：函数调⽤者不应关⼼函数返回的值，也不应依赖返回值进⾏任何操作，即使我们知道它返回了 undefined 。



### object 和 Object

**使用场景**：用于表示复杂的对象类型。实际开发中⽤的相对较少，因为范围太⼤。

`Object` 是 JavaScript 中的构造函数类型。所有 JavaScript 对象（包括数组和函数）都从 `Object` 继承属性和方法。`Object` 类型描述了所有 JavaScript 值的基类。Object 包括除了 undefined 和 null 的任何值。

```ts
let b:Object //b的值必须是Object的实例对象（除去undefined和null的任何值）

// 以下代码，均⽆警告，因为给a赋的值，都是Object的实例对象
b = {}
b = {name:'张三'}
b = [1,3,5,7,9]
b = function(){}
b = new String('123')
class Person {}
b = new Person()
b = 1 // 1不是Object的实例对象，但其包装对象是Object的实例
b = true // truue不是Object的实例对象，但其包装对象是Object的实例
b = '你好' // “你好”不是Object的实例对象，但其包装对象是Object的实例

// 以下代码均有警告
b = null // 警告：不能将类型“null”分配给类型“Object”
b = undefined // 警告：不能将类型“undefined”分配给类型“Object"
```

`object` 是 TypeScript 引入的新类型，表示非原始类型。它排除了 `number`、`string`、`boolean`、`symbol`、`null` 和 `undefined` 等原始类型，表示所有的非原始类型（即对象类型）。

```ts
let a:object //a的值可以是任何【⾮原始类型】，包括：对象、函数、数组等

// 以下代码，是将【⾮原始类型】赋给a，所以均符合要求
a = {}
a = {name:'张三'}
a = [1,3,5,7,9]
a = function(){}
a = new String('123')
class Person {}
a = new Person()

// 以下代码，是将【原始类型】赋给a，有警告
a = 1 // 警告：不能将类型“number”分配给类型“object”
a = true // 警告：不能将类型“boolean”分配给类型“object”
a = '你好' // 警告：不能将类型“string”分配给类型“object” 
a = null // 警告：不能将类型“null”分配给类型“object”
a = undefined // 警告：不能将类型“undefined”分配给类型“object"
```



#### 声明对象类型

- 实际开发中，限制⼀般对象，通常使⽤以下形式：（有`?`则为可选属性）

  ```ts
  // 限制person1对象必须有name属性，age为可选属性
  let person1: { name: string, age?: number }
  
  // 含义同上，也能⽤分号做分隔
  let person2: { name: string; age?: number }
  
  // 含义同上，也能⽤换⾏做分隔
  let person3: {
      name: string
      age?: number
  }
  
  // 如下赋值均可以
  person1 = {name:'李四',age:18}
  person2 = {name:'张三'}
  person3 = {name:'王五'}
  
  // 如下赋值不合法，因为person3的类型限制中，没有对gender属性的说明
  person3 = {name:'王五',gender:'男'}
  ```

- **索引签名**： 允许定义对象可以具有任意数量的属性，这些属性的键和类型是可变的， 常⽤于：描述类型不确定的属性，（具有动态属性的对象）。

  ```ts
  // 限制person对象必须有name属性，可选age属性但值必须是数字，同时可以有任意数
  量、任意类型的其他属性
  let person: {
      name: string
      age?: number
      [key: string]: any // 索引签名，完全可以不⽤key这个单词，换成其他的也可以
  }
  // 赋值合法
  person = {
      name:'张三',
      age:18,
      gender:'男'
  }
  ```



#### 声明函数类型

- <u>TypeScript</u> 中的 => 在函数类型声明时表示**函数类型**，描述其**参数类型**和**返回类型**。 
- <u>JavaScript</u> 中的 => 是⼀种定义函数的语法，是具体的函数实现。 
- 函数类型声明还可以使⽤：接⼝、⾃定义类型等⽅式。

```ts
let count: (a: number, b: number) => number
count = function (x, y) {
    return x + y
}
```



#### 声明数组类型

```ts
let arr1: string[]
let arr2: Array<string> // 泛型
arr1 = ['a','b','c']
arr2 = ['hello','world']
```



### tuple（元组）

元组 (`Tuple`) 是⼀种特殊的**数组类型**，可以存储**固定数量**的元素，并且每个元素的类型是已知的且可以不同。元组⽤于精确描述⼀组值的类型， `?` 表示可选元素。

```ts
// 第⼀个元素必须是 string 类型，第⼆个元素必须是 number 类型。
let arr1: [string,number]
// 第⼀个元素必须是 number 类型，第⼆个元素是可选的，如果存在，必须是 boolean 类型。
let arr2: [number,boolean?]
// 第⼀个元素必须是 number 类型，后⾯的元素可以是任意数量的 string 类型
let arr3: [number,...string[]]

// 可以赋值
arr1 = ['hello',123]
arr2 = [100,false]
arr2 = [200]
arr3 = [100,'hello','world']
arr3 = [100]

// 不可以赋值，arr1声明时是两个元素，赋值的是三个
arr1 = ['hello',123,false]
```



### enum（枚举）

`enum`（枚举）类型用于定义一组命名的常量。枚举成员可以是数字或字符串。它能增强代码的可读性，也让代码更好维护。

如下代码的功能是：根据调⽤ `walk` 时传⼊的不同参数，执⾏不同的逻辑。
	存在的问题是调⽤ `walk` 时传参时没有任何提示，编码者很容易写错字符串内容；并且⽤于判断逻辑的 up 、 dow n 、 left 、 right 是连续且相关的⼀组值，那此时就特别适合使⽤ 枚举（ enum ）。

```ts
function walk(str:string) {
    if (str === 'up') {
        console.log("向【上】⾛");
    } else if (str === 'down') {
        console.log("向【下】⾛");
    } else if (str === 'left') {
        console.log("向【左】⾛");
    } else if (str === 'right') {
        console.log("向【右】⾛");
    } else {
        console.log("未知⽅向");
    }
}
walk('up')
walk('down')
walk('left')
walk('right')
```

1. **数字枚举**

   数字枚举⼀种最常⻅的枚举类型，其成员的值会**⾃动递增**。且数字枚举还具备**反向映射**的特点：可以通过值来获取对应的枚举成员名称 。

   ```ts
   // 定义⼀个描述【上下左右】⽅向的枚举Direction
   enum Direction {
       Up,
       Down,
       Left,
       Right
   }
   
   console.log(Direction) // 打印Direction会看到如下内容
   /* 
    {
        0:'Up', 
        1:'Down', 
        2:'Left', 
        3:'Right', 
        Up:0, 
        Down:1, 
        Left:2,
        Right:3
    } 
   */
   
   // 反向映射
   console.log(Direction.Up)
   console.log(Direction[0])
   // 此⾏代码报错，枚举中的属性是只读的
   Direction.Up = 'shang'
   ```

   也可以指定枚举成员的初始值，其后的成员值会⾃动递增。

   ```ts
   enum Direction {
       Up = 6,
       Down,
       Left,
       Right
   }
   console.log(Direction.Up); // 输出: 6
   console.log(Direction.Down); // 输出: 7
   ```

   使⽤数字枚举完成刚才 walk 函数中的逻辑，此时我们发现： 代码更加直观易读，⽽且类型安全，同时也更易于维护。

   ```ts
   enum Direction {
       Up,
       Down,
       Left,
       Right,
   }
   function walk(n: Direction) {
       if (n === Direction.Up) {
           console.log("向【上】⾛");
       } else if (n === Direction.Down) {
           console.log("向【下】⾛");
       } else if (n === Direction.Left) {
           console.log("向【左】⾛");
       } else if (n === Direction.Right) {
           console.log("向【右】⾛");
       } else {
           console.log("未知⽅向");
       }
   }
   walk(Direction.Up)
   walk(Direction.Down)
   ```

   

2. **字符串枚举**

   枚举成员的值是字符串

   ```ts
   enum Direction {
       Up = "up",
       Down = "down",
       Left = "left",
       Right = "right"
   }
   let dir: Direction = Direction.Up;
   console.log(dir); // 输出: "up"
   ```

   

3. **常量枚举**

   官⽅描述：常量枚举是⼀种特殊枚举类型，它使⽤ const 关键字定义，在编译时会被 **内联**，避免⽣成⼀些额外的代码。

   所谓“内联”其实就是 TypeScript 在编译时，会将枚举成员引⽤替换为它们的实际值，⽽不是⽣成额外的枚举对象。这可以减少⽣成的 JavaScript 代码量，并提⾼运⾏时性能。

   使⽤**普通枚举**的 TypeScript 代码如下：

   ```ts
   enum Directions {
       Up,
       Down,
       Left,
       Right
   }
   let x = Directions.Up;
   ```

   编译后⽣成的 JavaScript 代码量较⼤ ：

   ```ts
   "use strict";
   var Directions;
   (function (Directions) {
       Directions[Directions["Up"] = 0] = "Up";
       Directions[Directions["Down"] = 1] = "Down";
       Directions[Directions["Left"] = 2] = "Left";
       Directions[Directions["Right"] = 3] = "Right";
   })(Directions || (Directions = {}));
   
   let x = Directions.Up;
   ```

   使⽤常量枚举（加上const）的 TypeScript 代码如下：

   ```ts
   const enum Directions {
       Up,
       Down,
       Left,
       Right
   }
   let x = Directions.Up;
   ```

   编译后⽣成的 JavaScript 代码量较⼩：

   ```ts
   "use strict";
   let x = 0 /* Directions.Up */;
   ```

   

### type

在 TypeScript 中，`type` 关键字用于创建类型别名。类型别名是一种为类型指定一个新名称的方式，使得代码更加简洁、可读和易于维护。`type` 关键字可以用于简单类型和复杂类型的组合，包括原始类型、联合类型、交叉类型、对象类型、函数类型等。

1. **基本用法**

   **类型别名**使⽤ type 关键字定义， type 后跟类型名称。

   1. **变量类型**

      ```ts
      type num = number;
      
      let price: num
      price = 100
      ```

   2. **对象类型**

      ```ts
      type User = {
          name: string;
          age: number;
          isActive: boolean;
      };
      
      const user: User = {
          name: "李四",
          age: 25,
          isActive: true,
      };
      ```

   3. **函数类型**

      ```ts
      type Add = (a: number, b: number) => number;
      
      const add: Add = (x, y) => x + y;
      ```

      

      **特殊情况**：使⽤<u>类型声明</u>限制函数返回值为 void 时， TypeScript 并不会严格要求函数返回空。

      ```typescript
      type LogFunc = () => void
      const f1: LogFunc = () => {
          return 100; // 允许返回⾮空值
      };
      
      const f2: LogFunc = () => 200; // 允许返回⾮空值
      const f3: LogFunc = function () {
          return 300; // 允许返回⾮空值
      };
      ```

      **原因**：是为了确保如下代码成⽴，我们知道 Array.prototype.push 的返回值是⼀个数字， ⽽ Array.prototype.forEach ⽅法期望其回调的返回类型是 void 。

      ```ts
      const src = [1, 2, 3];
      const dst = [0];
      src.forEach((el) => dst.push(el));
      ```

      

   4. **数组类型**

      ```ts
      type StringArray = string[];
      type NumberArray = Array<number>;
      
      const names: StringArray = ["Alice", "Bob", "Charlie"];
      const numbers: NumberArray = [1, 2, 3, 4, 5];
      ```

   

2. **联合类型**

   联合类型是⼀种⾼级类型，它表示⼀个值可以是⼏种不同类型之⼀。使用管道符`|`将类型连接。

   ```ts
   type Status = number | string
   
   function printStatus(status: Status) {
       console.log(status);
   }
   
   printStatus(404);
   printStatus('200');
   printStatus('501');
   ```

   **字面量类型**

   类型别名可以包含字面量类型：

   ```ts
   type Gender = '男' | '⼥'
   
   function logGender(str:Gender){
       console.log(str)
   }
   
   'logGender('男')
   logGender('⼥')
   ```

   

3. **交叉类型**

   交叉类型（Intersection Types）允许将多个类型合并为⼀个类型。合并后的类型将拥有所有被合并类型的成员。

   交叉类型将两个类型用`&`进行连接。

   交叉类型通常⽤于对象类型。

   ```ts
   //⾯积
   type Area = {
       height: number; //⾼
       width: number; //宽
   };
   //地址
   type Address = {
       num: number; //楼号
       cell: number; //单元号
       room: string; //房间号
   };
   
   // 定义类型House，且House是Area和Address组成的交叉类型
   type House = Area & Address;
   
   const house: House = {
       height: 180,
       width: 75,
       num: 6,
       cell: 3,
       room: '702'
   };
   ```

   



## 类（class）

### 类组成成分

**JavaScript 原有**：

1. **类名**：定义类的名称，用于唯一标识类。
2. **构造函数**：在创建类的实例时自动调用，用于初始化对象。
3. **方法**：定义对象的行为，类的实例方法。
4. **静态成员**：使用 `static` 关键字定义，可以直接通过类名访问。
5. **访问器（getter 和 setter）**：使用 `get` 和 `set` 关键字，为类属性定义访问器和设置器。
6. **继承**：使用 `extends` 关键字实现，可以创建一个类的子类，子类继承父类的属性和方法。

**TypeScript 新增**：

1. **属性（成员变量）**：直接在类中定义属性，并可选择性地进行类型声明。
2. **访问修饰符**：使用 `public`、`private` 和 `protected` 关键字控制类成员的可见性。
3. **抽象类和抽象方法**：使用 `abstract` 关键字定义抽象类和抽象方法，抽象类不能被实例化，只能被继承，抽象方法必须在派生类中实现。
4. **接口实现**：使用 `implements` 关键字实现一个或多个接口，接口定义类的结构，包括属性和方法的类型。



### 属性修饰符

若属性未显示标示属性修饰符，则默认为public。

| 修饰符    | 含义     | 具体规则                            |
| --------- | -------- | ----------------------------------- |
| public    | 公开的   | 可以被：类内部、⼦类、类外部访问 。 |
| protected | 受保护的 | 可以被：类内部、⼦类访问。          |
| private   | 私有的   | 可以被：类内部访问。                |
| readonly  | 只读属性 | 属性⽆法修改。（ts对象中的特性）    |
| ?         | 可选属性 | 属性可选。（ts对象中的特性）        |

以下是一个综合展示属性修饰符（`public`、`private`、`protected`）、`readonly` 属性以及可选属性 (`?`) 的示例。

```ts
class Car {
  public brand: string; // 公共属性
  private year: number; // 私有属性
  protected color: string; // 受保护的属性
  public readonly model: string; // 只读属性
  public owner?: string; // 可选属性

  constructor(
    brand: string,
    year: number,
    color: string,
    model: string,
    owner?: string
  ) {
    this.brand = brand;
    this.year = year;
    this.color = color;
    this.model = model;
    this.owner = owner;
  }

  // 公共方法，可以访问所有公共和受保护的属性
  public getCarInfo(): string {
    return `${this.brand} ${this.model} (${this.year}), Color: ${this.color}`;
  }

  // 受保护的方法，只能在类内部和子类中访问
  protected getCarAge(): number {
    const currentYear = new Date().getFullYear();
    return currentYear - this.year;
  }

  // 私有方法，只能在类内部访问
  private logCarDetails(): void {
    console.log(this.getCarInfo());
  }
}

// 创建一个 Car 对象实例
const myCar = new Car("Toyota", 2010, "Red", "Corolla", "John Doe");

console.log(myCar.getCarInfo()); // 输出：Toyota Corolla (2010), Color: Red
console.log(myCar.brand); // 可以访问公共属性
// console.log(myCar.year); // 错误：无法访问私有属性
// console.log(myCar.color); // 错误：无法访问受保护属性
console.log(myCar.model); // 可以访问只读属性
console.log(myCar.owner); // 可以访问可选属性，如果未定义则为 undefined

// myCar.model = 'Camry'; // 错误：无法修改只读属性
// myCar.logCarDetails(); // 错误：无法访问私有方法
```



### 类的简写形式

在 TypeScript 中，可以在构造函数的参数中直接使用访问修饰符（`public`、`private`、`protected`），来定义和初始化类的属性。这种方式避免了在构造函数中显式声明和赋值属性。

```ts
class Car {
  // 通过简写形式定义和初始化属性
  constructor(
    public brand: string, // 公共属性
    private year: number, // 私有属性
    protected color: string, // 受保护的属性
    public readonly model: string, // 只读属性
    public owner?: string // 可选属性
  ) {}

  public getCarInfo(): string {
    return `${this.brand} ${this.model} (${this.year}), Color: ${this.color}`;
  }

  protected getCarAge(): number {
    const currentYear = new Date().getFullYear();
    return currentYear - this.year;
  }

  private logCarDetails(): void {
    console.log(this.getCarInfo());
  }
}
```



###  抽象类 abstract

抽象类在 TypeScript 中是一种特殊的类，不能被实例化，主要用于定义一个基类，以供其他类继承。

抽象类通常包含**抽象方法**和**非抽象方法**，抽象方法必须在派生类中实现。

通过抽象类，可以在派生类中强制实现某些方法，从而实现代码的复用和设计模式中的模板方法模式。

#### 何时使⽤抽象类

1. **定义通用接口** ：为⼀组相关的类定义通⽤的⾏为（⽅法或属性）时。
2. **提供基础实现** ：在抽象类中提供某些⽅法或为其提供基础实现，这样派⽣类就可以继承这些实现。
3. **确保关键实现** ：强制派⽣类实现⼀些关键⾏为。
4. **共享代码和逻辑**：当多个类需要共享部分代码时，抽象类可以避免代码重复。

#### 示例

定义⼀个抽象类 Package ，表示所有包裹的基本结构：

1. 任何包裹都有重量属性 weight 
2. 包裹都需要计算运费。但不同类型的包裹（如：标准速度、特快专递）有不同的运费计算⽅式，因此⽤于计算运费的 calculate ⽅法是⼀个抽象⽅法，必须由具体的⼦类来实现。 

```ts
abstract class Package {
  //构造方法
  constructor(public weight: number) {}
  // 抽象⽅法：⽤来计算运费，不同类型包裹有不同的计算⽅式
  abstract calculate(): number;
  // 通⽤⽅法：打印包裹详情
  printPackage() {
    console.log(`包裹重量为: ${this.weight}kg，运费为: ${this.calculate()}元`);
  }
}

// 标准包裹 StandardPackage 类继承了 Package ，实现了 calculate ⽅法。
class StandardPackage extends Package {
  constructor(
    weight: number,
    public unitPrice: number // 每公⽄的固定费率
  ) {
    super(weight);
  }

  // 实现抽象⽅法：计算运费
  calculate(): number {
    return this.weight * this.unitPrice;
  }
}

// 创建标准包裹实例
const s1 = new StandardPackage(10, 5);
s1.printPackage();
```



## 接口（interface）

接口（Interface）是 TypeScript 中一种用来定义对象的结构和约束的机制。

接口可以定义属性、方法和索引签名，但不能包含具体的实现。

接口在面向对象编程中主要用于定义类的结构，并用于类型检查和代码复用。可以确保代码的⼀致性和类型安全。



### 接口定义

接口可以对类、对象、函数的结构进行定义。

### 何时使⽤接⼝

1. **定义对象的格式**： 描述数据模型、API 响应格式、配置对象........等等，是开发中⽤的最多的场景。
2. **类的契约**：规定⼀个类需要实现哪些属性和⽅法。
3. **扩展已有接⼝**：⼀般⽤于扩展第三⽅库的类型， 这种特性在⼤型项⽬中可能会⽤到。

- **定义类结构**

  ```ts
  // PersonInterface接⼝，⽤与限制Person类的格式
  interface PersonInterface {
    name: string;
    age: number;
    speak(n: number): void;
  }
  
  // 定义⼀个类 Person，实现 PersonInterface 接⼝
  class Person implements PersonInterface {
    constructor(public name: string, public age: number) {}
    // 实现接⼝中的 speak ⽅法
    speak(n: number): void {
      for (let i = 0; i < n; i++) {
        // 打印出包含名字和年龄的问候语句
        console.log(`你好，我叫${this.name}，我的年龄是${this.age}`);
      }
    }
  }
  
  // 创建⼀个 Person 类的实例 p1，传⼊名字 'tom' 和年龄 18
  const p1 = new Person("tom", 18);
  p1.speak(3);
  ```

- **定义对象结构**

  ```ts
  interface UserInterface {
    name: string;
    readonly gender: string; // 只读属性
    age?: number; // 可选属性
    run: (n: number) => void;
  }
  const user: UserInterface = {
    name: "张三",
    gender: "男",
    age: 18,
    run(n) {
      console.log(`奔跑了${n}⽶`);
    },
  };
  ```

- **定义函数结构**

  ```ts
  interface CountInterface {
    (a: number, b: number): number;
  }
  const count: CountInterface = (x, y) => {
    return x + y;
  };
  ```



### 接⼝继承

```ts
interface PersonInterface {
  name: string; // 姓名
  age: number; // 年龄
}
interface StudentInterface extends PersonInterface {
  grade: string; // 年级
}
const stu: StudentInterface = {
  name: "张三",
  age: 25,
  grade: "⾼三",
};
```



### 接⼝⾃动合并（可重复定义）

接口同名(重复定义)时，新定义的接口不会覆盖旧定义的接口，也不会出现报错和冲突，而是两个接口的内容合并。

```ts
// PersonInterface接⼝
interface PersonInterface {
  // 属性声明
  name: string;
  age: number;
}
// 给PersonInterface接⼝添加新属性
interface PersonInterface {
  // ⽅法声明
  speak(): void;
}
// Person类实现PersonInterface
class Teacher implements PersonInterface {
  name: string;
  age: number;
  // 构造器
  constructor(name: string, age: number) {
    this.name = name;
    this.age = age;
  }
  // ⽅法
  speak() {
    console.log("你好！我是⽼师:", this.name);
  }
}
```



## 相似概念的区别

### interface 与 type 的区别

**相同点**： 

interface 和 type 都可以⽤于定义对象结构，在定义对象结构时两者可以互换。 

**不同点**：

1. `interface` ：更专注于定义对象和类的结构，⽀持继承、合并。
2. `type` ：可以定义类型别名、联合类型、交叉类型，但不⽀持继承和⾃动合并。

使用type进行合并，则需使用交叉类型：

```ts
type PersonType = {
  name: string; // 姓名
  age: number; // 年龄
} & {
  speak: () => void;
};
// 使⽤ type 定义 Student 类型，并通过交叉类型继承 PersonType
type StudentType = PersonType & {
  grade: string; // 年级
};
const student: StudentType = {
  name: "李四",
  age: 18,
  grade: "⾼⼆",
  speak() {
    console.log(this.name, this.age, this.grade);
  },
};
```



### interface 与 abstract 的区别

**相同点**：

都能定义⼀个类的格式（定义类应遵循的契约）

**不同点**：

1. 接⼝：只能描述结构，不能有任何实现代码，⼀个类可以实现多个接⼝。
2. 抽象类：既可以包含抽象⽅法，也可以包含具体⽅法， ⼀个类只能继承⼀个抽象类。





## 泛型（Generics）

泛型允许我们在定义函数、类或接⼝时，使⽤类型参数来表示**未指定的类型**，这些参数在具体使⽤时，才被指定具体的类型，泛型能让同⼀段代码适⽤于多种类型，同时仍然保持类型的安全性。

### 泛型的作用

1. 代码重用：泛型使得一个函数、类或接口可以处理不同类型的数据，避免重复编写相同逻辑的代码。
2. 类型安全：泛型可以在编译时进行类型检查，确保传入的参数类型一致，减少运行时错误。
3. 灵活性和可扩展性：泛型使得代码更加灵活和可扩展，适用于更多的场景。

### 泛型的实际应用

- 泛型函数：实现通用的函数逻辑，如数组处理函数。
- 泛型类：实现通用的数据结构，如集合、栈、队列等。
- 泛型接口：定义通用的接口，如数据访问接口。
- 泛型工具类型：用于构造新的类型，简化类型定义。

### 泛型语法

泛型参数通常用尖括号 `<>` 包围，并放在对象名之后。

- **泛型函数**

  ```ts
  function identity<T>(arg: T): T {
      return arg;
  }
  
  let output1 = identity<string>("myString");  // 显式指定类型
  let output2 = identity<number>(123);         // 显示指定类型
  let output3 = identity("myString");          // 类型推断
  ```

- **泛型接口**

  ```ts
  interface PersonInterface<T> {
   name: string,
   age: number,
   extraInfo: T
  }
  let p1: PersonInterface<string>
  let p2: PersonInterface<number>
  p1 = { name: '张三', age: 18, extraInfo: '⼀个好⼈' }
  p2 = { name: '李四', age: 18, extraInfo: 250 }
  ```

- **泛型类**

  ```ts
  class Person<T> {
    constructor(public name: string, public age: number, public extraInfo: T) {}
    speak() {
      console.log(`我叫${this.name}今年${this.age}岁了`);
      console.log(this.extraInfo);
    }
  }
  
  // 测试代码1
  const p1 = new Person<number>("tom", 30, 250);
  
  // 测试代码2
  type JobInfo = {
    title: string;
    company: string;
  };
  const p2 = new Person<JobInfo>("tom", 30, {
    title: "研发总监",
    company: "发发发科技公司",
  });
  ```

- **补充**

  - **多个泛型**

    ```ts
    function logData<T, U>(data1: T, data2: U): T | U {
        console.log(data1,data2)
        return Date.now() % 2 ? data1 : data2
    }
    logData<number, string>(100, 'hello')
    logData<string, boolean>('ok', false)
    ```

  - **泛型约束**

    ```ts
    interface LengthInterface {
        length: number;
    }
    // 约束规则是：传⼊的类型T必须具有 length 属性
    function logPerson<T extends LengthInterface>(data: T): void {
        console.log(data.length);
    }
    logPerson<string>("hello");
    // logPerson<number>(100)  报错：因为number不具备length属性
    ```





## 类型声明⽂件（Type Declaration Files）

类型声明⽂件是 TypeScript 中的⼀种特殊⽂件，通常以 `.d.ts` 作为扩展名。

主要作⽤：可以用于声明模块、函数、变量等的类型，为现有的 JavaScript 代码提供类型信息，使得 TypeScript 能够在使⽤这些 JavaScript 库或模块时进⾏类型检查和提示。



### 类型声明文件的命名规则

- 如果是针对某个库的类型声明文件，使用 `库名.d.ts` 进行命名。

### 类型声明文件的结构

类型声明文件的基本结构包括：

- **`declare` 关键字**：用于声明一个全局变量、函数、模块或命名空间。
- **类型注释**：描述变量、函数、参数、返回值等的类型。

### 使用类型声明文件

类型声明文件通常包含以下内容：

1. **全局变量和类型声明**：

   ```ts
   declare const myGlobalVar: string;
   declare function myGlobalFunction(param: number): void;
   ```

2. **模块声明**：

   ```ts
   declare module "my-module" {
       export function myFunction(param: string): number;
   }
   ```

3. **命名空间声明**：

   ```ts
   declare namespace MyNamespace {
       function myFunction(param: boolean): void;
   }
   ```

**示例**：

- demo.js

  ```ts
  export function add(a, b) {
      return a + b;
  }
  export function mul(a, b) {
      return a * b;
  }
  ```

- demo.d.ts

  ```ts
  declare function add(a: number, b: number): number;
  declare function mul(a: number, b: number): number;
  export { add, mul };
  ```

- index.ts

  ```ts
  // example.ts
  import { add, mul } from "./demo.js";
  const x = add(2, 3); // x 类型为 number
  const y = mul(4, 5); // y 类型为 number
  console.log(x,y)
  ```



### 包含类型声明文件的项目配置

在 `tsconfig.json` 文件中，可以使用 `include` 或 `files` 字段来指定包含的类型声明文件。例如：

```json
{
    "compilerOptions": {
        "target": "es6",
        "module": "commonjs"
    },
    "include": [
        "src/**/*",
        "typings/**/*"  // 包含类型声明文件
    ]
}
```



### 社区提供的类型声明

TypeScript 社区为许多流行的 JavaScript 库提供了类型声明，可以通过 `DefinitelyTyped` 项目获得。

这些声明通常以 `@types` 前缀的形式发布在 npm 上。

例如，安装 jQuery 的类型声明可以使用以下命令：

```bash
npm install --save-dev @types/jquery
```