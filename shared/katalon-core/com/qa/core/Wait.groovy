package com.qa.core

import com.kms.katalon.core.util.KeywordUtil

/**
 * Polling and retry helpers that replace fixed delays.
 */
class Wait {

	/** Polls the condition until it returns a truthy value or the timeout elapses. */
	static boolean until(int timeoutSeconds, long pollMillis = 250L, Closure<Boolean> condition) {
		long deadline = System.currentTimeMillis() + timeoutSeconds * 1000L
		while (System.currentTimeMillis() < deadline) {
			try {
				if (condition.call()) {
					return true
				}
			} catch (Exception ignored) {
				// element not ready yet - keep polling
			}
			Thread.sleep(pollMillis)
		}
		return condition.call() as boolean
	}

	/** Runs the action up to {@code attempts} times, rethrowing the last error. */
	static <T> T retry(int attempts, long backoffMillis = 500L, Closure<T> action) {
		Throwable last = null
		for (int attempt = 1; attempt <= attempts; attempt++) {
			try {
				return action.call()
			} catch (Throwable error) {
				last = error
				KeywordUtil.logInfo("Attempt ${attempt}/${attempts} failed: ${error.message}")
				if (attempt < attempts) {
					Thread.sleep(backoffMillis * attempt)
				}
			}
		}
		throw last
	}
}
