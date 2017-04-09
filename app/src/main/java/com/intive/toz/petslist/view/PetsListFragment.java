package com.intive.toz.petslist.view;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateFragment;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState;
import com.intive.toz.Pet;
import com.intive.toz.R;
import com.intive.toz.petslist.presenter.PetsListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *  Fragment containing list of pets with image and short description of each pet.
 */
public class PetsListFragment extends MvpLceViewStateFragment<SwipeRefreshLayout, List<Pet>,
        PetsListView, PetsListPresenter> implements PetsListView, SwipeRefreshLayout.OnRefreshListener  {

    @BindView(R.id.contentView)
    RecyclerView petsRecyclerView;

    @BindView(R.id.loadingView)
    ProgressBar progress;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private Unbinder unbinder;
    private PetsAdapter petsAdapter;
    private List<Pet> petsList = new ArrayList<>();

    /**
     *
     * @return presenter
     */
    public PetsListPresenter createPresenter() {
        return new PetsListPresenter();
    }

    /**
     * empty public constructor.
     */
    public PetsListFragment() {
        // Required empty public constructor
    }

    @Override
    public LceViewState<List<Pet>, PetsListView> createViewState() {
        setRetainInstance(true);
        return new RetainingLceViewState<>();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pets_list, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        unbinder = ButterKnife.bind(this, view);
        swipeRefreshLayout.setOnRefreshListener(this);
        initPetsList();
        loadData(false);
    }

    private void initPetsList() {
        petsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        petsAdapter = new PetsAdapter(petsList);
        petsRecyclerView.setAdapter(petsAdapter);
    }

    @Override
    public void loadData(final boolean pullToRefresh) {
        presenter.loadPetsList(pullToRefresh);
    }

    @Override
    protected String getErrorMessage(final Throwable e, final boolean pullToRefresh) {
        swipeRefreshLayout.setRefreshing(pullToRefresh);
        return getString(R.string.connection_error);
    }

    @Override
    public void setData(final List<Pet> data) {
        petsAdapter.setPetsList(data);
        petsAdapter.notifyDataSetChanged();
    }

    @Override
    public List<Pet> getData() {
        return petsAdapter == null ? null : petsAdapter.getPetsList();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        loadData(true);
    }

    @Override
    public void showContent() {
        super.showContent();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        petsAdapter = null;
        unbinder.unbind();
    }
}
