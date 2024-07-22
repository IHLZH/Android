package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practicalassignments.R;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entity.Orders;
import entity.Shop;
import entity.ShopOfShopCar;
import utils.OrdersList;
import utils.ShopCar;

public class shopDetailsActivity extends AppCompatActivity {
    private ImageView iv_shop_img;
    private TextView tv_name;
    private TextView tv_price;
    private TextView tv_introduce;
    private Button btn_buy;
    private Button btn_toshopcar;
    public Shop shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);

        findViews();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("shopData");
        shop = bundle.getSerializable("shop", Shop.class);

        iv_shop_img.setImageResource(shop.getPic());
        tv_name.setText(shop.getName());
        tv_price.setText(String.valueOf(shop.getPrice()));
        tv_introduce.setText(shop.getIntroduce());

        setLinsteners();
    }

    private void setLinsteners() {
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Orders orders = new Orders();
                List<ShopOfShopCar> listShopCar = ShopCar.getListShopCar();
                List<ShopOfShopCar> shopList = new ArrayList<>();
                ShopOfShopCar shop1 = new ShopOfShopCar(shop);
                shop1.setNum(1);
                shopList.add(shop1);
                LocalDateTime date = LocalDateTime.now();
                orders.setDate(String.valueOf(date));
                orders.setSumPrice(shop.getPrice());
                orders.setShopList(shopList);
                OrdersList.listOrders.add(orders);
                Toast.makeText(shopDetailsActivity.this, "购买成功！", Toast.LENGTH_SHORT).show();
            }
        });
        btn_toshopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("shop", shop);
                intent.putExtra("shopData", bundle);
                intent.setClass(shopDetailsActivity.this, shopCarActivity.class);
                startActivity(intent);
                shopDetailsActivity.this.finish();
            }
        });
    }

    private void findViews() {
        iv_shop_img = findViewById(R.id.iv_shop_img);
        tv_name = findViewById(R.id.tv_name);
        tv_price = findViewById(R.id.tv_price);
        tv_introduce = findViewById(R.id.tv_introduce);
        btn_buy = findViewById(R.id.btn_buy);
        btn_toshopcar = findViewById(R.id.btn_toshopcar);
    }
}