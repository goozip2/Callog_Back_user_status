package com.callog.callog_user_status.common.exception;

public class StatNotFoundException extends ClientError {
    public StatNotFoundException(String message) {
        this.errorCode = "StatNotFound";
        this.errorMessage = message;
    }
}