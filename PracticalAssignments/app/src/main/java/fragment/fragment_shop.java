package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.practicalassignments.R;

import java.util.List;

import activity.shopDetailsActivity;
import adapter.adapter_gv_shop;
import entity.Shop;
import utils.ShopService;

public class fragment_shop extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_shop, null);
        GridView gv_shop = view.findViewById(R.id.gv_shop);
        List<Shop> shopList = ShopService.shopList;
        adapter_gv_shop adapterGvShop = new adapter_gv_shop(
                this.getContext(),
                R.layout.item_gv_shop,
                shopList
        );
        gv_shop.setAdapter(adapterGvShop);
        gv_shop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Shop shop = shopList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("shop", shop);
                intent.putExtra("shopData", bundle);
                intent.setClass(fragment_shop.this.getContext(), shopDetailsActivity.class);
                startActivity(intent);
            }
        });
        return gv_shop;
    }
}
