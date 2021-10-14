package com.zime.consumerclient.vo;

import com.zime.consumerclient.enums.ResultCodeEnum;

import java.io.Serializable;

/**
 *
 * @Author: lin
 * @Description: 响应结果生成工具
 */
public class ResultGenerator<T> implements Serializable {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    private static final long serialVersionUID = -1725910937242333499L;

    public static <T> ResultVo<T> genSuccessResult() {
        return new ResultVo<T>(ResultCodeEnum.SUCCESS, new Message("success", ""));
    }

    public static <T> ResultVo<T> genSuccessResult(T data) {
        return new ResultVo<T>(ResultCodeEnum.SUCCESS, new Message("success", ""), data);
    }

    public static <T> ResultVo<T> genSuccessResult(T data, String msg) {
        return new ResultVo<T>(ResultCodeEnum.SUCCESS, new Message("success", msg), data);
    }


    public static <T> ResultVo<T> genSuccessResult(T data, Pagination pagination) {
        return new ResultVo<T>(ResultCodeEnum.SUCCESS, data, pagination);
    }

    public static <T> ResultVo<T> genFailResult(String msg) {
        return new ResultVo<T>(ResultCodeEnum.SERVER_ERROR, new Message("error", msg));
    }

    public static <T> ResultVo<T> genRequestFailedResult(String msg) {
        return new ResultVo<T>(ResultCodeEnum.REQUEST_FAILED, new Message("error", msg));
    }

    public static <T> ResultVo<T> genRequestFailedResult() {
        return new ResultVo<T>(ResultCodeEnum.REQUEST_FAILED, new Message("error", null));
    }
}
