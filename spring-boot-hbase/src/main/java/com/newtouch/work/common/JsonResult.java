package com.newtouch.work.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: JsonResult
 * @description: 格式化回参
 * @author: yepengfei
 * @create: 2021/3/19、9:46
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
public class JsonResult<T> implements Serializable {
    private boolean status; // 状态码

    private String message;  // 提示信息

    private T result;  // 返回结果

}
