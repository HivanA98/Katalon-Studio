package com.calculator.screens

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.mobile.BaseScreen
import com.qa.core.mobile.MobileApp
import com.qa.core.mobile.MobileLocator

/**
 * Google Calculator. Digits that were recorded in the Object Repository are reused; every other
 * key is located by its stable resource-id, so any expression can be entered.
 */
class CalculatorScreen extends BaseScreen {

	private static final String PACKAGE = 'com.google.android.calculator:id'

	private final TestObject equalsKey = MobileLocator.repo('Test 1/hasil')
	private final TestObject plusKey = MobileLocator.repo('Test 1/tambah')
	private final TestObject timesKey = MobileLocator.repo('Test 1/kali')
	private final TestObject result = MobileLocator.resourceId("${PACKAGE}/result_final")

	private static final Map<String, String> OPERATORS = ['+': 'op_add', '-': 'op_sub', '×': 'op_mul', '*': 'op_mul', '÷': 'op_div', '/': 'op_div']

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
				tap(MobileLocator.resourceId("${PACKAGE}/digit_${key}"))
			} else if (key == '+') {
				tap(plusKey)
			} else if (key in ['*', '×']) {
				tap(timesKey)
			} else if (OPERATORS.containsKey(key)) {
				tap(MobileLocator.resourceId("${PACKAGE}/${OPERATORS[key]}"))
			} else {
				throw new IllegalArgumentException("Unsupported key '${key}'")
			}
		}
		return this
	}

	String evaluate() {
		tap(equalsKey)
		return textOf(result)
	}
}
