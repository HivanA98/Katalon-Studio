package com.traveloka.models

import groovy.transform.Canonical

/**
 * Booker / driver details. Real values live in the git-ignored fixture
 * Include/resources/fixtures/traveloka.json (template: traveloka.example.json).
 */
@Canonical
class Contact {

	String fullName
	String phone
	String email

	static Contact from(Map values) {
		return new Contact(values.fullName as String, values.phone as String, values.email as String)
	}
}
