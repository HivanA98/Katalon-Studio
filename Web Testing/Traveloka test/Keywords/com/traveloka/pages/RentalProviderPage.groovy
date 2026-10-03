package com.traveloka.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator
import com.traveloka.support.Rupiah

/**
 * "Select Rental Provider" step. This is the last step a guest can reach:
 * continuing from here asks for contact details, so the guest tests stop on this page.
 */
class RentalProviderPage extends BasePage {

	private final TestObject heading = Locator.byText('*', 'Select Rental Provider')
	private final TestObject prices = Locator.xpath(
			"//*[normalize-space()='Select Rental Provider']/following::*[not(*) and starts-with(normalize-space(text()), 'Rp ')]",
			'provider prices')

	@Override
	protected TestObject pageMarker() {
		return heading
	}

	List<BigDecimal> providerPrices() {
		return textsOf(prices).findAll { it }.collect { Rupiah.parse(it) }
	}
}
