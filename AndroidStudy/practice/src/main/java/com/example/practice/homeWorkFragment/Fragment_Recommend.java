package com.example.practice.homeWorkFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.practice.HomeWork.HomWorkActivity;
import com.example.practice.HomeWork.Shop;
import com.example.practice.R;
import com.example.practice.homeWorkAdapter.GridAdapter;

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
        return gv_recommend;
    }
}
