package com.sevenlist.jpaplayground.datamodel.mapping.onetomany.bidirectional

import com.sevenlist.jpaplayground.AbstractDatabaseTestcase

class OneToManyBidirectionalTest extends AbstractDatabaseTestcase {

    def "employee and department are persisted"() {
        given:
        def employee = new EmployeeOneToManyBidirectional(department: new DepartmentOneToManyBidirectional())

        when:
        persistEntityAndCommit(employee)
        EmployeeOneToManyBidirectional foundEmployee = findEntity(employee)

        then:
        foundEmployee.department.employees.size() == 1
    }
}
