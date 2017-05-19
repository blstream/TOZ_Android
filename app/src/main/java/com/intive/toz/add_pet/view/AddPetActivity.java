package com.intive.toz.add_pet.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.intive.toz.R;
import com.intive.toz.add_pet.AddPetMvp;
import com.intive.toz.add_pet.model.GalleryItem;
import com.intive.toz.add_pet.model.PetSettings;
import com.intive.toz.add_pet.presenter.AddPetPresenter;
import com.intive.toz.petslist.model.Pet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * The type Add pet activity.
 */
public class AddPetActivity extends MvpActivity<AddPetMvp.View, AddPetMvp.Presenter> implements AddPetMvp.View {
    public static final int REQUEST_CODE_PICKER = 1;

    public static final int REQUEST_CODE_REMOVE = 2;

    public static final String POSITION_EXTRA = "position";

    public static final int COLS_NUM = 5;

    public static final int IMAGES_COUNT = 10;

    /**
     * The Gender spinner.
     */
    @BindView(R.id.gender_spinner)
    MaterialSpinner genderSpinner;

    /**
     * The Type spinner.
     */
    @BindView(R.id.type_spinner)
    MaterialSpinner typeSpinner;

    /**
     * The Name et.
     */
    @BindView(R.id.name_et)
    TextInputEditText nameEt;

    /**
     * The Address et.
     */
    @BindView(R.id.address_et)
    TextInputEditText addressEt;

    /**
     * The Description et.
     */
    @BindView(R.id.description_et)
    TextInputEditText descriptionEt;

    /**
     * The Add images btn.
     */
    @BindView(R.id.add_images)
    View addImagesBtn;

    /**
     * The Grid view.
     */
    @BindView(R.id.images_grid_view)
    GridView gridView;

    private Menu menu;
    private ArrayList<Image> images;
    private List<GalleryItem> items;
    private GalleryAdapter adapter;
    private boolean hasButton = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);
        ButterKnife.bind(this);

        setTitle(R.string.add_pet_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setUpDropLists();
        setUpGridView();
        editTextsListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_pet_menu, menu);
        menu.findItem(R.id.action_save).setEnabled(false);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_save:
                onSaveClick();
                break;
            default:
                break;
        }
        return true;
    }

    @NonNull
    @Override
    public AddPetMvp.Presenter createPresenter() {
        return new AddPetPresenter();
    }

    @Override
    public void enableSaveButton() {
        if (presenter.validate(nameEt.getText().toString(), addressEt.getText().toString(), descriptionEt.getText().toString(),
                typeSpinner.getSelectedItemPosition(), genderSpinner.getSelectedItemPosition())) {
            menu.findItem(R.id.action_save).setEnabled(true);
        } else {
            menu.findItem(R.id.action_save).setEnabled(false);
        }
    }

    @Override
    public void editTextsListeners() {
        nameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                enableSaveButton();
            }

            @Override
            public void afterTextChanged(final Editable s) {
            }
        });

        addressEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                enableSaveButton();
            }

            @Override
            public void afterTextChanged(final Editable s) {
            }
        });

        descriptionEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
                enableSaveButton();
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                enableSaveButton();
            }

            @Override
            public void afterTextChanged(final Editable s) {
                enableSaveButton();
            }
        });

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
                enableSaveButton();
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {

            }
        });

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
                enableSaveButton();
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, getString(R.string.add_success_message), Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onError() {
        Toast.makeText(this, getString(R.string.default_error), Toast.LENGTH_LONG).show();
    }

    /**
     * On add images click.
     */
    @OnClick(R.id.add_images)
    public void onAddImagesClick() {
        openImagePicker();
    }

    /**
     * On item click.
     *
     * @param position the position
     */
    @OnItemClick(R.id.images_grid_view)
    public void onItemClick(final int position) {
        GalleryItem item = (GalleryItem) adapter.getItem(position);
        if (item.isButton()) {
            openImagePicker();
        } else {
            Intent intent = new Intent(this, FullScreenImageActivity.class);
            intent.putExtra(ImagePicker.EXTRA_SELECTED_IMAGES, item.getImage());
            intent.putExtra(POSITION_EXTRA, position);
            startActivityForResult(intent, REQUEST_CODE_REMOVE);
        }
    }

    /**
     * On item long click boolean.
     *
     * @param position the position
     * @return the boolean
     */
    @OnItemLongClick(R.id.images_grid_view)
    public boolean onItemLongClick(final int position) {
        GalleryItem item = (GalleryItem) adapter.getItem(position);
        if (!item.isButton()) {
            removeImage(position);
        }
        return true;
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (requestCode == REQUEST_CODE_PICKER && resultCode == RESULT_OK && data != null) {
            images = data.getParcelableArrayListExtra(ImagePicker.EXTRA_SELECTED_IMAGES);

            items = new ArrayList<>();
            hasButton = false;

            for (Image i : images) {
                GalleryItem item = new GalleryItem(i, false);
                items.add(item);
            }

            if (items.size() < IMAGES_COUNT) {
                GalleryItem item = new GalleryItem(null, true);
                hasButton = true;
                items.add(0, item);
            }

            adapter.setImages(items);
            gridView.setAdapter(adapter);
            addImagesBtn.setVisibility(View.GONE);

        } else if (requestCode == REQUEST_CODE_REMOVE && resultCode == RESULT_OK && data != null) {
            int position = data.getIntExtra(POSITION_EXTRA, 0);
            removeImage(position);
        }
    }

    private void setUpDropLists() {
        String[] gender = {PetSettings.GENDER.MALE.toString(), PetSettings.GENDER.FEMALE.toString()};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gender);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        String[] type = {PetSettings.TYPES.CAT.toString(), PetSettings.TYPES.DOG.toString()};
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
    }

    private void setUpGridView() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int size = dm.widthPixels / COLS_NUM;
        images = new ArrayList<>();
        items = new ArrayList<>();
        adapter = new GalleryAdapter(this, size);
        gridView.setAdapter(adapter);
    }

    private void removeImage(final int position) {
        items.remove(position);
        if (hasButton) {
            images.remove(position - 1);
        } else {
            images.remove(position);
            GalleryItem item = new GalleryItem(null, true);
            hasButton = true;
            items.add(0, item);
        }
        adapter.setImages(items);
        gridView.setAdapter(adapter);
    }

    private void onSaveClick() {
        Pet pet = new Pet();
        pet.setName(nameEt.getText().toString());
        pet.setAddress(addressEt.getText().toString());
        pet.setDescription(descriptionEt.getText().toString());

        if (genderSpinner.getSelectedItemPosition() == 1) {
            pet.setSex(PetSettings.GENDER.MALE.name());
        } else {
            pet.setSex(PetSettings.GENDER.FEMALE.name());
        }

        if (typeSpinner.getSelectedItemPosition() == 1) {
            pet.setType(PetSettings.TYPES.CAT.name());
        } else {
            pet.setType(PetSettings.TYPES.DOG.name());
        }

        presenter.addPet(pet);
    }

    private void openImagePicker() {
        ImagePicker.create(this)
                .limit(IMAGES_COUNT)
                .theme(R.style.ImagePicker)
                .showCamera(false)
                .origin(images)
                .start(REQUEST_CODE_PICKER);
    }
}
