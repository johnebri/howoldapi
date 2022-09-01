package com.johnebri.howoldapi.controller;

import com.johnebri.howoldapi.dto.BaseResponse;
import com.johnebri.howoldapi.service.AgeCalculatorService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by John on 30/08/2022
 */

@RestController
@RequestMapping("/api/v1")
public class AgeCalculationController {

    private final AgeCalculatorService ageCalculator;

    public AgeCalculationController(AgeCalculatorService ageCalculator) {
        this.ageCalculator = ageCalculator;
    }

    @GetMapping("/howold")
    @RateLimiter(name="how_old_api", fallbackMethod = "rateLimiterFallback")
    public ResponseEntity<BaseResponse> howOld(@RequestParam String dob) {
        Long age = ageCalculator.getAge(dob);
        BaseResponse br = new BaseResponse();
        br.setSuccess(true);
        br.setResponseMessage("You are " +  age + " years old");
        return new ResponseEntity<>(br, HttpStatus.OK);
    }

    public ResponseEntity<BaseResponse> rateLimiterFallback(Exception e) {
        BaseResponse br = new BaseResponse();
        br.setSuccess(false);
        br.setResponseMessage("The service does not permit further calls");
        return new ResponseEntity<>(br, HttpStatus.TOO_MANY_REQUESTS);
    }
}
