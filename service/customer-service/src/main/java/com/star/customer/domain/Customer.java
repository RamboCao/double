package com.star.customer.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    private static final long serialVersionUID = -513467905363162309L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_id")
    private Long createId;

    @Column(name = "create_instant")
    private Instant createInstant;

    @Column(name = "modify_id")
    private Long modifyId;

    @Column(name = "modify_instant")
    private Instant modifyInstant;

}
