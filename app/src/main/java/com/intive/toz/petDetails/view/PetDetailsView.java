package com.intive.toz.petDetails.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.intive.toz.info.model.Help;
import com.intive.toz.info.model.Info;
import com.intive.toz.petDetails.model.Comment;
import com.intive.toz.petslist.model.Pet;

import java.util.List;

/**
 * pets list interface used to communicate with pets list fragment and presenter.
 */
public interface PetDetailsView extends MvpView {

    /**
     * Show pet details.
     *
     * @param pet            pet object
     * @param petCreatedDate pet created date
     */
    void showPetDetails(final Pet pet, String petCreatedDate);

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
     * Send Pet data to adapter.
     *
     * @param pet object which contain url images.
     */
    void setPetInAdapter(Pet pet);

    /**
     * Show progress bar.
     */
    void showProgressHelp();

    /**
     * Hide progress bar.
     */
    void hideProgressHelp();

    /**
     * Show comments.
     *
     * @param comments the comments
     */
    void showComments(List<Comment> comments);

    /**
     * On add comment success.
     */
    void onAddCommentSuccess();

    /**
     * On add comment error.
     */
    void onAddCommentError();
}
