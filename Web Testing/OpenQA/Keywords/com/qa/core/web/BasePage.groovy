// GENERATED from shared/katalon-core - edit the original and run: python tools/sync_core.py
package com.qa.core.web

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.qa.core.Check
import com.qa.core.Config
import com.qa.core.Wait

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

/**
 * Base class for every page object and page component.
 *
 * Page objects expose *intent* ("addToCart", "bookAppointment") and keep the WebUI calls,
 * waits and locators private. All interactions wait for the element first, so test scripts
 * never need fixed delays.
 */
abstract class BasePage {

	protected final int timeout = Config.timeout()

	/** An element that is only present when this page is displayed. */
	protected abstract TestObject pageMarker()

	boolean isDisplayed(int seconds = timeout) {
		return WebUI.waitForElementVisible(pageMarker(), seconds, FailureHandling.OPTIONAL)
	}

	/** Fails the test if the page is not displayed, otherwise returns this page for chaining. */
	def verifyDisplayed() {
		Check.isTrue(isDisplayed(), "${getClass().simpleName} is displayed")
		return this
	}

	// ---- interactions -------------------------------------------------------------------

	protected void click(TestObject to) {
		WebUI.waitForElementClickable(to, timeout)
		WebUI.click(to)
	}

	/** Clicks through JavaScript; use for elements that are covered by ads or sticky headers. */
	protected void jsClick(TestObject to) {
		WebElement element = WebUI.findWebElement(to, timeout)
		WebUI.executeJavaScript('arguments[0].scrollIntoView({block: "center"}); arguments[0].click();', [element])
	}

	protected void doubleClick(TestObject to) {
		scrollTo(to)
		WebUI.doubleClick(to)
	}

	protected void rightClick(TestObject to) {
		scrollTo(to)
		WebUI.rightClick(to)
	}

	/**
	 * Replaces the field content. Clearing uses real key strokes because Selenium's clear()
	 * does not fire the input events React/Vue forms listen to, which leaves stale state behind.
	 */
	protected void type(TestObject to, String value) {
		clearField(to)
		if (value) {
			WebUI.sendKeys(to, value)
		}
	}

	protected void clearField(TestObject to) {
		WebUI.waitForElementVisible(to, timeout)
		Keys modifier = System.getProperty('os.name').toLowerCase().contains('mac') ? Keys.COMMAND : Keys.CONTROL
		WebUI.sendKeys(to, Keys.chord(modifier, 'a'))
		WebUI.sendKeys(to, Keys.chord(Keys.BACK_SPACE))
	}

	protected void selectByLabel(TestObject to, String label) {
		WebUI.waitForElementVisible(to, timeout)
		WebUI.selectOptionByLabel(to, label, false)
	}

	protected void selectByValue(TestObject to, String value) {
		WebUI.waitForElementVisible(to, timeout)
		WebUI.selectOptionByValue(to, value, false)
	}

	protected void setChecked(TestObject to, boolean checked) {
		if (WebUI.findWebElement(to, timeout).isSelected() != checked) {
			click(to)
		}
	}

	protected void scrollTo(TestObject to) {
		WebUI.waitForElementPresent(to, timeout)
		WebUI.scrollToElement(to, timeout)
	}

	// ---- queries ------------------------------------------------------------------------

	protected String textOf(TestObject to) {
		WebUI.waitForElementVisible(to, timeout)
		return WebUI.getText(to)?.trim()
	}

	protected String valueOf(TestObject to) {
		return attributeOf(to, 'value')
	}

	protected String attributeOf(TestObject to, String attribute) {
		WebUI.waitForElementPresent(to, timeout)
		return WebUI.getAttribute(to, attribute)
	}

	protected List<WebElement> elements(TestObject to, int seconds = timeout) {
		if (!WebUI.waitForElementPresent(to, seconds, FailureHandling.OPTIONAL)) {
			return []
		}
		return WebUI.findWebElements(to, 1)
	}

	protected List<String> textsOf(TestObject to, int seconds = timeout) {
		return elements(to, seconds).collect { it.getText().trim() }
	}

	protected int countOf(TestObject to, int seconds = 2) {
		return elements(to, seconds).size()
	}

	protected boolean isVisible(TestObject to, int seconds = 2) {
		return WebUI.waitForElementVisible(to, seconds, FailureHandling.OPTIONAL)
	}

	protected boolean isPresent(TestObject to, int seconds = 2) {
		return WebUI.waitForElementPresent(to, seconds, FailureHandling.OPTIONAL)
	}

	protected boolean waitUntilGone(TestObject to, int seconds = timeout) {
		return WebUI.waitForElementNotPresent(to, seconds, FailureHandling.OPTIONAL)
	}

	protected boolean waitForUrlContaining(String fragment, int seconds = timeout) {
		return Wait.until(seconds) { WebUI.getUrl().contains(fragment) }
	}

	// ---- page hygiene -------------------------------------------------------------------

	/** Removes elements matching the CSS selectors (ads, cookie banners, sticky footers). */
	protected void removeElements(String... cssSelectors) {
		String script = '''
			arguments[0].forEach(function (selector) {
				document.querySelectorAll(selector).forEach(function (el) { el.remove(); });
			});
		'''
		WebUI.executeJavaScript(script, [cssSelectors.toList()])
	}

	protected static void log(String message) {
		KeywordUtil.logInfo(message)
	}
}
