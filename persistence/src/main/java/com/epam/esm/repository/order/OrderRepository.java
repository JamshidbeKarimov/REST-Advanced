package com.epam.esm.repository.order;

import com.epam.esm.repository.CrudRepository;
import com.epam.esm.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<OrderEntity, Long>, OrderQueries{

    List<OrderEntity> getOrdersByUserId(Long userId, int limit, int offset);

    Optional<OrderEntity> getByUserIdAndOrderId(Long userId, Long orderId);

    Optional<OrderEntity> getByUserIdAndCertificateId(Long userId, Long certificateId);

    List<OrderEntity> getByCertificateId(Long certificateId, int limit, int offset);
}
