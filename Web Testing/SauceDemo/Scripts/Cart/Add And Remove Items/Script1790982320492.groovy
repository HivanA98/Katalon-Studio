import com.qa.core.Check
import com.saucedemo.models.Product
import com.saucedemo.models.User
import com.saucedemo.pages.CartPage
import com.saucedemo.pages.InventoryPage
import com.saucedemo.pages.LoginPage

List<String> picks = [Product.BACKPACK, Product.BOLT_TSHIRT, Product.ONESIE]

InventoryPage inventory = LoginPage.open().loginAs(User.STANDARD)
Map<String, BigDecimal> prices = inventory.products().collectEntries { [(it.name): it.price] }

// Add from the inventory page
inventory.addToCart(picks)
Check.equal(inventory.header.cartCount(), picks.size(), 'Badge after adding items')
picks.each { Check.equal(inventory.buttonLabel(it), 'Remove', "${it}: button toggles to Remove") }

// The cart reflects exactly what was added
CartPage cart = inventory.header.openCart()
Check.sameItems(cart.itemNames(), picks, 'Cart contents')
Check.isTrue(cart.quantities().every { it == 1 }, 'Every cart line has quantity 1')
cart.items().each { Product item -> Check.equal(item.price, prices[item.name], "${item.name}: price in cart") }

// Remove from the cart page
cart.remove(Product.BOLT_TSHIRT)
Check.equal(cart.header.cartCount(), 2, 'Badge after removing from the cart')

// Remove from the inventory page
inventory = cart.continueShopping()
Check.equal(inventory.buttonLabel(Product.BOLT_TSHIRT), 'Add to cart', 'Removed item can be added again')
inventory.removeFromCart(Product.ONESIE)
Check.equal(inventory.header.cartCount(), 1, 'Badge after removing from the inventory')

// Reset App State empties the cart
inventory.header.resetAppState()
Check.equal(inventory.header.cartCount(), 0, 'Badge after Reset App State')
Check.isTrue(inventory.header.openCart().itemNames().isEmpty(), 'Cart is empty after Reset App State')
