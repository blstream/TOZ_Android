package com.intive.toz.common.view.calendar.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.intive.toz.R;
import com.intive.toz.common.view.calendar.adapter.WeekAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * The type Week fragment.
 */
public class WeekFragment extends Fragment {

    /**
     * The constant FIRST.
     */
    public static final int FIRST = 0;
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

    private Unbinder unbinder;
    private int week;

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

}
