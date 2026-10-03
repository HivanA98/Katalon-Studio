package com.qa.core.web

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject

/**
 * Factory for Katalon {@link TestObject}s.
 *
 * Page objects declare their locators in code (css / xpath / id) so that a locator lives next
 * to the behaviour that uses it. {@link #repo} keeps backwards compatibility with entities that
 * are still maintained in the Object Repository.
 */
class Locator {

	static TestObject css(String selector, String name = null) {
		TestObject to = new TestObject(name ?: "css=${selector}")
		to.setSelectorMethod(SelectorMethod.CSS)
		to.setSelectorValue(SelectorMethod.CSS, selector)
		return to
	}

	static TestObject xpath(String expression, String name = null) {
		TestObject to = new TestObject(name ?: "xpath=${expression}")
		to.setSelectorMethod(SelectorMethod.XPATH)
		to.setSelectorValue(SelectorMethod.XPATH, expression)
		return to
	}

	static TestObject id(String id) {
		return css("#${id}", "id=${id}")
	}

	static TestObject dataTest(String value) {
		return css("[data-test='${value}']", "data-test=${value}")
	}

	/** Element of the given tag whose normalised text equals {@code text}. */
	static TestObject byText(String tag, String text) {
		return xpath("//${tag}[normalize-space()=${literal(text)}]", "${tag} with text '${text}'")
	}

	/** Element of the given tag whose text contains {@code text}. */
	static TestObject containingText(String tag, String text) {
		return xpath("//${tag}[contains(normalize-space(), ${literal(text)})]", "${tag} containing '${text}'")
	}

	/** Object Repository entity, optionally with variables for parameterised selectors. */
	static TestObject repo(String path, Map<String, Object> variables = [:]) {
		return variables ? findTestObject(path, variables) : findTestObject(path)
	}

	/** Safely quotes a value for XPath, including values that contain both quote types. */
	static String literal(String value) {
		if (!value.contains("'")) {
			return "'${value}'"
		}
		if (!value.contains('"')) {
			return "\"${value}\""
		}
		return "concat('" + value.replace("'", "', \"'\", '") + "')"
	}
}
