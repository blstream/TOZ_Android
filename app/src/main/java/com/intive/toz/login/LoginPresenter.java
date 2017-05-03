package com.intive.toz.login;


import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

/**
 * Class login presenter.
 */

class LoginPresenter extends MvpBasePresenter<LoginView> {


    private AuthValidator validator = new AuthValidator();

    /**
     * ValidateForm user login and password.
     *
     * @param login    login
     * @param password password
     */
    void validateUser(final String login, final String password) {
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
