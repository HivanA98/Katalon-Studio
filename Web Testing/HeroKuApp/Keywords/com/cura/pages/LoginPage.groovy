package com.cura.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.Config
import com.qa.core.web.BasePage
import com.qa.core.web.Locator

class LoginPage extends BasePage {

	static final String LOGIN_FAILED = 'Login failed! Please ensure the username and password are valid.'

	private final TestObject username = Locator.id('txt-username')
	private final TestObject password = Locator.id('txt-password')
	private final TestObject loginButton = Locator.id('btn-login')
	private final TestObject error = Locator.css('#login p.text-danger')

	@Override
	protected TestObject pageMarker() {
		return loginButton
	}

	/** Demo credentials published on the CURA login page. */
	static String demoUsername() {
		return Config.text('Username', 'John Doe')
	}

	static String demoPassword() {
		return Config.text('DemoPassword', 'ThisIsNotAPassword')
	}

	AppointmentPage loginAsDemoUser() {
		return loginAs(demoUsername(), demoPassword())
	}

	AppointmentPage loginAs(String user, String secret) {
		submit(user, secret)
		AppointmentPage appointment = new AppointmentPage()
		appointment.verifyDisplayed()
		return appointment
	}

	LoginPage submit(String user, String secret) {
		type(username, user)
		type(password, secret)
		click(loginButton)
		return this
	}

	boolean hasError() {
		return isVisible(error)
	}

	String error() {
		return textOf(error)
	}
}
