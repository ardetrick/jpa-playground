package com.sevenlist.jpaplayground.datamodel

import com.sevenlist.jpaplayground.AbstractDatabaseTestcase

class EntityManagerMergeTest extends AbstractDatabaseTestcase {

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
