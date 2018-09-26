package com.zwh.mvp.library.base.presenter;

import com.zwh.mvp.library.base.view.IBaseView;

/**
 * @author Zhaohao
 * @Description: MVP框架的简单封装 P处理层
 * @Date 2018/08/20 11:15
 */

public interface IBasePresenter<V extends IBaseView> {

    /**
     * 绑定视图
     *
     * @param view
     */
    void attachView(V view);

    /**
     * 解除绑定（每个V记得使用完之后解绑，主要是用于防止内存泄漏问题）
     */
    void detachView();
}
