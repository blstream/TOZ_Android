package com.intive.toz.add_pet;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.intive.toz.petslist.model.Pet;

/**
 * The interface Add pet mvp.
 */
public interface AddPetMvp {
    /**
     * The interface View.
     */
    interface View extends MvpView {
        /**
         * Enable save button.
         */
        void enableSaveButton();

        /**
         * Edit texts listeners.
         */
        void editTextsListeners();

        /**
         * On success.
         */
        void onSuccess();

        /**
         * On error.
         */
        void onError();
    }

    /**
     * The interface Presenter.
     */
    interface Presenter extends MvpPresenter<View> {
        /**
         * Validate boolean.
         *
         * @param name           the name
         * @param address        the address
         * @param description    the description
         * @param selectedType   the selected type
         * @param selectedGender the selected gender
         * @return the boolean
         */
        boolean validate(String name, String address, String description, int selectedType, int selectedGender);

        /**
         * Add pet.
         *
         * @param pet the pet
         */
        void addPet(Pet pet);
    }
}
