package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practicalassignments.R;

import org.intellij.lang.annotations.JdkConstants;

import java.util.List;

import entity.Shop;
import entity.ShopOfShopCar;

public class adapter_lv_orders_details extends BaseAdapter {
    private Context context;
    private Integer layoutId;
    private List<ShopOfShopCar> shopList;
    public adapter_lv_orders_details(){};
    public adapter_lv_orders_details(Context context, Integer layoutId, List<ShopOfShopCar> shopList){
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

        TextView tv_order_details_name = convertView.findViewById(R.id.tv_order_details_name);
        ImageView iv_order_details_img = convertView.findViewById(R.id.iv_order_details_img);
        TextView tv_order_details_price = convertView.findViewById(R.id.tv_order_details_price);
        TextView tv_orders_datails_num = convertView.findViewById(R.id.tv_order_details_num);

        ShopOfShopCar shop = shopList.get(position);
        tv_order_details_name.setText(shop.getName());
        iv_order_details_img.setImageResource(shop.getPic());
        tv_order_details_price.setText(String.valueOf(shop.getPrice()));
        tv_orders_datails_num.setText(String.valueOf(shop.getNum()));

        return convertView;
    }
}
