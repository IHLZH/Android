package com.example.homework_activity_jump.HomeWork;

import com.example.homework_activity_jump.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shop implements Serializable {
    public static Integer number = 0;
    private Integer idOfShop;
    private Integer pic;
    private String introduce;
    private String comments;
    private String price;
    public Integer num;
    public static List<Shop> shopList = new ArrayList<>();
    public Shop(){};

    public Shop(Integer idOfShop, Integer pic, String introduce, String comments, String price, Integer num){
        this.idOfShop = idOfShop;
        this.pic = pic;
        this.introduce = introduce;
        this.comments = comments;
        this.price = price;
        this.num = num;
    }

    public static List<Shop> get_Shop(){
        if(shopList.isEmpty()){
            shopList.add(new Shop(++number, R.drawable.pic1, "小米 10 双模5G 骁龙865 1亿像素8K电影", "100条评价 100%好评", "2333.00",0));
            shopList.add(new Shop(++number, R.drawable.pic2, "Apple iPhone 苹果11 双卡双待通4G手机", "100条评价 100%好评", "1145.00",0));
            shopList.add(new Shop(++number, R.drawable.pic3, "vivo X30 双模5G 6.4英寸全面屏 6400万超清", "100条评价 100%好评", "8848.00",0));
            shopList.add(new Shop(++number, R.drawable.pic4, "oppo 10 双模5G 骁龙865 1亿像素8K电影", "100条评价 100%好评", "9999.00",0));
            shopList.add(new Shop(++number, R.drawable.pic5, "荣耀 10 双模5G 骁龙865 1亿像素8K电影", "100条评价 100%好评", "9999.00",0));
            shopList.add(new Shop(++number, R.drawable.pic6, "华为 10 双模5G 骁龙865 1亿像素8K电影", "100条评价 100%好评", "9999.00",0));
        }
        return shopList;
    }

    public static List<Shop> getShop(){
        List<Shop> shopList1 = get_Shop();
        return shopList1;
    }

    public static List<Shop> getFood(){
        List<Shop> shopList = new ArrayList<>();
        shopList.add(new Food(R.drawable.pic7, "小米 10 双模5G 骁龙865 1亿像素8K电影", "100条评价 100%好评", "2333.00"));
        shopList.add(new Food(R.drawable.pic8, "Apple iPhone 苹果11 双卡双待通4G手机", "100条评价 100%好评", "1145.00"));
        shopList.add(new Food(R.drawable.pic9, "vivo X30 双模5G 6.4英寸全面屏 6400万超清", "100条评价 100%好评", "8848.00"));
        shopList.add(new Food(R.drawable.pic10, "小米 10 双模5G 骁龙865 1亿像素8K电影", "100条评价 100%好评", "9999.00"));
        return shopList;
    }

    public Integer getIdOfShop() {
        return idOfShop;
    }

    public Integer getPic() {
        return pic;
    }

    public void setPic(Integer pic) {
        this.pic = pic;
    }

    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
