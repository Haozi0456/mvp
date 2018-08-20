package com.zwh.mvp.presenter;

import com.lzy.okgo.model.Response;
import com.zwh.mvp.bean.Data;
import com.zwh.mvp.library.base.presenter.BasePresenter;
import com.zwh.mvp.library.base.response.BaseResponse;
import com.zwh.mvp.library.base.response.callback.JsonCallback;
import com.zwh.mvp.model.DataModel;
import com.zwh.mvp.view.DataView;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2018/08/20 11:37
 */

public class DataPresenter extends BasePresenter<DataView> {

    private DataModel dataModel;

    public DataPresenter() {
        this.dataModel = new DataModel();
    }

    public void getData(String key){
        dataModel.getData(key,new JsonCallback<BaseResponse<Data>>(){

            @Override
            public void onSuccess(Response<BaseResponse<Data>> response) {
                view.getDataFail(response.body().getData().toString());
            }
        });
    }

}
