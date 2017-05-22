package com.intive.toz.info;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.intive.toz.info.model.Help;
import com.intive.toz.info.model.Info;
import com.intive.toz.network.PetsApi;

/**
 * interfaces for Presenter and ButtonsView.
 */
public interface FinancialMvp {

    /**
     * interface to presenter.
     */
    interface Presenter extends MvpPresenter<FinancialView> {
        /**
         * interface method to get financial information from service.
         *
         * @param financialService
         */
        void loadFinancialData(PetsApi financialService);

        void loadHowToDonateData(PetsApi donateService);
    }

    /**
     * interface to view.
     */
    interface FinancialView extends MvpView {
        /**
         * interface method set financial data to textview.
         *
         * @param financial object class FinancialData
         */
        void setFinancialData(Info financial);

        /**
         * second part information how to donate particular pet.
         *
         * @param donate information have been included while create pet in form from database.
         */
        void setDonateInfo(Help donate);

        /**
         * show progress bar in financial activity.
         */
        void showProgress();

        /**
         * hide progress bar in financial activity.
         */
        void hideProgress();

        /**
         * show error in financial activity.
         */
        void showError();
    }
}