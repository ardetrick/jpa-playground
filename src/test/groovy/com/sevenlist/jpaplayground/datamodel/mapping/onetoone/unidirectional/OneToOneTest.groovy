package com.sevenlist.jpaplayground.datamodel.mapping.onetoone.unidirectional

import com.sevenlist.jpaplayground.AbstractDatabaseTestcase

class OneToOneTest extends AbstractDatabaseTestcase {

    def "employee and parking space are persisted"() {
        given:
        def employee = new EmployeeOneToOneUnidirectional(parkingSpace: new ParkingSpace())

        when:
        persistEntityAndCommit(employee)
        EmployeeOneToOneUnidirectional foundEmployee = findEntityWithNewEntityManager(employee)

        then:
        foundEmployee.parkingSpace
    }
}
