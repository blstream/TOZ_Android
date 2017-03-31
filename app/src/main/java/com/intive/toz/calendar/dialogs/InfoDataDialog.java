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
 * Created by mmate on 29.03.2017.
 */

public class InfoDataDialog extends DialogFragment {


    private String title;
    private String date;
    private String userName;

    public void setDate(String date) {
        this.date = date;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @BindView(R.id.data_dialog)
    TextView dataDialog;
    @BindView(R.id.title_dialog)
    TextView titleDialog;
    @BindView(R.id.description_dialog)
    TextView descriptionDialog;
    @BindView(R.id.dialog_btn1)
    Button button1Dialog;
    @BindView(R.id.dialog_btn2)
    Button backButton;


    public static InfoDataDialog newInstance() {
        return new InfoDataDialog();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        initView(view);
        return view;
    }

    public void initView(View view) {
        ButterKnife.bind(this, view);
        titleDialog.setText(title);
        dataDialog.setText(date);
        descriptionDialog.setText(userName);
        button1Dialog.setVisibility(View.GONE);
        backButton.setText("POWRÓT");
    }

    @OnClick(R.id.dialog_btn2)
    void backToCalendar() {
        dismiss();
    }


}
