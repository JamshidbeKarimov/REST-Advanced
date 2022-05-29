package com.epam.esm.repository.order;

import com.epam.esm.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public OrderEntity create(OrderEntity order) {
        entityManager.persist(order);
        long id = order.getId();
        if(id != 0)
            return order;
        return null;
    }

    @Override
    public List<OrderEntity> getAll(int limit, int offset) {
        return entityManager.createQuery("select o from orders o", OrderEntity.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public Optional<OrderEntity> findById(Long orderId) {
        OrderEntity orderEntity = entityManager.find(OrderEntity.class, orderId);
        if(orderEntity != null)
            return Optional.of(orderEntity);
        return Optional.empty();
    }

    @Override
    public OrderEntity update(OrderEntity obj) {
        return null;
    }

    @Override
    public int delete(Long aLong) {
        return 0;
    }

    @Override
    public List<OrderEntity> getOrdersByUserId(Long userId, int limit, int offset) {
        return entityManager
                .createQuery("select o from orders o where o.user.id = :id", OrderEntity.class)
                .setParameter("id", userId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public Optional<OrderEntity> getByUserIdAndOrderId(Long userId, Long orderId){
        List<OrderEntity> orders = entityManager
                .createQuery(
                        "select o from orders o where o.user.id = :userId and o.id = :orderId", OrderEntity.class)
                .setParameter("userId", userId)
                .setParameter("orderId", orderId)
                .getResultList();

        if(orders.isEmpty())
            return Optional.empty();
        return Optional.of(orders.get(0));
    }

    @Override
    public List<OrderEntity> getByCertificateId(Long certificateId, int limit, int offset) {
        return entityManager
                .createQuery("select o from orders o where o.certificate.id = :id", OrderEntity.class)
                .setParameter("id", certificateId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public Optional<OrderEntity> getByUserIdAndCertificateId(Long userId, Long certificateId) {
        List<OrderEntity> resultList
                = entityManager.createQuery(
                        "select o from orders o where o.certificate.id = :certificateId and o.user.id = :userId",
                        OrderEntity.class)
                .setParameter("certificateId", certificateId)
                .setParameter("userId", userId)
                .getResultList();

        if(resultList.isEmpty())
            return Optional.empty();
        return Optional.of(resultList.get(0));
    }
}
