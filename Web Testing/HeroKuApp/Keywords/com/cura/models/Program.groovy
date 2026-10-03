package com.cura.models

enum Program {

	MEDICARE('Medicare', 'radio_program_medicare'),
	MEDICAID('Medicaid', 'radio_program_medicaid'),
	NONE('None', 'radio_program_none')

	/** Text shown on the confirmation and history pages. */
	final String label
	/** Id of the radio button on the appointment form. */
	final String radioId

	Program(String label, String radioId) {
		this.label = label
		this.radioId = radioId
	}
}
