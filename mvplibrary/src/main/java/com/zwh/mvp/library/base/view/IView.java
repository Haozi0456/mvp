package com.zwh.mvp.library.base.view;

/**
 * @author Zhaohao
 * @Description: 一般的Activity中要用到View操作无非是显示加载框、
 *               影藏加载框、显示出错信息、显示当数据为空的时候的view之类的
 * @Date 2018/08/20 11:13
 */

public interface IView {

    void showToast(String msg);

    void showLoading();

    void hideLoading();
}
