package com.clp.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND("问题找不到了，重新试试");


    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
