package com.intive.toz.petDetails.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.petDetails.view.PetImgView;
import com.intive.toz.petslist.model.Pet;

/**
 * Presenter for Pet Details.
 */

public class PetImgPresenter extends MvpBasePresenter<PetImgView> {

    /**
     *  Load pet details.
     *
     * @param pet pet object
     */
    public void loadPetData(final Pet pet) {
        getView().setPetInAdapter(pet);
    }
}
