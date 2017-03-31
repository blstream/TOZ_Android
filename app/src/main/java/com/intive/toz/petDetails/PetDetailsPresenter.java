package com.intive.toz.petDetails;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.Pet;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Presenter for Pet Details.
 */

public class PetDetailsPresenter extends MvpBasePresenter<PetDetailsView> {

    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private Calendar calendar = Calendar.getInstance();

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
                    getView().showPetDetails(pet, convertToDate(pet.getCreated()));

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

    /**
     * Convert date.
     * @param dateInMilliseconds date in miliseconds
     * @return date in dd/MM/yyyy format
     */
    public String convertToDate(final long dateInMilliseconds) {
        calendar.setTimeInMillis(dateInMilliseconds);
        return formatter.format(calendar.getTime());
    }
}
