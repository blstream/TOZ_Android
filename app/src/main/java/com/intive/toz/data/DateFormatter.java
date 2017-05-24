package com.intive.toz.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *  Class for formatting date from miliseconds to (dd/mm/yyyy).
 */

public class DateFormatter {
    private DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    private Calendar calendar = Calendar.getInstance();
    private static final int MINUS_SIX_MONTHS = -6;


    /**
     * Convert date.
     * @param dateInMilliseconds date in miliseconds
     * @return date in dd/MM/yyyy format
     */
    public String convertToDate(final long dateInMilliseconds) {
        calendar.setTimeInMillis(dateInMilliseconds);
        return formatter.format(calendar.getTime());
    }

    /**
     *
     * @param createdDate news create date in milliseconds
     * @return true if news create date is earlier than six months and false if not.
     */
    public boolean isLessThanSixMonth(final long createdDate) {
        Calendar createNewsDate = new GregorianCalendar();
        createNewsDate.setTimeInMillis(createdDate);
        calendar.add(Calendar.MONTH, MINUS_SIX_MONTHS);
        return createNewsDate.after(calendar);
    }
}
