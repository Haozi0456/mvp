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
import com.zwh.mvp.library.base.presenter.IPresenter;
import com.zwh.mvp.library.base.view.IView;
import com.zwh.mvp.library.tools.listener.onTitleBarClikListener;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * @author Zhaohao
 * @Date 2018/08/27 10:40
 * @Description: BaseTitleActivity 带标题栏的基类activity
 */
public abstract class BaseTitleActivity<P extends IPresenter> extends AppCompatActivity implements IView {

    protected P presenter;
    protected Context context;
    private Unbinder unbinder;
    private ProgressDialog progressDialog;
    private TitleBar titleBar;

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
        BaseAPP.getInstance().addActivity(this);
        viewCreated();
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
        if (presenter != null){
            presenter.detachView();
        }
        unbinder.unbind();
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
}
