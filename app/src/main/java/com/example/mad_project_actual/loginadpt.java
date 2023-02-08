package com.example.mad_project_actual;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;

@Deprecated
public class loginadpt extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;
    TabLayout tabl;
    ViewPager vpger;
    public loginadpt(FragmentManager fm, Context context, int totalTabs)
    {
        super(fm);
        this.context=context;
        this.totalTabs=totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int pos)
    {
        switch (pos)
        {
            case 0:
                login loginf=new login();
                return loginf;

            case 1:
                signup singupf=new signup();
                return singupf;

            default:

                return new login();
        }
    }
}
