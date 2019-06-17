
@[toc]
维护一个pojo的时候最讨厌看到满屏的set/get，有些时候因为一个类的字段太多，对阅读毫无意思的set/get方法影响了阅读，甚是影响心情

lombok就是为了解决这个事情运用而生。那么来看下lombook的简单使用

`maven`依赖
```

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.16.18</version>
    <scope>provided</scope>
</dependency>

```
`idea 插件安装`
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190617223326591.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1kxODYzMDI0Njc5MzE5NDUzMA==,size_16,color_FFFFFF,t_70)

在plugins里搜索lombok，如果没有，选择从浏览器安装，点击右边的安装然后重启idea即可

## 常用注解使用方式说明
### `val`
> 可以使用val本地变量声明的类型而不是实际写入的类型。将从初始化表达式推断出新的类型，局部变量也将成为不可变变量。此功能仅适用于局部变量和foreach循环，不适用于字段
> 在本地变量声明中存在此类型会触发添加final关键字以及覆盖伪`val`类型的初始化表达式的类型

```
public void example2() {
    val map = new HashMap<Integer, String>();
    map.put(0, "zero");
    map.put(5, "five");
    for (val entry : map.entrySet()) {
      System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
    }
  }
```
**编译后**
```
public void example2() {
    final HashMap<Integer, String> map = new HashMap<Integer, String>();     map.put(0, "zero");
    map.put(5, "five");
    for (final Map.Entry<Integer, String> entry : map.entrySet()) {
      System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
    }
  }
```
**自动添加了final字段，以及生成了真实的类型**
### `var`
>和val一样的使用，只是编译时不会添加final关键字，即不会变成不可变变量
>需要1.16.22及以上的版本
```
public  void example2() {
    var list = new ArrayList<String>();
    list.add("1");
    list.add("2");
    for (var s : list) {
        System.out.println(s);
    }
}
```

**编译后**
```
 public void example2() {
        ArrayList<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            String s = (String)var2.next();
            System.out.println(s);
        }
    }
```
### `@NonNull`
> 使用`@NonNull`方法或构造函数的参数让lombok自动生成`null-check`语句

```
   public T testThrowException(@NonNull T generalType) throws NumberFormatException {
        return generalType;
    }
```
**编译后**
```
    public T testThrowException(@NonNull T generalType) throws NumberFormatException {
        if (generalType == null) {
            throw new NullPointerException("generalType");
        } else {
            return generalType;
        }
    }

```
### `@Clearup`
>使用`@Clearup`以确保在嗲吗执行路径退出当前作用域之前自动清理给定的资源，作用于局部变量

```
    public static void main(String[] args) throws IOException {
        @Cleanup InputStream in = new FileInputStream("/Users/momo/document/testin.pdf");
        @Cleanup OutputStream out = new FileOutputStream("/Users/momo/document/testout.pdf");
        byte[] b = new byte[10000];
        while (true) {
            int r = in.read(b);
            if (r == -1) {
                break;
            }
            out.write(b, 0, r);
        }
    }
```
**编译后**
```
   public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("/Users/momo/document/testin.pdf");

        try {
            FileOutputStream out = new FileOutputStream("/Users/momo/document/testout.pdf");

            try {
                byte[] b = new byte[10000];

                while(true) {
                    int r = in.read(b);
                    if (r == -1) {
                        return;
                    }

                    out.write(b, 0, r);
                }
            } finally {
                if (Collections.singletonList(out).get(0) != null) {
                    out.close();
                }

            }
        } finally {
            if (Collections.singletonList(in).get(0) != null) {
                in.close();
            }

        }
    }
```
### `@GET`和`@SET`
`@GET`
| 修饰符 | 可选元素 | 描述 |
| --- | --- | --- |
| boolean | lazy |  |
| Getter.AnyAnnotation[] |onMethod  |  这里列出的任何注释都放在生成的方法上。|
| AccessLevel | value |  如果你想让你的getter不公开，你可以在这里指定一个替代访问级别。|
**说明：**
* Lazy：默认为：false，如果设置 lazy 则 该属性必须为 final
* onMethod：默认为{},此处出现的注解会在生成的方法上面，最常见的使用是结合JackJson，做序列化标注
    * JDK7-：@Getter(onMethod=@__({@AnnotationsGoHere}))
    * JDK8+：@Getter(onMethod_={@AnnotationsGohere})//注意后面的下划线onMethod
* AccessLevel：默认为AccessLevel.PUBLIC 可以手动修改方法的访问级别，配置参数为 枚举类:AccessLevel中属性

`@SET`
|修饰符和类型|可选元素|描述|
| --- | --- | --- |
Setter.AnyAnnotation[]|onMethod|这里列出的任何注释都放在生成的方法上。|
|Setter.AnyAnnotation[]|onParam|这里列出的任何注释都放在生成的方法参数上。|
|AccessLevel|value|如果你希望你的setter是非公开的，你可以在这里指定一个替代访问级别。|
**说明：**

* 该属性配置和`@Getter` 经常结合使用
* `onParam` ：该属性为参数的注解，此处可以和`@NotNull` 配合使用控制参数不为空，该属性配置同 `onMethod`

### `@toString`

|修饰符和类型|可选元素|描述|
|---|---|---|
|boolean|callSuper|toString在输出中包含超类实现的结果。|
|boolean|doNotUseGetters|通常情况下，如果 getters是可用的，那么被调用。|
|java.lang.String[]|exclude|这里列出的任何字段将不会被打印在生成的toString实现中。|
|boolean|includeFieldNames|在打印时包括每个字段的名称。|
|java.lang.String[]|of|如果存在，明确列出要打印的字段。|

### `@EqualsAndHashCode`
|修饰符和类型|可选元素|描述|
|---|---|---|
|boolean|callSuper|调用超类的实现equals，并hashCode计算对该类中的字段值。|
|boolean|doNotUseGetters|通常情况下，如果getter是可用的，那么被调用。|
|java.lang.String[]|exclude|这里列出的任何字段在生成equals和hashCode实现中都不会被考虑在内。|
|java.lang.String[]|of|如果存在，则明确列出要用于身份的字段。|
|EqualsAndHashCode.AnyAnnotation[]|onParam|这里列出的任何注释都放在的生成的参数equals和canEqual。|
### `@AllArgsConstructor`
|修饰符和类型|可选元素|描述|
|---|---|---|
|AccessLevel|access|设置构造函数的访问级别。|
|AllArgsConstructor.AnyAnnotation[]|onConstructor|这里列出的任何注释都放在生成的构造函数中。|
|java.lang.String|staticName|如果设置，生成的构造函数将是私有的，并且用包装真实构造函数的相同参数列表生成额外的静态“构造函数”。|

### `@NoArgsConstructor`
|修饰符和类型|可选元素|描述|
|---|---|---|
|AccessLevel|access设|置构造函数的访问级别。|
|boolean|force|如果true，将所有最终字段初始化为0 / null / false。|
|NoArgsConstructor.AnyAnnotation[]|onConstructor|这里列出的任何注释都放在生成的构造函数中。|
|java.lang.String|staticName|如果设置，生成的构造函数将是私有的，并且用包装真实构造函数的相同参数列表生成额外的静态“构造函数”。|

说明：生成一个无参数的构造函数。如果由于final字段的存在而无法写入这样的构造函数，将会生成错误消息。
### `@RequiredArgsConstructor`
|修饰符和类型|可选元素|描述|
|---|---|---|
|AccessLevel|access|设置构造函数的访问级别。|
|RequiredArgsConstructor.AnyAnnotation[]|onConstructor|这里列出的任何注释都放在生成的构造函数中。|
|java.lang.String|staticName|如果设置，生成的构造函数将是私有的，并且用包装真实构造函数的相同参数列表生成额外的静态“构造函数”。|

说明：生成一个具有所需参数的构造函数。必需的参数是final和约束的字段，如

### `@DATA`

|修饰符和类型|可选元素|描述|
|---|---|---|
|java.lang.String|staticConstructor|如果你指定一个静态构造函数名，那么生成的构造函数将是私有的，而创建一个静态工厂方法，其他类可以用来创建实例。|

**说明：**
如果使用了staticConstructor，生成的构造方法时私有的，正常是public
```
@Data(staticConstructor="of")
public class User<T> {

    private Integer age;
    private String name;
    private String mobile;
    private short sex;
}
```
**编译后的构造方法**
```
    private User() {
    }

```

>@Data是一个方便的快捷方式注释，它捆绑了@ToString，@EqualsAndHashCode和@Getter/@Setter和的特征@RequiredArgsConstructor。
@Data就像具有隐式@Getter，@Setter，@ToString，@EqualsAndHashCode和@RequiredArgsConstructor在类注解（不同之处在于没有构造将生成如果已经存在任何明确写入构造函数）。但是，这些注释的参数（例如callSuper，includeFieldNames和exclude）不能设置@Data。如果您需要为这些参数中的任何一个设置非默认值，只需显式添加这些注释; @Data足够聪明，可以遵循这些注释。
所有生成的getter和setter都将是public。要覆盖访问级别，请使用显式@Setter和/或@Getter注释来注释字段或类。您也可以使用此注释（通过将其组合AccessLevel.NONE）来完全禁止生成getter和/或setter。
标记为的所有字段transient都不会被考虑hashCode和equals。将完全跳过所有静态字段（不考虑任何生成的方法，并且不会为它们创建setter / getter）。
如果类已经包含与通常生成的任何方法具有相同名称和参数计数的方法，则不会生成该方法，也不会发出警告或错误。例如，如果您已经有一个带签名的方法，则不会生成equals(AnyType param)任何equals方法，即使从技术上讲，由于具有不同的参数类型，它可能是完全不同的方法。该规则同样适用于构造函数（任何显式构造将防止@Data从生成一个），以及toString，equals和所有的getter和setter。您可以使用任何构造函数或方法标记@lombok.experimental.Tolerate它们以从lombok中隐藏它们。
@Data可以正常处理字段的泛型参数。为了在为具有泛型的类构造对象时减少样板，可以使用该staticConstructor参数生成私有构造函数，以及返回新实例的静态方法。这样，javac将推断变量名称。因此，通过这样声明：@Data(staticConstructor="of") class Foo<T> { private T x;}您可以Foo通过编写来创建新实例：Foo.of(5);而不是必须写：new Foo<Integer>(5);。

```
@Data
public class User<T> {

    private static String DESCRIBE_String = "test_user";

    private static final int DESCRIBE_INT = 32;
    private static final Integer DESCRIBE_INTEGER = 64;

    private static final short DESCRIBE_SHORT = 1;

    private static final User DESCRIBE_USER = new User();


    private Integer age;
    private String name;
    private String mobile;
    private short sex;

    public T testThrowException(@NonNull T generalType) throws NumberFormatException {
        return generalType;
    }
}
```
**编译后**
```
package com.momo.jdk.other.test.reflect;

import lombok.NonNull;

public class User<T> {
    private static String DESCRIBE_String = "test_user";
    private static final int DESCRIBE_INT = 32;
    private static final Integer DESCRIBE_INTEGER = Integer.valueOf(64);
    private static final short DESCRIBE_SHORT = 1;
    private static final User DESCRIBE_USER = new User();
    private Integer age;
    private String name;
    private String mobile;
    private short sex;

    public T testThrowException(@NonNull T generalType) throws NumberFormatException {
        if (generalType == null) {
            throw new NullPointerException("generalType");
        } else {
            return generalType;
        }
    }

    public User() {
    }

    public Integer getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    public String getMobile() {
        return this.mobile;
    }

    public short getSex() {
        return this.sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setSex(short sex) {
        this.sex = sex;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof User)) {
            return false;
        } else {
            User<?> other = (User)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$age = this.getAge();
                Object other$age = other.getAge();
                if (this$age == null) {
                    if (other$age != null) {
                        return false;
                    }
                } else if (!this$age.equals(other$age)) {
                    return false;
                }

                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                Object this$mobile = this.getMobile();
                Object other$mobile = other.getMobile();
                if (this$mobile == null) {
                    if (other$mobile != null) {
                        return false;
                    }
                } else if (!this$mobile.equals(other$mobile)) {
                    return false;
                }

                if (this.getSex() != other.getSex()) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        int PRIME = true;
        int result = 1;
        Object $age = this.getAge();
        int result = result * 59 + ($age == null ? 43 : $age.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $mobile = this.getMobile();
        result = result * 59 + ($mobile == null ? 43 : $mobile.hashCode());
        result = result * 59 + this.getSex();
        return result;
    }

    public String toString() {
        return "User(age=" + this.getAge() + ", name=" + this.getName() + ", mobile=" + this.getMobile() + ", sex=" + this.getSex() + ")";
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
```
## 日志相关的注解

 * lombok.extern.java.Log &#64;Log
 * lombok.extern.log4j.Log4j2 &#64;Log4j2
 * lombok.extern.apachecommons.CommonsLog &#64;CommonsLog
 * lombok.extern.java.Log &#64;Log
 * lombok.extern.slf4j.Slf4j &#64;Slf4j
 * lombok.extern.slf4j.XSlf4j &#64;XSlf4j
 * lombok.extern.jbosslog.JBossLog &#64;JBossLog

通常我们在添加日志时，先初始化一个logger
```
private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(LogExample.class);
```
使用`@Log4j`编译后生成
```
private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LogExample.class);
```
