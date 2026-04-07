package com.dsa.manager.controller;

import com.dsa.manager.dto.PagedResponse;
import com.dsa.manager.dto.QuestionRequest;
import com.dsa.manager.dto.QuestionResponse;
import com.dsa.manager.entity.enums.Difficulty;
import com.dsa.manager.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
@Tag(name = "Questions", description = "DSA Question management APIs")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    @Operation(summary = "Get all questions with pagination and sorting")
    public ResponseEntity<PagedResponse<QuestionResponse>> getAllQuestions(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "createdAt") String sortBy,
        @RequestParam(defaultValue = "desc") String sortDir
    ) {
        return ResponseEntity.ok(questionService.getAllQuestions(page, size, sortBy, sortDir));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get question by ID")
    public ResponseEntity<QuestionResponse> getQuestionById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new question")
    public ResponseEntity<QuestionResponse> createQuestion(@Valid @RequestBody QuestionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.createQuestion(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a question")
    public ResponseEntity<QuestionResponse> updateQuestion(
        @PathVariable Long id,
        @Valid @RequestBody QuestionRequest request
    ) {
        return ResponseEntity.ok(questionService.updateQuestion(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Soft delete a question")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search questions by topic, difficulty, or keyword")
    public ResponseEntity<PagedResponse<QuestionResponse>> searchQuestions(
        @RequestParam(required = false) Long topic,
        @RequestParam(required = false) Difficulty difficulty,
        @RequestParam(required = false) String keyword,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(questionService.searchQuestions(topic, difficulty, keyword, page, size));
    }
}
