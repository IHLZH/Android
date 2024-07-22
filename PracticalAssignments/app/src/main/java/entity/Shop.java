package entity;

import java.io.Serializable;

public class Shop implements Serializable {
    private Integer id;
    private Integer pic;
    private String name;
    private String introduce;
    private Double price;
    private Integer inventory;

    public Shop(){};
    public Shop(Integer id, Integer pic, String name, String introduce, Double price, Integer inventory){
        this.id = id;
        this.pic = pic;
        this.name = name;
        this.introduce = introduce;
        this.price = price;
        this.inventory = inventory;
    }
    public Shop(ShopOfShopCar shopOfShopCar1){
        this.id = shopOfShopCar1.getId();
        this.pic = shopOfShopCar1.getPic();
        this.name = shopOfShopCar1.getName();
        this.introduce = shopOfShopCar1.getIntroduce();
        this.price = shopOfShopCar1.getPrice();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPic(Integer pic) {
        this.pic = pic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPic() {
        return pic;
    }

    public String getName() {
        return name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getInventory() {
        return inventory;
    }
}
