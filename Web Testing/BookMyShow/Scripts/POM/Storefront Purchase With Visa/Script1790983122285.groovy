import com.bigtix.models.Buyer
import com.bigtix.models.Card
import com.bigtix.pages.web.CardPaymentPage
import com.bigtix.pages.web.EventCatalogPage
import com.qa.core.Check

// Test case variable: quantity
CardPaymentPage payment = EventCatalogPage.open()
	.openFirstSingaporeEvent()
	.book(quantity as int)
	.confirm(Buyer.fromProfile())
	.payWithVisa(Card.visa())

Check.isTrue(payment.paymentSucceeded(), 'Visa test-card payment succeeds')
payment.backToHome()
