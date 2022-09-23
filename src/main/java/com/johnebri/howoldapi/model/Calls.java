package com.johnebri.howoldapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by John on 23/09/2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("calls2")
public class Calls implements Serializable {
    private String ip;
    private int noOfCalls;
    private LocalDateTime firstCall;
    private LocalDateTime lastCall;
}
