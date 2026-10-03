import java.time.Duration
import java.time.Instant

import com.qa.core.Check
import com.reqres.api.ApiResponse
import com.reqres.api.JsonContract
import com.reqres.api.UsersApi

/*
 * Full CRUD flow against /api/users. ReqRes does not persist writes, so each step validates
 * the echoed payload, generated fields and timestamps instead of reading the record back.
 */
UsersApi users = new UsersApi()
String name = "katalon-${Long.toString(System.currentTimeMillis(), 36)}"

// Create
ApiResponse created = users.create(name, 'QA Engineer').expectStatus(201).expectJson()
Check.equal(created.json().name, name, 'Created name is echoed')
Check.equal(created.json().job, 'QA Engineer', 'Created job is echoed')
Check.matches(created.json().id.toString(), /\d+/, 'Generated id is numeric')
Check.matches(created.json().createdAt, JsonContract.ISO_TIMESTAMP, 'createdAt is an ISO-8601 timestamp')
Check.isTrue(Duration.between(Instant.parse(created.json().createdAt), Instant.now()).abs().toMinutes() < 10,
	'createdAt is close to the current time')

// Replace (PUT)
ApiResponse replaced = users.replace(2, name, 'Senior QA Engineer').expectStatus(200)
Check.equal(replaced.json().job, 'Senior QA Engineer', 'PUT replaces the job')
Check.matches(replaced.json().updatedAt, JsonContract.ISO_TIMESTAMP, 'PUT returns updatedAt')

// Partial update (PATCH)
ApiResponse patched = users.update(2, [job: 'QA Lead']).expectStatus(200)
Check.equal(patched.json().job, 'QA Lead', 'PATCH updates the job')
Check.isFalse(patched.json().containsKey('name'), 'PATCH only echoes the fields that were sent')

// Delete
ApiResponse deleted = users.delete(2).expectStatus(204)
Check.equal(deleted.body(), '', 'DELETE returns an empty body')
