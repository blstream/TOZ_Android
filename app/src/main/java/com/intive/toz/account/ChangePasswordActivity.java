package com.intive.toz.account;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.intive.toz.MainActivity;
import com.intive.toz.R;
import com.intive.toz.common.view.calendar.SnackbarFactory;
import com.intive.toz.login.LoginActivity;
import com.intive.toz.login.Session;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Acitivity for changing password formular.
 */
public class ChangePasswordActivity extends MvpActivity<ChangePasswordMVP.ChangePasswordView,
        ChangePasswordPresenter> implements ChangePasswordMVP.ChangePasswordView {

    @BindView(R.id.input_old_password_layout)
    TextInputLayout oldPasswordLayout;

    @BindView(R.id.input_new_password_layout)
    TextInputLayout newPasswordLayout;

    @BindView(R.id.input_repeat_new_password_layout)
    TextInputLayout repeatedNewPasswordLayout;

    @BindView(R.id.input_old_password)
    TextInputEditText oldPassword;

    @BindView(R.id.input_new_password)
    TextInputEditText newPassword;

    @BindView(R.id.input_repeat_new_password)
    TextInputEditText repeatedNewPassword;

    @BindView(R.id.confirm_change_password)
    Button changePassword;

    @BindView(R.id.cancel_change_password)
    Button cancelButton;

    private Snackbar snackbar;

    @NonNull
    @Override
    public ChangePasswordPresenter createPresenter() {
        return new ChangePasswordPresenter();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);
    }

    /**
     * Validate input passwords on button clicked.
     */
    @OnClick(R.id.confirm_change_password)
    public void onChangePasswordClicked() {
        presenter.validatePassword(oldPassword.getText().toString(),
                                    newPassword.getText().toString(),
                                    repeatedNewPassword.getText().toString());
    }

    /**
     * Go back to account tab when cancel button clicked.
     */
    @OnClick(R.id.cancel_change_password)
    public void onCancelButtonClicked() {
        finish();
    }

    @Override
    public void showEmptyOldPasswordError() {
        oldPasswordLayout.setError(getString(R.string.empty_field_error));
    }

    @Override
    public void showEmptyNewPasswordError() {
        newPasswordLayout.setError(getString(R.string.empty_field_error));
    }

    @Override
    public void showEmptyRepeatedNewPasswordError() {
        repeatedNewPasswordLayout.setError(getString(R.string.empty_field_error));
    }

    @Override
    public void showDifferentNewPasswordsError() {
        newPasswordLayout.setError(getString(R.string.different_new_passwords_error));
        repeatedNewPasswordLayout.setError(getString(R.string.different_new_passwords_error));
    }

    @Override
    public void showWrongOldPasswordError() {
        oldPasswordLayout.setError(getString(R.string.wrong_password_error));
    }

    @Override
    public void showWrongNewPasswordError() {
        newPasswordLayout.setError(getString(R.string.wrong_password_error));
    }

    @Override
    public void showTooLongPasswordError() {
        newPasswordLayout.setError(getString(R.string.too_long_password_error));
    }

    @Override
    public void hideOldPasswordError() {
        oldPasswordLayout.setError(null);
    }

    @Override
    public void hideNewPasswordError() {
        newPasswordLayout.setError(null);
    }

    @Override
    public void hideRepeatedNewPasswordError() {
        repeatedNewPasswordLayout.setError(null);
    }

    @Override
    public void hideDifferentNewPasswordsError() {
        newPasswordLayout.setError(null);
        repeatedNewPasswordLayout.setError(null);
    }

    @Override
    public void showError() {
        snackbar = SnackbarFactory.getSnackbar(this, getString(R.string.error_response));
        snackbar.show();
    }

    @Override
    public void showErrorCode(final int errorMessage) {
        snackbar = SnackbarFactory.getSnackbar(this, getString(errorMessage));
        snackbar.show();
    }

    @Override
    public void showSuccessfulPasswordChange() {
        snackbar = SnackbarFactory.getSnackbar(this, getString(R.string.successful_password_change));
        snackbar.show();
        oldPassword.getText().clear();
        newPassword.getText().clear();
        repeatedNewPassword.getText().clear();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.menu_log_in:
                Intent logIn = new Intent(this, LoginActivity.class);
                startActivity(logIn);
                return true;
            case R.id.menu_log_out:
                Session.logOut();
                Intent logOut = new Intent(this, MainActivity.class);
                logOut.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logOut);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        if (Session.isLogged()) {
            menu.findItem(R.id.menu_log_in).setVisible(false);
            menu.findItem(R.id.menu_log_out).setVisible(true);
        } else {
            menu.findItem(R.id.menu_log_in).setVisible(true);
            menu.findItem(R.id.menu_log_out).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }
}
