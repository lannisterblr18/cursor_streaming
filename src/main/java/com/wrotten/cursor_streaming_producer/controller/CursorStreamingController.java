package com.wrotten.cursor_streaming_producer.controller;

import com.wrotten.cursor_streaming_producer.model.CursorPositionModel;
import com.wrotten.cursor_streaming_producer.service.CursorStreamingService;
import com.wrotten.cursor_streaming_producer.service.RedisMessagePublisher;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.wrotten.cursor_streaming_producer.service.CursorStreamingService;
import org.springframework.web.util.HtmlUtils;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/cursor")
public class CursorStreamingController {

    private final CursorStreamingService cursorStreamingService;

//    @PostMapping("/stream")
//    public void stream(@RequestBody CursorPositionModel cursorPosition){
//        cursorStreamingService.sendMessageToStream(cursorPosition);
////        System.out.println(cursorPosition.getXPosition());
////        System.out.println(cursorPosition.getYPosition());
////        RedisClient redisClient = RedisClient.create("redis://localhost:6379"); // change to reflect your environment
////        StatefulRedisConnection<String, String> connection = redisClient.connect();
////        RedisCommands<String, String> syncCommands = connection.sync();
////        redisMessagePublisher.publishMessage("cursorPositions", "xPosition, yPosition", c);
//
////        String messageId = syncCommands.xadd(
////                "cursorPositions",
////                messageBody);
//
////        System.out.println( String.format("Message %s : %s posted", messageId, messageBody) );
//
////        connection.close();
////        redisClient.shutdown();
//    }

    @MessageMapping("/stream-test")
    public void streamTest(CursorPositionModel cursorPositionModel) throws InterruptedException {
//        Thread.sleep(1000);
        cursorStreamingService.sendMessageToStream(cursorPositionModel);
    }
}
