package com.intive.toz.petDetails.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.intive.toz.R;
import com.intive.toz.petDetails.presenter.PetImgPresenter;
import com.intive.toz.petDetails.view_pager.PetImgViewPagerAdapter;
import com.intive.toz.petslist.model.Pet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment to display PageView with images.
 */
public class PetImgFragment extends MvpFragment<PetImgView, PetImgPresenter>
        implements PetImgView {

    private PetImgViewPagerAdapter adapter;
    private String id;

    private Unbinder unbinder;

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.right_nav)
    ImageButton imageButtonRight;

    @BindView(R.id.left_nav)
    ImageButton imageButtonLeft;

    /**
     * empty constructor.
     */
    public PetImgFragment() {
        // Required empty public constructor
    }

    /**
     * create Presenter to fragment with ViewPager.
     *
     * @return PetImgPresenter.
     */
    @Override
    public PetImgPresenter createPresenter() {
        return new PetImgPresenter();
    }

    /**
     * Method to send pet id from activity.
     * @param id pet.
     */
    public void setPetID(final String id) {
        this.id = id;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pet_img, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);

        imageButtonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        });

        imageButtonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Send Pet to Adapter.
     *
     * @param pet object which contain url images.
     */
    @Override
    public void setPetInAdapter(final Pet pet) {
        Context context = getActivity().getApplicationContext();
        adapter = new PetImgViewPagerAdapter(context, pet);
        viewPager.setAdapter(adapter);
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
    public void onStart() {
        presenter.loadPetsDetails(id);
        super.onStart();
    }
}
