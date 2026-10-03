import com.demoqa.models.Employee
import com.demoqa.pages.RegistrationFormModal
import com.demoqa.pages.WebTablesPage
import com.qa.core.Check

String runId = Long.toString(System.currentTimeMillis(), 36)
Employee qa = new Employee('Ivan', 'Armadi', "ivan.${runId}@example.com", '29', '15000', 'Quality')
Employee dev = new Employee('Siti', 'Rahma', "siti.${runId}@example.com", '31', '17000', 'Engineering')

WebTablesPage table = WebTablesPage.open()
int initialRows = table.rows().size()

// Create
[qa, dev].each { table.add(it) }
Check.equal(table.rows().size(), initialRows + 2, 'Two rows were added')
[qa, dev].each { Check.equal(table.rowFor(it.email), it.asRow(), "Row for ${it.email}") }

// Read / search
table.search(qa.email)
Check.equal(table.emails(), [qa.email], 'Searching by e-mail returns exactly one row')
table.search('Engineering')
Check.isTrue(table.emails().contains(dev.email), 'Searching by department finds the new engineer')
table.clearSearch()
Check.equal(table.rows().size(), initialRows + 2, 'Clearing the search shows every row again')

// Update
Employee promoted = qa.copyWith(salary: '21000', department: 'Quality Lead')
RegistrationFormModal modal = table.edit(qa.email)
Check.equal(modal.currentValues(), qa, 'Edit form is pre-filled with the current values')
table = modal.save(promoted)
Check.equal(table.rowFor(promoted.email), promoted.asRow(), 'Row shows the updated salary and department')

// Delete
table.delete(dev.email)
Check.equal(table.rowFor(dev.email), null, 'Deleted row is gone')
Check.equal(table.rows().size(), initialRows + 1, 'Row count after delete')
