package com.epam.esm.dto.request;


import com.epam.esm.entity.TagEntity;
import com.epam.esm.exception.gift_certificate.InvalidCertificateException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GiftCertificatePostRequest {
    @NotBlank(message = "name can't be null or empty")
    private String name;
    private String description;
    @Nullable
    private String price;
    @Positive(message = "duration should be positive")
    private Integer duration;
    private List<TagEntity> tagEntities;

}
