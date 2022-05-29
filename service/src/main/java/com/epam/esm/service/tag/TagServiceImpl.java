package com.epam.esm.service.tag;

import com.epam.esm.dto.reponse.TagGetResponse;
import com.epam.esm.dto.request.TagPostRequest;
import com.epam.esm.entity.TagEntity;
import com.epam.esm.exception.DataAlreadyExistException;
import com.epam.esm.repository.tag.TagRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{
    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    public TagServiceImpl(TagRepository tagRepository, ModelMapper modelMapper) {
        this.tagRepository = tagRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public TagGetResponse create(TagPostRequest createTag){
        validator(createTag);
        TagEntity tagEntity = modelMapper.map(createTag, TagEntity.class);
        TagEntity createdTag = tagRepository.create(tagEntity);
        if(createdTag != null) {
            return modelMapper.map(createdTag, TagGetResponse.class);
        }
        throw new DataAlreadyExistException("tag with name: " + createdTag.getName() + "already exists");
    }

    @Override
    public TagGetResponse get(Long tagId) {
        TagEntity tag = tagRepository.findById(tagId).get();
        return modelMapper.map(tag, TagGetResponse.class);

    }

    @Override
    @Transactional
    public int delete(Long tagId) {
        return tagRepository.delete(tagId);
    }


    @Override
    public List<TagGetResponse> getAll(int limit, int offset) {
        List<TagEntity> allTags = tagRepository.getAll(limit, offset);
        return modelMapper.map(allTags, new TypeToken<List<TagGetResponse>>() {}.getType());
    }

    @Override
    public TagGetResponse getMostWidelyUserTagOfUser(Long userId) {
//
//        TagEntity tagOfUser = tagRepository.getMostWidelyUserTagOfUser(userId);
//        return modelMapper.map(tagOfUser, TagGetResponse.class);
        return null;
    }
}