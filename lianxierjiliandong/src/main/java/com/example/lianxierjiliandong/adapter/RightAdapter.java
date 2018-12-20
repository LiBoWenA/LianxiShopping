package com.example.lianxierjiliandong.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lianxierjiliandong.R;
import com.example.lianxierjiliandong.bean.RightBean;

import java.util.ArrayList;
import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
    private Context context;
    private List<RightBean.DataBean> list = new ArrayList<>();

    public RightAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<RightBean.DataBean> mlist){
        list = mlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = View.inflate(context, R.layout.item_right,null);
        View view1 = LayoutInflater.from(context).inflate(R.layout.item_right,viewGroup,false);
        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull RightAdapter.ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(list.get(i).getName());
        ItemAdapter itemAdapter = new ItemAdapter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.recyclerView.setLayoutManager(layoutManager);
        viewHolder.recyclerView.setAdapter(itemAdapter);
        viewHolder.recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));

        itemAdapter.setData(list.get(i).getList());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            recyclerView = itemView.findViewById(R.id.recycler_right);
        }
    }
}
