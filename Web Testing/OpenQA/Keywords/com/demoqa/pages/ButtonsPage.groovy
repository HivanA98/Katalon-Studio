package com.demoqa.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.web.Locator

class ButtonsPage extends DemoQaPage {

	private final TestObject doubleClickButton = Locator.id('doubleClickBtn')
	private final TestObject rightClickButton = Locator.id('rightClickBtn')
	// The third button has a random id on every load, so it is located by its exact text
	private final TestObject dynamicClickButton = Locator.byText('button', 'Click Me')
	private final TestObject doubleClickMessage = Locator.id('doubleClickMessage')
	private final TestObject rightClickMessage = Locator.id('rightClickMessage')
	private final TestObject dynamicClickMessage = Locator.id('dynamicClickMessage')

	@Override
	protected String path() {
		return 'buttons'
	}

	@Override
	protected TestObject pageMarker() {
		return doubleClickButton
	}

	static ButtonsPage open() {
		return launch(new ButtonsPage())
	}

	ButtonsPage performDoubleClick() {
		doubleClick(doubleClickButton)
		return this
	}

	ButtonsPage performRightClick() {
		rightClick(rightClickButton)
		return this
	}

	ButtonsPage performDynamicClick() {
		click(dynamicClickButton)
		return this
	}

	/** Message for 'double', 'right' or 'dynamic'; null if it is not shown. */
	String message(String kind) {
		TestObject message = ['double': doubleClickMessage, 'right': rightClickMessage, 'dynamic': dynamicClickMessage][kind]
		if (message == null) {
			throw new IllegalArgumentException("Unknown click kind '${kind}'")
		}
		return isVisible(message) ? textOf(message) : null
	}
}
