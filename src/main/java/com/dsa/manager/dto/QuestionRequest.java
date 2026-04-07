package com.dsa.manager.dto;

import com.dsa.manager.entity.enums.Difficulty;
import com.dsa.manager.entity.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class QuestionRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Difficulty is required")
    private Difficulty difficulty;

    private Status status;

    private Boolean isFavorite;

    @NotNull(message = "Topic ID is required")
    private Long topicId;

    private Set<Long> tagIds;
}
