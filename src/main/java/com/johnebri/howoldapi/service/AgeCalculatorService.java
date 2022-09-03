package com.johnebri.howoldapi.service;

/**
 * Created by John on 30/08/2022
 */
public interface AgeCalculatorService {
    Long getAge(String dob) throws Exception;
}
