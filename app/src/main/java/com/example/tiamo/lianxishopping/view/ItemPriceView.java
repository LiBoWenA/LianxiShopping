package com.example.tiamo.lianxishopping.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tiamo.lianxishopping.R;
import com.example.tiamo.lianxishopping.adapter.ChildAdapter;
import com.example.tiamo.lianxishopping.bean.Bean;

import java.util.List;

public class ItemPriceView extends LinearLayout implements View.OnClickListener {

    private EditText etNum;
    private Context context;
    private List<Bean.DataBean.listBean> list;
    private int position;
    private ChildAdapter childAdapter;
    public ItemPriceView(Context context) {
        super(context);
        init(context);
    }

    public ItemPriceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ItemPriceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View view = View.inflate(context, R.layout.item_price,null);
        view.findViewById(R.id.btn_jia).setOnClickListener(this);
        view.findViewById(R.id.btn_jian).setOnClickListener(this);
        etNum = view.findViewById(R.id.ed_num);
        addView(view);

        etNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    num = Integer.parseInt(String.valueOf(s));
                    list.get(position).setNum(num);
                }catch (Exception e){
                    list.get(position).setNum(1);
                }
                if (backListener != null){
                    backListener.callBack();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private int num;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_jia:
                num++;
                etNum.setText(num+"");

                break;
            case R.id.btn_jian:
                if (num>1){
                    num--;
                }else{
                    Toast.makeText(context,"我是有底线的啊",Toast.LENGTH_SHORT).show();
                }
                etNum.setText(num+"");
                break;
                default:
                    break;
        }
    }

    CallBackListener backListener;

    public void setOnCallBack(CallBackListener onCallBack){
        backListener = onCallBack;
    }

    public interface CallBackListener{
        void callBack();
    }

    public void setData(ChildAdapter childAdapter,List<Bean.DataBean.listBean> list,int i){
        this.list = list;
        this.childAdapter = childAdapter;
        position = i;
        num = list.get(i).getNum();
        etNum.setText(num+"");
    }

}
