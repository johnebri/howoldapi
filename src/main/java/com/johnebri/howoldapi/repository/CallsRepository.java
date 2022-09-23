package com.johnebri.howoldapi.repository;

import com.johnebri.howoldapi.model.Calls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


/**
 * Created by John on 23/09/2022
 */

@Repository
public class CallsRepository {

    public static final String HASH_KEY = "calls2";

    @Autowired
    private RedisTemplate template;

    public Calls save(Calls call) {
        template.opsForHash().put(HASH_KEY, call.getIp(), call);
        return call;
    }

    public Calls findByIp(String ip) {
        return (Calls) template.opsForHash().get(HASH_KEY, ip);
    }

}
