package com.example.template.global.error.exception;

import com.example.template.global.error.ErrorCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Slf4j
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomException extends RuntimeException {
    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private ErrorCode errorCode;
    private String detailMessage;

    public CustomException(String message) {
        super(message);
        log.error(message);
    }

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        log.error(errorCode.getMessage());
    }

    public CustomException(ErrorCode errorCode, String message) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = message;
        log.error(String.format("%s -> %s", super.getMessage(), message));
    }

    public CustomException(ErrorCode errorCode, String message, Throwable throwable) {
        super(errorCode.getMessage(), throwable);
        this.errorCode = errorCode;
        this.detailMessage = message;
        log.error(String.format("%s -> %s", super.getMessage(), message));
    }

    public CustomException(ErrorCode errorCode, HttpStatus status) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.status = status;
    }
}
