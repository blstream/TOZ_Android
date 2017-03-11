package com.intive.toz.financial;

import com.google.gson.annotations.SerializedName;

/**
 * Financial class for json response.
 */

public class FinancialData {
    @SerializedName("account_number")
    private String accountNumber;
    private String title;
    @SerializedName("transfer_receiver_name")
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
