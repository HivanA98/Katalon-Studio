package com.saucedemo.models

/**
 * Options of the product sort drop-down, together with the ordering they must produce.
 */
enum SortOption {

	NAME_A_TO_Z('az', { Product a, Product b -> a.name <=> b.name }),
	NAME_Z_TO_A('za', { Product a, Product b -> b.name <=> a.name }),
	PRICE_LOW_TO_HIGH('lohi', { Product a, Product b -> a.price <=> b.price }),
	PRICE_HIGH_TO_LOW('hilo', { Product a, Product b -> b.price <=> a.price })

	final String value
	final Closure<Integer> comparator

	SortOption(String value, Closure<Integer> comparator) {
		this.value = value
		this.comparator = comparator
	}

	Comparator<Product> order() {
		return comparator as Comparator<Product>
	}
}
