package com.example.lianxierjiliandong.model;

import com.example.lianxierjiliandong.okhttp.ICallBack;
import com.example.lianxierjiliandong.okhttp.MyCallBack;
import com.example.lianxierjiliandong.okhttp.OkHttpUtils;

import java.util.Map;

public class IModelIpml implements IModel {
    @Override
    public void requestData(String path, Map<String, String> pamars, Class clazz, final MyCallBack myCallBack) {
        OkHttpUtils.getInstance().postEnqueue(path, pamars, clazz, new ICallBack() {
            @Override
            public void failed(Exception e) {
                myCallBack.requestData(e);
            }

            @Override
            public void sucess(Object data) {
                myCallBack.requestData(data);
            }
        });
    }
}
