package com.intive.toz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.intive.toz.financial.view.FinancialActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * FIXME Set proper name when implemented.
 * Main app screen.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    /**
     * temporary click listener to activity.
     * @param view to onClick.
     */
    @OnClick(R.id.btn_tmp_finance)
    public void showFinance(final View view) {
        Intent i = new Intent(this, FinancialActivity.class);
        startActivity(i);
    }
}
