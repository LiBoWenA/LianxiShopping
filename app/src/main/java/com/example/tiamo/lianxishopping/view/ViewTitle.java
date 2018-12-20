package com.example.tiamo.lianxishopping.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.tiamo.lianxishopping.R;

public class ViewTitle extends LinearLayout {

    Context context;
    public ViewTitle(Context context) {
        super(context);
        init(context);
    }

    public ViewTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ViewTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.head,null);
        final EditText ed_serch = view.findViewById(R.id.ed_search);
        Button btn_serch = view.findViewById(R.id.btn_search);
        //点击按钮获取值，并回传
        btn_serch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data != null){
                    data.setData(ed_serch.getText().toString());
                }
            }
        });

        addView(view);
    }
    //利用接口回调回传值
    public interface GetData{
        void setData(String str);
    }

    GetData data;

    public void setGetData(GetData getData){
        data = getData;
    }
}
