package com.happyday.screens

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.mobile.BaseScreen
import com.qa.core.mobile.MobileApp
import com.qa.core.mobile.MobileLocator

/** Flash-sale landing screen of the Happy Day Shopping app. */
class FlashSaleScreen extends BaseScreen {

	private final TestObject flashSaleBanner = MobileLocator.repo('Object Repository/Shop/android.view.ViewGroup')
	private final TestObject firstProduct = MobileLocator.repo('Object Repository/Shop/android.view.ViewGroup (1)')
	private final TestObject quantity = MobileLocator.repo('Object Repository/Shop/android.widget.EditText - 1')
	private final TestObject addToBag = MobileLocator.repo('Object Repository/Shop/android.widget.TextView - Add to Bag')
	private final TestObject bagIcon = MobileLocator.repo('Object Repository/Shop/android.widget.ImageView')

	@Override
	protected TestObject screenMarker() {
		return flashSaleBanner
	}

	static FlashSaleScreen launch() {
		MobileApp.start('MobileApp/Happy Day Shopping v1.1.apk', true)
		FlashSaleScreen screen = new FlashSaleScreen()
		screen.verifyDisplayed()
		return screen
	}

	BagScreen addFirstProductToBag(int amount) {
		tap(flashSaleBanner)
		tap(firstProduct)
		type(quantity, amount.toString())
		tap(addToBag)
		tap(bagIcon)
		BagScreen bag = new BagScreen()
		bag.verifyDisplayed()
		return bag
	}
}
