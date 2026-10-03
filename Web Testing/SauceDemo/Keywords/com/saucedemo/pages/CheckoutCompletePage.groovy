package com.saucedemo.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator

class CheckoutCompletePage extends BasePage {

	final HeaderComponent header = new HeaderComponent()

	private final TestObject title = Locator.css('.title')
	private final TestObject completeHeader = Locator.css('.complete-header')
	private final TestObject backHomeButton = Locator.id('back-to-products')

	@Override
	protected TestObject pageMarker() {
		return completeHeader
	}

	String title() {
		return textOf(title)
	}

	String confirmationMessage() {
		return textOf(completeHeader)
	}

	InventoryPage backHome() {
		click(backHomeButton)
		InventoryPage inventory = new InventoryPage()
		inventory.verifyDisplayed()
		return inventory
	}
}
