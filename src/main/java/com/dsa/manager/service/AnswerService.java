package com.dsa.manager.service;

import com.dsa.manager.dto.AnswerRequest;
import com.dsa.manager.dto.AnswerResponse;
import com.dsa.manager.entity.Answer;
import com.dsa.manager.entity.Question;
import com.dsa.manager.exception.ResourceNotFoundException;
import com.dsa.manager.mapper.AnswerMapper;
import com.dsa.manager.repository.AnswerRepository;
import com.dsa.manager.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final AnswerMapper answerMapper;

    public List<AnswerResponse> getAnswersByQuestion(Long questionId) {
        findActiveQuestion(questionId);
        return answerRepository.findByQuestionId(questionId).stream()
            .map(answerMapper::toResponse)
            .toList();
    }

    @Transactional
    public AnswerResponse addAnswer(Long questionId, AnswerRequest request) {
        Question question = findActiveQuestion(questionId);
        Answer answer = answerMapper.toEntity(request);
        answer.setQuestion(question);
        return answerMapper.toResponse(answerRepository.save(answer));
    }

    @Transactional
    public void deleteAnswer(Long answerId) {
        Answer answer = answerRepository.findById(answerId)
            .orElseThrow(() -> new ResourceNotFoundException("Answer", answerId));
        answerRepository.delete(answer);
    }

    private Question findActiveQuestion(Long id) {
        return questionRepository.findById(id)
            .filter(q -> !q.getDeleted())
            .orElseThrow(() -> new ResourceNotFoundException("Question", id));
    }
}
