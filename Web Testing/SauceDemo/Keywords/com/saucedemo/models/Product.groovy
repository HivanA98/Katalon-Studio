package com.saucedemo.models

import groovy.transform.Canonical

@Canonical
class Product {

	String name
	BigDecimal price

	/** The six products of the default catalogue. */
	static final String BACKPACK = 'Sauce Labs Backpack'
	static final String BIKE_LIGHT = 'Sauce Labs Bike Light'
	static final String BOLT_TSHIRT = 'Sauce Labs Bolt T-Shirt'
	static final String FLEECE_JACKET = 'Sauce Labs Fleece Jacket'
	static final String ONESIE = 'Sauce Labs Onesie'
	static final String RED_TSHIRT = 'Test.allTheThings() T-Shirt (Red)'

	static List<String> catalogue() {
		return [BACKPACK, BIKE_LIGHT, BOLT_TSHIRT, FLEECE_JACKET, ONESIE, RED_TSHIRT]
	}
}
