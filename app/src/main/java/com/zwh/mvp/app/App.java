package com.zwh.mvp.app;

import com.mob.MobSDK;
import com.zwh.mvp.library.app.BaseAPP;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2018/08/20 16:24
 */

public class App extends BaseAPP {

    @Override
    public void onCreate() {
        super.onCreate();

        MobSDK.init(this);
    }
}
