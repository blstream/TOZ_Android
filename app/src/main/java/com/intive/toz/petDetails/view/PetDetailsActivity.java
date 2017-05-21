package com.intive.toz.petDetails.view;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ScrollView;

import com.intive.toz.R;
import com.intive.toz.petslist.model.Pet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.intive.toz.R.id.sv_pet_details;

/**
 * Activity containing fragment with pet details object.
 **/

public class PetDetailsActivity extends AppCompatActivity implements PetDetailsFragment.DataPassListener {

    @BindView(R.id.btn_help)
    Button buttonDonate;

    @BindView(sv_pet_details)
    ScrollView scrollView;

    boolean fragmentHelpVisible = false;
    int colorButton;
    Handler handler;
    boolean flagIsAlreadyScrooling = false;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        fragmentManager = getSupportFragmentManager();

        String message = getIntent().getStringExtra("petKey");
        PetDetailsFragment petDetailsFragment = (PetDetailsFragment) fragmentManager.findFragmentById(R.id.detail_pets_fragment);
        petDetailsFragment.setPetID(message);

        handler = new Handler();

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(R.string.pet_detail_action_bar_title);
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
    }

    /**
     * Button to create/delete fragment donate information.
     */
    @OnClick(R.id.btn_help)
    public void onHelpButtonClick() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!fragmentHelpVisible) {
            HelpFragment fragmentHelp = new HelpFragment();
            fragmentTransaction.add(R.id.fragment_container, fragmentHelp, "help");
            fragmentTransaction.commit();
            scrollToDownByTreeLayout();
            fragmentHelpVisible = true;
            colorButton = R.drawable.button_orange;
            setColorButton(colorButton);
            flagIsAlreadyScrooling = false;
        } else {
            Fragment fragmentHelp = fragmentManager.findFragmentById(R.id.fragment_container);
            fragmentTransaction.remove(fragmentHelp);
            fragmentTransaction.commit();
            fragmentHelpVisible = false;
            colorButton = R.drawable.button_grey;
            setColorButton(colorButton);
        }
    }

    /**
     * method to scroll down after added new fragment.
     * it's better checking entire size ScrollView
     */
    public void scrollToDownByTreeLayout() {
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!flagIsAlreadyScrooling) {
                    handler.post(r);
                    flagIsAlreadyScrooling = !flagIsAlreadyScrooling;
                }
            }
        });
    }

    /**
     * method to change color button.
     * getDrawable require api > 21
     *
     * @param color custom button from @drawable.
     */
    public void setColorButton(final int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            buttonDonate.setBackground(getDrawable(color));
        } else {
            buttonDonate.setBackground(getResources().getDrawable(color));
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

    /**
     * Pass data from fragment details to fragment img.
     *
     * @param pet object from fragment details.
     */
    @Override
    public void passData(final Pet pet) {

        PetImgFragment petImgFragment = new PetImgFragment();

        Bundle args = new Bundle();
        args.putSerializable("pet", pet);

        petImgFragment.setArguments(args);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_img, petImgFragment, "help");
        fragmentTransaction.commit();
    }
}
