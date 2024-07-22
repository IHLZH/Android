package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.practicalassignments.R;

import java.util.List;

import adapter.adapter_lv_orders_details;
import entity.Orders;
import entity.ShopOfShopCar;

public class ordersDetailsActivity extends AppCompatActivity {
    private TextView tv_orders_details_sumprice;
    private ListView lv_orders_details;
    private List<ShopOfShopCar> shopList;
    private Button btn_backtohome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_details);

        findViews();
        get_Intent();

        initListView();
        setListeners();
    }

    private void setListeners() {
        btn_backtohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ordersDetailsActivity.this.finish();
            }
        });
    }

    private void initListView() {
        adapter_lv_orders_details ordersDetailsAdapter = new adapter_lv_orders_details(
                ordersDetailsActivity.this,
                R.layout.item_orders_details,
                shopList
        );
        lv_orders_details.setAdapter(ordersDetailsAdapter);
    }

    private void findViews() {
        tv_orders_details_sumprice = findViewById(R.id.tv_orders_details_sumprice);
        lv_orders_details = findViewById(R.id.lv_orders_details);
        btn_backtohome = findViewById(R.id.btn_backtohome);
    }

    private void get_Intent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("orderData");
        Orders order = bundle.getSerializable("order", Orders.class);
        shopList = order.getShopList();
        double sumPrice = 0.0;
        for(ShopOfShopCar shop : shopList){
            sumPrice += shop.getPrice() * shop.getNum();
        }
        tv_orders_details_sumprice.setText(String.valueOf(sumPrice));
    }
}