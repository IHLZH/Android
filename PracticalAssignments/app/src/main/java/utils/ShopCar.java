package utils;

import java.util.ArrayList;
import java.util.List;

import entity.ShopOfShopCar;

public class ShopCar {
    public ShopCar(){};
    public static List<ShopOfShopCar> ListShopCar = new ArrayList<>();
    public static List<ShopOfShopCar> getListShopCar(){
        return ListShopCar;
    }
}
