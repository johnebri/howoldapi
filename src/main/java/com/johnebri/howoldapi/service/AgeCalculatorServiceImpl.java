package com.johnebri.howoldapi.service;

import com.johnebri.howoldapi.exception.IncorrectDateException;
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
    public Long getAge(String dob) throws Exception {

        LocalDateTime now = LocalDateTime.now();
        String dobStr = dob;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dobDate = LocalDateTime.parse(dobStr, formatter);

        // check if the date of birth is greater than the current date and time
        Long secondsDiff = ChronoUnit.SECONDS.between(dobDate, now);
        if(secondsDiff < 0) {
            throw new IncorrectDateException("Date of birth should not be greater than current date/time");
        }

        Long diff = ChronoUnit.YEARS.between(dobDate, now);
        return diff;
    }
}
