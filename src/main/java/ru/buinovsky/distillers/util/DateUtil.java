package ru.buinovsky.distillers.util;

import java.time.LocalDate;
import java.util.Objects;

public class DateUtil {

    public static LocalDate checkStartDate(LocalDate startDate) {
        return Objects.requireNonNullElse(startDate, LocalDate.of(2000, 1, 1));
    }

    public static LocalDate checkEndDate(LocalDate endDate) {
        return Objects.requireNonNullElse(endDate, LocalDate.of(2050, 1, 1));
    }
}
