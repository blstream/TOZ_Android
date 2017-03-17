package com.intive.toz.mock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.intive.toz.Pet;
import com.intive.toz.R;
import com.intive.toz.news.model.News;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FIXME Change to MVP.
 * News screen.
 */
public class MockActivity extends AppCompatActivity {

    public static final String NEWS = "news";
    public static final String PET = "pet";

    private News news;
    private Pet pet;

    /**
     * The Title tv.
     */
    @BindView(R.id.title_tv)
    TextView titleTv;

    /**
     * The Description tv.
     */
    @BindView(R.id.description_tv)
    TextView descriptionTv;

    /**
     * The Date tv.
     */
    @BindView(R.id.date_tv)
    TextView dateTv;

    /**
     * The News iv.
     */
    @BindView(R.id.news_iv)
    ImageView newsIv;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        news = (News) bundle.getSerializable(NEWS);
        pet = (Pet) bundle.getSerializable(PET);
        if (news != null) {
            setNewsFields();
        } else {
            setPetFields();
        }
    }

    private void setNewsFields() {
        titleTv.setText(news.getTitle());
        descriptionTv.setText(news.getDescription());
        dateTv.setText(news.getDate());
        Glide.with(this)
                .load(news.getImage())
                .centerCrop()
                .placeholder(R.color.colorAccent)
                .error(R.color.colorAccent)
                .into(newsIv);
    }

    private void setPetFields() {
        titleTv.setText(pet.getName());
        descriptionTv.setText(pet.getDescription());
        dateTv.setText(pet.getDate());
        Glide.with(this)
                .load(pet.getProfilePic())
                .centerCrop()
                .placeholder(R.color.colorAccent)
                .error(R.color.colorAccent)
                .into(newsIv);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
