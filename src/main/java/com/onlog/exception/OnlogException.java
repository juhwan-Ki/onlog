package com.onlog.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class OnlogException extends RuntimeException{

    public final Map<String, String> validation = new HashMap<>();

    public OnlogException(String message) {
        super(message);
    }

    public OnlogException(String message, Throwable cause) {
        super(message, cause);
    }

    // 상위 예외처리 클래스에서 status code를 넘기는 메소드를 반드시 구현하도록 정의
    public abstract int getStatusCode();

    public void addValidation(String filedName, String message) {
        validation.put(filedName, message);
    }
}
