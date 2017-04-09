package com.intive.toz.common.view.calendar.presenter;


import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.common.view.calendar.ButtonsMvp;
import com.intive.toz.common.view.calendar.dialogs.DialogFactory;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * mvp presenter for calendar activity.
 */

public class ButtonsPresenter extends MvpBasePresenter<ButtonsMvp.ButtonsView> implements ButtonsMvp.Presenter {

    List<Integer> stateButtons = new ArrayList<Integer>(Arrays.asList(new Integer[]{1, 2, 0, 0, 2, 2, 1, 1, 1, 0, 1, 2, 0, 1}));
    List<Integer> morning = new ArrayList<>();
    List<Integer> afternoon = new ArrayList<>();

    @Override
    public void loadData() {
        for (int i = 0; i < stateButtons.size() / 2; i++) {
            morning.add(stateButtons.get(i));
        }
        for (int i = stateButtons.size() / 2; i < stateButtons.size(); i++) {
            afternoon.add(stateButtons.get(i));
        }
        getView().setButtons(afternoon, morning);
    }


    @Override
    public void checkDate(final int position, final Date day) {
        DialogFactory.day = day;
        DialogFactory.position = position;
        switch (stateButtons.get(position)) {
            case 1:
                getView().showDialog(DialogFactory.infoDialog("Ktoś Inny"));
                break;
            case 2:
                getView().showDialog(DialogFactory.deleteDialog("JA"));
                break;
            default:
                getView().showDialog(DialogFactory.saveDialog());
                break;
        }
    }

    @Override
    public void setDate(final int position, final boolean save, final boolean delete) {
        if (save) {
            stateButtons.set(position, 2);
        }
        if (delete) {
            stateButtons.set(position, 0);
        }
        loadData();
    }
}
