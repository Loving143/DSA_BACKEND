package com.dsa.manager.service;

import com.dsa.manager.dto.QuestionResponse;
import com.dsa.manager.dto.TopicRequest;
import com.dsa.manager.dto.TopicResponse;
import com.dsa.manager.entity.Topic;
import com.dsa.manager.exception.DuplicateResourceException;
import com.dsa.manager.exception.ResourceNotFoundException;
import com.dsa.manager.mapper.QuestionMapper;
import com.dsa.manager.mapper.TopicMapper;
import com.dsa.manager.repository.QuestionRepository;
import com.dsa.manager.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TopicService {

    private final TopicRepository topicRepository;
    private final QuestionRepository questionRepository;
    private final TopicMapper topicMapper;
    private final QuestionMapper questionMapper;

    public List<TopicResponse> getAllTopics() {
        return topicRepository.findAll().stream()
            .map(topicMapper::toResponse)
            .toList();
    }

    public TopicResponse getTopicById(Long id) {
        return topicMapper.toResponse(findTopicById(id));
    }

    @Transactional
    public TopicResponse createTopic(TopicRequest request) {
        if (topicRepository.existsByNameIgnoreCase(request.getName())) {
            throw new DuplicateResourceException("Topic already exists with name: " + request.getName());
        }
        Topic topic = topicMapper.toEntity(request);
        return topicMapper.toResponse(topicRepository.save(topic));
    }

    @Transactional
    public TopicResponse updateTopic(Long id, TopicRequest request) {
        Topic topic = findTopicById(id);
        if (!topic.getName().equalsIgnoreCase(request.getName())
            && topicRepository.existsByNameIgnoreCase(request.getName())) {
            throw new DuplicateResourceException("Topic already exists with name: " + request.getName());
        }
        topic.setName(request.getName());
        return topicMapper.toResponse(topicRepository.save(topic));
    }

    @Transactional
    public void deleteTopic(Long id) {
        Topic topic = findTopicById(id);
        topicRepository.delete(topic);
    }

    public List<QuestionResponse> getQuestionsByTopic(Long topicId, int page, int size) {
        findTopicById(topicId);
        return questionRepository.findByTopicIdAndDeletedFalse(topicId, PageRequest.of(page, size))
            .stream()
            .map(questionMapper::toResponse)
            .toList();
    }

    private Topic findTopicById(Long id) {
        return topicRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Topic", id));
    }
}
