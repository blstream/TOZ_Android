package com.intive.toz.common.view.calendar.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.intive.toz.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mmate on 29.03.2017.
 */

public class ModelDialog extends DialogFragment {

    @BindView(R.id.data_dialog)
    TextView dataDialog;
    @BindView(R.id.title_dialog)
    TextView titleDialog;
    @BindView(R.id.description_dialog)
    TextView descriptionDialog;
    @BindView(R.id.dialog_btn1)
    Button cancelButton;
    @BindView(R.id.dialog_btn2)
    Button actionButton;

    private int position;
    private int state;
    private String title;
    private String date;
    private String userName;
    private DialogSelectedListener mCallback;

    /**
     * New instance dialog fragment.
     *
     * @param state    the state
     * @param position the position
     * @return the dialog fragment
     */
    public static ModelDialog newInstance(final int state, final int position) {
        Bundle arguments = new Bundle();
        arguments.putInt("DIALOG", state);
        arguments.putInt("POSITION", position);
        ModelDialog dialogFragment = new ModelDialog();
        dialogFragment.setArguments(arguments);
        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state = getArguments().getInt("DIALOG");
        position = getArguments().getInt("POSITION");
    }


    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        try {
            mCallback = (DialogSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DialogSelectedListener");
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

        if (state == 2) {
            mCallback.onDeleteDateSelected(position);
        } else {
            mCallback.onSaveDateSelected(position);
        }
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
