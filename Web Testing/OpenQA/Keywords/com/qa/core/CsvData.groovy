// GENERATED from shared/katalon-core - edit the original and run: python tools/sync_core.py
package com.qa.core

import com.kms.katalon.core.configuration.RunConfiguration

/**
 * Minimal RFC 4180 CSV reader for data-driven test cases.
 *
 * Files live inside the project (for example 'Include/resources/testdata/login.csv') so they
 * travel with the repository and work identically in Katalon Studio and in CI.
 * Lines starting with '#' are treated as comments.
 */
class CsvData {

	static List<Map<String, String>> read(String projectRelativePath) {
		File file = new File(RunConfiguration.getProjectDir(), projectRelativePath)
		if (!file.isFile()) {
			throw new FileNotFoundException("Test data file not found: ${file.absolutePath}")
		}
		List<String> lines = file.readLines('UTF-8').findAll { String line ->
			line.trim() && !line.trim().startsWith('#')
		}
		if (lines.isEmpty()) {
			return []
		}
		List<String> header = parseLine(lines.head()).collect { it.trim() }
		return lines.tail().collect { String line ->
			List<String> cells = parseLine(line)
			Map<String, String> row = [:]
			header.eachWithIndex { String column, int i -> row[column] = i < cells.size() ? cells[i] : '' }
			row
		}
	}

	/** Rows whose 'enabled' column is not 'false' (column is optional). */
	static List<Map<String, String>> readEnabled(String projectRelativePath) {
		return read(projectRelativePath).findAll { it.enabled == null || it.enabled.toLowerCase() != 'false' }
	}

	static List<String> parseLine(String line) {
		List<String> cells = []
		StringBuilder current = new StringBuilder()
		boolean quoted = false
		int i = 0
		while (i < line.length()) {
			char c = line.charAt(i)
			if (quoted) {
				if (c == '"' as char && i + 1 < line.length() && line.charAt(i + 1) == '"' as char) {
					current.append('"')
					i++
				} else if (c == '"' as char) {
					quoted = false
				} else {
					current.append(c)
				}
			} else if (c == '"' as char) {
				quoted = true
			} else if (c == ',' as char) {
				cells << current.toString()
				current.setLength(0)
			} else {
				current.append(c)
			}
			i++
		}
		cells << current.toString()
		return cells
	}
}
