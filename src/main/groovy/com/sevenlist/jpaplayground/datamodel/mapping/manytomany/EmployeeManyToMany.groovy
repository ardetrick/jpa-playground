package com.sevenlist.jpaplayground.datamodel.mapping.manytomany

import com.sevenlist.jpaplayground.datamodel.AbstractEntity

import javax.persistence.Entity
import javax.persistence.ManyToMany

import static javax.persistence.CascadeType.PERSIST
import static javax.persistence.FetchType.EAGER

@Entity
class EmployeeManyToMany extends AbstractEntity {

    @ManyToMany(cascade = PERSIST, fetch = EAGER)
    Collection<ProjectManyToMany> projects = [] as Set

    void addProject(ProjectManyToMany project) {
        projects << project
        project.employees << this
    }
}
