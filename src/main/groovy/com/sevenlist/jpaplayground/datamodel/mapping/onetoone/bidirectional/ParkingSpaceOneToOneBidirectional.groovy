package com.sevenlist.jpaplayground.datamodel.mapping.onetoone.bidirectional

import com.sevenlist.jpaplayground.datamodel.AbstractEntity

import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity
class ParkingSpaceOneToOneBidirectional extends AbstractEntity {

    @OneToOne(mappedBy = 'parkingSpace')
    EmployeeOneToOneBidirectional employee
}
