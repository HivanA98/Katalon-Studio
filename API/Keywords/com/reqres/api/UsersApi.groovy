package com.reqres.api

/**
 * Service object for /api/users - the API counterpart of a page object.
 */
class UsersApi {

	private final RestClient client

	UsersApi(RestClient client = new RestClient()) {
		this.client = client
	}

	ApiResponse list(int page) {
		return client.get('/api/users', [page: page])
	}

	ApiResponse listWithDelay(int seconds) {
		return client.get('/api/users', [delay: seconds])
	}

	ApiResponse get(int id) {
		return client.get("/api/users/${id}")
	}

	ApiResponse create(String name, String job) {
		return client.post('/api/users', [name: name, job: job])
	}

	ApiResponse replace(int id, String name, String job) {
		return client.put("/api/users/${id}", [name: name, job: job])
	}

	ApiResponse update(int id, Map<String, Object> fields) {
		return client.patch("/api/users/${id}", fields)
	}

	ApiResponse delete(int id) {
		return client.delete("/api/users/${id}")
	}

	/** Follows pagination and returns every user record across all pages. */
	List<Map> listAll() {
		List<Map> users = []
		int page = 1
		int totalPages = 1
		while (page <= totalPages) {
			def json = list(page).expectStatus(200).json()
			users.addAll(json.data as List<Map>)
			totalPages = json.total_pages as int
			page++
		}
		return users
	}
}
