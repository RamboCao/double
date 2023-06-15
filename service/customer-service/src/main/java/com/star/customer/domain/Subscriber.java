package com.star.customer.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Caolp
 */
@Entity
@Table(name = "subscriber")
@Data
public class Subscriber {

    private static final long serialVersionUID = -513467905363162309L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SERVICE_CODE")
    private String serviceCode;

    public Subscriber(String serviceCode) {
        this.serviceCode = serviceCode;
    }
}
