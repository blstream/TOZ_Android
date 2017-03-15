package com.intive.toz.homescreen.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.intive.toz.R;

/**
 *  Temporary activity just to hold PetListFragment.
 */
public class PetsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets_list);

        if (findViewById(R.id.fragment_container_pets) != null) {
            if (savedInstanceState != null) {
                return;
            }
            PetsListFragment fragmentPetsList = new PetsListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_pets, fragmentPetsList).commit();
        }
    }
}
