import com.demoqa.pages.RadioButtonPage
import com.qa.core.Check

RadioButtonPage page = RadioButtonPage.open()
Check.equal(page.selectedResult(), null, 'No result before a choice is made')

['Yes', 'Impressive'].each { String option ->
	page.choose(option)
	Check.isTrue(page.isSelected(option), "'${option}' is selected")
	Check.equal(page.selectedResult(), option, "Result text for '${option}'")
}

Check.isFalse(page.isSelected('Yes'), "Choosing 'Impressive' deselects 'Yes'")
Check.isFalse(page.isEnabled('No'), "'No' is disabled")
