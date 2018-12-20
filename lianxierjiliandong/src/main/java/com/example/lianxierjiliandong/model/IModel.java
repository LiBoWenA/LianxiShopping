package com.example.lianxierjiliandong.model;

import com.example.lianxierjiliandong.okhttp.ICallBack;
import com.example.lianxierjiliandong.okhttp.MyCallBack;

import java.util.Map;

public interface IModel {
    void requestData(String path, Map<String,String> pamars, Class clazz, MyCallBack myCallBack);
}
