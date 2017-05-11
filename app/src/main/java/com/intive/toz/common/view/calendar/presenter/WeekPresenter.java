package com.intive.toz.common.view.calendar.presenter;

import android.text.format.DateFormat;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.common.view.calendar.WeekMvp;
import com.intive.toz.common.view.calendar.dialogs.DialogFactory;
import com.intive.toz.common.view.calendar.dialogs.OnReservationChangeListener;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;
import com.intive.toz.schedule.model.Config;
import com.intive.toz.schedule.model.Reservation;
import com.intive.toz.schedule.model.Schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * mvp presenter for calendar activity.
 */
public class WeekPresenter extends MvpBasePresenter<WeekMvp.ButtonsView>
        implements WeekMvp.Presenter, DataProvider.ResponseCallback<Schedule>, OnReservationChangeListener {

    private Schedule schedule;

    @Override
    public void checkDate(final int position, final Date day, final int week, final boolean isMorning) {

        DialogFactory.day = day;
        DialogFactory.position = position;
        DialogFactory.isMorning = isMorning;
        DialogFactory.week = week;

        List<Reservation> reservations = schedule.getReservations();
        List<Config> configs = schedule.getConfigs();
        String currentDate = DateFormat.format("yyyy-MM-dd", day).toString();
        if (reservations == null) {
            reservations = new ArrayList<>();
        }

        int result = 0;
        Reservation reservation = null;
        for (Reservation r : reservations) {
            if (r.getDate().equals(currentDate)) {
                if (isMorning && r.getStartTime().equals(configs.get(position).getPeriods().get(0).getPeriodStart())) {
                    reservation = r;
                    result = 1;
                } else if (!isMorning && r.getStartTime().equals(configs.get(position).getPeriods().get(1).getPeriodStart())) {
                    reservation = r;
                    result = 1;
                }
            }
        }

        String startDate = isMorning
                ? configs.get(position).getPeriods().get(0).getPeriodStart()
                : configs.get(position).getPeriods().get(1).getPeriodStart();

        String endDate = isMorning
                ? configs.get(position).getPeriods().get(0).getPeriodEnd()
                : configs.get(position).getPeriods().get(1).getPeriodEnd();

        switch (result) {
            case 1:
                getView().showDialog(DialogFactory.infoDialog(reservation.getOwnerName()));
                break;
            case 2:
                getView().showDialog(DialogFactory.deleteDialog(reservation.getOwnerName()));
                break;
            default:
                if (day.after(new Date())) {
                    getView().showDialog(DialogFactory.saveDialog(startDate, endDate, this));
                }
                break;
        }
    }

    @Override
    public void fetchSchedule(final String from, final String to) {
        DataLoader dataLoader = new DataLoader();
        dataLoader.fetchSchedule(this, from, to);
    }

    @Override
    public void onSuccess(final Schedule response) {
        if (response != null) {
            schedule = response;
            getView().setSchedule(response);
        }
    }

    @Override
    public void onError(final Throwable e) {

    }

    @Override
    public void onSuccess() {
        getView().showSnackbar();
        getView().refreshSchedule();
    }
}
