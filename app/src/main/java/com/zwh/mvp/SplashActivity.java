package com.zwh.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.zwh.mvp.library.base.activity.BaseActivity;

public class SplashActivity extends BaseActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // 隐藏标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        // 隐藏状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_splash);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent mainIntemt = new Intent(SplashActivity.this, LoginActivity.class);
//                SplashActivity.this.startActivity(mainIntemt);
//                SplashActivity.this.finish();
//                //实现淡入浅出的效果
////                overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
////                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
//            }
//
//        }, 2000);
//    }

    @Override
    protected int getLayoutView() {
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    protected void viewCreated(Bundle savedInstanceState) {
        isTitleBarHidden(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntemt = new Intent(SplashActivity.this, LoginActivity.class);
                SplashActivity.this.startActivity(mainIntemt);
                SplashActivity.this.finish();
                //实现淡入浅出的效果
//                overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
//                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }

        }, 2000);
    }
}
