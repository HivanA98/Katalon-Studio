import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.util.KeywordUtil

/**
 * Logs the duration and outcome of every API test case.
 */
class ApiTestListener {

	private long startedAt

	@BeforeTestCase
	def beforeTestCase(TestCaseContext context) {
		startedAt = System.currentTimeMillis()
		KeywordUtil.logInfo("▶ ${context.getTestCaseId()}")
	}

	@AfterTestCase
	def afterTestCase(TestCaseContext context) {
		KeywordUtil.logInfo("■ ${context.getTestCaseId()} → ${context.getTestCaseStatus()} in ${System.currentTimeMillis() - startedAt} ms")
	}
}
