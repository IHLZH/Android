package com.example.adapterview.entity;

import com.example.adapterview.R;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String productName;
    private Integer productImage;
    private Double productPrice;

    public Product(String productName, Integer productImage, Double productPrice) {
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
    }

    public static List<Product> getProductList(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("华为", R.drawable.pic5, 6299.0));
        productList.add(new Product("小米", R.drawable.pic1, 1299.0));
        productList.add(new Product("iPhone", R.drawable.pic2, 9299.0));
        productList.add(new Product("vivo", R.drawable.pic3, 3299.0));
        productList.add(new Product("oppo", R.drawable.pic4, 2299.0));
        productList.add(new Product("荣耀", R.drawable.pic6, 5299.0));

        return productList;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductImage() {
        return productImage;
    }

    public void setProductImage(Integer productImage) {
        this.productImage = productImage;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}
