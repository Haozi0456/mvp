package com.zwh.mvp.fragment;

import android.os.Bundle;

import com.zwh.mvp.R;
import com.zwh.mvp.library.base.activity.BaseTitleFragment;
import com.zwh.mvp.model.MineModel;
import com.zwh.mvp.presenter.HomePresenter;
import com.zwh.mvp.presenter.MinePresenter;
import com.zwh.mvp.view.HomeView;
import com.zwh.mvp.view.MineView;

public class MineFragment extends BaseTitleFragment<MinePresenter> implements MineView {


    @Override
    protected int getLayoutView() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void viewCreated(Bundle bundle) {
        setTitle("我的");
        isBackIconHidden(true);
    }

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter(new MineModel());
    }


    @Override
    public void result(String reslut) {

    }
}
