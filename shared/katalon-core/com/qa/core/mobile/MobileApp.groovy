package com.qa.core.mobile

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil

/**
 * Application lifecycle for mobile tests.
 */
class MobileApp {

	/**
	 * Installs (if needed) and launches the application.
	 *
	 * @param projectRelativeApk path such as 'MobileApp/Calculator.apk'; resolved against the project
	 *        folder so it works on every OS (the old scripts used Windows-only back-slashes).
	 */
	static void start(String projectRelativeApk, boolean uninstallAfterClose = false) {
		File apk = new File(RunConfiguration.getProjectDir(), projectRelativeApk)
		if (!apk.isFile()) {
			KeywordUtil.markFailedAndStop("Application not found: ${apk.absolutePath}")
		}
		Mobile.startApplication(apk.absolutePath, uninstallAfterClose)
	}

	static boolean isRunning() {
		try {
			return MobileDriverFactory.getDriver() != null
		} catch (Exception ignored) {
			return false
		}
	}

	static void close() {
		if (isRunning()) {
			Mobile.closeApplication(FailureHandling.OPTIONAL)
		}
	}

	static void screenshot(String label) {
		if (isRunning()) {
			String safe = label.replaceAll(/[^A-Za-z0-9._-]+/, '_')
			Mobile.takeScreenshot("${RunConfiguration.getReportFolder()}/screenshots/${safe}.png", FailureHandling.OPTIONAL)
		}
	}
}
