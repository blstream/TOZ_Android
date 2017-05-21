package com.intive.toz.news_detail.view;


import android.content.Intent;
import android.support.v4.app.FragmentManager;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.intive.toz.MainActivity;
import com.intive.toz.R;
import com.intive.toz.login.LoginActivity;
import com.intive.toz.login.Session;

/**
 *  Activity containing fragment with news detail object.
 */

public class NewsDetailActivity extends AppCompatActivity {

    public static final String ID = "ID";
    private String id;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            id = null;
        } else {
            id = bundle.getString("ID");
        }

        Bundle extras = new Bundle();
        extras.putString(ID, id);
        NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        newsDetailFragment.setArguments(extras);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_news_detail, newsDetailFragment);
        fragmentTransaction.commit();
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
            case android.R.id.home:
                this.finish();
                return true;
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
