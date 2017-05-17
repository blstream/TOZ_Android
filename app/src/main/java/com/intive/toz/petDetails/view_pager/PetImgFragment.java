package com.intive.toz.petDetails.view_pager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.intive.toz.R;
import com.intive.toz.petslist.model.Pet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PetImgFragment extends MvpFragment<PetDetailsImageView, PetDetailsImagePresenter>
        implements PetDetailsImageView {

    ViewPagerAdapter adapter;
    String id;

    private Unbinder unbinder;

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.right_nav)
    ImageButton imageButtonRight;

    @BindView(R.id.left_nav)
    ImageButton imageButtonLeft;

    private OnFragmentInteractionListener mListener;

    public PetImgFragment() {
        // Required empty public constructor
    }

    @Override
    public PetDetailsImagePresenter createPresenter() {
        return new PetDetailsImagePresenter();
    }

    public void setPetID(final String id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pet_img, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showPetDetails(final Pet pet) {
//        nameTv.setText(pet.getName());
    }

    @Override
    public void setPetInAdapter(Pet pet) {
        Context context = getActivity().getApplicationContext();
        adapter = new ViewPagerAdapter(context, pet);

        adapter.setPet(pet);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(Throwable e) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onStart() {
        presenter.loadPetsDetails(id);
        super.onStart();
    }
}
