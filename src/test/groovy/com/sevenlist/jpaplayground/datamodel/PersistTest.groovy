package com.sevenlist.jpaplayground.datamodel

import com.sevenlist.jpaplayground.AbstractDatabaseTestcase

import javax.persistence.PersistenceException

class PersistTest extends AbstractDatabaseTestcase {

    def "cascading persist stores new A and new B"() {
        given:
        A a = new A(b: new B())

        when:
        persistEntityAndCommit(a)
        A foundA = findEntityWithNewEntityManager(a)

        then:
        foundA.b
    }

    def "A cannot be persisted when B is detached"() {
        given:
        B b = new B()

        when:
        persistEntityAndCommit(b)

        A a = new A(b: b)
        persistEntityAndCommit(a)

        then:
        def e = thrown(PersistenceException)
        e.message.contains 'detached entity'
    }

    def "A can be persisted when detached B is found before"() {
        given:
        B b = new B()

        when:
        persistEntityAndCommit(b)

        startTransaction()
        B foundB = entityManager.find(B, b.id)
        A a = new A(b: foundB)
        persistEntityAndCommit(a)

        A foundA = findEntityWithNewEntityManager(a)

        then:
        foundA.b
    }


    def "A can be persisted when detached B is merged before"() {
        given:
        B b = new B()

        when:
        persistEntityAndCommit(b)

        B mergedB = mergeEntity(b)
        A a = new A(b: mergedB)
        persistEntityAndCommit(a)

        A foundA = findEntityWithNewEntityManager(a)

        then:
        foundA.b
    }

    def "detached B can be persisted by merging A"() {
        given:
        B b = new B()

        when:
        persistEntityAndCommit(b)

        A a = new A(b: b)
        A mergedA = mergeEntityAndCommit(a)

        A foundA = findEntityWithNewEntityManager(mergedA)

        then:
        foundA.b
    }
}
