package com.ems.ems.Utils;

import java.util.Calendar;

/**
 * Utilities class for handling date functions.
 *
 * @author Lawrence
 */
public abstract class DateUtils {

    /**
     * Get the start of the current month in milliseconds.
     *
     * @return the start of the current month.
     */
    public static long getStartOfCurrentMonthInMillis() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        calendar.set(year, month, 0, 0, 0, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * Get the start of the current day in milliseconds.
     *
     * @return the start of the current day.
     */
    public static long getStartOfCurrentDayInMillis() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE - 1);

        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * Get the end of the current month in milliseconds.
     *
     * @return the end of the current month.
     */
    public static long getEndOfCurrentMonthInMillis() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH + 1);

        calendar.set(year, month, 0, 0, 0, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * Get the end of the current month in milliseconds.
     *
     * @return the end of the current month.
     */
    public static long getEndOfCurrentDayInMillis() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);

        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * Converts milliseconds to hours.
     *
     * @param milli - The millisecond to covert.
     * @return the number of hours.
     */
    public static int convertMillitoHours(long milli) {
        return (int)(milli / (1000*60*60));
    }
}

