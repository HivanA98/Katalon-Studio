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

	/**
	 * Matches 'any.package:id/<id>' - useful when the same app ships under different
	 * application ids (e.g. AOSP vs. Google builds).
	 */
	static TestObject idSuffix(String id) {
		return xpath("//*[substring(@resource-id, string-length(@resource-id) - ${id.length() + 3}) = ':id/${id}']", "id=*:id/${id}")
	}

	static TestObject text(String text) {
		return xpath("//*[@text='${text}']", "text=${text}")
	}

	static TestObject repo(String path) {
		return findTestObject(path)
	}
}
