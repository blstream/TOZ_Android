package com.intive.toz.calendar.dialogs;

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
 * fragment to display dialog.
 */

public class DeleteDataDialog extends DialogFragment {


    @BindView(R.id.data_dialog)
    TextView dataDialog;
    @BindView(R.id.title_dialog)
    TextView titleDialog;
    @BindView(R.id.description_dialog)
    TextView descriptionDialog;
    @BindView(R.id.dialog_btn1)
    Button backButton;
    @BindView(R.id.dialog_btn2)
    Button deleteButton;

    private String title;
    private String date;
    private String userName;

    /**
     * set title in dialog.
     *
     * @param title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * set date in dialog.
     *
     * @param date
     */

    public void setDate(final String date) {
        this.date = date;
    }

    /**
     * set userName in dialog.
     *
     * @param userName
     */

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * @return new constructor dialog.
     */
    public static DeleteDataDialog newInstance() {
        return new DeleteDataDialog();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final @Nullable ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        initView(view);
        return view;
    }


    /**
     * set content of dialog.
     *
     * @param view
     */
    public void initView(final View view) {
        ButterKnife.bind(this, view);
        titleDialog.setText(title);
        dataDialog.setText(date);
        descriptionDialog.setText(userName);
        deleteButton.setText("USUŃ");
        backButton.setText("POWRÓT");

    }

    @OnClick(R.id.dialog_btn2)
    void delete() {
        dismiss();
    }

    @OnClick(R.id.dialog_btn1)
    void backToCalendar() {
        dismiss();
    }

}
