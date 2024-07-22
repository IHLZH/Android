package com.example.homework_activity_jump.HomeWork;

public class Food extends Shop {
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
