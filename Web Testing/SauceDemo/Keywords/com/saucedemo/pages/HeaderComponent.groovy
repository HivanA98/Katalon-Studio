package com.saucedemo.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator

/**
 * Header and burger menu shared by every page behind the login.
 */
class HeaderComponent extends BasePage {

	private final TestObject cartLink = Locator.css('.shopping_cart_link')
	private final TestObject cartBadge = Locator.css('.shopping_cart_badge')
	private final TestObject menuButton = Locator.id('react-burger-menu-btn')
	private final TestObject closeMenuButton = Locator.id('react-burger-cross-btn')
	private final TestObject allItemsLink = Locator.id('inventory_sidebar_link')
	private final TestObject logoutLink = Locator.id('logout_sidebar_link')
	private final TestObject resetLink = Locator.id('reset_sidebar_link')

	@Override
	protected TestObject pageMarker() {
		return cartLink
	}

	/** Number shown on the cart badge; the badge is absent when the cart is empty. */
	int cartCount() {
		return isPresent(cartBadge, 1) ? textOf(cartBadge).toInteger() : 0
	}

	CartPage openCart() {
		click(cartLink)
		CartPage cart = new CartPage()
		cart.verifyDisplayed()
		return cart
	}

	InventoryPage goToAllItems() {
		openMenu()
		click(allItemsLink)
		InventoryPage inventory = new InventoryPage()
		inventory.verifyDisplayed()
		return inventory
	}

	LoginPage logout() {
		openMenu()
		click(logoutLink)
		LoginPage login = new LoginPage()
		login.verifyDisplayed()
		return login
	}

	/** Empties the cart server-side state and restores all 'Add to cart' buttons. */
	void resetAppState() {
		openMenu()
		click(resetLink)
		click(closeMenuButton)
	}

	private void openMenu() {
		click(menuButton)
		isVisible(logoutLink, timeout)
	}
}
