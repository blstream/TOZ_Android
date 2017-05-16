package com.intive.toz.login;


import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;
import com.intive.toz.login.model.Login;
import com.intive.toz.login.model.User;

/**
 * class to get input data from screen and send them to server.
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
            Login loginObj = new Login();
            loginObj.setEmail(login);
            loginObj.setPassword(password);
            validateFromServer(loginObj);
        }
        getView().hideProgress();
    }

    /**
     * method to checking response from server according to sending login and password.
     *
     * @param loginObj object which contain email and password from input screen.
     */
    private void validateFromServer(final Login loginObj) {
        DataLoader dataLoader = new DataLoader();

        dataLoader.fetchResponseLogin(new DataProvider.ResponseLoginCallback<User>() {
            @Override
            public void onSuccess(final User response) {
                if (isViewAttached()) {
                    Session.logIn(response.getJwt(), response.getUserId(), response.getRoles().get(0), response.getExpirationDateSeconds());
                    Session.storeCredentials(loginObj.getEmail(), loginObj.getPassword());
                    getView().onLoginSuccessful();

                }
            }

            @Override
            public void onError(final Throwable e) {
                if (isViewAttached()) {
                    getView().showError();
                }
            }

            @Override
            public void onErrorCode(final int code) {
                getView().showErrorGeneral(code);
            }

            @Override
            public void onErrorPassword() {
                getView().showPasswordError();
            }

            @Override
            public void onErrorLogin() {
                getView().showLoginError();
            }
        }, loginObj);
    }
}
