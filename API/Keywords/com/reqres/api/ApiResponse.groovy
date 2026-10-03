package com.reqres.api

import com.kms.katalon.core.testobject.ResponseObject
import com.qa.core.Check

import groovy.json.JsonSlurper

/**
 * Thin, fluent wrapper around Katalon's ResponseObject.
 */
class ApiResponse {

	final ResponseObject raw
	final long durationMs
	private Object parsed

	ApiResponse(ResponseObject raw, long durationMs) {
		this.raw = raw
		this.durationMs = durationMs
	}

	int status() {
		return raw.getStatusCode()
	}

	String body() {
		return raw.getResponseText() ?: ''
	}

	/** Parsed JSON body (Map or List); an empty body yields an empty map. */
	def json() {
		if (parsed == null) {
			parsed = body().trim() ? new JsonSlurper().parseText(body()) : [:]
		}
		return parsed
	}

	String header(String name) {
		Map.Entry<String, List<String>> entry = raw.getHeaderFields()?.find { it.key?.equalsIgnoreCase(name) }
		return entry?.value?.join(', ')
	}

	ApiResponse expectStatus(int expected) {
		Check.equal(status(), expected, "HTTP status (body: ${body().take(200)})")
		return this
	}

	ApiResponse expectJson() {
		Check.contains(header('Content-Type'), 'application/json', 'Content-Type header')
		return this
	}

	ApiResponse expectFasterThan(long millis) {
		Check.isTrue(durationMs < millis, "Response time ${durationMs} ms is below ${millis} ms")
		return this
	}
}
