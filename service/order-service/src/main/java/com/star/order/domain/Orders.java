package com.star.order.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

/**
 * @author Caolp
 */
@Entity
@Table(name = "orders")
@Data
public class Orders {
    private static final long serialVersionUID = -1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "create_id")
    private Long createId;

    @Column(name = "create_instant")
    private Instant createInstant;

    @Column(name = "modify_id")
    private Long modifyId;

    @Column(name = "modify_instant")
    private Instant modifyInstant;
}
