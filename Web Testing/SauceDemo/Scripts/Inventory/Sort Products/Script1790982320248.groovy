import com.qa.core.Check
import com.saucedemo.models.Product
import com.saucedemo.models.SortOption
import com.saucedemo.models.User
import com.saucedemo.pages.InventoryPage
import com.saucedemo.pages.LoginPage

InventoryPage inventory = LoginPage.open().loginAs(User.STANDARD)

Check.equal(inventory.selectedSort(), SortOption.NAME_A_TO_Z.value, 'Default sort option')

SortOption.values().each { SortOption option ->
	List<Product> shown = inventory.sortBy(option).products()

	Check.equal(inventory.selectedSort(), option.value, "${option} is selected")
	Check.equal(shown.size(), Product.catalogue().size(), "${option} keeps every product")
	Check.sorted(shown, option.order(), option.name())
}
