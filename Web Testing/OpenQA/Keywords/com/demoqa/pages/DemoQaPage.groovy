package com.demoqa.pages

import com.qa.core.Config
import com.qa.core.web.BasePage
import com.qa.core.web.Browser

/**
 * Common behaviour of DemoQA pages: direct navigation and removal of the ad slots
 * that otherwise cover buttons in a 1440x900 viewport.
 */
abstract class DemoQaPage extends BasePage {

	/** Path below the site root, e.g. 'text-box'. */
	protected abstract String path()

	static String baseUrl() {
		String url = Config.text('Web', 'https://demoqa.com')
		return url.endsWith('/') ? url[0..-2] : url
	}

	/** Opens the page in a new browser (first page of a test). */
	protected static <T extends DemoQaPage> T launch(T page) {
		Browser.open("${baseUrl()}/${page.path()}")
		return page.ready()
	}

	/** Navigates the current browser to the page. */
	protected static <T extends DemoQaPage> T goTo(T page) {
		Browser.navigate("${baseUrl()}/${page.path()}")
		return page.ready()
	}

	protected <T extends DemoQaPage> T ready() {
		verifyDisplayed()
		hideAds()
		return (T) this
	}

	protected void hideAds() {
		removeElements('iframe', 'footer', '#fixedban', "[id^='Ad.Plus']", '#RightSide_Advertisement')
	}
}
