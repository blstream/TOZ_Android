package com.intive.toz.petDetails.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.intive.toz.R;
import com.intive.toz.petslist.model.Pet;

import butterknife.ButterKnife;

/**
 * Activity containing fragment with pet details object.
 **/

public class PetDetailsActivity extends AppCompatActivity implements PetDetailsFragment.DataPassListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        fragmentManager = getSupportFragmentManager();

        String message = getIntent().getStringExtra("petKey");
        PetDetailsFragment petDetailsFragment = (PetDetailsFragment) fragmentManager.findFragmentById(R.id.detail_pets_fragment);
        petDetailsFragment.setPetID(message);

        getSupportActionBar().setTitle(R.string.pet_detail_action_bar_title);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
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
