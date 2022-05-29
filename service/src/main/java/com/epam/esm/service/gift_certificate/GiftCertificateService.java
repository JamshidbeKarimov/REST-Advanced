package com.epam.esm.service.gift_certificate;

import com.epam.esm.dto.BaseResponse;
import com.epam.esm.dto.reponse.GiftCertificateGetResponse;
import com.epam.esm.dto.request.GiftCertificatePostRequest;
import com.epam.esm.exception.gift_certificate.InvalidCertificateException;
import com.epam.esm.resources.GiftCertificateResource;
import com.epam.esm.service.base.BaseService;

import java.math.BigDecimal;
import java.util.List;

public interface GiftCertificateService extends BaseService<GiftCertificatePostRequest, GiftCertificateGetResponse> {

    List<GiftCertificateGetResponse> getAll(
            String searchWord, String tagName, boolean doNameSort, boolean doDateSort,
            boolean isDescending, int limit, int offset
    );

    GiftCertificateGetResponse update(GiftCertificatePostRequest update, Long certificateId);

    GiftCertificateGetResponse updateDuration(int duration, Long id);

    List<GiftCertificateGetResponse> searchWithMultipleTags(List<String> tags, int limit, int offset);

    @Override
    default void validator(GiftCertificatePostRequest certificate){
        if(certificate.getName() == null || certificate.getName().length() == 0)
            throw new InvalidCertificateException("Gift certificate name cannot be empty or null");
        if(certificate.getDuration() != null && certificate.getDuration() <= 0)
            throw  new InvalidCertificateException(
                    "certificate duration should be positive"
            );
        if(certificate.getPrice() != null && certificate.getPrice().compareTo(new BigDecimal(0)) == - 1)
            throw  new InvalidCertificateException(
                    "certificate price cannot be negative"
            );
    }

}
