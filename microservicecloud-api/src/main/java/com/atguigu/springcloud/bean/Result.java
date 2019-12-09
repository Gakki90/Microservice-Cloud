package com.atguigu.springcloud.bean;

import com.alibaba.fastjson.JSONObject;

public class Result {
    private Integer code;
    private Boolean success;
    private String msg;
    private JSONObject jsonObject;

    public Result(Integer code, Boolean success, String msg, JSONObject jsonObject) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.jsonObject = jsonObject;
    }

    public Result(Integer code, Boolean success, String msg) {
        this.code = code;
        this.success = success;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
