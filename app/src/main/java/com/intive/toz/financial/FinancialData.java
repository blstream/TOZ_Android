package com.intive.toz.financial;

/**
 * Financial class for json response.
 */

public class FinancialData {

    private String accountNumber;
    private String title;
    private String transferReceiverName;

    /**
     * getter to return account number.
     * @return String
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * getter to return transfer title.
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * getter to return receiver name.
     * @return String
     */
    public String getTransferReceiverName() {
        return transferReceiverName;
    }
}
