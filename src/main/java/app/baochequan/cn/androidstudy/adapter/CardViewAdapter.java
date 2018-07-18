package app.baochequan.cn.androidstudy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import app.baochequan.cn.androidstudy.Pdf_webActivity;
import app.baochequan.cn.androidstudy.R;
import app.baochequan.cn.androidstudy.model.ItemCard;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author gaohangbo
 * date: 2018/7/3 0003.
 */
public class CardViewAdapter extends RecyclerView.Adapter{

    private List<ItemCard> itemCards=new ArrayList<>();

    private Context mContext;
    private List<ItemCard> list;

    public CardViewAdapter(Context mContext, List<ItemCard> list){

        this.mContext=mContext;
        this.list=list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bankaccount, parent,
//                false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_list_2,null); //解决条目显示不全
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
       // ((ItemViewHolder)holder).iv_cover.setImageResource(R.drawable.ic_launcher_background);
        ((ItemViewHolder)holder).tv_name.setText(list.get(position).getName());

        ((ItemViewHolder)holder).cv_content.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(mContext, Pdf_webActivity.class);
            intent.putExtra("url",list.get(position).getUrl());
            mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_cover)
        ImageView iv_cover;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.cv_content)
        android.support.v7.widget.CardView cv_content;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
