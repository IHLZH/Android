package entity;

import java.io.Serializable;

public class Userinfo implements Serializable {
    private String account;
    private String passward;
    private Integer avater;
    private String name;
    private String address;
    public Userinfo(){};

    public Userinfo(Userinfo userinfo){
        this.avater = userinfo.getAvater();
        this.name = userinfo.getName();
        this.address = userinfo.getAddress();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public Integer getAvater() {
        return avater;
    }

    public void setAvater(Integer avater) {
        this.avater = avater;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassward() {
        return passward;
    }

    public void setPassward(String passward) {
        this.passward = passward;
    }
}
