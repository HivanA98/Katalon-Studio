import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.util.KeywordUtil
import com.qa.core.mobile.MobileApp

/**
 * Captures a screenshot when a mobile test fails and always closes the application.
 */
class MobileTestListener {

	@BeforeTestCase
	def beforeTestCase(TestCaseContext context) {
		KeywordUtil.logInfo("▶ ${context.getTestCaseId()}")
	}

	@AfterTestCase
	def afterTestCase(TestCaseContext context) {
		if (context.getTestCaseStatus() in ['FAILED', 'ERROR']) {
			MobileApp.screenshot("${context.getTestCaseId()}_${context.getTestCaseStatus()}")
		}
		MobileApp.close()
	}
}
