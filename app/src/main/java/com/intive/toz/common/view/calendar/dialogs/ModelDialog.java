package com.intive.toz.common.view.calendar.dialogs;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.intive.toz.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created the model dialog.
 */

public class ModelDialog extends DialogFragment {

    private static final double WINDOW_WIDTH = 0.80;

    @BindView(R.id.data_dialog)
    TextView dataDialog;

    @BindView(R.id.title_dialog)
    TextView titleDialog;

    @BindView(R.id.description_dialog)
    TextView descriptionDialog;

    @BindView(R.id.dialog_btn1)
    TextView cancelButton;

    @BindView(R.id.dialog_btn2)
    TextView actionButton;

    private int state;
    private String title;
    private String date;
    private String userName;

    /**
     * New instance dialog fragment.
     *
     * @param state     the state
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
     * Set action of action button.
     */
    @OnClick(R.id.dialog_btn2)
    public void action() {
        dismiss();
    }

    /**
     * Set action of cancel button .
     */
    @OnClick(R.id.dialog_btn1)
    public void cancel() {
        dismiss();
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
