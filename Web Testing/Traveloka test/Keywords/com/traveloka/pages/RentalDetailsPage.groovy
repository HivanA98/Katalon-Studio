package com.traveloka.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator

/** Pick-up / drop-off options (legacy "Task3/Part 2", second half). */
class RentalDetailsPage extends BasePage {

	private final TestObject pickupAtOffice = Locator.repo('11-20/11_Pengambilan_Office')
	private final TestObject pickupOption = Locator.repo('11-20/12_Pengambilan_Opsi_1')
	private final TestObject returnLocation = Locator.repo('11-20/13_Pencarian_Pengambilan')
	private final TestObject returnToRagunanZoo = Locator.repo('11-20/Pengembalian_ke_Ragunan_Zoo')
	private final TestObject note = Locator.repo('11-20/15_Note')
	private final TestObject continueButton = Locator.repo('11-20/16_Continoue')

	@Override
	protected TestObject pageMarker() {
		return pickupAtOffice
	}

	BookingFormPage pickUpAtOfficeAndReturnTo(String location, String noteText) {
		click(pickupAtOffice)
		click(pickupOption)
		click(returnLocation)
		type(returnLocation, location)
		click(returnToRagunanZoo)
		click(note)
		type(note, noteText)
		click(continueButton)
		BookingFormPage form = new BookingFormPage()
		form.verifyDisplayed()
		return form
	}
}
