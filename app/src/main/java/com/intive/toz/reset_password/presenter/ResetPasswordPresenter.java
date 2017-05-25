package com.intive.toz.reset_password.presenter;

import android.view.View;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;
import com.intive.toz.reset_password.ResetPasswordMvp;
import com.intive.toz.reset_password.model.Email;

import okhttp3.ResponseBody;

/**
 * The type Reset password presenter.
 */
public class ResetPasswordPresenter extends MvpBasePresenter<ResetPasswordMvp.View> implements ResetPasswordMvp.Presenter {

    @Override
    public void resetPassword(final String email) {
        if (!email.isEmpty()) {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                getView().onValidationError();
                return;
            }
        } else {
            getView().onValidationError();
            return;
        }

        getView().showProgressBar(View.VISIBLE);
        Email body = new Email(email);
        DataLoader dataLoader = new DataLoader();
        dataLoader.resetPassword(new DataProvider.ResponseCallback<ResponseBody>() {
            @Override
            public void onSuccess(final ResponseBody response) {
                getView().showProgressBar(View.GONE);
                getView().onSuccess();
            }

            @Override
            public void onError(final Throwable e) {
                getView().showProgressBar(View.GONE);
                getView().onError();
            }
        }, body);
    }
}
