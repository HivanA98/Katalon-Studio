import com.qa.core.Check
import com.qa.core.Config
import com.qa.core.SoftAssert
import com.saucedemo.models.Customer
import com.saucedemo.models.Product
import com.saucedemo.models.User
import com.saucedemo.pages.CheckoutOverviewPage
import com.saucedemo.pages.InventoryPage
import com.saucedemo.pages.LoginPage
import com.saucedemo.support.Money

List<String> picks = [Product.BACKPACK, Product.BIKE_LIGHT, Product.FLEECE_JACKET, Product.RED_TSHIRT]
BigDecimal taxRate = new BigDecimal(Config.text('TaxRate', '0.08'))

InventoryPage inventory = LoginPage.open().loginAs(User.STANDARD)
Map<String, BigDecimal> prices = inventory.products().collectEntries { [(it.name): it.price] }

CheckoutOverviewPage overview = inventory
	.addToCart(picks)
	.header.openCart()
	.checkout()
	.continueWith(Customer.fromProfile())

BigDecimal expectedItemTotal = picks.collect { prices[it] }.sum() as BigDecimal
BigDecimal expectedTax = Money.round(expectedItemTotal * taxRate)

SoftAssert soft = new SoftAssert('Order summary')
soft.equal(overview.items().collect { it.name }.sort(), new ArrayList<String>(picks).sort(), 'Order lines')
soft.equal(overview.itemTotal(), expectedItemTotal, 'Item total = sum of product prices')
soft.equal(overview.tax(), expectedTax, "Tax = ${taxRate * 100}% of item total")
soft.equal(overview.total(), expectedItemTotal + expectedTax, 'Total = item total + tax')
soft.assertAll()

Check.equal(overview.finish().confirmationMessage(), 'Thank you for your order!', 'Order is placed')
