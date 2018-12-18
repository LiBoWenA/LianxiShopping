package com.example.tiamo.lianxishopping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tiamo.lianxishopping.R;
import com.example.tiamo.lianxishopping.bean.Bean;
import com.example.tiamo.lianxishopping.view.ItemPriceView;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {
    private Context context;
    private List<Bean.DataBean.listBean> list;

    public ChildAdapter(Context context, List<Bean.DataBean.listBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_child_adapter,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildAdapter.ViewHolder viewHolder, final int i) {
        String images = list.get(i).getImages();
        String[] split = images.split("\\|");
        String path = split[0].replace("https", "http");
        Glide.with(context).load(path).into(viewHolder.img);
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.price.setText("¥ ："+list.get(i).getPrice());
        viewHolder.checkBox.setChecked(list.get(i).isCheck());

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(i).setCheck(isChecked);
                if (callBackLitener != null){
                    callBackLitener.callBack();
                }
            }
        });

        //自定义view
        viewHolder.priceView.setData(this,list,i);
        viewHolder.priceView.setOnCallBack(new ItemPriceView.CallBackListener() {
            @Override
            public void callBack() {
                if (callBackLitener != null){
                    callBackLitener.callBack();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemPriceView priceView;
        TextView title,price;
        ImageView img;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            priceView = itemView.findViewById(R.id.itemprice);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            img = itemView.findViewById(R.id.img);
            checkBox = itemView.findViewById(R.id.c_ck);
        }
    }
    //更改选中状态
    public void selectOrRemoveAll(boolean isSelectAll){
        for (Bean.DataBean.listBean listbean:list) {
            listbean.setCheck(isSelectAll);
        }
        notifyDataSetChanged();
    }

    ChildCallBackLitener callBackLitener;

    public void setChildCallBackLitener(ChildCallBackLitener shopCallBackLitener){
        callBackLitener = shopCallBackLitener;
    }

    public interface ChildCallBackLitener{
        void callBack();
    }
}
