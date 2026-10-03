import com.cura.models.Facility
import com.cura.pages.AppointmentPage
import com.cura.pages.HomePage
import com.qa.core.Check

HomePage home = HomePage.open()
Check.equal(home.heading(), 'CURA Healthcare Service', 'Home page heading')

AppointmentPage appointment = home.makeAppointment().loginAsDemoUser()

Check.equal(appointment.heading(), 'Make Appointment', 'Appointment form heading')
Check.equal(appointment.selectedFacility(), Facility.TOKYO.label, 'Default facility')
