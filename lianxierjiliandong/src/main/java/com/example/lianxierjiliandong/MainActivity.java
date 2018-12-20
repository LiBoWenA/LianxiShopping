package com.example.lianxierjiliandong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lianxierjiliandong.adapter.LeftAdapter;
import com.example.lianxierjiliandong.adapter.RightAdapter;
import com.example.lianxierjiliandong.bean.RightBean;
import com.example.lianxierjiliandong.bean.ShopTypeBean;
import com.example.lianxierjiliandong.persenter.IPersenterImpl;
import com.example.lianxierjiliandong.view.IView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView {


    private IPersenterImpl iPersenter;
    private RecyclerView leftRecyclerView,rightRecyclerView;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private String path = "http://www.zhaoapi.cn/product/getCatagory";
    private String rpath = "http://www.zhaoapi.cn/product/getProductCatagory";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iPersenter = new IPersenterImpl(this);

        init();
        initData();
        Map<String,String> map = new HashMap<>();
        iPersenter.showRequestData(path,map,ShopTypeBean.class);
    }

    private void initData() {
        leftRecyclerView = findViewById(R.id.names);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        leftRecyclerView.setLayoutManager(layoutManager);
        leftRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        leftAdapter = new LeftAdapter(this);
        leftRecyclerView.setAdapter(leftAdapter);
        //点击左边传给右边
        leftAdapter.setmOnClick(new LeftAdapter.OnClick() {
            @Override
            public void Click(int i, String cid) {
                getCid(cid);
            }
        });
    }

    private void getCid(String cid) {
        Map<String,String> map = new HashMap<>();
        map.put("cid",cid);
        iPersenter.showRequestData(rpath,map,RightBean.class);
    }

    //初始化数据
    private void init() {
        rightRecyclerView = findViewById(R.id.xxxx);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rightRecyclerView.setLayoutManager(layoutManager);
        rightRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rightAdapter = new RightAdapter(this);
        rightRecyclerView.setAdapter(rightAdapter);
    }

    //防止内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPersenter.onDestory();

    }

    @Override
    public void startRequestData(Object data) {
        if (data instanceof ShopTypeBean){
            ShopTypeBean typeBean = (ShopTypeBean) data;
            leftAdapter.setData(typeBean.getData());
        }if (data instanceof RightBean){
            RightBean bean = (RightBean) data;
            rightAdapter.setData(bean.getData());
            rightRecyclerView.scrollToPosition(0);
        }

    }
}
