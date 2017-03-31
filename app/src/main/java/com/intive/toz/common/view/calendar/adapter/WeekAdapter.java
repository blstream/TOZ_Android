package com.intive.toz.common.view.calendar.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.intive.toz.R;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The type Week adapter.
 */
public class WeekAdapter extends BaseAdapter {

    /**
     * The Text tv.
     */
    @BindView(R.id.text_tv)
    TextView textTv;

    /**
     * The Number tv.
     */
    @BindView(R.id.number_tv)
    TextView numberTv;

    private Context context;
    private List<Date> week;

    /**
     * Instantiates a new Week adapter.
     *
     * @param context the context
     * @param week    the week
     */
    public WeekAdapter(final Context context, final List<Date> week) {
        this.context = context;
        this.week = week;
    }

    @Override
    public int getCount() {
        return week == null ? 0 : week.size();
    }

    @Override
    public Object getItem(final int position) {
        return week.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final Date day = week.get(position);
        View view = convertView;

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.item_week_view, parent, false);
            ButterKnife.bind(this, view);
        }

        numberTv.setText(DateFormat.format("dd", day).toString());
        textTv.setText(DateFormat.format("EEE", day).toString());

        return view;
    }
}
