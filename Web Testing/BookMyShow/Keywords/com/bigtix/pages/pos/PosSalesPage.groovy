package com.bigtix.pages.pos

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator

/** Event search, session and quantity selection (legacy "Task2/Search" + "Task2/Order"). */
class PosSalesPage extends BasePage {

	private final TestObject sellProducts = Locator.repo('Task2/Login/01VerifiySellProducts')
	private final TestObject searchBox = Locator.repo('Task2/Search/04Search')
	private final TestObject firstResult = Locator.repo('Task2/Search/05Result')
	private final TestObject schedule = Locator.repo('Task2/Search/06Schendule')
	private final TestObject confirmSelection = Locator.repo('Task2/Search/07ConfirmSelection')
	private final TestObject manualQuantityButton = Locator.repo('Task2/Order/08AddManual')
	private final TestObject quantityInput = Locator.repo('Task2/Order/08InputText')
	private final TestObject confirmQuantity = Locator.repo('Task2/Order/09ConfirmQuantity')
	private final TestObject addToCart = Locator.repo('Task2/Order/10AddToCart')
	private final TestObject cartProducts = Locator.repo('Task2/Order/Validation_Products')
	private final TestObject seatLayout = Locator.repo('Task2/Order/Validation_Layout')
	private final TestObject checkoutButton = Locator.repo('Task2/Order/11CHECKOUT')

	@Override
	protected TestObject pageMarker() {
		return sellProducts
	}

	PosSalesPage selectEvent(String eventName) {
		type(searchBox, eventName)
		click(firstResult)
		click(schedule)
		click(confirmSelection)
		return this
	}

	PosSalesPage addTickets(int quantity) {
		isVisible(manualQuantityButton, timeout)
		type(quantityInput, quantity.toString())
		click(confirmQuantity)
		click(addToCart)
		return this
	}

	boolean cartIsPopulated() {
		return isVisible(cartProducts, timeout) && isVisible(seatLayout, timeout)
	}

	PosCheckoutPage checkout() {
		click(checkoutButton)
		PosCheckoutPage checkout = new PosCheckoutPage()
		checkout.verifyDisplayed()
		return checkout
	}
}
