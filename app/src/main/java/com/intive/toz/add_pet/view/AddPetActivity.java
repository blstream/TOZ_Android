package com.intive.toz.add_pet.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.intive.toz.R;
import com.intive.toz.add_pet.AddPetMvp;
import com.intive.toz.add_pet.presenter.AddPetPresenter;

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

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);
        ButterKnife.bind(this);
        setupDropLists();
    }

    private void setupDropLists() {
        String[] gender = {"Samiec", "Samica"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gender);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        String[] type = {"Kot", "Pies", "Inny"};
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_pet_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
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
}
