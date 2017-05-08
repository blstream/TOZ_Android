package com.intive.toz.kindOfHelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.intive.toz.R;
import com.intive.toz.info.view.FinancialActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Activity with navigate button to FinancialActivity and FormActivity.
 */
public class KindOfHelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind_of_help);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Navigate to FinancialActivity.
     *
     * @param view to onClick.
     */
    @OnClick(R.id.volunteer_btn)
    public void showCharityPayment(final View view) {
        Intent i = new Intent(this, FinancialActivity.class);
        startActivity(i);
    }

    /**
     * Navigate to FormActivity.
     *
     * @param view to onClick.
     */
    @OnClick(R.id.charity_payment_btn)
    public void showVolunteer(final View view) {
        Intent i = new Intent(this, KindOfHelpActivity.class);
        startActivity(i);
    }
}
