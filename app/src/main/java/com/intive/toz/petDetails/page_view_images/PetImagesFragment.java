package com.intive.toz.petDetails.page_view_images;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.intive.toz.petslist.model.Pet;
import com.intive.toz.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment to display selected pet images with navigation.
 */
public class PetImagesFragment extends MvpFragment<PetDetailsImageView, PetDetailsImagePresenter>
        implements PetDetailsImageView {

    private String id;
    String message;

    @BindView(R.id.tv_nr_page)
    TextView tvPage;

    @BindView(R.id.iv_pet_images)
    ImageView image;

    private Unbinder unbinder;

    /**
     * empty constructor PetImagesFragment.
     */
    public PetImagesFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public PetDetailsImagePresenter createPresenter() {
        return new PetDetailsImagePresenter();
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pet_images, container, false);

        Bundle bundle = getArguments();
        message = Integer.toString(bundle.getInt("count"));
        id = bundle.getString("idPet");

        return view;
    }

    @Override
    public void showPetDetails(final Pet pet) {
        tvPage.setText(String.format("%s/10", message));
        Glide.with(this)
                .load(pet.getAddress())
                .centerCrop()
                .placeholder(R.drawable.ic_pets_black_error48dp)
                .error(R.color.greyLight)
                .into(image);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(final Throwable e) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadPetsDetails(id);
    }


}
