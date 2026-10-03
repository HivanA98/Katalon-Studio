package com.saucedemo.models

import com.qa.core.Config

import groovy.transform.Canonical

@Canonical
class Customer {

	String firstName
	String lastName
	String postalCode

	/** Customer built from the execution profile, so data can be changed per environment. */
	static Customer fromProfile() {
		return new Customer(
				Config.text('FirstName', 'Jane'),
				Config.text('LastName', 'Tester'),
				Config.text('PostalCode', '20220'))
	}
}
