package com.cura.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator

class HistoryPage extends BasePage {

	final MenuComponent menu = new MenuComponent()

	private final TestObject section = Locator.css('section#history')
	private final TestObject visitDates = Locator.css('section#history .panel-heading')
	private final TestObject facilities = Locator.css('section#history [id=facility]')
	private final TestObject comments = Locator.css('section#history [id=comment]')

	@Override
	protected TestObject pageMarker() {
		return section
	}

	int entryCount() {
		return countOf(visitDates)
	}

	List<String> visitDates() {
		return textsOf(visitDates, 2)
	}

	List<String> facilities() {
		return textsOf(facilities, 2)
	}

	List<String> comments() {
		return textsOf(comments, 2)
	}
}
