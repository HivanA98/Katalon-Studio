package com.reqres.api

/**
 * Service object for /api/unknown (the "resources" collection of colours).
 */
class ResourcesApi {

	private final RestClient client

	ResourcesApi(RestClient client = new RestClient()) {
		this.client = client
	}

	ApiResponse list(int page = 1) {
		return client.get('/api/unknown', [page: page])
	}

	ApiResponse get(int id) {
		return client.get("/api/unknown/${id}")
	}
}
