package com.epam.esm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "orders")
public class OrderEntity extends BaseEntity{
    private BigDecimal price;

    @ManyToOne
    private GiftCertificateEntity certificate;

    @ManyToOne
    private UserEntity user;
}
