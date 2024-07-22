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

import entity.Orders;

public class adapter_lv_orders extends BaseAdapter {
    private Context context;
    private Integer layoutId;
    private List<Orders> listOrders;
    public adapter_lv_orders(){};
    public adapter_lv_orders(Context context, Integer layoutId, List<Orders> listOrders){
        this.context = context;
        this.layoutId = layoutId;
        this.listOrders = listOrders;
    }
    @Override
    public int getCount() {
        return listOrders.size();
    }

    @Override
    public Object getItem(int position) {
        return listOrders.get(position);
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

        TextView tv_orders_date = convertView.findViewById(R.id.tv_orders_date);
        TextView tv_orders_sumprice = convertView.findViewById(R.id.tv_orders_sumprice);

        Orders orders = listOrders.get(position);
        tv_orders_date.setText(orders.getDate());
        tv_orders_sumprice.setText(String.valueOf(orders.getSumPrice()));

        return convertView;
    }
}
