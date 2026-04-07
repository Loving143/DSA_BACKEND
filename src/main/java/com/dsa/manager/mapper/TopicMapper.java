package com.dsa.manager.mapper;

import com.dsa.manager.dto.TopicRequest;
import com.dsa.manager.dto.TopicResponse;
import com.dsa.manager.entity.Topic;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    Topic toEntity(TopicRequest request);

    @Mapping(target = "questionCount", expression = "java(topic.getQuestions() != null ? (int) topic.getQuestions().stream().filter(q -> !q.getDeleted()).count() : 0)")
    TopicResponse toResponse(Topic topic);
}
