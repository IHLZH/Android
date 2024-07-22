package com.example.homework_activity_jump.homeWorkFragment;

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

import com.example.homework_activity_jump.Activity.ProductDetailsActivity;
import com.example.homework_activity_jump.HomeWork.Shop;
import com.example.homework_activity_jump.R;
import com.example.homework_activity_jump.homeWorkAdapter.GridAdapter;

import java.util.List;

public class Fragment_Recommend extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_recommend, null);
        GridView gv_recommend = view.findViewById(R.id.gv_recommend);
        List<Shop> shopList = Shop.getShop();
        GridAdapter gridAdapter = new GridAdapter(
                this.getContext(),
                R.layout.item_goods,
                shopList
        );
        gv_recommend.setAdapter(gridAdapter);
        gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Shop shop = shopList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("shop", shop);
                intent.putExtra("msg", bundle);
                intent.setClass(Fragment_Recommend.this.getContext(), ProductDetailsActivity.class);
                startActivity(intent);
            }
        });
        return gv_recommend;
    }
}
