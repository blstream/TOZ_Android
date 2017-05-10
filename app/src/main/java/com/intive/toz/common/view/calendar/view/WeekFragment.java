package com.intive.toz.common.view.calendar.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.intive.toz.R;
import com.intive.toz.common.view.calendar.SnackbarFactory;
import com.intive.toz.common.view.calendar.WeekMvp;
import com.intive.toz.common.view.calendar.adapter.ButtonsAdapter;
import com.intive.toz.common.view.calendar.adapter.WeekAdapter;
import com.intive.toz.common.view.calendar.presenter.WeekPresenter;
import com.intive.toz.schedule.model.Reservation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;

/**
 * The type Week fragment.
 */
public class WeekFragment extends MvpFragment<WeekMvp.ButtonsView, WeekMvp.Presenter> implements WeekMvp.ButtonsView {

    /**
     * The constant FIRST.
     */
    public static final int FIRST = 0;
    /**
     * The constant WEEKS.
     */
    public static final int WEEKS = 3;
    /**
     * The constant LAST.
     */
    public static final int LAST = 6;
    /**
     * The constant WEEK.
     */
    public static final String WEEK = "week";

    /**
     * The Grid view.
     */
    @BindView(R.id.week_view)
    GridView gridView;
    @BindView(R.id.afternoon_buttons_view)
    GridView gridViewAfternoon;
    @BindView(R.id.morning_buttons_view)
    GridView gridViewMorning;

    private Unbinder unbinder;
    private int week;
    private Snackbar snackbar;
    private FragmentManager fragmentManager;
    private ButtonsAdapter adapterMorning;
    private ButtonsAdapter adapterAfternoon;


    /**
     * New instance week fragment.
     *
     * @param week the week
     * @return the week fragment
     */
    public static WeekFragment newInstance(final int week) {
        Bundle arguments = new Bundle();
        arguments.putInt(WEEK, week);
        WeekFragment weekFragment = new WeekFragment();

        weekFragment.setArguments(arguments);
        return weekFragment;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        week = getArguments().getInt(WEEK, 0);
    }

    @Override
    @Nullable
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.calendar_week_view, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        unbinder = ButterKnife.bind(this, view);

        List<Date> dates = getWeek(week);
        WeekAdapter adapter = new WeekAdapter(getContext(), dates);

        gridView.setAdapter(adapter);
        adapterMorning = new ButtonsAdapter(getContext(), true);
        adapterAfternoon = new ButtonsAdapter(getContext(), false);

        presenter.fetchSchedule(DateFormat.format("yyyy-MM-dd", getWeek(week).get(FIRST)).toString(),
                DateFormat.format("yyyy-MM-dd", getWeek(week).get(LAST)).toString());
        fragmentManager = getFragmentManager();
    }

    /**
     * Gets title.
     *
     * @param week the week
     * @return the title
     */
    public String getTitle(final int week) {
        List<Date> dates = getWeek(week);
        Date first = dates.get(FIRST);
        Date last = dates.get(LAST);
        if (DateFormat.format("MMMM", first).toString().equals(DateFormat.format("MMMM", last).toString())) {
            return (DateFormat.format("dd", first).toString()
                    + " - " + DateFormat.format("dd", last).toString()
                    + " " + DateFormat.format("MMMM", first).toString()).toUpperCase();
        }
        return (DateFormat.format("dd", first).toString()
                + " " + DateFormat.format("MMMM", first).toString()
                + " - " + DateFormat.format("dd", last).toString()
                + " " + DateFormat.format("MMMM", last).toString()).toUpperCase();
    }

    /**
     * Gets week.
     *
     * @param week the week
     * @return the week
     */
    public List<Date> getWeek(final int week) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        if (week == 0) {
            currentWeek--;
        } else if (week == 2) {
            currentWeek++;
        }
        calendar.set(Calendar.WEEK_OF_MONTH, currentWeek);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());

        List<Date> dates = new ArrayList<>();
        for (int i = 0; i <= LAST; i++) {
            dates.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }
        return dates;
    }

    /**
     * Check morning button date state.
     *
     * @param position the position
     */
    @OnItemClick(R.id.morning_buttons_view)
    public void checkStateMorning(final int position) {
        List<Date> dates = getWeek(week);
        Date day = dates.get(position);
        presenter.checkDate(position, day, week, true);
    }

    /**
     * Check afternoon button date state.
     *
     * @param position the position
     */
    @OnItemClick(R.id.afternoon_buttons_view)
    public void checkStateAfternoon(final int position) {
        List<Date> dates = getWeek(week);
        Date day = dates.get(position);
        presenter.checkDate(position, day, week, false);
    }

    @Override
    public WeekMvp.Presenter createPresenter() {
        return new WeekPresenter();
    }

    @Override
    public void setButtons(final List<Reservation> reservedDayList) {
        adapterMorning.clear();
        adapterAfternoon.clear();

        adapterMorning.setButtons(reservedDayList);
        adapterAfternoon.setButtons(reservedDayList);

        gridViewAfternoon.setAdapter(adapterAfternoon);
        gridViewMorning.setAdapter(adapterMorning);
    }

    @Override
    public void showDialog(final DialogFragment dialog) {
        dialog.show(fragmentManager, "Dialog");
    }

    @Override
    public void showSnackbar() {
        snackbar = SnackbarFactory.getSnackbar(getActivity(), "Termin został pomyślnie zapisany");
        snackbar.show();
    }

}
