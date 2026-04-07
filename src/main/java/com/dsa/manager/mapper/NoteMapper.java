package com.dsa.manager.mapper;

import com.dsa.manager.dto.NoteRequest;
import com.dsa.manager.dto.NoteResponse;
import com.dsa.manager.entity.Note;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    @Mapping(target = "question", ignore = true)
    Note toEntity(NoteRequest request);

    @Mapping(target = "questionId", source = "question.id")
    NoteResponse toResponse(Note note);
}
