package com.ideal.jzyx.decode;

/**
 * Created by Administrator on 2015/10/30.
 */
public class DecodeException extends Exception {
    public DecodeException() {
        super();
    }

    public DecodeException(String message) {
        super(message);
    }

    public DecodeException(String message, Throwable cause) {
        super(message, cause);
    }
}