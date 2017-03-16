package com.intive.toz;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.support.annotation.NonNull;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import com.intive.toz.network.PetsApi;
import com.intive.toz.network.ApiClient;
import com.intive.toz.common.view.navigationTabs.NavigationTabsFragment;
import com.intive.toz.common.view.navigationTabs.NavigationTabsView;
import com.intive.toz.common.view.navigationTabs.NavigationTabsPresenter;
import com.intive.toz.common.view.navigationTabs.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * FIXME Set proper name when implemented.
 * Main app screen.
 */
public class MainActivity extends MvpActivity<NavigationTabsView, NavigationTabsPresenter> implements  NavigationTabsView{


    List<Pet> petsList;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        getPresenter().loadNavigationTabs();
        getPresenter().loadNavigationTabsIcons();
    }

        /**
         *
         * Example of use retrofit.
         *
         *
         * */
        petsList = new ArrayList<>();
        final PetsApi petsApi = ApiClient.getPetsApiService();
        final Call<List<Pet>> call = petsApi.getGalleryPetsListCall();


        call.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(final Call<List<Pet>> call, final Response<List<Pet>> response) {
                Log.i("RESPONSE", "onResponse: ");
                if (response.isSuccessful()) {
                    petsList = response.body();
                    for (Pet p : petsList) {
                        Log.e("Lista ", "imie " + p.getName());

                    }
                }
            }
    @NonNull
    @Override
    public NavigationTabsPresenter createPresenter() {
        return new NavigationTabsPresenter();
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
    }

            @Override
            public void onFailure(final Call<List<Pet>> call, final Throwable t) {
                Log.e("RESPONSE", "onFailure: ");
    @Override
    public void showNavigationTabsForGuest() {
        adapter.addFragment(new NavigationTabsFragment().newInstance("One"), getResources().getString(R.string.navigation_tab_neews));
        adapter.addFragment(new NavigationTabsFragment().newInstance("Two"), getResources().getString(R.string.navigation_tab_gallery));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void showNavigationTabsForLoggedInUser() {
        adapter.addFragment(new NavigationTabsFragment().newInstance("One"), getResources().getString(R.string.navigation_tab_neews));
        adapter.addFragment(new NavigationTabsFragment().newInstance("Two"), getResources().getString(R.string.navigation_tab_gallery));
        adapter.addFragment(new NavigationTabsFragment().newInstance("Three"), getResources().getString(R.string.navigation_tab_schedule));
        adapter.addFragment(new NavigationTabsFragment().newInstance("Four"), getResources().getString(R.string.navigation_tab_account));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void shownTabsIconsForGuest() {
        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_launcher);
        tabLayout.getTabAt(1).setIcon(R.mipmap.ic_launcher);
    }

    @Override
    public void showTabsIconsForLoggedInUser() {
        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_launcher);
        tabLayout.getTabAt(1).setIcon(R.mipmap.ic_launcher);
        tabLayout.getTabAt(2).setIcon(R.mipmap.ic_launcher);
        tabLayout.getTabAt(3).setIcon(R.mipmap.ic_launcher);
    }
}
