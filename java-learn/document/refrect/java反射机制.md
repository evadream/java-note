

## 定义
**反射就是在运行时才知道要操作的类是什么，并且可以在运行时获取类的完整构造，并调用相应的方法**

我们经常用的生成一个实例的一般如下
```
  User user = new User();
  user.setName("lili");
```
但是如果大家有看过开源框架，constructor.newInstance()来实例化一个对象比如spring BeanUtils里面的
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190617195626111.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1kxODYzMDI0Njc5MzE5NDUzMA==,size_16,color_FFFFFF,t_70)

这里就用到了java的反射机制
> java反射机制：在程序运行时，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用他的任意一个方法和属性。这种`动态的获取信息以及动态调用对象方法`的功能叫做`java的反射机制`

那么接下来我们来看看java的反射机制到底都有写什么
## 反射常用API
### 获取类的信息
```
//获取类的名称
String clazzName = clazz.getName();
//获取类的所有属性
Field[] fields = clazz.getFields();
//获取类的所有方法
Method[] methods = clazz.getMethods();
//获取类上的所有的注解
Annotation[] annotations = clazz.getAnnotations();
//获取类的构造器
Constructor cons = clazz.getConstructor();
```

### 获取所有的方法信息
```
 for (Method meth : methods) {
            //获去方法声明的类
            System.out.println("method name:" + meth.getDeclaringClass().getName());
            //获取方法访问权限
            int modify = meth.getModifiers();
            System.out.println("method modify:" + modify);
            System.out.println("method returnType name:" + meth.getReturnType().getName());
            //获取方法范型类名称
            System.out.println("method generalReturnType name:" + meth.getGenericReturnType().getTypeName());
            //获取方法异常类型
            Class[] c = meth.getExceptionTypes();
            System.out.println("method exceptionType name:" + (c.length == 0 ? "null" : c[0].getName()));
            //获取方法参数类型
            Class[] paramTypeClazz = meth.getParameterTypes();
            System.out.println("method params types length:" + paramTypeClazz.length);
            //获取方法个数
            System.out.println("method param count:" + meth.getParameterCount());
        }
```


### 获取实例的属性值及修改属性值

```
    Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field.getName());
            //获取字段访问权限
            int modify = field.getModifiers();
            System.out.println("modify:" + modify);
            //获取字段是否可被访问
            boolean access = field.isAccessible();
            if (!access) {
                //设置访问权限，保证对private的属性的访问（表示反射时不检查属性权限）
                //field.setAccessible(true) 只是改变了field实例的属性，没有改变object实例属性的权限
                field.setAccessible(true);
            }
            //获取字段的值
            Object value = field.get(user);
            System.out.println("before modify value:" + value.toString());
            if (field.getType().getSimpleName().equals("String")) {
                field.set(user, "test");
            } else if (field.getType().getSimpleName().equalsIgnoreCase("short")) {
                field.set(user, (short) 1);
            } else {
                field.set(user, 1);
            }

            Object valueAfterModify = field.get(user);
            System.out.println("after modify value:" + valueAfterModify.toString());
        }
    }
```

注意这里获取字段集合有两种方式`clazz.getFields()`和`clazz.getDeclaredFields()`这两个方法返回的结果不一样，我们来看下源码注释
`clazz.getFields()`
>Returns an array containing {@code Field} objects reflecting all the accessible public fields of the class or interface represented by this {@code Class} object.
>返回这个对象所代表的类或接口所有可访问的公共字段

`clazz.getDeclaredFields()`
>Returns an array of {@code Field} objects reflecting all the fields declared by the class or interface represented by this {@code Class} object. This includes public, protected, default (package) access, and private fields, but excludes inherited fields.
>返回对象所代表的所有类或接口声明的所有字段，包括，公共的、受保护的、默认可访问的，以及私有字段，但不包括继承的字段

这里需要注意的是如果要访问这个对象的私有字段，我们应该用getDeclaredFields()这个方法

### 修改常量
在上面`获取实例的属性值及修改属性值`的例子是可以修改String 类型的私有常量，可以看见和修改私有属性一样。那么其他类型的是否可以修改呢？看代码：
先在user类里面定义几个私有常量
```
@Data
public class User<T> {

    private static String DESCRIBE_String = "test_user";

    private static int DESCRIBE_INT = 32;
    private static Integer DESCRIBE_INTEGER = 64;

    private static short DESCRIBE_SHORT = 1;

    private static User DESCRIBE_USER = new User();


    private Integer age;
    private String name;
    private String mobile;
    private short sex;

    public T testThrowException(T generalType) throws NumberFormatException {
        return generalType;
    }
}
```
测试代码
```
       // 测试是否可以修改私有常量

        Field fieldInt = clazz.getDeclaredField("DESCRIBE_INT");
        fieldInt.setAccessible(true);
        System.out.println("before modify fieldInt value:" + fieldInt.get(user));
        fieldInt.set(user, 128);
        System.out.println("after modify fieldInt value:" + fieldInt.get(user));


        Field fieldInteger = clazz.getDeclaredField("DESCRIBE_INTEGER");
        fieldInteger.setAccessible(true);
        System.out.println("before modify fieldInteger value:" + fieldInteger.get(user));
        fieldInteger.set(user, 1024);
        System.out.println("after modify fieldInteger value:" + fieldInteger.get(user));


        Field fieldShort = clazz.getDeclaredField("DESCRIBE_SHORT");
        fieldShort.setAccessible(true);
        System.out.println("before modify fieldShort value:" + fieldShort.get(user));
        fieldInt.set(user, (short) 2);
        System.out.println("after modify fieldShort value:" + fieldShort.get(user));


        Field fieldUser = clazz.getDeclaredField("DESCRIBE_USER");
        fieldUser.setAccessible(true);
        Field userNameFiled = user.getClass().getDeclaredField("name");
        userNameFiled.setAccessible(true);
        System.out.println("before modify fieldUser value:" + userNameFiled.get(user));
        userNameFiled.set(user, "test test test");
        System.out.println("after modify fieldUser value:" + userNameFiled.get(user));

```
运行结果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019061719541250.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1kxODYzMDI0Njc5MzE5NDUzMA==,size_16,color_FFFFFF,t_70)
可以看到所有类型的私有常量的值都被正确修改。
### 修改静态私有属性
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190617195437131.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1kxODYzMDI0Njc5MzE5NDUzMA==,size_16,color_FFFFFF,t_70)
可以看到报了异常
能够正常修改ser对象的静态属性
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190617195507767.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1kxODYzMDI0Njc5MzE5NDUzMA==,size_16,color_FFFFFF,t_70)
## 源码
`Class.forName(name)`
```
 @CallerSensitive
    public static Class<?> forName(String className)
                throws ClassNotFoundException {
        Class<?> caller = Reflection.getCallerClass();
        return forName0(className, true, ClassLoader.getClassLoader(caller), caller);
    }
```
这个方法代码比较少，但是用到了native方法，此处省略

看什么源码。。。都是native方法。。。

