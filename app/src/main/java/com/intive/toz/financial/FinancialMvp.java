package com.intive.toz.financial;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.intive.toz.financial.model.FinancialData;
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
        void setFinancialData(FinancialData financial);

        /**
         * show progress bar in financial activity.
         */
        void showProgres();

        /**
         * hide progress bar in financial activity.
         */
        void hideProgres();

        /**
         * show error in financial activity.
         */
        void showError();
    }
}