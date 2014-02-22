package com.sevenlist.jpaplayground.datamodel.mapping.onetoone.unidirectional

import com.sevenlist.jpaplayground.datamodel.AbstractEntity
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.OneToOne

import static javax.persistence.CascadeType.PERSIST

@Entity
@ToString(includeSuper = true, includeNames = true, includeFields = true, includePackage = false)
class EmployeeOneToOneUnidirectional extends AbstractEntity {

    @OneToOne(cascade = PERSIST)
    ParkingSpace parkingSpace
}
