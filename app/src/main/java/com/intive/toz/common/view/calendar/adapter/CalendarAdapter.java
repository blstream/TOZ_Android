package com.intive.toz.common.view.calendar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.intive.toz.common.view.calendar.view.WeekFragment;

import java.util.Date;
import java.util.List;

/**
 * The type Calendar adapter.
 */
public class CalendarAdapter extends FragmentPagerAdapter {
    /**
     * The constant WEEKS.
     */
    public static final int WEEKS = 3;

    /**
     * Instantiates a new Calendar adapter.
     *
     * @param fm the fm
     */
    public CalendarAdapter(final FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case 0:
                return WeekFragment.newInstance(0);
            case 1:
                return WeekFragment.newInstance(1);
            case 2:
                return WeekFragment.newInstance(2);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return WEEKS;
    }

    /**
     * Gets title.
     *
     * @param position the position
     * @return the title
     */
    public String getTitle(final int position) {
        return ((WeekFragment) getItem(position)).getTitle(position);
    }

    /**
     * Gets week.
     *
     * @param position the position
     * @return the week
     */
    public List<Date> getWeek(final int position) {
        return ((WeekFragment) getItem(position)).getWeek(position);
    }
}
