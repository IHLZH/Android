package utils;

import cn.hutool.json.JSONUtil;

public class ResultUtils {
    public static<T> String success(Integer code, String msg, T data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return JSONUtil.toJsonStr(result);
    }
    public static String error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return JSONUtil.toJsonStr(result);
    }
}
