package com.callog.callog_user_status.common.exception;

import com.callog.callog_user_status.common.exception.ClientError;

public class NotFound extends ClientError {
    public NotFound(String message) {
        this.errorCode = "NotFound";
        this.errorMessage = message;
    }
}
