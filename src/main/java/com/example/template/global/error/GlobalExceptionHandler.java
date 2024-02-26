package com.example.template.global.error;

import com.example.template.global.error.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();
        String message = e.getMessage();
        String detailMessage = e.getDetailMessage();
        HttpStatus status = e.getStatus();
        ErrorResponse errorResponse = new ErrorResponse(errorCode, detailMessage);
        return new ResponseEntity<>(errorResponse, status);
    }

    // 지원하지 않는 method 405 error
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    // @RequestBody validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : e.getAllErrors()) {
            FieldError fieldError = (FieldError) error;
            sb.append(fieldError.getField()).append(" : ").append(fieldError.getDefaultMessage()).append(" ");
        }
        ErrorResponse errorResponse = getErrorResponseByMessage(sb.toString());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // @ModelAttribute validation error
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorResponse errorResponse = getErrorResponseByMessage(message);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public ErrorResponse getErrorResponseByMessage(String message) {
        ErrorCode errorCode = ErrorCode.valueOfOrNull(message);

        if (errorCode == null) {
            return new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, message);
        } else {
            return new ErrorResponse(errorCode);
        }
    }
}

