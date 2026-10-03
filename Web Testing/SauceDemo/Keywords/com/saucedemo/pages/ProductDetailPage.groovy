package com.saucedemo.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator
import com.saucedemo.models.Product
import com.saucedemo.support.Money

class ProductDetailPage extends BasePage {

	final HeaderComponent header = new HeaderComponent()

	private final TestObject name = Locator.css('.inventory_details_name')
	private final TestObject price = Locator.css('.inventory_details_price')
	private final TestObject description = Locator.css('.inventory_details_desc')
	private final TestObject actionButton = Locator.xpath("//div[contains(@class,'inventory_details_desc_container')]//button")
	private final TestObject backButton = Locator.id('back-to-products')

	@Override
	protected TestObject pageMarker() {
		return name
	}

	Product product() {
		return new Product(textOf(name), Money.parse(textOf(price)))
	}

	String description() {
		return textOf(description)
	}

	ProductDetailPage addToCart() {
		click(actionButton)
		return this
	}

	String buttonLabel() {
		return textOf(actionButton)
	}

	InventoryPage backToProducts() {
		click(backButton)
		InventoryPage inventory = new InventoryPage()
		inventory.verifyDisplayed()
		return inventory
	}
}
