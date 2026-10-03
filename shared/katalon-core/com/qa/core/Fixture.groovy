package com.qa.core

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil

import groovy.json.JsonSlurper

/**
 * JSON fixtures for data that must not be committed (personal details, real accounts).
 *
 * Looks for 'Include/resources/fixtures/<name>.json' (git-ignored, your real data) and falls
 * back to the committed '<name>.example.json' template with dummy values.
 */
class Fixture {

	static Map load(String name) {
		File folder = new File(RunConfiguration.getProjectDir(), 'Include/resources/fixtures')
		File local = new File(folder, "${name}.json")
		File example = new File(folder, "${name}.example.json")
		if (local.isFile()) {
			return parse(local)
		}
		if (example.isFile()) {
			KeywordUtil.logInfo("Fixture ${local.name} not found - using ${example.name} (dummy data)")
			return parse(example)
		}
		throw new FileNotFoundException("Neither ${local.absolutePath} nor ${example.name} exists")
	}

	private static Map parse(File file) {
		return new JsonSlurper().parse(file, 'UTF-8') as Map
	}
}
