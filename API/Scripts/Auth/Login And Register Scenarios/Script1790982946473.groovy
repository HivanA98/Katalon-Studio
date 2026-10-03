import com.qa.core.CsvData
import com.qa.core.SoftAssert
import com.reqres.api.ApiResponse
import com.reqres.api.AuthApi

/*
 * Data-driven authentication matrix. Columns:
 *   endpoint (login|register), email, password, expectedStatus, expectedError (optional)
 * Successful calls must return a token; failed calls must return an error message.
 */
AuthApi auth = new AuthApi()
SoftAssert soft = new SoftAssert('Authentication scenarios')

CsvData.read('Include/resources/testdata/auth_scenarios.csv').each { Map<String, String> row ->
	String label = "[${row.endpoint}] ${row.scenario}"
	ApiResponse response = row.endpoint == 'register'
		? auth.register(row.email, row.password)
		: auth.login(row.email, row.password)

	int expectedStatus = row.expectedStatus.toInteger()
	soft.equal(response.status(), expectedStatus, "${label}: status")

	if (expectedStatus == 200) {
		soft.isTrue(response.json().token as boolean, "${label}: returns a token")
		if (row.endpoint == 'register') {
			soft.isTrue(response.json().id != null, "${label}: returns the new user id")
		}
	} else {
		soft.isTrue(response.json().error as boolean, "${label}: returns an error message")
		if (row.expectedError) {
			soft.equal(response.json().error, row.expectedError, "${label}: error message")
		}
	}
}

soft.assertAll()
