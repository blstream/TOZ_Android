package com.intive.toz.financial;

import com.intive.toz.network.PetsApi;

/**
 * Created by Krzysiek on 2017-03-15.
 */

public interface IFinancialPresenter {

    void loadFinancialData(PetsApi financialService);
}
