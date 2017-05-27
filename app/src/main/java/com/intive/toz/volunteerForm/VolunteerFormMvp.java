package com.intive.toz.volunteerForm;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.intive.toz.volunteerForm.model.Proposal;


/**
 * Interface for Form MVP.
 */
public interface VolunteerFormMvp {

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
         *
         * @param proposal the proposal
         */
        void sendData(Proposal proposal);

        /**
         * Sets description text.
         */
        void setDescriptionText();
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
        void onSuccess();

        /**
         * On error.
         */
        void onError();

        /**
         * On conflict.
         */
        void onConflict();

        /**
         * On descripion success.
         */
        void onDescriptionSuccess(String description);

        /**
         * On descripion error.
         */
        void onDescriptionError();
    }

}
