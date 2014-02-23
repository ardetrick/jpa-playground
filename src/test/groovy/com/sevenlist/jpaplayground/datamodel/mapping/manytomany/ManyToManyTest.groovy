package com.sevenlist.jpaplayground.datamodel.mapping.manytomany

import com.sevenlist.jpaplayground.AbstractDatabaseTestcase

class ManyToManyTest extends AbstractDatabaseTestcase {

    def "employee and project are persisted"() {
        given:
        def employee = new EmployeeManyToMany(projects: [new ProjectManyToMany()])

        when:
        persistEntityAndCommit(employee)
        EmployeeManyToMany foundEmployee = findEntity(employee)

        then:
        foundEmployee.projects[0].employees.size() == 1
    }
}
