package com.example.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.layout.adapters.PracticeAdapter;
import com.example.layout.entity.Practice;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class TabPracticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_practice);

        GridView gv_product = findViewById(R.id.gv_product);
        List<Practice> list = Practice.getPractice();
        PracticeAdapter adapter = new PracticeAdapter(
                TabPracticeActivity.this,
                R.layout.item_practice, //子控件id
                list
        );
        gv_product.setAdapter(adapter);
        gv_product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TabPracticeActivity.this, list.get(position).getNickName(), Toast.LENGTH_SHORT).show();
            }
        });

        TabLayout tabLayout = findViewById(R.id.tab_mode);

        TabLayout.Tab pyro = tabLayout.newTab();
        pyro.setIcon(R.mipmap.pyro);

        TabLayout.Tab hydro = tabLayout.newTab();
        hydro.setIcon(R.mipmap.hydro);

        TabLayout.Tab anemo = tabLayout.newTab();
        anemo.setIcon(R.mipmap.anemo);

        TabLayout.Tab electro = tabLayout.newTab();
        electro.setIcon(R.mipmap.electro);

        TabLayout.Tab cryo = tabLayout.newTab();
        cryo.setIcon(R.mipmap.cryo);

        TabLayout.Tab dendro = tabLayout.newTab();
        dendro.setIcon(R.mipmap.dendro);

        TabLayout.Tab geo = tabLayout.newTab();
        geo.setIcon(R.mipmap.geo);

        tabLayout.addTab(pyro);
        tabLayout.addTab(hydro);
        tabLayout.addTab(anemo);
        tabLayout.addTab(electro);
        tabLayout.addTab(cryo);
        tabLayout.addTab(dendro);
        tabLayout.addTab(geo);

        tabLayout.getTabAt(0);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch(position){
                    case 0:
                        pyro.setIcon(R.mipmap.pyro_copy);
                        hydro.setIcon(R.mipmap.hydro);
                        anemo.setIcon(R.mipmap.anemo);
                        electro.setIcon(R.mipmap.electro);
                        cryo.setIcon(R.mipmap.cryo);
                        dendro.setIcon(R.mipmap.dendro);
                        geo.setIcon(R.mipmap.geo);
                        gv_product.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        pyro.setIcon(R.mipmap.pyro);
                        hydro.setIcon(R.mipmap.hydro_copy);
                        anemo.setIcon(R.mipmap.anemo);
                        electro.setIcon(R.mipmap.electro);
                        cryo.setIcon(R.mipmap.cryo);
                        dendro.setIcon(R.mipmap.dendro);
                        geo.setIcon(R.mipmap.geo);
                        break;
                    case 2:
                        pyro.setIcon(R.mipmap.pyro);
                        hydro.setIcon(R.mipmap.hydro);
                        anemo.setIcon(R.mipmap.anemo_copy);
                        electro.setIcon(R.mipmap.electro);
                        cryo.setIcon(R.mipmap.cryo);
                        dendro.setIcon(R.mipmap.dendro);
                        geo.setIcon(R.mipmap.geo);
                        break;
                    case 3:
                        pyro.setIcon(R.mipmap.pyro);
                        hydro.setIcon(R.mipmap.hydro);
                        anemo.setIcon(R.mipmap.anemo);
                        electro.setIcon(R.mipmap.electro_copy);
                        cryo.setIcon(R.mipmap.cryo);
                        dendro.setIcon(R.mipmap.dendro);
                        geo.setIcon(R.mipmap.geo);
                        break;
                    case 4:
                        pyro.setIcon(R.mipmap.pyro);
                        hydro.setIcon(R.mipmap.hydro);
                        anemo.setIcon(R.mipmap.anemo);
                        electro.setIcon(R.mipmap.electro);
                        cryo.setIcon(R.mipmap.cryo_copy);
                        dendro.setIcon(R.mipmap.dendro);
                        geo.setIcon(R.mipmap.geo);
                        break;
                    case 5:
                        pyro.setIcon(R.mipmap.pyro);
                        hydro.setIcon(R.mipmap.hydro);
                        anemo.setIcon(R.mipmap.anemo);
                        electro.setIcon(R.mipmap.electro);
                        cryo.setIcon(R.mipmap.cryo);
                        dendro.setIcon(R.mipmap.dendro_copy);
                        geo.setIcon(R.mipmap.geo);
                        break;
                    case 6:
                        pyro.setIcon(R.mipmap.pyro);
                        hydro.setIcon(R.mipmap.hydro);
                        anemo.setIcon(R.mipmap.anemo);
                        electro.setIcon(R.mipmap.electro);
                        cryo.setIcon(R.mipmap.cryo);
                        dendro.setIcon(R.mipmap.dendro);
                        geo.setIcon(R.mipmap.geo_copy);
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
}