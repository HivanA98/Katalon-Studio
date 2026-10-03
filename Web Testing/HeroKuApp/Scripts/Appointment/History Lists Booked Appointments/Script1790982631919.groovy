import com.cura.models.Appointment
import com.cura.models.Facility
import com.cura.models.Program
import com.cura.pages.AppointmentPage
import com.cura.pages.HistoryPage
import com.cura.pages.HomePage
import com.qa.core.Check

String runId = Long.toString(System.currentTimeMillis(), 36)
List<Appointment> bookings = [
	new Appointment(Facility.TOKYO, false, Program.MEDICAID, Appointment.dateInDays(21), "History check ${runId}-A"),
	new Appointment(Facility.SEOUL, true, Program.NONE, Appointment.dateInDays(28), "History check ${runId}-B")
]

AppointmentPage form = HomePage.open().makeAppointment().loginAsDemoUser()
bookings.each { Appointment booking ->
	form.book(booking)
	form = AppointmentPage.openForSignedInUser()
}

HistoryPage history = form.menu.openHistory()

Check.isTrue(history.entryCount() >= bookings.size(), "History shows at least ${bookings.size()} appointments")
bookings.each { Appointment booking ->
	Check.isTrue(history.comments().contains(booking.comment), "History contains '${booking.comment}'")
	Check.isTrue(history.visitDates().contains(booking.visitDate), "History contains visit date ${booking.visitDate}")
	Check.isTrue(history.facilities().contains(booking.facility.label), "History contains ${booking.facility.label}")
}
