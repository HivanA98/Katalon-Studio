package com.calculator.screens

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.mobile.BaseScreen
import com.qa.core.mobile.MobileApp
import com.qa.core.mobile.MobileLocator

/**
 * Android calculator (AOSP and Google builds share the same view ids).
 * Keys are located by resource-id suffix so the screen works regardless of the application id.
 */
class CalculatorScreen extends BaseScreen {

	private static final Map<String, String> KEYS = [
		'+': 'op_add', '-': 'op_sub', '*': 'op_mul', '×': 'op_mul', '/': 'op_div', '÷': 'op_div'
	]

	private final TestObject equalsKey = MobileLocator.idSuffix('eq')
	/** Newer builds show the result in 'result_final', older ones in 'result'. */
	private final List<TestObject> resultViews = [MobileLocator.idSuffix('result_final'), MobileLocator.idSuffix('result')]

	@Override
	protected TestObject screenMarker() {
		return equalsKey
	}

	static CalculatorScreen launch() {
		MobileApp.start('MobileApp/Calculator.apk')
		CalculatorScreen screen = new CalculatorScreen()
		screen.verifyDisplayed()
		return screen
	}

	/** Enters an expression such as '5+4*6' key by key. */
	CalculatorScreen enter(String expression) {
		expression.replaceAll(/\s+/, '').each { String key ->
			if (key ==~ /\d/) {
				tap(MobileLocator.idSuffix("digit_${key}"))
			} else if (KEYS.containsKey(key)) {
				tap(MobileLocator.idSuffix(KEYS[key]))
			} else {
				throw new IllegalArgumentException("Unsupported key '${key}'")
			}
		}
		return this
	}

	String evaluate() {
		tap(equalsKey)
		TestObject result = resultViews.find { isPresent(it, 3) }
		if (result == null) {
			throw new IllegalStateException('Result view not found (tried result_final and result)')
		}
		return textOf(result)
	}
}
