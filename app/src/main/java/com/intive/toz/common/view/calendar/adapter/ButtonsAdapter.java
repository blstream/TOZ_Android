package com.intive.toz.common.view.calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.intive.toz.R;
import com.intive.toz.common.view.circular_text_view.CircularTextView;
import com.intive.toz.schedule.model.Reservation;

import java.util.List;

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
    private List<Reservation> buttons;
    private final boolean isMorning;

    /**
     * Instantiates a new Buttons adapter.
     *
     * @param context the context
     * @param isMorning the is morning
     */
    public ButtonsAdapter(final Context context, final boolean isMorning) {
        this.context = context;
        this.isMorning = isMorning;
    }

    @Override
    public int getCount() {
        return buttons == null ? 0 : buttons.size();
    }

    @Override
    public Object getItem(final int position) {
        return buttons.get(position);
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

        textView.setStrokeColor(R.color.black);
        textView.setStrokeWidth(1);
//        switch (state) {
//            case 1:
//                textView.setSolidColor(R.color.busy);
//                textView.setText(name);
//                break;
//            case 2:
//                textView.setSolidColor(R.color.my);
//                textView.setText(R.string.user_calendar_button_name);
//                break;
//            default:
//                textView.setSolidColor(R.color.free);
//                textView.setText("  ");
//                break;
//        }

        return view;
    }

    /**
     * Clear buttons state.
     */
    public void clear() {
        this.buttons = null;
    }

    /**
     * Set buttons state.
     *
     * @param buttons the buttons
     */
    public void setButtons(final List<Reservation> buttons) {
        this.buttons = buttons;
    }
}
