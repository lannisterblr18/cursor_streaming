package com.wrotten.cursor_streaming_producer.service;

import com.wrotten.cursor_streaming_producer.model.CursorPositionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CursorStreamingService {

    private final RedisMessagePublisher redisMessagePublisher;

    public void sendMessageToStream(CursorPositionModel cursorPositionModel) {
        Map<String, String> messageBody = new HashMap<>();
        messageBody.put( "xPosition", String.valueOf(cursorPositionModel.getXPosition()));
        messageBody.put( "yPosition", String.valueOf(cursorPositionModel.getYPosition()));
        messageBody.put("action", cursorPositionModel.getAction());
        redisMessagePublisher.publishToStream("cursorPositions", messageBody);
    }
}
