package com.example.tiamo.lianxishopping;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tiamo.lianxishopping.bean.UserBean;
import com.example.tiamo.lianxishopping.sqlite.UserDao;
import com.example.tiamo.lianxishopping.view.PullLayout;
import com.example.tiamo.lianxishopping.view.ViewTitle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewTitle viewTitle;
    private PullLayout pullLayout;
    private UserDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    //获取资源ID
    private void init() {
        viewTitle = findViewById(R.id.title);
        pullLayout = findViewById(R.id.pull);
        dao = new UserDao(this);
        //查询数据库
        List<UserBean> query = dao.query();
        for (int i = 0; i < query.size(); i++) {
            TextView tv = new TextView(this);
            tv.setText(query.get(i).getName());
            tv.setTextColor(Color.BLACK);
            tv.setBackgroundResource(R.drawable.bg);
            pullLayout.addView(tv);
        }

        viewTitle.setGetData(new ViewTitle.GetData() {
            @Override
            public void setData(String str) {
                dao.insert(str);
                TextView tv = new TextView(MainActivity.this);
                tv.setText(str);
                tv.setTextColor(Color.BLACK);
                tv.setBackgroundResource(R.drawable.bg);
                pullLayout.addView(tv);
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
