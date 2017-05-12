package com.intive.toz.common.view.calendar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.intive.toz.common.view.calendar.view.WeekFragment;

/**
 * The type Calendar adapter.
 */
public class CalendarAdapter extends FragmentPagerAdapter {
    /**
     * The constant WEEKS.
     */
    private static final int WEEKS = 3;

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
        return WeekFragment.newInstance(position);
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


}
