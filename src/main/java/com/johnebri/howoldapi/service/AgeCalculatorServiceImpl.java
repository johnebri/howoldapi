package com.johnebri.howoldapi.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Created by John on 31/08/2022
 */

@Service
public class AgeCalculatorServiceImpl implements AgeCalculatorService {

    @Override
    public Long getAge(String dob) {

        LocalDateTime now = LocalDateTime.now();
        String dobStr = dob;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dobDate = LocalDateTime.parse(dobStr, formatter);

        Long diff = ChronoUnit.YEARS.between(dobDate, now);

        return diff;
    }
}
