package com.zwh.mvp.library.tools.listener;

import android.view.View;


/**
 * @author Zhaohao
 * @Description:
 * @Date 2018/08/27 11:19
 */

public interface onTitleBarClikListener {

    /**
     * 左项被点击
     */
     void onLeftClick(View v);

    /**
     * 标题被点击
     */
    void onTitleClick(View v);

    /**
     * 右项被点击
     */
    void onRightClick(View v);
}
