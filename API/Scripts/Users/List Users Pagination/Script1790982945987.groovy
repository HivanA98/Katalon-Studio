import com.qa.core.Check
import com.qa.core.SoftAssert
import com.reqres.api.ApiResponse
import com.reqres.api.JsonContract
import com.reqres.api.UsersApi

UsersApi users = new UsersApi()
SoftAssert soft = new SoftAssert('GET /api/users pagination')

ApiResponse first = users.list(1).expectStatus(200).expectJson()
def page1 = first.json()
JsonContract.verify(soft, page1, JsonContract.PAGE, 'page 1')
soft.equal(page1.page, 1, 'page 1: page number')
soft.equal(page1.data.size(), page1.per_page, 'page 1: data size equals per_page')

// Walk every page and check the totals add up
List<Map> everyone = users.listAll()
soft.equal(everyone.size(), page1.total, 'Users across all pages equal "total"')
soft.equal(everyone*.id.unique(false).size(), everyone.size(), 'User ids are unique across pages')
soft.equal(everyone*.id, (everyone*.id).sort(false), 'Users are ordered by id')

everyone.each { Map user ->
	JsonContract.verify(soft, user, JsonContract.USER, "user ${user.id}")
	soft.isTrue(user.email ==~ JsonContract.EMAIL, "user ${user.id}: e-mail '${user.email}' is well-formed")
	soft.isTrue(user.avatar.toString().startsWith('https://'), "user ${user.id}: avatar is served over HTTPS")
}

// A page past the end is empty, not an error
def beyond = users.list((page1.total_pages as int) + 1).expectStatus(200).json()
soft.isTrue(beyond.data.isEmpty(), 'Page after the last one has no data')

soft.assertAll()
Check.isTrue(everyone.size() > 0, 'At least one user exists')
