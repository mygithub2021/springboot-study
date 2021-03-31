package com.newtouch.work.utils;

import com.newtouch.work.common.JsonResult;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ResultUtil
 * @description: JsonResult 封装功能工具类
 * @author: yepengfei
 * @create: 2021/3/19、9:55
 * @Version 1.0
 **/
@Getter
public class ResultUtil {
    private ResultUtil(){}

    final static String EMPTY_MESSAGE = "数据内容为空";

    final static List EMPT_LIST = new ArrayList<>(0);

    public static JsonResult success(String message) {
        return new JsonResult<String>(true, message, null);
    }

    public static <T> JsonResult<T> success(T result) {
        return new JsonResult<>(true, "数据获取成功", result);
    }

    public static <T> JsonResult<T> success(String message, T result) {
        return new JsonResult<>(true, message, result);
    }

    public static JsonResult empty() {
        return new JsonResult<>(true, EMPTY_MESSAGE, EMPT_LIST);
    }

    public static JsonResult empty(String message) {
        return new JsonResult<>(true, message, null);
    }

    public static JsonResult fail(String message) {
        return new JsonResult<>(false, message, null);
    }

    public static <T> JsonResult<T> fail(String message, T result) {
        return new JsonResult<>(false, message, result);
    }

    public static JsonResult fail(Throwable e) {
        return fail(e.getMessage());
    }


}
