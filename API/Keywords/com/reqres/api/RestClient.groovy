package com.reqres.api

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.qa.core.Config

import groovy.json.JsonOutput

/**
 * Builds Katalon RequestObjects in code, so every request is versioned, reviewable and
 * parameterised without maintaining one Object Repository entity per call.
 */
class RestClient {

	private final String baseUrl
	private final Map<String, String> headers

	RestClient(String baseUrl = Config.text('baseUrl', 'https://reqres.in'), Map<String, String> headers = defaultHeaders()) {
		this.baseUrl = baseUrl.endsWith('/') ? baseUrl[0..-2] : baseUrl
		this.headers = headers
	}

	static Map<String, String> defaultHeaders() {
		Map<String, String> headers = ['Accept': 'application/json']
		String apiKey = Config.text('apiKey', '')
		if (apiKey) {
			headers['x-api-key'] = apiKey
		}
		return headers
	}

	ApiResponse get(String path, Map<String, Object> query = [:]) {
		return send('GET', path, query, null)
	}

	ApiResponse post(String path, Object body) {
		return send('POST', path, [:], body)
	}

	ApiResponse put(String path, Object body) {
		return send('PUT', path, [:], body)
	}

	ApiResponse patch(String path, Object body) {
		return send('PATCH', path, [:], body)
	}

	ApiResponse delete(String path) {
		return send('DELETE', path, [:], null)
	}

	ApiResponse send(String method, String path, Map<String, Object> query, Object body) {
		RequestObject request = new RequestObject("${method} ${path}")
		request.setRestRequestMethod(method)
		request.setRestUrl(url(path, query))

		Map<String, String> requestHeaders = new LinkedHashMap<>(this.headers)
		if (body != null) {
			String json = body instanceof String ? (String) body : JsonOutput.toJson(body)
			requestHeaders['Content-Type'] = 'application/json'
			request.setBodyContent(new HttpTextBodyContent(json, 'UTF-8', 'application/json'))
		}
		request.setHttpHeaderProperties(requestHeaders.collect { name, value ->
			new TestObjectProperty(name, ConditionType.EQUALS, value)
		})

		long startedAt = System.currentTimeMillis()
		ResponseObject response = WS.sendRequest(request)
		long duration = System.currentTimeMillis() - startedAt
		KeywordUtil.logInfo("${method} ${request.getRestUrl()} → ${response.getStatusCode()} in ${duration} ms")
		return new ApiResponse(response, duration)
	}

	private String url(String path, Map<String, Object> query) {
		String full = "${baseUrl}/${path.startsWith('/') ? path.substring(1) : path}"
		if (query) {
			full += '?' + query.collect { k, v -> "${URLEncoder.encode(k, 'UTF-8')}=${URLEncoder.encode(String.valueOf(v), 'UTF-8')}" }.join('&')
		}
		return full
	}
}
