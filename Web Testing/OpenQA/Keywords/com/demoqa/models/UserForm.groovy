package com.demoqa.models

import com.qa.core.Config

import groovy.transform.Canonical

@Canonical
class UserForm {

	String fullName
	String email
	String currentAddress
	String permanentAddress

	static UserForm fromProfile() {
		return new UserForm(
				Config.text('FirstName', 'Jane Tester'),
				Config.text('Email', 'jane.tester@example.com'),
				Config.text('City', 'Jakarta'),
				Config.text('Country', 'Indonesia'))
	}
}
