package com.intive.toz.petDetails.view_pager;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.intive.toz.petslist.model.Pet;

/**
 *  pets list interface used to communicate with pets list fragment and presenter.
 */

interface PetDetailsImageView extends MvpView {

    /**
     * Show pet details.
     * @param pet pet object
     */
    void showPetDetails(final Pet pet);

    void setPetInAdapter(Pet pet);

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
     * @param e error
     */
    void showError(Throwable e);
}
