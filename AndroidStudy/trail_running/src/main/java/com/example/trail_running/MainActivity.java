package com.example.trail_running;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends Activity {
    private TabLayout tab_lepao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        TabLayout.Tab dongtai = tab_lepao.newTab();//内部类
        dongtai.setIcon(R.mipmap.dongtai);
        dongtai.setText("动态");

        TabLayout.Tab xiaoyuan = tab_lepao.newTab();
        xiaoyuan.setIcon(R.mipmap.xiaoyuan);
        xiaoyuan.setText("校园");

        TabLayout.Tab lepao = tab_lepao.newTab();
        lepao.setIcon(R.mipmap.lepao);

        lepao.setText("乐跑");

        TabLayout.Tab wode = tab_lepao.newTab();
        wode.setIcon(R.mipmap.mine);
        wode.setText("我的");

        int gray = ContextCompat.getColor(this, R.color.gray);
        int green = ContextCompat.getColor(this, R.color.green);
        tab_lepao.setTabTextColors(gray, green);

        tab_lepao.addTab(dongtai);
        tab_lepao.addTab(xiaoyuan);
        tab_lepao.addTab(lepao);
        tab_lepao.addTab(wode);

        tab_lepao.getTabAt(2).select();

        tab_lepao.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position){
                    case 0:
                        dongtai.setIcon(R.mipmap.dongtai_green);
                        xiaoyuan.setIcon(R.mipmap.xiaoyuan);
                        lepao.setIcon(R.mipmap.lepao);
                        wode.setIcon(R.mipmap.mine);
                        break;
                    case 1:
                        dongtai.setIcon(R.mipmap.dongtai);
                        xiaoyuan.setIcon(R.mipmap.xiaoyuan_green);
                        lepao.setIcon(R.mipmap.lepao);
                        wode.setIcon(R.mipmap.mine);
                        break;
                    case 2:
                        dongtai.setIcon(R.mipmap.dongtai);
                        xiaoyuan.setIcon(R.mipmap.xiaoyuan);
                        lepao.setIcon(R.mipmap.lepao_green);
                        wode.setIcon(R.mipmap.mine);
                        break;
                    case 3:
                        dongtai.setIcon(R.mipmap.dongtai);
                        xiaoyuan.setIcon(R.mipmap.xiaoyuan);
                        lepao.setIcon(R.mipmap.lepao);
                        wode.setIcon(R.mipmap.mine_green);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void findViews() {
        tab_lepao = findViewById(R.id.tab_lepao);
    }
}