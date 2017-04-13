package com.intive.toz.news_detail.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.intive.toz.R;
import com.intive.toz.news.model.News;
import com.intive.toz.news_detail.presenter.NewsDetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  Fragment containing news details of one object.
 */
public class NewsDetailFragment extends MvpFragment<NewsDetailView, NewsDetailPresenter>
        implements NewsDetailView {

    @BindView(R.id.title_detail_news)
    TextView title;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.detail_news_iv)
    ImageView image;

    @BindView(R.id.detail_news_description_tv)
    TextView description;

    @BindView(R.id.detail_news_date_tv)
    TextView date;

    private String id;

    @Override
    public NewsDetailPresenter createPresenter() {
        return new NewsDetailPresenter();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_detail, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        ButterKnife.bind(this, view);
        id = getArguments().getString(NewsDetailActivity.ID);
        getPresenter().loadDetailNews(id);
    }

    @Override
    public void showDetailNews(final News news, final String creationDate) {
        title.setText(news.getTitle());
        description.setText(news.getContents());
        date.setText(creationDate);
        Glide.with(getActivity())
                .load(news.getPhotoUrl())
                .placeholder(R.color.colorAccent)
                .error(R.color.colorPrimaryDark)
                .into(image);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(final Throwable e) {
        e.printStackTrace();
    }





}
