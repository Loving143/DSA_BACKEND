package com.dsa.manager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnswerRequest {

    @NotBlank(message = "Code is required")
    private String code;

    private String explanation;
}
