package com.sevenlist.jpaplayground.datamodel.mapping.manytoone

import com.sevenlist.jpaplayground.datamodel.AbstractEntity
import groovy.transform.ToString

import javax.persistence.Entity

@Entity
@ToString(includeSuper = true, includeNames = true, includeFields = true, includePackage = false)
class DepartmentManyToOne extends AbstractEntity {
}
