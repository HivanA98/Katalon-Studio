package com.traveloka.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator

/** Search results and rental provider (legacy "Task3/Part 2", first half). */
class CarSelectionPage extends BasePage {

	private final TestObject secondCar = Locator.repo('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/09_Mobil_pilihan2')
	private final TestObject secondProvider = Locator.repo('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/10_Provider_Kedua')

	@Override
	protected TestObject pageMarker() {
		return secondCar
	}

	RentalDetailsPage chooseSecondCarFromSecondProvider() {
		click(secondCar)
		click(secondProvider)
		RentalDetailsPage details = new RentalDetailsPage()
		details.verifyDisplayed()
		return details
	}
}
