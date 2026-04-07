package com.dsa.manager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TopicRequest {

    @NotBlank(message = "Topic name is required")
    private String name;
}
