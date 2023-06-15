package com.star.customer.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    private static final long serialVersionUID = -513467905363162309L;

    /**
     * Table 'DBNAME.hibernate_sequence' doesn't exist
     * GenerationType.AUTO ---> GenerationType.IDENTITY
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_id")
    private Long createId;

    /**
     * java.sql.SQLIntegrityConstraintViolationException: Column 'create_instant' cannot be null
     * post 请求时字段为 create_instant 应该修改为 createInstant
     */
    @Column(name = "create_instant")
    private Instant createInstant;

    @Column(name = "modify_id")
    private Long modifyId;

    @Column(name = "modify_instant")
    private Instant modifyInstant;

    @Column(name = "status")
    private Status status;

    public Customer(String name, Long createId, Instant createInstant, Long modifyId, Instant modifyInstant, Status status) {
        this.name = name;
        this.createId = createId;
        this.createInstant = createInstant;
        this.modifyId = modifyId;
        this.modifyInstant = modifyInstant;
        this.status = status;
    }
}
