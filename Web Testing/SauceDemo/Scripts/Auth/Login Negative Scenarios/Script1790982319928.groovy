import com.qa.core.CsvData
import com.qa.core.SoftAssert
import com.saucedemo.pages.LoginPage

/*
 * Data-driven: every row of the CSV is one negative login scenario.
 * Failures are collected with SoftAssert so a single run reports every broken scenario.
 */
List<Map<String, String>> scenarios = CsvData.read('Include/resources/testdata/login_negative.csv')
SoftAssert soft = new SoftAssert('Login negative scenarios')
LoginPage login = LoginPage.open()

scenarios.each { Map<String, String> row ->
	String password = row.password.replace('{{PASSWORD}}', LoginPage.password())

	login.submit(row.username, password)

	soft.isTrue(login.isDisplayed(2), "[${row.scenario}] user stays on the login page")
	soft.equal(login.error(), row.expectedError, "[${row.scenario}] error message")
	soft.isTrue(login.inputsHighlighted(), "[${row.scenario}] inputs are highlighted")

	login.dismissError()
	soft.isTrue(!login.hasError(), "[${row.scenario}] error can be dismissed")
}

soft.assertAll()
