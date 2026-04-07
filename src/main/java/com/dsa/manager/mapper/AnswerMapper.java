package com.dsa.manager.mapper;

import com.dsa.manager.dto.AnswerRequest;
import com.dsa.manager.dto.AnswerResponse;
import com.dsa.manager.entity.Answer;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    @Mapping(target = "question", ignore = true)
    Answer toEntity(AnswerRequest request);

    @Mapping(target = "questionId", source = "question.id")
    AnswerResponse toResponse(Answer answer);
}
