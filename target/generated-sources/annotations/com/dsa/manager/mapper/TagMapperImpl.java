package com.dsa.manager.mapper;

import com.dsa.manager.dto.TagRequest;
import com.dsa.manager.dto.TagResponse;
import com.dsa.manager.entity.Tag;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-31T11:33:07+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public Tag toEntity(TagRequest request) {
        if ( request == null ) {
            return null;
        }

        Tag.TagBuilder tag = Tag.builder();

        tag.name( request.getName() );

        return tag.build();
    }

    @Override
    public TagResponse toResponse(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagResponse tagResponse = new TagResponse();

        tagResponse.setId( tag.getId() );
        tagResponse.setName( tag.getName() );

        return tagResponse;
    }
}
