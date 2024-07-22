package com.example.adapterview.entity;



import com.example.adapterview.R;

import java.util.ArrayList;
import java.util.List;

public class Wechat {
    private Integer avatar;
    private String nickName;
    private String endMessage;

    public Wechat(){

    }
    public Wechat(Integer avatar, String nickName, String endMessage){
        this.avatar = avatar;
        this.nickName = nickName;
        this.endMessage = endMessage;
    }

    //模拟聊天数据
    public static List<Wechat> getWechatList(){
        List<Wechat> wechatList = new ArrayList<>();
        wechatList.add(new Wechat(R.mipmap.cxk, "cxk", "师傅！救我口牙！"));
        wechatList.add(new Wechat(R.mipmap.cxk1, "cxk1", "2333333"));
        wechatList.add(new Wechat(R.mipmap.cxk2, "cxk2", "[语音聊天]"));
        return wechatList;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEndMessage() {
        return endMessage;
    }

    public void setEndMessage(String endMessage) {
        this.endMessage = endMessage;
    }
}
