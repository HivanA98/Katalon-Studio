package com.samsungshop.screens

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.mobile.BaseScreen
import com.qa.core.mobile.MobileApp
import com.qa.core.mobile.MobileLocator

/** Home screen with the SHOP entry point. */
class ShopHomeScreen extends BaseScreen {

	private final TestObject dismissButton = MobileLocator.repo('Object Repository/SAMSUNG S22 ULTRA/android.widget.ImageView')
	private final TestObject shopTab = MobileLocator.repo('Object Repository/SAMSUNG S22 ULTRA/android.widget.TextView - SHOP')
	private final TestObject mobileCategory = MobileLocator.repo('Object Repository/SAMSUNG S22 ULTRA/android.widget.ImageView (1)')
	private final TestObject smartphones = MobileLocator.repo('Object Repository/SAMSUNG S22 ULTRA/android.widget.ImageView (2)')
	private final TestObject galaxySeries = MobileLocator.repo('Object Repository/SAMSUNG S22 ULTRA/android.widget.RelativeLayout')

	@Override
	protected TestObject screenMarker() {
		return dismissButton
	}

	ProductScreen openGalaxyS22Ultra() {
		tap(dismissButton)
		tap(shopTab)
		tap(mobileCategory)
		tap(smartphones)
		tap(galaxySeries)
		ProductScreen product = new ProductScreen()
		product.openFromList()
		return product
	}
}
