package com.intive.toz.petDetails.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.intive.toz.petslist.model.Pet;

/**
 *  pets list interface used to communicate with pets list fragment and presenter.
 */

public interface PetImgView extends MvpView {

    /**
     * Send Pet data to adapter.
     *
     * @param pet object which contain url images.
     */
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
