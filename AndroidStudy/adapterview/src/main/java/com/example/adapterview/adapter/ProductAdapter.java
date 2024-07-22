package com.example.adapterview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapterview.R;
import com.example.adapterview.entity.Product;

import java.util.List;
import java.util.zip.Inflater;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private Integer layout;
    private List<Product> productList;

    public ProductAdapter(Context context, Integer layout, List<Product> productList) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout, null);
        }
        //ctrl + shift + 回车 = 换行
        TextView tv_product_name = convertView.findViewById(R.id.tv_product_name);
        ImageView iv_product_img = convertView.findViewById(R.id.iv_product_img);
        TextView tv_product_price = convertView.findViewById(R.id.tv_product_price);
        Button btn_product = convertView.findViewById(R.id.btn_product);
        //绑定数据
        Product product = productList.get(position);
        tv_product_name.setText(product.getProductName());
        iv_product_img.setImageResource(product.getProductImage());
        tv_product_price.setText(String.valueOf(product.getProductPrice()));
        btn_product.setText("购物车");

        return convertView;
    }
}
