package com.intive.toz.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *  Class for formatting date from miliseconds to (dd/mm/yyyy).
 */

public class DateFormatter {
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private Calendar calendar = Calendar.getInstance();


    /**
     * Convert date.
     * @param dateInMilliseconds date in miliseconds
     * @return date in dd/MM/yyyy format
     */
    public String convertToDate(final long dateInMilliseconds) {
        calendar.setTimeInMillis(dateInMilliseconds);
        return formatter.format(calendar.getTime());
    }
}
