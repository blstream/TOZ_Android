package com.intive.toz.news_detail.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;
import com.intive.toz.news.model.News;
import com.intive.toz.news_detail.view.NewsDetailView;


/**
 *  Presenter for NewsDetail.
 */
public class NewsDetailPresenter extends MvpBasePresenter<NewsDetailView> {

    /**
     *  Load all details of given news object.
     * @param id id of news object
     */
    public void loadDetailNews(final String id) {
        getView().showProgress();
        DataLoader dataLoader = new DataLoader();
        dataLoader.fetchDetailNews(new DataProvider.ResponseCallback<News>() {
            @Override
            public void onSuccess(final News news) {
                if (isViewAttached()) {
                    getView().hideProgress();
                    getView().showDetailNews(news);
                }
            }
            @Override
            public void onError(final Throwable e) {
                if (isViewAttached()) {
                    getView().showError(e);
                }
            }
        }, id);
    }
}
