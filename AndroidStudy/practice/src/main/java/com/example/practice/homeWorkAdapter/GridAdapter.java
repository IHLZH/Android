package com.example.practice.homeWorkAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practice.HomeWork.HomWorkActivity;
import com.example.practice.HomeWork.Shop;
import com.example.practice.R;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private Integer layoutId;
    private List<? extends Shop> shopList;

    public GridAdapter(){};
    public GridAdapter(Context context, Integer layoutId, List<? extends Shop> shopList){
        this.context = context;
        this.layoutId = layoutId;
        this.shopList = shopList;
    }
    @Override
    public int getCount() {
        return shopList.size();
    }

    @Override
    public Object getItem(int position) {
        return shopList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
        }
        ImageView iv_pic = convertView.findViewById(R.id.iv_pic);
        TextView tv_introduce = convertView.findViewById(R.id.tv_introduce);
        TextView tv_comments = convertView.findViewById(R.id.tv_comments);
        TextView tv_price = convertView.findViewById(R.id.tv_price);

        Shop shop = shopList.get(position);
        iv_pic.setImageResource(shop.getPic());
        tv_introduce.setText(shop.getIntroduce());
        tv_comments.setText(shop.getComments());
        tv_price.setText(shop.getPrice());

        return convertView;
    }
}
