package com.zwh.mvp.fragment;

import com.zwh.mvp.R;
import com.zwh.mvp.library.base.activity.BaseTitleFragment;
import com.zwh.mvp.presenter.HomePresenter;
import com.zwh.mvp.view.HomeView;

public class HomeFragment extends BaseTitleFragment<HomePresenter> implements HomeView {


    @Override
    protected int getLayoutView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void viewCreated() {
        setTitle("首页");
        isBackIconHidden(true);
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void result(String reslut) {

    }
}
