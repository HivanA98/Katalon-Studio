package com.bigtix.pages.web

import com.bigtix.models.Card
import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator

/** Card payment step of the storefront (legacy "Task1/Paid"). */
class CardPaymentPage extends BasePage {

	private final TestObject visaOption = Locator.repo('Task1/Paid/15Payment_Visa')
	private final TestObject holder = Locator.repo('Task1/Paid/16Cardholder_Name')
	private final TestObject number = Locator.repo('Task1/Paid/17Card_Number')
	private final TestObject month = Locator.repo('Task1/Paid/18Ex_Month')
	private final TestObject year = Locator.repo('Task1/Paid/19Ex_Year')
	private final TestObject cvv = Locator.repo('Task1/Paid/20CVV')
	private final TestObject payButton = Locator.repo('Task1/Paid/21Pay_Button')
	private final TestObject backToHomeButton = Locator.repo('Task1/Paid/22_Back_To_Home')

	@Override
	protected TestObject pageMarker() {
		return visaOption
	}

	CardPaymentPage payWithVisa(Card card) {
		click(visaOption)
		type(holder, card.holder)
		type(number, card.number)
		type(month, card.expiryMonth)
		type(year, card.expiryYear)
		type(cvv, card.cvv)
		click(payButton)
		return this
	}

	/** The "Back to home" button only appears once the payment succeeded. */
	boolean paymentSucceeded() {
		return isVisible(backToHomeButton, timeout)
	}

	void backToHome() {
		click(backToHomeButton)
	}
}
