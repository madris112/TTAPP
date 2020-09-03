package com.example.ttapp;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<String> tabnames=new ArrayList<>();
    List<Fragment> fraglist = new ArrayList<>();

    public void addfragment(Fragment frag,String title)
    {
        fraglist.add(frag);
        tabnames.add(title);

    }

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                todayclass tab1 = new todayclass();
                return tab1;
            case 1:
                todaydeadline tab2 = new todaydeadline();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabnames.get(position);
    }
}