import com.demoqa.models.UserForm
import com.demoqa.pages.TextBoxPage
import com.qa.core.Check
import com.qa.core.CsvData
import com.qa.core.SoftAssert

UserForm valid = UserForm.fromProfile()
TextBoxPage page = TextBoxPage.open()
SoftAssert soft = new SoftAssert('Invalid e-mail addresses')

CsvData.read('Include/resources/testdata/invalid_emails.csv').each { Map<String, String> row ->
	page.submit(new UserForm(valid.fullName, row.email, valid.currentAddress, valid.permanentAddress))

	soft.isTrue(page.emailFlaggedAsInvalid(), "'${row.email}' (${row.reason}) is flagged")
	soft.isTrue(page.output().isEmpty(), "'${row.email}' produces no output")

	page = page.reload()
}

soft.assertAll()

// Control: a valid address is accepted on the same form
page.submit(valid)
Check.isFalse(page.emailFlaggedAsInvalid(), 'A valid e-mail is accepted')
Check.equal(page.output()['Email'], valid.email, 'Valid e-mail is echoed')
