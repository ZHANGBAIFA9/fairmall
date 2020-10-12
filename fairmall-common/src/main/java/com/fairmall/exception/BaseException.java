package com.fairmall.exception;

import com.fairmall.validator.ErrorType;
import com.fairmall.validator.impl.SystemErrorType;
import lombok.Getter;

/**
 * @Version 1.0
 * @Author ZHANGBAIFA
 * @Date 2020/10/10 17:44
 * @Description:
 */
@Getter
public class BaseException extends RuntimeException {
    /**
     * 异常对应的错误类型
     */
    private final ErrorType errorType;
    /**
     * 默认是系统异常
     */
    public BaseException() {
        this.errorType = SystemErrorType.SYSTEM_ERROR;
    }

    public BaseException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public BaseException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public BaseException(ErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }
}
