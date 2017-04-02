package com.intive.toz.news_detail.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.intive.toz.news.model.News;

/**
 *  Interface NewsDetailView.
 *
 */
public interface NewsDetailView extends MvpView {

    /**
     * Shows detail news.
     * @param news news
     * @param date formatted date of creation
     */
    void showDetailNews(final News news, final String date);

    /**
     * Shows progressBar.
     */
    void showProgress();

    /**
     * Hides progressBar.
     */
    void hideProgress();

    /**
     * Shows error.
     * @param e error
     */
    void showError(Throwable e);
}
