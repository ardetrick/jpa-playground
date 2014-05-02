package com.sevenlist.jpaplayground.datamodel

import com.sevenlist.jpaplayground.AbstractDatabaseTestcase
import org.hibernate.PropertyValueException
import org.hibernate.TransientPropertyValueException

import javax.persistence.PersistenceException

class EntityManagerPersistTest extends AbstractDatabaseTestcase {

    def "X can be persisted after persisting B"() {
        given:
        def b = new B()
        def x = new X(b: b)

        expect:
        persistEntity(b)
        persistEntity(x)
        commitTransaction()
    }

    def "X can be persisted with detached B"() {
        given:
        def b = new B()
        persistEntityAndCommit(b)

        // b is detached but has an id
        def x = new X(b: b)

        expect:
        persistEntityAndCommit(x)
    }

    def "X cannot be persisted without B"() {
        given:
        def x = new X()

        when:
        persistEntity(x)

        then:
        def e = thrown(PersistenceException)
        e.cause.class == PropertyValueException
        e.cause.message.contains 'not-null property references a null or transient value'
    }

    def "X cannot be persisted before B is persisted"() {
        given:
        def b = new B()
        def x = new X(b: b);

        when:
        persistEntity(x)
        // persistEntity(b)
        // commitTransaction()

        then:
        def e = thrown(IllegalStateException)
        e.cause.class == TransientPropertyValueException
        e.cause.message.contains 'Not-null property references a transient value - transient instance must be saved before current operation'
    }

    def "inheritance works out of the box"() {
        given:
        def c = new C(d: new E())

        when:
        persistEntityAndCommit(c)
        C foundC = findEntity(c)

        then:
        ((E) foundC.d).secondName == 'e'
    }
}
