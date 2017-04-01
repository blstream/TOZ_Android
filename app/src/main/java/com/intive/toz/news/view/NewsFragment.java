package com.intive.toz.news.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateFragment;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState;
import com.intive.toz.R;
import com.intive.toz.network.NetworkState;
import com.intive.toz.news.NewsMvp;
import com.intive.toz.news.adapter.NewsAdapter;
import com.intive.toz.news.model.News;
import com.intive.toz.news.presenter.NewsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * The type News fragment.
 */
public class NewsFragment extends MvpLceViewStateFragment<SwipeRefreshLayout, List<News>, NewsMvp.View, NewsMvp.Presenter>
        implements NewsMvp.View, SwipeRefreshLayout.OnRefreshListener {

    /**
     * The Recycler view.
     */
    @BindView(R.id.contentView)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private Unbinder unbinder;
    private NewsAdapter adapter;
    private NetworkState networkState;
    private boolean hasLoadedSuccessfullyBefore = false;

    /**
     * New instance news fragment.
     *
     * @return the news fragment
     */
    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        unbinder = ButterKnife.bind(this, view);
        swipeRefreshLayout.setOnRefreshListener(this);
        adapter = new NewsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        loadData(false);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter = null;
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        networkState = new NetworkState(getActivity());
        if (hasLoadedSuccessfullyBefore && !networkState.isOnline()) {
            Toast.makeText(getActivity(), getString(R.string.connection_error_on_refresh) ,Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        } else {
            loadData(true);
        }

    }

    @Override
    protected String getErrorMessage(final Throwable e, final boolean pullToRefresh) {
        swipeRefreshLayout.setRefreshing(pullToRefresh);
        return getString(R.string.connection_error);
    }


    @Override
    @NonNull
    public NewsMvp.Presenter createPresenter() {
        return new NewsPresenter();
    }

    @Override
    public void setData(final List<News> data) {
        hasLoadedSuccessfullyBefore = true;
        adapter.setNewsList(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(final boolean pullToRefresh) {
        presenter.loadNews(pullToRefresh);
    }

    @Override
    public void showContent() {
        super.showContent();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public List<News> getData() {
        return adapter == null ? null : adapter.getNewsList();
    }

    @NonNull
    @Override
    public LceViewState<List<News>, NewsMvp.View> createViewState() {
        setRetainInstance(true);
        return new RetainingLceViewState<>();
    }
}
