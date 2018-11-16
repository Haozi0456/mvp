package com.zwh.mvp.library.app;

import android.app.Activity;
import android.app.Application;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.hjq.bar.ITitleBarStyle;
import com.hjq.bar.TitleBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.zwh.mvp.library.R;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2018/08/20 11:06
 */

public class BaseAPP extends Application {

    private static BaseAPP instance;
    private Set<Activity> allActivities;

    public static synchronized BaseAPP getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //初始化工具类
        Utils.init(this);
        //设置toast背景颜色
        ToastUtils.setBgColor(getResources().getColor(R.color.toast_stroke_gray));

        initTitleBar();

        initOkHttpConfig();
    }

    private void initTitleBar() {
        TitleBar.initStyle(new ITitleBarStyle() {
            @Override
            public int getTitleBarHeight() {
                return 0;
            }

            @Override
            public int getBackgroundColor() {
                return 0;
            }

            @Override
            public int getBackIconResource() {
                return R.mipmap.bar_icon_back_white;
            }

            @Override
            public int getLeftViewColor() {
                return 0xFFFFFFFF;
            }

            @Override
            public int getTitleViewColor() {
                return 0xFFFFFFFF;
            }

            @Override
            public int getRightViewColor() {
                return 0xFFFFFFFF;
            }

            @Override
            public float getLeftViewSize() {
                return 16;
            }

            @Override
            public float getTitleViewSize() {
                return 18;
            }

            @Override
            public float getRightViewSize() {
                return 16;
            }

            @Override
            public int getLeftViewBackground() {
                return 0;
            }

            @Override
            public int getRightViewBackground() {
                return 0;
            }

            @Override
            public boolean isLineVisible() {
                return true;
            }

            @Override
            public int getLineColor() {
                return 0xFFECECEC;
            }

            @Override
            public int getLineSize() {
                return 2;
            }
        });

    }

    /**
     * 设置OkHttp的配置参数
     */
    private void initOkHttpConfig() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);

        //非必要情况，不建议使用，第三方的开源库，使用通知显示当前请求的log，不过在做文件下载的时候，这个库好像有问题，对文件判断不准确
//        builder.addInterceptor(new ChuckInterceptor(this));

        //全局的读取超时时间 60秒
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间 60秒
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间 10秒
        builder.connectTimeout(10000, TimeUnit.MILLISECONDS);


        //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
//        //使用数据库保持cookie，如果cookie不过期，则一直有效
//        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));
        //使用内存保持cookie，app退出后，cookie消失
//        builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));


        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        HttpHeaders headers = new HttpHeaders();
//        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文，不允许有特殊字符
//        headers.put("commonHeaderKey2", "commonHeaderValue2");
        HttpParams params = new HttpParams();
//        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
//        params.put("commonParamsKey2", "这里支持中文参数");
//-------------------------------------------------------------------------------------//

        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers)                      //全局公共头
                .addCommonParams(params);                       //全局公共参数
    }


    /**
     * 添加activity
     */
    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    /**
     * 移除activity
     */
    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    /**
     * 退出app
     */
    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
