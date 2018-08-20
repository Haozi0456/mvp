package com.zwh.mvp.library.base.response;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2018/08/20 15:21
 */

public class Response {

    public int code;
    public String msg;

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
}
