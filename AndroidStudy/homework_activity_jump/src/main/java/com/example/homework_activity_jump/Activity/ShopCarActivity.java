package com.example.homework_activity_jump.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.homework_activity_jump.HomeWork.Shop;
import com.example.homework_activity_jump.HomeWork.ShopCar;
import com.example.homework_activity_jump.R;
import com.example.homework_activity_jump.homeWorkAdapter.ShopCarAdapter;

import java.io.Serializable;
import java.util.List;

public class ShopCarActivity extends AppCompatActivity {
    public static TextView tv_sum_price;
    private Button btn_getsum;
    private Button btn_back;
    private Double sumOfAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);

        findViews();
        get_Intent();

        initList();
        setListeners();
    }

    private void setListeners() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(ShopCarActivity.this, HomWorkActivity.class);
//                startActivity(intent);
                ShopCarActivity.this.finish();
            }
        });
    }

    private void get_Intent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("msg");
        Shop shop = (Shop)bundle.getSerializable("shop");
        boolean flag = true;
        Integer numOfShop = 0;
        for(Shop shop1 : ShopCar.ListShopCar){
            if(shop1.getIdOfShop().equals(shop.getIdOfShop())){
                shop1.setNum(shop1.getNum() + 1);
                flag = false;
            }
        }
        if(flag)ShopCar.ListShopCar.add(shop);
        shop.setNum(1);
        calculateAmount(ShopCar.ListShopCar);
    }

    private void calculateAmount(List<Shop> ListShopCar) {
        if(sumOfAmount == null){sumOfAmount = 0.0;}
        for(Shop shop : ListShopCar){
            sumOfAmount += Double.parseDouble(shop.getPrice()) * shop.getNum();
        }
        tv_sum_price.setText(sumOfAmount.toString());
    }

    private void initList() {
        ListView lv_list_shopcar = findViewById(R.id.lv_list_shopcar);
        List<Shop> listShopCar = ShopCar.getListShopCar();
        ShopCarAdapter shopCarAdapter = new ShopCarAdapter(
                ShopCarActivity.this,
                R.layout.item_shop_car,
                listShopCar
        );
        lv_list_shopcar.setAdapter(shopCarAdapter);
    }

    private void findViews() {
        tv_sum_price = findViewById(R.id.tv_sum_price);
        btn_getsum = findViewById(R.id.btn_getsum);
        btn_back = findViewById(R.id.btn_back);
    }
}