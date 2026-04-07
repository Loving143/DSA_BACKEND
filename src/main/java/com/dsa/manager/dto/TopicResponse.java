package com.dsa.manager.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TopicResponse {
    private Long id;
    private String name;
    private int questionCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
