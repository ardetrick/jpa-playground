package com.sevenlist.jpaplayground.datamodel

import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity
@ToString(includeSuper = true, includeNames = true, includeFields = true, includePackage = false)
class X extends AbstractEntity {

    @OneToOne(optional = false)
    B b;
}
