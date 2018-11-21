package com.zwh.mvp.presenter;

import com.lzy.okgo.model.Response;
import com.zwh.mvp.api.BackPageResponse;
import com.zwh.mvp.library.base.presenter.BasePresenter;
import com.zwh.mvp.library.base.response.BaseResponse;
import com.zwh.mvp.library.base.response.callback.JsonCallback;
import com.zwh.mvp.model.LoginModel;
import com.zwh.mvp.model.bean.UserBean;
import com.zwh.mvp.view.LoginView;

import java.util.Map;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2018/08/20 11:37
 */

public class LoginPresenter extends BasePresenter<LoginModel,LoginView> {

    public LoginPresenter(LoginModel model) {
        super(model);
    }

////    private LoginModel dataModel;
//
//    public LoginPresenter(IBaseModel model) {
//        super(model);
//    }

//    public LoginPresenter(new LoginModel()) {
//        this.dataModel = new LoginModel();
//    }

    public void login(String key){
        view.showLoading(false,"登录中,请稍候...");
        model.login(key,new JsonCallback<BaseResponse<UserBean>>(){
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

    public void getMemberList(Map<String,String> params){
        model.getMemberList(params,new  JsonCallback<BackPageResponse<UserBean>>(){
            @Override
            public void onSuccess(Response<BackPageResponse<UserBean>> response) {
                if(100 == response.body().getCode()){
                    if(getView() != null){

                    }

                }else{
                    if(getView() != null){
                        getView().showToast(response.body().getMsg());

                    }

                }
            }


        });
    }

}
