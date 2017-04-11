package com.intive.toz.petDetails.page_view_images;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.Pet;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;

/**
 * Presenter for Pet Details.
 */

public class PetDetailsImagePresenter extends MvpBasePresenter<PetDetailsImageView> {

    /**
     *  Load pet details.
     * @param petID pet id
     */
    public void loadPetsDetails(final String petID) {
        getView().showProgress();
        DataLoader dataLoader = new DataLoader();

        dataLoader.fetchPetDetails(petID, new DataProvider.ResponseCallback<Pet>() {
            @Override
            public void onSuccess(final Pet pet) {
                if (isViewAttached()) {
                    getView().hideProgress();
                    getView().showPetDetails(pet);
                }
            }

            @Override
            public void onError(final Throwable e) {
                if (isViewAttached()) {
                    getView().showError(e);
                }
            }
        });
    }
}
