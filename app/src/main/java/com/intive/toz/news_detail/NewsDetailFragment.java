package com.intive.toz.news_detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.intive.toz.R;
import com.intive.toz.news_detail.presenter.NewsDetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by K on 2017-03-29.
 */

public class NewsDetailFragment extends MvpFragment<NewsDetailView, NewsDetailPresenter>
        implements NewsDetailView{

    @BindView(R.id.title_detail_news)
    TextView title;

    @Override
    public NewsDetailPresenter createPresenter() {
        return new NewsDetailPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_detail, container, false);

    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        ButterKnife.bind(this, view);
        title.setText(getArguments().getString(NewsDetailActivity.ID));
    }


}
