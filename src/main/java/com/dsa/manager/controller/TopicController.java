package com.dsa.manager.controller;

import com.dsa.manager.dto.QuestionResponse;
import com.dsa.manager.dto.TopicRequest;
import com.dsa.manager.dto.TopicResponse;
import com.dsa.manager.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
@Tag(name = "Topics", description = "DSA Topic management APIs")
public class TopicController {

    private final TopicService topicService;

    @GetMapping
    @Operation(summary = "Get all topics")
    public ResponseEntity<List<TopicResponse>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get topic by ID")
    public ResponseEntity<TopicResponse> getTopicById(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new topic")
    public ResponseEntity<TopicResponse> createTopic(@Valid @RequestBody TopicRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.createTopic(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a topic")
    public ResponseEntity<TopicResponse> updateTopic(@PathVariable Long id, @Valid @RequestBody TopicRequest request) {
        return ResponseEntity.ok(topicService.updateTopic(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a topic")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/questions")
    @Operation(summary = "Get all questions for a topic")
    public ResponseEntity<List<QuestionResponse>> getQuestionsByTopic(
        @PathVariable Long id,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(topicService.getQuestionsByTopic(id, page, size));
    }
}
