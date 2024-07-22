package com.example.gson.Entity;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private String name;
    private int id;
    private Date date;
    private Grade grade;
    public Student(){
    }

    public Student(String name, int id, Date date) {
        this.name = name;
        this.id = id;
        this.date = date;
    }

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", date=" + date +
                ", grade=" + grade +
                '}';
    }
}
