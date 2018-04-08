package com.wzh.paper.entity;

public class Result {
    private int code;
    private String tip;

    public static class ResultCode{
        public static final int SEND_SUCCESS = 101;
        public static final int SEND_FAIL = 102;
        public static final int RECEIVE_SUCCESS = 201;
        public static final int RECEIVE_FAIL = 202;
    }

    public Result(int code, String tip){
        this.code = code;
        this.tip = tip;
    }

    public Result(){}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
