package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practicalassignments.R;

import java.util.List;

import entity.Shop;

public class adapter_gv_shop extends BaseAdapter {
    private Context context;
    private Integer layoutId;
    private List<Shop> shopList;

    public adapter_gv_shop(){};

    public adapter_gv_shop(Context context, Integer layoutId, List<Shop> shopList){
        this.context = context;
        this.layoutId = layoutId;
        this.shopList = shopList;
    }

    @Override
    public int getCount() {
        return shopList.size();
    }

    @Override
    public Object getItem(int position) {
        return shopList.get(position);
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
        ImageView iv_pic = convertView.findViewById(R.id.iv_pic);
        TextView tv_introduce = convertView.findViewById(R.id.tv_introduce);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_price = convertView.findViewById(R.id.tv_price);
        TextView tv_inventory = convertView.findViewById(R.id.tv_inventory);

        Shop shop = shopList.get(position);
        iv_pic.setImageResource(shop.getPic());
        tv_introduce.setText(shop.getIntroduce());
        tv_name.setText(shop.getName());
        tv_price.setText(String.valueOf(shop.getPrice()));
        tv_inventory.setText(String.valueOf(shop.getInventory()));

        return convertView;
    }
}
