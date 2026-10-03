package com.traveloka.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.Config
import com.qa.core.web.BasePage
import com.qa.core.web.Browser
import com.qa.core.web.Locator

/**
 * Traveloka home page > Car Rental search form (legacy "Task3/Part 1").
 * Dates and times use the recorded calendar objects (15th 09:00 → 20th 11:00).
 */
class CarRentalSearchPage extends BasePage {

	private final TestObject carRentalTab = Locator.repo('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/01_Car_Rental')
	private final TestObject withoutDriver = Locator.repo('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/02_Without_Driver')
	private final TestObject pickupLocation = Locator.repo('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/03_Lokasi_Peminjaman')
	private final TestObject ragunanSuggestion = Locator.repo('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/Terminal_Ragunan')
	private final TestObject startDate = Locator.repo('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/04_Tanggal_Awal_Peminjaman')
	private final TestObject day15 = Locator.repo('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/Tanggal_15')
	private final TestObject startTime = Locator.repo('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/05_Jam_awal_Peminjaman')
	private final TestObject hour9 = Locator.repo('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/Jam_9')
	private final TestObject endDate = Locator.repo('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/06_Tanggal_Akhir_Peminjaman')
	private final TestObject day20 = Locator.repo('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/Tanggal_20')
	private final TestObject endTime = Locator.repo('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/07_Jam_akhir_Peminjaman')
	private final TestObject hour11 = Locator.repo('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/Jam_11')
	private final TestObject doneButton = Locator.repo('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/Tombol_Done')
	private final TestObject searchButton = Locator.repo('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/08_Tombol_Search')

	@Override
	protected TestObject pageMarker() {
		return carRentalTab
	}

	static CarRentalSearchPage open() {
		Browser.open(Config.text('URL', 'https://www.traveloka.com/en-id'))
		CarRentalSearchPage page = new CarRentalSearchPage()
		page.verifyDisplayed()
		return page
	}

	CarSelectionPage searchWithoutDriver(String location) {
		click(carRentalTab)
		isVisible(withoutDriver, timeout)
		click(pickupLocation)
		type(pickupLocation, location)
		click(ragunanSuggestion)

		click(startDate)
		click(day15)
		click(startTime)
		click(hour9)
		click(doneButton)

		click(endDate)
		click(day20)
		click(endTime)
		click(hour11)
		click(doneButton)

		click(searchButton)
		CarSelectionPage results = new CarSelectionPage()
		results.verifyDisplayed()
		return results
	}
}
