package com.example.layout.entity;

import com.example.layout.R;

import java.util.ArrayList;
import java.util.List;

public class Practice {
    private Integer avatar;
    private String nickName;
    private String endMessage;

    public Practice(){

    }
    public Practice(Integer avatar, String nickName, String endMessage){
        this.avatar = avatar;
        this.nickName = nickName;
        this.endMessage = endMessage;
    }

    //数据源
    public static List<Practice> getPractice(){
        List<Practice> list = new ArrayList<>();
        list.add(new Practice(R.mipmap.liyue, "cxk", "师傅！救我口牙！"));
        list.add(new Practice(R.mipmap.liyue, "cxk1", "2333333"));
        list.add(new Practice(R.mipmap.liyue, "cxk2", "[语音聊天]"));
        return list;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public String getEndMessage() {
        return endMessage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public void setEndMessage(String endMessage) {
        this.endMessage = endMessage;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
