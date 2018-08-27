package com.zwh.mvp.presenter;

import com.lzy.okgo.model.Response;
import com.zwh.mvp.model.bean.UserBean;
import com.zwh.mvp.library.base.presenter.BasePresenter;
import com.zwh.mvp.library.base.response.BaseResponse;
import com.zwh.mvp.library.base.response.callback.JsonCallback;
import com.zwh.mvp.model.LoginModel;
import com.zwh.mvp.view.LoginView;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2018/08/20 11:37
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginModel dataModel;

    public LoginPresenter() {
        this.dataModel = new LoginModel();
    }

    public void login(String key){
        view.showLoading(false,"登录中,请稍候...");
        dataModel.login(key,new JsonCallback<BaseResponse<UserBean>>(){
            @Override
            public void onSuccess(Response<BaseResponse<UserBean>> response) {
                view.hideLoading();
                if(response.body().code == 100){
                    view.loginReslut(response.body().getData());
                }else{
                    view.showToast(response.body().msg);
                }

            }
        });
    }

}
