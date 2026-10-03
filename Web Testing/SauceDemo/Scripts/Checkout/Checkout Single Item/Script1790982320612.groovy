import com.qa.core.Check
import com.saucedemo.models.Customer
import com.saucedemo.models.Product
import com.saucedemo.models.User
import com.saucedemo.pages.CheckoutCompletePage
import com.saucedemo.pages.CheckoutOverviewPage
import com.saucedemo.pages.InventoryPage
import com.saucedemo.pages.LoginPage
import com.saucedemo.support.Money

// Test case variables: product, expectedPrice (overridable from a test suite or data binding)
BigDecimal price = new BigDecimal(expectedPrice.toString())

InventoryPage inventory = LoginPage.open().loginAs(User.STANDARD)
Check.equal(inventory.priceOf(product), price, "${product}: price on the inventory page")

CheckoutOverviewPage overview = inventory
	.addToCart(product)
	.header.openCart()
	.checkout()
	.continueWith(Customer.fromProfile())

Check.equal(overview.items(), [new Product(product, price)], 'Order lines')
Check.equal(overview.itemTotal(), price, 'Item total')
Check.equal(overview.total(), Money.round(overview.itemTotal() + overview.tax()), 'Total = item total + tax')

CheckoutCompletePage complete = overview.finish()
Check.equal(complete.title(), 'Checkout: Complete!', 'Completion page title')
Check.equal(complete.confirmationMessage(), 'Thank you for your order!', 'Confirmation message')
Check.equal(complete.header.cartCount(), 0, 'Cart is emptied after the order')
