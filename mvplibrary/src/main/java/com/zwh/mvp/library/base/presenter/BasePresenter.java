package com.zwh.mvp.library.base.presenter;

import com.zwh.mvp.library.base.view.IView;


/**
 * @author Zhaohao
 * @Description:
 * @Date 2018/08/20 1:59
 */

public class BasePresenter<V extends IView> implements IPresenter<V> {

    protected V view;

    public V getView() {
        return view;
    }


    @Override
    public void attachView(V view) {
        this.view = view;

    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
