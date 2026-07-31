package com.dsa.manager.mapper;

import com.dsa.manager.dto.TopicRequest;
import com.dsa.manager.dto.TopicResponse;
import com.dsa.manager.entity.Topic;
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
public class TopicMapperImpl implements TopicMapper {

    @Override
    public Topic toEntity(TopicRequest request) {
        if ( request == null ) {
            return null;
        }

        Topic.TopicBuilder topic = Topic.builder();

        topic.name( request.getName() );

        return topic.build();
    }

    @Override
    public TopicResponse toResponse(Topic topic) {
        if ( topic == null ) {
            return null;
        }

        TopicResponse topicResponse = new TopicResponse();

        topicResponse.setId( topic.getId() );
        topicResponse.setName( topic.getName() );
        topicResponse.setCreatedAt( topic.getCreatedAt() );
        topicResponse.setUpdatedAt( topic.getUpdatedAt() );

        topicResponse.setQuestionCount( topic.getQuestions() != null ? (int) topic.getQuestions().stream().filter(q -> !q.getDeleted()).count() : 0 );

        return topicResponse;
    }
}
