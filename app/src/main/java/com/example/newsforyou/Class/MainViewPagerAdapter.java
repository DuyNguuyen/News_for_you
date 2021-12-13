package com.example.newsforyou.Class;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.newsforyou.Fragments.HomeFragment;
import com.example.newsforyou.Fragments.SaveFragment;
import com.example.newsforyou.Fragments.SearchFragment;
import com.example.newsforyou.Fragments.SettingFragment;

public class MainViewPagerAdapter extends FragmentStateAdapter {

    public MainViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new SaveFragment();
            case 3:
                return new SettingFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
