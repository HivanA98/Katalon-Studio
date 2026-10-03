package com.reqres.api

/**
 * Service object for /api/login and /api/register.
 */
class AuthApi {

	private final RestClient client

	AuthApi(RestClient client = new RestClient()) {
		this.client = client
	}

	ApiResponse login(String email, String password) {
		return client.post('/api/login', credentials(email, password))
	}

	ApiResponse register(String email, String password) {
		return client.post('/api/register', credentials(email, password))
	}

	/** Omits empty values so 'missing field' scenarios send a genuinely absent property. */
	private static Map<String, String> credentials(String email, String password) {
		return [email: email, password: password].findAll { it.value } as Map<String, String>
	}
}
