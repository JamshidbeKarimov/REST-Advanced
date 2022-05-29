package com.epam.esm.service.base;

import com.epam.esm.dto.BaseResponse;

public interface BaseService<P, G> {
    G create(P p);

    G get(Long id);

    int delete(Long id);

    void validator(P p);
}
