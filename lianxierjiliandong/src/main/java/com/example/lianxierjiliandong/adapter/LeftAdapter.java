package com.example.lianxierjiliandong.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lianxierjiliandong.R;
import com.example.lianxierjiliandong.bean.ShopTypeBean;

import java.util.ArrayList;
import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {
    private Context context;
    private List<ShopTypeBean.Data> list = new ArrayList<>();

    public LeftAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ShopTypeBean.Data> lists){
        list = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LeftAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_left,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeftAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(list.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClick != null){
                    mOnClick.Click(i,list.get(i).getCid());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

    OnClick mOnClick;

    public void setmOnClick(OnClick onClick){
        mOnClick = onClick;
    }

    public interface OnClick{
        void Click(int i,String cid);
    }
}
