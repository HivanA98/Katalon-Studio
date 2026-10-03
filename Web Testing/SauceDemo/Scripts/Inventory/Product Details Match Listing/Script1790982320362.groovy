import com.qa.core.SoftAssert
import com.saucedemo.models.Product
import com.saucedemo.models.User
import com.saucedemo.pages.InventoryPage
import com.saucedemo.pages.LoginPage
import com.saucedemo.pages.ProductDetailPage

InventoryPage inventory = LoginPage.open().loginAs(User.STANDARD)
List<Product> listing = inventory.products()
SoftAssert soft = new SoftAssert('Product detail pages')

listing.each { Product expected ->
	ProductDetailPage details = inventory.openProduct(expected.name)

	soft.equal(details.product(), expected, "${expected.name}: name and price")
	soft.isTrue(details.description().length() > 20, "${expected.name}: has a description")
	soft.equal(details.buttonLabel(), 'Add to cart', "${expected.name}: action button")

	inventory = details.backToProducts()
}

soft.assertAll()
