import com.demoqa.pages.ButtonsPage
import com.qa.core.Check

/*
 * Replaces the legacy ButtonDoubleClick / ButtonRightClick test cases, whose names were swapped
 * and which never asserted the result.
 */
ButtonsPage page = ButtonsPage.open()
Check.equal(page.message('dynamic'), null, 'No message before any click')

page.performDoubleClick()
Check.equal(page.message('double'), 'You have done a double click', 'Double click message')

page.performRightClick()
Check.equal(page.message('right'), 'You have done a right click', 'Right click message')

page.performDynamicClick()
Check.equal(page.message('dynamic'), 'You have done a dynamic click', 'Dynamic click message')
