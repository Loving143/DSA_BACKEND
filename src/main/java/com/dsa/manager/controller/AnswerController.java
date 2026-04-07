package com.dsa.manager.controller;

import com.dsa.manager.dto.AnswerRequest;
import com.dsa.manager.dto.AnswerResponse;
import com.dsa.manager.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions/{questionId}/answers")
@RequiredArgsConstructor
@Tag(name = "Answers", description = "Answer management APIs")
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping
    @Operation(summary = "Get all answers for a question")
    public ResponseEntity<List<AnswerResponse>> getAnswers(@PathVariable Long questionId) {
        return ResponseEntity.ok(answerService.getAnswersByQuestion(questionId));
    }

    @PostMapping
    @Operation(summary = "Add an answer to a question")
    public ResponseEntity<AnswerResponse> addAnswer(
        @PathVariable Long questionId,
        @Valid @RequestBody AnswerRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(answerService.addAnswer(questionId, request));
    }

    @DeleteMapping("/{answerId}")
    @Operation(summary = "Delete an answer")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long questionId, @PathVariable Long answerId) {
        answerService.deleteAnswer(answerId);
        return ResponseEntity.noContent().build();
    }
}
