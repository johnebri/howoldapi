package com.johnebri.howoldapi.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by John on 30/08/2022
 */
public interface AgeCalculatorService {
    Long getAge(String dob, HttpServletRequest request) throws Exception;
}
