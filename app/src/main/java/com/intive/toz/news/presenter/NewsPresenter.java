package com.intive.toz.news.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;
import com.intive.toz.data.DateFormatter;
import com.intive.toz.news.NewsMvp;
import com.intive.toz.news.model.News;
import com.intive.toz.petDetails.model.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * The type News presenter.
 */
public class NewsPresenter extends MvpBasePresenter<NewsMvp.View> implements NewsMvp.Presenter {

    private DateFormatter dateFormatter = new DateFormatter();

    /**
     * Instantiates a new News presenter.
     */
    public NewsPresenter() {
    }

    @Override
    public void loadNews(final boolean pullToRefresh) {
        getView().showLoading(pullToRefresh);
        DataLoader dataLoader = new DataLoader();

        dataLoader.fetchReleasedNews(new DataProvider.ResponseCallback<List<News>>() {
            @Override
            public void onSuccess(final List<News> news) {
                if (isViewAttached()) {
                    getView().setData(newsDateFilter(news));
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

    /**
     * Load comments.
     *
     */
    @Override
    public void loadComments() {
        DataLoader dataLoader = new DataLoader();
        dataLoader.allComments(new DataProvider.ResponseCallback<List<Comment>>() {
            @Override
            public void onSuccess(final List<Comment> response) {
                if (response.size() > 5) {
                    getView().setComments(response.subList(0, 5));
                } else {
                    getView().setComments(response);
                }
            }

            @Override
            public void onError(final Throwable e) {

            }
        });
    }

    /**
     *
     * @param news List of news from backend
     * @return List with only news since half year
     */
    private List<News> newsDateFilter(final List<News> news) {
        List<News> sortNews = new ArrayList<>();
        for (News n : news) {
            if (dateFormatter.isLessThanSixMonth(n.getCreated())) {
                sortNews.add(n);
            }
        }
        return sortNews;
    }
}
