package com.bigtix.models

import com.qa.core.Config

import groovy.transform.Canonical

@Canonical
class Buyer {

	String fullName
	String email
	String phone
	String address
	String country
	String postalCode

	static Buyer fromProfile() {
		return new Buyer(
				Config.text('FullName', 'Automation Tester'),
				Config.text('Email', 'automation@example.com'),
				Config.text('PhoneNumber', '63754128'),
				Config.text('Address', 'Jendral Sudirman'),
				Config.text('Country', 'singapore'),
				Config.text('PostalCode', '801111'))
	}
}
