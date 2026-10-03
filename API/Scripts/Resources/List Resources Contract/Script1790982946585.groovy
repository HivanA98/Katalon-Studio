import com.qa.core.SoftAssert
import com.reqres.api.JsonContract
import com.reqres.api.ResourcesApi

ResourcesApi resources = new ResourcesApi()
SoftAssert soft = new SoftAssert('GET /api/unknown')

def page = resources.list().expectStatus(200).expectJson().json()
JsonContract.verify(soft, page, JsonContract.PAGE, 'resources page')

List<Map> items = page.data as List<Map>
soft.isTrue(!items.isEmpty(), 'Resource list is not empty')
items.each { Map item ->
	JsonContract.verify(soft, item, JsonContract.RESOURCE, "resource ${item.id}")
	soft.isTrue(item.color ==~ JsonContract.HEX_COLOR, "resource ${item.id}: colour '${item.color}' is a hex code")
	soft.isTrue((item.year as int) in 1990..2100, "resource ${item.id}: year ${item.year} is plausible")
}
soft.equal(items*.year, (items*.year).sort(false), 'Resources are ordered by year')

// The single-resource endpoint returns the same record as the list
Map firstItem = items.first()
def single = resources.get(firstItem.id as int).expectStatus(200).json().data
soft.equal(single, firstItem, "GET /api/unknown/${firstItem.id} matches the list entry")

soft.assertAll()
