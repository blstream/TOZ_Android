package com.intive.toz.petDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.intive.toz.MainActivity;
import com.intive.toz.R;
import com.intive.toz.login.LoginActivity;
import com.intive.toz.login.Session;
import com.intive.toz.petDetails.details_fragment.PetDetailsFragment;
import com.intive.toz.petDetails.page_view_images.SwipeAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *Activity containing fragment with pet details object.
 *
 **/

public class PetDetailsActivity extends AppCompatActivity {

    @BindView(R.id.view_pager_images_gallery)
    ViewPager viewPager;

    @BindView(R.id.right_nav)
    ImageButton imageButtonRight;

    @BindView(R.id.left_nav)
    ImageButton imageButtonLeft;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        ButterKnife.bind(this);

        String message = getIntent().getStringExtra("petKey");
        PetDetailsFragment petDetailsFragment = (PetDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detail_pets_fragment);
        petDetailsFragment.setPetID(message);

        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        swipeAdapter.setPetId(message);

        viewPager.setAdapter(swipeAdapter);

        imageButtonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        });

        imageButtonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
            }
        });

        String messageName = getIntent().getStringExtra("petName");

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(messageName);
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
