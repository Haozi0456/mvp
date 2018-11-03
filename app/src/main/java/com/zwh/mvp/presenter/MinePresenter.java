package com.zwh.mvp.presenter;

import com.zwh.mvp.library.base.presenter.BasePresenter;
import com.zwh.mvp.model.MineModel;
import com.zwh.mvp.view.HomeView;
import com.zwh.mvp.view.MineView;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2018/08/27 15:16
 */

public class MinePresenter extends BasePresenter<MineModel,MineView> {

    public MinePresenter(MineModel model) {
        super(model);
    }
}
