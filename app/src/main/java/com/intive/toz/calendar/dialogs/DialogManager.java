package com.intive.toz.calendar.dialogs;

import android.widget.Button;

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
    public static InfoDataDialog infoDialog() {
        InfoDataDialog dialog = InfoDataDialog.newInstance();
        dialog.setTitle("Rano");
        dialog.setDate("Czwartek, 9marca ");
        dialog.setUserName("Magdalena Męczywora");
        return dialog;
    }

    /**
     * set dialog.
     *
     * @return savedialog
     */

    public static SaveDataDialog saveDialog() {
        SaveDataDialog dialog = SaveDataDialog.newInstance();
        dialog.setDate("");
        dialog.setTitle("");
        return SaveDataDialog.newInstance();
    }

    /**
     * set dialog.
     *
     * @return deletedialog
     */
    public static DeleteDataDialog deleteDialog() {
        DeleteDataDialog dialog = DeleteDataDialog.newInstance();
        dialog.setDate("");
        dialog.setUserName("");
        dialog.setTitle("");
        return DeleteDataDialog.newInstance();
    }




}
