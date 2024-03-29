package com.onlog.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/*
* {
*   "code": "400",
*   "message": "잘못된 요청입니다",
*   "validation": {
*       "title": "값을 입력해주세요:
*   }
* */
@Getter
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY) // 비어있는 값은 제외하고 전달
public class ErrorResponse {

    private final String code;
    private final String message;
    private final Map<String, String> validation;

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation;
    }

    public void addValidation(String filedName, String errorMessage) {
        this.validation.put(filedName, errorMessage);
    }
}
