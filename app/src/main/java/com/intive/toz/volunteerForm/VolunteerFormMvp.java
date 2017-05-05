package com.intive.toz.volunteerForm;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;


/**
 * Interface for Form MVP.
 */
interface VolunteerFormMvp {

    /**
     * Interface for presenter.
     */
    interface Presenter extends MvpPresenter<FormView> {

        /**
         * Validate user input.
         *
         * @param name    is name
         * @param surname is surname
         * @param phone   is phone
         * @param email   is email
         * @return state of validate
         */
        boolean validate(String name, String surname, String phone, String email);

        /**
         * Send data.
         * @param userData is user data
         * @param volunteer is volunteer state
         */
        void sendData(String[] userData, boolean volunteer);
    }

    /**
     * Interface for View.
     */
    interface FormView extends MvpView {


        /**
         * Set error name.
         *
         * @param isEmpty is empty
         */
        void setErrorName(boolean isEmpty);

        /**
         * Set error surname.
         *
         * @param isEmpty is empty
         */
        void setErrorSurname(boolean isEmpty);

        /**
         * Set error phone.
         *
         * @param isEmpty is empty
         */
        void setErrorPhone(boolean isEmpty);

        /**
         * Set error email.
         *
         * @param isEmpty is empty.
         */
        void setErrorEmail(boolean isEmpty);

        /**
         * show saved info.
         */
        void showSaveInfo();

        /**
         * calncel form.
         */
        void cancel();
    }

}
