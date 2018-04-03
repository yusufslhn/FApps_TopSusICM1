package com.example.asus.f_apps;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by ASUS on 31/03/2018.
 */

public class pager_adapter extends FragmentStatePagerAdapter{

    int no_of_tab;

    public pager_adapter(FragmentManager fm, int number_of_tabs)
    {
        super(fm);
        this.no_of_tab = number_of_tabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                tab_transaction Tab_transaction = new tab_transaction();
                return Tab_transaction;
            case 1:
                tab_budget Tab_budget = new tab_budget();
                return Tab_budget;
            case 2:
                tab_setting Tab_setting = new tab_setting();
                return Tab_setting;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return no_of_tab;
    }
}
