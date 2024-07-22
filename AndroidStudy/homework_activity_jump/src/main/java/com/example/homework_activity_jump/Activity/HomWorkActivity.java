package com.example.homework_activity_jump.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.homework_activity_jump.homeWorkAdapter.FragmentShopAdapter;
import com.example.homework_activity_jump.homeWorkFragment.Fragment_Recommend;
import com.example.homework_activity_jump.homeWorkFragment.Fragment_food;
import com.example.homework_activity_jump.homeWorkFragment.Fragment_ladies;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import com.example.homework_activity_jump.R;


public class HomWorkActivity extends AppCompatActivity {

    private TabLayout tb_shop;
    private ViewPager2 vp_shop;
    private List<String> tabNameList;
    private List<Fragment> fragmentList;
    private GridView gv_recommend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_hom_work);

        findViews();

        initFragment();

        FragmentShopAdapter shopAdapter = new FragmentShopAdapter(
                fragmentList,
                this
        );
        vp_shop.setAdapter(shopAdapter);

        TabLayoutMediator mediator = new TabLayoutMediator(
                tb_shop,
                vp_shop,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabNameList.get(position));
                    }
                }
        );
        mediator.attach();
    }


    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment_Recommend());
        fragmentList.add(new Fragment_food());
        fragmentList.add(new Fragment_ladies());


        tabNameList = new ArrayList<>();
        tabNameList.add("推荐");
        tabNameList.add("食物");
        tabNameList.add("女装");
    }

    private void findViews() {
        View layout = LayoutInflater.from(HomWorkActivity.this).inflate(R.layout.item_recommend, null);
        gv_recommend = layout.findViewById(R.id.gv_recommend);
        tb_shop = findViewById(R.id.tb_shop);
        vp_shop = findViewById(R.id.vp_shop);
    }
}