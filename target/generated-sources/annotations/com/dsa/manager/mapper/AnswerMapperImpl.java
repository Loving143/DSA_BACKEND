package com.dsa.manager.mapper;

import com.dsa.manager.dto.AnswerRequest;
import com.dsa.manager.dto.AnswerResponse;
import com.dsa.manager.entity.Answer;
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
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer toEntity(AnswerRequest request) {
        if ( request == null ) {
            return null;
        }

        Answer.AnswerBuilder answer = Answer.builder();

        answer.code( request.getCode() );
        answer.explanation( request.getExplanation() );

        return answer.build();
    }

    @Override
    public AnswerResponse toResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerResponse answerResponse = new AnswerResponse();

        answerResponse.setQuestionId( answerQuestionId( answer ) );
        answerResponse.setId( answer.getId() );
        answerResponse.setCode( answer.getCode() );
        answerResponse.setExplanation( answer.getExplanation() );
        answerResponse.setCreatedAt( answer.getCreatedAt() );
        answerResponse.setUpdatedAt( answer.getUpdatedAt() );

        return answerResponse;
    }

    private Long answerQuestionId(Answer answer) {
        if ( answer == null ) {
            return null;
        }
        Question question = answer.getQuestion();
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
