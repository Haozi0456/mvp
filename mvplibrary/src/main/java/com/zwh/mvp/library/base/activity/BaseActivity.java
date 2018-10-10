package com.zwh.mvp.library.base.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zwh.mvp.library.R;
import com.zwh.mvp.library.app.BaseAPP;
import com.zwh.mvp.library.base.presenter.IBasePresenter;
import com.zwh.mvp.library.base.view.IBaseView;
import com.zwh.mvp.library.event.Event;
import com.zwh.mvp.library.event.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * @author Zhaohao
 * @Date 2018/08/27 10:39
 * @Description: BaseActivity 基类Activity
 */
public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {

    protected P presenter;
    protected Context context;
    private Unbinder unbinder;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutView());

        unbinder = ButterKnife.bind(this);
        context = this;
        presenter = createPresenter();

        if (presenter != null) {
            presenter.attachView(this);
        }else{
            throw new IllegalStateException("Please call mPresenter in BaseMVPActivity(createPresenter) to create!");
        }

        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }

        BaseAPP.getInstance().addActivity(this);

        viewCreated();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter.detachView();
        }
        unbinder.unbind();

        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }

        BaseAPP.getInstance().removeActivity(this);
    }


    protected abstract int getLayoutView();

    protected abstract void viewCreated();

    protected abstract P  createPresenter();

    @Override
    public void showLoading(boolean isCancelable,String tip) {
        if(progressDialog != null){
            progressDialog.dismiss();
        }
        progressDialog = new ProgressDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.progress_view,null);
        TextView tipTextView = view.findViewById(R.id.tipTV);
        if(!StringUtils.isEmpty(tip)){
            tipTextView.setText(tip);
        }
        progressDialog.setProgressDrawable(new BitmapDrawable());
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(isCancelable);
        if(!progressDialog.isShowing()){
            progressDialog.show();
            progressDialog.setContentView(view);
        }
    }

    @Override
    public void hideLoading() {
        if(progressDialog != null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showShort(msg);
    }

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(Event event) {

    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(Event event) {

    }

}
