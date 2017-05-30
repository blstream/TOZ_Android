package com.intive.toz.petDetails.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.intive.toz.info.model.Help;
import com.intive.toz.info.model.Info;
import com.intive.toz.petslist.model.Pet;

/**
 * pets list interface used to communicate with pets list fragment and presenter.
 */

public interface PetDetailsView extends MvpView {

    /**
     * Show pet details.
     *
     * @param pet            pet object
     * @param petCreatedDate pet created date
     */
    void showPetDetails(final Pet pet, String petCreatedDate);

    /**
     * Send Pet data from fragment through activity to img fragment.
     *
     * @param pet data.
     */
    void sendPetToFragmentImg(final Pet pet);

    /**
     * Show progress bar.
     */
    void showProgress();

    /**
     * Hide progress bar.
     */
    void hideProgress();

    /**
     * Show error message.
     *
     * @param e error
     */
    void showError(Throwable e);

    /**
     * first part financial information how to donate particular pet.
     *
     * @param financial information from organizaton/info.
     */
    void setFinancialData(Info financial);

    /**
     * second part information how to donate particular pet.
     *
     * @param donate information have been included while create pet in form from database.
     */
    void setDonateInfo(Help donate);

    /**
     * Show progress bar.
     */
    void showProgressHelp();

    /**
     * Hide progress bar.
     */
    void hideProgressHelp();
}
