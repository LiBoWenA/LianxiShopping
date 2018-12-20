package com.example.lianxierjiliandong.persenter;

import com.example.lianxierjiliandong.model.IModel;
import com.example.lianxierjiliandong.model.IModelIpml;
import com.example.lianxierjiliandong.okhttp.MyCallBack;
import com.example.lianxierjiliandong.view.IView;

import java.util.Map;

public class IPersenterImpl implements IPersenter {
    private IView iView;
    private IModel iModel;

    public IPersenterImpl(IView iView) {
        this.iView = iView;
        iModel = new IModelIpml();
    }

    @Override
    public void showRequestData(String path, Map<String, String> pamars, Class clazz) {
        iModel.requestData(path, pamars, clazz, new MyCallBack() {
            @Override
            public void requestData(Object data) {
                iView.startRequestData(data);
            }
        });
    }
    public void onDestory(){
        if (iModel != null){
            iModel = null;
        }
        if (iView != null){
            iView = null;
        }
    }
}
