package com.saucedemo.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator
import com.saucedemo.models.Customer

class CheckoutInformationPage extends BasePage {

	private final TestObject firstName = Locator.id('first-name')
	private final TestObject lastName = Locator.id('last-name')
	private final TestObject postalCode = Locator.id('postal-code')
	private final TestObject continueButton = Locator.id('continue')
	private final TestObject cancelButton = Locator.id('cancel')
	private final TestObject errorMessage = Locator.css("[data-test='error']")

	@Override
	protected TestObject pageMarker() {
		return postalCode
	}

	CheckoutInformationPage fill(Customer customer) {
		type(firstName, customer.firstName)
		type(lastName, customer.lastName)
		type(postalCode, customer.postalCode)
		return this
	}

	CheckoutOverviewPage continueWith(Customer customer) {
		fill(customer)
		click(continueButton)
		CheckoutOverviewPage overview = new CheckoutOverviewPage()
		overview.verifyDisplayed()
		return overview
	}

	/** Submits the form and stays on this page (negative scenarios). */
	CheckoutInformationPage submitExpectingError(Customer customer) {
		fill(customer)
		click(continueButton)
		return this
	}

	String error() {
		return textOf(errorMessage)
	}

	CartPage cancel() {
		click(cancelButton)
		CartPage cart = new CartPage()
		cart.verifyDisplayed()
		return cart
	}
}
