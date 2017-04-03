package com.intive.toz.calendar.view;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.intive.toz.R;
import com.intive.toz.calendar.ButtonsMvp;

import com.intive.toz.calendar.SnackbarManager;

import com.intive.toz.calendar.adapter.ButtonsAdapter;
import com.intive.toz.calendar.presenter.ButtonsPresenter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;


/**
 * Fragment to display calendar view.
 */

public class ButtonsFragment extends MvpFragment<ButtonsMvp.ButtonsView, ButtonsMvp.Presenter> implements ButtonsMvp.ButtonsView {

    @BindView(R.id.afternoon_buttons_view)
    GridView gridViewAfternoon;
    @BindView(R.id.morning_buttons_view)
    GridView gridViewMorning;

    Snackbar snackbar;
    FragmentManager fragmentManager;
    Unbinder unbinder;


    @Override
    public android.view.View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_btncalendar, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        presenter.loadData();
        snackbar = SnackbarManager.getSnackbar(getActivity(), "Termin został pomyślnie zapisany");
        fragmentManager = getFragmentManager();
    }

    @OnItemClick(R.id.afternoon_buttons_view)
    public void checkStateAfternoon(View view, int position) {
        presenter.checkButton(position, view, false);
    }


    @OnItemClick(R.id.morning_buttons_view)
    public void checkStateMorning(View view, int position) {
        presenter.checkButton(position, view, true);
    }


    /**
     * set button content
     *
     * @param view view.
     * @param name
     */
    public void setButton(final View view, final String name) {

        ImageView button = (ImageView) view.findViewById(R.id.roundButton);
        button.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
        TextView textView = (TextView) view.findViewById(R.id.textRoundButton);
        textView.setText(name);

    }


    @Override
    public void setButtons(List<Integer> afternoon, List<Integer> morning) {
        ButtonsAdapter AdapterMorning = new ButtonsAdapter(getContext(), morning);
        ButtonsAdapter AdapterAfternoon = new ButtonsAdapter(getContext(), afternoon);

        gridViewAfternoon.setAdapter(AdapterAfternoon);
        gridViewMorning.setAdapter(AdapterMorning);
    }

    @Override
    public ButtonsMvp.Presenter createPresenter() {
        return new ButtonsPresenter();
    }


    @Override
    public void showDialog(DialogFragment dialog) {

        dialog.show(fragmentManager, "Dialog");


    }

    @Override
    public void hideDialog(DialogFragment dialog) {
        dialog.dismiss();
    }


    @Override
    public void showSnackbar() {

        snackbar.show();
    }

    @Override
    public void hideSnackbar() {
        snackbar.dismiss();

    }


}
