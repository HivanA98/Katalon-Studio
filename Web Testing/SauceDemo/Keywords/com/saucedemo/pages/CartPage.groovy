package com.saucedemo.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator
import com.saucedemo.models.Product
import com.saucedemo.support.Money

class CartPage extends BasePage {

	final HeaderComponent header = new HeaderComponent()

	private final TestObject cartList = Locator.css('.cart_list')
	private final TestObject itemNames = Locator.css('.cart_item .inventory_item_name')
	private final TestObject itemPrices = Locator.css('.cart_item .inventory_item_price')
	private final TestObject quantities = Locator.css('.cart_item .cart_quantity')
	private final TestObject checkoutButton = Locator.id('checkout')
	private final TestObject continueShoppingButton = Locator.id('continue-shopping')

	@Override
	protected TestObject pageMarker() {
		return cartList
	}

	List<Product> items() {
		List<String> names = textsOf(itemNames, 2)
		List<String> prices = textsOf(itemPrices, 2)
		return [names, prices].transpose().collect { name, price -> new Product(name, Money.parse(price)) }
	}

	List<String> itemNames() {
		return textsOf(itemNames, 2)
	}

	List<Integer> quantities() {
		return textsOf(quantities, 2).collect { it.toInteger() }
	}

	CartPage remove(String productName) {
		String card = "//div[contains(concat(' ', normalize-space(@class), ' '), ' cart_item ')][.//div[contains(@class,'inventory_item_name') and normalize-space()=${Locator.literal(productName)}]]"
		TestObject button = Locator.xpath("${card}//button", "remove ${productName}")
		click(button)
		waitUntilGone(button)
		return this
	}

	CheckoutInformationPage checkout() {
		click(checkoutButton)
		CheckoutInformationPage information = new CheckoutInformationPage()
		information.verifyDisplayed()
		return information
	}

	InventoryPage continueShopping() {
		click(continueShoppingButton)
		InventoryPage inventory = new InventoryPage()
		inventory.verifyDisplayed()
		return inventory
	}
}
