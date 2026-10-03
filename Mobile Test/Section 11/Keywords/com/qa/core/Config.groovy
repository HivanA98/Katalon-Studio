// GENERATED from shared/katalon-core - edit the original and run: python tools/sync_core.py
package com.qa.core

import internal.GlobalVariable

/**
 * Typed, null-safe access to execution profile values.
 *
 * Every lookup falls back to a default when the variable is missing from the active profile,
 * so shared code never crashes on a project that does not declare an optional setting.
 * Values can be overridden from the command line with Katalon's -g_<name>=<value> flag.
 */
class Config {

	static Object get(String name, Object fallback = null) {
		try {
			Object value = GlobalVariable."$name"
			return value != null && value.toString() != '' ? value : fallback
		} catch (MissingPropertyException ignored) {
			return fallback
		}
	}

	static String text(String name, String fallback = '') {
		return get(name, fallback)?.toString()
	}

	static int integer(String name, int fallback) {
		Object value = get(name, fallback)
		return value instanceof Number ? ((Number) value).intValue() : value.toString().trim().toInteger()
	}

	static boolean bool(String name, boolean fallback) {
		Object value = get(name, fallback)
		return value instanceof Boolean ? (Boolean) value : value.toString().trim().toBoolean()
	}

	/** Default explicit-wait timeout in seconds, overridable with the 'timeout' profile variable. */
	static int timeout() {
		return integer('timeout', 15)
	}
}
