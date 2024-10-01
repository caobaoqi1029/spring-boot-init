package com.mcddhub.init.common;

/**
 * 返回工具类
 *
 * @author: caobaoqi1029
 * @date: 2024/10/1 11:51
 * */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data data
     * @param <T> T
     * @return response
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode errorCode
     * @return response
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code code
     * @param message message
     * @return response
     */
    public static <T> BaseResponse<T> error(int code, String message) {
        return new BaseResponse<>(code, null, message);
    }

    /**
     * 失败
     *
     * @param errorCode errorCode
     * @return response
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode, String message) {
        return new BaseResponse<>(errorCode.getCode(), null, message);
    }
}
