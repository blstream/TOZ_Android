package com.intive.toz.kindOfHelp.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.intive.toz.kindOfHelp.model.KindOfHelp;
/**
 *  Interface KindOfHelpView.
 *
 */
public interface KindOfHelpView extends MvpView {

    /**
     * Shows kind of help images.
     * @param kindOfHelp kind Of Help
     */
    void showKindOfHelp(final KindOfHelp kindOfHelp);
}
