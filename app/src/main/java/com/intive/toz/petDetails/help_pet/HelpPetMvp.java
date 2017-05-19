package com.intive.toz.petDetails.help_pet;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.intive.toz.info.model.Info;
import com.intive.toz.network.PetsApi;
import com.intive.toz.petDetails.model.Help;

/**
 * pet help interface used to communicate with pets list fragment and presenter.
 */

interface HelpPetMvp {

    interface HelpPetView extends MvpView {

        void setFinancialData(Info financial);

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

    interface Presenter extends MvpPresenter<HelpPetView> {

        void loadFinancialData(PetsApi financialService);

        void loadHowToDonateData(PetsApi donateService);
    }
}
