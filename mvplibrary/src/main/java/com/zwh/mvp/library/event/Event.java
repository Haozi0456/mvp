package com.zwh.mvp.library.event;

/**
 * @author Zhaohao
 * @Description: 泛型事件数据对象
 * @date 2018-10-10 21:38
 */
public class Event<T> {
    private int code; //事件代码
    private T data; // 泛型数据对象

    public Event(int code) {
        this.code = code;
    }

    public Event(int code, T data) {
        this.code = code;
        this.data = data;
    }

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
}
