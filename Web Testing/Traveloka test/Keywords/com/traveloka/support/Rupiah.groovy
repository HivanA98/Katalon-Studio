package com.traveloka.support

class Rupiah {

	/** Parses Indonesian formatted amounts such as 'Rp 1.236.150' or 'Rp 600.000'. */
	static BigDecimal parse(String label) {
		String digits = label.replaceAll(/[^0-9]/, '')
		if (!digits) {
			throw new IllegalArgumentException("No amount found in '${label}'")
		}
		return new BigDecimal(digits)
	}
}
