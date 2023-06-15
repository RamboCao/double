package com.star.common.exception;

/**
 * @author Caolp
 */

public class BusinessException extends Throwable{
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    public BusinessException(String message) {
        this.message = message;
    }
}
