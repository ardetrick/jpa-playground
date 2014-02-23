package com.sevenlist.jpaplayground.datamodel.mapping.manytomany

import com.sevenlist.jpaplayground.datamodel.AbstractEntity

import javax.persistence.Entity
import javax.persistence.ManyToMany

import static javax.persistence.CascadeType.PERSIST

@Entity
class EmployeeManyToMany extends AbstractEntity {

    @ManyToMany(cascade = PERSIST)
    Collection<ProjectManyToMany> projects
}
