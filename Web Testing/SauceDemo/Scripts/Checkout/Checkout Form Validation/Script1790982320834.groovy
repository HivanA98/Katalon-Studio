import com.qa.core.CsvData
import com.qa.core.SoftAssert
import com.saucedemo.models.Customer
import com.saucedemo.models.Product
import com.saucedemo.models.User
import com.saucedemo.pages.CheckoutInformationPage
import com.saucedemo.pages.LoginPage

CheckoutInformationPage information = LoginPage.open()
	.loginAs(User.STANDARD)
	.addToCart(Product.BACKPACK)
	.header.openCart()
	.checkout()

SoftAssert soft = new SoftAssert('Checkout form validation')

CsvData.read('Include/resources/testdata/checkout_validation.csv').each { Map<String, String> row ->
	information.submitExpectingError(new Customer(row.firstName, row.lastName, row.postalCode))

	soft.equal(information.error(), row.expectedError, "[${row.scenario}] validation message")
	soft.isTrue(information.isDisplayed(2), "[${row.scenario}] user stays on the information step")
}

soft.assertAll()

// A complete form moves on to the overview step
information.continueWith(Customer.fromProfile())
