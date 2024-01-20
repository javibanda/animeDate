package org.javibanda.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class UtilDate {

    public static Date formateDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy");
        return formatter.parse(date);
    }

    public static int calculateAge(Date birthdate) {
        LocalDate birthdateLocal = new java.sql.Date(birthdate.getTime()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthdateLocal, currentDate);
        return period.getYears();
    }
}
