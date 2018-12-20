package com.example.lianxierjiliandong.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lianxierjiliandong.R;
import com.example.lianxierjiliandong.bean.RightBean;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private Context context;
    private List<RightBean.DataBean.ListBean> list;

    public ItemAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<RightBean.DataBean.ListBean> mlist){
        list = mlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder viewHolder, int i) {
        Glide.with(context).load(list.get(i).getIcon()).into(viewHolder.img);
        viewHolder.text.setText(list.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text_item);
            img = itemView.findViewById(R.id.img);
        }
    }
}
