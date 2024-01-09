package com.onlog.exception;

import lombok.Getter;

// status -> 400
@Getter
public class InvalidRequest extends OnlogException{

    private static final String MESSAGE = "잘못된 요청입니다.";

    public InvalidRequest() {
        super(MESSAGE);
    }

    public InvalidRequest(String filedName, String message) {
        super(MESSAGE);
        addValidation(filedName, message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
