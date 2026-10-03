package com.demoqa.models

import groovy.transform.Canonical

/** One row of the DemoQA Web Tables grid. */
@Canonical
class Employee {

	String firstName
	String lastName
	String email
	String age
	String salary
	String department

	/** Values in grid column order (the 'Action' column excluded). */
	List<String> asRow() {
		return [firstName, lastName, age, email, salary, department]
	}

	/** Copy of this employee with some fields replaced, e.g. copyWith(salary: '9000'). */
	Employee copyWith(Map<String, String> changes) {
		Employee copy = new Employee(firstName, lastName, email, age, salary, department)
		changes.each { String field, String value -> copy.setProperty(field, value) }
		return copy
	}
}
