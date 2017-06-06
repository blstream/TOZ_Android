package com.intive.toz.news;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.intive.toz.news.model.News;
import com.intive.toz.petDetails.model.Comment;

import java.util.List;

/**
 * The interface News mvp.
 */
public interface NewsMvp {
    /**
     * The interface ButtonsView.
     */
    interface View extends MvpLceView<List<News>> {
        /**
         * Sets comments.
         *
         * @param comments the comments
         */
        void setComments(List<Comment> comments);
    }

    /**
     * The interface Presenter.
     */
    interface Presenter extends MvpPresenter<View> {
        /**
         * Load news.
         *
         * @param pullToRefresh the pull to refresh
         */
        void loadNews(final boolean pullToRefresh);

        /**
         * Load comments.
         */
        void loadComments();
    }
}
