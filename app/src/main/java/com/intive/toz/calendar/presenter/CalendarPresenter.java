package com.intive.toz.calendar.presenter;

import android.support.v4.app.DialogFragment;
import android.telecom.Call;
import android.view.View;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.calendar.CalendarMvp;
import com.intive.toz.calendar.dialogs.DialogManager;
import com.intive.toz.calendar.dialogs.SaveDataDialog;

import java.util.Arrays;

/**
 * mvp presenter for calendar activity.
 */

public class CalendarPresenter extends MvpBasePresenter<CalendarMvp.CalendarView> implements CalendarMvp.Presenter {
    boolean[] arrayState = new boolean[14];

    public void loadData() {
//TODO callback of  day state
        Arrays.fill(arrayState, Boolean.FALSE);
        arrayState[1] = true;
        arrayState[0] = true;
        arrayState[8] = true;
        arrayState[10] = true;
        getView().setCalendarButtons(arrayState);
    }

    @Override
    public void checkButtonState(View view) {

        SaveDataDialog saveDataDialog = DialogManager.saveDialog();

        getView().showDialog(saveDataDialog);
        getView().setButton(view,"JA");
    }


}
