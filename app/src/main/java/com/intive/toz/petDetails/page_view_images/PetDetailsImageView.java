package com.intive.toz.petDetails.page_view_images;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.intive.toz.Pet;

/**
 *  pets list interface used to communicate with pets list fragment and presenter.
 */

public interface PetDetailsImageView extends MvpView {

    /**
     * Show pet details.
     * @param pet pet object
     * @param petCreatedDate pet created date
     */
    void showPetDetails(final Pet pet);

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
