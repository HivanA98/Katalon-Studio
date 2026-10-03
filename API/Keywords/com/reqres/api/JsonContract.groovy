package com.reqres.api

import com.qa.core.SoftAssert

/**
 * Lightweight JSON contract checks: required fields, their types and optional patterns.
 * Extra fields are allowed, so additive API changes do not break the tests.
 */
class JsonContract {

	static final Map<String, Class> USER = [
		id        : Number,
		email     : String,
		first_name: String,
		last_name : String,
		avatar    : String
	]

	static final Map<String, Class> RESOURCE = [
		id           : Number,
		name         : String,
		year         : Number,
		color        : String,
		pantone_value: String
	]

	static final Map<String, Class> PAGE = [
		page       : Number,
		per_page   : Number,
		total      : Number,
		total_pages: Number,
		data       : List
	]

	static final String EMAIL = /[\w.+-]+@[\w-]+(\.[\w-]+)+/
	static final String ISO_TIMESTAMP = /\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}(\.\d+)?Z/
	static final String HEX_COLOR = /#[0-9A-Fa-f]{6}/

	/** Records one failure per missing or mistyped field. */
	static void verify(SoftAssert soft, Object json, Map<String, Class> contract, String label) {
		if (!(json instanceof Map)) {
			soft.isTrue(false, "${label}: expected a JSON object but got ${json?.getClass()?.simpleName}")
			return
		}
		Map body = (Map) json
		contract.each { String field, Class type ->
			soft.isTrue(body.containsKey(field), "${label}: has '${field}'")
			if (body[field] != null) {
				soft.isTrue(type.isInstance(body[field]), "${label}: '${field}' is a ${type.simpleName}")
			}
		}
	}
}
