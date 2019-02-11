package com.zwh.mvp.library.base.activity;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.zwh.mvp.library.R;
import com.zwh.mvp.library.app.BaseAPP;
import com.zwh.mvp.library.event.Event;
import com.zwh.mvp.library.event.EventBusUtils;
import com.zwh.mvp.library.tools.listener.onTitleBarClikListener;

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
public abstract class BaseActivity extends AppCompatActivity {

    protected Context context;
    private Unbinder unbinder;
    private ProgressDialog progressDialog;
    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutView());
        context = this;
        unbinder = ButterKnife.bind(this);

        viewCreated(savedInstanceState);

        if (isRegisterEventBus()) {
            if(!EventBusUtils.isRegistered(this)){//加上判断
                EventBusUtils.register(this);
            }
        }

        BaseAPP.getInstance().addActivity(this);


    }

    @SuppressLint("InflateParams")
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(R.layout.activity_base_title, null);
        //设置填充activity_base布局
        super.setContentView(view);

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            view.setFitsSystemWindows(true);
        }

        //加载子类Activity的布局
        initDefaultView(layoutResID);
    }


    /**
     * 初始化默认布局的View
     *
     * @param layoutResId 子View的布局id
     */
    private void initDefaultView(int layoutResId) {
        titleBar =  findViewById(R.id.titleBar);
        FrameLayout container = findViewById(R.id.child_container);
        View childView = LayoutInflater.from(this).inflate(layoutResId, null);
        container.addView(childView, 0);
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

    /**
     * 设置左标题的文本大小
     */
    public void setLeftSize(int unit, float size) {
        titleBar.setLeftSize(unit,size);
    }

    /**
     * 设置标题的文本大小
     */
    public void setTitleSize(int unit, float size) {
        titleBar.setTitleSize(unit, size);
    }

    /**
     * 设置右标题的文本大小
     */
    public void setRightSize(int unit, float size) {
        titleBar.setRightSize(unit, size);
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
    protected void onDestroy() {
        super.onDestroy();

        if(unbinder != null){
            unbinder.unbind();
        }

        if (isRegisterEventBus()) {
            if(EventBusUtils.isRegistered(this)){
                EventBusUtils.unregister(this);
            }
        }

        BaseAPP.getInstance().removeActivity(this);
    }


    protected abstract int getLayoutView();

    protected abstract void viewCreated(Bundle savedInstanceState);


    public void showLoading(boolean isCancelable,String tip) {
        if(progressDialog != null){
            progressDialog.dismiss();
        }
        progressDialog = new ProgressDialog(context,R.style.progress_bar_cycle_dialog);
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

    public void hideLoading() {
        if(progressDialog != null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

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
