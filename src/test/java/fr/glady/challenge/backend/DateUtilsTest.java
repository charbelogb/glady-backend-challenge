package fr.glady.challenge.backend;

import fr.glady.challenge.backend.utils.DateUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

public class DateUtilsTest {

    @Test
    public void getEndOfNextFebruary_OK() {
        LocalDate expectedDate = LocalDate.of(2021, Month.FEBRUARY, 28);
        LocalDate actualDate = DateUtils.getEndOfNextFebruary(LocalDate.of(2020, Month.JANUARY, 1));
        assertEquals(expectedDate, actualDate);
    }

}
