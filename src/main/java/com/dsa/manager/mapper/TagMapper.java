package com.dsa.manager.mapper;

import com.dsa.manager.dto.TagRequest;
import com.dsa.manager.dto.TagResponse;
import com.dsa.manager.entity.Tag;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag toEntity(TagRequest request);

    TagResponse toResponse(Tag tag);
}
