package com.wrotten.cursor_streaming_producer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CursorPositionModel {

    private int xPosition;
    private int yPosition;
    private String action;
}
