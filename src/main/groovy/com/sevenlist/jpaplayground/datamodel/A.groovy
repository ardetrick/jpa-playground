package com.sevenlist.jpaplayground.datamodel

import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.OneToOne

import static javax.persistence.CascadeType.PERSIST

@Entity
@ToString(includeSuper = true, includeNames = true, includeFields = true, includePackage = false)
class A extends AbstractEntity {

    @OneToOne(cascade = PERSIST)
    B b;
}
