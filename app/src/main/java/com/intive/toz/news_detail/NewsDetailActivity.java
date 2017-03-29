package com.intive.toz.news_detail;


import android.support.v4.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.intive.toz.R;

/**
 * Created by K on 2017-03-29.
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
}
