import com.qa.core.Check
import com.qa.core.Config
import com.reqres.api.ApiResponse
import com.reqres.api.UsersApi

// Test case variable: delaySeconds
int budgetMs = Config.integer('maxResponseMs', 8000)
UsersApi users = new UsersApi()

// Baseline: an undelayed call stays well inside the budget
ApiResponse baseline = users.list(1).expectStatus(200).expectFasterThan(budgetMs)

// The server honours the requested delay, and the full round trip still meets the budget
ApiResponse delayed = users.listWithDelay(delaySeconds as int).expectStatus(200)
Check.isTrue(delayed.durationMs >= (delaySeconds as int) * 1000L,
	"Delayed response took ${delayed.durationMs} ms (requested delay ${delaySeconds}s)")
delayed.expectFasterThan(budgetMs + (delaySeconds as int) * 1000L)
Check.equal(delayed.json().data, baseline.json().data, 'Delayed response returns the same data as page 1')
