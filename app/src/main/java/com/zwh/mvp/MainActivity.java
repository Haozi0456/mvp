package com.zwh.mvp;

import android.os.Bundle;
import android.widget.TextView;

import com.zwh.mvp.library.base.activity.BaseActivity;
import com.zwh.mvp.presenter.DataPresenter;
import com.zwh.mvp.view.DataView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<DataPresenter> implements DataView {

    DataPresenter dataPresenter;
    @BindView(R.id.texTV)
    TextView texTV;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    protected void viewCreated() {

    }

    @Override
    protected DataPresenter createPresenter() {
        return new DataPresenter();
    }

    @Override
    public void getDataFail(String failMsg) {

    }

    @OnClick(R.id.texTV)
    public void onClick() {
        presenter.getData("ll");
    }

}