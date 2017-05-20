package com.intive.toz.petDetails.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;
import com.intive.toz.petDetails.view.PetImgView;
import com.intive.toz.petslist.model.Pet;

/**
 * Presenter for Pet Details.
 */

public class PetImgPresenter extends MvpBasePresenter<PetImgView> {

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
