package com.wrotten.cursor_streaming_producer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/cursor")
public class CursorStreamingController {

    @PostMapping("/stream")
    public void stream(){

    }
}
