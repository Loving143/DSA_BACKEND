package com.dsa.manager.mapper;

import com.dsa.manager.dto.NoteRequest;
import com.dsa.manager.dto.NoteResponse;
import com.dsa.manager.entity.Note;
import com.dsa.manager.entity.Question;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2026-07-31T11:33:07+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
=======
    date = "2026-06-06T22:26:32+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.17 (Microsoft)"
>>>>>>> 3cba9366fc53532357e2ab340cd9910a1379d4df
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
