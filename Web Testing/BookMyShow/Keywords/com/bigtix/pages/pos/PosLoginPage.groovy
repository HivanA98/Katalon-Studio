package com.bigtix.pages.pos

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.qa.core.Config
import com.qa.core.web.BasePage
import com.qa.core.web.Browser
import com.qa.core.web.Locator

/** Box-office point-of-sale login (legacy "Task2/Login"). */
class PosLoginPage extends BasePage {

	private final TestObject username = Locator.repo('Task2/Login/01Username')
	private final TestObject password = Locator.repo('Task2/Login/02Paswword')
	private final TestObject loginButton = Locator.repo('Task2/Login/03Login')

	@Override
	protected TestObject pageMarker() {
		return loginButton
	}

	static PosLoginPage open() {
		Browser.open(Config.text('PosUrl', 'https://pos2-sg.uat.bigtix.dev/'))
		PosLoginPage page = new PosLoginPage()
		page.verifyDisplayed()
		return page
	}

	/** Uses the Katalon-encrypted password from the profile (GlobalVariable.PosPasswordEncrypted). */
	PosSalesPage login() {
		type(username, Config.text('Login'))
		WebUI.setEncryptedText(password, Config.text('PosPasswordEncrypted'))
		click(loginButton)
		PosSalesPage sales = new PosSalesPage()
		sales.verifyDisplayed()
		return sales
	}
}
