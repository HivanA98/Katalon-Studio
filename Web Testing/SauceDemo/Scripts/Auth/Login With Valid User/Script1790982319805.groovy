import com.qa.core.Check
import com.saucedemo.models.Product
import com.saucedemo.models.User
import com.saucedemo.pages.InventoryPage
import com.saucedemo.pages.LoginPage

InventoryPage inventory = LoginPage.open().loginAs(User.STANDARD)

Check.equal(inventory.title(), 'Products', 'Inventory page title')
Check.sameItems(inventory.productNames(), Product.catalogue(), 'Product catalogue')
Check.isTrue(inventory.products().every { it.price > 0 }, 'Every product has a positive price')
Check.equal(inventory.header.cartCount(), 0, 'Cart badge for a fresh session')
