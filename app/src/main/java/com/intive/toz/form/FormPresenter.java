package com.intive.toz.form;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;


/**
 * Presenter Form class.
 */
class FormPresenter extends MvpBasePresenter<FormMvp.FormView> implements FormMvp.Presenter {

    private ValidateForm validateForm = new ValidateForm();

    @Override
    public boolean validate(final String name, final String surname, final String phone, final String email) {
        boolean isCorrect = true;


        if (!validateForm.name(name)) {
            isCorrect = false;
            if (name.isEmpty()) {
                getView().setErrorName(true);
            } else {
                getView().setErrorName(false);
            }
        }
        if (!validateForm.name(surname)) {
            isCorrect = false;
            if (surname.isEmpty()) {
                getView().setErrorSurname(true);
            } else {
                getView().setErrorSurname(false);
            }
        }

        if (!validateForm.phoneNumber(phone)) {
            isCorrect = false;
            if (!phone.isEmpty()) {
                getView().setErrorPhone(false);
            } else {
                getView().setErrorPhone(true);
            }
        }

        if (!validateForm.email(email)) {
            isCorrect = false;
            if (email.isEmpty()) {
                getView().setErrorEmail(true);
            } else {
                getView().setErrorEmail(false);
            }

        }

        return isCorrect;


    }

    /**
     * Send data function.
     *
     * @param userData  is user data
     * @param volunteer is volunteer state
     */
    @Override
    public void sendData(final String[] userData, final boolean volunteer) {


        //TODO send data form
       /* Send:
        name, surname, phone, email, volunteer state, unique Id, date of form*/

        getView().showSaveInfo();
    }


}
