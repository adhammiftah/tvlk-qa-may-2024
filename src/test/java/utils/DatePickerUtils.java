package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePickerUtils {
    public static String addDaysToTodayDate(int days) {
        LocalDate currentDate = LocalDate.now();
        LocalDate newDate = currentDate.plusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        return newDate.format(formatter);
    }
}
