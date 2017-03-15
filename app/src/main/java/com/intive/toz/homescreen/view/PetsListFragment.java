package com.intive.toz.homescreen.view;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.intive.toz.Pet;
import com.intive.toz.R;
import com.intive.toz.homescreen.presenter.PetsListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 *  Fragment containing list of pets with image and short description of each pet.
 */
public class PetsListFragment extends MvpFragment<PetsListView, PetsListPresenter> implements PetsListView {


    private ProgressBar progress;
    private RecyclerView petsRecyclerView;
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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pets_list, container, false);
        initViews(rootView);
        initPetsList(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        getPresenter().loadData();
    }

    private void initViews(final View rootView) {
        progress = (ProgressBar) rootView.findViewById(R.id.progress_bar);
    }

    private void initPetsList(final View rootView) {
        petsRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        petsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        petsAdapter = new PetsAdapter(petsList);
        petsRecyclerView.setAdapter(petsAdapter);
    }

    @Override
    public void showPetsList(final List<Pet> loadedPetsList) {
        petsList.clear();
        petsList.addAll(loadedPetsList);
        petsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
    }

}
