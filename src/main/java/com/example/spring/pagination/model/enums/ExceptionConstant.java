package com.example.spring.pagination.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum ExceptionConstant {
    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "405 Method not allowed"),
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found"),
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION", "Unexpected exception occurred");
    private String code;
    private String message;
}
