# kotlin summary
kotlin目标平台包括：服务器端、Android以及任何运行Java的地方。以及可以使用Intel Multi-OS Engine让Kotlin代码运行在iOS设备上，还可以使用Kotlin和TornadoFX以及JavaFX一起来构建桌面应用程序。除了Java以外，Kotlin还可以编译成JavaScript允许在浏览器上运行Kotlin代码。  

# Kotlin做为编程语言的关键特质

## 静态类型

Kotlin和Java一样是一种静态类型的编程语言。这意味着所有表达式的类型在编译期已经确定了，而编译器就能验证对象是否包含了你想访问的方法和字段。这与动态类型的变成语言形成了鲜明的对比，后者在JVM上的代表包括Groovy和JRuby。这些语言允许定义可以存储任何数据类型的变量，或者返回任何数据类型的函数，并在运行时期才解析方法和字段引用。这会减少代码量并增加创建数据结构的灵活性。但它的缺点是，在编译期不能发现像名字拼写错误这样的问题，继而导致运行时的错误。  
  
另一方面，与Java不同的是，Kotlin不需要在源代码中显式的声明每个变量的类型。很多情况下，变量类型可以根据上下文来自动判断，这样就可以省略类型声明。like this: ```val x = 1```在声明这个变量时，由于变量初始化为整数值，Kotlin自动判断出它的类型是Int。编译器这种从上下文推断变量类型的能力被称为`类型推导`。
  
下面罗列了一些静态类型带来的好处：</br>
性能： 方法调用速度更快，因为不需要在运行时才来判断调用的是哪个方法。</br>
可靠性： 编译器验证了程序的正确性，因而运行时崩溃的概率更低。</br>
可维护性： 陌生的代码更容易维护，因为可以看到代码中用到的对象的类型。</br>
工具支持： 静态类型使IDE能提供可靠的重构、精准的代码补全以及其他特性。</br>
  
得益于Kotlin对类型推导的支持，不再需要显式的声明类型，因此大部分关于静态类型的额外冗长代码也就不复存在了。 

当检视Kotlin类型系统的细节时，会发现许多熟悉的概念。类、接口以及泛型等和Java接近。但也有一些新概念出现。比如，Kotlin对`可空类型的支持`，通过在编译器检测可能存在的空指针异常，让我们可以写出更可靠的程序。另一个Kotlin类型系统的新概念，是对`函数类型`的支持。要清楚这一点，需要先了解`函数式编程`的主要思想，以及Kotlin如何支持这种编程风格的。  

## 函数式和面向对象

函数式编程的核心概念：</br>
头等函数： 把函数(一小段行为)当作值使用，可以用变量保存它，把它当作参数传递，或者当作其他函数的返回值。</br>
不可变性： 使用不可变对象，这保证了它们的状态在其创建之后不能再变化。</br>
无副作用： 使用的纯函数。此类函数在输入相同时会产生同样的结果，并且不会修改其他对象的状态，也不会和外面的世界交互。</br>

函数式编程风格的代码带来的好处首先是`简洁`。函数式风格的代码比相应的命令式风格的代码更优雅，更简练，因为把函数当作值可以获得更强大的抽象能力，从而避免重复代码。</br>

假设有俩段类似的代码，实现相似的任务(例如，在集合中寻找一个匹配的元素)但具体细节略有不同。可以轻易的将这段逻辑中公共的部分提取到一个函数中，并将其他不同的部分作为参数传递给它。这些参数本身也是函数，但可以用一种简洁的语法来表示这些匿名函数，它被称作`lambda表达式`：</br>

```fun findAlice() = findPerson{ it.name == "Alice"}```</br>
```fun findAlice() = findPerson{ it.name == "Bob"}```</br>

其中```findPerson()```方法包含了寻找一个人的公共逻辑，而花括号中的代码块用来识别出要找的特定的人。</br>

函数式编程带来的第二个好处就是`多线程安全`，多线程程序中最大的错误来源之一就是在没有采取适当的同步机制的情况下，在不同的线程上修改同一份数据。如果使用的是`不可变数据结构`和`纯函数`，就能保证这样不安全的修改根本不会发生，也就不需要考虑为其设计复杂的同步方案。</br>

最后，函数式编程意味着<b>测试更加容易</b>。没有副作用的函数可以独立的进行测试，因为不需要写大量的设置代码来构造他们依赖的整个环境。</br>

Java是Java 8 之后提供函数时编程，而Kotlin从一开始就拥有丰富的特性集来支持函数式编程风格，包括：</br>

函数类型，允许函数接收其他函数作为参数，或者返回其他函数。</br>
lambda表达式，用最少的样板代码方便地传递代码块。</br>
数据类，提供了创建不可变对象的简明语法。</br>
标准库中包含了丰富的API集合，方便的使用函数式编风格操作对象和集合。</br>
