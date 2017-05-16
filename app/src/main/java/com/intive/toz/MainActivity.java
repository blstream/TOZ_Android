package com.intive.toz;


import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.intive.toz.common.view.navigationTabs.NavigationTabsPresenter;
import com.intive.toz.common.view.navigationTabs.NavigationTabsView;
import com.intive.toz.common.view.navigationTabs.Tab;
import com.intive.toz.common.view.navigationTabs.ViewPagerAdapter;
import com.intive.toz.info.view.FinancialActivity;
import com.intive.toz.login.LoginActivity;
import com.intive.toz.login.Session;
import com.intive.toz.network.NetworkStateReceiver;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main app screen.
 */
public class MainActivity
        extends MvpActivity<NavigationTabsView, NavigationTabsPresenter>
        implements NavigationTabsView, NetworkStateReceiver.NetworkStateListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.root_view)
    View rootView;

    private ViewPagerAdapter adapter;
    private NetworkStateReceiver networkStateReceiver;
    private Snackbar snackbar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        getPresenter().loadNavigationTabs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(networkStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkStateReceiver);
    }

    /**
     * temporary click listener to activity.
     *
     * @param view to onClick.
     */
    @OnClick(R.id.financial_btn)
    public void showFinance(final View view) {
        Intent i = new Intent(this, FinancialActivity.class);
        startActivity(i);
        initViews();
        getPresenter().loadNavigationTabs();
    }

    @NonNull
    @Override
    public NavigationTabsPresenter createPresenter() {
        return new NavigationTabsPresenter();
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        tabLayout.setupWithViewPager(viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        snackbar = Snackbar.make(rootView, getResources().getString(R.string.connection_problem), Snackbar.LENGTH_INDEFINITE);
    }


    @Override
    public void showTabs(final List<Tab> tabs) {
        for (Tab t : tabs) {
            adapter.addFragment(t.getTabFragment(), getResources().getString(t.getTabTitle()));
        }

        viewPager.setAdapter(adapter);

        for (int i = 0; i < tabs.size(); i++) {
            tabLayout.getTabAt(i).setIcon(tabs.get(i).getTabIcon());
        }
    }


    @Override
    public void networkAvailable() {
        snackbar.dismiss();
    }

    @Override
    public void networkUnavailable() {
        snackbar.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_log_in:
                Intent logIn = new Intent(this, LoginActivity.class);
                startActivity(logIn);
                return true;
            case R.id.menu_log_out:
                Session.logOut();
                Intent logOut = new Intent(this, MainActivity.class);
                logOut.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logOut);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        if (Session.isLogged()) {
            menu.findItem(R.id.menu_log_in).setVisible(false);
            menu.findItem(R.id.menu_log_out).setVisible(true);
        } else {
            menu.findItem(R.id.menu_log_in).setVisible(true);
            menu.findItem(R.id.menu_log_out).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }
}
