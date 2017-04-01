package com.intive.toz.news_detail.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;
import com.intive.toz.news.model.News;
import com.intive.toz.news_detail.view.NewsDetailView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 *  Presenter for NewsDetail.
 */
public class NewsDetailPresenter extends MvpBasePresenter<NewsDetailView> {

    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private Calendar calendar = Calendar.getInstance();

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
                    getView().showDetailNews(news, convertToDate(news.getCreated()));
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

    /**
     * Convert date.
     * @param dateInMilliseconds date in miliseconds
     * @return date in dd/MM/yyyy format
     */
    public String convertToDate(final long dateInMilliseconds) {
        calendar.setTimeInMillis(dateInMilliseconds);
        return formatter.format(calendar.getTime());
    }
}
