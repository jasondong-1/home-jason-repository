package com.ideal.tagportrait.framework.web;

/**
 * Email: xingsen@join-cn.com
 * User: 邢森
 */
public class ResultMessage<T> {
    private String message;
    private T data;
    private boolean success;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
