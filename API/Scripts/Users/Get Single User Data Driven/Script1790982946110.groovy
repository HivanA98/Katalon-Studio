import com.qa.core.CsvData
import com.qa.core.SoftAssert
import com.reqres.api.JsonContract
import com.reqres.api.UsersApi

UsersApi users = new UsersApi()
SoftAssert soft = new SoftAssert('GET /api/users/{id}')

CsvData.read('Include/resources/testdata/users.csv').each { Map<String, String> row ->
	int id = row.id.toInteger()
	def response = users.get(id)
	soft.equal(response.status(), 200, "user ${id}: status")

	def user = response.json().data
	JsonContract.verify(soft, user, JsonContract.USER, "user ${id}")
	soft.equal(user?.id, id, "user ${id}: id")
	soft.equal(user?.email, row.email, "user ${id}: email")
	soft.equal(user?.first_name, row.first_name, "user ${id}: first name")
	soft.equal(user?.last_name, row.last_name, "user ${id}: last name")
	soft.isTrue(response.json().support?.url as boolean, "user ${id}: support block is present")
}

soft.assertAll()
