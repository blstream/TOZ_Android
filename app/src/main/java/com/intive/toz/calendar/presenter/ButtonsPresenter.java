package com.intive.toz.calendar.presenter;

import android.util.Log;
import android.view.View;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.calendar.ButtonsMvp;
import com.intive.toz.calendar.dialogs.DialogManager;
import com.intive.toz.calendar.dialogs.InfoDataDialog;
import com.intive.toz.calendar.dialogs.SaveDataDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * mvp presenter for calendar activity.
 */

public class ButtonsPresenter extends MvpBasePresenter<ButtonsMvp.ButtonsView> implements ButtonsMvp.Presenter {

    List<Integer> morning = new ArrayList<>();
    List<Integer> afternoon = new ArrayList<>();

    @Override
    public void loadData() {

        morning.add(3);
        morning.add(1);
        morning.add(3);
        morning.add(2);
        morning.add(1);
        morning.add(2);
        morning.add(3);

        afternoon.add(3);
        afternoon.add(1);
        afternoon.add(2);
        afternoon.add(1);
        afternoon.add(3);
        afternoon.add(2);
        afternoon.add(1);

        getView().setButtons(afternoon, morning);
    }




    @Override
    public void checkButton(final int position,final View view, final boolean isMorning) {
        int resoult;
        if (isMorning) {
            resoult = morning.get(position);
        } else { resoult = afternoon.get(position);}

        switch (resoult) {
            case 1:

                getView().showDialog(DialogManager.infoDialog("Rano", "Czwartek 9 marca ", "Jan Kowal" ));
                break;
            case 2:

                getView().showDialog(DialogManager.deleteDialog("Popołudniu", "Czwartek 9 marca", "Michał Matera"));
                break;
            default:
                getView().showDialog(DialogManager.saveDialog("Popołudniu", "Czwartek 9 marca"));
                break;

        }


    }
}
