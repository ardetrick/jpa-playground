package com.sevenlist.jpaplayground.datamodel

import groovy.transform.ToString

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

import static javax.persistence.GenerationType.SEQUENCE

@MappedSuperclass
@ToString(includeNames = true, includeFields = true, includePackage = false)
abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id

    public Long getId() {
        id
    }
}
