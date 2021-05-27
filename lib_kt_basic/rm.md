# Kotlin基础

## 基本要素：函数和变量

下面展示Kotlin如何省略许多类型的声明，以及它如何鼓励使用不可变的数据而不是可变的数据。</br>

### Hello，World

```kotlin
fun main(args: Array<String>){
	println("Hello,World!")
}
```

从这里可以看出哪些特性和语法？</br>

* 关键字`fun`用来声明一个函数。</br>
* 参数的类型写在名称的后面，变量的声明也是如此。</br>
* 函数可以定义在文件最外层，不需要把它放在类中。</br>
* 数组就是类，和Java不同，Kotlin没有声明数组类型的特殊语法。</br>
* 使用`println`代替了`System.out.println`。Kotlin标准库给Java标准库函数提供了许多语法更简洁的包装，而`println`只是其中一个。</br>
* 和许多现代语言一样，可以省略每行代码结尾的分号</br>

### 函数

上面的`main`函数是一个没有返回任何东西的函数。但是如果需要一个有意义的结果，返回类型可以猜到，位于参数列表之后的某处：

```kotlin
fun max(a: Int, b: Int): Int{
	return if(a>b) a else b
}

>>> println(max(1,2))
2
```

函数的声明以关键字`fun`开始，函数名紧随其后即`max`，接下来是括号括起来的参数列表。参数列表后面跟着返回类型，它们之间使用一个冒号隔开。注意，在Kotlin中，`if`是有结果值的表达式，它和`Java`中的三元运算符类似: `(a>b)?a:b`。</br>

> 语句和表达式
>
> 在Kotlin中，if是表达式，而不是语句。语句和表达式的区别在于，表达式有值，并且能作为另一个表达式的一部分使用；而语句总是包围着它的代码块中的顶层元素，并且没有自己的值。在Java中，所有的控制结构的都是语句。而在Kotlin中，除了循环(for、do和do/while)以外大多数控制结构都是表达式。这种结合控制结构和其他表达式的能力可以简明扼要的表示许多常见的模式。
>
> 另一方面，Java中的赋值操作是表达式，在Kotlin中反而成了语句。这有助于避免比较和赋值之间的混淆，而这种混淆常是错误的来源。

#### 表达式函数体

可以让前面的函数变得更简单。因为它的函数体是由单个表达式构成的，可以用这个表达式作为完整的函数体，并去掉花括号和`return`语句：</br>

```kotlin
fun max(a: Int, b: Int): Int = if(a > b) a else b
```

如果函数体卸载花括号中，可以说这个函数有`代码块体`。如果它直接返回了一个表达式，可以说它有`表达式体`。</br>

IDEA提供了在俩种函数风格之间的转换的`intention actions`：`Convert to expression body`(转换成表达式函数体)和`Convert to block body`(转换成代码块函数体)</br>

这种风格不光用在一些简单的单行函数，也会用在更复杂的单个表达式求值的函数中，比如if、when以及try等。还可以进一步简化`max`函数，省略掉返回类型：

```kotlin
fun max(a: Int, b: Int) = if (a > b) a else b
```

作为静态类型语言，Kotlin不是说编译器没有具体类型，而是对表达式体函数来说，编译器会分析作为函数体的表达式，并把它的类型作为函数的返回类型，即使没有显式的写出来。这种分析被称作`类型推导`。注意，只有表达式体函数的返回类型可以省略，对于有返回值的代码块体函数，必须显式的声明返回类型和`return`语句。</br>

### 变量

在Java中声明变量的时候会以类型开始。在Kotlin中是行不通的，因为许多变量声明的类型都可以省略。所以在Kotlin中以关键字开始，然后是变量名称，最后可以加上类型(不加也可以)：</br>

```kotlin
val question = "Question of Life"
val answer = 42
```

和表达式体函数一样，如果不指定变量的类型，编译器会分析初始化器表达式的值，并把它的类型作为变量的类型。在前面的例子中，变量的初始化器42的类型是Int，那么变量就是这个类型。如果使用浮点数常量，那么变量就是`Double`类型：</br>

```kotlin
val yearsToComplete = 7.5e6
```

7.5*10的6次方~~~</br>

如果变量没有初始化器，需要显式的指定它的类型：</br>

```kotlin
val answer: Int
answer = 42
```

如果不提供任何可以赋给这个变量的值的信息，编译器就无法推导出它的类型。</br>

#### 可变变量和不可变变量

声明变量的关键字有俩个：</br>

* val 来自value ：不可变引用。使用val声明的变量不能在初始化之后再次赋值，它对应的是Java 的 final变量。</br>
* var 来自variable ：可变引用。这种变量的值可以被改变。这种声明对应的是普通的Java变量(非final)。</br>

默认情况下，应尽可能使用`val`关键字来声明所有的kotlin变量。仅在必要的时候换成`var`。使用不可变引用、不可变对象及无副作用的函数让代码更接近函数式风格。</br>

在定义了`val`变量的代码块执行期间，`val`变量只能进行唯一一次初始化。但是，*如果编译器能确保只有唯一一条初始化语句被执行，可以根据条件使用不同的值来初始化它*:</br>

```kotlin
val message: String
if(canPerformOperation){
	message = "Success"
}else{
	message = "Failed"
}
```

注意，*尽管`val`引用自身是不可变的，但是它指向的对象可能是可变的。*例如：</br>

```kotlin
val languages = arrayListOf("Java")//声明不可变引用
languages.add("Kotlin")//改变引用指向的对象
```

即使`val`关键字允许变量改变自己的值，但它的类型却是改变不了的。例如，下面这段代码是不会编译的：</br>

```kotlin
var answer = 42
answer = "no answer" //错误：类型不匹配
```

编译器只会根据初始化器来推断变量的类型，在决定类型的时候不会考虑后续的赋值操作。如果需要在变量中存储不匹配类型的值，必须手动把值转换或强制转换成正确的类型。</br>

### 更简单的字符串格式化： 字符串模板

介绍一个新特性，`字符串模板`。在代码中可以在字符串字面值中引用局部变量，只需要在变量名称前面加上`$`。这等价于Java中的字符串拼接`("Hello, "+ name +"!.")`，效率一样但是更紧凑。如果需要在字符中使用`$`字符，需要对它转义`println("\$x")`。</br>

```kotlin
fun main(args: Array<String>){
	val name = if (args.size > 0) args[0] else "Kotlin"
	println("Hello,$name!")
}

//打印结果是"Hello, Kotlin"，如果把"Bob"作为实参传进来，打印的就是"Hello,Bob"
```

```kotlin
fun main(args: Array<String>){
	if (args.size > 0){
		println("Hello, ${args[0]}!")
	}
}

//使用${}的语法插入args数组中第一个元素
```

还可以在双引号中直接嵌套双引号，只要它处在某个表达式的范围内，即花括号内：</br>

```kotlin
fun main(args: Array<String>){
	println("Hello, ${if (args.size > 0) args[0] else "someone"}!")
}
```

知道了如何定义函数和变量，接下来看看类。</br>

### 类和属性

一个简单的JavaBean类Person，目前它只有一个属性，name：</br>

```java
/* Java */
public class Person{
	private final String name;
	
	public Person(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
```

使用Java到Kotlin的转换器，把Person类转换成Kotlin：</br>

```kotlin
class Person(val name: String)
```

看起来还不错，如果试过其他一些现代JVM语言，会发现类似的事情。这种类(只有数据没有其他代码)，通常被叫做`值对象`，许多语言都提供简明的语法来声明它们。注意，从Java到Kotlin的转换过程中，`public`修饰符消失了。在Kotlin中，`public`是默认的可见性，所以可以省略它。</br>

#### 属性

类的概念，就是把`数据`和`处理数据`的代码封装成一个单一的实体。在Java中，数据存储在字段中，通常还是私有的。如果想让类的使用者访问到数据，得提供`访问器方法`：`getter`和`setter`。`setter`还可以包含额外的逻辑，包括*验证传给它的值*、*发送关于变化的通知等*。</br>

在Java中，字段和其访问器的组合常被叫做`属性`，而许多框架严重依赖这个概念。`在Kotlin中，属性是头等的语言特性`，完全代替了字段和访问器方法。在类中声明一个属性和声明一个变量一样：使用`val`和`var`关键字。声明成`val`的属性是只读的，而`var`属性是可变的。</br>

```kotlin
class Person(
	val name: String,//只读属性：生产一个字段和一个简单的getter
	var isMarried: Boolean//可写属性：一个字段，一个getter和一个setter
)
```

基本上，当声明属性的时候，就声明了对应的访问器，只读属性有一个getter，而可写属性既有getter也有setter。如果需要，可以声明自定义访问器，使用不同的逻辑来计算和更新属性的值。</br>

看一下Java和Kotlin如何使用Person：</br>

```java
/* Java */
>>> Person person = new Person("Bob",true);
>>> System.out.println(person.getName());
Bob
>>> System.out.println(person.isMarried());
true
```

```kotlin
/* Kotlin */
>>> val person = Person("Bob", true) //调用构造方法不需要new关键字
>>> println(person.name) //可以直接访问属性，但调用的是getter
Bob
>>> println(person.isMarried)
true
```



#### 自定义访问器

假设声明一个矩形，它能判断自己是否是正方形，不需要一个单独的字段来存储这个信息(是否是正方形)，因为可以随时的通过检查举行的长宽是否相等来判断：

```kotlin
class Rectangle(val height: Int, val width: Int){
	val isSquare: Boolean
		get(){//声明属性的getter
			return height == width
		}
}
```

属性isSquare不需要字段来保存它的值。它只有一个自定义实现的getter，它的值是每次访问属性的时候计算出来的。</br>

也可以不使用花括号：

```kotlin
get() = height == width
```

声明一个没有参数的函数是否比声明带有自定义getter的属性更好。其实俩种方式几乎一样：实现和性能都没有差别，唯一的差异就是可读性。在了解其他的语言特性之前，先探索一下Kotlin的代码在磁盘中是怎样组织的。</br>



#### Kotlin源码布局：目录和包

Java会把所有的类组织成包。Kotlin也有和Java类似的包的概念。每一个Kotlin文件都能以一条package语句开头，而文件中定义的所有声明(类、函数及属性)都会被放到这个包中。如果其他文件中定义的声明也有相同的包，这个文件可以直接使用它们；如果包不相同，则需要导入它们。和Java一样，导入语句放在文件的最前面并使用关键字import。下面这个源码文件的例子展示了包声明和导入语句的语法。</br>

```kotlin
package geometry.shapes //包声明

import java.util.Random //导入标准Java库的类

class Rectangle(val height: Int, val width: Int){
	val isSquare: Boolean
		get() = height == width
}

fun createRandomRectangle(): Rectangle{
	val random = Random()
	return Rectangle(random.nextInt(), random.nextInt())
}
```

Kotlin不区分导入的是类还是函数，而且，它允许使用import关键字导入任何类的声明。可以直接导入顶层函数的名称。like this：</br>

```kotlin
package geometry.example

import geometry.shapes.createRandomRectangle //导入函数名称

fun main(args: Array<String>){
	println(createRandomRectangle().isSquare) //极少数会打印true
}
```

也可以在包名称后面加上`.*`来导入特定包中定义的所有声明。注意，这种星号导入不仅让保重定义的类可见，也会让顶层函数和属性可见。</br>

在Java中，要把类放到和包结构相匹配的文件和目录中。在Kotlin中，可以把多个类放在同一个文件中，文件的名字还可以随意选择。Kotlin也没有对磁盘上源文件的布局强加任何限制。不需要遵循目录层级结构。不过，大多数情况下，遵循Java的目录层级结构把源码文件放到目录中，是个不错的选择。Java和Kotlin混编的项目尤为重要，因为比较清晰。但是也应该毫不犹豫的把多个类放在同一个文件中，特别是那些很小的类。</br>

现在了解了程序的结构是怎样的，继续看下Kotlin的控制结构。</br>

### 表示和处理选择：枚举和"when"

`when`结构可以被认为是Java中switch结构的替代品，但是更强大，也使用的更频繁。顺便看下Kotlin声明枚举的例子以及`智能转换`的概念。</br>

#### 声明枚举类

```kotlin
enum class Color{
	RED,ORANGE,YELLOW,GREEN,BLUE,INDIGO,VIOLET
}
```

这是极少数Kotlin声明比Java使用了更多关键字的例子：Kotlin用了`enum`，`class`俩个关键字。而Java只有一个`enum`关键字。在Kotlin中，`enum`是一个所谓的软关键字：只有当它出现在class前面时候才有特殊意义，在其他地方可以把它当作普通的名称使用。与此不同的是`class`仍是一个关键字，要继续使用名称`clazz`和`aClass`来声明变量。</br>

和Java一样，枚举并不是值的列表：可以给枚举类声明属性和方法。如下所示：

```kotlin
enum class Color(
	val r: Int, val g: Int,	b: Int //声明枚举常量的属性
){
	RED(255,0,0), ORANGE(255,165,0), //在每个常量创建的时候指定属性值
	YELLOW(255,255,0), GREEN(0,255,0), BLUE(0,0,255), 
	INDIGO(75,0,130), VIOLET(238,130,238); //这里必须有分号
	
	fun rgb() = (r * 256 + g) * 256 + b //给枚举类定义一个方法
}
>>>println(Color.BLUE.rgb())
255
```

枚举常量用的声明构造方法和属性的语法与之前的常规类一样。当声明每个枚举常量的时候，必须提供该常量的属性值。注意：`这个例子展示了Kotlin语法中唯一需要使用分号的地方`：如果要在枚举类中定义任何方法，就要使用分号把枚举常量列表和方法分开。下面看一些在代码中处理枚举常量的超酷的方式。</br>

#### 使用"when"处理枚举类

假设需要一个函数来告诉每种颜色在这个口诀（红橙黄绿蓝靛紫）中对应的单词，而并不想把这些信息存储在枚举内部。在Java中可以使用`switch`语句完成。而Kotlin中对应的结构是`when`。</br>

和`if`相似，`when`是一个有返回值的表达式，因此可以写一个直接返回`when`表达式的表达式体函数。这里给出一个多行的表达式体函数的例子：

```kotlin
fun getMnemonic(color: Color) = 
	when(color){
		Color.RED -> "红"
		Color.ORANGE -> "橙"
		Color.YELLO -> "黄"
		Color.GREEN -> "绿"
		Color.BLUE -> "蓝"
		Color.INDIGO -> "靛"
		Color.VIOLET -> "紫"
	}
>>> println(getMnemonic(Color.BLUE))
蓝
```

上面的代码更具传进来的color值找到对应的分支。和Java不一样，不需要在每个分支上都写上`break`语句(在Java中遗漏break通常会导致bug)。如果匹配成功，只有对应的分支会执行。也可以把多个值合并到一个分支，只需要逗号隔开。

```kotlin
fun getWarmth(color: Color) = when(color){
	Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
	Color.GREEN -> "neutral"
	Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
}
>>> println(getWarmth(Color.ORANGE))
warm
```

这些例子用的都是枚举常量的完整名称，即指定了枚举类的名称`Color`。可以通过导入这些常量来简化代码。

```kotlin
import ch02.colors.Color //导入其他包中定义的Color类
import ch02.colors.Color.* //显式的导入枚举常量就可以使用它们的名称

fun getWarmth(color: Color) = when(color){
	RED, ORANGE, YELLOW -> "warm" //使用导入常量的名称
	GREEN -> "netural"
	BLUE, INDIGO, VIOLET -> "cold"
}
```

#### 在"when"结构中使用任意对象

Kotlin中的`when`结构比Java中的`switch`强大的多。`switch`要求必须使用常量(枚举常量、字符串或者数字字面值)作为分支条件。`when`允许使用任何对象。写一个函数来混合俩种颜色，如果它们在我们这个小小的调色板中是能够混合的。只要很少的选项，就可以简单的把所有组合列举出来。

```kotlin
fun mix(c1: Color, c2: Color) = 
	when(setOf(c1, c2)){ //"when"表达式的实参可以是任何对象，它被检查是否与分支条件相等
		setOf(RED, YELLOW) -> ORANGE //列举出能够混合的颜色对
		setOf(YELLOW, BLUE) -> GREEN
		setOf(BLUE, VIOLET) -> INDIGO
		else -> throw Exception("Dirty color") //如果没有任何其他分支匹配这里就会执行
	}
```

能使用任何表达式做`when`的分支条件，很多情况下写的代码即简洁又漂亮，这个列子中分支条件是等式检查，接下会看到条件还可以是任意的布尔表达式。

#### 使用不带参数的"when"

可能发现上一个代码片段效率多少有些低，每次调用这个函数的时候，它都会创建一些Set实例，仅仅用来检查俩种给定颜色是否和另外俩种颜色匹配。一般没什么大问题，但是如果这个函数调用频繁，就非常值得用另一种方式重写，来避免创建额外的垃圾对象。`代码可读性会变差，但这是为了达到更好的性能付出的代价`

```kotlin
fun mixOptimized(c1: Color, c2: Color) = 
	when{ //没有实参传给"when"
		(c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
		(c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) -> ORANGE
		(c1 == BLUE && c2 == VIOLET) || (c1 == VIOLET && c2 == BLUE) -> ORANGE
		else -> throw Exception("Dirty color")
	}
>>> println(mixOptimized(BLUE, YELLOW))
GREEN
```

如果没有给`when`表达式提供参数，分支条件就是任意的布尔表达式。`mixOptimized`函数和前面的`mix`函数做的事情一样。这种写法的优点是不会创建额外的对象，但代价是更难理解。继续看下`智能转换`在`when`结构中发挥作用的例子。

#### 智能转换： 合并类型检查和转换

to be continue...

