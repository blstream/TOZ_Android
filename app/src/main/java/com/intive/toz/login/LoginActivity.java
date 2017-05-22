package com.intive.toz.login;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;

import android.os.Bundle;

import android.support.design.widget.Snackbar;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.intive.toz.MainActivity;
import com.intive.toz.R;

import com.intive.toz.volunteerForm.VolunteerFormActivity;
import com.intive.toz.common.view.calendar.SnackbarFactory;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * LoginActivity.
 */
public class LoginActivity extends MvpActivity<LoginView, LoginPresenter> implements LoginView {


    @BindView(R.id.input_username_layout)
    TextInputLayout usernameInputLayout;

    @BindView(R.id.input_password_layout)
    TextInputLayout passwordInputLayout;

    @BindView(R.id.input_username)
    TextInputEditText usernameInput;

    @BindView(R.id.input_password)
    TextInputEditText passwordInput;

    @BindView(R.id.layout_login)
    LinearLayout layoutLogin;

    @BindView(R.id.button_login)
    Button loginButton;

    @BindView(R.id.progress_bar_login)
    ProgressBar progressBar;

    private Snackbar snackbar;

    /**
     * Create LoginPresenter.
     *
     * @return presenter
     */
    @NonNull
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Session.isLogged()) {
            onLoginSuccessful();
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    /**
     * ValidateForm user when login button clicked.
     */
    @OnClick(R.id.button_login)
    public void onLoginButtonClick() {
        String login = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        presenter.validateUser(login, password);

    }

    @Override
    public void emptyPasswordViewError() {
        passwordInputLayout.setError(getString(R.string.auth_empty_error));
    }

    @Override
    public void emptyLoginViewError() {
        usernameInputLayout.setError(getString(R.string.auth_empty_error));
    }

    @Override
    public void showLoginError() {
        usernameInputLayout.setError(getString(R.string.auth_username_error));
    }

    @Override
    public void showPasswordError() {
        passwordInputLayout.setError(getString(R.string.auth_password_error));
    }

    @Override
    public void hideErrorViews() {
        usernameInputLayout.setError(null);
        passwordInputLayout.setError(null);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showAllViews() {
        layoutLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAllViews() {
        layoutLogin.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoginSuccessful() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /**
     *  Snackbar to show forbidden and other error code in successfull response.
     */
    @Override
    public void showErrorGeneral(final int codeString) {
        snackbar = SnackbarFactory.getSnackbar(this, getString(codeString));
        snackbar.show();
    }


    /**
     * Snackbar to show failure request from server.
     */
    @Override
    public void showError() {
        snackbar = SnackbarFactory.getSnackbar(this, getString(R.string.auth_error_response));
        snackbar.show();
    }
}
