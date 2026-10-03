import com.cura.pages.HomePage
import com.cura.pages.LoginPage
import com.qa.core.CsvData
import com.qa.core.SoftAssert

LoginPage login = HomePage.open().makeAppointment()
SoftAssert soft = new SoftAssert('CURA login negative scenarios')

CsvData.read('Include/resources/testdata/login_negative.csv').each { Map<String, String> row ->
	login.submit(row.username, row.password.replace('{{PASSWORD}}', LoginPage.demoPassword()))

	soft.isTrue(login.isDisplayed(5), "[${row.scenario}] user stays on the login page")
	soft.equal(login.error(), LoginPage.LOGIN_FAILED, "[${row.scenario}] error message")
}

soft.assertAll()
