package com.dsa.manager.mapper;

import com.dsa.manager.dto.NoteRequest;
import com.dsa.manager.dto.NoteResponse;
import com.dsa.manager.entity.Note;
import com.dsa.manager.entity.Question;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-07T16:19:35+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class NoteMapperImpl implements NoteMapper {

    @Override
    public Note toEntity(NoteRequest request) {
        if ( request == null ) {
            return null;
        }

        Note.NoteBuilder note = Note.builder();

        note.content( request.getContent() );

        return note.build();
    }

    @Override
    public NoteResponse toResponse(Note note) {
        if ( note == null ) {
            return null;
        }

        NoteResponse noteResponse = new NoteResponse();

        noteResponse.setQuestionId( noteQuestionId( note ) );
        noteResponse.setId( note.getId() );
        noteResponse.setContent( note.getContent() );
        noteResponse.setCreatedAt( note.getCreatedAt() );
        noteResponse.setUpdatedAt( note.getUpdatedAt() );

        return noteResponse;
    }

    private Long noteQuestionId(Note note) {
        if ( note == null ) {
            return null;
        }
        Question question = note.getQuestion();
        if ( question == null ) {
            return null;
        }
        Long id = question.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
