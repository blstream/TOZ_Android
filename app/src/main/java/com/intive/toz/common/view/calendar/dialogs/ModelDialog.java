package com.intive.toz.common.view.calendar.dialogs;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.intive.toz.R;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;
import com.intive.toz.login.Session;
import com.intive.toz.schedule.model.Reservation;
import com.intive.toz.schedule.model.Reserve;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * Created the model dialog.
 */
public class ModelDialog extends DialogFragment {

    private static final double WINDOW_WIDTH = 0.80;

    /**
     * The Data dialog.
     */
    @BindView(R.id.data_dialog)
    TextView dataDialog;

    /**
     * The Title dialog.
     */
    @BindView(R.id.title_dialog)
    TextView titleDialog;

    /**
     * The Description dialog.
     */
    @BindView(R.id.description_dialog)
    TextView descriptionDialog;

    /**
     * The Cancel button.
     */
    @BindView(R.id.dialog_btn1)
    TextView cancelButton;

    /**
     * The Action button.
     */
    @BindView(R.id.dialog_btn2)
    TextView actionButton;

    /**
     * The Progress bar.
     */
    @BindView(R.id.content)
    View progressBar;

    private int state;
    private String title;
    private String date;
    private String userName;
    private String startDate;
    private String endDate;
    private String id;
    private Date day;
    private OnReservationChangeListener listener;

    /**
     * New instance dialog fragment.
     *
     * @param state the state
     * @return the dialog fragment
     */
    public static ModelDialog newInstance(final int state) {
        Bundle arguments = new Bundle();
        arguments.putInt("DIALOG", state);
        ModelDialog dialogFragment = new ModelDialog();
        dialogFragment.setArguments(arguments);
        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state = getArguments().getInt("DIALOG");
    }

    @Override
    public void onResume() {
        super.onResume();

        Window window = getDialog().getWindow();
        Point size = new Point();

        Display display;
        if (window != null) {
            display = window.getWindowManager().getDefaultDisplay();
            display.getSize(size);

            int width = size.x;

            window.setLayout((int) (width * WINDOW_WIDTH), WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
        }

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    /**
     * Set title.
     *
     * @param title the title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Set date.
     *
     * @param date the date
     */
    public void setDate(final String date) {
        this.date = date;
    }

    /**
     * Set user name.
     *
     * @param userName the user name
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(final String endDate) {
        this.endDate = endDate;
    }

    /**
     * Sets day.
     *
     * @param day the day
     */
    public void setDay(final Date day) {
        this.day = day;
    }

    /**
     * Sets listener.
     *
     * @param listener the listener
     */
    public void setListener(final OnReservationChangeListener listener) {
        this.listener = listener;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Set action of action button.
     */
    @OnClick(R.id.dialog_btn2)
    public void action() {
        if (state == 2) {
            removeReservation();
        } else {
            reserve();
        }
    }

    /**
     * Set action of cancel button .
     */
    @OnClick(R.id.dialog_btn1)
    public void cancel() {
        dismiss();
    }

    private void reserve() {
        progressBar.setVisibility(View.INVISIBLE);
        DataLoader dataLoader = new DataLoader();
        Reserve reserve = new Reserve();
        reserve.setDate(DateFormat.format("yyyy-MM-dd", day).toString());
        reserve.setStartTime(startDate);
        reserve.setEndTime(endDate);
        reserve.setOwnerId(Session.getUserId());
        reserve.setModificationMessage("");
        dataLoader.reserve(new DataProvider.ResponseCallback<Reservation>() {
            @Override
            public void onSuccess(final Reservation response) {
                progressBar.setVisibility(View.VISIBLE);
                dismiss();
                listener.onChanged(R.string.reservation_done);
            }

            @Override
            public void onError(final Throwable e) {
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), getResources().getString(R.string.default_error), Toast.LENGTH_SHORT).show();
            }
        }, reserve);
    }

    private void removeReservation() {
        progressBar.setVisibility(View.INVISIBLE);
        DataLoader dataLoader = new DataLoader();
        dataLoader.removeReservation(new DataProvider.ResponseCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody response) {
                progressBar.setVisibility(View.VISIBLE);
                dismiss();
                listener.onChanged(R.string.reservation_removed);
            }

            @Override
            public void onError(Throwable e) {
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), getResources().getString(R.string.default_error), Toast.LENGTH_SHORT).show();
            }
        }, id);
    }

    /**
     * Init view param.
     */
    public void initView() {
        switch (state) {
            case 1:
                titleDialog.setText(title);
                dataDialog.setText(date);
                descriptionDialog.setText(userName);
                actionButton.setVisibility(View.GONE);
                cancelButton.setText(R.string.calendar_info_dialog_cancel_button);
                break;
            case 2:
                titleDialog.setText(title);
                dataDialog.setText(date);
                descriptionDialog.setText(userName);
                actionButton.setText(R.string.calendar_delete_dialog_delete_button);
                cancelButton.setText(R.string.calendar_delete_dialog_cancel_button);
                break;
            default:
                titleDialog.setText(title);
                dataDialog.setText(date);
                descriptionDialog.setText(R.string.calendar_info_save_dialog);
                cancelButton.setText(R.string.calendar_save_dialog_cancel_button);
                actionButton.setText(R.string.calendar_save_dialog_save_button);
                break;
        }
    }
}
