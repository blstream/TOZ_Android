package com.intive.toz.add_pet;

import com.esafirm.imagepicker.model.Image;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.intive.toz.petslist.model.Pet;

import java.util.List;

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

        /**
         * Show progress bar.
         *
         * @param visibility the visibility
         */
        void showProgressBar(int visibility);
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
         * @param pet    the pet
         * @param images the images
         */
        void addPet(Pet pet, List<Image> images);

        /**
         * Upload images.
         *
         * @param id the id
         */
        void uploadImages(String id);
    }
}
