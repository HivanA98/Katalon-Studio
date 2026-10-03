package com.traveloka.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.BasePage
import com.qa.core.web.Locator

/** Rental requirements and payment method (legacy "Task3/Part 4"). */
class PaymentPage extends BasePage {

	private final TestObject requirements = Locator.repo('21-33/27_Requirments')
	private final TestObject checkAll = Locator.repo('21-33/28_Check_All')
	private final TestObject saveRequirements = Locator.repo('21-33/29_Save_Requirments')
	private final TestObject continueToPayment = Locator.repo('21-33/30_Continoue_to_Payments_InBooking')
	private final TestObject continueReconfirm = Locator.repo('21-33/31_Continoue_at_ReConfirm')
	private final TestObject bcaTransfer = Locator.repo('21-33/32_BCA_Transfer')
	private final TestObject payWithBcaTransfer = Locator.repo('21-33/33_Pay_with_BCA_Transfer')

	@Override
	protected TestObject pageMarker() {
		return requirements
	}

	PaymentPage acceptRequirementsAndContinue() {
		click(requirements)
		click(checkAll)
		click(saveRequirements)
		click(continueToPayment)
		click(continueReconfirm)
		return this
	}

	PaymentPage chooseBcaTransfer() {
		click(bcaTransfer)
		return this
	}

	boolean payButtonAvailable() {
		return isVisible(payWithBcaTransfer, timeout)
	}

	/** Creates a real booking on production - only call when explicitly enabled. */
	void pay() {
		click(payWithBcaTransfer)
	}
}
