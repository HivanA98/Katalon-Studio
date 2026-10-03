package com.happyday.screens

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.mobile.BaseScreen
import com.qa.core.mobile.MobileApp
import com.qa.core.mobile.MobileLocator

/** Shopping bag and checkout hand-off. */
class BagScreen extends BaseScreen {

	private final TestObject bagItem = MobileLocator.repo('Object Repository/Shop/android.view.ViewGroup (2)')
	private final TestObject illustration = MobileLocator.repo('Object Repository/Shop/android.widget.Image - Block illustration')
	private final TestObject product = MobileLocator.repo('Object Repository/Shop/android.widget.Image - Ponds Age Miracle Youth Boosting Whip 50g')
	private final TestObject checkoutButton = MobileLocator.repo('Object Repository/android.widget.Button - Go to Checkout')
	private final TestObject inquiryNotice = MobileLocator.repo('Object Repository/Shop/android.view.View - If you have any inquiries or updates pls whatsap us at 94617563')

	@Override
	protected TestObject screenMarker() {
		return bagItem
	}

	/** Returns the support notice shown on the checkout page. */
	String goToCheckout() {
		tap(bagItem)
		tap(illustration)
		tap(product)
		tap(checkoutButton)
		return textOf(inquiryNotice)
	}
}
