package com.cura.models

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import groovy.transform.Canonical

@Canonical
class Appointment {

	static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern('dd/MM/yyyy')

	Facility facility
	boolean readmission
	Program program
	String visitDate
	String comment

	/** Visit date relative to today, so the data never goes stale (the old profile hard-coded 2024). */
	static String dateInDays(int days) {
		return LocalDate.now().plusDays(days).format(DATE_FORMAT)
	}

	/** Builds an appointment from a CSV row: facility, readmission, program, daysAhead, comment. */
	static Appointment fromRow(Map<String, String> row) {
		return new Appointment(
				Facility.valueOf(row.facility.trim().toUpperCase()),
				row.readmission.trim().toBoolean(),
				Program.valueOf(row.program.trim().toUpperCase()),
				dateInDays(row.daysAhead.trim().toInteger()),
				row.comment)
	}

	/** Readmission as rendered by CURA ('Yes' / 'No'). */
	String readmissionLabel() {
		return readmission ? 'Yes' : 'No'
	}

	/** How the confirmation page must render this appointment (same keys as ConfirmationPage.details()). */
	Map<String, String> asDisplayed() {
		return [
			facility   : facility.label,
			readmission: readmissionLabel(),
			program    : program.label,
			visitDate  : visitDate,
			comment    : comment
		]
	}

	String toString() {
		return "${facility.label} | readmission=${readmissionLabel()} | ${program.label} | ${visitDate}"
	}
}
