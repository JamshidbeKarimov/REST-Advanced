package com.epam.esm.service.order;

import com.epam.esm.dto.BaseResponse;
import com.epam.esm.dto.reponse.OrderGetResponse;
import com.epam.esm.dto.request.OrderPostRequest;
import com.epam.esm.entity.OrderEntity;
import com.epam.esm.service.base.BaseService;

import java.util.List;
import java.util.Optional;

public interface OrderService extends BaseService<OrderPostRequest, OrderGetResponse> {

    List<OrderGetResponse> getOrdersByUserId(Long userId, int limit, int offset);

    OrderGetResponse getByUserIdAndOrderId(Long userId, Long orderId);

    List<OrderGetResponse> getByCertificateId(Long certificateId, int limit, int offset);

}
