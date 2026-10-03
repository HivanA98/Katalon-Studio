package com.demoqa.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.Locator

class RadioButtonPage extends DemoQaPage {

	private final TestObject yesRadio = Locator.id('yesRadio')
	private final TestObject result = Locator.css('p .text-success')

	@Override
	protected String path() {
		return 'radio-button'
	}

	@Override
	protected TestObject pageMarker() {
		return yesRadio
	}

	static RadioButtonPage open() {
		return launch(new RadioButtonPage())
	}

	/** Options are selected through their label, as a user would ('Yes', 'Impressive', 'No'). */
	RadioButtonPage choose(String option) {
		click(label(option))
		return this
	}

	String selectedResult() {
		return isVisible(result) ? textOf(result) : null
	}

	boolean isEnabled(String option) {
		return attributeOf(input(option), 'disabled') == null
	}

	boolean isSelected(String option) {
		return elements(input(option)).first().isSelected()
	}

	private static TestObject label(String option) {
		return Locator.xpath("//label[contains(@class,'form-check-label') and normalize-space()=${Locator.literal(option)}]",
				"radio label ${option}")
	}

	private static TestObject input(String option) {
		return Locator.xpath("//label[normalize-space()=${Locator.literal(option)}]/preceding-sibling::input[@type='radio']",
				"radio input ${option}")
	}
}
