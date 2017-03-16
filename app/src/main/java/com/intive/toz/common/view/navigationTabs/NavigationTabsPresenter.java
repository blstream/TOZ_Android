package com.intive.toz.common.view.navigationTabs;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.intive.toz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter for Navigation Tabs.
 */

public class NavigationTabsPresenter extends MvpBasePresenter<NavigationTabsView> {

    private int userStatus = 2;

    /**
     * Set title and icon to Navigate Tab object.
     */

    public void loadNavigationTabs() {
        List<Tab> tabs = new ArrayList<>();

        if (userStatus == 1) {
            tabs.add(new Tab(R.string.navigation_tab_neews, R.mipmap.ic_launcher));
            tabs.add(new Tab(R.string.navigation_tab_gallery, R.mipmap.ic_launcher));
        } else {
            tabs.add(new Tab(R.string.navigation_tab_neews, R.mipmap.ic_launcher));
            tabs.add(new Tab(R.string.navigation_tab_gallery, R.mipmap.ic_launcher));
            tabs.add(new Tab(R.string.navigation_tab_schedule, R.mipmap.ic_launcher));
            tabs.add(new Tab(R.string.navigation_tab_account, R.mipmap.ic_launcher));
        }
        getView().showTabs(tabs);
    }

}
