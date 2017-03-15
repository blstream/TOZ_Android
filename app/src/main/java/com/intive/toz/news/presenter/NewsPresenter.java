package com.intive.toz.news.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;
import com.intive.toz.news.NewsMvp;
import com.intive.toz.news.model.News;

import java.util.List;

/**
 * The type News presenter.
 */
public class NewsPresenter extends MvpBasePresenter<NewsMvp.View> implements NewsMvp.Presenter {

    /**
     * Instantiates a new News presenter.
     */
    public NewsPresenter() {
    }

    @Override
    public void loadNews(final boolean pullToRefresh) {
        getView().showLoading(pullToRefresh);
        DataLoader dataLoader = new DataLoader();

        dataLoader.fetchNews(new DataProvider.ResponseCallback<List<News>>() {
            @Override
            public void onSuccess(final List<News> news) {
                if (isViewAttached()) {
                    getView().setData(news);
                    getView().showContent();
                }
            }

            @Override
            public void onError(final Throwable e) {
                if (isViewAttached()) {
                    getView().showError(e, false);
                }
            }
        });
    }
}
