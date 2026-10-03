package com.samsungshop.screens

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.mobile.BaseScreen
import com.qa.core.mobile.MobileApp
import com.qa.core.mobile.MobileLocator

/** Galaxy S22 Ultra product page. */
class ProductScreen extends BaseScreen {

	private final TestObject productTile = MobileLocator.repo('Object Repository/SAMSUNG S22 ULTRA/android.widget.TextView - Galaxy S22 Ultra')
	private final TestObject buyNow = MobileLocator.repo('Object Repository/SAMSUNG S22 ULTRA/android.widget.TextView - BUY NOW')
	private final TestObject buyPage = MobileLocator.repo('Object Repository/SAMSUNG S22 ULTRA/android.view.View - Galaxy S22  S22')

	@Override
	protected TestObject screenMarker() {
		return buyNow
	}

	void openFromList() {
		tap(productTile)
		verifyDisplayed()
	}

	boolean buyNowLeadsToPurchasePage() {
		tap(buyNow)
		return isPresent(buyPage, timeout)
	}
}
