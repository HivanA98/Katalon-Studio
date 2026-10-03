package com.qa.core

import com.kms.katalon.core.util.KeywordUtil

/**
 * Hard assertions with readable failure messages.
 *
 * A failed check stops the test case immediately (StepFailedException), which is what
 * Katalon reports as FAILED. Use {@link SoftAssert} when several checks should run first.
 */
class Check {

	static void isTrue(boolean condition, String message) {
		if (!condition) {
			KeywordUtil.markFailedAndStop(message)
		}
		KeywordUtil.logInfo("✔ ${message}")
	}

	static void isFalse(boolean condition, String message) {
		isTrue(!condition, message)
	}

	static void equal(Object actual, Object expected, String what) {
		if (actual != expected) {
			KeywordUtil.markFailedAndStop("${what}: expected <${expected}> but was <${actual}>")
		}
		KeywordUtil.logInfo("✔ ${what} = <${actual}>")
	}

	static void contains(String actual, String expectedPart, String what) {
		if (actual == null || !actual.contains(expectedPart)) {
			KeywordUtil.markFailedAndStop("${what}: expected <${actual}> to contain <${expectedPart}>")
		}
		KeywordUtil.logInfo("✔ ${what} contains <${expectedPart}>")
	}

	static void matches(String actual, String regex, String what) {
		if (actual == null || !(actual ==~ regex)) {
			KeywordUtil.markFailedAndStop("${what}: <${actual}> does not match /${regex}/")
		}
		KeywordUtil.logInfo("✔ ${what} matches /${regex}/")
	}

	static void closeTo(BigDecimal actual, BigDecimal expected, BigDecimal tolerance, String what) {
		if ((actual - expected).abs() > tolerance) {
			KeywordUtil.markFailedAndStop("${what}: expected ${expected} ± ${tolerance} but was ${actual}")
		}
		KeywordUtil.logInfo("✔ ${what} = ${actual} (expected ${expected} ± ${tolerance})")
	}

	static <T> void sorted(List<T> values, Comparator<? super T> order, String what) {
		List<T> expected = new ArrayList<T>(values)
		expected.sort(order)
		equal(values, expected, "${what} sort order")
	}

	static void sameItems(Collection actual, Collection expected, String what) {
		equal(new ArrayList(actual).sort(), new ArrayList(expected).sort(), what)
	}
}
