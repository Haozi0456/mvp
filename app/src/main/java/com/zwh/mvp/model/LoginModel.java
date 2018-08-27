package com.zwh.mvp.model;

import com.zwh.mvp.api.URLConfig;
import com.zwh.mvp.library.base.model.IModel;
import com.zwh.mvp.library.base.request.OkHelper;
import com.zwh.mvp.library.base.response.callback.JsonCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2018/08/20 16:11
 */

public class LoginModel implements IModel {

    public void login(String key, JsonCallback callback){
        Map<String, String> map = new HashMap<>();
        map.put("account","admin");
        map.put("password","123456");
        OkHelper.postRequest(URLConfig.login,map,callback);
    }
}
