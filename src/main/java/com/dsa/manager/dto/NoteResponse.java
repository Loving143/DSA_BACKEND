package com.dsa.manager.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoteResponse {
    private Long id;
    private String content;
    private Long questionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
