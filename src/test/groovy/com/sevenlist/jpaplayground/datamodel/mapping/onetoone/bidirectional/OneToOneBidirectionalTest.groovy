package com.sevenlist.jpaplayground.datamodel.mapping.onetoone.bidirectional

import com.sevenlist.jpaplayground.AbstractDatabaseTestcase

class OneToOneBidirectionalTest extends AbstractDatabaseTestcase {

    def "employee and parking space are persisted"() {
        given:
        def employee = new EmployeeOneToOneBidirectional(parkingSpace: new ParkingSpaceOneToOneBidirectional())

        when:
        persistEntityAndCommit(employee)
        EmployeeOneToOneBidirectional foundEmployee = findEntity(employee)
        detachEntity(foundEmployee)

        then:
        foundEmployee.parkingSpace.employee
    }
}
