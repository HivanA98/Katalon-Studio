package com.demoqa.pages

import com.demoqa.models.Employee
import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.Locator

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class WebTablesPage extends DemoQaPage {

	private final TestObject addButton = Locator.id('addNewRecordButton')
	private final TestObject searchBox = Locator.id('searchBox')
	private final TestObject rows = Locator.css('table tbody tr')

	@Override
	protected String path() {
		return 'webtables'
	}

	@Override
	protected TestObject pageMarker() {
		return addButton
	}

	static WebTablesPage open() {
		return launch(new WebTablesPage())
	}

	WebTablesPage add(Employee employee) {
		click(addButton)
		RegistrationFormModal modal = new RegistrationFormModal()
		modal.verifyDisplayed()
		return modal.save(employee)
	}

	RegistrationFormModal edit(String email) {
		click(rowAction(email, 'Edit'))
		RegistrationFormModal modal = new RegistrationFormModal()
		modal.verifyDisplayed()
		return modal
	}

	WebTablesPage delete(String email) {
		TestObject button = rowAction(email, 'Delete')
		click(button)
		waitUntilGone(button)
		return this
	}

	WebTablesPage search(String text) {
		type(searchBox, text)
		return this
	}

	WebTablesPage clearSearch() {
		return search('')
	}

	/** Every non-empty row as a list of cell texts (without the Action column). */
	List<List<String>> rows() {
		return elements(rows, 3)
				.collect { WebElement row -> row.findElements(By.tagName('td')).collect { it.getText().trim() } }
				.findAll { List<String> cells -> cells.any { it } }
				.collect { List<String> cells -> cells.size() > 6 ? cells[0..5] : cells }
	}

	List<String> emails() {
		return rows().collect { it[3] }
	}

	List<String> rowFor(String email) {
		return rows().find { it[3] == email }
	}

	private static TestObject rowAction(String email, String action) {
		return Locator.xpath("//table//tbody/tr[td[normalize-space()=${Locator.literal(email)}]]//span[@title='${action}']",
				"${action} ${email}")
	}
}
