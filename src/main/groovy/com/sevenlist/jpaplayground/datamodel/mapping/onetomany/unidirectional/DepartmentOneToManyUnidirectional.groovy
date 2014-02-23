package com.sevenlist.jpaplayground.datamodel.mapping.onetomany.unidirectional

import com.sevenlist.jpaplayground.datamodel.AbstractEntity

import javax.persistence.Entity
import javax.persistence.OneToMany

import static javax.persistence.CascadeType.PERSIST

@Entity
class DepartmentOneToManyUnidirectional extends AbstractEntity {

    @OneToMany(cascade = PERSIST)
    Collection<EmployeeOneToManyUnidirectional> employees
}
