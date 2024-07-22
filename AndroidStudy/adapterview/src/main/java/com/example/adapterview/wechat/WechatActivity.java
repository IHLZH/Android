package com.example.adapterview.wechat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.adapterview.R;
import com.example.adapterview.adapter.WechatFragmentAdapter;
import com.example.adapterview.fragments.ContractFragment;
import com.example.adapterview.fragments.FindFragment;
import com.example.adapterview.fragments.MineFragment;
import com.example.adapterview.fragments.WechatFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class WechatActivity extends AppCompatActivity {
    private TabLayout tb_nav;
    private ViewPager2 vp_context;
    private List<Fragment> fragmentList;
    private List<String> tabNameList;
    private List<Integer> tabImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);
        //获取activity中的控件
        findViews();
        //创建fragment
        initFragments();
        //创建适配器
        WechatFragmentAdapter adapter = new WechatFragmentAdapter(
                fragmentList, this
        );
        //viewpager2绑定适配器
        vp_context.setAdapter(adapter);
        //TabLayout和ViewPager2关联 通过TabLayoutMediator
        TabLayoutMediator mediator = new TabLayoutMediator(
                tb_nav,
                vp_context,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    //设置每个tab位置的属性（图标，名称等）
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                        switch(position){
//                            case 0:
//                                tab.setText("微信");
//                                tab.setIcon(R.mipmap.message);
//                                break;
//                            case 1:
//                                tab.setText("联系人");
//                                tab.setIcon(R.mipmap.contract);
//                                break;
//                            case 2:
//                                tab.setText("发现");
//                                tab.setIcon(R.mipmap.find);
//                                break;
//                            case 3:
//                                tab.setText("我的");
//                                tab.setIcon(R.mipmap.mine);
//                                break;
//                        }
                        //按顺序设置图标和名称
                        tab.setText(tabNameList.get(position));
                        //tab.setIcon(tabImageList.get(position));
                    }
                }
        );
        //使效果生效
        mediator.attach();
    }

    private void initFragments() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new WechatFragment());
        fragmentList.add(new ContractFragment());
        fragmentList.add(new FindFragment());
        fragmentList.add(new MineFragment());

        tabNameList = new ArrayList<>();
        tabNameList.add("微信");
        tabNameList.add("联系人");
        tabNameList.add("发现");
        tabNameList.add("我的");

        tabImageList = new ArrayList<>();
        tabImageList.add(R.mipmap.message);
        tabImageList.add(R.mipmap.contract);
        tabImageList.add(R.mipmap.find);
        tabImageList.add(R.mipmap.mine);
    }

    private void findViews() {
        tb_nav = findViewById(R.id.tb_nav);
        vp_context = findViewById(R.id.vp_context);
    }
}