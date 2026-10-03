package com.qa.core.web

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.qa.core.Config

/**
 * Browser lifecycle in one place: open with a deterministic viewport, close safely, capture evidence.
 */
class Browser {

	static void open(String url) {
		WebUI.openBrowser('')
		WebUI.setViewPortSize(Config.integer('viewportWidth', 1440), Config.integer('viewportHeight', 900))
		WebUI.navigateToUrl(url)
		WebUI.waitForPageLoad(Config.integer('pageLoadTimeout', 30))
	}

	static boolean isOpen() {
		try {
			return DriverFactory.getWebDriver() != null
		} catch (Exception ignored) {
			return false
		}
	}

	static void close() {
		if (isOpen()) {
			WebUI.closeBrowser(FailureHandling.OPTIONAL)
		}
	}

	static void navigate(String url) {
		WebUI.navigateToUrl(url)
		WebUI.waitForPageLoad(Config.integer('pageLoadTimeout', 30))
	}

	static String currentUrl() {
		return WebUI.getUrl()
	}

	/** Clears cookies plus local/session storage, e.g. to start a scenario from a clean state. */
	static void resetSession() {
		WebUI.deleteAllCookies()
		WebUI.executeJavaScript('window.localStorage.clear(); window.sessionStorage.clear();', null)
	}

	/** Saves a screenshot into the report folder and returns its path (or null if no browser is open). */
	static String screenshot(String label) {
		if (!isOpen()) {
			return null
		}
		String safe = label.replaceAll(/[^A-Za-z0-9._-]+/, '_')
		String path = "${RunConfiguration.getReportFolder()}/screenshots/${safe}_${System.currentTimeMillis()}.png"
		WebUI.takeScreenshot(path, FailureHandling.OPTIONAL)
		KeywordUtil.logInfo("Screenshot saved: ${path}")
		return path
	}
}
