package com.dsa.manager.controller;

import com.dsa.manager.dto.NoteRequest;
import com.dsa.manager.dto.NoteResponse;
import com.dsa.manager.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions/{questionId}/notes")
@RequiredArgsConstructor
@Tag(name = "Notes", description = "Note management APIs")
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    @Operation(summary = "Get all notes for a question")
    public ResponseEntity<List<NoteResponse>> getNotes(@PathVariable Long questionId) {
        return ResponseEntity.ok(noteService.getNotesByQuestion(questionId));
    }

    @PostMapping
    @Operation(summary = "Add a note to a question")
    public ResponseEntity<NoteResponse> addNote(
        @PathVariable Long questionId,
        @Valid @RequestBody NoteRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.addNote(questionId, request));
    }

    @DeleteMapping("/{noteId}")
    @Operation(summary = "Delete a note")
    public ResponseEntity<Void> deleteNote(@PathVariable Long questionId, @PathVariable Long noteId) {
        noteService.deleteNote(noteId);
        return ResponseEntity.noContent().build();
    }
}
