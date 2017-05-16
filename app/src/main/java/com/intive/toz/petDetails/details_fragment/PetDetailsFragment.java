package com.intive.toz.petDetails.details_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.intive.toz.R;
import com.intive.toz.petslist.model.Pet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetDetailsFragment extends MvpFragment<PetDetailsView, PetDetailsPresenter>
        implements PetDetailsView {

    @BindView(R.id.name_pet_details)
    TextView nameTv;

    @BindView(R.id.type_pet_details)
    TextView typeTv;

    @BindView(R.id.sex_pet_details)
    TextView sexTv;

    @BindView(R.id.date_pet_details)
    TextView dateTv;

    @BindView(R.id.description_pet_details)
    TextView descriptionTv;

    @BindView(R.id.progress_bar_pet_details)
    ProgressBar progressBar;

    private String id;
    private Unbinder unbinder;

    /**
     *  pet constructor.
     */
    public PetDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * set pet id from activity to fragment.
     * @param id pet id
     */
    public void setPetID(final String id) {
        this.id = id;
    }

    @Override
    public PetDetailsPresenter createPresenter() {
        return new PetDetailsPresenter();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pet_details, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showPetDetails(final Pet pet, final String petCreatedDate) {
        nameTv.setText(pet.getName());
        typeTv.setText(pet.getType());
        dateTv.setText(petCreatedDate);
        sexTv.setText(pet.getSex());
        descriptionTv.setText(pet.getDescription());
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(final Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadPetsDetails(id);
    }
}
