package com.sevenlist.jpaplayground.datamodel.mapping.onetomany.unidirectional

import com.sevenlist.jpaplayground.AbstractDatabaseTestcase

class OneToManyUnidirectionalTest extends AbstractDatabaseTestcase {

    def "department and employee are persisted"() {
        given:
        def department = new DepartmentOneToManyUnidirectional(employees: [new EmployeeOneToManyUnidirectional()])

        when:
        persistEntityAndCommit(department)
        DepartmentOneToManyUnidirectional foundDepartment = findEntityWithNewEntityManager(department)

        then:
        foundDepartment.employees[0].id
    }
}
