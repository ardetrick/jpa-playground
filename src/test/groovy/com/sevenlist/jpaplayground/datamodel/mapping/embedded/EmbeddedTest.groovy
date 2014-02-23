package com.sevenlist.jpaplayground.datamodel.mapping.embedded

import com.sevenlist.jpaplayground.AbstractDatabaseTestcase

class EmbeddedTest extends AbstractDatabaseTestcase {

    def "employee and address are persisted"() {
        given:
        def employee = new EmployeeEmbedded(address: new AddressEmbedded())

        when:
        persistEntityAndCommit(employee)

        EmployeeEmbedded foundEmployee = findEntity(employee)

        then:
        foundEmployee.address.street == 'Street'
    }
}
