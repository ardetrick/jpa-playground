package com.sevenlist.jpaplayground.datamodel.mapping.onetoone.bidirectional

import com.sevenlist.jpaplayground.datamodel.AbstractEntity
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.OneToOne

import static javax.persistence.CascadeType.PERSIST

@Entity
@ToString(includeSuper = true, includeNames = true, includeFields = true, includePackage = false)
class EmployeeOneToOneBidirectional extends AbstractEntity {

    @OneToOne(cascade = PERSIST)
    ParkingSpaceOneToOneBidirectional parkingSpace

    void setParkingSpace(ParkingSpaceOneToOneBidirectional parkingSpace) {
        this.parkingSpace = parkingSpace
        parkingSpace.employee = this
    }
}
