package com.bigtix.pages.pos

import com.bigtix.models.Buyer
import com.bigtix.models.Card
import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator

/** Customer details and Mastercard payment (legacy "Task2/Contact" + "Task2/Payment"). */
class PosCheckoutPage extends BasePage {

	private final TestObject fullName = Locator.repo('Task2/Contact/12FullName')
	private final TestObject email = Locator.repo('Task2/Contact/13Email')
	private final TestObject phone = Locator.repo('Task2/Contact/16SingaporeNumber')
	private final TestObject address = Locator.repo('Task2/Contact/17Addreas')
	private final TestObject country = Locator.repo('Task2/Contact/18Country')
	private final TestObject countryOption = Locator.repo('Task2/Contact/19SingaporeCountry')
	private final TestObject postalCode = Locator.repo('Task2/Contact/20PostalCode')
	private final TestObject confirmContact = Locator.repo('Task2/Contact/21ButtonCONFIRM')

	private final TestObject mastercardOption = Locator.repo('Task2/Payment/22MasterCard')
	private final TestObject cardNumber = Locator.repo('Task2/Payment/23CardNumber')
	private final TestObject cardHolder = Locator.repo('Task2/Payment/24CardHolderName')
	private final TestObject expiryMonth = Locator.repo('Task2/Payment/25EXMonth')
	private final TestObject expiryYear = Locator.repo('Task2/Payment/25EXYear')
	private final TestObject cvv = Locator.repo('Task2/Payment/26CVV')
	private final TestObject confirmPayment = Locator.repo('Task2/Payment/27buttonCONFIRM')
	private final TestObject successMessage = Locator.repo('Task2/Payment/27Tickets successfully purchased')
	private final TestObject bookingIdLabel = Locator.repo('Task2/Payment/28BookingID')
	private final TestObject backToHomeButton = Locator.repo('Task2/Payment/29button_BACK TO HOME')

	@Override
	protected TestObject pageMarker() {
		return fullName
	}

	PosCheckoutPage enterBuyer(Buyer buyer) {
		type(fullName, buyer.fullName)
		type(email, buyer.email)
		type(phone, buyer.phone)
		type(address, buyer.address)
		type(country, buyer.country)
		click(countryOption)
		type(postalCode, buyer.postalCode)
		click(confirmContact)
		return this
	}

	PosCheckoutPage payWithMastercard(Card card) {
		click(mastercardOption)
		type(cardNumber, card.number)
		type(cardHolder, card.holder)
		type(expiryMonth, card.expiryMonth)
		type(expiryYear, card.expiryYear)
		type(cvv, card.cvv)
		click(confirmPayment)
		return this
	}

	boolean purchaseSucceeded() {
		return isVisible(successMessage, timeout)
	}

	String bookingId() {
		return textOf(bookingIdLabel)
	}

	void backToHome() {
		click(backToHomeButton)
	}
}
