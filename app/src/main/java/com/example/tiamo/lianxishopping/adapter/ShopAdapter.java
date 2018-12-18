package com.example.tiamo.lianxishopping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.tiamo.lianxishopping.R;
import com.example.tiamo.lianxishopping.bean.Bean;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private Context context;
    private List<Bean.DataBean> list;

    public ShopAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }
    public void setData(List<Bean.DataBean> lists){
        list = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_adapter,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShopAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.sName.setText(list.get(i).getSellerName());
        final ChildAdapter childAdapter = new ChildAdapter(context,list.get(i).getList());
        LinearLayoutManager manager = new LinearLayoutManager(context);
        viewHolder.recyclerView.setLayoutManager(manager);
        viewHolder.recyclerView.setAdapter(childAdapter);

        viewHolder.sCk.setChecked(list.get(i).isIsck());

        childAdapter.setChildCallBackLitener(new ChildAdapter.ChildCallBackLitener() {
            @Override
            public void callBack() {
                if (shopCallBackListener != null){
                    shopCallBackListener.callBack(list);
                }

                List<Bean.DataBean.listBean> bean = list.get(i).getList();

                boolean isAllCheck = true;
                for (Bean.DataBean.listBean list: bean) {
                    if (! list.isCheck()){
                        isAllCheck = false;
                        break;
                    }
                }

                viewHolder.sCk.setChecked(isAllCheck);
                list.get(i).setIsck(isAllCheck);
            }
        });

        viewHolder.sCk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).setIsck(viewHolder.sCk.isChecked());
                childAdapter.selectOrRemoveAll(viewHolder.sCk.isChecked());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        TextView sName;
        CheckBox sCk;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.shop_name);
            sName = itemView.findViewById(R.id.sName);
            sCk = itemView.findViewById(R.id.s_ck);
        }
    }

    ShopCallBackListener shopCallBackListener;

    public void setShopCallBackListener(ShopCallBackListener listener){
        shopCallBackListener = listener;
    }

    public interface ShopCallBackListener{
        void callBack(List<Bean.DataBean> list);
    }
}
