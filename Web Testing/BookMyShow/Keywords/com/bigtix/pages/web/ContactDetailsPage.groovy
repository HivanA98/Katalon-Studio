package com.bigtix.pages.web

import com.bigtix.models.Buyer
import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator

/** Checkout contact form (legacy "Task1/Addres"). */
class ContactDetailsPage extends BasePage {

	private final TestObject checkoutButton = Locator.repo('Task1/Addres/10Checkout')
	private final TestObject fullName = Locator.repo('Task1/Addres/11Full_Name')
	private final TestObject email = Locator.repo('Task1/Addres/12Email')
	private final TestObject phone = Locator.repo('Task1/Addres/13Singapore_Phone')
	private final TestObject confirmDetailsButton = Locator.repo('Task1/Addres/14Confirm_Details')

	@Override
	protected TestObject pageMarker() {
		return checkoutButton
	}

	CardPaymentPage confirm(Buyer buyer) {
		click(checkoutButton)
		type(fullName, buyer.fullName)
		type(email, buyer.email)
		type(phone, buyer.phone)
		click(confirmDetailsButton)
		CardPaymentPage payment = new CardPaymentPage()
		payment.verifyDisplayed()
		return payment
	}
}
