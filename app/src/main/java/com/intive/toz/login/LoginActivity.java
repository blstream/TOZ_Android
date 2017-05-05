package com.intive.toz.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.intive.toz.MainActivity;
import com.intive.toz.R;
import com.intive.toz.volunteerForm.VolunteerFormActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * LoginActivity.
 */
public class LoginActivity extends MvpActivity<LoginView, LoginPresenter> implements LoginView {

    @BindView(R.id.input_username)
    TextInputEditText usernameInput;

    @BindView(R.id.input_password)
    TextInputEditText passwordInput;

    @BindView(R.id.auth_username_error)
    TextView authErrorViewUsername;

    @BindView(R.id.auth_username_icon_error)
    ImageView authIconUsername;

    @BindView(R.id.auth_password_error)
    TextView authErrorViewPassword;

    @BindView(R.id.auth_password_icon_error)
    ImageView authIconPassword;

    @BindView(R.id.button_login)
    Button loginButton;

    @BindView(R.id.button_bypass_login)
    Button bypassLoginButton;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.button_form_info)
    Button formInfo;

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
        authErrorViewPassword.setVisibility(View.VISIBLE);
        authIconPassword.setVisibility(View.VISIBLE);
        authErrorViewPassword.setText(R.string.auth_empty_error);
    }

    @Override
    public void emptyLoginViewError() {
        authErrorViewUsername.setVisibility(View.VISIBLE);
        authIconUsername.setVisibility(View.VISIBLE);
        authErrorViewUsername.setText(R.string.auth_empty_error);
    }

    @Override
    public void showLoginError() {
        authErrorViewUsername.setVisibility(View.VISIBLE);
        authErrorViewUsername.setText(R.string.auth_username_error);
        authIconUsername.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPasswordError() {
        authErrorViewPassword.setVisibility(View.VISIBLE);
        authErrorViewPassword.setText(R.string.auth_password_error);
        authIconPassword.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorViews() {
        authErrorViewUsername.setVisibility(View.INVISIBLE);
        authIconUsername.setVisibility(View.INVISIBLE);
        authErrorViewPassword.setVisibility(View.INVISIBLE);
        authIconPassword.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoginSuccessful() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Temporary button to avoid typing login and password.
     */
    @OnClick(R.id.button_bypass_login)
    public void onBypassLoginButtonClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Start form information activity.
     */
    @OnClick(R.id.button_form_info)
    public void onFormInfoActivity() {
        Intent intent = new Intent(this, VolunteerFormActivity.class);
        startActivity(intent);
    }


}
