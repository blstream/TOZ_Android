package com.intive.toz.calendar.view;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.intive.toz.R;
import com.intive.toz.calendar.CalendarMvp;

import com.intive.toz.calendar.SnackbarManager;
import com.intive.toz.calendar.dialogs.DialogManager;

import com.intive.toz.calendar.presenter.CalendarPresenter;


import java.lang.reflect.Array;
import java.util.Arrays;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Fragment to display calendar view.
 */

public class CalendarFragment extends MvpFragment<CalendarMvp.CalendarView, CalendarMvp.Presenter> implements CalendarMvp.CalendarView {

    @BindViews({R.id.ranoBtn1, R.id.ranoBtn2, R.id.ranoBtn3, R.id.ranoBtn4, R.id.ranoBtn5, R.id.ranoBtn6, R.id.ranoBtn7, R.id.popoludniuBtn1, R.id.popoludniuBtn2, R.id.popoludniuBtn3, R.id.popoludniuBtn4, R.id.popoludniuBtn5, R.id.popoludniuBtn6, R.id.popoludniuBtn7})
    View[] calendarButtons;


    FragmentManager fragmentManager;
    Unbinder unbinder;
    Snackbar snackbar;


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_btncalendar, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        snackbar = SnackbarManager.getSnackbar(getActivity(), "Termin został pomyślnie zapisany");
        fragmentManager = getFragmentManager();
        presenter.loadData();


    }


    @OnClick({R.id.ranoBtn1, R.id.ranoBtn2, R.id.ranoBtn3, R.id.ranoBtn4, R.id.ranoBtn5, R.id.ranoBtn6, R.id.ranoBtn7, R.id.popoludniuBtn1, R.id.popoludniuBtn2, R.id.popoludniuBtn3, R.id.popoludniuBtn4, R.id.popoludniuBtn5, R.id.popoludniuBtn6, R.id.popoludniuBtn7})
    public void chooseButton(View view) {
        presenter.checkButtonState(view);
    }

    /**
     * set button content
     * @param view
     * @param name
     */
    public void setButton(final View view,String name){
        view.findViewById(R.id.calendarButton).findViewById(R.id.calendarButton).setBackgroundTintList(ColorStateList.valueOf(Color
                .parseColor(getResources().getString(0 + R.color.colorPrimaryDark))));
        TextView buttonText = (TextView) view.findViewById(R.id.textCalendarButton);
        buttonText.setText(name);
    }

    @Override
    public void setCalendarButtons(boolean[] arrayState) {

        for (int i = 0; i < arrayState.length; i++) {
            if (arrayState[i]) {
                calendarButtons[i].findViewById(R.id.calendarButton).setBackgroundTintList(ColorStateList.valueOf(Color
                        .parseColor(getResources().getString(0 + R.color.colorAccent))));
                TextView buttonText = (TextView) calendarButtons[i].findViewById(R.id.textCalendarButton);
                buttonText.setText("OK");
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public CalendarMvp.Presenter createPresenter() {
        return new CalendarPresenter();
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
