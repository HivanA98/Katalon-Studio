import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.util.KeywordUtil
import com.qa.core.web.Browser

/**
 * Guarantees test isolation for web projects:
 *  - every test case starts with a fresh browser (test scripts open it through a page object)
 *  - on failure a full-page screenshot is stored in the report folder
 *  - the browser is always closed, even when the script stops halfway
 */
class WebTestListener {

	private long startedAt

	@BeforeTestCase
	def beforeTestCase(TestCaseContext context) {
		startedAt = System.currentTimeMillis()
		KeywordUtil.logInfo("▶ ${context.getTestCaseId()}")
	}

	@AfterTestCase
	def afterTestCase(TestCaseContext context) {
		String status = context.getTestCaseStatus()
		if (status in ['FAILED', 'ERROR']) {
			Browser.screenshot("${context.getTestCaseId()}_${status}")
		}
		Browser.close()
		KeywordUtil.logInfo("■ ${context.getTestCaseId()} → ${status} in ${System.currentTimeMillis() - startedAt} ms")
	}
}
