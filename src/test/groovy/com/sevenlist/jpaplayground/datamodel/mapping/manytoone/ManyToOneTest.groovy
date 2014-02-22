package com.sevenlist.jpaplayground.datamodel.mapping.manytoone

import com.sevenlist.jpaplayground.AbstractDatabaseTestcase

class ManyToOneTest extends AbstractDatabaseTestcase {

    def "employee and department are persisted"() {
        given:
        def employee = new EmployeeManyToOne(department: new DepartmentManyToOne())

        when:
        persistEntityAndCommit(employee)
        EmployeeManyToOne foundEmployee = findEntityWithNewEntityManager(employee)

        then:
        foundEmployee.department
    }
}
