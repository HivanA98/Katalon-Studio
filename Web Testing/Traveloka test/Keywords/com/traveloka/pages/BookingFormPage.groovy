package com.traveloka.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator
import com.traveloka.models.Contact

/** Contact and driver details (legacy "Task3/Part 3"). */
class BookingFormPage extends BasePage {

	private final TestObject contactName = Locator.repo('11-20/17_Full_Name')
	private final TestObject contactPhone = Locator.repo('11-20/18_Mobile_Number')
	private final TestObject contactEmail = Locator.repo('11-20/19_Email')
	private final TestObject saveContact = Locator.repo('11-20/20_Save_Contact')
	private final TestObject titleSelect = Locator.repo('21-33/21_select_Mr.Mrs.Ms')
	private final TestObject titleMr = Locator.repo('21-33/22_Mr')
	private final TestObject driverName = Locator.repo('21-33/23_Full_Name_Driver')
	private final TestObject driverPhone = Locator.repo('21-33/24_Mobile_Number_Driver')
	private final TestObject saveDriver = Locator.repo('21-33/25_Save_Contact_Driver')
	private final TestObject confirmBooking = Locator.repo('21-33/26_Confirm_Booking')

	@Override
	protected TestObject pageMarker() {
		return contactName
	}

	PaymentPage submit(Contact booker, Contact driver) {
		type(contactName, booker.fullName)
		type(contactPhone, booker.phone)
		type(contactEmail, booker.email)
		click(saveContact)

		click(titleSelect)
		click(titleMr)
		type(driverName, driver.fullName)
		type(driverPhone, driver.phone)
		click(saveDriver)

		click(confirmBooking)
		PaymentPage payment = new PaymentPage()
		payment.verifyDisplayed()
		return payment
	}
}
