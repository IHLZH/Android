package entity;

import java.io.Serializable;

public class ShopOfShopCar implements Serializable {
    private Integer id;
    private Integer pic;
    private String name;
    private String introduce;
    private Double price;
    private Integer num;
    public ShopOfShopCar(){};
    public ShopOfShopCar(Integer id, Integer pic, String name, String introduce, Double price, Integer num){
        this.id = id;
        this.pic = pic;
        this.name = name;
        this.introduce = introduce;
        this.price = price;
        this.num = num;
    }

    public ShopOfShopCar(Shop shop){
        this.id = shop.getId();
        this.pic = shop.getPic();
        this.name = shop.getName();
        this.introduce = shop.getIntroduce();
        this.price = shop.getPrice();
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

    public void setNum(Integer num) {
        this.num = num;
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

    public Integer getNum() {
        return num;
    }
}
