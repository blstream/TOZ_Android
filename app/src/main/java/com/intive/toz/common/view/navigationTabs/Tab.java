package com.intive.toz.common.view.navigationTabs;

/**
 * Navigation Tabs model class.
 */

public class Tab {

    private int tabTitle;
    private int tabIcon;

    /**
     *
     * @param tabTitle tab title
     * @param tabIcon tab icon
     */

    public Tab(final int tabTitle, final int tabIcon) {
        this.tabTitle = tabTitle;
        this.tabIcon = tabIcon;
    }

    /**
     * Getter to return Navigation Tab title.
     * @return tabTitle
     */

    public int getTabTitle() {
        return tabTitle;
    }

    /**
     * Getter to return Navigation Tab icon.
     * @return tabIcon
     */

    public int getTabIcon() {
        return tabIcon;
    }
}
