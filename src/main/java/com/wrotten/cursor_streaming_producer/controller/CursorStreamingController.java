package com.wrotten.cursor_streaming_producer.controller;

import com.wrotten.cursor_streaming_producer.model.CursorPositionModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursor")
public class CursorStreamingController {

    @PostMapping("/stream")
    public void stream(@RequestBody CursorPositionModel cursorPosition){
        System.out.println(cursorPosition.getXPosition());
        System.out.println(cursorPosition.getYPosition());
    }
}
