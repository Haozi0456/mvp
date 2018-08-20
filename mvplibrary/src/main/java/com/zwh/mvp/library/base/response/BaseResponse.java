package com.zwh.mvp.library.base.response;

/**
 * @author Zhaohao
 * @Description: 数据请求返回
 * @Date 2018/08/20 11:07
 */

public class BaseResponse<T> extends Response{

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
