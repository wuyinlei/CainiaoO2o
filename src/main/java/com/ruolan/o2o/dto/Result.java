package com.ruolan.o2o.dto;

public class Result<T> {

    private boolean success = false;  //是否成功的标志
    private T data;  //成功时候返回的数据

    private String errMsg;  //错误的时候的信息
    private int errCode;  //错误码

    public Result(){

    }

    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    // 错误时的构造器
    public Result(boolean success, int errorCode, String errorMsg) {
        this.success = success;
        this.errMsg = errorMsg;
        this.errCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errMsg = errorMsg;
    }

    public int getErrorCode() {
        return errCode;
    }

    public void setErrorCode(int errorCode) {
        this.errCode = errorCode;
    }

}
