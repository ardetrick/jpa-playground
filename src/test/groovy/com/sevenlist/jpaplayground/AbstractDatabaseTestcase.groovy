package com.sevenlist.jpaplayground

import com.sevenlist.jpaplayground.datamodel.AbstractEntity
import de.akquinet.jbosscc.needle.junit.DatabaseRule
import de.akquinet.jbosscc.needle.junit.NeedleRule
import groovy.util.logging.Slf4j
import org.junit.Rule
import spock.lang.Specification

import javax.persistence.EntityManager
import javax.persistence.EntityTransaction

@Slf4j
abstract class AbstractDatabaseTestcase extends Specification {

    @Rule
    final DatabaseRule databaseRule = new DatabaseRule()

    @Rule
    final NeedleRule needleRule = new NeedleRule(databaseRule)

    protected final EntityManager entityManager = databaseRule.getEntityManager();

    protected final EntityTransaction entityTransaction = entityManager.getTransaction()

    def setup() {
        startTransaction()
    }

    def cleanup() {
        rollbackTransaction()
    }

    protected final void startTransaction() {
        if (entityTransaction.active) {
            return
        }
        entityTransaction.begin()
        entityManager.clear()
        log.info('transaction begun')
    }

    protected final void commitTransaction() {
        if (!entityTransaction.active) {
            throw new IllegalStateException('no transaction is active')
        }
        entityTransaction.commit()
        log.info('transaction committed')
    }

    protected final void rollbackTransaction() {
        if (!entityTransaction.active) {
            return
        }
        entityTransaction.rollback()
        log.info('transaction rolled back')
    }

    protected final void persistEntity(AbstractEntity entity) {
        startTransaction()
        entityManager.persist(entity)
        commitTransaction()
        log.info('persisted entity: {}', entity)
    }

    protected final <E extends AbstractEntity> E mergeEntity(E entity) {
        startTransaction()
        def mergedEntity = entityManager.merge(entity)
        commitTransaction()
        log.info('merged entity: {}', entity)
        mergedEntity
    }

    protected EntityManager newEntityManager() {
        databaseRule.entityManagerFactory.createEntityManager()
    }
}
