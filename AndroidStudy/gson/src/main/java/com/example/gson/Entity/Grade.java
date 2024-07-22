package com.example.gson.Entity;

import java.io.Serializable;

public class Grade implements Serializable {
    private int math;
    private int code;

    public Grade() {
    }

    public Grade(int math, int code) {
        this.math = math;
        this.code = code;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "math=" + math +
                ", code=" + code +
                '}';
    }
}
