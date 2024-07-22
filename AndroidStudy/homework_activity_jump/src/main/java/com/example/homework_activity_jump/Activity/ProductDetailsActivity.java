package com.example.homework_activity_jump.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homework_activity_jump.HomeWork.Shop;
import com.example.homework_activity_jump.R;

public class ProductDetailsActivity extends AppCompatActivity {
    private ImageView iv_product_img;
    private TextView tv_name;
    private TextView tv_price;
    private TextView tv_introduce;
    private Button btn_exit;
    private Button btn_buy;
    public Shop shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        findViews();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("msg");
        shop = (Shop)bundle.getSerializable("shop");

        iv_product_img.setImageResource(shop.getPic());
        tv_name.setText(shop.getIntroduce());
        tv_price.setText(shop.getPrice());
        tv_introduce.setText(shop.getComments());

        setLinsteners();
    }

    private void setLinsteners() {
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ProductDetailsActivity.this, HomWorkActivity.class);
                startActivity(intent);
                ProductDetailsActivity.this.finish();
            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("shop", shop);
                intent.putExtra("msg", bundle);
                intent.setClass(ProductDetailsActivity.this, ShopCarActivity.class);
                startActivity(intent);
                ProductDetailsActivity.this.finish();
            }
        });
    }

    private void findViews() {
        iv_product_img = findViewById(R.id.iv_product_img);
        tv_name = findViewById(R.id.tv_name);
        tv_price = findViewById(R.id.tv_price);
        tv_introduce = findViewById(R.id.tv_introduce);
        btn_exit = findViewById(R.id.btn_exit);
        btn_buy = findViewById(R.id.btn_buy);
    }
}