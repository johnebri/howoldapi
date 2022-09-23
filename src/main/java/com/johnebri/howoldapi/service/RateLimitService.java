package com.johnebri.howoldapi.service;

import com.johnebri.howoldapi.model.Calls;
import com.johnebri.howoldapi.repository.CallsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by John on 23/09/2022
 */

@Service
public class RateLimitService {

    @Autowired
    private CallsRepository callsRepository;

    public void interceptCall(HttpServletRequest request) throws Exception {
        // check rate limit
        // get the ip address
        String ip = request.getRemoteAddr();

        // find ip address in db
        Calls calls = callsRepository.findByIp(ip);
        if(calls == null) {
            // first call, create call
            Calls call = new Calls();
            call.setIp(ip);
            call.setNoOfCalls(1);
            call.setFirstCall(LocalDateTime.now());
            call.setLastCall(LocalDateTime.now());
            callsRepository.save(call);
        } else {

            // update calls
            calls.setNoOfCalls(calls.getNoOfCalls() + 1);
            calls.setLastCall(LocalDateTime.now());
            callsRepository.save(calls);

            // check if limit has exceeded
            int noOfCalls = calls.getNoOfCalls();
            if(noOfCalls > 3) {
                LocalDateTime firstCall = calls.getFirstCall();
                LocalDateTime lastCall = calls.getLastCall();
                Long callsDiff = ChronoUnit.SECONDS.between(firstCall, lastCall);
                if(callsDiff < 1) {
                    throw new Exception("Call limit reached");
                } else {
                    // reset
                    calls.setNoOfCalls(0);
                    calls.setFirstCall(LocalDateTime.now());
                    calls.setLastCall(LocalDateTime.now());
                    callsRepository.save(calls);
                }
            }
        }
    }
}
