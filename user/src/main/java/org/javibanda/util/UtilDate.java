package org.javibanda.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UtilDate {

    public static Date formateDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy");
        return (Date) formatter.parse(date);
    }

    public static int calculateAge(Date birthdate) {
        LocalDate birthdateLocal = new java.sql.Date(birthdate.getTime()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthdateLocal, currentDate);
        return period.getYears();
    }
}
