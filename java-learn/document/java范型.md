@[toc]

## 1 什么是范型
>面向对象的一个重要目标就是重用代码的支持，支持这个目标的重要机制就是范型机制（generic implementation）：如果除去基本类型外，实现方法是相同的，那么就可以用范型实现来这种基本功能
>范型是为了参数化类型，或者说将类型当作参数传递给一个类或者方法。

范型在java中的地位很重要，在设计模式中有非常广泛的应用
## 2 实现范型构件
### 2.1 使用Object表示范型
在java5之前，java并不直接支持范型范型实现，范型编程需要通过使用继承来实现。通常使用继承Object这个超类来实现
例如：
```
 public Object testThrowException(@NonNull Object generalType) throws NumberFormatException {
        return generalType;
    }
```
这种方式需要注意两点

* 当访问这个对象时，需要强制转型
* 不能使用基本类型，只有引用类能够与Object相融

比如我们调用上面的方法时，编译时生成的时包装类型的参数：
```
    public static void main(String[] args) {
        User user = of();
        System.out.println(user.testThrowException(Integer.valueOf(1)));
    }
```

### 2.2 基本类型的包装
    在java语言中虽然每一个引用类型都和Object相融，但是8中基本类型（byte,short,int,long,duoble,float,char,boolen）却不能，，于是java为每一种基本类型都提供了一个包装类。每一个包装对象都是不可变的（ **它的状态绝对不能改变** ，存储的字段被设置成final）
**对应的8种包装类**

*   Byte
*   Short
*   Long
*   Double
*   Float
*   Char
*   Boolean
**扩展：Number类**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190621174012718.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1kxODYzMDI0Njc5MzE5NDUzMA==,size_16,color_FFFFFF,t_70)
### 2.3 使用接口表示范型
函数对象
>一个函数通过将其放在一个对象内部而被传递，这样的对象叫做**函数对象**（funtion object）

```

    public class TestFunctionObject<T> {
        public void compare(T t) {
            System.out.println(t);
        }
    }


```
```
      public void testFuntionObject(Y z, TestFunctionObject girl) {
        girl.compare(z);
    }
```

对象如何实现范型将在后面讲到。

### 2.4 数组类型的兼容
一般 a IS-A c,b IS-A c,如果接口能够接手c类型的参数，也意味着能够接受a或c类型的参数，但实际上还是比较复杂的。
例如，定义一个数组，并将其第一位赋值，如下
```
   public static void main(String[] args) {
        Object[] j = new Integer [5];
        j[0] = new Long(999999999999L);
        System.out.println(j);
    }
```
编译通过，但是运行时报ArrayStoreException
因为j[0]其实是一个Integer类型的引用，而程序试图将一个Long类型的肤质给Integer类型的引用，显然会报错。
编译通过是Java中的数组类型是兼容的，这叫做**协变数组类型**。
## 3 利用Java5范型特性时间范型构件

* 范型是Java5引入的一个很重要的概念。相比较Object代替一切类型简单粗暴而言，范型使得类型像参数那样传递给类或方法。它提供了一种扩展能力，更符合面向抽象开发的编程宗旨。
* 当具体的类型确定后，范型又提供了一种类型检测机制，只有相匹配的类型数据才能正常赋值，否则编译就不通过。所以它是一种类型安全检测机制，一定程度上提高了软件的安全性防止出现低级错误。
* 范型提花高领程序代码的可读性，不必要等到运行的时候菜去强制转换，在定义货值实例化阶段就已经确定了要操作的数据的类型
### 3.1 范型类
当指定一个范型类时，累的声明则包含了一个或多个类型参数，这些参数被放在类名后面的一对尖括号内
```
public class User<T, Y, Z> {

    private T t;

    private Y y;

    private Z z;
    
    public Z setZ(Z z) {
        return z;

    }
    
    public static void main(String[] args) {
        User user = User.of();
        System.out.println(user.setZ(1));
    }
    
}
```

### 3.2 范型接口
```
public interface UserInterface<T, B> {
    T testT(T t);

    B testB(B b);
}
```
实现范型接口
```
@Data(staticConstructor = "of")
public class User<T, Y, Z, B> implements UserInterface<T, B> {


    @Override
    public T testT(T t) {
        return null;
    }

    @Override
    public B testB(B b) {
        return null;
    }
    
}
```
**注意**
* 在实现范型接口的时候需要将范型的声明一起声明在实现类里，如果不声明，重载的方法将会被声明为Object范型
* 当实现类声明了接口范型的实参类型，则所有使用到范型的地方都必须使用实参类型
例如：
```
public class User<T, Y, Z, B> implements UserInterface<String, B> {

    @Override
    public String testT(String s) {
        return s;
    }

    @Override
    public B testB(B b) {
        return b;
    }
    
    public static void main(String[] args) {
        User user = User.of();
        System.out.println(user.testT("3sfdg"));
        System.out.println(user.testB(3));
        System.out.println(user.testB("dfhgu"));

    }
    
 }
```
### 3.3 自动装箱拆箱
Java5之前如果需要将基本类型传递给一个Object 类型的参数，需要先创建起包装类才能正确编译。Java5矫正了这种情形。
而如果一个包装类被放到需要使用基本类型的地方，比如Integer类型被int类型引用，则编译器会插入一个intValue方法，这就叫做 **自动拆箱**
 
```
    public static void main(String[] args) {
        Integer i = 3;
        int j =i;
    }
    
```
**编译后**
```
     public static void main(String[] args) {
        Integer i = Integer.valueOf(3);
        int j = i.intValue();
    }

```
### 3.4 范型的界限
菱形运算符是Java7增加的一种新的语言特性，它在不增加开发者负担的情况下简化了代码
`User<Intger> user = New User<Integer>()`，在在Java7之前是必须要如此定义的，但是既然已经声明了`User<Intger>`后面的`User<Integer>`就显得多余。Java7后可以写成`User<Intger> user = New User<>()`
这个功能称为 **类型推断**（type inference），它允许你像通常的方法那样调用泛型方法，而不用在尖括号之间指定类型
### 3.5 通配符
在2.4中讲到数组具有协变性，但是范型集合不具有协变性。Java5使用通配符来弥补这个不足
* <?> 无限定的通配符
* <? extends T> 有上限的通配符
* <? super T> 有下限的通配符
* <T extends B1 & B2 & B3>多重界限
#### 3.5.1 <?>无界限的通配符
```
       List<?> list = new ArrayList<>();
       // list.add("sd");这句无法编译
        list.get(0);
        for (Object o : list) {
            o.getClass();
        }
```

<?>删减了增加具体类型元素的能力，只保留与具体类型无关的能力。它不关心装载在这个容器里的元素到底是什么类型，值关系元素的数量等，这种需求是常见的也是必要的，比如在设计一套算法时，<?>提供了类型无关的思想，能够很方便的阅读代码。
#### 3.5.2 <? extends T>有上限`Upper Bounds`的通配符
 <?>代表无界参数，但是有些时候我们需要确定参数的一个范围，这时我们可以使用类型界限（type bound）解决这个问题。类型界限在<>内指定，它指定参数必须具有的性质。<? extends T> 代表类型参数 IS-A T

比如定义一个方法只能接受Number类型的参数

```
public class User<Y> {
    public Y y;

    public Y getY() {
        return y;
    }

    public <Z extends Number> Z testUpperBound(Z z) {
        return z;
    }

    public static void main(String[] args) {
        User user = new User();
       // user.testUpperBound("s");此行无法被编译，因为testUpperBound只接受Number类型的参数
        user.testUpperBound(2);
        user.testUpperBound(2.3);
    }
}
```
有界的类型参数还允许调用边界中定义的方法：
```
    public <Z extends Number> Z testUpperBound(Z z) {
        z.byteValue();
        z.doubleValue();
        return z;
    }
```
上界会影响读数据，必须通过强转类型才能正确读取
```
public class User<Y extends Woman> {
    public Y y;

    public Y getY() {
        return y;
    }

    public void setY(Y y) {
        this.y = y;
    }

    public <Z extends Girl & Human & Woman> Z testUpperBound(Z z) {
        System.out.println(z.getSex());
        return z;
    }

    public static void main(String[] args) {
        User user = new User();
        Girl girl = new Girl();
        user.setY(girl);
        Girl getGirl = (Girl) user.getY();//必须通过强转才能正常取
        System.out.println(getGirl.getSex());
    }

}
```
#### 3.5.3 <? super T>有下限`Lower Bounds`的通配符

  ><? super T> 表示 T IS-A ? ，范型参数是T的基类。
也即：下界规定了元素的最小粒度的下界，实际上是放松了容易元素的类型控制。
 super不能用在类的声明或方法声明里，只能用在定义变量的时候
 `public class User<Y super Woman> {}` 无法被编译
 ` User<? super Woman> user = new User();`能正常编译及使用


####  3.5.4 多重界限<T extends B1 & B2 & B3>
具有多个边界的类型变量是边界中列出的所有类型的子类型。如果其中一个边界是一个类，则必须先指定它.
```
public class User<Y> {
    public Y y;

    public Y getY() {
        return y;
    }

    public <Z extends Girl & Human & Woman> Z testUpperBound(Z z) {
        System.out.println(z.getSex());
        return z;
    }

    public static void main(String[] args) {
        User user = new User();
        Girl woman = null;
        user.testUpperBound(woman);
    }

}
```
参数必须是Girl或Human或Woman至少其中一个的子类，如果第一个是具体类或抽象类，后面的几个`&`连接的必须是接口`interface`

### 3.6 类型擦除
> 范型在很大程度上是Java语言中的成分而不是虚拟机中的结构。范型类可以由编译器通过所谓的**类型擦除**（type erasure）过程而转变成非范型类。这样，编译器就生成一种与范型类同名的原始类（raw class），但是类型参数都被删去了。类型变量由它们的类型衔接来代替，当一个具有擦除返回类型的范型方法被调用的时候，一些特性被自动地插入。如果使用一个范型类而不带类型参数，那么使用的是原始类。
> 类型擦除的一个重要推论是，所生成的代码与程序员在范型之前所写入的代码并没有太多的差异，而且事实上运行的也并不快。起显著的优点在于，程序员不必把一些类型转换放到代码里，编译器将进行重要的类型检查

举个例子
```
    List<String> stringList = new ArrayList<>();
    List<Integer> integerList = new ArrayList<>();
    System.out.println(stringList.getClass().equals(integerList.getClass()));
```
上面程序输出的是true，在JVM中`List<String>`和`List<Integer>`的`Class`都是`List.class`,范型的类型被擦除了
javac 编译后的内容；
```
        ArrayList var1 = new ArrayList();
        ArrayList var2 = new ArrayList();
        System.out.println(var1.getClass().equals(var2.getClass()));
```
编译后并没有指定其范型ArrayList<String>，而是生成原始类型ArrayList

```
    System.out.println(stringList.get(0).getClass().equals(integerList.get(0).getClass()));
```
这句输出的是false，在JVM中它们的`Class`分别是`java.lang.String`和`java.lang.Integer`
为什么获取单个元素值的事哈还是能识别出其具体类型呢？
看下getClass源码：
```
    /**
     *  返回对象运行时的class
     * @return The {@code Class} object that represents the runtime
     *         class of this object.
     */
    public final native Class<?> getClass();
```

附`javac`编译文件即查看文件命令
`javac -d path User.java`：编译java文件
`javap -verbose 文件名` ：查看class文件

###  3.7 未经检查的错误
```
        User user = User.of();
        user.setY("3");
```
如果将原始类型调用在相应范型中定义的范型方法，会得到警告：
>注: User.java使用了未经检查或不安全的操作。
 注: 有关详细信息, 请使用 `-Xlint:unchecked` 重新编译。
 
该警告显示原始类型绕过了范型类型检查去，将不安全代码的扑火推迟到运行时。因此因该避免使用原始类型。
`unchecked`表示编译器没有足够的类型信息来执行确保类型安全所需要的所有类型的检查。虽然编译器提供了一个提示，但是默认情况下`unchecked`警告被禁用。要查看所有`unchecked`警告，需要使用` -Xlint：unchecked `进行重新编译。

使用`-Xlint:unchecked` 重新编译后`javac -Xlint:unchecked User.java`得到如下附加信息
>User.java:37: 警告: [unchecked] 对作为原始类型User的成员的setY(Y)的调用未经过检查
        testUser.setY("23");
                     ^
  其中, Y是类型变量:
    Y扩展已在类 TestUser中声明的Object
1 个警告


要完全禁用未经检查的警告，需要使用`@SuppressWarnings("unchecked")`标注在类上，抑制unchecked警告。

### 3.8 类型擦除带来的局限性
>类型擦除是范型能够与之前的java版本代码兼容共存的原因，但是也因为类型擦除，它会抹掉很多继承相关的特性，这是他的局限性
定义一个数组：
```
        List<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        integerList.add("2");//这里将会报编译错误  
```
因为类型不匹配，最后一行是无法编译通过的。但是在类型擦除的时候类型参数都被擦除，理论上所有Object类型都可以add进去。
可以是利用反射绕过这个限制
```
        List<?> list = new ArrayList<>();
        //list.add("sd");这句无法编译
        Method method = list.getClass().getMethod("add", Object.class);
        method.invoke(list,"1");
        method.invoke(list,1);
        for (Object o : list) {
            System.out.println(o.getClass());
        }

```
输出结果依然是保持各自的具体类型。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190621174656982.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1kxODYzMDI0Njc5MzE5NDUzMA==,size_16,color_FFFFFF,t_70)

### 3.9 桥接方法的影响
此段来自[jdk官网学习教程连接](https://zq99299.github.io/java-tutorial/java/generics/)
笔者没有真正去实践桥接方法
```
public class Node<T> {

    public T data;

    public Node(T data) { this.data = data; }

    public void setData(T data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}

public class MyNode extends Node<Integer> {
    public MyNode(Integer data) { super(data); }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}
```
考虑如下代码
```
MyNode mn = new MyNode(5);
Node n = mn;            // A raw type - compiler throws an unchecked warning
n.setData("Hello");
Integer x = mn.data;    // 报错ClassCastException
```
类型擦除后
```
MyNode mn = new MyNode(5);
Node n = (MyNode)mn;         // A raw type - compiler throws an unchecked warning
n.setData("Hello");
Integer x = (String)mn.data; // Causes a ClassCastException to be thrown.
```
这是执行代码时发生的情况：

* `n.setData("Hello")` 方法在 `MyNode` 上执行
* 在 `setData(Object)` 的主体中，由 `n`引用的对象的数据字段被分配给一个 `String`。
* 可以通过 `mn` 引用访问 `data`，并且期望它是一个整数（因为 `MyNode` 是一个 `Node <Integer>`）
* 尝试强转一个字符串为 `Integer`，导致 `ClassCastException`

**桥接方法**
>编译扩展参数化类或实现参数化接口的类或接口时，编译器可能需要创建一个称为桥接方法的合成方法， 作为类型擦除过程的一部分。您通常不需要担心桥接方法，但是如果出现在堆栈轨迹中，您可能会感到困惑。

类型擦除后，`Node` 和 `MyNode` 类成为：
```
public class Node {

    public Object data;

    public Node(Object data) { this.data = data; }

    public void setData(Object data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}

public class MyNode extends Node {

    public MyNode(Integer data) { super(data); }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}
```
类型擦除后，方法签名不匹配。`Node` 的 `setData(Object data)` 和 `MyNode` 的 `setData(Integer data)` 方法不会被重写了。

为了解决这个问题并在类型擦除之后保留泛型类型的多态性，`Java` 编译器生成一个桥接方法来确保子类型按预期工作。 对于 `MyNode` 类，编译器为 `setData` 生成以下桥接方法：
```
class MyNode extends Node {

    // Bridge method generated by the compiler
    //
    public void setData(Object data) {
        setData((Integer) data);
    }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}
```
正如你看到的，桥接方法具有和 `Node` 类方法签名一致的方法，然后委托具体的类型方法。

### 3.9 对于范型的限制
由于类型擦除的原因，这里列出的每一个限制都是必须要遵守的
#### 3.9.1 基本类型不能用做类型参数
`User<int>`是非法的，必须使用包装类
#### 3.9.2 instanceof检测
instanceof 检测和类型转换工作只对原始类型进行。
#### 3.9.3 static的语境
在一个范型类中，static方法和static域均不可饮用类的类型变量，因为在类型擦除后类型变量就不存在了。另外由于实际上只存在一个原始的类，因此static域在该类的诸范型实例之间是共享的。
```
public class User<Y extends Woman> {
    private static Y y;
}
```
```
 public static <Z extends Number> Z testZ() {
    }
```
这两段代码都无法被编译
#### 3.9.4 范型类型的实例化
不能创建一个范型类型的实例
`T obj = new T（）`是非法的
#### 范型数组对象
不能创建一个范型数组
`T[] arr = new T[10]`是非法的
