package com.zwh.mvp.model;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.model.HttpHeaders;
import com.zwh.mvp.api.URLConfig;
import com.zwh.mvp.app.App;
import com.zwh.mvp.library.base.model.BaseModel;
import com.zwh.mvp.library.base.request.OkHelper;
import com.zwh.mvp.library.base.response.callback.JsonCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2018/08/20 16:11
 */

public class LoginModel extends BaseModel {

    public void login(String key, JsonCallback callback){
        Map<String, String> map = new HashMap<>();
        map.put("account","admin");
        map.put("password","123456");
        OkHelper.postRequest("dd",URLConfig.login,null,map,callback);
    }

    //会员列表
    public void getMemberList(Map<String,String> params, JsonCallback callback){
        HttpHeaders headers = new HttpHeaders();
        headers.put("token", App.userBean.getToken());
        OkHelper.getRequest("memberTag", URLConfig.member_list,headers,params,callback);
    }


    public void onDestroy() {
        ToastUtils.showShort("Model销毁");
    }
}
