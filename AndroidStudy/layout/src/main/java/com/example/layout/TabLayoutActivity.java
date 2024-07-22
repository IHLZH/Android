package com.example.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.layout.adapters.WechatAdapter;
import com.example.layout.entity.Wechat;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        //布局控件
        ListView lv_wechat = findViewById(R.id.lv_wechat);
        //信息源
        List<Wechat> wechatList = Wechat.getWechatList();
        //创建一个adpter，连接布局子项视图和信息源
        WechatAdapter adapter = new WechatAdapter(
                this,
                R.layout.item_wechat,
                wechatList
        );
        //给布局控件绑定适配器
        lv_wechat.setAdapter(adapter);
        //设置监听事件
        lv_wechat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TabLayoutActivity.this,
                        wechatList.get(position).getNickName(),
                        Toast.LENGTH_LONG
                        ).show();
            }
        });

        TextView tv_contract = findViewById(R.id.tv_contract);
        TextView tv_find = findViewById(R.id.tv_find);
        TextView tv_mine = findViewById(R.id.tv_mine);

        TabLayout tabLayout = findViewById(R.id.Tab_nav);

        //给layout设置颜色（绿色）
        int green = ContextCompat.getColor(this, R.color.green);
        int black = ContextCompat.getColor(this, R.color.black);
        tabLayout.setTabTextColors(black, green);


        TabLayout.Tab wechat = tabLayout.newTab();//内部类
        wechat.setIcon(R.mipmap.message);
        wechat.setText("微信");

        TabLayout.Tab contract = tabLayout.newTab();
        contract.setIcon(R.mipmap.contract);
        contract.setText("通讯录");

        TabLayout.Tab find = tabLayout.newTab();
        find.setIcon(R.mipmap.find);
        find.setText("发现");

        TabLayout.Tab mine = tabLayout.newTab();
        mine.setIcon(R.mipmap.mine);
        mine.setText("我的");

        tabLayout.addTab(wechat);
        tabLayout.addTab(contract);
        tabLayout.addTab(find);
        tabLayout.addTab(mine);

        tabLayout.getTabAt(2).select();

        //设置底部栏图标点击事件
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中
                int position = tab.getPosition();
                switch(position){
                    case 0 :
                        wechat.setIcon(R.mipmap.msg);
                        contract.setIcon(R.mipmap.contract);
                        find.setIcon(R.mipmap.find);
                        mine.setIcon(R.mipmap.mine);
                        lv_wechat.setVisibility(View.VISIBLE);
                        tv_contract.setVisibility(View.INVISIBLE);
                        tv_find.setVisibility(View.INVISIBLE);
                        tv_mine.setVisibility(View.INVISIBLE);
                        break;
                    case 1 :
                        contract.setIcon(R.mipmap.contract_green);
                        wechat.setIcon(R.mipmap.message);
                        find.setIcon(R.mipmap.find);
                        mine.setIcon(R.mipmap.mine);
                        tv_contract.setVisibility(View.VISIBLE);
                        lv_wechat.setVisibility(View.INVISIBLE);
                        tv_find.setVisibility(View.INVISIBLE);
                        tv_mine.setVisibility(View.INVISIBLE);
                        break;
                    case 2 :
                        find.setIcon(R.mipmap.find_green);
                        contract.setIcon(R.mipmap.contract);
                        wechat.setIcon(R.mipmap.message);
                        mine.setIcon(R.mipmap.mine);
                        tv_find.setVisibility(View.VISIBLE);
                        lv_wechat.setVisibility(View.INVISIBLE);
                        tv_contract.setVisibility(View.INVISIBLE);
                        tv_mine.setVisibility(View.INVISIBLE);
                        break;
                    case 3 :
                        mine.setIcon(R.mipmap.mine_green);
                        find.setIcon(R.mipmap.find);
                        contract.setIcon(R.mipmap.contract);
                        wechat.setIcon(R.mipmap.message);
                        tv_mine.setVisibility(View.VISIBLE);
                        lv_wechat.setVisibility(View.INVISIBLE);
                        tv_contract.setVisibility(View.INVISIBLE);
                        tv_find.setVisibility(View.INVISIBLE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //重置时
            }
        });
    }
}