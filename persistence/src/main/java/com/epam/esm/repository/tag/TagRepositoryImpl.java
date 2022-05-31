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
        if (tagEntity.getId() != null)
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
        if (tagEntity != null)
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
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<TagEntity> getMostWidelyUserTagOfUser(Long userId) {
        List tags = entityManager.createNativeQuery("""
                        select t from tag t where t.id in (select ct.tag_id from certificate_tag ct where ct.certificate_id in
                                         (select gc.id from gift_certificate gc where gc.id in
                                                   (select o.certificate_id from orders o where o.user_id = :userId))
                        group by ct.tag_id having count(ct.tag_id) = (
                            select count(ct.tag_id) from certificate_tag ct where ct.certificate_id in
                                                    (select gc.id from gift_certificate gc where gc.id in 
                                                            (select o.certificate_id from orders o where o.user_id = :userId))
                            group by ct.tag_id order by count(ct.tag_id) desc limit 1));""", TagEntity.class)
                .setParameter("userId", userId)
                .getResultList();
        return tags;
    }
}
