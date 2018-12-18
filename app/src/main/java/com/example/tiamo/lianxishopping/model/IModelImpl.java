package com.example.tiamo.lianxishopping.model;

import com.example.tiamo.lianxishopping.okhttp.ICallBack;
import com.example.tiamo.lianxishopping.okhttp.MyCallBack;
import com.example.tiamo.lianxishopping.okhttp.OkHttpUtils;

import java.util.Map;

public class IModelImpl implements IModel {
    @Override
    public void requestData(String path, Map<String, String> params, Class clazz, final MyCallBack myCallBack) {
        OkHttpUtils.getInstance().postEnqueue(path, params, clazz, new ICallBack() {
            @Override
            public void sucess(Object data) {
                myCallBack.sucess(data);
            }

            @Override
            public void failed(Exception e) {
                myCallBack.sucess(e);
            }
        });
    }
}
