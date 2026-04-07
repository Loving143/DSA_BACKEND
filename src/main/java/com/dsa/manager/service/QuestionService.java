package com.dsa.manager.service;

import com.dsa.manager.dto.PagedResponse;
import com.dsa.manager.dto.QuestionRequest;
import com.dsa.manager.dto.QuestionResponse;
import com.dsa.manager.entity.Question;
import com.dsa.manager.entity.Tag;
import com.dsa.manager.entity.Topic;
import com.dsa.manager.entity.enums.Difficulty;
import com.dsa.manager.exception.ResourceNotFoundException;
import com.dsa.manager.mapper.QuestionMapper;
import com.dsa.manager.repository.QuestionRepository;
import com.dsa.manager.repository.TagRepository;
import com.dsa.manager.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TopicRepository topicRepository;
    private final TagRepository tagRepository;
    private final QuestionMapper questionMapper;

    public PagedResponse<QuestionResponse> getAllQuestions(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Question> result = questionRepository.findByDeletedFalse(pageable);
        return toPagedResponse(result);
    }

    public QuestionResponse getQuestionById(Long id) {
        return questionMapper.toResponse(findActiveQuestion(id));
    }

    @Transactional
    public QuestionResponse createQuestion(QuestionRequest request) {
        Topic topic = topicRepository.findById(request.getTopicId())
            .orElseThrow(() -> new ResourceNotFoundException("Topic", request.getTopicId()));

        Question question = questionMapper.toEntity(request);
        question.setTopic(topic);
        question.setTags(resolveTags(request.getTagIds()));

        return questionMapper.toResponse(questionRepository.save(question));
    }

    @Transactional
    public QuestionResponse updateQuestion(Long id, QuestionRequest request) {
        Question question = findActiveQuestion(id);

        if (request.getTopicId() != null) {
            Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() -> new ResourceNotFoundException("Topic", request.getTopicId()));
            question.setTopic(topic);
        }

        questionMapper.updateEntity(request, question);

        if (request.getTagIds() != null) {
            question.setTags(resolveTags(request.getTagIds()));
        }

        return questionMapper.toResponse(questionRepository.save(question));
    }

    @Transactional
    public void deleteQuestion(Long id) {
        Question question = findActiveQuestion(id);
        question.setDeleted(true); // soft delete
        questionRepository.save(question);
    }

    public PagedResponse<QuestionResponse> searchQuestions(
        Long topicId, Difficulty difficulty, String keyword, int page, int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Question> result = questionRepository.searchQuestions(
            topicId,
            difficulty,
            StringUtils.hasText(keyword) ? keyword : null,
            pageable
        );
        return toPagedResponse(result);
    }

    private Question findActiveQuestion(Long id) {
        return questionRepository.findById(id)
            .filter(q -> !q.getDeleted())
            .orElseThrow(() -> new ResourceNotFoundException("Question", id));
    }

    private Set<Tag> resolveTags(Set<Long> tagIds) {
        if (tagIds == null || tagIds.isEmpty()) return new HashSet<>();
        Set<Tag> tags = new HashSet<>();
        for (Long tagId : tagIds) {
            Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", tagId));
            tags.add(tag);
        }
        return tags;
    }

    private PagedResponse<QuestionResponse> toPagedResponse(Page<Question> page) {
        return new PagedResponse<>(
            page.getContent().stream().map(questionMapper::toResponse).toList(),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements(),
            page.getTotalPages(),
            page.isLast()
        );
    }
}
