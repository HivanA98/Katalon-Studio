import com.qa.core.Check
import com.saucedemo.models.User
import com.saucedemo.pages.InventoryPage
import com.saucedemo.pages.LoginPage

// Test case variable: maxLoginSeconds
LoginPage login = LoginPage.open()

long startedAt = System.currentTimeMillis()
InventoryPage inventory = login.loginAs(User.PERFORMANCE_GLITCH)
BigDecimal seconds = (System.currentTimeMillis() - startedAt) / 1000

Check.isTrue(seconds <= (maxLoginSeconds as BigDecimal),
	"performance_glitch_user signed in after ${seconds}s (budget ${maxLoginSeconds}s)")
Check.equal(inventory.productNames().size(), 6, 'Catalogue is fully rendered')
