package com.sevenlist.jpaplayground.datamodel.mapping.onetomany.bidirectional

import com.sevenlist.jpaplayground.datamodel.AbstractEntity

import javax.persistence.Entity
import javax.persistence.ManyToOne

import static javax.persistence.CascadeType.PERSIST

@Entity
class EmployeeOneToManyBidirectional extends AbstractEntity {

    @ManyToOne(cascade = PERSIST)
    DepartmentOneToManyBidirectional department
}
