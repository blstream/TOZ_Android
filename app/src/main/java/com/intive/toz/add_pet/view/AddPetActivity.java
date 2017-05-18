package com.intive.toz.add_pet.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.intive.toz.R;
import com.intive.toz.add_pet.AddPetMvp;
import com.intive.toz.add_pet.model.PetSettings;
import com.intive.toz.add_pet.presenter.AddPetPresenter;
import com.intive.toz.petslist.model.Pet;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * The type Add pet activity.
 */
public class AddPetActivity extends MvpActivity<AddPetMvp.View, AddPetMvp.Presenter> implements AddPetMvp.View {

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

    private Menu menu;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);
        ButterKnife.bind(this);

        setTitle(R.string.add_pet_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setupDropLists();
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

    private void setupDropLists() {
        String[] gender = {PetSettings.GENDER.MALE.toString(), PetSettings.GENDER.FEMALE.toString()};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gender);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        String[] type = {PetSettings.TYPES.CAT.toString(), PetSettings.TYPES.DOG.toString()};
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
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
}
