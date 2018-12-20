package com.example.lianxierjiliandong.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {
    private static volatile OkHttpUtils instance;
    private OkHttpClient mClient;
    private Handler handler = new Handler(Looper.getMainLooper());
    //单例
    public static OkHttpUtils getInstance(){
        if (instance == null){
            synchronized (OkHttpUtils.class){
                instance = new OkHttpUtils();
            }
        }
        return instance;
    }

    private OkHttpUtils(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .build();
    }
    //post
    public void postEnqueue(String path, Map<String,String> pamars, final Class clazz, final ICallBack iCallBack){
        FormBody.Builder builder = new FormBody.Builder();

        for (Map.Entry<String,String> entry:pamars.entrySet()) {
            builder.add(entry.getKey(),entry.getValue());
        }

        RequestBody body = builder.build();
        final Request request = new Request.Builder()
                .post(body)
                .url(path)
                .build();

        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iCallBack.failed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                final Object o = new Gson().fromJson(json, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.sucess(o);
                    }
                });
            }
        });
    }

    //get
    public void getEnqueue(String path, final Class clazz, final ICallBack iCallBack){
        Request request = new Request.Builder()
                .get()
                .url(path)
                .build();

        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iCallBack.failed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                final Object o = new Gson().fromJson(json, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.sucess(o);
                    }
                });
            }
        });
    }
}
