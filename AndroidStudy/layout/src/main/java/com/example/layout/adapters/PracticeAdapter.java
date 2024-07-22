package com.example.layout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.layout.entity.Practice;
import com.example.layout.R;

import java.util.List;

public class PracticeAdapter extends BaseAdapter {

    private Context context; //上下文
    private Integer layoutId; //布局子控件id
    private List<Practice> list; //数据源

    public PracticeAdapter(){

    }
    public PracticeAdapter(Context context, Integer layoutId, List<Practice> list){
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
    }

    @Override
    public int getCount() { //数据个数
        return list.size();
    }

    @Override
    public Object getItem(int position) { //对应位置的item
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
        }

        ImageView iv_avatar1 = convertView.findViewById(R.id.iv_avatar1);
        TextView tv_nickname1 = convertView.findViewById(R.id.tv_nickname1);
        TextView tv_endmessage1 = convertView.findViewById(R.id.tv_endmessage1);
        Practice practice = list.get(position);
        iv_avatar1.setImageResource(practice.getAvatar());
        tv_nickname1.setText(practice.getNickName());
        tv_endmessage1.setText(practice.getEndMessage());

        return convertView;
    }
}
