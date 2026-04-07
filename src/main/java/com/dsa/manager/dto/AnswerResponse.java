package com.dsa.manager.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnswerResponse {
    private Long id;
    private String code;
    private String explanation;
    private Long questionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
