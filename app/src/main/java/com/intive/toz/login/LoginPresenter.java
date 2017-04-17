package com.intive.toz.login;


import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

/**
 * Created by K on 2017-04-03.
 */

public class LoginPresenter extends MvpBasePresenter<LoginView> {


    AuthValidator validator = new AuthValidator();

    /**
     * Validate user login and password.
     *
     * @param login    login
     * @param password password
     */
    public void validateUser(final String login, final String password) {
        getView().hideErrorViews();
        getView().showProgress();
        if (login.isEmpty()) {
            getView().emptyLoginViewError();
        } else if (!validator.validateLogin(login)) {
            getView().showLoginError();
        }
        if (password.isEmpty()) {
            getView().emptyPasswordViewError();
        } else if (!validator.validatePassword(password)) {
            getView().showPasswordError();
        }
        if (validator.isAllValid()) {
            //successful validation
            Session.logIn();
            getView().onLoginSuccessful();
        }
        getView().hideProgress();

    }
}
