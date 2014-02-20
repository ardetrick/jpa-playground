package com.sevenlist.jpaplayground.datamodel

import com.sevenlist.jpaplayground.AbstractDatabaseTestcase

import javax.persistence.PersistenceException

class PersistTest extends AbstractDatabaseTestcase {

    def "cascading persist stores new A and new B"() {
        given:
        A a = new A(b: new B())

        when:
        persistEntity(a)
        A foundA = newEntityManager().find(A, a.id)

        then:
        foundA.b
    }

    def "persisting a detached B throws a PersistenceException"() {
        given:
        B b = new B()

        when:
        persistEntity(b)

        A a = new A(b: b)
        persistEntity(a)

        then:
        def e = thrown(PersistenceException)
        e.message.contains 'detached entity'
    }

    def "detached B can be persisted by merging A"() {
        given:
        B b = new B()

        when:
        persistEntity(b)

        A a = new A(b: b)
        A mergedA = mergeEntity(a)

        A foundA = newEntityManager().find(A, mergedA.id)

        then:
        foundA.b
    }
}
