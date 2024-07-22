package com.example.activitytest;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String id;
    private int age;

    public Student(){};
    public Student(String name, String id, int age){
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", age=" + age +
                '}';
    }
}
