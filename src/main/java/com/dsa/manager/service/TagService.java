package com.dsa.manager.service;

import com.dsa.manager.dto.TagRequest;
import com.dsa.manager.dto.TagResponse;
import com.dsa.manager.entity.Tag;
import com.dsa.manager.exception.DuplicateResourceException;
import com.dsa.manager.exception.ResourceNotFoundException;
import com.dsa.manager.mapper.TagMapper;
import com.dsa.manager.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public List<TagResponse> getAllTags() {
        return tagRepository.findAll().stream()
            .map(tagMapper::toResponse)
            .toList();
    }

    @Transactional
    public TagResponse createTag(TagRequest request) {
        if (tagRepository.existsByNameIgnoreCase(request.getName())) {
            throw new DuplicateResourceException("Tag already exists with name: " + request.getName());
        }
        Tag tag = tagMapper.toEntity(request);
        return tagMapper.toResponse(tagRepository.save(tag));
    }

    @Transactional
    public void deleteTag(Long id) {
        Tag tag = tagRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tag", id));
        tagRepository.delete(tag);
    }
}
