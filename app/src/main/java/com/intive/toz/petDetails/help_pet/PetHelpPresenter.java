package com.intive.toz.petDetails.help_pet;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

/**
 * Presenter for Pet Details.
 */

class PetHelpPresenter extends MvpBasePresenter<PetHelpView> {

    /**
     *  Load pet details.
     * @param petID pet id
     */
//    void loadFinancialDetails() {
//        getView().showProgress();
//        DataLoader dataLoader = new DataLoader();
//
//        dataLoader.fetchPetDetails(petID, new DataProvider.ResponseCallback<Pet>() {
//            @Override
//            public void onSuccess(final Pet pet) {
//                if (isViewAttached()) {
//                    getView().hideProgress();
//                    getView().showPetDetails();
//                }
//            }
//
//            @Override
//            public void onError(final Throwable e) {
//                if (isViewAttached()) {
//                    getView().showError(e);
//                }
//            }
//        });
//    }
}
