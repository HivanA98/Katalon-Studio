package com.cura.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.Config
import com.qa.core.web.BasePage
import com.qa.core.web.Browser
import com.qa.core.web.Locator

class HomePage extends BasePage {

	final MenuComponent menu = new MenuComponent()

	private final TestObject heading = Locator.css('header h1')
	private final TestObject makeAppointmentButton = Locator.id('btn-make-appointment')

	@Override
	protected TestObject pageMarker() {
		return makeAppointmentButton
	}

	static String baseUrl() {
		String url = Config.text('URL', 'https://katalon-demo-cura.herokuapp.com/')
		return url.endsWith('/') ? url : "${url}/"
	}

	static HomePage open() {
		Browser.open(baseUrl())
		HomePage home = new HomePage()
		home.verifyDisplayed()
		return home
	}

	String heading() {
		return textOf(heading)
	}

	/** Anonymous users are sent to the login page first. */
	LoginPage makeAppointment() {
		click(makeAppointmentButton)
		LoginPage login = new LoginPage()
		login.verifyDisplayed()
		return login
	}
}
