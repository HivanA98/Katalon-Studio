import com.demoqa.models.UserForm
import com.demoqa.pages.TextBoxPage
import com.qa.core.Check

UserForm form = UserForm.fromProfile()

Map<String, String> output = TextBoxPage.open().submit(form).output()

// 'Permananet' is the label DemoQA really renders
Check.equal(output, [
	'Name'              : form.fullName,
	'Email'             : form.email,
	'Current Address'   : form.currentAddress,
	'Permananet Address': form.permanentAddress
], 'Submitted values are echoed in the output panel')
