package com.intive.toz.petDetails.page_view_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.intive.toz.Pet;
import com.intive.toz.R;
import com.intive.toz.petDetails.details_fragment.PetDetailsPresenter;
import com.intive.toz.petDetails.details_fragment.PetDetailsView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PetImagesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PetImagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PetImagesFragment extends MvpFragment<PetDetailsView, PetDetailsImagePresenter>
implements PetDetailsView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView tvPage;

    private String id;

//    private OnFragmentInteractionListener mListener;

    public PetImagesFragment() {
        // Required empty public constructor
    }

    @Override
    public PetDetailsImagePresenter createPresenter() {
        return new PetDetailsImagePresenter();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PetImagesFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static PetImagesFragment newInstance(String param1, String param2) {
//        PetImagesFragment fragment = new PetImagesFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_pet_images, container, false);
        tvPage = (TextView) view.findViewById(R.id.tv_nr_page);

        Bundle bundle = getArguments();
        String message = Integer.toString(bundle.getInt("count"));

        tvPage.setText(message + " / " + "10");

        return view;
    }

    @Override
    public void showPetDetails(Pet pet, String petCreatedDate) {
        tvPage.setText(pet.getName());
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

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

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

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadPetsDetails(id);
    }
}
