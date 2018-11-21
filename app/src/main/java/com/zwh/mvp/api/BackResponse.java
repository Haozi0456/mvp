package com.zwh.mvp.api;

/**
 * @author Zhaohao
 * @Description:
 * @date 2018-11-19 15:30
 */
public class BackResponse<T> {

    /**
     * code : 0
     * data : {}
     * msg : string
     */

    private int code;
    private T data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
