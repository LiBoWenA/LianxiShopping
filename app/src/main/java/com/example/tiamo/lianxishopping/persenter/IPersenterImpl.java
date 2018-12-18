package com.example.tiamo.lianxishopping.persenter;

import com.example.tiamo.lianxishopping.model.IModel;
import com.example.tiamo.lianxishopping.model.IModelImpl;
import com.example.tiamo.lianxishopping.okhttp.MyCallBack;
import com.example.tiamo.lianxishopping.view.IView;

import java.util.Map;

public class IPersenterImpl implements IPersenter {
    private IView iView;
    private IModel iModel;

    public IPersenterImpl(IView iView) {
        this.iView = iView;
        iModel = new IModelImpl();
    }

    @Override
    public void showRequestData(String path, Map<String, String> params, Class clazz) {
        iModel.requestData(path, params, clazz, new MyCallBack() {
            @Override
            public void sucess(Object data) {
                iView.startRequestData(data);
            }
        });
    }
}
