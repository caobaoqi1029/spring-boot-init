package com.mcddhub.init.utils;

import com.mcddhub.init.common.ErrorCode;
import com.mcddhub.init.exception.BusinessException;

/**
 * ThrowUtils
 *
 * @version 1.0.0
 * @author: caobaoqi1029
 * @date: 2024/10/1 12:55
 */
public class ThrowUtils {

    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }
}
