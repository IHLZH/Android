package com.example.kotlinstudy.utils;

public class ResultUtils<T> {

    public static<T> Result success(Integer code, String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
    public static<T> Result success(String msg, T data) {
        return success(200, msg, data);
    }
    public static<T> Result success(T data) {
        return success(200, "成功", data);
    }
    public static<T> Result success() {
        return success(200, "成功", null);
    }

    public static<T> Result error(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
    public static<T> Result error(String msg) {
        return error(500, msg);
    }

}
