package com.sevenlist.jpaplayground.datamodel

import com.sevenlist.jpaplayground.AbstractDatabaseTestcase

class EntityManagerOperationsTest extends AbstractDatabaseTestcase {

    def "B can be updated using find()"() {
        given:
        B b = new B()

        when:
        persistEntityAndCommit(b)

        startTransaction()
        B bWithNewName = findEntity(b)
        assert b.name == 'b'
        bWithNewName.name = 'updated b'
        commitTransaction()
        detachEntity(bWithNewName)

        B updatedB = findEntity(bWithNewName)

        then:
        updatedB.name == 'updated b'
    }

    def "B can be updated using merge()"() {
        given:
        B b = new B()

        when:
        persistEntityAndCommit(b)

        b.name = 'updated b'
        mergeEntityAndCommit(b)

        B updatedB = findEntity(b)

        then:
        updatedB.name == 'updated b'
    }
}
