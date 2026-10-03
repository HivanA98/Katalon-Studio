package com.bigtix.models

import com.qa.core.Config

import groovy.transform.Canonical

/**
 * Payment-gateway TEST cards for the UAT environment (never real card data).
 */
@Canonical
class Card {

	String holder
	String number
	String expiryMonth
	String expiryYear
	String cvv

	static Card visa() {
		return new Card(Config.text('CardName'), Config.text('VisaCardNumber'), Config.text('EXMonth'),
				Config.text('VisaEXYear'), Config.text('CVV'))
	}

	static Card mastercard() {
		return new Card(Config.text('CardName'), Config.text('MastercardNumber'), Config.text('EXMonth'),
				Config.text('MasterCardEXYear'), Config.text('CVV'))
	}
}
