// GENERATED from shared/katalon-core - edit the original and run: python tools/sync_core.py
package com.qa.core

import com.kms.katalon.core.util.KeywordUtil

/**
 * Collects assertion failures and reports them together.
 *
 * <pre>
 * SoftAssert soft = new SoftAssert('Confirmation page')
 * soft.equal(page.facility(), 'Tokyo CURA Healthcare Center', 'facility')
 * soft.equal(page.program(), 'Medicaid', 'program')
 * soft.assertAll()
 * </pre>
 */
class SoftAssert {

	private final String context
	private final List<String> failures = []

	SoftAssert(String context = 'Soft assertions') {
		this.context = context
	}

	SoftAssert isTrue(boolean condition, String message) {
		if (condition) {
			KeywordUtil.logInfo("✔ ${message}")
		} else {
			record(message)
		}
		return this
	}

	SoftAssert equal(Object actual, Object expected, String what) {
		return isTrue(actual == expected, actual == expected
				? "${what} = <${actual}>"
				: "${what}: expected <${expected}> but was <${actual}>")
	}

	SoftAssert contains(String actual, String expectedPart, String what) {
		boolean ok = actual != null && actual.contains(expectedPart)
		return isTrue(ok, ok ? "${what} contains <${expectedPart}>" : "${what}: <${actual}> does not contain <${expectedPart}>")
	}

	int failureCount() {
		return failures.size()
	}

	void assertAll() {
		if (!failures.isEmpty()) {
			String report = failures.withIndex().collect { String msg, int i -> "  ${i + 1}. ${msg}" }.join('\n')
			KeywordUtil.markFailedAndStop("${context}: ${failures.size()} check(s) failed\n${report}")
		}
	}

	private void record(String message) {
		failures << message
		KeywordUtil.logInfo("✘ ${message}")
	}
}
