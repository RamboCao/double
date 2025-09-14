package com.star.order.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Caolp
 */
@Entity
@Table(name = "dog")
@Data
public class Dog {
    private static final long serialVersionUID = -1;

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Long age;

}