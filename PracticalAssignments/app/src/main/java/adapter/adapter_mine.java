package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practicalassignments.R;

import entity.Userinfo;
import utils.UserUtil;

public class adapter_mine extends BaseAdapter {
    private Context context;
    private Integer layoutId;
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
        }

        ImageView iv_avater = convertView.findViewById(R.id.iv_avater);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_address = convertView.findViewById(R.id.tv_address);
        Button btn_modify = convertView.findViewById(R.id.btn_modify);

        Userinfo userinfo = UserUtil.user;
        tv_name.setText(userinfo.getName());
        tv_address.setText(userinfo.getAddress());
        iv_avater.setImageResource(userinfo.getAvater());

        return convertView;
    }
}
