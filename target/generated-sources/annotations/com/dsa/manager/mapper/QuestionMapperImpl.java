package com.dsa.manager.mapper;

import com.dsa.manager.dto.QuestionRequest;
import com.dsa.manager.dto.QuestionResponse;
import com.dsa.manager.dto.TagResponse;
import com.dsa.manager.entity.Question;
import com.dsa.manager.entity.Tag;
import com.dsa.manager.entity.Topic;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-31T11:33:07+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Question toEntity(QuestionRequest request) {
        if ( request == null ) {
            return null;
        }

        Question.QuestionBuilder question = Question.builder();

        question.title( request.getTitle() );
        question.description( request.getDescription() );
        question.difficulty( request.getDifficulty() );
        question.status( request.getStatus() );
        question.isFavorite( request.getIsFavorite() );

        return question.build();
    }

    @Override
    public QuestionResponse toResponse(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionResponse questionResponse = new QuestionResponse();

        questionResponse.setTopicId( questionTopicId( question ) );
        questionResponse.setTopicName( questionTopicName( question ) );
        questionResponse.setId( question.getId() );
        questionResponse.setTitle( question.getTitle() );
        questionResponse.setDescription( question.getDescription() );
        questionResponse.setDifficulty( question.getDifficulty() );
        questionResponse.setStatus( question.getStatus() );
        questionResponse.setIsFavorite( question.getIsFavorite() );
        questionResponse.setTags( tagSetToTagResponseSet( question.getTags() ) );
        questionResponse.setCreatedAt( question.getCreatedAt() );
        questionResponse.setUpdatedAt( question.getUpdatedAt() );

        return questionResponse;
    }

    @Override
    public void updateEntity(QuestionRequest request, Question question) {
        if ( request == null ) {
            return;
        }

        if ( request.getTitle() != null ) {
            question.setTitle( request.getTitle() );
        }
        if ( request.getDescription() != null ) {
            question.setDescription( request.getDescription() );
        }
        if ( request.getDifficulty() != null ) {
            question.setDifficulty( request.getDifficulty() );
        }
        if ( request.getStatus() != null ) {
            question.setStatus( request.getStatus() );
        }
        if ( request.getIsFavorite() != null ) {
            question.setIsFavorite( request.getIsFavorite() );
        }
    }

    private Long questionTopicId(Question question) {
        if ( question == null ) {
            return null;
        }
        Topic topic = question.getTopic();
        if ( topic == null ) {
            return null;
        }
        Long id = topic.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String questionTopicName(Question question) {
        if ( question == null ) {
            return null;
        }
        Topic topic = question.getTopic();
        if ( topic == null ) {
            return null;
        }
        String name = topic.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Set<TagResponse> tagSetToTagResponseSet(Set<Tag> set) {
        if ( set == null ) {
            return null;
        }

        Set<TagResponse> set1 = new LinkedHashSet<TagResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Tag tag : set ) {
            set1.add( tagMapper.toResponse( tag ) );
        }

        return set1;
    }
}
