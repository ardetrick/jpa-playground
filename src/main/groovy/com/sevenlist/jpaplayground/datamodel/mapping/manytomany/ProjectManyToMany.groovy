package com.sevenlist.jpaplayground.datamodel.mapping.manytomany

import com.sevenlist.jpaplayground.datamodel.AbstractEntity

import javax.persistence.Entity
import javax.persistence.ManyToMany

@Entity
class ProjectManyToMany extends AbstractEntity {

    @ManyToMany(mappedBy = 'projects')
    Collection<EmployeeManyToMany> employees
}
