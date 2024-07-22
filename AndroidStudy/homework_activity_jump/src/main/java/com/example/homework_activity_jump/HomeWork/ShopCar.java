package com.example.homework_activity_jump.HomeWork;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.homework_activity_jump.R;

import java.util.ArrayList;
import java.util.List;

public class ShopCar {
    public ShopCar(){};
    public static List<Shop> ListShopCar = new ArrayList<>();
    public static List<Shop> getListShopCar(){
        return ListShopCar;
    }
}
