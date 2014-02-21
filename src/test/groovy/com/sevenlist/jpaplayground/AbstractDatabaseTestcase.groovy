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
        log.debug('transaction begun')
        entityManager.clear()
    }

    protected final void commitTransaction() {
        if (!entityTransaction.active) {
            throw new IllegalStateException('no transaction is active')
        }
        entityTransaction.commit()
        log.debug('transaction committed')
    }

    protected final void rollbackTransaction() {
        if (!entityTransaction.active) {
            return
        }
        entityTransaction.rollback()
        log.debug('transaction rolled back')
    }

    protected final void persistEntity(AbstractEntity entity) {
        startTransaction()
        entityManager.persist(entity)
        log.debug('persisted entity: {}', entity)
    }

    protected final void persistEntityAndCommit(AbstractEntity entity) {
        persistEntity(entity)
        commitTransaction()
    }

    protected final <T extends AbstractEntity> T mergeEntity(T entity) {
        startTransaction()
        def mergedEntity = entityManager.merge(entity)
        log.debug('merged entity: {}', entity)
        mergedEntity
    }

    protected final <T extends AbstractEntity> T mergeEntityAndCommit(T entity) {
        def mergedEntity = mergeEntity(entity)
        commitTransaction()
        mergedEntity
    }

    protected final EntityManager newEntityManager() {
        databaseRule.entityManagerFactory.createEntityManager()
    }

    protected final <T extends AbstractEntity> T findEntity(T entity) {
        newEntityManager().find(entity.class, entity.id)
    }
}
