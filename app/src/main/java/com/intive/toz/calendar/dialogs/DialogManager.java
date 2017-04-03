package com.intive.toz.calendar.dialogs;


import android.view.View;

/**
 * class to create type of dialogs.
 */

public final class DialogManager {

    /**
     * class constructor.
     */
    private DialogManager() {
    }

    /**
     * set dialog with information parametrs.
     *
     * @return infodialog
     */
    public static InfoDataDialog infoDialog(final String title, final String date, final String name) {
        InfoDataDialog dialog = InfoDataDialog.newInstance();
        dialog.setTitle(title);
        dialog.setDate(date);
        dialog.setUserName(name);

        return dialog;
    }

    /**
     * set dialog.
     *
     * @return savedialog
     */

    public static SaveDataDialog saveDialog(final String title, final String date) {
        SaveDataDialog dialog = SaveDataDialog.newInstance();
        dialog.setTitle(title);
        dialog.setDate(date);
        return dialog;
    }

    /**
     * set dialog.
     *
     * @return deletedialog
     */
    public static DeleteDataDialog deleteDialog(final String title, final String date, final String name) {
        DeleteDataDialog dialog = DeleteDataDialog.newInstance();
        dialog.setDate(date);
        dialog.setUserName(name);
        dialog.setTitle(title);
        return dialog;
    }


}
