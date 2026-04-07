package com.dsa.manager.service;

import com.dsa.manager.dto.NoteRequest;
import com.dsa.manager.dto.NoteResponse;
import com.dsa.manager.entity.Note;
import com.dsa.manager.entity.Question;
import com.dsa.manager.exception.ResourceNotFoundException;
import com.dsa.manager.mapper.NoteMapper;
import com.dsa.manager.repository.NoteRepository;
import com.dsa.manager.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoteService {

    private final NoteRepository noteRepository;
    private final QuestionRepository questionRepository;
    private final NoteMapper noteMapper;

    public List<NoteResponse> getNotesByQuestion(Long questionId) {
        findActiveQuestion(questionId);
        return noteRepository.findByQuestionId(questionId).stream()
            .map(noteMapper::toResponse)
            .toList();
    }

    @Transactional
    public NoteResponse addNote(Long questionId, NoteRequest request) {
        Question question = findActiveQuestion(questionId);
        Note note = noteMapper.toEntity(request);
        note.setQuestion(question);
        return noteMapper.toResponse(noteRepository.save(note));
    }

    @Transactional
    public void deleteNote(Long noteId) {
        Note note = noteRepository.findById(noteId)
            .orElseThrow(() -> new ResourceNotFoundException("Note", noteId));
        noteRepository.delete(note);
    }

    private Question findActiveQuestion(Long id) {
        return questionRepository.findById(id)
            .filter(q -> !q.getDeleted())
            .orElseThrow(() -> new ResourceNotFoundException("Question", id));
    }
}
