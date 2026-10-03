package com.cura.pages

import com.cura.models.Appointment
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.qa.core.web.BasePage
import com.qa.core.web.Browser
import com.qa.core.web.Locator

class AppointmentPage extends BasePage {

	final MenuComponent menu = new MenuComponent()

	private final TestObject facility = Locator.id('combo_facility')
	private final TestObject readmission = Locator.id('chk_hospotal_readmission')
	private final TestObject visitDate = Locator.id('txt_visit_date')
	private final TestObject comment = Locator.id('txt_comment')
	private final TestObject bookButton = Locator.id('btn-book-appointment')
	private final TestObject heading = Locator.css('#appointment h2')

	@Override
	protected TestObject pageMarker() {
		return bookButton
	}

	/** Re-opens the form for a signed-in user (e.g. after a booking). */
	static AppointmentPage openForSignedInUser() {
		Browser.navigate("${HomePage.baseUrl()}#appointment")
		AppointmentPage page = new AppointmentPage()
		page.verifyDisplayed()
		return page
	}

	String heading() {
		return textOf(heading)
	}

	AppointmentPage fill(Appointment appointment) {
		selectByValue(facility, appointment.facility.label)
		setChecked(readmission, appointment.readmission)
		click(Locator.id(appointment.program.radioId))
		type(visitDate, appointment.visitDate)
		closeDatePicker()
		type(comment, appointment.comment)
		return this
	}

	ConfirmationPage book(Appointment appointment) {
		fill(appointment)
		click(bookButton)
		ConfirmationPage confirmation = new ConfirmationPage()
		confirmation.verifyDisplayed()
		return confirmation
	}

	/** Submits the form expecting browser-side validation to keep the user on this page. */
	AppointmentPage submitExpectingValidation(Appointment appointment) {
		fill(appointment)
		click(bookButton)
		return this
	}

	/** True when the browser's constraint validation rejects the empty, required visit date. */
	boolean visitDateMissing() {
		return WebUI.executeJavaScript("return document.getElementById('txt_visit_date').validity.valueMissing;", null) as boolean
	}

	String selectedFacility() {
		return valueOf(facility)
	}

	/** The bootstrap date picker overlays the comment field; clicking outside closes it. */
	private void closeDatePicker() {
		click(heading)
		waitUntilGone(Locator.css('.datepicker-dropdown'), 3)
	}
}
