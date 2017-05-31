package com.intive.toz.add_pet.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.intive.toz.R;
import com.intive.toz.petDetails.view_pager.PetImgViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * The type Full screen image activity.
 */
public class FullScreenImageActivity extends AppCompatActivity {

    /**
     * The Image view.
     */
    @BindView(R.id.image)
    ImageView imageView;

    private int position;
    private boolean showMenu = true;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        ButterKnife.bind(this);

        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Image image = getIntent().getParcelableExtra(ImagePicker.EXTRA_SELECTED_IMAGES);
        position = getIntent().getIntExtra(AddPetActivity.POSITION_EXTRA, 0);
        if (image == null) {
            showMenu = false;
            String url = getIntent().getStringExtra(PetImgViewPagerAdapter.IMG_URL);
            Glide.with(this)
                    .load(url)
                    .fitCenter()
                    .into(imageView);
        } else {
            Glide.with(this)
                    .load(image.getPath())
                    .fitCenter()
                    .into(imageView);
        }
    }

    private void onRemoveImage() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(AddPetActivity.POSITION_EXTRA, position);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        if (showMenu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.remove_image_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_remove:
                onRemoveImage();
                break;
            default:
                break;
        }
        return true;
    }
}
