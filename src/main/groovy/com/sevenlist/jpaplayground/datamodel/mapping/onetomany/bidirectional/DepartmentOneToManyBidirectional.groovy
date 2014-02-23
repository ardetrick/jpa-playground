package com.sevenlist.jpaplayground.datamodel.mapping.onetomany.bidirectional

import com.sevenlist.jpaplayground.datamodel.AbstractEntity

import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class DepartmentOneToManyBidirectional extends AbstractEntity {

    @OneToMany(mappedBy = 'department')
    Collection<EmployeeOneToManyBidirectional> employees
}
