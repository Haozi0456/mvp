package com.zwh.mvp.library.base.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.zwh.mvp.library.R;
import com.zwh.mvp.library.base.presenter.IBasePresenter;
import com.zwh.mvp.library.base.view.IBaseView;
import com.zwh.mvp.library.event.Event;
import com.zwh.mvp.library.event.EventBusUtil;
import com.zwh.mvp.library.tools.listener.onTitleBarClikListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * @author Zhaohao
 * @Date 2018/08/27 10:39
 * @Description: BaseFragment 带标题的基类Fragment
 */
public abstract class BaseTitleFragment<P extends IBasePresenter> extends Fragment implements IBaseView {

    protected P presenter;
    protected Context context;
    private Unbinder unbinder;
    private View mView;
    private TitleBar titleBar;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_base_title, container, false);
        this.context = getActivity();

        ViewGroup parent = (ViewGroup) mView.getParent();
        if (null != parent) {
            parent.removeView(mView);
        }

        addChildView(inflater);

        unbinder = ButterKnife.bind(this, mView);
        presenter = createPresenter();

        if (presenter != null) {
            presenter.attachView(this);
        }else{
            throw new IllegalStateException("Please call mPresenter in BaseMVPActivity(createPresenter) to create!");
        }


        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }

        viewCreated();
        return mView;
    }

    /**
     * 添加子Fragment的布局文件
     * @param inflater
     */
    private void addChildView(LayoutInflater inflater) {
        titleBar =  mView.findViewById(R.id.titleBar);
        FrameLayout container =  mView.findViewById(R.id.fragment_child_container);
        View child = inflater.inflate( getLayoutView(), null);
        container.addView(child, 0);
    }

    public void isTitleBarHidden(boolean isHidden) {
        if(isHidden){
            titleBar.setVisibility(View.GONE);
        }
    }

    public void isBackIconHidden(boolean isHidden){
        if(isHidden){
            titleBar.setLeftIcon(null);
            titleBar.setLeftTitle("");
        }
    }

    public void setTitle(CharSequence text) {
        titleBar.setTitle(text);
    }

    public void setLeftTitle(CharSequence text) {
        titleBar.setLeftTitle(text);
    }

    public void setRightTitle(CharSequence text) {
        titleBar.setRightTitle(text);
    }

    public void setLeftIcon(Drawable drawable) {
        titleBar.setLeftIcon(drawable);
    }

    public void setRightIcon(Drawable drawable) {
        titleBar.setRightIcon(drawable);
    }

    public void setTitleBarBackground(int color){
        titleBar.setBackgroundColor(color);
    }

    
    public void setTitleTextColor(int color){
        titleBar.getTitleView().setTextColor(color);
    }

    public void setSizeTitle(float size){
        titleBar.getTitleView().setTextSize(size);
    }

    public void setSizeLeft(float size){
        titleBar.getLeftView().setTextSize(size);
    }

    public void setSizeRight(float size){
        titleBar.getRightView().setTextSize(size);
    }

    public void setLeftTilteColor(int color){
        titleBar.getLeftView().setTextColor(color);
    }

    public void setRightTitleColor(int color){
        titleBar.getRightView().setTextColor(color);
    }

    public void setTitleBarClickListener(final onTitleBarClikListener listener){
        if(listener != null){
            titleBar.setOnTitleBarListener(new OnTitleBarListener() {
                @Override
                public void onLeftClick(View v) {
                    listener.onLeftClick(v);
                }

                @Override
                public void onTitleClick(View v) {
                    listener.onTitleClick(v);
                }

                @Override
                public void onRightClick(View v) {
                    listener.onRightClick(v);
                }
            });
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter.detachView();
        }
        unbinder.unbind();


        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
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
