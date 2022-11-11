package com.conmu.web;


import com.conmu.exception.AppCode;
import com.conmu.exception.AppRuntimeException;
import com.conmu.utils.ContextLogBean;

import java.io.Serializable;

/**
 * web返回结构统一处理
 *
 * @author yine
 * @createTime 2021/4/20 10:12 上午
 * @description
 */
public class Response<T> implements Serializable {

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 描述信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    private Response() {

    }

    private Response(AppCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }

    public static Response<Void> success() {
        return new Response<Void>(AppCode.OK, null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<T>(AppCode.OK, data);
    }

    public static <T> Response<T> success(AppCode responseCode, T data) {
        return new Response<>(responseCode, data);
    }

    public static <T> Response<T> failure() {
        return new Response<>(AppCode.INTERNAL_ERROR, null);
    }

    public static <T> Response<T> failure(AppCode resultStatus) {
        return failure(resultStatus, null);
    }

    public static <T> Response<T> failure(AppCode resultStatus, T data) {
        if (resultStatus == null) {
            return new Response<T>(AppCode.INTERNAL_ERROR, null);
        }
        return new Response<T>(resultStatus, data);
    }

    public static <T> Response<T> exceptionI18N(AppRuntimeException ex, String language) {
        Response response = new Response<>();
        response.code = ex.getErrorCode();
        response.message = ex.getErrorMessageI18N(language);
        response.data = null;
        ContextLogBean.setCode(ex.getErrorCode());
        ContextLogBean.addProp("errorMessage", ex.getErrorMessage());
        return response;
    }

    public static <T> Response<T> exception(AppRuntimeException ex) {
        Response response = new Response<>();
        response.code = ex.getErrorCode();
        response.message = ex.getErrorMessage();
        response.data = null;
        ContextLogBean.setCode(ex.getErrorCode());
        ContextLogBean.addProp("errorMessage", ex.getErrorMessage());
        return response;
    }

    public static Response<String> unknown(Exception e) {
        Response<String> response = new Response<>();
        response.setCode(AppCode.UNKNOWN.getCode());
        //response.setMessage(e.getMessage());具体堆栈信息不应该返回给前向
        ContextLogBean.setCode(AppCode.UNKNOWN.getCode());
        ContextLogBean.addProp("errorMessage", e.getMessage());
        return response;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}