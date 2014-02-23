package com.sevenlist.jpaplayground.datamodel.mapping.embedded

import groovy.transform.ToString

import javax.persistence.Embeddable

@Embeddable
@ToString(includeNames = true, includeFields = true, includePackage = false)
class AddressEmbedded {

    String street = 'Street'
}
