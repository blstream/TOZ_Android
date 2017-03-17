package com.intive.toz.common.view.navigationTabs;

import android.support.v4.app.Fragment;

/**
 * Navigation Tabs model class.
 */
public class Tab {

    private int tabTitle;
    private int tabIcon;
    private Fragment tabFragment;

    /**
     * Instantiates a new Tab.
     *
     * @param tabTitle    the tab title
     * @param tabIcon     the tab icon
     * @param tabFragment the tab fragment
     */
    public Tab(final int tabTitle, final int tabIcon, final Fragment tabFragment) {
        this.tabTitle = tabTitle;
        this.tabIcon = tabIcon;
        this.tabFragment = tabFragment;
    }

    /**
     * Gets tab title.
     *
     * @return the tab title
     */
    public int getTabTitle() {
        return tabTitle;
    }

    /**
     * Gets tab icon.
     *
     * @return the tab icon
     */
    public int getTabIcon() {
        return tabIcon;
    }

    /**
     * Gets tab fragment.
     *
     * @return the tab fragment
     */
    public Fragment getTabFragment() {
        return tabFragment;
    }

}
