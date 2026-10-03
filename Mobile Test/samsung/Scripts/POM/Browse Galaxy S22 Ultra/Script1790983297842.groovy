import com.qa.core.Check
import com.samsungshop.screens.ProductScreen
import com.samsungshop.screens.WelcomeScreen

ProductScreen product = WelcomeScreen.launch()
	.getStarted()
	.openGalaxyS22Ultra()

Check.isTrue(product.buyNowLeadsToPurchasePage(), 'BUY NOW opens the Galaxy S22 purchase page')
