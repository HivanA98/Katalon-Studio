package com.cura.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator

class ConfirmationPage extends BasePage {

	final MenuComponent menu = new MenuComponent()

	private final TestObject heading = Locator.css('#summary h2')
	private final TestObject facility = Locator.css('#summary #facility')
	private final TestObject readmission = Locator.css('#summary #hospital_readmission')
	private final TestObject program = Locator.css('#summary #program')
	private final TestObject visitDate = Locator.css('#summary #visit_date')
	private final TestObject comment = Locator.css('#summary #comment')

	@Override
	protected TestObject pageMarker() {
		return facility
	}

	String heading() {
		return textOf(heading)
	}

	/** The confirmation exactly as rendered, keyed like the CSV columns. */
	Map<String, String> details() {
		return [
			facility   : textOf(facility),
			readmission: textOf(readmission),
			program    : textOf(program),
			visitDate  : textOf(visitDate),
			comment    : textOf(comment)
		]
	}
}
