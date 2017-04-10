package com.intive.toz.schedule.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.intive.toz.R;
import com.intive.toz.common.view.calendar.adapter.CalendarAdapter;
import com.intive.toz.common.view.calendar.view.CalendarViewPager;
import com.intive.toz.schedule.ScheduleMvp;
import com.intive.toz.schedule.presenter.SchedulePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * The type Schedule fragment.
 */
public class ScheduleFragment extends MvpFragment<ScheduleMvp.View, ScheduleMvp.Presenter> implements ScheduleMvp.View {
    /**
     * The Calendar vp.
     */
    @BindView(R.id.view_pager)
    CalendarViewPager calendarVp;

    /**
     * The Title tv.
     */
    @BindView(R.id.title_tv)
    TextView titleTv;

    private Unbinder unbinder;
    private CalendarAdapter pagerAdapter;

    /**
     * New instance schedule fragment.
     *
     * @return the schedule fragment
     */
    public static ScheduleFragment newInstance() {
        return new ScheduleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        configCalendar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public ScheduleMvp.Presenter createPresenter() {
        return new SchedulePresenter();
    }

    /**
     * On page move click.
     *
     * @param v the v
     */
    @OnClick({R.id.previous_btn, R.id.next_btn})
    public void onPageMoveClick(final View v) {
        int item = calendarVp.getCurrentItem();
        switch (v.getId()) {
            case R.id.previous_btn:
                calendarVp.setCurrentItem(item - 1, true);
                titleTv.setText(pagerAdapter.getTitle(calendarVp.getCurrentItem()));
                break;
            case R.id.next_btn:
                calendarVp.setCurrentItem(item + 1, true);
                titleTv.setText(pagerAdapter.getTitle(calendarVp.getCurrentItem()));
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        titleTv.setText(pagerAdapter.getTitle(calendarVp.getCurrentItem()));
    }

    @Override
    public void configCalendar() {
          pagerAdapter = new CalendarAdapter(getFragmentManager());
        // pagerAdapter = new CalendarAdapter(getChildFragmentManager());
        calendarVp.setAdapter(pagerAdapter);
        calendarVp.setCurrentItem(1);
        calendarVp.setCanSwipe(false);
    }
}
