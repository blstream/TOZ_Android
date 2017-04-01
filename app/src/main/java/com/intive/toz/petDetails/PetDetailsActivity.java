package com.intive.toz.petDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.intive.toz.R;

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

    }
}
