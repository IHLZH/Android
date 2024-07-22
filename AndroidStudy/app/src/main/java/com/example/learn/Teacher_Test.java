package com.example.learn;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Teacher_Test {
    public static final int dm = 1;
    public static final int ww = 2;
    @IntDef(value = {dm, ww})
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.CLASS)
    public @interface Teacher{
    }
    public static void test(@Teacher int teacher){
        System.out.println(teacher);
    }
    public static void main(String[] args) {
        test(dm);
    }
}
