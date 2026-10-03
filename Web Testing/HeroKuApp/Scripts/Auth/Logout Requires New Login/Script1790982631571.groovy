import com.cura.pages.AppointmentPage
import com.cura.pages.HomePage
import com.cura.pages.LoginPage
import com.qa.core.Check

AppointmentPage appointment = HomePage.open().makeAppointment().loginAsDemoUser()

HomePage home = appointment.menu.logout()
Check.isTrue(home.isDisplayed(), 'Logout returns to the home page')

// Without a session, "Make Appointment" must ask for credentials again
LoginPage login = home.makeAppointment()
Check.isTrue(login.isDisplayed(), 'Login is required again after logout')
