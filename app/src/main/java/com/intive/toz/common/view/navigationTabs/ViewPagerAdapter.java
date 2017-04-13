package com.intive.toz.common.view.navigationTabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *  Navigation tabs adapter.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    /**
     * ButtonsView adapter.
     * @param manager fragment manager
     */

    public ViewPagerAdapter(final FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(final int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    /**
     * Add fragment.
     * @param fragment tab fragment
     * @param title fragment title
     */

    public void addFragment(final Fragment fragment, final String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        return fragmentTitleList.get(position);
    }
}


