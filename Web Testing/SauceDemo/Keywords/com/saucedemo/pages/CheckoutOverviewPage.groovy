package com.saucedemo.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator
import com.saucedemo.models.Product
import com.saucedemo.support.Money

class CheckoutOverviewPage extends BasePage {

	private final TestObject summary = Locator.css('.summary_info')
	private final TestObject itemNames = Locator.css('.cart_item .inventory_item_name')
	private final TestObject itemPrices = Locator.css('.cart_item .inventory_item_price')
	private final TestObject itemTotal = Locator.css('.summary_subtotal_label')
	private final TestObject tax = Locator.css('.summary_tax_label')
	private final TestObject total = Locator.css('.summary_total_label')
	private final TestObject finishButton = Locator.id('finish')
	private final TestObject cancelButton = Locator.id('cancel')

	@Override
	protected TestObject pageMarker() {
		return summary
	}

	List<Product> items() {
		return [textsOf(itemNames), textsOf(itemPrices)].transpose().collect { name, price ->
			new Product(name, Money.parse(price))
		}
	}

	BigDecimal itemTotal() {
		return Money.parse(textOf(itemTotal))
	}

	BigDecimal tax() {
		return Money.parse(textOf(tax))
	}

	BigDecimal total() {
		return Money.parse(textOf(total))
	}

	CheckoutCompletePage finish() {
		click(finishButton)
		CheckoutCompletePage complete = new CheckoutCompletePage()
		complete.verifyDisplayed()
		return complete
	}

	InventoryPage cancel() {
		click(cancelButton)
		InventoryPage inventory = new InventoryPage()
		inventory.verifyDisplayed()
		return inventory
	}
}
