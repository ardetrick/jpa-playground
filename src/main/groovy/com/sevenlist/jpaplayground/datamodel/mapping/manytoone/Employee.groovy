package com.sevenlist.jpaplayground.datamodel.mapping.manytoone

import com.sevenlist.jpaplayground.datamodel.AbstractEntity
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.ManyToOne

import static javax.persistence.CascadeType.PERSIST

@Entity
@ToString(includeSuper = true, includeNames = true, includeFields = true, includePackage = false)
class Employee extends AbstractEntity {

    @ManyToOne(cascade = PERSIST)
    Department department
}
