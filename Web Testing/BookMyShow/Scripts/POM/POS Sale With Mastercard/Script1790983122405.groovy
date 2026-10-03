import com.bigtix.models.Buyer
import com.bigtix.models.Card
import com.bigtix.pages.pos.PosCheckoutPage
import com.bigtix.pages.pos.PosLoginPage
import com.bigtix.pages.pos.PosSalesPage
import com.qa.core.Check

// Test case variables: eventName, quantity
PosSalesPage sales = PosLoginPage.open()
	.login()
	.selectEvent(eventName)
	.addTickets(quantity as int)

Check.isTrue(sales.cartIsPopulated(), 'Cart shows the selected products and seat layout')

PosCheckoutPage checkout = sales.checkout()
	.enterBuyer(Buyer.fromProfile())
	.payWithMastercard(Card.mastercard())

Check.isTrue(checkout.purchaseSucceeded(), 'Tickets were purchased')
String bookingId = checkout.bookingId()
Check.matches(bookingId, /.*\w{4,}.*/, 'A booking id is issued')
println("Booking ID: ${bookingId}")
checkout.backToHome()
