package com.qa.core.mobile

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

/**
 * Mobile counterpart of Locator: Appium XPath / resource-id / text based TestObjects.
 */
class MobileLocator {

	static TestObject xpath(String expression, String name = null) {
		TestObject to = new TestObject(name ?: expression)
		to.addProperty('xpath', ConditionType.EQUALS, expression)
		return to
	}

	static TestObject resourceId(String id) {
		return xpath("//*[@resource-id='${id}']", "resource-id=${id}")
	}

	static TestObject text(String text) {
		return xpath("//*[@text='${text}']", "text=${text}")
	}

	static TestObject repo(String path) {
		return findTestObject(path)
	}
}
