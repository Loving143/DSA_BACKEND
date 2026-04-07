package com.dsa.manager.mapper;

import com.dsa.manager.dto.QuestionRequest;
import com.dsa.manager.dto.QuestionResponse;
import com.dsa.manager.entity.Question;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {TagMapper.class})
public interface QuestionMapper {

    @Mapping(target = "topic", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Question toEntity(QuestionRequest request);

    @Mapping(target = "topicId", source = "topic.id")
    @Mapping(target = "topicName", source = "topic.name")
    QuestionResponse toResponse(Question question);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "topic", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    void updateEntity(QuestionRequest request, @MappingTarget Question question);
}
