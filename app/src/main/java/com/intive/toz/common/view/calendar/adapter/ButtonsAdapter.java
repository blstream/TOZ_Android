package com.intive.toz.common.view.calendar.adapter;

import android.content.Context;
import android.graphics.PorterDuff;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.intive.toz.R;
import com.intive.toz.common.view.calendar.model.ReservedDay;

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
    @BindView(R.id.roundButton)
    ImageView button;

    @BindView(R.id.textRoundButton)
    TextView textView;

    private Context context;
    private List<ReservedDay> buttons;
    private final boolean isMorning;

    /**
     * Instantiates a new Buttons adapter.
     *
     * @param context the context
     * @param buttons the buttons
     * @param isMorning the is morning
     */
    public ButtonsAdapter(final Context context, final List<ReservedDay> buttons, final boolean isMorning) {
        this.context = context;
        this.buttons = buttons;
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


        ReservedDay d = (ReservedDay) getItem(position);
        int state = isMorning ? d.getStateMorning() : d.getStateAfternoon();
        String name = isMorning ? d.getUserNameMorning() : d.getUserNameAfternoon();
        switch (state) {
            case 1:
                button.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
                textView.setText(name);
                break;
            case 2:
                button.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark), PorterDuff.Mode.MULTIPLY);
                textView.setText("JA");
                break;
            default:
                button.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.whiteText), PorterDuff.Mode.MULTIPLY);
                textView.setText("");
                break;
        }

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
    public void setButtons(final List<ReservedDay> buttons) {
        this.buttons = buttons;
    }
}
