package com.sevenlist.jpaplayground.datamodel.mapping.manytoone

import com.sevenlist.jpaplayground.AbstractDatabaseTestcase

class ManyToOneTest extends AbstractDatabaseTestcase {

    def "employee and department are persisted"() {
        given:
        def employee = new Employee(department: new Department())

        when:
        persistEntityAndCommit(employee)
        Employee foundEmployee = findEntityWithNewEntityManager(employee)

        then:
        foundEmployee.department
    }
}
