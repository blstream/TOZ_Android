package com.intive.toz.petDetails.page_view_fragments;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.Pet;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;
import com.intive.toz.data.DateFormatter;
import com.intive.toz.petDetails.details_fragment.PetDetailsView;

/**
 * Presenter for Pet Details.
 */

public class PetDetailsImagePresenter extends MvpBasePresenter<PetDetailsView> {

    DateFormatter dateFormatter = new DateFormatter();

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
                    getView().showPetDetails(pet, dateFormatter.convertToDate(pet.getCreated()));
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
