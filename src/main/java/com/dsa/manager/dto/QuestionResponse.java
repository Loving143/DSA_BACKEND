package com.dsa.manager.dto;

import com.dsa.manager.entity.enums.Difficulty;
import com.dsa.manager.entity.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class QuestionResponse {
    private Long id;
    private String title;
    private String description;
    private Difficulty difficulty;
    private Status status;
    private Boolean isFavorite;
    private Long topicId;
    private String topicName;
    private Set<TagResponse> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
