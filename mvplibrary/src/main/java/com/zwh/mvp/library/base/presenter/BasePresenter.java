package com.zwh.mvp.library.base.presenter;

import com.zwh.mvp.library.base.model.BaseModel;
import com.zwh.mvp.library.base.view.IBaseView;


/**
 * @author Zhaohao
 * @Description:
 * @Date 2018/08/20 1:59
 */

public class BasePresenter<M extends BaseModel, V extends IBaseView> implements IBasePresenter<V> {

    protected V view;
    protected M model;

    public BasePresenter(M model) {
        this.model = model;
    }

    public V getView() {
        if (view == null) throw new IllegalStateException("view not attached,视图不附/销毁");
        return view;
    }


    public void attachView(V view) {

        this.view = view;

    }


    public void detachView() {
        //解绑V层 避免导致内存泄漏
        this.view = null;
        if (this.model != null) {
            this.model.onDestroy();
        }
        this.model = null;
    }
}
