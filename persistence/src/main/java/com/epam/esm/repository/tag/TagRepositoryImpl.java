package com.epam.esm.repository.tag;

import com.epam.esm.entity.TagEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepositoryImpl implements TagRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public TagEntity create(TagEntity tagEntity) {
        entityManager.persist(tagEntity);
        if(tagEntity.getId() != null)
            return tagEntity;
        return null;
    }

    @Override
    public List<TagEntity> getAll(int limit, int offset) {
        return entityManager
                .createQuery("select t from TagEntity t", TagEntity.class)
                .setMaxResults(limit)
                .setFirstResult(offset)
                .getResultList();
    }

    @Override
    public Optional<TagEntity> findById(Long id) {
        TagEntity tagEntity = entityManager.find(TagEntity.class, id);
        if(tagEntity != null)
            return Optional.of(tagEntity);
        return Optional.empty();
    }

    @Override
    public TagEntity update(TagEntity obj) {
        return null;
    }

    @Override
    public int delete(Long id) {
        return entityManager.createQuery("delete from TagEntity where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public TagEntity findByName(String name) {
        try {
            TagEntity tag = entityManager.createQuery(
                            "select t from TagEntity t where t.name = :name", TagEntity.class)
                    .setParameter("name", name).getSingleResult();
            return tag;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public TagEntity getMostWidelyUserTagOfUser(Long userId) {
//        TagEntity tagEntity = entityManager.createQuery("SELECT t FROM users u " +
//                                                 "JOIN u.orders o " +
//                                                 "JOIN o.certificate.tagEntities t " +
//                                                 "WHERE u.id = :id " +
//                                                 "GROUP BY t.id, t.name " +
//                                                 "ORDER BY SUM(o.price), COUNT(t) DESC limit 1", TagEntity.class)
//                .setParameter("id", userId)
//                .getSingleResult();
//
//        return tagEntity;
        return null;
    }
}
