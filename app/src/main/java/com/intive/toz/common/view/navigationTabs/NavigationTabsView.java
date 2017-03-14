package com.intive.toz.common.view.navigationTabs;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface NavigationTabsView extends MvpView {

    void showNavigationTabsForGuest();

    void showNavigationTabsForLoggedInUser();

    void shownTabsIconsForGuest();

    void showTabsIconsForLoggedInUser();

}
