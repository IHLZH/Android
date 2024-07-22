package com.example.adapterview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;


public class WechatFragmentAdapter extends FragmentStateAdapter {

    private List<Fragment> fragmentList;

    public WechatFragmentAdapter(List<Fragment> fragmentList, @NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

}
