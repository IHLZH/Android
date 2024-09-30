package com.example.kotlinstudy

class MyKotlin2 {

}

fun main(){
    //1.var 和 val
    var x : Int = 1;
    val a : Int = 2;
    x = 2;
    println(x);

    val str : String = "hello world";
    println(str);

    //2.类型推断
    val str2 = "hello world2";
    //str2.inc();

    //3.null安全 默认情况下，kotlin变量不能为null值
    //可为null值，必须在变量类型后加上？

    //Kotlin提供了严格的可为null性规则，默认情况下，Kotlin变量不可位null值，如果想使变量可为null，需要在基本类型的末尾加上？
    //表示这个变量可能为null值，对于可为null值的变量，Kotlin提供了严格的null值处理机制

    //大多数Android api是由java编写的，因此在编写Android应用时，可能会出现在Kotlin中调用非Kotlin代码的情况
    //java代码没有严格的可为null值规则，因此在Kotlin中引用java类中的变量时，如果没有注解修饰，编译器不知道变量是否可为null
    //在java代码中，可为null值的变量用@Nullable修饰，不可能为null值的变量用@NonNull修饰
//    val str4 : String = null;
    //val str7 : String = Comic().name;
    var str3 : String? = null;

    //处理可为null性
    //3.1 ?.安全调用方法，调用者为null时，返回null
    println(str3?.length);
    //3.1 !! 非null断言运算符 str3为null时报空指针异常
    str3 = "str3非null"
    println(str3!!.length);
    //3.2 ?: Elvis运算符
    str3 = null
    val str3Length : Int = str3?.length ?: 0;
    println(str3Length)


    var count : Int = 1;
    //4.条件语句
    var str4 : String = if(count == 1){
        "hello"
    }else if(count == 2){
        "world"
    }else{
        "hello world"
    };
    println(str4);

    count = 3;
    str4 = when{
        count == 1 -> "hello"
        count > 2 -> "world"
        else -> "hello world"
    };
    println(str4);

    //4.1 智能类型转换
    var str6 : String? = "xdyd";
    //str6可能为null
    println(str6?.length)
    //str6一定不会为null
    if(str6 != null){
        println(str6.length)
    }


    println(getStringLength("android"))

    println(stringMapper("android nb", getStringLength))
    println(stringMapper("android nb") { input -> input.length })
    println(stringMapper("android nb", ::getMapper))
    println("mapper")

    val person : Person = Person("xdyd", 18);
    println(person.name)


}

// 5.函数
fun getAnswerString(): String {
    var count : Int = 1;
    val ans: String = when{
        count == 1 -> "hello world"
        count < 1 -> "xxx"
        else -> "yyy"
    };
    return ans
}

fun getAnswerString(count : Int) : String{
    val ans: String = when{
        count == 1 -> "hello world"
        count < 1 -> "xxx"
        else -> "yyy"
    };
    return ans
}

//5.1 简化函数声明 将 return 关键字替换为赋值运算符
fun getAnswerStringByCount(count : Int) : String = when{
    count == 1 -> "hello world"
    count < 1 -> "xxx"
    else -> "yyy"
}

//5.2 匿名函数 通过输入输出直接表示
val getStringLength : (String) -> Int = {
    input -> input.length
}

val getStrLength = fun (str : String) : Int{
    return str.length
}


//5.3 高阶函数 将其他函数作为参数的函数 下面这个函数需要接收一个字符串和一个函数
fun stringMapper(str : String, mapper : (String) -> Int) : Int {
    return mapper(str);
}

fun getMapper(str : String) : Int{
    return str.length
}

//6. 类 构造方法
class Person(var name : String, var age : Int){

    //成员方法
    fun getName() : Int{
        return name.length
    }

//    fun setName(name : String){
//        this.name = name
//    }

    //内部类
    class man(){

    }
}

class Man(name : String, age : Int){
    var myName : String
    var myAge : Int

    init{
        myName = name
        myAge = age

        println(myName)
        println(myAge)
    }
}

class Person1(fName: String, personAge: Int) {
    val firstName: String
    var age: Int

    //初始化块
    init {
        firstName = fName.capitalize()
        age = personAge

        println("First Name = $firstName")
        println("Age = $age")
    }
}





//6.1 封装
class Car(){
    private var doorLock : Boolean = true;

    fun unLock(key : Int){
        if(key == 1)doorLock = false;
    }

    fun Lock(key : Int){
        if(key == 2)doorLock = true;
    }
}

