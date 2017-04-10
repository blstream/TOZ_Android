package com.intive.toz.common.view.calendar.presenter;

import android.text.format.DateFormat;
import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.common.view.calendar.ButtonsMvp;
import com.intive.toz.common.view.calendar.dialogs.DialogFactory;
import com.intive.toz.common.view.calendar.model.ReservedDay;
import com.intive.toz.common.view.calendar.model.ReservedDayList;

import java.util.Date;


/**
 * mvp presenter for calendar activity.
 */

public class ButtonsPresenter extends MvpBasePresenter<ButtonsMvp.ButtonsView> implements ButtonsMvp.Presenter {


    @Override
    public void loadData(final int week) {

        getView().setButtons(ReservedDayList.newInstance(week));
    }


    @Override
    public void checkDate(final int position, final Date day, final int week, final boolean isMorning) {

        DialogFactory.day = day;
        DialogFactory.position = position;
        DialogFactory.isMorning = isMorning;
        DialogFactory.week = week;

        ReservedDay reservedDay = getDateObjectReserved(day);

        int resoult = isMorning ? reservedDay.getStateMorning() : reservedDay.getStateAfternoon();
        String name = isMorning ? reservedDay.getUserNameMorning() : reservedDay.getUserNameAfternoon();
        switch (resoult) {
            case 1:
                getView().showDialog(DialogFactory.infoDialog(name));
                break;
            case 2:
                getView().showDialog(DialogFactory.deleteDialog(name));
                break;
            default:
                getView().showDialog(DialogFactory.saveDialog());
                break;
        }
    }

    private ReservedDay getDateObjectReserved(final Date day) {
        String date = getDate(day);
        for (ReservedDay p : ReservedDayList.stateBtn) {
            if (p.getDate().equals(date)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void setDate(final String date, final int week, final boolean isSaved, final boolean isMorning) {
        int value = isSaved ? 2 : 0;
        for (ReservedDay p : ReservedDayList.stateBtn) {
            if (p.getDate().equals(date)) {
                if (isMorning) {
                    p.setStateMorning(value);
                } else {
                    p.setStateAfternoon(value);
                }
            }
        }
        if (isSaved) {
            getView().showSnackbar();
        }
        loadData(week);
        Log.e("BUTTONSPRESENTER", "ZAPISUJE");
    }

    /**
     * Get date.
     *
     * @param day the day
     * @return string date
     */
    public String getDate(final Date day) {
        String date = DateFormat.format("dd", day).toString()
                + DateFormat.format("MM", day).toString()
                + DateFormat.format("yy", day).toString();
        return date;
    }
}
