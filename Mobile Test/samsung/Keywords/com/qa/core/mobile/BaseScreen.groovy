// GENERATED from shared/katalon-core - edit the original and run: python tools/sync_core.py
package com.qa.core.mobile

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.qa.core.Check
import com.qa.core.Config

/**
 * Base class for mobile screen objects (the mobile counterpart of BasePage).
 */
abstract class BaseScreen {

	protected final int timeout = Config.timeout()

	/** An element that is only present when this screen is displayed. */
	protected abstract TestObject screenMarker()

	boolean isDisplayed(int seconds = timeout) {
		return Mobile.waitForElementPresent(screenMarker(), seconds, FailureHandling.OPTIONAL)
	}

	def verifyDisplayed() {
		Check.isTrue(isDisplayed(), "${getClass().simpleName} is displayed")
		return this
	}

	protected void tap(TestObject to) {
		Mobile.waitForElementPresent(to, timeout)
		Mobile.tap(to, timeout)
	}

	protected void type(TestObject to, String value) {
		Mobile.waitForElementPresent(to, timeout)
		Mobile.setText(to, value, timeout)
	}

	protected String textOf(TestObject to) {
		Mobile.waitForElementPresent(to, timeout)
		return Mobile.getText(to, timeout)?.trim()
	}

	protected boolean isPresent(TestObject to, int seconds = 3) {
		return Mobile.waitForElementPresent(to, seconds, FailureHandling.OPTIONAL)
	}

	protected void scrollToText(String text) {
		Mobile.scrollToText(text, FailureHandling.OPTIONAL)
	}
}
