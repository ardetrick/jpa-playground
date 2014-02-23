package com.sevenlist.jpaplayground.datamodel.mapping.embedded

import com.sevenlist.jpaplayground.datamodel.AbstractEntity
import groovy.transform.ToString

import javax.persistence.Embedded
import javax.persistence.Entity

@Entity
@ToString(includeNames = true, includeFields = true, includePackage = false)
class EmployeeEmbedded extends AbstractEntity {

    @Embedded // annotation is optional
    AddressEmbedded address
}
