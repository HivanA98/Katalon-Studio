package com.saucedemo.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.Config
import com.qa.core.web.BasePage
import com.qa.core.web.Browser
import com.qa.core.web.Locator
import com.saucedemo.models.User

class LoginPage extends BasePage {

	private final TestObject usernameInput = Locator.id('user-name')
	private final TestObject passwordInput = Locator.id('password')
	private final TestObject loginButton = Locator.id('login-button')
	private final TestObject errorMessage = Locator.css("[data-test='error']")
	private final TestObject errorCloseButton = Locator.css('.error-button')

	@Override
	protected TestObject pageMarker() {
		return loginButton
	}

	/** Opens a new browser on the login page. */
	static LoginPage open() {
		Browser.open(baseUrl())
		LoginPage page = new LoginPage()
		page.verifyDisplayed()
		return page
	}

	static String baseUrl() {
		return Config.text('URL', 'https://www.saucedemo.com/')
	}

	static String password() {
		return Config.text('UserPassword', 'secret_sauce')
	}

	InventoryPage loginAs(User user) {
		return loginWith(user.username, password())
	}

	InventoryPage loginWith(String username, String password) {
		submit(username, password)
		InventoryPage inventory = new InventoryPage()
		inventory.verifyDisplayed()
		return inventory
	}

	/** Submits the form without asserting the outcome (used for negative scenarios). */
	LoginPage submit(String username, String password) {
		type(usernameInput, username)
		type(passwordInput, password)
		click(loginButton)
		return this
	}

	boolean hasError() {
		return isVisible(errorMessage)
	}

	String error() {
		return textOf(errorMessage)
	}

	/**
	 * Swag Labs adds the 'error' class to both inputs while an error is shown.
	 * Tokens are compared exactly: the inputs always carry an unrelated 'input_error' class.
	 */
	boolean inputsHighlighted() {
		return [usernameInput, passwordInput].every { attributeOf(it, 'class').split(/\s+/).contains('error') }
	}

	LoginPage dismissError() {
		click(errorCloseButton)
		waitUntilGone(errorCloseButton)
		return this
	}
}
