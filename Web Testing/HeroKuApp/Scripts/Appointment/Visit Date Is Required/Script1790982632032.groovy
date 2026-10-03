import com.cura.models.Appointment
import com.cura.models.Facility
import com.cura.models.Program
import com.cura.pages.AppointmentPage
import com.cura.pages.HomePage
import com.qa.core.Check

AppointmentPage form = HomePage.open().makeAppointment().loginAsDemoUser()

Appointment withoutDate = new Appointment(Facility.SEOUL, false, Program.NONE, '', 'No visit date on purpose')
form.submitExpectingValidation(withoutDate)

Check.isTrue(form.visitDateMissing(), 'Browser flags the empty, required visit date')
Check.isTrue(form.isDisplayed(3), 'User stays on the appointment form')
