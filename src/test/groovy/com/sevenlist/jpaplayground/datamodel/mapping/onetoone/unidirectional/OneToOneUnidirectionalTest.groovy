package com.sevenlist.jpaplayground.datamodel.mapping.onetoone.unidirectional

import com.sevenlist.jpaplayground.AbstractDatabaseTestcase

class OneToOneUnidirectionalTest extends AbstractDatabaseTestcase {

    def "employee and parking space are persisted"() {
        given:
        def employee = new EmployeeOneToOneUnidirectional(parkingSpace: new ParkingSpaceOneToOneUnidirectional())

        when:
        persistEntityAndCommit(employee)
        EmployeeOneToOneUnidirectional foundEmployee = findEntity(employee)

        then:
        foundEmployee.parkingSpace
    }
}
