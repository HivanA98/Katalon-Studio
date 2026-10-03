package com.saucedemo.support

import java.math.RoundingMode

class Money {

	/** Extracts the amount from labels such as '$29.99', 'Item total: $29.99' or 'Tax: $2.40'. */
	static BigDecimal parse(String label) {
		def matcher = label =~ /\$\s*([0-9]+(?:\.[0-9]{1,2})?)/
		if (!matcher.find()) {
			throw new IllegalArgumentException("No amount found in '${label}'")
		}
		return new BigDecimal(matcher.group(1))
	}

	static BigDecimal round(BigDecimal amount) {
		return amount.setScale(2, RoundingMode.HALF_UP)
	}
}
