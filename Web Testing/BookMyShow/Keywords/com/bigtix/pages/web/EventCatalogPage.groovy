package com.bigtix.pages.web

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.Config
import com.qa.core.web.BasePage
import com.qa.core.web.Browser
import com.qa.core.web.Locator

/** Public BigTix storefront: region picker and event list (legacy "Task1/Main Page"). */
class EventCatalogPage extends BasePage {

	private final TestObject singaporeButton = Locator.repo('Object Repository/Task1/Main Page/01button_Singapore')
	private final TestObject viewEventButton = Locator.repo('Object Repository/Task1/Main Page/02View')

	@Override
	protected TestObject pageMarker() {
		return singaporeButton
	}

	static EventCatalogPage open() {
		Browser.open(Config.text('StorefrontUrl', 'https://sg.uat.bigtix.dev/'))
		EventCatalogPage page = new EventCatalogPage()
		page.verifyDisplayed()
		return page
	}

	TicketSelectionPage openFirstSingaporeEvent() {
		click(singaporeButton)
		click(viewEventButton)
		TicketSelectionPage tickets = new TicketSelectionPage()
		tickets.verifyDisplayed()
		return tickets
	}
}
