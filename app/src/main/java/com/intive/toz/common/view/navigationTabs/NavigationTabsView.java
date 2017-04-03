package com.intive.toz.common.view.navigationTabs;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

/**
 * Interface ButtonsView for Navigation Tabs.
 */

public interface NavigationTabsView extends MvpView {

    /**
     * Send list with Navigate Tabs.
     * @param tabs list with Navigate Tabs
     */

    void showTabs(List<Tab> tabs);
}
