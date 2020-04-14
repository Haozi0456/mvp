package com.zwh.mvp;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zwh.easy.permissions.EasyPermission;
import com.zwh.easy.permissions.PermissionCallback;
import com.zwh.easy.permissions.PermissionItem;
import com.zwh.mvp.acitivity.AppActivity;
import com.zwh.mvp.app.App;
import com.zwh.mvp.library.base.activity.BaseMVPActivity;
import com.zwh.mvp.library.tools.listener.onTitleBarClikListener;
import com.zwh.mvp.library.widget.ClearnEditText;
import com.zwh.mvp.library.widget.PasswordEditText;
import com.zwh.mvp.model.LoginModel;
import com.zwh.mvp.model.bean.UserBean;
import com.zwh.mvp.presenter.LoginPresenter;
import com.zwh.mvp.view.LoginView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.core.content.res.ResourcesCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseMVPActivity<LoginPresenter> implements LoginView {


    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.logoIV)
    ImageView logoIV;
    @BindView(R.id.accountET)
    ClearnEditText accountET;
    @BindView(R.id.pwdET)
    PasswordEditText pwdET;
    @BindView(R.id.inputLayout)
    LinearLayout inputLayout;
    private String Tag = "loginTag";

    @Override
    protected int getLayoutView() {
        return R.layout.activity_login;
    }


    @Override
    protected void viewCreated(Bundle savedInstanceState) {
//        isTitleBarHidden(true);
        setTitleBarBackground(Color.WHITE);
        setTitle("登录测试");
        setTitleTextColor(getResources().getColor(R.color.text_title));
//        setLeftTitle("返回");
        isBackIconHidden(true);
        setRightTitle("编辑");
        setRightTitleColor(getResources().getColor(R.color.text_title));

        setTitleBarClickListener(new onTitleBarClikListener() {
            @Override
            public void onLeftClick(View v) {
                showToast("返回被点击了");
            }

            @Override
            public void onTitleClick(View v) {
                showToast("标题被点击了");
            }

            @Override
            public void onRightClick(View v) {
                showToast("编辑被点击了");
            }
        });
        initPermission();
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(new LoginModel());
    }

    @Override
    public void loginReslut(UserBean userBean) {
//        presenter.getView().hideLoading();
//        Intent intent = new Intent(context, AppActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //退出防止重启
//        startActivity(intent);
    }

    @OnClick(R.id.loginBtn)
    public void onViewClicked() {
        Intent intent = new Intent(context, AppActivity.class);
        startActivity(intent);
        finish();
    }

//    @OnClick(R.id.loginBtn)
//    public void onClick() {
//        Map<String, String> params = new HashMap<>();
//        params.put("account", "13419519796");
//        params.put("password", "123456");
//        params.put("type", "1");
//
//        OkHelper.postRequest(Tag, URLConfig.login,params, new JsonCallback<BackResponse<UserBean>>() {
//            @Override
//            public void onSuccess(Response<BackResponse<UserBean>> response) {
//                hideLoading();
//                if(response.body().getCode() == 100){
//                    UserBean userBean = response.body().getData();
//                    App.userBean = userBean;
//                    Intent intent = new Intent(context, AppActivity.class);
//                    startActivity(intent);
//                    finish();
//                    getMemberList();
//                }else{
//                    ToastUtils.showShort(response.body().getMsg());
//                }
//            }
//
//            @Override
//            public void onError(Response<BackResponse<UserBean>> response) {
//                super.onError(response);
//                hideLoading();
//            }
//        });
//        Intent intent = new Intent(context, AppActivity.class);
//        startActivity(intent);
//        finish();
//    }

    private void getMemberList() {
        UserBean user = App.userBean;
        Map<String, String> params = new HashMap<>();
        params.put("shopId", "1");
        params.put("size", 10 + "");
        params.put("current", 1 + "");
        presenter.getMemberList(params);
    }


    /**
     * 初始化权限申请
     */
    private void initPermission() {
        List<PermissionItem> permissionItems = new ArrayList<>();
        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
        permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入存储卡", R.drawable.permission_ic_storage));
        permissionItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取存储卡", R.drawable.permission_ic_storage));
//        permissionItems.add(new PermissionItem(Manifest.permission.RECORD_AUDIO, "录音", R.drawable.permission_ic_micro_phone));
//        permissionItems.add(new PermissionItem(Manifest.permission_group.SENSORS, "传感器", R.drawable.permission_ic_sensors));
        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.drawable.permission_ic_location));
//        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_COARSE_LOCATION, "位置", R.drawable.permission_ic_location));
        EasyPermission.create(context)
//                .title("Dear God")
                .permissions(permissionItems).filterColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getTheme()))//permission icon color
//                .msg("To protect the peace of the world, open these permissions! You and I together save the world!")
                .style(R.style.PermissionBlueStyle).checkMutiPermission(new PermissionCallback() {
            @Override
            public void onClose() {

            }

            @Override
            public void onFinish() {
                // showToast("所有权限申请完成");

            }

            @Override
            public void onDeny(String permission, int position) {
            }

            @Override
            public void onGuarantee(String permission, int position) {
            }
        });
    }



}
