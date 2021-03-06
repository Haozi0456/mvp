package com.zwh.mvp.library.event;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Zhaohao
 * @Description: eventBus工具类
 * @date 2018-10-10 21:40
 */
public class EventBusUtils {

    /**
     * 注册事件
     * @param subscriber
     */
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    /**
     * 解除事假注册
     * @param subscriber
     */
    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    /**
     * 判断是否已经注册
     * @param subscriber
     * @return
     */
    public static boolean isRegistered(Object subscriber){
        return EventBus.getDefault().isRegistered(subscriber);
    }

    /**
     * 发送普通事件
     * @param event
     */
    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 发送粘性事件
     * @param event
     */
    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }

}
