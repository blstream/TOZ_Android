package com.intive.toz.volunteerForm;

import android.app.AlertDialog;
import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.NonNull;

import android.support.design.widget.TextInputEditText;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.intive.toz.R;


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
    EditText email;

    @BindView(R.id.phoneNumber)
    TextInputEditText phoneNumber;


    @BindView(R.id.radioVolunteer)
    RadioButton volunteer;

    @BindView(R.id.cancel)
    Button cancel;

    @BindView(R.id.send)
    Button send;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_form);
        ButterKnife.bind(this);
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
        String[] userData = {
                name.getText().toString(),
                surname.getText().toString(),
                phoneNumber.getText().toString(),
                email.getText().toString()
        };

        boolean isCorrect = presenter.validate(
                userData[0],
                userData[1],
                userData[2],
                userData[userData.length - 1]);

        if (isCorrect) {
            presenter.sendData(userData, volunteer.isChecked());

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
    public void showSaveInfo() {
        final int delayTime = 2000;
        AlertDialog.Builder builder = new AlertDialog.Builder(VolunteerFormActivity.this);
        builder.setMessage("Zgłoszenie zostało wysłane")
                .setCancelable(false);
        final AlertDialog alert = builder.create();
        alert.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                alert.dismiss();
                finish();
            }
        }, delayTime);

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


}
