package com.intive.toz.common.view.navigationTabs;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * Interface View for Navigation Tabs.
 */

public interface NavigationTabsView extends MvpView {

    /**
     * Send list with Navigate Tabs.
     * @param tabs list with Navigate Tabs
     */

    void showTabs(List<Tab> tabs);
}
