package com.example.template.global.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_INPUT_VALUE("Invalid input value"),
    CONTENT_TYPE_NOT_ALLOWED("Content-type not allowed"),
    METHOD_NOT_ALLOWED("Method not allowed"),
    ENTITY_NOT_FOUND("Entity not found"),
    REQUEST_REJECTED("Invalid request url"),
    NUMBER_FORMAT_EXCEPTION("Number format exception"),
    REQUEST_FAILED("Request failed"),
    RESPONSE_STATUS_CODE_IS_NOT_200("Response status code is not 200"),

    // 인증
    AUTHORIZATION_HEADER_NOT_FOUND("Authorization header not found"),
    INVALID_AUTHENTICATION_TYPE("Unsupported authentication type"),
    INVALID_TOKEN("Invalid token"),
    EXPIRED_TOKEN("Expired token"),
    UNKNOWN_USER("Unknown user"),
    UNKNOWN_VIRTUAL_HOST("Unknown virtual host"),

    UNIQUE_CONSTRAINT_VIOLATION("Duplicate key value violates unique constraint"),

    //------------------ domain error--------------------
    SAMPLE_NOT_FOUND("sample이 없습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public static ErrorCode valueOfOrNull(String name) {
        try {
            return valueOf(name);
        } catch (Exception e) {
            return null;
        }
    }
}
