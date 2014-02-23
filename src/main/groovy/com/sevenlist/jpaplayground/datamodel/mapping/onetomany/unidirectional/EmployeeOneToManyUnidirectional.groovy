package com.sevenlist.jpaplayground.datamodel.mapping.onetomany.unidirectional

import com.sevenlist.jpaplayground.datamodel.AbstractEntity

import javax.persistence.Entity
import javax.persistence.OneToMany

import static javax.persistence.CascadeType.PERSIST
import static javax.persistence.FetchType.EAGER

@Entity
class EmployeeOneToManyUnidirectional extends AbstractEntity {

    @OneToMany(cascade = PERSIST, fetch = EAGER)
    Collection<PhoneOneToManyUnidirectional> phones
}
