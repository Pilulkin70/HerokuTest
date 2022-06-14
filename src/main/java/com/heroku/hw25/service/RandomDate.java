package com.heroku.hw25.service;

import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

@ToString
public class RandomDate {
    private final LocalDate minDate;
    private final LocalDate maxDate;
    private final Random random;

    public RandomDate(LocalDate minDate, LocalDate maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
        this.random = new Random();
    }

    public Date nextDate() {
        int minDay = (int) minDate.toEpochDay();
        int maxDay = (int) maxDate.toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        return Date.valueOf(LocalDate.ofEpochDay(randomDay));
    }
}
