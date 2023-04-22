package com.example.finalproject;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.finalproject.Fragments.CarFragment;
import com.example.finalproject.Fragments.SuvFragment;

public class PagerAdapter extends FragmentStateAdapter {

    private int mNumberTabs;
    private final Context mContext;

    public PagerAdapter(FragmentActivity fragmentActivity, int numTabs, Context context){
        super(fragmentActivity);
        this.mNumberTabs = numTabs;
        this.mContext = context;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new CarFragment();
            case 1:
                return new SuvFragment();
            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return mNumberTabs;
    }
}