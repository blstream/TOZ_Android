package com.intive.toz.petDetails.view;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.intive.toz.info.model.Info;
import com.intive.toz.network.PetsApi;
import com.intive.toz.petDetails.model.Help;

/**
 * pet help interface used to communicate with help fragment and presenter.
 */

public interface HelpPetMvp {

    /**
     * interface view donate pet.
     */
    interface HelpPetView extends MvpView {

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

    }

    /**
     * presenter to get both data how to donate.
     */
    interface Presenter extends MvpPresenter<HelpPetView> {

        void loadFinancialData(PetsApi financialService);

        void loadHowToDonateData(PetsApi donateService);
    }
}
