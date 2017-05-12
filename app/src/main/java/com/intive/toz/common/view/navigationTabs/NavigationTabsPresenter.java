package com.intive.toz.common.view.navigationTabs;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.R;
import com.intive.toz.account.AccountFragment;
import com.intive.toz.login.Session;
import com.intive.toz.news.view.NewsFragment;
import com.intive.toz.petslist.view.PetsListFragment;
import com.intive.toz.schedule.view.ScheduleFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter for Navigation Tabs.
 */

public class NavigationTabsPresenter extends MvpBasePresenter<NavigationTabsView> {

    /**
     * Set title and icon to Navigate Tab object.
     */

    public void loadNavigationTabs() {
        List<Tab> tabs = new ArrayList<>();


        tabs.add(new Tab(R.string.navigation_tab_news, R.drawable.ic_home_white_24dp, NewsFragment.newInstance()));
        tabs.add(new Tab(R.string.navigation_tab_gallery, R.drawable.ic_pets_white_24dp, new PetsListFragment()));

        if (Session.isLogged() && (Session.getRole().equals("VOLUNTEER"))) {
            tabs.add(new Tab(R.string.navigation_tab_schedule, R.drawable.ic_today_white_24dp, ScheduleFragment.newInstance()));
            tabs.add(new Tab(R.string.navigation_tab_account, R.drawable.ic_person_white_24dp, AccountFragment.newInstance()));
        }

        getView().showTabs(tabs);
    }

}
