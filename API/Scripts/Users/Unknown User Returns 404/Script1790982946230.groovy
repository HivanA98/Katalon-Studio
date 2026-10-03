import com.qa.core.Check
import com.reqres.api.ResourcesApi
import com.reqres.api.UsersApi

def user = new UsersApi().get(23).expectStatus(404)
Check.equal(user.json(), [:], 'Unknown user has an empty JSON body')

def resource = new ResourcesApi().get(23).expectStatus(404)
Check.equal(resource.json(), [:], 'Unknown resource has an empty JSON body')
