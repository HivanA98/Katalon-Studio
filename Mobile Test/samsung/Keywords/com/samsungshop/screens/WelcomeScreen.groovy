package com.samsungshop.screens

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.mobile.BaseScreen
import com.qa.core.mobile.MobileApp
import com.qa.core.mobile.MobileLocator

/** Onboarding screen of the Shop Samsung app. */
class WelcomeScreen extends BaseScreen {

	private final TestObject getStarted = MobileLocator.repo('Object Repository/SAMSUNG S22 ULTRA/android.widget.TextView - GET STARTED')

	@Override
	protected TestObject screenMarker() {
		return getStarted
	}

	static WelcomeScreen launch() {
		MobileApp.start('MobileApp/Shop Samsung.apk', true)
		WelcomeScreen screen = new WelcomeScreen()
		screen.scrollToText('GET STARTED')
		screen.verifyDisplayed()
		return screen
	}

	ShopHomeScreen getStarted() {
		tap(getStarted)
		ShopHomeScreen home = new ShopHomeScreen()
		home.verifyDisplayed()
		return home
	}
}
