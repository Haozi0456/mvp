package com.zwh.mvp.view;

import com.zwh.mvp.library.base.view.IBaseView;
import com.zwh.mvp.model.bean.UserBean;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2018/08/20 11:42
 */

public interface LoginView extends IBaseView {

    void loginReslut(UserBean userBean);

}
