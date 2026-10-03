package com.cura.models

enum Facility {

	TOKYO('Tokyo CURA Healthcare Center'),
	HONGKONG('Hongkong CURA Healthcare Center'),
	SEOUL('Seoul CURA Healthcare Center')

	/** Option label and value of the facility drop-down (they are identical on CURA). */
	final String label

	Facility(String label) {
		this.label = label
	}
}
