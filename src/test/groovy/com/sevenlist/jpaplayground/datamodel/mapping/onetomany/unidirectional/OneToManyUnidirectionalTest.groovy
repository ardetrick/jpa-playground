package com.sevenlist.jpaplayground.datamodel.mapping.onetomany.unidirectional

import com.sevenlist.jpaplayground.AbstractDatabaseTestcase

class OneToManyUnidirectionalTest extends AbstractDatabaseTestcase {

    def "employee and phone are persisted"() {
        given:
        def employee = new EmployeeOneToManyUnidirectional(phones: [new PhoneOneToManyUnidirectional()])

        when:
        persistEntityAndCommit(employee)
        EmployeeOneToManyUnidirectional foundEmployee = findEntity(employee)
        detachEntity(foundEmployee)

        then:
        foundEmployee.phones[0]
    }
}
