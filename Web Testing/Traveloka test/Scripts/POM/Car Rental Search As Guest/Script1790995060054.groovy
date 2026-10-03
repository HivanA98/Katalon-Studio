import com.qa.core.Check
import com.traveloka.pages.CarRentalResultsPage
import com.traveloka.pages.CarRentalSearchPage
import com.traveloka.pages.RentalProviderPage

/*
 * Guest-only flow: search -> car list -> rental providers.
 * Stops before the booking form, so no login and no personal data are needed.
 * Test case variable: city
 */
CarRentalSearchPage search = CarRentalSearchPage.open()

Map<String, String> period = search.period()
Check.isTrue(period.start?.trim() && period.end?.trim(), "Rental period is pre-filled (${period.start} -> ${period.end})")

CarRentalResultsPage results = search.searchWithoutDriver(city)

String url = results.currentUrl()
Check.contains(url, '/car-rental/search', 'Results URL')
Check.contains(url, 'driverType=WITHOUT_DRIVER', 'Driver type in the URL')
Check.contains(url, "city=${URLEncoder.encode(city, 'UTF-8')}", 'City in the URL')

List<BigDecimal> prices = results.prices()
Check.isTrue(!prices.isEmpty(), "Cars are offered in ${city} (${prices.size()} prices)")
Check.isTrue(prices.every { it > 0 }, 'Every daily price is positive')

RentalProviderPage providers = results.chooseFirstCar()
List<BigDecimal> providerPrices = providers.providerPrices()
Check.isTrue(!providerPrices.isEmpty(), 'At least one rental provider is offered for the first car')
