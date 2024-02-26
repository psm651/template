package com.example.template.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final Error error;

    public ErrorResponse(ErrorCode errorCode) {
        this.error = new Error(errorCode);
    }

    public ErrorResponse(ErrorCode errorCode, String detailMessage) {
        this.error = new Error(errorCode.toString(), errorCode.getMessage(), detailMessage);
    }

    @Getter
    @AllArgsConstructor
    public static class Error {
        private String code;
        private String message;
        private String detailMessage;

        public Error(ErrorCode errorCode) {
            this.code = errorCode.toString();
            this.message = errorCode.getMessage();
        }
    }
}
