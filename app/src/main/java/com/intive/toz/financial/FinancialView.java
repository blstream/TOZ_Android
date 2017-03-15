package com.intive.toz.financial;

/**
 * interface method for presenter.
 */

public interface FinancialView {

    /**
     * interface method set financial data to textview.
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
