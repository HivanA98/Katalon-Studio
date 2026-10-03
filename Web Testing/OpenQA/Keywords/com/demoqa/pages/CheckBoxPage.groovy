package com.demoqa.pages

import com.kms.katalon.core.testobject.TestObject
import com.qa.core.Wait
import com.qa.core.web.Locator

/**
 * The file-system tree on /checkbox (rendered with rc-tree).
 */
class CheckBoxPage extends DemoQaPage {

	private final TestObject tree = Locator.css("[role='tree']")
	private final TestObject resultItems = Locator.css('#result .text-success')

	@Override
	protected String path() {
		return 'checkbox'
	}

	@Override
	protected TestObject pageMarker() {
		return tree
	}

	static CheckBoxPage open() {
		return launch(new CheckBoxPage())
	}

	CheckBoxPage expand(String... nodes) {
		nodes.each { String node ->
			if (!isExpanded(node)) {
				click(nodePart(node, "span[contains(@class,'rc-tree-switcher')]"))
				Wait.until(timeout) { isExpanded(node) }
			}
		}
		return this
	}

	CheckBoxPage toggle(String node) {
		click(checkbox(node))
		return this
	}

	/** 'true', 'false' or 'mixed' (some, but not all, children selected). */
	String state(String node) {
		return attributeOf(checkbox(node), 'aria-checked')
	}

	boolean isExpanded(String node) {
		return attributeOf(treeItem(node), 'aria-expanded') == 'true'
	}

	List<String> visibleNodes() {
		return textsOf(Locator.css("[role='treeitem'] .rc-tree-title"))
	}

	/** Keys listed under "You have selected :" (empty when nothing is selected). */
	List<String> selection() {
		return textsOf(resultItems, 2)
	}

	private static TestObject checkbox(String node) {
		return Locator.css("span.rc-tree-checkbox[aria-label='Select ${node}']", "checkbox ${node}")
	}

	private static TestObject treeItem(String node) {
		return Locator.xpath("//div[@role='treeitem'][.//span[contains(@class,'rc-tree-title') and normalize-space()=${Locator.literal(node)}]]",
				"tree item ${node}")
	}

	private static TestObject nodePart(String node, String relativeXpath) {
		return Locator.xpath("//div[@role='treeitem'][.//span[contains(@class,'rc-tree-title') and normalize-space()=${Locator.literal(node)}]]/${relativeXpath}",
				"${node} › ${relativeXpath}")
	}
}
