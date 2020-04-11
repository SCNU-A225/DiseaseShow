package com.a225.diseaseshow.bean;

/*
Controller 返回结果类
 */
public class ResultRes {

    //状态码
    private int code;
    //状态信息
    private String msg;
    //返回数据
    private Object data;

    public ResultRes(int code, String msg){
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public ResultRes(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultRes{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
