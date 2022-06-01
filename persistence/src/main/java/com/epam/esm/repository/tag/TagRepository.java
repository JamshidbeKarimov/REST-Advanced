package com.epam.esm.repository.tag;


import com.epam.esm.entity.TagEntity;
import com.epam.esm.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends CrudRepository<TagEntity, Long>, TagQueries {
    TagEntity findByName(String name);

    List<TagEntity> getMostWidelyUserTagOfUser(Long userId);
}
