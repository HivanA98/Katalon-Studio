package com.traveloka.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.Config
import com.qa.core.web.BasePage
import com.qa.core.web.Browser
import com.qa.core.web.Locator

/**
 * Traveloka car rental search form, used as a guest (no login, no personal data).
 * Locators rely on Traveloka's data-testid attributes instead of recorded XPaths.
 */
class CarRentalSearchPage extends BasePage {

	private final TestObject form = Locator.css("[data-testid='rental-search-form']")
	private final TestObject locationInput = Locator.css("[data-testid='rental-search-form-location-input']")
	private final TestObject startDate = Locator.css("[data-testid='rental-search-form-date-input-start']")
	private final TestObject startTime = Locator.css("[data-testid='rental-search-form-time-input-start']")
	private final TestObject endDate = Locator.css("[data-testid='rental-search-form-date-input-end']")
	private final TestObject endTime = Locator.css("[data-testid='rental-search-form-time-input-end']")
	private final TestObject searchButton = Locator.css("[data-testid='rental-search-form-cta']")

	@Override
	protected TestObject pageMarker() {
		return form
	}

	static CarRentalSearchPage open() {
		Browser.open(Config.text('URL', 'https://www.traveloka.com/en-id/car-rental'))
		CarRentalSearchPage page = new CarRentalSearchPage()
		page.verifyDisplayed()
		return page
	}

	/** Pre-filled rental period, e.g. [start: '4 Oct 2026 09.00', end: '6 Oct 2026 09.00']. */
	Map<String, String> period() {
		return [start: "${valueOf(startDate)} ${valueOf(startTime)}".toString(),
			end  : "${valueOf(endDate)} ${valueOf(endTime)}".toString()]
	}

	CarRentalSearchPage chooseLocation(String city) {
		click(locationInput)
		type(locationInput, city)
		click(suggestion(city))
		return this
	}

	/** Searches without driver using the default (pre-filled) dates and times. */
	CarRentalResultsPage searchWithoutDriver(String city) {
		chooseLocation(city)
		click(searchButton)
		CarRentalResultsPage results = new CarRentalResultsPage()
		results.verifyDisplayed()
		return results
	}

	private static TestObject suggestion(String city) {
		return Locator.xpath("//*[@data-testid='rental-search-form-location-item']" +
				"[.//*[@data-testid='rental-search-form-location-item-title' and normalize-space()=${Locator.literal(city)}]]",
				"location suggestion ${city}")
	}
}
