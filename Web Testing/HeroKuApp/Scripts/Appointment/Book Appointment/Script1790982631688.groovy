import com.cura.models.Appointment
import com.cura.models.Facility
import com.cura.models.Program
import com.cura.pages.ConfirmationPage
import com.cura.pages.HomePage
import com.qa.core.Check
import com.qa.core.Config

// Smoke booking: every optional field filled in (replaces the legacy "All Select" test case)
Appointment expected = new Appointment(
	Facility.HONGKONG,
	true,
	Program.MEDICARE,
	Appointment.dateInDays(7),
	Config.text('Commentar', 'Booked by the Katalon smoke suite'))

ConfirmationPage confirmation = HomePage.open()
	.makeAppointment()
	.loginAsDemoUser()
	.book(expected)

Check.equal(confirmation.heading(), 'Appointment Confirmation', 'Confirmation heading')
Check.equal(confirmation.details(), expected.asDisplayed(), 'Confirmed appointment')
