import com.happyday.screens.FlashSaleScreen
import com.qa.core.Check

// Test case variable: quantity
String notice = FlashSaleScreen.launch()
	.addFirstProductToBag(quantity as int)
	.goToCheckout()

Check.contains(notice.toLowerCase(), 'inquiries', 'Checkout shows the support notice')
