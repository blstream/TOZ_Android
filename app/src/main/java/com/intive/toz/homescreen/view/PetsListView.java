package com.intive.toz.homescreen.view;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.intive.toz.Pet;


import java.util.List;

/**
 *  pets list interface used to communicate with pets list fragment and presenter.
 */

public interface PetsListView extends MvpView {

    /**
     *  Displays a list of Pet objects in recycler view.
     * @param loadedPetsList list of loaded Pet objects
     */
    void showPetsList(List<Pet> loadedPetsList);

    /**
     * Displays a progress bar indicating a loading state.
     */
    void showProgress();

    /**
     * Hides a progress bar indicating a loading state.
     */
    void hideProgress();

    /**
     *  Shows an error indicating a failure during receiving data from server.
     */
    void showError();
}
