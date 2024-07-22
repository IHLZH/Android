package com.example.adapterview.product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.example.adapterview.R;
import com.example.adapterview.adapter.ProductAdapter;
import com.example.adapterview.entity.Product;

import java.util.List;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        GridView gv_product = findViewById(R.id.gv_product);
        List<Product> productlist = Product.getProductList();

        ProductAdapter productAdapter = new ProductAdapter(
                this,
                R.layout.item_product,
                productlist
        );
        gv_product.setAdapter(productAdapter);
    }
}