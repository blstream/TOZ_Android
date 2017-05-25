package com.intive.toz.reset_password.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.intive.toz.R;
import com.intive.toz.reset_password.ResetPasswordMvp;
import com.intive.toz.reset_password.presenter.ResetPasswordPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * The type Reset password activity.
 */
public class ResetPasswordActivity extends MvpActivity<ResetPasswordMvp.View, ResetPasswordMvp.Presenter> implements ResetPasswordMvp.View {

    /**
     * The Username.
     */
    @BindView(R.id.input_username)
    TextInputEditText username;

    /**
     * The Username til.
     */
    @BindView(R.id.input_username_layout)
    TextInputLayout usernameTil;

    /**
     * The Progress bar.
     */
    @BindView(R.id.progress_bar)
    View progressBar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @NonNull
    @Override
    public ResetPasswordMvp.Presenter createPresenter() {
        return new ResetPasswordPresenter();
    }

    /**
     * On cancel click.
     */
    @OnClick(R.id.button_cancel)
    public void onCancelClick() {
        onBackPressed();
    }

    /**
     * On reset password click.
     */
    @OnClick(R.id.button_ok)
    public void onResetPasswordClick() {
        usernameTil.setError(null);
        presenter.resetPassword(username.getText().toString());
    }


    @Override
    public void onSuccess() {
        Toast.makeText(this, R.string.on_reset_success, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onError() {
        Toast.makeText(this, R.string.default_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationError() {
        usernameTil.setError(getString(R.string.auth_username_error));
    }

    @Override
    public void showProgressBar(final int show) {
        progressBar.setVisibility(show);
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
