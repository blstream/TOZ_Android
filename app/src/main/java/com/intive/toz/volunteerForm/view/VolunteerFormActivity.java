package com.intive.toz.volunteerForm.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.intive.toz.R;
import com.intive.toz.volunteerForm.VolunteerFormMvp;
import com.intive.toz.volunteerForm.model.Proposal;
import com.intive.toz.volunteerForm.presenter.VolunteerFormPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Form Activity class.
 */
public class VolunteerFormActivity extends MvpActivity<VolunteerFormMvp.FormView, VolunteerFormMvp.Presenter> implements VolunteerFormMvp.FormView {

    @BindView(R.id.name)
    TextInputEditText name;

    @BindView(R.id.surname)
    TextInputEditText surname;

    @BindView(R.id.email)
    TextInputEditText email;

    @BindView(R.id.phoneNumber)
    TextInputEditText phoneNumber;

    @BindView(R.id.description)
    TextView descriptionTv;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_form);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        presenter.setDescriptionText();
    }

    @NonNull
    @Override
    public VolunteerFormMvp.Presenter createPresenter() {
        return new VolunteerFormPresenter();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_pet_menu, menu);
        return true;
    }


    /**
     * Validate form  when send button clicked.
     */
    public void onSaveClick() {
        Proposal proposal = new Proposal();
        proposal.setEmail(email.getText().toString());
        proposal.setName(name.getText().toString());
        proposal.setPhoneNumber(phoneNumber.getText().toString());
        proposal.setSurname(surname.getText().toString());
        ArrayList<String> roles = new ArrayList<>();
        roles.add("VOLUNTEER");
        proposal.setRoles(roles);

        if (presenter.validate(proposal.getName(), proposal.getSurname(),
                proposal.getPhoneNumber(), proposal.getEmail())) {
            presenter.sendData(proposal);
        }
    }

    @Override
    public void onDescriptionSuccess(final String description) {
        descriptionTv.setVisibility(View.VISIBLE);
        descriptionTv.setText(description);
    }

    @Override
    public void onDescriptionError() {
        descriptionTv.setVisibility(View.GONE);
    }

    @Override
    public void setErrorName(final boolean isEmpty) {
        if (isEmpty) {
            name.setError(getString(R.string.form_error_empty_field));
        } else {
            name.setError(getString(R.string.form_error_incorrect_data));
        }
    }

    @Override
    public void setErrorSurname(final boolean isEmpty) {
        if (isEmpty) {
            surname.setError(getString(R.string.form_error_empty_field));
        } else {
            surname.setError(getString(R.string.form_error_incorrect_data));
        }
    }

    @Override
    public void setErrorPhone(final boolean isEmpty) {
        if (isEmpty) {
            phoneNumber.setError(getString(R.string.form_error_empty_field));
        } else {
            phoneNumber.setError(getString(R.string.form_error_incorrect_data));
        }

    }

    @Override
    public void setErrorEmail(final boolean isEmpty) {
        if (isEmpty) {
            email.setError(getString(R.string.form_error_empty_field));
        } else {
            email.setError(getString(R.string.form_error_incorrect_data));
        }
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, R.string.form_success_massage, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onError() {
        Toast.makeText(this, R.string.default_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConflict() {
        Toast.makeText(this, R.string.form_error_conflict, Toast.LENGTH_SHORT).show();
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

}
