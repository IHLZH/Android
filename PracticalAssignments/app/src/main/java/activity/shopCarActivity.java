package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practicalassignments.R;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import adapter.adapter_lv_shopcar;
import entity.Orders;
import entity.Shop;
import entity.ShopOfShopCar;
import utils.OrdersList;
import utils.ShopCar;

public class shopCarActivity extends AppCompatActivity {
    public static TextView tv_sum_price;
    private Button btn_getsum;
    private Button btn_back;
    private Double sumOfAmount;
    private adapter_lv_shopcar shopCarAdapter;

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
                shopCarActivity.this.finish();
            }
        });
        btn_getsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Orders orders = new Orders();
                List<ShopOfShopCar> listShopCar = ShopCar.getListShopCar();
                List<ShopOfShopCar> copy = new ArrayList<>();
                List<ShopOfShopCar> shopList = new ArrayList<>();
                double sumPrice = 0;
                for(ShopOfShopCar shop : listShopCar){
                    sumPrice += shop.getPrice() * shop.getNum();
                    shopList.add(shop);
                    copy.add(shop);
                }
                listShopCar.removeAll(copy);
                LocalDateTime date = LocalDateTime.now();
                orders.setDate(String.valueOf(date));
                orders.setSumPrice(sumPrice);
                orders.setShopList(shopList);
                OrdersList.listOrders.add(orders);
                tv_sum_price.setText("0.0");
                Toast.makeText(shopCarActivity.this, "结算成功！", Toast.LENGTH_SHORT).show();
                shopCarAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initList() {
        ListView lv_list_shopcar = findViewById(R.id.lv_list_shopcar);
        List<ShopOfShopCar> listShopCar = ShopCar.getListShopCar();
        shopCarAdapter = new adapter_lv_shopcar(
                shopCarActivity.this,
                R.layout.item_lv_shopcar,
                listShopCar
        );
        lv_list_shopcar.setAdapter(shopCarAdapter);
    }

    private void get_Intent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("shopData");
        Shop shop = bundle.getSerializable("shop", Shop.class);
        ShopOfShopCar shop1 = new ShopOfShopCar(shop);
        boolean flag = true;
        for(ShopOfShopCar shop2 : ShopCar.ListShopCar){
            if(shop1.getId().equals(shop2.getId())){
                shop2.setNum(shop2.getNum() + 1);
                flag = false;
            }
        }
        if(flag){
            ShopCar.ListShopCar.add(shop1);
            shop1.setNum(1);
        }
        calculateAmount(ShopCar.ListShopCar);
    }

    private void calculateAmount(List<ShopOfShopCar> listShopCar) {
        if(sumOfAmount == null){sumOfAmount = 0.0;}
        for(ShopOfShopCar shop : listShopCar){
            sumOfAmount += shop.getPrice() * shop.getNum();
        }
        changeSumOfPrice(String.valueOf(sumOfAmount));
    }

    private void findViews() {
        tv_sum_price = findViewById(R.id.tv_sum_price);
        btn_getsum = findViewById(R.id.btn_getsum);
        btn_back = findViewById(R.id.btn_back);
    }

    public void changeSumOfPrice(String sumOfPrice){
        tv_sum_price.setText(sumOfPrice);
    }
}