package com.wrotten.cursor_streaming_producer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RedisMessagePublisher {

    private final RedisTemplate<String, String> redisTemplate;

//    public void publishCoordinatesToStream(String streamName, String xPosition, String yPosition) {
//        HashMap<String, String> message = new HashMap<>();
//        message.put(key, value);
//        redisTemplate.opsForStream().add(streamName, message);
//    }

    public void publishToStream(String streamName, Map<String, String> message) {
        redisTemplate.opsForStream().add(streamName, message);
    }

}
