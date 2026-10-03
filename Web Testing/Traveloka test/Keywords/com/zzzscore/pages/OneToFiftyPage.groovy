package com.zzzscore.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.Config
import com.qa.core.web.BasePage
import com.qa.core.web.Browser
import com.qa.core.web.Locator

/**
 * zzzscore.com "1 to 50" reaction game (legacy "Task_4").
 * Tiles are re-shuffled after every click, so each number is located by its text right before clicking.
 */
class OneToFiftyPage extends BasePage {

	private final TestObject grid = Locator.id('grid')
	private final TestObject score = Locator.css('#result .resultContent strong.level')

	@Override
	protected TestObject pageMarker() {
		return grid
	}

	static OneToFiftyPage open() {
		Browser.open(Config.text('OneToFiftyUrl', 'https://zzzscore.com/1to50/en/'))
		OneToFiftyPage page = new OneToFiftyPage()
		page.verifyDisplayed()
		return page
	}

	OneToFiftyPage clickNumbersInOrder(int last = 50) {
		(1..last).each { int number -> click(tile(number)) }
		return this
	}

	boolean finished() {
		return waitForUrlContaining('/result')
	}

	/** Completion time in seconds as shown on the result page. */
	BigDecimal score() {
		return new BigDecimal(textOf(score).replaceAll(/[^0-9.]/, ''))
	}

	private static TestObject tile(int number) {
		return Locator.xpath("//div[@id='grid']/div[normalize-space()='${number}']", "tile ${number}")
	}
}
