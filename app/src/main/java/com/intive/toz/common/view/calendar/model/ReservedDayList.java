package com.intive.toz.common.view.calendar.model;

import android.text.format.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by mmate on 09.04.2017.
 */

public final class ReservedDayList {

    public static List<ReservedDay> stateBtn = new ArrayList<>();
    public static boolean fillArray;

    /**
     * The constant FIRST_WEEK.
     */
    public static final int FIRST_WEEK = 7;

    /**
     * The constant SECOND_WEEK.
     */
    public static final int SECOND_WEEK = 14;

    /**
     * The constant THIRD_WEEK.
     */
    public static final int THIRD_WEEK = 21;

    /**
     * Base constructor.
     */
    private ReservedDayList() {
    }


    /**
     * Get new instance of day list.
     *
     * @param week the week
     * @return List of Reseved Day
     */
    public static List<ReservedDay> newInstance(final int week) {
        List<ReservedDay> array = new ArrayList<>();
        switch (week) {
            case 0:
                for (int i = 0; i < FIRST_WEEK; i++) {
                    array.add(stateBtn.get(i));
                }
                break;
            case 1:

                for (int i = FIRST_WEEK; i < SECOND_WEEK; i++) {
                    array.add(stateBtn.get(i));
                }
                break;
            case 2:
                for (int i = SECOND_WEEK; i < THIRD_WEEK; i++) {
                    array.add(stateBtn.get(i));
                }
                break;
            default:
                break;
        }
        return array;
    }

    /**
     * Set date buttons.
     *
     * @param dates the dates
     */
    public static void setDateButtons(final List<Date> dates) {

        if (!ReservedDayList.fillArray) {
            for (int j = 0; j < dates.size(); j++) {
                Date day = dates.get(j);
                ReservedDay reservedDay = new ReservedDay();
                String date = DateFormat.format("dd", day).toString()
                        + DateFormat.format("MM", day).toString()
                        + DateFormat.format("yy", day);
                reservedDay.setDate(date);
                ReservedDayList.stateBtn.add(reservedDay);
            }

            for (ReservedDay r : stateBtn) {
                Random random = new Random();
                final int state = 3;
                int rand1 = random.nextInt(state);
                int rand2 = random.nextInt(state);
                r.setStateAfternoon(rand1);
                r.setStateMorning(rand2);
            }

            ReservedDayList.fillArray = true;
        }

    }
}
