package com.example.tiamo.lianxishopping.model;

import com.example.tiamo.lianxishopping.okhttp.MyCallBack;

import java.util.Map;

public interface IModel {
    void requestData(String path, Map<String,String> params, Class clazz, MyCallBack myCallBack);
}
