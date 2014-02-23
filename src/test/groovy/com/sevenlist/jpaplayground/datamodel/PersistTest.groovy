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

        // detached b becomes managed again
        B foundB = findEntity(b)
        A a = new A(b: foundB)
        // em.persist(b) triggered by the cascading persist is ignored, as b is already managed
        persistEntityAndCommit(a)

        A foundA = findEntityWithNewEntityManager(a)

        then:
        foundA.b
    }

    def "A can be persisted even if B was persisted before"() {
        given:
        B b = new B()
        A a = new A(b: b)

        when:
        persistEntityAndCommit(b)

        // use "old" b reference from a; detached b becomes managed again
        def foundB = findEntity(a.b)
        // b has to be replaced with the managed b in a
        a.b = foundB
        // a can be persisted, as persisting of already managed (and formerly persisted) b is ignored
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

        // detached b becomes managed again
        B mergedB = mergeEntity(b)
        A a = new A(b: mergedB)
        // em.persist(b) triggered by the cascading persist is ignored, as b is already managed
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
