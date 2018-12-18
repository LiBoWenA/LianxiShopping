package com.example.tiamo.lianxishopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.tiamo.lianxishopping.adapter.ShopAdapter;
import com.example.tiamo.lianxishopping.bean.Bean;
import com.example.tiamo.lianxishopping.persenter.IPersenter;
import com.example.tiamo.lianxishopping.persenter.IPersenterImpl;
import com.example.tiamo.lianxishopping.view.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements IView,View.OnClickListener {

    private RecyclerView recyclerView;
    private IPersenterImpl iPersenter;
    private CheckBox bQx;
    private TextView tPrice,tNum;
    private ShopAdapter shopAdapter;
    private String path = "http://www.zhaoapi.cn/product/getCarts";
    private List<Bean.DataBean> mlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //获取资源ID
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.rview);
        bQx = findViewById(R.id.z_qx);
        bQx.setOnClickListener(this);
        tPrice = findViewById(R.id.price);
        tNum = findViewById(R.id.num);
        iPersenter = new IPersenterImpl(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        shopAdapter = new ShopAdapter(this);
        recyclerView.setAdapter(shopAdapter);


       shopAdapter.setShopCallBackListener(new ShopAdapter.ShopCallBackListener() {
           @Override
           public void callBack(List<Bean.DataBean> list) {
               double price = 0;
               int num = 0;
               int totlenum = 0;
               for (int a = 0; a < list.size(); a++) {
                   List<Bean.DataBean.listBean> lists = list.get(a).getList();
                   for (int b = 0; b < lists.size(); b++) {
                       totlenum = totlenum + lists.get(b).getNum();
                       if (lists.get(b).isCheck()){
                           price = price+(lists.get(b).getPrice() * lists.get(b).getNum());
                           num = num + lists.get(b).getNum();
                       }
                   }

                   if (num < totlenum){
                       bQx.setChecked(false);
                   }else{
                       bQx.setChecked(true);
                   }

                   tPrice.setText("合计："+price);
                   tNum.setText("去结算（"+num+"）");
               }
           }
       });

        Map<String,String> pamars = new HashMap<>();
        pamars.put("uid",71+"");
        iPersenter.showRequestData(path,pamars, Bean.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.z_qx:
                getNumPrice(bQx.isChecked());
                shopAdapter.notifyDataSetChanged();
                break;
                default:
                    break;
        }

    }

    private void getNumPrice(boolean isck) {
        double price = 0;
        int num = 0;
        for (int i = 0; i < mlist.size(); i++) {
            Bean.DataBean dataBean = mlist.get(i);
            dataBean.setIsck(isck);

            List<Bean.DataBean.listBean> listAll = mlist.get(i).getList();
            for (int j = 0; j < listAll.size(); j++) {
                listAll.get(j).setCheck(isck);
                price = price+(listAll.get(j).getPrice()*listAll.get(j).getNum());
                num = num + listAll.get(j).getNum();
            }

            if (isck){
                tPrice.setText("合计："+price);
                tNum.setText("去结算（"+num+"）");
            }else{
                tPrice.setText("合计：0.00");
                tNum.setText("去结算（0）");
            }

        }
    }

    @Override
    public void startRequestData(Object data) {
        if (data instanceof Bean){
            Bean bean = (Bean) data;
            mlist = bean.getData();
            if (mlist != null){
                mlist.remove(0);
                shopAdapter.setData(mlist);
            }
        }
    }
}
