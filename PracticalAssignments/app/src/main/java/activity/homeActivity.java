package activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.practicalassignments.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import adapter.adapter_fg_home;
import fragment.fragment_mine;
import fragment.fragment_orders;
import fragment.fragment_shop;
import utils.ShopService;

public class homeActivity extends AppCompatActivity {
    private TabLayout tb_home;
    private ViewPager2 vp_home;
    private List<String> tabNameList;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initdata();

        findViews();

        initFragment();

        adapter_fg_home adapterFgHome = new adapter_fg_home(
                fragmentList,
                this
        );
        vp_home.setAdapter(adapterFgHome);

        TabLayoutMediator mediator = new TabLayoutMediator(
                tb_home,
                vp_home,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabNameList.get(position));
                    }
                }
        );
        mediator.attach();
    }

    private void initdata() {
        new ShopService().getShop();
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new fragment_shop());
        fragmentList.add(new fragment_orders());
        fragmentList.add(new fragment_mine());

        tabNameList = new ArrayList<>();
        tabNameList.add("商品");
        tabNameList.add("订单");
        tabNameList.add("我的");
    }

    private void findViews() {
        tb_home = findViewById(R.id.tb_home);
        vp_home = findViewById(R.id.vp_home);
    }
}