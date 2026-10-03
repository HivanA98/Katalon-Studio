package com.cura.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator

/**
 * Slide-in side menu available on every CURA page.
 */
class MenuComponent extends BasePage {

	private final TestObject toggle = Locator.id('menu-toggle')
	private final TestObject sidebar = Locator.css('#sidebar-wrapper.active')
	private final TestObject historyLink = Locator.css("#sidebar-wrapper a[href='history.php#history']")
	private final TestObject logoutLink = Locator.css("#sidebar-wrapper a[href='authenticate.php?logout']")
	private final TestObject loginLink = Locator.css("#sidebar-wrapper a[href='profile.php#login']")

	@Override
	protected TestObject pageMarker() {
		return toggle
	}

	HistoryPage openHistory() {
		open()
		click(historyLink)
		HistoryPage history = new HistoryPage()
		history.verifyDisplayed()
		return history
	}

	HomePage logout() {
		open()
		click(logoutLink)
		HomePage home = new HomePage()
		home.verifyDisplayed()
		return home
	}

	LoginPage goToLogin() {
		open()
		click(loginLink)
		LoginPage login = new LoginPage()
		login.verifyDisplayed()
		return login
	}

	private void open() {
		if (!isVisible(sidebar, 1)) {
			click(toggle)
			isVisible(sidebar, timeout)
		}
	}
}
