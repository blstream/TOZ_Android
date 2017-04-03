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

public class SaveDataDialog extends DialogFragment implements OnDialogResultListener {


    String title;
    String date;
    private boolean isSaved = false;


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @BindView(R.id.data_dialog)
    TextView dataDialog;
    @BindView(R.id.title_dialog)
    TextView titleDialog;
    @BindView(R.id.description_dialog)
    TextView descriptionDialog;
    @BindView(R.id.dialog_btn1)
    Button cancelBtn;
    @BindView(R.id.dialog_btn2)
    public Button saveBtn;

    public static SaveDataDialog newInstance() {
        return new SaveDataDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        initView(view);

        return view;
    }

    
    @OnClick(R.id.dialog_btn2)
    public void saveDate() {
        isSaved = true;
        dismiss();
        onPositiveResult();
    }

    @OnClick(R.id.dialog_btn1)
   public void cancelSaveDate() {
        dismiss();
    }


    public void initView(View view) {
        ButterKnife.bind(this, view);
        titleDialog.setText(title);
        dataDialog.setText(date);
        descriptionDialog.setText("Czy na pewno chcesz się zapisać na ten termin?");
        cancelBtn.setText("ANULUJ");
        saveBtn.setText("ZAPISZ");
    }


    @Override
    public boolean onPositiveResult() {
        return isSaved;
    }
}
