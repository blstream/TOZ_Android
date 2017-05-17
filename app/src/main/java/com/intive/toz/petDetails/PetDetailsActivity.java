package com.intive.toz.petDetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.intive.toz.R;
import com.intive.toz.petDetails.details_fragment.PetDetailsFragment;
import com.intive.toz.petDetails.view_pager.PetImgFragment;

/**
 *Activity containing fragment with pet details object.
 *
 **/

public class PetDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        String message = getIntent().getStringExtra("petKey");
        PetDetailsFragment petDetailsFragment = (PetDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detail_pets_fragment);
        petDetailsFragment.setPetID(message);

        PetImgFragment petImgFragment = (PetImgFragment) getSupportFragmentManager().findFragmentById(R.id.detail_pets_images);
        petImgFragment.setPetID(message);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(R.string.pet_detail_action_bar_title);
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
