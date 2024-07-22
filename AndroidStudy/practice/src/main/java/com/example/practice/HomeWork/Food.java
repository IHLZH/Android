package com.example.practice.HomeWork;

import com.example.practice.R;

import java.util.ArrayList;
import java.util.List;

public class Food extends Shop{
    private Integer pic;
    private String introduce;
    private String comments;
    private String price;

    public Food(){};
    public Food(Integer pic, String introduce, String comments, String price){
        this.pic = pic;
        this.introduce = introduce;
        this.comments = comments;
        this.price = price;
    }

//    public static List<Food> getFood(){
//        List<Food> shopList = new ArrayList<>();
//        shopList.add(new Food(R.drawable.pic7, "小米 10 双模5G 骁龙865 1亿像素8K电影", "100条评价 100%好评", "￥2333.00"));
//        shopList.add(new Food(R.drawable.pic8, "Apple iPhone 苹果11 双卡双待通4G手机", "100条评价 100%好评", "￥1145.00"));
//        shopList.add(new Food(R.drawable.pic9, "vivo X30 双模5G 6.4英寸全面屏 6400万超清", "100条评价 100%好评", "￥8848.00"));
//        shopList.add(new Food(R.drawable.pic10, "小米 10 双模5G 骁龙865 1亿像素8K电影", "100条评价 100%好评", "￥9999.00"));
//        return shopList;
//    }

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
}
