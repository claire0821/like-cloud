package com.mdd.common.core;

import com.mdd.common.enums.HttpEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

//@Data
public class AjaxResult<T> implements Serializable {

    /** 状态码 **/
    private Integer code;

    /** 提示信息 **/
    private String msg;

    /** 响应数据 **/
    private T data;

    /** 无参构造 **/
    protected AjaxResult() {}

    /**
     * 带参构造
     *
     * @author fzr
     * @param code 状态码
     * @param msg 提示信息
     * @param data 响应数据
     */
    public AjaxResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @author fzr
     * @return AjaxResult
     */
    public static AjaxResult<Object> success() {
        return new AjaxResult<>(HttpEnum.SUCCESS.getCode(), HttpEnum.SUCCESS.getMsg(), new ArrayList<>());
    }

    /**
     * 成功返回结果
     *
     * @author fzr
     * @param code 状态码
     * @return AjaxResult
     */
    public static AjaxResult<Object> success(Integer code) {
        return new AjaxResult<>(code, HttpEnum.SUCCESS.getMsg(), new ArrayList<>());
    }

    /**
     * 成功返回结果
     *
     * @author fzr
     * @param msg 提示信息
     * @return AjaxResult
     */
    public static AjaxResult<Object> success(String msg) {
        return new AjaxResult<>(HttpEnum.SUCCESS.getCode(), msg, new ArrayList<>());
    }

    /**
     * 成功返回结果
     *
     * @author fzr
     * @param data 响应数据
     * @return AjaxResult
     */
    public static <T> AjaxResult<T> success(T data) {
        return new AjaxResult<>(HttpEnum.SUCCESS.getCode(), HttpEnum.SUCCESS.getMsg(), data);
    }

    /**
     * 成功返回结果
     *
     * @author fzr
     * @param code 状态码
     * @param msg 提示信息
     * @return AjaxResult
     */
    public static AjaxResult<Object> success(Integer code, String msg) {
        return new AjaxResult<>(code, msg, new ArrayList<>());
    }

    /**
     * 成功返回结果
     *
     * @author fzr
     * @param msg 提示信息
     * @param data 响应数据
     * @return AjaxResult
     */
    public static <T> AjaxResult <T> success(String msg, T data) {
        return new AjaxResult<>(HttpEnum.SUCCESS.getCode(), msg, data);
    }

    /**
     * 成功返回结果
     *
     * @author fzr
     * @param code 状态码
     * @param msg 提示信息
     * @param data 响应数据
     * @return AjaxResult
     */
    public static <T> AjaxResult<T> success(Integer code, String msg, T data) {
        return new AjaxResult<>(code, msg, data);
    }

    /**
     * 响应失败结果
     *
     * @author fzr
     * @param code 状态码
     * @return AjaxResult
     */
    public static AjaxResult<Object> failed(Integer code) {
        return new AjaxResult<>(code, HttpEnum.FAILED.getMsg(), new ArrayList<>());
    }

    /**
     * 响应失败结果
     *
     * @author fzr
     * @param msg 提示信息
     * @return AjaxResult
     */
    public static AjaxResult<Object> failed(String msg) {
        return new AjaxResult<>(HttpEnum.FAILED.getCode(), msg, new ArrayList<>());
    }

    /**
     * 响应失败结果
     *
     * @author fzr
     * @param data 响应数据
     * @return AjaxResult
     */
    public static <T> AjaxResult<T> failed(T data) {
        return new AjaxResult<T>(HttpEnum.FAILED.getCode(), HttpEnum.FAILED.getMsg(), data);
    }

    /**
     * 响应失败结果
     *
     * @author fzr
     * @param code 状态码
     * @param msg 提示信息
     * @return AjaxResult
     */
    public static AjaxResult<Object> failed(Integer code, String msg) {
        return new AjaxResult<>(code, msg, new ArrayList<>());
    }

    /**
     * 响应失败结果
     *
     * @author fzr
     * @param code 状态码
     * @param msg 提示信息
     * @param data 响应数据
     * @return AjaxResult
     */
    public static <T> AjaxResult<T> failed(Integer code, String msg, T data) {
        return new AjaxResult<>(code, msg, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
