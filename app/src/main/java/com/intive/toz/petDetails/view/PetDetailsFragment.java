package com.intive.toz.petDetails.view;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.intive.toz.R;
import com.intive.toz.petDetails.presenter.PetDetailsPresenter;
import com.intive.toz.petslist.model.Pet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment to show details about Pet.
 */
public class PetDetailsFragment extends MvpFragment<PetDetailsView, PetDetailsPresenter>
        implements PetDetailsView {

    /**
     * interface to pass data through activity.
     */
    interface DataPassListener {

        /**
         * Method to pass pet data.
         *
         * @param data Pet data.
         */
        void passData(final Pet data);
    }

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

    DataPassListener mCallback;

    /**
     * pet constructor.
     */
    public PetDetailsFragment() {
    }

    /**
     * set pet id from activity to fragment.
     *
     * @param id pet id
     */
    public void setPetID(final String id) {
        this.id = id;
    }

    @NonNull
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

        if (pet.getSex().equals(getString(R.string.male_tag))) {
            sexTv.setText(R.string.pet_sex_male);
        } else {
            sexTv.setText(R.string.pet_sex_female);
        }
        if (pet.getType().equals(getString(R.string.dog_tag))) {
            typeTv.setText(R.string.dog_type);
        } else {
            typeTv.setText(R.string.cat_type);
        }
        nameTv.setText(pet.getName());
        dateTv.setText(petCreatedDate);
        descriptionTv.setText(pet.getDescription());
    }

    @Override
    public void sendPetToFragmentImg(final Pet pet) {
        mCallback.passData(pet);
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

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (DataPassListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement DataPassListener");
        }
    }
}
