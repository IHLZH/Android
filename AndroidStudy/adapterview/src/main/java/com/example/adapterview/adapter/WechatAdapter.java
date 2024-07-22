package com.example.adapterview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapterview.R;
import com.example.adapterview.entity.Wechat;

import java.util.List;

public class WechatAdapter extends BaseAdapter {
    private Context context;
    private Integer layoutId;
    private List<Wechat> wechatList;
    public WechatAdapter(Context context, Integer layoutId, List<Wechat> wechatList){
        this.context = context;
        this.layoutId = layoutId;
        this.wechatList = wechatList;
    }

    @Override
    public int getCount() { //获取数据源中数据的个数
        return wechatList.size();
    }

    @Override
    public Object getItem(int position) { //获取数据，获取position下标位置的数据
        return wechatList.get(position);
    }

    @Override
    public long getItemId(int position) { //返回数据下标的位置
        return position;
    }

    //拿到一个未绑定数据的视图
    //绑定数据
    //返回绑定好数据的视图
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
        }
        //获取视图中的头像，昵称，最后一条信息，并进行绑定
        ImageView iv_avatar = convertView.findViewById(R.id.lv_avatar);
        TextView tv_nickname = convertView.findViewById(R.id.tv_nickname);
        TextView tv_endMessage = convertView.findViewById(R.id.tv_endmessage);
        Wechat wechat = wechatList.get(position);
        iv_avatar.setImageResource(wechat.getAvatar());
        tv_nickname.setText(wechat.getNickName());
        tv_endMessage.setText(wechat.getEndMessage());


        //return null;
        return convertView;
    }
}
