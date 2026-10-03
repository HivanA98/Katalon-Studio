package com.traveloka.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Browser
import com.qa.core.web.Locator
import com.traveloka.support.Rupiah

/** Car list returned by a "without driver" search. */
class CarRentalResultsPage extends BasePage {

	private final TestObject heading = Locator.byText('h2', 'Car Rental Without Driver')
	private final TestObject prices = Locator.xpath("//*[not(*) and starts-with(normalize-space(text()), 'Rp ')]", 'daily prices')
	private final TestObject continueButtons = Locator.xpath("//div[@role='button'][normalize-space()='Continue']", 'Continue buttons')

	@Override
	protected TestObject pageMarker() {
		return heading
	}

	String currentUrl() {
		return Browser.currentUrl()
	}

	/** Every price shown on the result cards, in Rupiah. */
	List<BigDecimal> prices() {
		isVisible(prices, timeout)
		return textsOf(prices).findAll { it }.collect { Rupiah.parse(it) }
	}

	RentalProviderPage chooseFirstCar() {
		clickFirstVisible(continueButtons)
		RentalProviderPage providers = new RentalProviderPage()
		providers.verifyDisplayed()
		return providers
	}
}
