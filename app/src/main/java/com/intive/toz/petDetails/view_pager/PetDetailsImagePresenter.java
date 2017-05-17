package com.intive.toz.petDetails.view_pager;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;
import com.intive.toz.petslist.model.Pet;

/**
 * Presenter for Pet Details.
 */

class PetDetailsImagePresenter extends MvpBasePresenter<PetDetailsImageView> {

    /**
     *  Load pet details.
     * @param petID pet id
     */
    void loadPetsDetails(final String petID) {
        getView().showProgress();
        DataLoader dataLoader = new DataLoader();

        dataLoader.fetchPetDetails(petID, new DataProvider.ResponseCallback<Pet>() {
            @Override
            public void onSuccess(final Pet pet) {
                if (isViewAttached()) {
                    getView().hideProgress();
                    getView().showPetDetails(pet);
                    getView().setPetInAdapter(pet);
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
