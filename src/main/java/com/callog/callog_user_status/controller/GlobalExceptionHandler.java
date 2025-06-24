package com.callog.callog_user_status.controller;

import com.callog.callog_user_status.common.dto.ApiResponseDto;
import com.callog.callog_user_status.common.exception.StatNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StatNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponseDto<String> notFound(StatNotFoundException ex) {
        return ApiResponseDto.createError("NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseDto<Object> validation(MethodArgumentNotValidException ex) {
        var details = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> Map.of("field", fe.getField(),
                        "msg", fe.getDefaultMessage()))
                .toList();
        return ApiResponseDto.createError("BAD_REQUEST", "validation failed", details);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponseDto<String> others(Exception ex) {
        return ApiResponseDto.createError("INTERNAL_ERROR", ex.getMessage());
    }
}
