package fr.glady.challenge.backend.utils;

import java.time.LocalDate;
import java.time.Month;

public class DateUtils {

    /**
     * Determines the date of the end of February of the year following the given year
     * @param date
     * @return
     */
    public static LocalDate getEndOfNextFebruary(LocalDate date) {
        // next year february month start
        LocalDate nextFebruary = LocalDate.of(date.getYear()+1, Month.FEBRUARY, 1);
        return LocalDate.of(nextFebruary.getYear(), nextFebruary.getMonth(), nextFebruary.getMonth().length(nextFebruary.isLeapYear()));
    }

}
