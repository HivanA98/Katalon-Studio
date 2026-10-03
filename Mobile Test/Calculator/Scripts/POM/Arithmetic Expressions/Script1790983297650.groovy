import com.calculator.screens.CalculatorScreen
import com.qa.core.CsvData
import com.qa.core.SoftAssert
import com.qa.core.mobile.MobileApp

/*
 * Data-driven arithmetic, including operator precedence (5+4×6 = 29, not 54).
 * Replaces the legacy "Test 1", which tapped keys but never checked the result.
 */
SoftAssert soft = new SoftAssert('Calculator expressions')

CsvData.read('Include/resources/testdata/expressions.csv').each { Map<String, String> row ->
	String actual = CalculatorScreen.launch().enter(row.expression).evaluate()
	soft.equal(actual.replace('−', '-'), row.expected, "${row.expression} =")
	MobileApp.close()
}

soft.assertAll()
