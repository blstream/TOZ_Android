package com.intive.toz.petDetails;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ScrollView;

import com.intive.toz.R;
import com.intive.toz.petDetails.details_fragment.PetDetailsFragment;
import com.intive.toz.petDetails.financial_button.FinancialInfoFragment;
import com.intive.toz.petDetails.view_pager.PetImgFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity containing fragment with pet details object.
 **/

public class PetDetailsActivity extends AppCompatActivity {

    @BindView(R.id.btn_help)
    Button B1;
    boolean fragmentHelpVisible = false;
    int colorButton;
    Handler handler;
    @BindView(R.id.sv_pet_details)
    ScrollView scrollView;
    boolean flagIsAlreadyScrooling = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        String message = getIntent().getStringExtra("petKey");
        PetDetailsFragment petDetailsFragment = (PetDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detail_pets_fragment);
        petDetailsFragment.setPetID(message);

        PetImgFragment petImgFragment = (PetImgFragment) getSupportFragmentManager().findFragmentById(R.id.detail_pets_images);
        petImgFragment.setPetID(message);

        handler = new Handler();

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(R.string.pet_detail_action_bar_title);
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_help)
    public void onLoginButtonClick() {
        if (fragmentHelpVisible == false) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FinancialInfoFragment hello = new FinancialInfoFragment();
            fragmentTransaction.add(R.id.fragment_container, hello, "HELLO");
            fragmentTransaction.commit();
            fragmentHelpVisible = true;
            colorButton = R.color.greyDark;
            setColorButton(colorButton);
            scrollToDownByTreeLayout();
            flagIsAlreadyScrooling = false;
        } else {
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.fragment_container)).commit();
            fragmentHelpVisible = false;
            colorButton = R.color.colorAccent;
            setColorButton(colorButton);
        }
    }

    public void scrollToDownByTreeLayout() {
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (flagIsAlreadyScrooling == false) {
                    handler.post(r);
                    flagIsAlreadyScrooling = !flagIsAlreadyScrooling;
                }
            }
        });
    }

    public void setColorButton(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            B1.setBackgroundColor(getColor(color));
        } else {
            B1.setBackgroundColor(getResources().getColor(color));
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    final Runnable r = new Runnable() {
        @Override
        public void run() {
            handler.post(this);
            scrollView.scrollTo(0, scrollView.getBottom());
            handler.removeCallbacks(this);
        }
    };
}
