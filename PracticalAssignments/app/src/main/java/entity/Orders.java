package entity;

import java.io.Serializable;
import java.util.List;

public class Orders implements Serializable {
    private String id;
    private String date;
    private double sumPrice;
    private List<ShopOfShopCar> shopList;

    public void setShopList(List<ShopOfShopCar> shopList) {
        this.shopList = shopList;
    }
    public List<ShopOfShopCar> getShopList() {
        return shopList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }
    public double getSumPrice() {
        return sumPrice;
    }
}
