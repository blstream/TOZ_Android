package com.intive.toz.common.view.calendar.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * The type Calendar view pager.
 */
public class CalendarViewPager extends ViewPager {

    private boolean canSwipe;

    /**
     * Instantiates a new Calendar view pager.
     *
     * @param context the context
     */
    public CalendarViewPager(final Context context) {
        super(context);
    }

    /**
     * Instantiates a new Calendar view pager.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public CalendarViewPager(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Sets can swipe.
     *
     * @param canSwipe the can swipe
     */
    public void setCanSwipe(final boolean canSwipe) {
        this.canSwipe = canSwipe;
    }

    @Override
    public boolean onTouchEvent(final MotionEvent ev) {
        return canSwipe && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(final MotionEvent ev) {
        return canSwipe && super.onInterceptTouchEvent(ev);
    }
}
