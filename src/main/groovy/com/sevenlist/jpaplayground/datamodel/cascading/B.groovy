package com.sevenlist.jpaplayground.datamodel.cascading

import com.sevenlist.jpaplayground.datamodel.AbstractEntity
import groovy.transform.ToString

import javax.persistence.Entity

@Entity
@ToString(includeSuper = true, includeNames = true, includeFields = true, includePackage = false)
class B extends AbstractEntity {
}
