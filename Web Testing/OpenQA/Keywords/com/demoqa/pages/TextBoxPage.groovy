package com.demoqa.pages

import com.demoqa.models.UserForm
import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.Locator

class TextBoxPage extends DemoQaPage {

	private final TestObject fullName = Locator.id('userName')
	private final TestObject email = Locator.id('userEmail')
	private final TestObject currentAddress = Locator.css('textarea#currentAddress')
	private final TestObject permanentAddress = Locator.css('textarea#permanentAddress')
	private final TestObject submitButton = Locator.id('submit')
	private final TestObject outputLines = Locator.css('#output p')

	@Override
	protected String path() {
		return 'text-box'
	}

	@Override
	protected TestObject pageMarker() {
		return submitButton
	}

	static TextBoxPage open() {
		return launch(new TextBoxPage())
	}

	/** Reloads the form so the next scenario starts without validation state. */
	TextBoxPage reload() {
		return goTo(new TextBoxPage())
	}

	TextBoxPage submit(UserForm form) {
		type(fullName, form.fullName)
		type(email, form.email)
		type(currentAddress, form.currentAddress)
		type(permanentAddress, form.permanentAddress)
		jsClick(submitButton)
		return this
	}

	/**
	 * The output panel as a map, e.g. [Name: 'Jane', Email: 'jane@example.com', ...].
	 * Labels are normalised ('Current Address :' -> 'Current Address').
	 */
	Map<String, String> output() {
		return textsOf(outputLines, 3).collectEntries { String line ->
			int separator = line.indexOf(':')
			[(line.substring(0, separator).trim()): line.substring(separator + 1).trim()]
		}
	}

	/** DemoQA flags an invalid e-mail with the 'field-error' class instead of a message. */
	boolean emailFlaggedAsInvalid() {
		return attributeOf(email, 'class').split(/\s+/).contains('field-error')
	}
}
