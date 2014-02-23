package com.sevenlist.jpaplayground.datamodel.mapping.onetomany.unidirectional

import com.sevenlist.jpaplayground.AbstractDatabaseTestcase

class OneToManyUnidirectionalTest extends AbstractDatabaseTestcase {

    def "employee and phone are persisted"() {
        given:
        def employee = new EmployeeOneToManyUnidirectional(phones: [new PhoneOneToManyUnidirectional()])

        when:
        persistEntityAndCommit(employee)
        EmployeeOneToManyUnidirectional foundEmployee = findEntityWithNewEntityManager(employee)

        then:
        foundEmployee.phones.size() == 1
    }
}