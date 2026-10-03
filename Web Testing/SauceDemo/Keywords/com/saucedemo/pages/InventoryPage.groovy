package com.saucedemo.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator
import com.saucedemo.models.Product
import com.saucedemo.models.SortOption
import com.saucedemo.support.Money

class InventoryPage extends BasePage {

	final HeaderComponent header = new HeaderComponent()

	private final TestObject inventoryList = Locator.css('.inventory_list')
	private final TestObject title = Locator.css('.title')
	private final TestObject itemNames = Locator.css('.inventory_item_name')
	private final TestObject itemPrices = Locator.css('.inventory_item_price')
	private final TestObject sortSelect = Locator.css('.product_sort_container')

	@Override
	protected TestObject pageMarker() {
		return inventoryList
	}

	String title() {
		return textOf(title)
	}

	List<Product> products() {
		List<String> names = textsOf(itemNames)
		List<String> prices = textsOf(itemPrices)
		return [names, prices].transpose().collect { name, price -> new Product(name, Money.parse(price)) }
	}

	List<String> productNames() {
		return textsOf(itemNames)
	}

	BigDecimal priceOf(String productName) {
		return Money.parse(textOf(itemPart(productName, "div[contains(@class,'inventory_item_price')]")))
	}

	InventoryPage sortBy(SortOption option) {
		selectByValue(sortSelect, option.value)
		return this
	}

	String selectedSort() {
		return valueOf(sortSelect)
	}

	InventoryPage addToCart(String productName) {
		TestObject button = actionButton(productName)
		if (textOf(button) != 'Add to cart') {
			throw new IllegalStateException("'${productName}' is already in the cart")
		}
		click(button)
		return this
	}

	InventoryPage addToCart(List<String> productNames) {
		productNames.each { addToCart(it) }
		return this
	}

	InventoryPage removeFromCart(String productName) {
		click(actionButton(productName))
		return this
	}

	/** Label of the item's action button: 'Add to cart' or 'Remove'. */
	String buttonLabel(String productName) {
		return textOf(actionButton(productName))
	}

	ProductDetailPage openProduct(String productName) {
		click(itemPart(productName, "div[contains(@class,'inventory_item_name')]"))
		ProductDetailPage details = new ProductDetailPage()
		details.verifyDisplayed()
		return details
	}

	private TestObject actionButton(String productName) {
		return itemPart(productName, 'button')
	}

	/** Locates a descendant of the inventory card whose title is {@code productName}. */
	private static TestObject itemPart(String productName, String relativeXpath) {
		String card = "//div[contains(concat(' ', normalize-space(@class), ' '), ' inventory_item ')]" +
				"[.//div[contains(@class,'inventory_item_name') and normalize-space()=${Locator.literal(productName)}]]"
		return Locator.xpath("${card}//${relativeXpath}", "${productName} › ${relativeXpath}")
	}
}
