package com.intive.toz.common.view.calendar.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.intive.toz.R;
import com.intive.toz.common.view.circular_text_view.CircularTextView;
import com.intive.toz.login.Session;
import com.intive.toz.schedule.model.Config;
import com.intive.toz.schedule.model.Reservation;
import com.intive.toz.schedule.model.Schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The type Buttons adapter.
 */
public class ButtonsAdapter extends BaseAdapter {
    /**
     * The Button calendar.
     */

    @BindView(R.id.textRoundButton)
    CircularTextView textView;

    private Context context;
    private Schedule schedule;
    private List<Date> dates;
    private boolean isMorning;

    /**
     * Instantiates a new Buttons adapter.
     *
     * @param context   the context
     * @param dates     the dates
     * @param isMorning the is morning
     */
    public ButtonsAdapter(final Context context, final List<Date> dates, final boolean isMorning) {
        this.context = context;
        this.dates = dates;
        this.isMorning = isMorning;
    }

    @Override
    public int getCount() {
        return dates == null ? 0 : dates.size();
    }

    @Override
    public Object getItem(final int position) {
        return schedule.getReservations().get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.item_calendar_button, parent, false);
            ButterKnife.bind(this, view);
        }

        textView.setStrokeColor(R.color.inactive_arrow_color);
        textView.setStrokeWidth(1);
        textView.setSolidColor(R.color.free);
        textView.setText("  ");

        String currentDate = DateFormat.format("yyyy-MM-dd", dates.get(position)).toString();

        List<Reservation> reservations = schedule.getReservations();
        List<Config> configs = schedule.getConfigs();

        if (reservations == null) {
            reservations = new ArrayList<>();
        }

        for (Reservation r : reservations) {
            if (r.getDate().equals(currentDate)) {
                if (isMorning && r.getStartTime().equals(configs.get(position).getPeriods().get(0).getPeriodStart())) {
                    textView.setSolidColor(R.color.busy);
                    textView.setStrokeColor(R.color.busy);
                    textView.setText(getInitials(r.getOwnerName()));
                    if (r.getOwnerId().equals(Session.getUserId())) {
                        textView.setSolidColor(R.color.my);
                        textView.setStrokeColor(R.color.my);
                        textView.setText(R.string.user_calendar_button_name);
                    }
                } else if (!isMorning && r.getStartTime().equals(configs.get(position).getPeriods().get(1).getPeriodStart())) {
                    textView.setSolidColor(R.color.busy);
                    textView.setStrokeColor(R.color.busy);
                    textView.setText(getInitials(r.getOwnerName()));
                    if (r.getOwnerId().equals(Session.getUserId())) {
                        textView.setSolidColor(R.color.my);
                        textView.setStrokeColor(R.color.my);
                        textView.setText(R.string.user_calendar_button_name);
                    }
                }
            }
        }

        return view;
    }

    private String getInitials(final String name) {
        Pattern p = Pattern.compile("((^| )[A-Za-z])");
        Matcher m = p.matcher(name);
        String initials = "";
        while (m.find()) {
            initials += m.group().trim();
        }
        return initials;
    }

    /**
     * Clear buttons state.
     */
    public void clear() {
        this.schedule = null;
    }

    /**
     * Sets schedule.
     *
     * @param schedule the schedule
     */
    public void setSchedule(final Schedule schedule) {
        this.schedule = schedule;
    }
}
