package com.epam.esm.dto.request;

import com.epam.esm.entity.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

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
    @NotBlank(message = "description can't be null or empty")
    private String description;
    @DecimalMin(message = "price cannot be negative", value = "0.00")
    private BigDecimal price;
    @Positive(message = "duration should be positive")
    private Integer duration;
    private List<TagEntity> tagEntities;
}
