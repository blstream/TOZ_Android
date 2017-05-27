package com.intive.toz.kindOfHelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.intive.toz.R;
import com.intive.toz.info.view.FinancialActivity;
import com.intive.toz.kindOfHelp.model.KindOfHelp;
import com.intive.toz.kindOfHelp.presenter.KindOfHelpPresenter;
import com.intive.toz.kindOfHelp.view.KindOfHelpView;
import com.intive.toz.volunteerForm.view.VolunteerFormActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Activity with navigate button to FinancialActivity and FormActivity.
 */
public class KindOfHelpActivity extends MvpActivity<KindOfHelpView, KindOfHelpPresenter>
        implements KindOfHelpView  {

    @BindView(R.id.volunteer_img)
    ImageView volunteerImage;

    @BindView(R.id.charity_payment_img)
    ImageView charityPaymentImage;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind_of_help);
        ButterKnife.bind(this);
        getPresenter().loadKindOfHelp();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public KindOfHelpPresenter createPresenter() {
        return new KindOfHelpPresenter();
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
        Intent i = new Intent(this, VolunteerFormActivity.class);
        startActivity(i);
    }

    /**
     * Navigate to FormActivity.
     *
     * @param view to onClick.
     */
    @OnClick(R.id.charity_payment_btn)
    public void showVolunteer(final View view) {
        Intent i = new Intent(this, FinancialActivity.class);
        startActivity(i);
    }

    @Override
    public void showKindOfHelp(final KindOfHelp kindOfHelp) {
        volunteerImage.setImageResource(kindOfHelp.getVolunteerPhoto());
        charityPaymentImage.setImageResource(kindOfHelp.getCharityPaymentPhoto());
    }
}
