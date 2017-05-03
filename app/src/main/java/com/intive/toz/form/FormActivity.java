package com.intive.toz.form;

import android.app.AlertDialog;
import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.NonNull;

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
public class FormActivity extends MvpActivity<FormMvp.FormView, FormMvp.Presenter> implements FormMvp.FormView {


    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.surname)
    EditText surname;

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.phoneNumber)
    EditText phoneNumber;


    @BindView(R.id.radioVolunteer)
    RadioButton volunteer;

    @BindView(R.id.cancel)
    Button cancel;

    @BindView(R.id.send)
    Button send;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    public FormMvp.Presenter createPresenter() {
        return new FormPresenter();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
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
            name.setError("Pole wymagane");
        } else {
            name.setError("Niepoprawne dane");
        }
    }

    @Override
    public void setErrorSurname(final boolean isEmpty) {
        if (isEmpty) {
            surname.setError("Pole wymagane");
        } else {
            surname.setError("Niepoprawne dane");
        }
    }

    @Override
    public void setErrorPhone(final boolean isEmpty) {
        if (isEmpty) {
            phoneNumber.setError("Pole wymagane");
        } else {
            phoneNumber.setError("Niepoprawne dane");
        }

    }

    @Override
    public void setErrorEmail(final boolean isEmpty) {
        if (isEmpty) {
            email.setError("Pole wymagane");
        } else {
            email.setError("Niepoprawne dane");
        }
    }


}
