package com.intive.toz.volunteerForm.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;
import com.intive.toz.volunteerForm.ValidateVolunteerForm;
import com.intive.toz.volunteerForm.VolunteerFormMvp;
import com.intive.toz.volunteerForm.model.BecomeVolunteerInfo;
import com.intive.toz.volunteerForm.model.Proposal;


/**
 * Presenter Form class.
 */
public class VolunteerFormPresenter extends MvpBasePresenter<VolunteerFormMvp.FormView> implements VolunteerFormMvp.Presenter {

    private ValidateVolunteerForm validateForm = new ValidateVolunteerForm();

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


    @Override
    public void sendData(final Proposal proposal) {
        final DataLoader dataLoader = new DataLoader();
        dataLoader.proposal(new DataProvider.ResponseCallback<Integer>() {
            @Override
            public void onSuccess(final Integer response) {
                if (response >= DataLoader.SUCCESS_CODE_START && response < DataLoader.SUCCESS_CODE_END) {
                    getView().onSuccess();
                } else if (response == DataLoader.ERROR_CODE_CONFLICT) {
                    getView().onConflict();
                } else {
                    getView().onError();
                }
            }

            @Override
            public void onError(final Throwable e) {
                getView().onError();
            }
        }, proposal);
    }

    @Override
    public void setDescriptionText() {
        final DataLoader dataLoader = new DataLoader();
        dataLoader.becomeVolunteer(new DataProvider.ResponseCallback<BecomeVolunteerInfo>() {
            @Override
            public void onSuccess(final BecomeVolunteerInfo response) {
                getView().onDescriptionSuccess(response.getHowToHelpDescription());
            }

            @Override
            public void onError(final Throwable e) {
                getView().onDescriptionError();
            }
        });
    }


}
