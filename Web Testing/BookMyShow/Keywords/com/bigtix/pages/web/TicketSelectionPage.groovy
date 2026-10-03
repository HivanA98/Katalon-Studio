package com.bigtix.pages.web

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator

/** Bundle, session and quantity selection (legacy "Task1/Order"). */
class TicketSelectionPage extends BasePage {

	private final TestObject bundle = Locator.repo('Object Repository/Task1/Order/03Bundle')
	private final TestObject bookNowButton = Locator.repo('Task1/Order/04Book now')
	private final TestObject sessionButton = Locator.repo('Task1/Order/05button_839 AM')
	private final TestObject quantityInput = Locator.repo('Task1/Order/06Input_Quantity_Dummy')
	private final TestObject confirmButton = Locator.repo('Task1/Order/09button_Confirm')

	@Override
	protected TestObject pageMarker() {
		return bundle
	}

	ContactDetailsPage book(int quantity) {
		click(bundle)
		click(bookNowButton)
		click(sessionButton)
		type(quantityInput, quantity.toString())
		click(confirmButton)
		ContactDetailsPage contact = new ContactDetailsPage()
		contact.verifyDisplayed()
		return contact
	}
}
