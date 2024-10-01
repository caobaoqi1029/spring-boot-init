package com.mcddhub.init.exception;

import com.mcddhub.init.common.ErrorCode;

/**
 * BusinessException
 *
 * @version 1.0.0
 * @author: caobaoqi1029
 * @date: 2024/10/1 12:55
 */
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}

