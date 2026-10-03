package com.demoqa.pages

import com.demoqa.models.Employee
import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator

/** The "Registration Form" modal used to add and edit Web Tables rows. */
class RegistrationFormModal extends BasePage {

	private final TestObject title = Locator.id('registration-form-modal')
	private final TestObject firstName = Locator.id('firstName')
	private final TestObject lastName = Locator.id('lastName')
	private final TestObject email = Locator.id('userEmail')
	private final TestObject age = Locator.id('age')
	private final TestObject salary = Locator.id('salary')
	private final TestObject department = Locator.id('department')
	private final TestObject submitButton = Locator.css('#userForm #submit')

	@Override
	protected TestObject pageMarker() {
		return title
	}

	WebTablesPage save(Employee employee) {
		type(firstName, employee.firstName)
		type(lastName, employee.lastName)
		type(email, employee.email)
		type(age, employee.age)
		type(salary, employee.salary)
		type(department, employee.department)
		click(submitButton)
		waitUntilGone(title)
		return new WebTablesPage()
	}

	Employee currentValues() {
		return new Employee(valueOf(firstName), valueOf(lastName), valueOf(email),
				valueOf(age), valueOf(salary), valueOf(department))
	}
}
