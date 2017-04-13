package com.intive.toz.petDetails;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.intive.toz.R;
import com.intive.toz.petDetails.details_fragment.PetDetailsFragment;
import com.intive.toz.petDetails.page_view_images.SwipeAdapter;

/**
 *Activity containing fragment with pet details object.
 *
 **/

public class PetDetailsActivity extends AppCompatActivity {

    ViewPager viewPager;
    ImageButton imageButtonRight;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        String message = getIntent().getStringExtra("petKey");
        PetDetailsFragment petDetailsFragment = (PetDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detail_pets_fragment);
        petDetailsFragment.setPetID(message);

        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        swipeAdapter.setAnimalId(message);

        viewPager = (ViewPager) findViewById(R.id.view_pager_images_gallery); // nie ten
        viewPager.setAdapter(swipeAdapter);

        imageButtonRight = (ImageButton) findViewById(R.id.right_nav);
        imageButtonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        });

        imageButtonRight = (ImageButton) findViewById(R.id.left_nav);
        imageButtonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
            }
        });

        String messageName = getIntent().getStringExtra("petName");

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle(messageName);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
