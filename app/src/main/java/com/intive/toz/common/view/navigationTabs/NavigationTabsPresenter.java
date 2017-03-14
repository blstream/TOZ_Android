package com.intive.toz.common.view.navigationTabs;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

public class NavigationTabsPresenter extends MvpBasePresenter<NavigationTabsView>{

    private int userStatus = 2;

    public void loadNavigationTabs() {

        if (userStatus == 1) {
            getView().showNavigationTabsForGuest();
        }
        else {
            getView().showNavigationTabsForLoggedInUser();
        }
    }

    public void loadNavigationTabsIcons() {

        if (userStatus == 1) {
            getView().shownTabsIconsForGuest();
        }
        else {
            getView().showTabsIconsForLoggedInUser();
        }
    }

}
