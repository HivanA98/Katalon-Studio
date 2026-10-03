import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.SetupTestCase
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.annotation.TearDownTestCase

/**
 * Suite-level hooks. Per-test-case browser lifecycle is handled by the project's Test Listener.
 */
@SetUp(skipped = true)
def setUp() {
}

@TearDown(skipped = true)
def tearDown() {
}

@SetupTestCase(skipped = true)
def setupTestCase() {
}

@TearDownTestCase(skipped = true)
def tearDownTestCase() {
}
