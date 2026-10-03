import com.demoqa.pages.CheckBoxPage
import com.qa.core.Check

CheckBoxPage page = CheckBoxPage.open()
Check.isTrue(page.selection().isEmpty(), 'Nothing is selected initially')

// Expanding reveals the nested nodes
page.expand('Home', 'Desktop')
Check.isTrue(page.visibleNodes().containsAll(['Desktop', 'Documents', 'Downloads', 'Notes', 'Commands']),
	'Home and Desktop children are visible')

// Selecting a branch selects its leaves and makes the parent indeterminate
page.toggle('Desktop')
Check.equal(page.state('Desktop'), 'true', 'Desktop state')
Check.equal(page.state('Home'), 'mixed', 'Home becomes partially selected')
Check.equal(page.selection(), ['desktop', 'notes', 'commands'], 'Selection after checking Desktop')

// Clicking a partially selected parent selects the whole tree
page.toggle('Home')
Check.equal(page.state('Home'), 'true', 'Home state')
Check.equal(page.selection().size(), 17, 'Every node of the tree is selected')
Check.equal(page.selection().first(), 'home', 'Root is listed first')

// Unchecking the root clears everything
page.toggle('Home')
Check.isTrue(page.selection().isEmpty(), 'Selection is cleared')
Check.equal(page.state('Desktop'), 'false', 'Children are unchecked with the root')
