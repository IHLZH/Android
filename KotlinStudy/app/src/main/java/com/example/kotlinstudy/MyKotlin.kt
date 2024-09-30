package com.example.kotlinstudy

public class MyKotlin{

}

fun add(a : Int, b : Int){
    println(a + b);
}

fun prt(a : Int, b : Int, c : String){
    println(c + (a + b));
}

fun prtans(count : Int) : String{
    val ans : String = if(count > 0){
        "coutn > 0";
    }else if(count == 0){
        "count == 0";
    }else "count < 0";

    return ans;
}

fun prtans2(count : Int) : String{
    val ans2 = when{
        count > 0 -> "count > 0";
        count == 0 -> "count == 0";
        else -> "count < 0";
    }
    return ans2;
}

fun checkNull(str : String){
    val str : String? = null;
    if(str != null){
        println(str.toUpperCase());
    }
}

fun getAnsString(count : Int) : String{
    return if(count > 0){
        "count > 0";
    }else if(count == 0){
        "count == 0";
    }else "count < 0";
}

fun getAnsString2(count : Int) : String = if(count > 0){
        "count > 0";
    }else if(count == 0){
        "count == 0";
    }else "count < 0";
// 匿名函数
val getStringLengthFun : (String) -> Int = {
    input -> input.length;
}
// 高阶函数，函数的参数为函数，参数函数的具体功能在调用时写明
fun getStringMapper (str : String, mapper : (String) -> Int) : Int{
    return mapper(str);
}
//匿名高阶函数

fun main() {
    var a : Int = 1;
    var b : Int = 2;
    val str : String = "Kotlin";
    //add(1, 2);
    //prt(a, b, str);

    var str2 : String? = null;
    //println(prtans(1));
    //println(prtans2(0));
    //println(getAnsString(1));
    //println(getAnsString2(0));
    //println(getStringLengthFun("Kotlin"));

    println(getStringMapper("Kotlin", {input -> input.length}));
    println(getStringMapper("Kotlin"){input -> input.length});

    //类型转换
    var c : Long = 1;


}

class Student1(age : Int, gender : Int, id : String){

    private var age : Int = age;
    private var gender : Int = gender;
    private var id : String = id;

    fun getAge() : Int{
        return this.age;
    }

    fun setAge(age : Int) {
        this.age = age;
    }

}
