import com.cura.models.Appointment
import com.cura.pages.AppointmentPage
import com.cura.pages.ConfirmationPage
import com.cura.pages.HomePage
import com.qa.core.CsvData
import com.qa.core.SoftAssert

/*
 * Replaces the 13 copy-pasted "<City> Hospital Program N" test cases: every facility/program/
 * readmission combination is a row in appointments.csv and is verified field by field.
 */
List<Appointment> appointments = CsvData.readEnabled('Include/resources/testdata/appointments.csv')
	.collect { Appointment.fromRow(it) }

SoftAssert soft = new SoftAssert('Data-driven appointment booking')
AppointmentPage form = HomePage.open().makeAppointment().loginAsDemoUser()

appointments.eachWithIndex { Appointment appointment, int index ->
	String label = "#${index + 1} ${appointment.facility}/${appointment.program}"
	ConfirmationPage confirmation = form.book(appointment)
	Map<String, String> actual = confirmation.details()

	appointment.asDisplayed().each { String field, String expected ->
		soft.equal(actual[field], expected, "${label} ${field}")
	}

	form = AppointmentPage.openForSignedInUser()
}

soft.assertAll()
