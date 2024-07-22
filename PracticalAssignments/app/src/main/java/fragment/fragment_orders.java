package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.practicalassignments.R;

import java.io.Serializable;
import java.util.List;

import activity.ordersDetailsActivity;
import adapter.adapter_lv_orders;
import entity.Orders;
import entity.ShopOfShopCar;
import utils.OrdersList;

public class fragment_orders extends Fragment {
    private adapter_lv_orders lvOrdersAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_orders, null);
        ListView lv_orders = view.findViewById(R.id.lv_orders);
        List<Orders> listOrders = OrdersList.getListOrders();
        lvOrdersAdapter = new adapter_lv_orders(
                this.getContext(),
                R.layout.item_lv_orders,
                listOrders
        );
        lv_orders.setAdapter(lvOrdersAdapter);
        lv_orders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Orders order = listOrders.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("order", order);
                intent.putExtra("orderData", bundle);
                intent.setClass(fragment_orders.this.getContext(), ordersDetailsActivity.class);
                startActivity(intent);
            }
        });
        return lv_orders;
    }

    @Override
    public void onResume() {
        super.onResume();
        lvOrdersAdapter.notifyDataSetChanged();
    }
}
