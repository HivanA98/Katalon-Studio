import com.qa.core.Check
import com.qa.core.Fixture
import com.traveloka.models.Contact
import com.traveloka.pages.CarRentalSearchPage
import com.traveloka.pages.PaymentPage

/*
 * Replaces Task_3 + Task3/Part 1..4 (which shared state through callTestCase).
 * Booking data comes from Include/resources/fixtures/traveloka.json (git-ignored);
 * without it the dummy traveloka.example.json is used.
 * Test case variable: submitPayment - false (default) stops before "Pay", so no real booking is created.
 */
Map data = Fixture.load('traveloka')
Contact booker = Contact.from(data.contact as Map)
Contact driver = Contact.from((data.driver ?: data.contact) as Map)
Map rental = data.rental as Map

PaymentPage payment = CarRentalSearchPage.open()
	.searchWithoutDriver(rental.pickupLocation as String)
	.chooseSecondCarFromSecondProvider()
	.pickUpAtOfficeAndReturnTo(rental.returnLocation as String, rental.note as String)
	.submit(booker, driver)
	.acceptRequirementsAndContinue()
	.chooseBcaTransfer()

Check.isTrue(payment.payButtonAvailable(), 'BCA transfer can be paid')

if (submitPayment.toString().toBoolean()) {
	payment.pay()
}
