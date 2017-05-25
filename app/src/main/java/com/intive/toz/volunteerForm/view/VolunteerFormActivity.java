package com.intive.toz.volunteerForm.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.MenuItem;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.intive.toz.R;
import com.intive.toz.volunteerForm.VolunteerFormMvp;
import com.intive.toz.volunteerForm.model.Proposal;
import com.intive.toz.volunteerForm.presenter.VolunteerFormPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_form);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        phoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    @NonNull
    @Override
    public VolunteerFormMvp.Presenter createPresenter() {
        return new VolunteerFormPresenter();
    }


    /**
     * Validate form  when send button clicked.
     */
    @OnClick(R.id.send)
    public void tryToSend() {
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

    /**
     * Cancel when cancel button clicked.
     */
    @OnClick(R.id.cancel)
    public void cancel() {
        finish();
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
            default:
                break;
        }
        return true;
    }

}
