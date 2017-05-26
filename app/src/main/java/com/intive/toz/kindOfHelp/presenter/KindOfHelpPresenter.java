package com.intive.toz.kindOfHelp.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.R;
import com.intive.toz.kindOfHelp.model.KindOfHelp;
import com.intive.toz.kindOfHelp.view.KindOfHelpView;


/**
 * Presenter for Kind Of Help.
 */

public class KindOfHelpPresenter extends MvpBasePresenter<KindOfHelpView> {

    /**
     *  Load kind of help.
     *
     */
    public void loadKindOfHelp() {
        KindOfHelp kindOfHelp = new KindOfHelp(R.drawable.charity_payment, R.drawable.volunteer);
        getView().showKindOfHelp(kindOfHelp);
    }
}
