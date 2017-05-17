package com.intive.toz.add_pet;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * The interface Add pet mvp.
 */
public interface AddPetMvp {
    /**
     * The interface View.
     */
    interface View extends MvpView {

    }

    /**
     * The interface Presenter.
     */
    interface Presenter extends MvpPresenter<View> {

    }
}
