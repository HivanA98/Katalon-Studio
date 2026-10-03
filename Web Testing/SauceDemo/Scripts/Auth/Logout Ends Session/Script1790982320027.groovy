import com.qa.core.Check
import com.qa.core.web.Browser
import com.saucedemo.models.Product
import com.saucedemo.models.User
import com.saucedemo.pages.InventoryPage
import com.saucedemo.pages.LoginPage

InventoryPage inventory = LoginPage.open().loginAs(User.STANDARD)
inventory.addToCart(Product.BACKPACK)

LoginPage login = inventory.header.logout()
Check.isTrue(login.isDisplayed(), 'Logout returns to the login page')

// Deep-linking into a protected page must be rejected once the session is gone
Browser.navigate(new URI(LoginPage.baseUrl()).resolve('inventory.html').toString())

Check.isTrue(login.isDisplayed(), 'Protected page redirects to login')
Check.equal(login.error(), "Epic sadface: You can only access '/inventory.html' when you are logged in.", 'Access error')
