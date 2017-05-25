package com.intive.toz.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.intive.toz.MainActivity;
import com.intive.toz.R;

import com.intive.toz.volunteerForm.view.VolunteerFormActivity;
import com.intive.toz.common.view.calendar.SnackbarFactory;
import com.intive.toz.reset_password.view.ResetPasswordActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * LoginActivity.
 */
public class LoginActivity extends MvpActivity<LoginView, LoginPresenter> implements LoginView {


    /**
     * The Username input layout.
     */
    @BindView(R.id.input_username_layout)
    TextInputLayout usernameInputLayout;

    /**
     * The Password input layout.
     */
    @BindView(R.id.input_password_layout)
    TextInputLayout passwordInputLayout;

    /**
     * The Username input.
     */
    @BindView(R.id.input_username)
    TextInputEditText usernameInput;

    /**
     * The Password input.
     */
    @BindView(R.id.input_password)
    TextInputEditText passwordInput;

    /**
     * The Layout login.
     */
    @BindView(R.id.layout_login)
    LinearLayout layoutLogin;

    /**
     * The Login button.
     */
    @BindView(R.id.button_login)
    Button loginButton;

    /**
     * The Progress bar.
     */
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
        setContentView(R.layout.activity_login);
        if (Session.isLogged()) {
            onLoginSuccessful();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

    /**
     * On reset password click.
     */
    @OnClick(R.id.forgot_password)
    public void onResetPasswordClick() {
        Intent intent = new Intent(this, ResetPasswordActivity.class);
        startActivity(intent);
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
